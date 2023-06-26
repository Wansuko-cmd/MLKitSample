task<Delete>("clean") {
    delete(rootProject.buildDir)
}

subprojects {

    val ktlint by configurations.creating

    dependencies {
        ktlint("com.pinterest:ktlint:0.49.1") {
            attributes {
                attribute(Bundling.BUNDLING_ATTRIBUTE, objects.named(Bundling.EXTERNAL))
            }
        }
    }

    @Suppress("UNUSED_VARIABLE")
    val ktlintCheck by tasks.creating(JavaExec::class) {
        group = "verification"
        description = "Check Kotlin code style."
        classpath = ktlint
        mainClass.set("com.pinterest.ktlint.Main")
        args = listOf("src/**/*.kt", "**.kts", "!**/build/**")
    }

    @Suppress("UNUSED_VARIABLE")
    val ktlintFormat by tasks.creating(JavaExec::class) {
        group = "formatting"
        description = "Fix Kotlin code style deviations."
        classpath = ktlint
        mainClass.set("com.pinterest.ktlint.Main")
        jvmArgs = listOf("--add-opens=java.base/java.lang=ALL-UNNAMED")
        args = listOf("-F", "src/**/*.kt", "**.kts", "!**/build/**")
    }
}
