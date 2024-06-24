
import com.android.build.api.dsl.LibraryExtension
import com.arijeng.convention.configureAndroidCompose
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType

/**
 * Created by {Bennette Molepo} on {2024/05/08}.
 * Company {Multichoice}
 * Email address {bennette.molepo@multichoice.com}
 */

class AndroidLibraryComposeConventionPlugin: Plugin<Project> {

    override fun apply(target: Project) {
        target.run {
            pluginManager.run {
                apply("runique.android.library")
            }

            val extension = extensions.getByType<LibraryExtension>()
            configureAndroidCompose(extension)
        }
    }
}