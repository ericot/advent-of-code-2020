import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.4.20" apply false
}

allprojects {
    group = "com.ericot"
    version = "1.0-SNAPSHOT"

    repositories {
        mavenCentral()
    }
}
