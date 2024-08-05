plugins {
    `kotlin-dsl`
}

group = "com.arijeng.buildlogic"

dependencies {
    //include the plugins only during compile time: we use compileOnly not implementation
    //they are only build when they are needed/used
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.android.tools.common)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.ksp.gradlePlugin)
    compileOnly(libs.bundles.ktorfit)
    compileOnly(libs.room.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("androidApplication") {
            id = "arijeng.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }

        register("androidApplicationCompose"){
            id = "arijeng.android.application.compose"
            implementationClass = "AndroidApplicationComposeConventionPlugin"
        }

        register("androidApplicationLibrary"){
            id = "arijeng.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }

        register("androidLibraryCompose"){
            id = "arijeng.android.library.compose"
            implementationClass = "AndroidLibraryComposeConventionPlugin"
        }

        register("androidFeatureUi"){
            id = "arijeng.android.feature.ui"
            implementationClass = "AndroidFeatureUiConventionPlugin"
        }

        register("androidRoom"){
            id = "arijeng.android.room"
            implementationClass = "AndroidRoomConventionPlugin"
        }

        register("jvmLibrary"){
            id = "arijeng.jvm.library"
            implementationClass = "JvmLibraryConventionPlugin"
        }

        register("jvmKtor"){
            id = "arijeng.jvm.ktor"
            implementationClass = "JvmKtorConventionPlugin"
        }
    }
}