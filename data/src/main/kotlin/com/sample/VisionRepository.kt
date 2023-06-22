package com.sample

import android.graphics.Bitmap

interface VisionRepository {
    fun detectText(bitmap: Bitmap): String
}