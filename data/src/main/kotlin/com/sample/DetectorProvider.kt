package com.sample

import com.google.firebase.ml.vision.FirebaseVision
import com.google.firebase.ml.vision.document.FirebaseVisionDocumentTextRecognizer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DetectorProvider {
    @Provides
    @Singleton
    fun providersDocumentTextRecognizer(): FirebaseVisionDocumentTextRecognizer =
        FirebaseVision.getInstance().cloudDocumentTextRecognizer
}
