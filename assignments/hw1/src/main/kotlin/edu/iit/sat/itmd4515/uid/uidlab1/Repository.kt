package edu.iit.sat.itmd4515.uid.uidlab1

import java.io.File
import java.nio.file.Paths

/** Personal GitHub repositories. */
enum class Repository(
    val id: String,
    val fullName: String,
    val description: String,
    val url: String,
) {
    RULEBOOK(
        "rulebook",
        "Rulebook",
        "Extended lint rules for JVM and Python",
        "https://github.com/hanggrian/rulebook",
    ),
    JAVAPOET_DSL(
        "javapoet-dsl",
        "JavaPoet DSL",
        "Replace JavaPoet builders with Kotlin DSL",
        "https://github.com/hanggrian/javapoet-dsl",
    ),
    KTFX(
        "ktfx",
        "KtFX",
        "Kotlin extensions for JavaFX app development",
        "https://github.com/hanggrian/ktfx",
    ),
    ;

    val content: File
        get() = Paths.get(Repository::class.java.getResource("/repos/$id.md")!!.toURI()).toFile()
}
