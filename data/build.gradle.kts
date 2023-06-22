plugins {
    id(Plugins.androidLibrary)
    id(Plugins.daggerHilt)
}

android {
    namespace = "com.sample.data"
}

dependencies {
    implementation(project(":utils"))

    implementation(libs.bundles.androidx)
    implementation(libs.mlkit.text.recognition.japanese)
    testImplementation(libs.bundles.test)
}
