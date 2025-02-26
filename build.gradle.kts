plugins {
    kotlin("jvm") version "1.9.25"
    kotlin("plugin.spring") version "1.9.25"
    kotlin("kapt") version "1.9.25"
    id("org.springframework.boot") version "3.4.1"
    id("io.spring.dependency-management") version "1.1.7"
    kotlin("plugin.jpa") version "1.9.25"
    id("org.jlleitschuh.gradle.ktlint") version "12.1.1"
}

allprojects {
    group = "com.myhome"
    version = "0.0.1-SNAPSHOT"

    repositories {
        mavenCentral()
    }
}

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(17)
	}
}

subprojects {
    plugins.apply("org.jetbrains.kotlin.jvm")
    plugins.apply("org.jetbrains.kotlin.plugin.spring")
    plugins.apply("org.jetbrains.kotlin.kapt") // kapt 플러그인 적용
    plugins.apply("org.springframework.boot")
    plugins.apply("io.spring.dependency-management")
    plugins.apply("org.jetbrains.kotlin.plugin.jpa")
    plugins.apply("org.jlleitschuh.gradle.ktlint")

    dependencies {
        implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
        implementation("org.jetbrains.kotlin:kotlin-reflect")
        testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
        testRuntimeOnly("org.junit.platform:junit-platform-launcher")
        implementation("io.github.microutils:kotlin-logging-jvm:3.0.5")

        // Kotest & Mockk
        testImplementation("io.kotest.extensions:kotest-extensions-spring:1.1.2")
        testImplementation("io.kotest:kotest-assertions-core:5.5.5")
        testImplementation("io.kotest:kotest-runner-junit5:5.5.5")
        testImplementation("io.mockk:mockk:1.13.9")
        implementation("com.appmattus.fixture:fixture:1.1.0")
    }
}

kotlin {
	compilerOptions {
		freeCompilerArgs.addAll("-Xjsr305=strict")
	}
}

allOpen {
    annotation("jakarta.persistence.Entity")
    annotation("jakarta.persistence.MappedSuperclass")
    annotation("jakarta.persistence.Embeddable")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.named("bootJar") {
    enabled = false
}

tasks.named("jar") {
    enabled = true
}