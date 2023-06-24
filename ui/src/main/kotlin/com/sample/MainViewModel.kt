package com.sample

import androidx.camera.core.ImageProxy
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val visionRepository: TextRecognizeRepository,
) : ViewModel() {
    @androidx.annotation.OptIn(androidx.camera.core.ExperimentalGetImage::class)
    fun detect(imageProxy: ImageProxy) {
        viewModelScope.launch {
            imageProxy.image?.let { println(visionRepository.recognize(it)) } ?: println("Err")
            imageProxy.close()
        }
    }
}
