package com.shubham.iginsulation

import android.app.ProgressDialog
import android.content.Context
import android.net.Uri
import android.view.View
import android.widget.Toast
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.shubham.iginsulation.databinding.FragmentSettingsBinding
import com.shubham.iginsulation.databinding.FragmentStartBinding
import java.io.File
import java.io.FileOutputStream


object BackupRestore {

    fun backup(context: Context?, binding: FragmentSettingsBinding) {
        try {
            val f = File(context?.getExternalFilesDir(null)?.path + "/IGIBackup")
            f.mkdirs()
            val tableNames = listOf(
                "customer_data_table",
                "customer_data_table-shm",
                "customer_data_table-wal",
                "sale_data_table",
                "sale_data_table-shm",
                "sale_data_table-wal",
                "sale_details_data_table",
                "sale_details_data_table-shm",
                "sale_details_data_table-wal",
                "stock_data_table",
                "stock_data_table-shm",
                "stock_data_table-wal",
                "shop_stock_data_table",
                "shop_stock_data_table-shm",
                "shop_stock_data_table-wal",
                "shop_stock_transaction_data_table",
                "shop_stock_transaction_data_table-shm",
                "shop_stock_transaction_data_table-wal",
                "transaction_data_table",
                "transaction_data_table-shm",
                "transaction_data_table-wal",
                "rate_data_table",
                "rate_data_table-shm",
                "rate_data_table-wal"
            )

            binding.notifications.text = "Backup Started"
            val backupDBPath =
                context?.getExternalFilesDir(null)?.path + "/IGIBackup/"
            val s = arrayOfNulls<String>(tableNames.size)
            for ((i, item) in tableNames.withIndex()) {
                context?.getDatabasePath(item)?.let {
                    copyDataFromOneToAnother(
                        it.path,
                        context.getExternalFilesDir(null)?.path + "/IGIBackup/" + "backup_" + item
                    )
                }

                val backupZipDB = File(backupDBPath, "backup_$item")
                s[i] = backupZipDB.absolutePath
            }

            ZipManager.zip(s, "$backupDBPath/igiBackup.zip")

            binding.notifications.text =
                binding.notifications.text.toString() + "\nBackup File created."
            val filePath: Uri? = Uri.fromFile(File("$backupDBPath/igiBackup.zip"))

            if (filePath != null) {
                val progressDialog = ProgressDialog(context)
                progressDialog.setTitle("Uploading")
                progressDialog.show()
                val storage = FirebaseStorage.getInstance()
                val storageReference = storage.reference
                val riversRef: StorageReference = storageReference.child("igi_backup/igiBackup.zip")

                riversRef.putFile(filePath)
                    .addOnSuccessListener {
                        progressDialog.dismiss()

//                        val metadata = StorageMetadata.Builder()
//                            .setCustomMetadata("version", "1")
//                            .build()
//
//                        riversRef.updateMetadata(metadata)

                        binding.notifications.text =
                            binding.notifications.text.toString() + "\nBackup File uploaded."
                    }
                    .addOnFailureListener { exception ->
                        progressDialog.dismiss()
                        Toast.makeText(context, exception.message, Toast.LENGTH_LONG).show()
                    }
                    .addOnProgressListener { taskSnapshot ->
                        val progress =
                            100.0 * taskSnapshot.bytesTransferred / taskSnapshot.totalByteCount

                        progressDialog.setMessage("Uploaded " + progress.toInt() + "%...")
                    }
            }

        } catch (e: Exception) {
            binding.notifications.text =
                binding.notifications.text.toString() + "\n" + e.stackTraceToString()
            e.printStackTrace()
        }
    }

    fun restore(context: Context?, binding: FragmentStartBinding) {

        val storage = FirebaseStorage.getInstance()
        val storageRef = storage.reference
        val riversRef: StorageReference = storageRef.child("igi_backup/igiBackup.zip")

//        riversRef.metadata.addOnSuccessListener {metadata ->
//            val version = metadata.getCustomMetadata("version")
//            println("version = $version")
//        }.addOnFailureListener{
//            println("problem")
//        }

        binding.loader.progress = 5
        val rootPath = File(context?.getExternalFilesDir(null)?.path, "IGIBackup")
        if (!rootPath.exists()) {
            rootPath.mkdirs()
        }
        val localFile = File(rootPath, "igiBackup.zip")
       binding.loader.progress += 10

        riversRef.getFile(localFile)
            .addOnSuccessListener {

                binding.loader.progress += 50
                try {
                    val tableNames = listOf(
                        "customer_data_table",
                        "customer_data_table-shm",
                        "customer_data_table-wal",
                        "sale_data_table",
                        "sale_data_table-shm",
                        "sale_data_table-wal",
                        "sale_details_data_table",
                        "sale_details_data_table-shm",
                        "sale_details_data_table-wal",
                        "stock_data_table",
                        "stock_data_table-shm",
                        "stock_data_table-wal",
                        "shop_stock_data_table",
                        "shop_stock_data_table-shm",
                        "shop_stock_data_table-wal",
                        "shop_stock_transaction_data_table",
                        "shop_stock_transaction_data_table-shm",
                        "shop_stock_transaction_data_table-wal",
                        "transaction_data_table",
                        "transaction_data_table-shm",
                        "transaction_data_table-wal",
                        "rate_data_table",
                        "rate_data_table-shm",
                        "rate_data_table-wal"
                    )

                    val backupDBPath = context?.getExternalFilesDir(null)?.path + "/IGIBackup/"

                    val backupDBFolder = File(backupDBPath)
                    ZipManager.unzip("$backupDBPath/igiBackup.zip", backupDBFolder.path)

                    binding.loader.progress += 10
                    for (item in tableNames) {
                        context?.getDatabasePath(item)?.let {
                            copyDataFromOneToAnother(
                                context.getExternalFilesDir(null)?.path + "/IGIBackup/" + "backup_" + item,
                                it.path
                            )

                            binding.loader.progress+= 1
                        }
                    }

                } catch (e: Exception) {
                    e.printStackTrace()
                }
                binding.restoreLayout.visibility = View.GONE
                binding.buttonsLayout.visibility = View.VISIBLE
            }
            .addOnFailureListener { exception ->
                Toast.makeText(context, exception.message, Toast.LENGTH_LONG).show()
            }
    }

    private fun copyDataFromOneToAnother(
        fromPath: String,
        toPath: String
    ) {
        val inStream = File(fromPath).inputStream()
        val outStream = FileOutputStream(toPath)

        inStream.use { input ->
            outStream.use { output ->
                input.copyTo(output)
            }
        }
    }
}