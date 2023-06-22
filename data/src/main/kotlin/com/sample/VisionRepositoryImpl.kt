package com.sample

import android.media.Image
import com.google.firebase.ml.vision.common.FirebaseVisionImage
import com.google.firebase.ml.vision.common.FirebaseVisionImageMetadata
import com.google.firebase.ml.vision.document.FirebaseVisionDocumentTextRecognizer
import javax.inject.Inject

class VisionRepositoryImpl @Inject constructor(
    private val documentTextRecognizer: FirebaseVisionDocumentTextRecognizer,
) : VisionRepository {
    override fun detectText(mediaImage: Image) =
        documentTextRecognizer
            .processImage(FirebaseVisionImage.fromMediaImage(mediaImage, FirebaseVisionImageMetadata.ROTATION_0))
            .result.text
}
