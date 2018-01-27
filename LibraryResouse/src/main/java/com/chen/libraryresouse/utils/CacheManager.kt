package com.chen.libraryresouse.utils

import android.content.Context
import android.os.Build
import android.support.annotation.RequiresApi
import java.util.*
import java.util.concurrent.atomic.AtomicInteger
import java.util.concurrent.atomic.AtomicLong
import java.io.*
import org.json.JSONObject
import org.json.JSONArray
import android.graphics.Bitmap
import android.graphics.drawable.Drawable


/**
 * Created by chenyuelun on 2018/1/27.
 */
class CacheManager private constructor(cacheDir: File, maxSize: Long, max_count: Int) {

    companion object {
        private const val TIME_HOUR = 60 * 60
        private const val TIME_DAY = TIME_HOUR * 24
        private const val MAX_SIZE = 50000000L  // 50 mb
        private const val MAX_COUNT = Integer.MAX_VALUE // 不限制存放数据的数量
        private val mInstanceMap = HashMap<String, CacheManager>()

        operator fun get(ctx: Context): CacheManager {
            return get(ctx, "ACache")
        }

        operator fun get(ctx: Context, cacheName: String): CacheManager {
            val f = File(ctx.cacheDir, cacheName)
            return get(f, MAX_SIZE, MAX_COUNT)
        }

        operator fun get(cacheDir: File): CacheManager {
            return get(cacheDir, MAX_SIZE, MAX_COUNT)
        }

        operator fun get(ctx: Context, maxSize: Long, max_count: Int): CacheManager {
            val f = File(ctx.cacheDir, "ACache")
            return get(f, maxSize, max_count)
        }

        operator fun get(cacheDir: File, maxSize: Long, max_count: Int): CacheManager {
            var manager = mInstanceMap[cacheDir.absolutePath + myPid()]
            if (manager == null) {
                manager = CacheManager(cacheDir, maxSize, max_count)
                mInstanceMap[cacheDir.absolutePath + myPid()] = manager
            }
            return manager
        }

        private fun myPid(): String {
            return "_" + android.os.Process.myPid()
        }

    }

    private lateinit var mCache: ACacheManager

// =======================================
    // ============ String数据 读写 ==============
    // =======================================
    /**
     * 保存 String数据 到 缓存中
     *
     * @param key
     * 保存的key
     * @param value
     * 保存的String数据
     */
    fun put(key: String, value: String) {
        val file = mCache.newFile(key)
        var out: BufferedWriter? = null
        try {
            out = BufferedWriter(FileWriter(file), 1024)
            out.write(value)
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            if (out != null) {
                try {
                    out.flush()
                    out.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }

            }
            mCache.put(file)
        }
    }

    /**
     * 保存 String数据 到 缓存中
     *
     * @param key
     * 保存的key
     * @param value
     * 保存的String数据
     * @param saveTime
     * 保存的时间，单位：秒
     */
    fun put(key: String, value: String, saveTime: Int) {
        put(key, Utils.newStringWithDateInfo(saveTime, value))
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private
            /**
             * 读取 String数据
             *
             * @param key
             * @return String 数据
             */
    fun getAsString(key: String): String? {
        val file = mCache[key]
        if (!file.exists())
            return null
        var removeFile = false
        var input: BufferedReader? = null
        try {
            input = BufferedReader(FileReader(file))
            var readString = ""
            var currentLine: String
            while ((input.readLine()) != null) {
                currentLine = input.readLine()
                readString += currentLine
            }
            if (!Utils.isDue(readString)) {
                return Utils.clearDateInfo(readString.toByteArray()).toString()
            } else {
                removeFile = true
                return null
            }
        } catch (e: IOException) {
            e.printStackTrace()
            return null
        } finally {
            if (input != null) {
                try {
                    input.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }

            }
            if (removeFile)
                remove(key)
        }

    }


    /**
     * 保存 JSONObject数据 到 缓存中
     *
     * @param key
     * 保存的key
     * @param value
     * 保存的JSONObject数据
     * @param saveTime
     * 保存的时间，单位：秒
     */
    fun put(key: String, value: JSONObject, saveTime: Int) {
        put(key, value.toString(), saveTime)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
            /**
             * 读取JSONObject数据
             *
             * @param key
             * @return JSONObject数据
             */
    fun getAsJSONObject(key: String): JSONObject? {
        val JSONString = getAsString(key)
        try {
            return JSONObject(JSONString)
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }

    }

    // =======================================
    // ============ JSONArray 数据 读写 =============
    // =======================================
    /**
     * 保存 JSONArray数据 到 缓存中
     *
     * @param key
     * 保存的key
     * @param value
     * 保存的JSONArray数据
     */
    fun put(key: String, value: JSONArray) {
        put(key, value.toString())
    }

    /**
     * 保存 JSONArray数据 到 缓存中
     *
     * @param key
     * 保存的key
     * @param value
     * 保存的JSONArray数据
     * @param saveTime
     * 保存的时间，单位：秒
     */
    fun put(key: String, value: JSONArray, saveTime: Int) {
        put(key, value.toString(), saveTime)
    }


    /**
     * 读取JSONArray数据
     *
     * @param key
     * @return JSONArray数据
     */
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun getAsJSONArray(key: String): JSONArray? {
        val mJSONString = getAsString(key)
        return try {
            JSONArray(mJSONString)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }

    }


    // =======================================
    // ============== byte 数据 读写 =============
    // =======================================
    /**
     * 保存 byte数据 到 缓存中
     *
     * @param key
     * 保存的key
     * @param value
     * 保存的数据
     */
    fun put(key: String, value: ByteArray) {
        val file = mCache.newFile(key)
        var out: FileOutputStream? = null
        try {
            out = FileOutputStream(file)
            out.write(value)
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            if (out != null) {
                try {
                    out.flush()
                    out.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }

            }
            mCache.put(file)
        }
    }

    /**
     * 保存 byte数据 到 缓存中
     *
     * @param key
     * 保存的key
     * @param value
     * 保存的数据
     * @param saveTime
     * 保存的时间，单位：秒
     */
    private fun put(key: String, value: ByteArray, saveTime: Int) {
        put(key, Utils.newByteArrayWithDateInfo(saveTime, value))
    }


    /**
     * 获取 byte 数据
     *
     * @param key
     * @return byte 数据
     */
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun getAsBinary(key: String): ByteArray? {
        var RAFile: RandomAccessFile? = null
        var removeFile = false
        try {
            val file = mCache[key]
            if (!file.exists())
                return null
            RAFile = RandomAccessFile(file, "r")
            val byteArray = ByteArray(RAFile.length().toInt())
            RAFile.read(byteArray)
            if (!Utils.isDue(byteArray)) {
                return Utils.clearDateInfo(byteArray)
            } else {
                removeFile = true
                return null
            }
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        } finally {
            if (RAFile != null) {
                try {
                    RAFile.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }

            }
            if (removeFile)
                remove(key)
        }
    }

    // =======================================
    // ============= 序列化 数据 读写 ===============
    // =======================================
    /**
     * 保存 Serializable数据 到 缓存中
     *
     * @param key
     * 保存的key
     * @param value
     * 保存的value
     */
    fun put(key: String, value: Serializable) {
        put(key, value, -1)
    }

    /**
     * 保存 Serializable数据到 缓存中
     *
     * @param key
     * 保存的key
     * @param value
     * 保存的value
     * @param saveTime
     * 保存的时间，单位：秒
     */
    fun put(key: String, value: Serializable, saveTime: Int) {
        var baos: ByteArrayOutputStream? = null
        var oos: ObjectOutputStream? = null
        try {
            baos = ByteArrayOutputStream()
            oos = ObjectOutputStream(baos)
            oos.writeObject(value)
            val data = baos.toByteArray()
            if (saveTime != -1) {
                put(key, data, saveTime)
            } else {
                put(key, data)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            try {
                oos!!.close()
            } catch (e: IOException) {
            }

        }
    }


    /**
     * 读取 Serializable数据
     *
     * @param key
     * @return Serializable 数据
     */
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun getAsObject(key: String): Any? {
        val data = getAsBinary(key)
        if (data != null) {
            var bais: ByteArrayInputStream? = null
            var ois: ObjectInputStream? = null
            try {
                bais = ByteArrayInputStream(data)
                ois = ObjectInputStream(bais)
                return ois.readObject()
            } catch (e: Exception) {
                e.printStackTrace()
                return null
            } finally {
                try {
                    if (bais != null)
                        bais.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }

                try {
                    if (ois != null)
                        ois.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }

            }
        }
        return null

    }

    // =======================================
    // ============== bitmap 数据 读写 =============
    // =======================================
    /**
     * 保存 bitmap 到 缓存中
     *
     * @param key
     * 保存的key
     * @param value
     * 保存的bitmap数据
     */
    private fun put(key: String, value: Bitmap) {
        put(key, Utils.Bitmap2Bytes(value)!!)
    }

    /**
     * 保存 bitmap 到 缓存中
     *
     * @param key
     * 保存的key
     * @param value
     * 保存的 bitmap 数据
     * @param saveTime
     * 保存的时间，单位：秒
     */
    private fun put(key: String, value: Bitmap, saveTime: Int) {
        put(key, Utils.Bitmap2Bytes(value)!!, saveTime)
    }


    /**
     * 读取 bitmap 数据
     *
     * @param key
     * @return bitmap 数据
     */
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun getAsBitmap(key: String): Bitmap? {
        return if (getAsBinary(key) == null) {
            null
        } else Utils.Bytes2Bimap(getAsBinary(key)!!)
    }


    // =======================================
    // ============= drawable 数据 读写 =============
    // =======================================
    /**
     * 保存 drawable 到 缓存中
     *
     * @param key
     * 保存的key
     * @param value
     * 保存的drawable数据
     */
    fun put(key: String, value: Drawable) {
        put(key, Utils.drawable2Bitmap(value)!!)
    }

    /**
     * 保存 drawable 到 缓存中
     *
     * @param key
     * 保存的key
     * @param value
     * 保存的 drawable 数据
     * @param saveTime
     * 保存的时间，单位：秒
     */
    fun put(key: String, value: Drawable, saveTime: Int) {
        put(key, Utils.drawable2Bitmap(value)!!, saveTime)
    }

    /**
     * 读取 Drawable 数据
     *
     * @param key
     * @return Drawable 数据
     */
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun getAsDrawable(key: String): Drawable? {
        return if (getAsBinary(key) == null) {
            null
        } else Utils.bitmap2Drawable(Utils.Bytes2Bimap(getAsBinary(key)!!))
    }


    /**
     * 获取缓存文件
     *
     * @param key
     * @return value 缓存的文件
     */
    fun file(key: String): File? {
        val f = mCache.newFile(key)
        return if (f.exists()) f else null
    }

    /**
     * 移除某个key
     *
     * @param key
     * @return 是否移除成功
     */
    private fun remove(key: String): Boolean {
        return mCache.remove(key)
    }

    /**
     * 清除所有数据
     */
    fun clear() {
        mCache.clear()
    }
    inner class ACacheManager private constructor(private val cacheDir: File, private val sizeLimit: Long, private val countLimit: Int) {
        private val cacheSize = AtomicLong()
        private val cacheCount = AtomicInteger()
        private val lastUsageDates = Collections
                .synchronizedMap(HashMap<File, Long>())


        /**
         * 计算 cacheSize和cacheCount
         */
        private fun calculateCacheSizeAndCacheCount() {
            Thread(Runnable {
                var size = 0L
                var count = 0
                val cachedFiles = cacheDir.listFiles()
                if (cachedFiles != null) {
                    for (cachedFile in cachedFiles) {
                        size += calculateSize(cachedFile)
                        count += 1
                        lastUsageDates[cachedFile] = cachedFile.lastModified()
                    }
                    cacheSize.set(size)
                    cacheCount.set(count)
                }
            }).start()
        }

        fun put(file: File) {
            var curCacheCount = cacheCount.get()
            while (curCacheCount + 1 > countLimit) {
                val freedSize = removeNext()
                cacheSize.addAndGet(-freedSize)

                curCacheCount = cacheCount.addAndGet(-1)
            }
            cacheCount.addAndGet(1)

            val valueSize = calculateSize(file)
            var curCacheSize = cacheSize.get()
            while (curCacheSize + valueSize > sizeLimit) {
                val freedSize = removeNext()
                curCacheSize = cacheSize.addAndGet(-freedSize)
            }
            cacheSize.addAndGet(valueSize)

            val currentTime = System.currentTimeMillis()
            file.setLastModified(currentTime)
            lastUsageDates[file] = currentTime
        }

        operator fun get(key: String): File {
            val file = newFile(key)
            val currentTime = System.currentTimeMillis()
            file.setLastModified(currentTime)
            lastUsageDates[file] = currentTime

            return file
        }

        fun newFile(key: String): File {
            return File(cacheDir, key.hashCode().toString() + "")
        }

        fun remove(key: String): Boolean {
            val image = get(key)
            return image.delete()
        }

        fun clear() {
            lastUsageDates.clear()
            cacheSize.set(0)
            val files = cacheDir.listFiles()
            if (files != null) {
                for (f in files) {
                    f.delete()
                }
            }
        }

        /**
         * 移除旧的文件
         *
         * @return
         */
        private fun removeNext(): Long {
            if (lastUsageDates.isEmpty()) {
                return 0
            }

            var oldestUsage: Long? = null
            var mostLongUsedFile: File? = null
            val entries = lastUsageDates.entries
            synchronized(lastUsageDates) {
                for (entry in entries) {
                    if (mostLongUsedFile == null) {
                        mostLongUsedFile = entry.key
                        oldestUsage = entry.value
                    } else {
                        val lastValueUsage = entry.value
                        if (lastValueUsage < oldestUsage!!) {
                            oldestUsage = lastValueUsage
                            mostLongUsedFile = entry.key
                        }
                    }
                }
            }

            val fileSize = calculateSize(mostLongUsedFile!!)
            if (mostLongUsedFile!!.delete()) {
                lastUsageDates.remove(mostLongUsedFile)
            }
            return fileSize
        }

        private fun calculateSize(file: File): Long {
            return file.length()
        }

    }


}

