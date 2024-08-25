import org.jetbrains.kotlin.gradle.dsl.JvmTarget

val releaseGroup: String by project
val releaseArtifact: String by project
val releaseVersion: String by project

val jdkVersion = JavaLanguageVersion.of(libs.versions.jdk.get())
val jreVersion = JavaLanguageVersion.of(libs.versions.jre.get())

group = releaseGroup
version = releaseVersion

plugins {
    kotlin("jvm") version libs.versions.kotlin
    war
    alias(libs.plugins.cargo)
    alias(libs.plugins.ktlint)
}

kotlin.jvmToolchain(jdkVersion.asInt())

ktlint.version.set(libs.versions.ktlint.get())

cargo {
    containerId = "glassfish5x"
    local.homeDir = file("/Users/hanggrian/payara6")
    withGroovyBuilder {
        "deployable" {
            "setContext"("/")
            "setFile"(layout.buildDirectory.file("libs/$releaseArtifact-$releaseVersion.war"))
        }
    }
}

dependencies {
    ktlintRuleset(libs.rulebook.ktlint)

    compileOnly(libs.bundles.jakarta)
    cargo(libs.bundles.cargo)

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
