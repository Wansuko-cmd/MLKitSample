plugins {
    id(Plugins.androidLibrary)
}

android {
    namespace = "com.sample.lib"
}

dependencies {
    implementation(project(":utils"))

    implementation(libs.bundles.androidx)
    testImplementation(libs.bundles.test)
}
