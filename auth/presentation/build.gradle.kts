plugins {
    alias(libs.plugins.arijeng.android.feature.ui)
}

android {
    namespace = "com.arijeng.auth.presentation"
}

dependencies {

    implementation(projects.core.domain)
    implementation(projects.auth.domain)
}