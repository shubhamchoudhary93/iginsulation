package com.shubham.iginsulation

import android.util.Log
import java.io.*
import java.util.zip.ZipEntry
import java.util.zip.ZipInputStream
import java.util.zip.ZipOutputStream

object ZipManager {
    private const val BUFFER_SIZE = 6 * 1024

    @Throws(IOException::class)
    fun zip(files: Array<String?>, zipFile: String?) {
        var origin: BufferedInputStream?
        val out = ZipOutputStream(BufferedOutputStream(FileOutputStream(zipFile)))
        out.use { out1 ->
            val data = ByteArray(BUFFER_SIZE)
            for (i in files.indices) {
                val fi = FileInputStream(files[i])
                origin = BufferedInputStream(fi, BUFFER_SIZE)
                try {
                    val entry = ZipEntry(files[i]?.lastIndexOf("/")?.plus(1)?.let {
                        files[i]?.substring(
                            it
                        )
                    })
                    out1.putNextEntry(entry)
                    var count: Int
                    while (origin!!.read(data, 0, BUFFER_SIZE).also { count = it } != -1) {
                        out1.write(data, 0, count)
                    }
                } finally {
                    origin!!.close()
                }
            }
        }
    }

    fun unzip(zipFile: String?, location: String) {
        try {
            val inputStream = FileInputStream(zipFile)
            val zipStream = ZipInputStream(inputStream)
            var zEntry: ZipEntry? = null
            while (zipStream.nextEntry.also { zEntry = it } != null) {
                Log.d(
                    "Unzip", "Unzipping " + zEntry!!.name + " at "
                            + location
                )
                if (zEntry!!.isDirectory) {
                    handleDirectory(zEntry!!.name,location)
                } else {
                    val fout = FileOutputStream(
                        location + "/" + zEntry!!.name
                    )
                    val bufout = BufferedOutputStream(fout)
                    val buffer = ByteArray(1024)
                    var read = 0
                    while (zipStream.read(buffer).also { read = it } != -1) {
                        bufout.write(buffer, 0, read)
                    }
                    zipStream.closeEntry()
                    bufout.close()
                    fout.close()
                }
            }
            zipStream.close()
            Log.d("Unzip", "Unzipping complete. path :  $location")
        } catch (e: java.lang.Exception) {
            Log.d("Unzip", "Unzipping failed")
            e.printStackTrace()
        }
    }

    private fun handleDirectory(dir: String, location: String) {
        val f = File(location + dir)
        if (!f.isDirectory) {
            f.mkdirs()
        }
    }
}