package com.sample

import android.media.Image
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.TextRecognizer
import javax.inject.Inject

class VisionRepositoryImpl @Inject constructor(
    private val textRecognizer: TextRecognizer,
) : VisionRepository {
    override fun detectText(mediaImage: Image) {
        textRecognizer
            .process(InputImage.fromMediaImage(mediaImage, 0))
            .addOnSuccessListener { println(it.text) }
    }
}
