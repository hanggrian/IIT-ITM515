import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.gradle.api.tasks.testing.logging.TestLogEvent

val releaseGroup: String by project
val releaseArtifact: String by project
val releaseVersion: String by project

val jdkVersion = JavaLanguageVersion.of(libs.versions.jdk.get())
val jreVersion = JavaLanguageVersion.of(libs.versions.jre.get())

val javafxModules = listOf("javafx.controls", "javafx.fxml")

group = releaseGroup
version = releaseVersion

plugins {
    java
    checkstyle
    application
    alias(libs.plugins.javafx)
    alias(libs.plugins.hibernate)
}

java.toolchain.languageVersion.set(jdkVersion)

checkstyle.toolVersion = libs.versions.checkstyle.get()

application {
    applicationName = "CTA Stations"
    mainClass.set("$releaseGroup.${releaseArtifact.replace("-", "")}.CtaApp")
}

javafx {
    version = libs.versions.javafx.get()
    modules = javafxModules
}

hibernate.enhancement {
    enableLazyInitialization = true
    enableDirtyTracking = true
    enableAssociationManagement = true
}

dependencies {
    checkstyle(libs.rulebook.checkstyle)

    compileOnly(libs.bundles.jakarta)
    compileOnly(libs.hibernate.annotations)

    implementation(libs.bundles.hibernate)
    implementation(libs.bundles.hibernate.validator)

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
}
