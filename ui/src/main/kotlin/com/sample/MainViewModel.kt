package com.sample

import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageProxy
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.concurrent.Executors
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val textRecognizeRepository: TextRecognizeRepository,
) : ViewModel() {

    private val _dialogMessage = MutableStateFlow<State>(State.Waiting)
    val dialogMessage = _dialogMessage.asStateFlow()

    val imageCapture = ImageCapture.Builder().build()

    @androidx.annotation.OptIn(androidx.camera.core.ExperimentalGetImage::class)
    fun onClickTakePictureButton() {
        imageCapture.takePicture { image ->
            _dialogMessage.value = State.Loading
            viewModelScope.launch {
                _dialogMessage.value =
                    image.image
                        ?.let { State.Result(textRecognizeRepository.recognize(it)) }
                        ?: State.Result("Error")
                image.close()
            }
        }
    }

    private fun ImageCapture.takePicture(onCaptureSuccess: (image: ImageProxy) -> Unit) = this.takePicture(
        Executors.newSingleThreadExecutor(),
        object : ImageCapture.OnImageCapturedCallback() {
            override fun onCaptureSuccess(image: ImageProxy) {
                super.onCaptureSuccess(image)
                onCaptureSuccess(image)
            }
        },
    )

    fun onDismissDialog() {
        _dialogMessage.value = State.Waiting
    }
}

sealed class State {
    data class Result(val message: String) : State()
    object Loading : State()
    object Waiting : State()
}
