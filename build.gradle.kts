plugins {
    kotlin("jvm") version "1.9.22"
    id("com.gradle.plugin-publish") version "1.2.1"
    `kotlin-dsl`
    `java-gradle-plugin`
    `maven-publish`
}

group = "com.example.bump-project-version"
version = "1.0.0"

gradlePlugin {
    website = "<substitute your project website>"
    vcsUrl = "https://github.com/Jorgen-5/Bump-project-version/tree/main"
    plugins {
        create("bumpProjectVersion") {
            id = "com.example.bump-project-version"
            implementationClass = "com.example.bumpProjectVersion.BumpProjectVersion"

        }
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.9.22")
    implementation(kotlin("stdlib"))
    testImplementation("io.kotest:kotest-runner-junit5:5.0.0")
    testImplementation("io.kotest:kotest-assertions-core:5.0.0")
    testImplementation("io.kotest:kotest-property:5.0.0")
    testImplementation("org.jetbrains.kotlin:kotlin-reflect:1.9.22")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5:1.9.22")
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(21)
}

tasks.wrapper {
    gradleVersion = "8.1"
}
