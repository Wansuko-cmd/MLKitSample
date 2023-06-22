package com.sample

import android.graphics.Bitmap
import com.google.firebase.ml.vision.common.FirebaseVisionImage
import com.google.firebase.ml.vision.document.FirebaseVisionDocumentTextRecognizer
import javax.inject.Inject

class VisionRepositoryImpl @Inject constructor(
    private val documentTextRecognizer: FirebaseVisionDocumentTextRecognizer,
) : VisionRepository {
    override fun detectText(bitmap: Bitmap) =
        documentTextRecognizer
            .processImage(FirebaseVisionImage.fromBitmap(bitmap))
            .result.text
}
