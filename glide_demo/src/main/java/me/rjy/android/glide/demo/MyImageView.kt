package me.rjy.android.glide.demo

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.util.Log

class MyImageView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : androidx.appcompat.widget.AppCompatImageView(context, attrs, defStyleAttr) {
    override fun setImageBitmap(bm: Bitmap?) {
        if (bm != null) {
            Log.d(TAG, "setImageBitmap: $bm")
            Throwable("[rjy] setImageBitmap").printStackTrace()
        }
        super.setImageBitmap(bm)
    }

    override fun setImageDrawable(drawable: Drawable?) {
        if (drawable != null) {
            Log.d(TAG, "setImageDrawable: $drawable")
//            Throwable("[rjy] setImageDrawable").printStackTrace()
        }
        super.setImageDrawable(drawable)
    }

    override fun setBackgroundDrawable(background: Drawable?) {
        Log.d(TAG, "setBackgroundDrawable: $background")
        super.setBackgroundDrawable(background)
    }

    companion object {
        private const val TAG = "MyImageView"
    }
}