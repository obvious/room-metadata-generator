import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import org.gradle.api.tasks.testing.logging.TestExceptionFormat

plugins {
    id("java-gradle-plugin")
    id("org.jetbrains.kotlin.jvm") version("1.6.10")
    `kotlin-dsl`
    id("com.github.johnrengelman.shadow") version("7.1.0")
}

group = "org.simple"
version = "2.0.0"

repositories {
    mavenCentral()
}

tasks.withType<ShadowJar> {
    archiveClassifier.set("")
}

tasks.withType<Test> {
    useJUnitPlatform()

    testLogging {
        showStackTraces = true
        exceptionFormat = TestExceptionFormat.FULL
    }
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib")
    implementation("com.github.javaparser:javaparser-core:3.19.0")
    implementation("ch.qos.logback:logback-classic:1.2.3")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.7.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.7.1")

    testImplementation("com.google.truth:truth:1.1.2")
}
