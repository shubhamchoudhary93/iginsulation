package com.shubham.iginsulation

import android.content.Context
import android.os.Environment.getExternalStorageDirectory
import java.io.File
import java.io.FileOutputStream

object BackupRestore {

    fun backup(context: Context?) {
        try {
            val f = File(getExternalStorageDirectory().path + "/IGIBackup")
            f.mkdirs()
            val tableNames = listOf(
                "customer_data_table",
                "customer_data_table-journal",
                "sale_data_table",
                "sale_data_table-journal",
                "sale_details_data_table",
                "sale_details_data_table-journal",
                "stock_data_table",
                "stock_data_table-journal",
                "shop_stock_data_table",
                "shop_stock_data_table-journal",
                "shop_stock_transaction_data_table",
                "shop_stock_transaction_data_table-journal",
                "transaction_data_table",
                "transaction_data_table-journal",
            )

            getExternalStorageDirectory()
            val backupDBPath =
                getExternalStorageDirectory().path + "/IGIBackup/"
            val s = arrayOfNulls<String>(tableNames.size)
            for ((i, item) in tableNames.withIndex()) {
                context?.getDatabasePath(item)?.let {
                    copyDataFromOneToAnother(
                        it.path,
                        getExternalStorageDirectory().path + "/IGIBackup/" + "backup_" + item
                    )
                }

                val backupZipDB = File(backupDBPath, "backup_$item")
                s[i] = backupZipDB.absolutePath
            }

            ZipManager.zip(s, "$backupDBPath/igiBackup.zip")

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun restore(context: Context?) {
        try {
            val tableNames = listOf(
                "customer_data_table",
                "customer_data_table-journal",
                "sale_data_table",
                "sale_data_table-journal",
                "sale_details_data_table",
                "sale_details_data_table-journal",
                "stock_data_table",
                "stock_data_table-journal",
                "shop_stock_data_table",
                "shop_stock_data_table-journal",
                "shop_stock_transaction_data_table",
                "shop_stock_transaction_data_table-journal",
                "transaction_data_table",
                "transaction_data_table-journal",
            )

            val backupDBPath = getExternalStorageDirectory().path + "/IGIBackup/"

            val backupDBFolder = File(backupDBPath)
            ZipManager.unzip("$backupDBPath/igiBackup.zip", backupDBFolder.path)

            for (item in tableNames) {
                context?.getDatabasePath(item)?.let {
                    copyDataFromOneToAnother(
                        getExternalStorageDirectory().path + "/IGIBackup/" + "backup_" + item,
                        it.path
                    )
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun copyDataFromOneToAnother(fromPath: String, toPath: String) {
        val inStream = File(fromPath).inputStream()
        val outStream = FileOutputStream(toPath)

        inStream.use { input ->
            outStream.use { output ->
                input.copyTo(output)
            }
        }
    }
}