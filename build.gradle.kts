plugins {
    application
    kotlin("jvm") version "1.9.0"
    kotlin("plugin.serialization") version "1.9.0"
    id("com.diffplug.spotless") version "6.19.0"
}

group = "dev.beisenherz.dfm"
version = "1.0-SNAPSHOT"

application {
    mainClass.set("dev.beisenherz.dfm.MainKt")
}


repositories {
    mavenCentral()
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(20))
    }
}

dependencies {
    implementation("com.github.ajalt.clikt:clikt:4.0.0")
    implementation("io.github.microutils:kotlin-logging:3.0.5")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.1")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.1")
    implementation("org.jetbrains.kotlinx:kotlinx-io-core:0.2.1")
    implementation("org.eclipse.jgit:org.eclipse.jgit:6.6.0.202305301015-r")

    implementation(kotlin("test"))
}

val test by tasks.getting(Test::class) {
    useJUnitPlatform()
}

configure<com.diffplug.gradle.spotless.SpotlessExtension> {
    kotlin {
        ktlint()
        targetExclude("*/generated/**")
    }
    kotlinGradle {
        ktlint()
        targetExclude("*/generated/**")
    }
}
