package com.sample

import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.Text
import com.google.mlkit.vision.text.TextRecognizer
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

suspend fun TextRecognizer.processWith(
    inputImage: InputImage,
): Text = suspendCancellableCoroutine { coroutine ->
    this.process(inputImage)
        .addOnSuccessListener { coroutine.resume(it) }
        .addOnCanceledListener { coroutine.cancel() }
        .addOnFailureListener { coroutine.resumeWithException(it) }
}
