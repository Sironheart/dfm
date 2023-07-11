plugins {
    kotlin("multiplatform") version "1.8.21"
    kotlin("plugin.serialization") version "1.8.21"
}

repositories {
    google()
    mavenCentral()
}

kotlin {
    jvm() // for testing
    listOf(
        macosX64(),
        macosArm64(),
        linuxX64(),
//        linuxArm64()
    ).forEach {
        it.binaries {
            executable {
                entryPoint = "dev.beisenherz.dfm.main"
            }
        }
    }

    sourceSets {
        all {
            languageSettings.optIn("kotlin.RequiresOptIn")
        }

        val commonMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.1")
                implementation("com.github.ajalt.clikt:clikt:4.0.0")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.1")
                implementation("co.touchlab:kermit:2.0.0-RC4")
                implementation("org.eclipse.jgit:org.eclipse.jgit:3.5.0.201409260305-r")
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }

        // MacOS Configuration
        val macosX64Main by getting
        val macosArm64Main by getting
        val macosMain by creating
        val macosX64Test by getting
        val macosArm64Test by getting
        val macosTest by creating

        macosMain.dependsOn(commonMain)
        macosX64Main.dependsOn(macosMain)
        macosArm64Main.dependsOn(macosMain)

        macosTest.dependsOn(commonTest)
        macosX64Test.dependsOn(macosTest)
        macosArm64Test.dependsOn(macosTest)

        // Linux Configuration
        val linuxX64Main by getting
//        val linuxArm64Main by getting
        val linuxMain by creating
        val linuxX64Test by getting
//        val linuxArm64Test by getting
        val linuxTest by creating

        linuxMain.dependsOn(commonMain)
        linuxX64Main.dependsOn(linuxMain)
//        linuxArm64Main.dependsOn(linuxMain)

        linuxTest.dependsOn(commonTest)
        linuxX64Main.dependsOn(linuxTest)
//        linuxArm64Main.dependsOn(linuxTest)
    }
}

//tasks.register<Exec>("assembleReleaseExecutableMacos") {
//    dependsOn(":kdoctor:jvmTest", ":kdoctor:linkReleaseExecutableMacosX64", ":kdoctor:linkReleaseExecutableMacosArm64")
//    commandLine("lipo", "-create", "-output", "kdoctor", "bin/macosX64/releaseExecutable/kdoctor.kexe", "bin/macosArm64/releaseExecutable/kdoctor.kexe")
//    workingDir = buildDir
//    group = "Build"
//    description = "Builds universal macOS binary"
//}
