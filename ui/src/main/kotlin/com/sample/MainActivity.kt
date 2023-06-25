package com.sample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sample.common.CameraWithPermissionScreen
import com.sample.common.LoadingScreen
import com.sample.theme.MLKitSampleTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MLKitSampleTheme {
                val mainViewModel: MainViewModel = hiltViewModel()
                val dialogMessage by mainViewModel.dialogMessage.collectAsStateWithLifecycle()

                Column(Modifier.fillMaxSize()) {
                    CameraWithPermissionScreen(
                        modifier = Modifier.weight(1f),
                        useCases = listOf(mainViewModel.imageCapture),
                    )
                    TextButton(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = mainViewModel::onClickTakePictureButton,
                    ) {
                        Text(text = "Take Picture")
                    }
                }
                if (dialogMessage is State.Result) {
                    Dialog(onDismissRequest = mainViewModel::onDismissDialog) {
                        Surface {
                            Column(Modifier.fillMaxSize()) {
                                Column(
                                    Modifier
                                        .verticalScroll(rememberScrollState())
                                        .horizontalScroll(rememberScrollState())
                                        .weight(1f),
                                ) {
                                    Text(text = (dialogMessage as? State.Result)?.message ?: "")
                                }
                                TextButton(
                                    modifier = Modifier.fillMaxWidth(),
                                    onClick = mainViewModel::onDismissDialog,
                                ) {
                                    Text(text = "Close")
                                }
                            }
                        }
                    }
                }
                if (dialogMessage is State.Loading) {
                    LoadingScreen()
                }
            }
        }
    }
}
