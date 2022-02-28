package com.shubham.iginsulation

import android.content.Context
import android.net.Uri
import android.view.View
import android.widget.Toast
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.shubham.iginsulation.databinding.FragmentStartBinding
import java.io.File
import java.io.FileOutputStream

object BackupRestore {

    fun backup(context: Context?, which: String) {
        try {
            val newTableNames =
                listOf("${which}_data_table", "${which}_data_table-shm", "${which}_data_table-wal")
            val f = File(context?.getExternalFilesDir(null)?.path + "/IGIBackup")
            f.mkdirs()

            val backupDBPath =
                context?.getExternalFilesDir(null)?.path + "/IGIBackup/"


            val s = arrayOfNulls<String>(newTableNames.size)
            for ((i, item) in newTableNames.withIndex()) {
                context?.getDatabasePath(item)?.let {
                    copyDataFromOneToAnother(
                        it.path,
                        context.getExternalFilesDir(null)?.path + "/IGIBackup/" + "backup_" + item
                    )
                }

                val backupZipDB = File(backupDBPath, "backup_$item")
                s[i] = backupZipDB.absolutePath
            }

            ZipManager.zip(s, "$backupDBPath/${which}.zip")
            val filePath: Uri? = Uri.fromFile(File("$backupDBPath/${which}.zip"))

            if (filePath != null) {
                val storage = FirebaseStorage.getInstance()
                val storageReference = storage.reference
                val riversRef: StorageReference = storageReference.child("igi_backup/${which}.zip")

                riversRef.putFile(filePath).addOnFailureListener { exception ->
                    Toast.makeText(context, exception.message, Toast.LENGTH_LONG).show()
                }
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun restore(context: Context?, binding: FragmentStartBinding) {
        val tables = listOf(
            "customer",
            "sale",
            "sale_details",
            "stock",
            "shop_stock",
            "shop_stock_transaction",
            "transaction",
            "rate"
        )

        binding.loader.progress = 10
        for (table in tables) {
            val storage = FirebaseStorage.getInstance()
            val storageRef = storage.reference
            val riversRef: StorageReference = storageRef.child("igi_backup/${table}.zip")
            val rootPath = File(context?.getExternalFilesDir(null)?.path, "IGIBackup")
            if (!rootPath.exists()) {
                rootPath.mkdirs()
            }
            val localFile = File(rootPath, "${table}.zip")
            riversRef.getFile(localFile)
                .addOnSuccessListener {
                    try {

                        val backupDBPath = context?.getExternalFilesDir(null)?.path + "/IGIBackup/"

                        val backupDBFolder = File(backupDBPath)
                        ZipManager.unzip("${backupDBPath}/${table}.zip", backupDBFolder.path)

                        var item = "${table}_data_table"
                        context?.getDatabasePath(item)?.let {
                            copyDataFromOneToAnother(
                                context.getExternalFilesDir(null)?.path + "/IGIBackup/" + "backup_" + item,
                                it.path
                            )
                        }

                        item = "${table}_data_table-shm"
                        context?.getDatabasePath(item)?.let {
                            copyDataFromOneToAnother(
                                context.getExternalFilesDir(null)?.path + "/IGIBackup/" + "backup_" + item,
                                it.path
                            )
                        }

                        item = "${table}_data_table-wal"
                        context?.getDatabasePath(item)?.let {
                            copyDataFromOneToAnother(
                                context.getExternalFilesDir(null)?.path + "/IGIBackup/" + "backup_" + item,
                                it.path
                            )
                        }

                        binding.loader.progress += 10
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
                .addOnFailureListener { exception ->
                    Toast.makeText(context, exception.message, Toast.LENGTH_LONG).show()
                }
        }

        binding.restoreLayout.visibility = View.GONE
        binding.buttonsLayout.visibility = View.VISIBLE
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