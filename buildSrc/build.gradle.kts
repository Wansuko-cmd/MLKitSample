plugins {
    `kotlin-dsl`
}

dependencies {
    implementation(libs.gradle.kotlin)
    implementation(libs.gradle.android)
    implementation(libs.gradle.hilt)
    implementation("com.google.gms:google-services:4.3.15")
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
        register("firebase") {
            id = "com.sample.firebase"
            implementationClass = "plugins.FirebasePlugin"
        }
    }
}
