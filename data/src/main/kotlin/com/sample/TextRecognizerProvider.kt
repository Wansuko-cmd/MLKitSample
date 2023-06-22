package com.sample

import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.TextRecognizer
import com.google.mlkit.vision.text.japanese.JapaneseTextRecognizerOptions
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class TextRecognizerProvider {
    @Provides
    @Singleton
    fun providersTextRecognizer(): TextRecognizer =
        TextRecognition.getClient(JapaneseTextRecognizerOptions.Builder().build())
}
