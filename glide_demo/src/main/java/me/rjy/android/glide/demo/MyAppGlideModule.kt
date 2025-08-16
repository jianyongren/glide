package me.rjy.android.glide.demo

import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.AppGlideModule

@GlideModule
class MyAppGlideModule : AppGlideModule() {
    // 这个类用于生成Glide的代码
    // 注解处理器会自动生成必要的代码
}