package com.example.bumpProjectVersion

import org.gradle.api.Plugin
import org.gradle.api.Project

class BumpProjectVersion : Plugin<Project> {
    override fun apply(project: Project) {
        val versionFile = project.file("build.gradle.kts")

        fun bumpVersion(type: String) {
            val versionRegex = """version\s*=\s*"(\d+)\.(\d+)\.(\d+)"""".toRegex()
            val content = versionFile.readText()

            val updatedContent = versionRegex.replace(content) { matchResult ->
                val (major, minor, patch) = matchResult.destructured
                val newVersion = when (type) {
                    "major" -> "${major.toInt() + 1}.0.0"
                    "minor" -> "$major.${minor.toInt() + 1}.0"
                    "patch" -> "$major.$minor.${patch.toInt() + 1}"
                    else -> throw IllegalArgumentException("Invalid version type: $type")
                }
                """version = "$newVersion""""
            }

            versionFile.writeText(updatedContent)
            // Extracting the new version for printing
            val extractedVersion = versionRegex.find(updatedContent)?.groups?.get(0)?.value ?: "unknown"
            println("Version bumped to $type! New version: $extractedVersion")
        }

        project.tasks.register("bumpMajor") {
            group = "versioning"
            description = "Bump the major version"
            doLast {
                bumpVersion("major")
            }
        }

        project.tasks.register("bumpMinor") {
            group = "versioning"
            description = "Bump the minor version"
            doLast {
                bumpVersion("minor")
            }
        }

        project.tasks.register("bumpPatch") {
            group = "versioning"
            description = "Bump the patch version"
            doLast {
                bumpVersion("patch")
            }
        }
    }
}
