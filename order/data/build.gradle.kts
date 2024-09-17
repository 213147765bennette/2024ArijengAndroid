plugins {
    //alias(libs.plugins.android.library)
    //alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.arijeng.android.library)
    alias(libs.plugins.arijeng.jvm.ktor)
}

android {
    namespace = "com.arijeng.order.data"
    compileSdk = 34

}

dependencies {

    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.google.android.gms.play.services.location)
    implementation(libs.androidx.work)
    implementation(libs.koin.android.workmanager)
    implementation(libs.kotlinx.serialization.json)


    implementation(projects.core.domain)
    implementation(projects.core.database)
    implementation(projects.order.domain)
    implementation(projects.core.data)

    implementation(libs.bundles.koin)



}