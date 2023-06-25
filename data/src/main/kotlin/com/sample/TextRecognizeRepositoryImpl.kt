package com.sample

import android.media.Image
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.TextRecognizer
import com.sample.di.IODispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TextRecognizeRepositoryImpl @Inject constructor(
    private val textRecognizer: TextRecognizer,
    @IODispatcher private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
) : TextRecognizeRepository {
    override suspend fun recognize(mediaImage: Image): String = withContext(dispatcher) {
        textRecognizer
            .processWith(InputImage.fromMediaImage(mediaImage, 0))
            .textBlocks.joinToString(separator = "\n") { it.text }
    }
}
