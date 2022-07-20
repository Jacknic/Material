plugins {
    alias(libs.plugins.android.application).apply(false)
    alias(libs.plugins.android.library).apply(false)
    alias(libs.plugins.jetbrains.kotlin.android).apply(false)
}

tasks.create("clean", Delete::class) {
    delete(rootProject.buildDir)
}