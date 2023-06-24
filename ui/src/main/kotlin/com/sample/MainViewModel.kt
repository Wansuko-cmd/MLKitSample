package com.sample

import androidx.camera.core.ImageProxy
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val visionRepository: VisionRepository,
) : ViewModel() {
    @androidx.annotation.OptIn(androidx.camera.core.ExperimentalGetImage::class)
    fun detect(imageProxy: ImageProxy) {
        viewModelScope.launch {
            imageProxy.image?.let { println(visionRepository.detectText(it)) } ?: println("Err")
            imageProxy.close()
        }
    }
}
