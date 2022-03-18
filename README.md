# Room Metadata Generator

A Gradle plugin that transform the generated Android Room code to measure and report
metadata like dao name, method name, start and end time of the query.

## Usage

- Create a `RoomReporter` object in your project. It should contain a `begin` and `end` functions like so.

```kotlin
object RoomReporter {

  @JvmStatic
  fun begin(
    daoName: String,
    startTimeMillis: Long,
    methodName: String
  ) {
    // do stuff
  }

  @JvmStatic
  fun end(
    daoName: String,
    startTimeMillis: Long,
    methodName: String
  ) {
    // do stuff
  }
}
```

- Add Room metadata generator Gradle plugin to your modules that contains Room dao interfaces

```kotlin
plugins {
  id("in.obvious.rpm") version("2.1.0")
}
```

- Now set the reporter class in `roomMetadataGenerator` config

```kotlin
roomMetadataGenerator {
  reporterClass.set("in.obvious.monitoring.RoomReporter")
}
```
