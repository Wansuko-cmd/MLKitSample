plugins {
    id(Plugins.androidLibrary)
    id(Plugins.firebase)
}

android {
    namespace = "com.sample.data"
}

dependencies {
    implementation(project(":utils"))

    implementation(libs.bundles.androidx)
    testImplementation(libs.bundles.test)
}
