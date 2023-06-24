package com.sample

import android.media.Image

interface TextRecognizeRepository {
    suspend fun recognize(mediaImage: Image): String
}
