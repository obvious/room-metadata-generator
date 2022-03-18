package org.simple.rmg

import com.android.build.gradle.AppExtension
import com.android.build.gradle.internal.tasks.factory.dependsOn
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.provider.Property
import org.gradle.kotlin.dsl.create
import org.gradle.kotlin.dsl.the

abstract class MetadataGeneratorExtension {

  abstract val reporterClass: Property<String>
}

class MetadataGeneratorPlugin : Plugin<Project> {

  override fun apply(project: Project) = project.run {
    val extension = extensions.create("roomMetadataGenerator", MetadataGeneratorExtension::class.java)

    pluginManager.withPlugin("com.android.application") {
      the<AppExtension>().run {
        afterEvaluate {
          applicationVariants
            .onEach { variant ->
              val variantName = variant.name
              val taskQualifier = variantName.first().toUpperCase() + variantName.substring(1)

              println("MetadataGeneratorPlugin: Creating transform task for variant ${variantName}")

              val transformRoomDaoTask = tasks.create<TransformGeneratedRoomDaoTask>(
                name = "transform${taskQualifier}GeneratedRoomDao"
              ) {
                projectPath.set(projectDir.absolutePath)
                sourceSet.set(variantName)
                reporterClassName.set(extension.reporterClass.get())
              }

              transformRoomDaoTask.mustRunAfter("kapt${taskQualifier}Kotlin")
              tasks.named("compile${taskQualifier}JavaWithJavac").dependsOn(transformRoomDaoTask.name)
            }
        }
      }
    }
  }
}
