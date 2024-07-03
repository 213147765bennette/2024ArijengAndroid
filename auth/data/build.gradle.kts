plugins {
    alias(libs.plugins.arijeng.android.library)
    alias(libs.plugins.arijeng.jvm.ktor)
}

android {
    namespace = "com.arijeng.auth.data"
}

dependencies {

    implementation(libs.bundles.koin)

    implementation(projects.auth.domain)
    implementation(projects.core.domain)
    implementation(projects.core.data)

}