package com.arijeng.convention

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.BuildType
import com.android.build.api.dsl.CommonExtension
import com.android.build.api.dsl.LibraryExtension
import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure


/**
 * Created by {Bennette Molepo} on {2024/05/08}.
 * Company {Multichoice}
 * Email address {bennette.molepo@multichoice.com}
 */
internal fun Project.configureBuildTypes(
    commonExtension: CommonExtension<*,*,*,*,*,*>,
    extensionType: ExtensionType
){
    commonExtension.run {

        buildFeatures {
            buildConfig = true
        }

        var apiKey = gradleLocalProperties(rootDir,rootProject.providers).getProperty("API_KEY")
        if (apiKey == null){
            apiKey = "testme4636537674"
        }
        when(extensionType){
            ExtensionType.APPLICATION -> {
                extensions.configure<ApplicationExtension>(){
                    buildTypes {
                        debug{
                            configureDebugBuildType(apiKey)
                        }

                        release {
                            configureReleaseBuildType(commonExtension, apiKey)                        }
                    }
                }
            }
            ExtensionType.LIBRARY -> {
                extensions.configure<LibraryExtension>(){
                    buildTypes {
                        debug{
                            configureDebugBuildType(apiKey)
                        }

                        release {
                            configureReleaseBuildType(commonExtension, apiKey)
                        }
                    }
                }
            }
        }

    }
}

private fun BuildType.configureDebugBuildType(apiKey:String){
    //buildConfigField("String","API_KEY", "\"$apiKey\"")
    buildConfigField("String","BASE_URL", "\"http://192.168.0.31:9091/api/v1\"")
    //Home ip: 192.168.0.31
    //office ip: 10.50.113.113
}

private fun BuildType.configureReleaseBuildType(
    commonExtension: CommonExtension<*, *, *, *, *,*>,
    apiKey: String
) {
    buildConfigField("String", "API_KEY", "\"$apiKey\"")
    buildConfigField("String", "BASE_URL", "\"https://arijeng-app.herokuapp.com/api/v1/\"")

    isMinifyEnabled = true
    proguardFiles(
        commonExtension.getDefaultProguardFile("proguard-android-optimize.txt"),
        "proguard-rules.pro"
    )
}