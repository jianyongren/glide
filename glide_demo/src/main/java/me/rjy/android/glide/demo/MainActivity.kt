package me.rjy.android.glide.demo

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import me.rjy.android.glide.demo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imageView.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        binding.clearCache.setOnClickListener {
            Log.d(TAG, "glide clearMemory")
            Glide.get(this@MainActivity).clearMemory()
            Thread {
                Log.d(TAG, "glide clearDiskCache")
                Glide.get(this@MainActivity).clearDiskCache()
                Thread.sleep(3000)
                restartApp(this@MainActivity)
            }.start()
        }

        // 使用一个更可靠的测试图片URL
        val imageUrl = "https://images.pexels.com/photos/1640777/pexels-photo-1640777.jpeg"
        Log.d(TAG, "[rjy] load $imageUrl")
        
        // 添加错误处理和占位符
        Glide.with(this)
            .load(imageUrl)
//            .placeholder(android.R.drawable.ic_menu_gallery)
//            .error(android.R.drawable.ic_menu_report_image)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>,
                    isFirstResource: Boolean
                ): Boolean {
                    Log.e(TAG, "Image load failed: ${e?.message}")
                    e?.logRootCauses(TAG)
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable,
                    model: Any,
                    target: Target<Drawable>?,
                    dataSource: DataSource,
                    isFirstResource: Boolean
                ): Boolean {
                    Log.d(TAG, "Image loaded successfully from: $dataSource")
                    return false
                }
            })
            .into(binding.imageView)

    }

    fun restartApp(context: Context) {
        Log.d(TAG, "restartApp")
        val intent = context.packageManager.getLaunchIntentForPackage(context.packageName)?.apply {
            flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or  // 清除任务栈
                    Intent.FLAG_ACTIVITY_NEW_TASK      // 创建新任务栈
        }
        context.startActivity(intent)
        (context as? Activity)?.finishAffinity()        // 终止当前进程所有Activity
        Runtime.getRuntime().exit(0)
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}