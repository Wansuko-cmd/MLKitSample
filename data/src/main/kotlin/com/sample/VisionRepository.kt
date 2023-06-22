package com.sample

import android.media.Image

interface VisionRepository {
    fun detectText(mediaImage: Image)
}
