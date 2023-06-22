plugins {
    id(Plugins.androidLibrary)
}

android {
    namespace = "com.sample.data"
}

dependencies {
    implementation(project(":utils"))

    implementation(libs.bundles.androidx)
    testImplementation(libs.bundles.test)
}
