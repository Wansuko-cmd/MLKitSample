package com.sample

import android.graphics.Bitmap
import android.media.Image

interface VisionRepository {
    fun detectText(mediaImage: Image): String
}