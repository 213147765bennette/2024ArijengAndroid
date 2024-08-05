plugins {
    alias(libs.plugins.arijeng.jvm.library)

}

dependencies {
    implementation(projects.core.domain)
    implementation(libs.bundles.ktorfit)
}