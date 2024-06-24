package com.arijeng.convention

import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.getByType


/**
 * Created by {Bennette Molepo} on {2024/05/07}.
 * Company {Multichoice}
 * Email address {bennette.molepo@multichoice.com}
 */
val Project.libs
    get() = extensions.getByType<VersionCatalogsExtension>().named("libs")