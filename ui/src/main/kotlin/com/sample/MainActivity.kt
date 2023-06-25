package com.sample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.sample.common.CameraWithPermissionScreen
import com.sample.theme.MLKitSampleTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MLKitSampleTheme {
                val mainViewModel: MainViewModel = hiltViewModel()
                Column(Modifier.fillMaxSize()) {
                    CameraWithPermissionScreen(
                        modifier = Modifier.weight(1f),
                        useCases = listOf(mainViewModel.imageCapture),
                    )
                    TextButton(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = mainViewModel::onClickButton,
                    ) {
                        Text(text = "Take Picture")
                    }
                }
            }
        }
    }
}
