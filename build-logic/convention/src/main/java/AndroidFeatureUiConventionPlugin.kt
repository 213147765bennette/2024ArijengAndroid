import com.arijeng.convention.addUiLayerDependencies
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

/**
 * Created by {Bennette Molepo} on {2024/05/08}.
 * Company {Multichoice}
 * Email address {bennette.molepo@multichoice.com}
 */

class AndroidFeatureUiConventionPlugin: Plugin<Project> {

    override fun apply(target: Project) {
        target.run {
            pluginManager.run {
                apply("arijeng.android.library.compose")
            }

            dependencies {
                addUiLayerDependencies(target)
            }
        }
    }
}