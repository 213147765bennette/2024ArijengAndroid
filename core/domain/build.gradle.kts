plugins {
    alias(libs.plugins.arijeng.jvm.library)
    alias(libs.plugins.arijeng.jvm.ktor)
}

dependencies {
    implementation(libs.kotlinx.coroutines.core)
}