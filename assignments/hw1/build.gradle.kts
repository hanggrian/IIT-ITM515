import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.gradle.api.tasks.testing.logging.TestLogEvent
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

val releaseGroup: String by project
val releaseArtifact: String by project
val releaseVersion: String by project
val payaraContainer: String by project
val payaraPath: String by project

val jdkVersion = JavaLanguageVersion.of(libs.versions.jdk.get())
val jreVersion = JavaLanguageVersion.of(libs.versions.jre.get())

group = releaseGroup
version = releaseVersion

plugins {
    kotlin("jvm") version libs.versions.kotlin
    alias(libs.plugins.ktlint)
    war
    alias(libs.plugins.cargo)
}

kotlin.jvmToolchain(jdkVersion.asInt())

ktlint.version.set(libs.versions.ktlint.get())

cargo {
    containerId = payaraContainer
    local.homeDir = file(payaraPath)
    withGroovyBuilder {
        "deployable" {
            "setContext"("/")
            "setFile"(
                layout.buildDirectory.file("libs/hanggrian-$releaseArtifact-$releaseVersion.war"),
            )
        }
    }
}

dependencies {
    ktlintRuleset(libs.rulebook.ktlint)

    cargo(libs.bundles.cargo)

    compileOnly(libs.bundles.jakarta)

    implementation(libs.bundles.markup)

    testImplementation(kotlin("test", libs.versions.kotlin.get()))
    testImplementation(libs.truth)
}

tasks {
    compileJava {
        options.release = jreVersion.asInt()
    }
    compileKotlin {
        compilerOptions.jvmTarget
            .set(JvmTarget.fromTarget(JavaVersion.toVersion(jreVersion).toString()))
    }
    test {
        useJUnitPlatform()
        testLogging {
            events(TestLogEvent.FAILED, TestLogEvent.PASSED, TestLogEvent.SKIPPED)
            exceptionFormat = TestExceptionFormat.FULL
            showExceptions = true
            showCauses = true
            showStackTraces = true
        }
    }

    named<War>("war") {
        webAppDirectory = layout.projectDirectory.dir("src/main/webapp")
    }

    cargoRunLocal.get().dependsOn(war)
    cargoStartLocal.get().dependsOn(war)
    cargoDeployRemote.get().dependsOn(war)
    cargoRedeployRemote.get().dependsOn(war)
    cargoRedeployLocal.get().dependsOn(war)
}
