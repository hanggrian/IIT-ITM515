import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.gradle.api.tasks.testing.logging.TestLogEvent
import java.awt.SystemColor.text

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
    war
    checkstyle
    alias(libs.plugins.cargo)
    alias(libs.plugins.hibernate)
}

java.toolchain.languageVersion.set(jdkVersion)

checkstyle.toolVersion = libs.versions.checkstyle.get()

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
    checkstyle(libs.rulebook.checkstyle)

    cargo(libs.bundles.cargo)

    compileOnly(libs.bundles.jakarta)
    compileOnly(libs.hibernate.annotations)

    implementation(libs.bundles.hibernate)
    implementation(libs.bundles.hibernate.validator)
    implementation(libs.commons.text)
    implementation(libs.javafaker)
    implementation(libs.primefaces) {
        artifact {
            classifier = "jakarta"
        }
    }

    testImplementation(libs.jupiter)
    testImplementation(libs.truth)
}

tasks {
    compileJava {
        options.release = jreVersion.asInt()
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
        webAppDirectory = file("src/main/webapp")
    }

    cargoRunLocal.get().dependsOn(war)
    cargoStartLocal.get().dependsOn(war)
    cargoDeployRemote.get().dependsOn(war)
    cargoRedeployRemote.get().dependsOn(war)
    cargoRedeployLocal.get().dependsOn(war)
}
