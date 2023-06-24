package com.sample

import android.media.Image
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.TextRecognizer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TextRecognizeRepositoryImpl @Inject constructor(
    private val textRecognizer: TextRecognizer,
) : TextRecognizeRepository {
    override suspend fun recognize(mediaImage: Image): String = withContext(Dispatchers.IO) {
        textRecognizer
            .processWith(InputImage.fromMediaImage(mediaImage, 0))
            .text
    }
}
