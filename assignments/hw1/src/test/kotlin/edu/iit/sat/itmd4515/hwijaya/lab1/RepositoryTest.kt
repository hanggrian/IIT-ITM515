package edu.iit.sat.itmd4515.hwijaya.lab1

import kotlin.test.Test
import kotlin.test.assertTrue

class RepositoryTest {
    @Test
    fun `Retrieve markdown file from resources`() =
        Repository.entries.forEach {
            val file = it.content
            assertTrue(file.exists())
            assertTrue(file.readText().isNotBlank())
        }
}
