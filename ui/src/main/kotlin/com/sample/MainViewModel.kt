package com.sample

import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageProxy
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.concurrent.Executors
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val visionRepository: TextRecognizeRepository,
) : ViewModel() {

    val imageCapture = ImageCapture.Builder().build()

    @androidx.annotation.OptIn(androidx.camera.core.ExperimentalGetImage::class)
    fun onClickButton() {
        imageCapture.takePicture(
            Executors.newSingleThreadExecutor(),
            object : ImageCapture.OnImageCapturedCallback() {
                override fun onCaptureSuccess(image: ImageProxy) {
                    super.onCaptureSuccess(image)
                    viewModelScope.launch {
                        image.image?.let { println(visionRepository.recognize(it)) }
                            ?: println("Err")
                        image.close()
                    }
                }
            },
        )
    }
}
