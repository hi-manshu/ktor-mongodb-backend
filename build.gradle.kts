val ktor_version: String by project
val kotlin_version: String by project
val logback_version: String by project
val kmongo_version: String by project
val klint_version: String by project

buildscript {

    repositories {
        maven { url = uri("https://plugins.gradle.org/m2/") }
        jcenter()
    }
    dependencies {
        classpath("org.jlleitschuh.gradle:ktlint-gradle:9.4.1")
    }
}

plugins {
    application
    kotlin("jvm") version "1.5.10"
    id("org.jlleitschuh.gradle.ktlint")
}

group = "com.himanshoe"
version = "0.0.1"

application {
    mainClass.set("io.ktor.server.netty.EngineMain")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.ktor:ktor-server-core:$ktor_version")
    implementation("io.ktor:ktor-auth:$ktor_version")
    implementation("io.ktor:ktor-auth-jwt:$ktor_version")
    implementation("io.ktor:ktor-locations:$ktor_version")
    implementation("io.ktor:ktor-server-host-common:$ktor_version")
    implementation("io.ktor:ktor-gson:$ktor_version")
    implementation("io.ktor:ktor-server-netty:$ktor_version")
    implementation("org.litote.kmongo:kmongo-coroutine:$kmongo_version")
    implementation("commons-codec:commons-codec:1.14")
    implementation("ch.qos.logback:logback-classic:$logback_version")
    testImplementation("io.ktor:ktor-server-tests:$ktor_version")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.3.3")
    testImplementation("com.nhaarman.mockitokotlin2:mockito-kotlin:2.2.0")
}

tasks.register("configureGithooks") {
    exec {
        commandLine("git config core.hooksPath .githooks".split(' '))
    }
}
