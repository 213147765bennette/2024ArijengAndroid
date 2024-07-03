import com.android.build.api.dsl.ApplicationExtension
import com.arijeng.convention.ExtensionType
import com.arijeng.convention.configureBuildTypes
import com.arijeng.convention.configureKotlinAndroid
import com.arijeng.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure


/**
 * Created by {Bennette Molepo} on {2024/06/24}.
 * Company {Multichoice}
 * Email address {bennette.molepo@multichoice.com}
 */
class AndroidApplicationConventionPlugin: Plugin<Project> {
    override fun apply(target: Project) {
        target.run {
            pluginManager.run {
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android")
            }

            extensions.configure<ApplicationExtension> {
                defaultConfig {
                    applicationId = libs.findVersion("projectApplicationId").get().toString()
                    targetSdk = libs.findVersion("projectTargetSdkVersion").get().toString().toInt()

                    versionCode = libs.findVersion("projectVersionCode").get().toString().toInt()
                    versionName = libs.findVersion("projectVersionName").get().toString()
                }

                configureKotlinAndroid(this)

                configureBuildTypes(
                    commonExtension = this,
                    extensionType =  ExtensionType.APPLICATION
                )

            }
        }
    }
}