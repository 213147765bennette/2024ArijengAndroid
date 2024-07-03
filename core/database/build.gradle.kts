plugins {
    alias(libs.plugins.arijeng.android.library)
    alias(libs.plugins.arijeng.android.room)
}

android {
    namespace = "com.arijeng.core.database"
}

dependencies {
    implementation(libs.org.mongodb.bson)
    implementation(projects.core.domain)
}