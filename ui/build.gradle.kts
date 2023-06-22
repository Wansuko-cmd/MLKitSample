plugins {
    id(Plugins.androidCompose)
    id(Plugins.daggerHilt)
}

android {
    namespace = "com.sample.ui"
}

dependencies {
    implementation(project(":data"))

    implementation(libs.bundles.androidx)
    implementation(libs.bundles.androidx.camera)

    testImplementation(libs.bundles.test)

    androidTestImplementation(libs.bundles.androidx.test)
}
