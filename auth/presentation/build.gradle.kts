plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrainsKotlinAndroid)
}

android {
    namespace = "com.arijeng.auth.presentation"
}

dependencies {

    implementation(libs.sms.retriever)
    implementation(projects.core.domain)
    implementation(projects.auth.domain)
}