plugins {
    alias(libs.plugins.arijeng.android.library)
    alias(libs.plugins.arijeng.jvm.ktor)
}

android {
    namespace = "com.arijeng.core.data"
}

dependencies {
    implementation(libs.timber)

    implementation(libs.bundles.koin)

    implementation(projects.core.domain)
    implementation(projects.core.database)
}