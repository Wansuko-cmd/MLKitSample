plugins {
    `kotlin-dsl`
}

dependencies {
    implementation(libs.gradle.kotlin)
    implementation(libs.gradle.android)
    implementation(libs.gradle.hilt)
}

gradlePlugin {
    plugins {
        register("androidApplication") {
            id = "com.sample.application"
            implementationClass = "plugins.AndroidApplicationPlugin"
        }
        register("androidCompose") {
            id = "com.sample.compose"
            implementationClass = "plugins.AndroidComposePlugin"
        }
        register("androidLibrary") {
            id = "com.sample.library"
            implementationClass = "plugins.AndroidLibraryPlugin"
        }
        register("daggerHilt") {
            id = "com.sample.dagger-hilt"
            implementationClass = "plugins.DaggerHiltPlugin"
        }
    }
}
