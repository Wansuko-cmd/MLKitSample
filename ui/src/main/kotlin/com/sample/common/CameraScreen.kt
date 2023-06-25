package com.sample.common

import android.Manifest
import androidx.camera.core.CameraSelector
import androidx.camera.core.Preview
import androidx.camera.core.UseCase
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.viewinterop.AndroidView
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun CameraScreenWithPermission(
    modifier: Modifier = Modifier,
    useCases: List<UseCase>,
) {
    val cameraPermissionState = rememberPermissionState(permission = Manifest.permission.CAMERA)
    LaunchedEffect(Unit) {
        cameraPermissionState.launchPermissionRequest()
    }
    if (cameraPermissionState.status.isGranted) {
        CameraScreen(modifier, useCases)
    }
}

@Composable
fun CameraScreen(
    modifier: Modifier = Modifier,
    useCases: List<UseCase>,
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    val previewView = remember { PreviewView(context) }
    val preview = remember { Preview.Builder().build() }
    val cameraProvider: ProcessCameraProvider =
        remember { ProcessCameraProvider.getInstance(context).get() }

    val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA
    LaunchedEffect(previewView) {
        cameraProvider.unbindAll()
        cameraProvider.bindToLifecycle(
            lifecycleOwner,
            cameraSelector,
            preview,
            *useCases.toTypedArray(),
        )
        preview.setSurfaceProvider(previewView.surfaceProvider)
    }
    AndroidView(
        modifier = modifier,
        factory = { previewView },
    )
}
