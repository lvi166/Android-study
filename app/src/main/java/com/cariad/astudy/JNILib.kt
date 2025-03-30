package com.cariad.astudy

import android.graphics.Bitmap

class JNILib {


    external fun stringFromJNI(): String

    external fun test(): String

    external fun invertColor(
        originalBitmap: Bitmap,
        invertedBitmap: Bitmap
    ): Bitmap

    companion object {
        init {
            System.loadLibrary("native-lib")
        }
    }

}