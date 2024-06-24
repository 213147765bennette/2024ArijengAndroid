import com.arijeng.convention.configureKotlinJvm
import org.gradle.api.Plugin
import org.gradle.api.Project


/**
 * Created by {Bennette Molepo} on {2024/05/09}.
 * Company {Multichoice}
 * Email address {bennette.molepo@multichoice.com}
 */

class JvmLibraryConventionPlugin: Plugin<Project> {

    override fun apply(target: Project) {
        target.run {
            pluginManager.apply("org.jetbrains.kotlin.jvm")
            configureKotlinJvm()
        }
    }
}