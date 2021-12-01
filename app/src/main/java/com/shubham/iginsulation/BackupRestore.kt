package com.shubham.iginsulation

import android.app.ProgressDialog
import android.content.Context
import android.net.Uri
import android.widget.Toast
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.io.File
import java.io.FileOutputStream

object BackupRestore {

    fun backup(context: Context?) {
        try {
            val f = File(context?.getExternalFilesDir(null)?.path + "/IGIBackup")
            f.mkdirs()
            val tableNames = listOf(
                "customer_data_table",
                "customer_data_table-journal",
                "customer_data_table-shm",
                "customer_data_table-wal",
                "sale_data_table",
                "sale_data_table-journal",
                "sale_data_table-shm",
                "sale_data_table-wal",
                "sale_details_data_table",
                "sale_details_data_table-journal",
                "sale_details_data_table-shm",
                "sale_details_data_table-wal",
                "stock_data_table",
                "stock_data_table-journal",
                "stock_data_table-shm",
                "stock_data_table-wal",
                "shop_stock_data_table",
                "shop_stock_data_table-journal",
                "shop_stock_data_table-shm",
                "shop_stock_data_table-wal",
                "shop_stock_transaction_data_table",
                "shop_stock_transaction_data_table-journal",
                "shop_stock_transaction_data_table-shm",
                "shop_stock_transaction_data_table-wal",
                "transaction_data_table",
                "transaction_data_table-journal",
                "transaction_data_table-shm",
                "transaction_data_table-wal",
            )

            val backupDBPath =
                context?.getExternalFilesDir(null)?.path + "/IGIBackup/"
            val s = arrayOfNulls<String>(tableNames.size)
            for ((i, item) in tableNames.withIndex()) {
                context?.getDatabasePath(item)?.let {
                    copyDataFromOneToAnother(
                        it.path,
                        context?.getExternalFilesDir(null)?.path + "/IGIBackup/" + "backup_" + item
                    )
                }

                val backupZipDB = File(backupDBPath, "backup_$item")
                s[i] = backupZipDB.absolutePath
            }

            ZipManager.zip(s, "$backupDBPath/igiBackup.zip")

            val filePath: Uri? = Uri.fromFile(File("$backupDBPath/igiBackup.zip"))

            if (filePath != null) {
                //displaying a progress dialog while upload is going on
                val progressDialog = ProgressDialog(context)
                progressDialog.setTitle("Uploading")
                progressDialog.show()
                val storage = FirebaseStorage.getInstance()
                val storageReference = storage.reference
                val riversRef: StorageReference = storageReference.child("igi_backup/igiBackup.zip")
                riversRef.putFile(filePath)
                    .addOnSuccessListener { //if the upload is successfull
                        //hiding the progress dialog
                        progressDialog.dismiss()

                        //and displaying a success toast
                        Toast.makeText(context, "File Uploaded ", Toast.LENGTH_LONG).show()
                    }
                    .addOnFailureListener { exception -> //if the upload is not successfull
                        //hiding the progress dialog
                        progressDialog.dismiss()

                        //and displaying error message
                        Toast.makeText(context, exception.message, Toast.LENGTH_LONG).show()
                    }
                    .addOnProgressListener { taskSnapshot -> //calculating progress percentage
                        val progress =
                            100.0 * taskSnapshot.bytesTransferred / taskSnapshot.totalByteCount

                        //displaying percentage in progress dialog
                        progressDialog.setMessage("Uploaded " + progress.toInt() + "%...")
                    }
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun restore(context: Context?) {

        val storage = FirebaseStorage.getInstance()
        val storageRef = storage.reference
        val riversRef: StorageReference = storageRef.child("igi_backup/igiBackup.zip")
        val rootPath = File(context?.getExternalFilesDir(null)?.path, "IGIBackup")
        if (!rootPath.exists()) {
            rootPath.mkdirs()
        }
        val localFile = File(rootPath, "igiBackup.zip")
        val progressDialog = ProgressDialog(context)
        progressDialog.setTitle("Downloading")
        progressDialog.show()
        riversRef.getFile(localFile)
            .addOnSuccessListener { //if the upload is successfull
                //hiding the progress dialog
                try {
                    val tableNames = listOf(
                        "customer_data_table",
                        "customer_data_table-journal",
                        "customer_data_table-shm",
                        "customer_data_table-wal",
                        "sale_data_table",
                        "sale_data_table-journal",
                        "sale_data_table-shm",
                        "sale_data_table-wal",
                        "sale_details_data_table",
                        "sale_details_data_table-journal",
                        "sale_details_data_table-shm",
                        "sale_details_data_table-wal",
                        "stock_data_table",
                        "stock_data_table-journal",
                        "stock_data_table-shm",
                        "stock_data_table-wal",
                        "shop_stock_data_table",
                        "shop_stock_data_table-journal",
                        "shop_stock_data_table-shm",
                        "shop_stock_data_table-wal",
                        "shop_stock_transaction_data_table",
                        "shop_stock_transaction_data_table-journal",
                        "shop_stock_transaction_data_table-shm",
                        "shop_stock_transaction_data_table-wal",
                        "transaction_data_table",
                        "transaction_data_table-journal",
                        "transaction_data_table-shm",
                        "transaction_data_table-wal",
                    )

                    val backupDBPath = context?.getExternalFilesDir(null)?.path + "/IGIBackup/"

                    val backupDBFolder = File(backupDBPath)
                    ZipManager.unzip("$backupDBPath/igiBackup.zip", backupDBFolder.path)

                    for (item in tableNames) {
                        println("inside loop")
                        context?.getDatabasePath(item)?.let {
                            copyDataFromOneToAnother(
                                context.getExternalFilesDir(null)?.path + "/IGIBackup/" + "backup_" + item,
                                it.path
                            )
                        }
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }

                progressDialog.dismiss()
                //and displaying a success toast
                Toast.makeText(context, "File Downloaded ", Toast.LENGTH_LONG).show()
            }
            .addOnFailureListener { exception -> //if the upload is not successfull
                //hiding the progress dialog
                progressDialog.dismiss()

                //and displaying error message
                Toast.makeText(context, exception.message, Toast.LENGTH_LONG).show()
            }
            .addOnProgressListener { taskSnapshot -> //calculating progress percentage
                val progress =
                    100.0 * taskSnapshot.bytesTransferred / taskSnapshot.totalByteCount

                //displaying percentage in progress dialog
                progressDialog.setMessage("Downloaded " + progress.toInt() + "%...")
            }


    }

    private fun copyDataFromOneToAnother(fromPath: String, toPath: String) {
        val inStream = File(fromPath).inputStream()
        val outStream = FileOutputStream(toPath)

        println("copying from $fromPath to $toPath")
        inStream.use { input ->
            outStream.use { output ->
                input.copyTo(output)
            }
        }
    }
}