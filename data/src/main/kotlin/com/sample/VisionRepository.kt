package com.sample

import android.media.Image

interface VisionRepository {
    suspend fun detectText(mediaImage: Image): String
}
