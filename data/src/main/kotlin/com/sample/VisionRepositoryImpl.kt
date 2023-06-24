package com.sample

import android.media.Image
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.TextRecognizer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class VisionRepositoryImpl @Inject constructor(
    private val textRecognizer: TextRecognizer,
) : VisionRepository {
    override suspend fun detectText(mediaImage: Image): String = withContext(Dispatchers.IO) {
        textRecognizer
            .processWith(InputImage.fromMediaImage(mediaImage, 0))
            .text
    }
}
