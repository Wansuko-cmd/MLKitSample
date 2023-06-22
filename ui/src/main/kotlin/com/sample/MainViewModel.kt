package com.sample

import androidx.camera.core.ImageProxy
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val visionRepository: VisionRepository,
) : ViewModel() {
    @androidx.annotation.OptIn(androidx.camera.core.ExperimentalGetImage::class)
    fun detect(imageProxy: ImageProxy) {
        imageProxy.image?.let { println(visionRepository.detectText(it)) } ?: println("Err")
    }
}
