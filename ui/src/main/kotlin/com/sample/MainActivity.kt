package com.sample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.sample.common.AnalyzeCameraWithPermissionScreen
import com.sample.theme.MLKitSampleTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MLKitSampleTheme {
                val mainViewModel: MainViewModel = hiltViewModel()
                AnalyzeCameraWithPermissionScreen(
                    modifier = Modifier.fillMaxSize(),
                    analyze = {
                        mainViewModel.detect(it)
                    },
                )
            }
        }
    }
}
