package com.shubham.iginsulation

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

    @Throws(IOException::class)
    fun unzip(zipFile: String?, location: String) {
        try {
            val f = File(location)
            if (!f.isDirectory) {
                f.mkdirs()
            }
            val zIn = ZipInputStream(FileInputStream(zipFile))
            zIn.use { zIn1 ->
                var ze: ZipEntry?
                while (zIn1.nextEntry.also { ze = it } != null) {
                    val path = location + File.separator.toString() + ze!!.name
                    if (ze!!.isDirectory) {
                        val unzipFile = File(path)
                        if (!unzipFile.isDirectory) {
                            unzipFile.mkdirs()
                        }
                    } else {
                        val fOut = FileOutputStream(path, false)
                        fOut.use { fOut1 ->
                            var c: Int = zIn1.read()
                            while (c != -1) {
                                fOut1.write(c)
                                c = zIn1.read()
                            }
                            zIn1.closeEntry()
                        }
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}