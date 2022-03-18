package org.simple.rmg

import org.gradle.api.DefaultTask
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction

abstract class TransformGeneratedRoomDaoTask : DefaultTask() {

  @get:Input
  abstract val projectPath: Property<String>

  @get:Input
  abstract val sourceSet: Property<String>

  @get:Input
  abstract val reporterClassName: Property<String>

  @TaskAction
  fun run() {
    RoomMetadataGenerator().run(
      projectPath = projectPath.get(),
      sourceSet = sourceSet.get(),
      reporterName = reporterClassName.get()
    )
  }
}

