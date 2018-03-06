import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import kotlin.collections.listOf

description = "Sample for https://github.com/spring-cloud/spring-cloud-sleuth/issues/881"
group = "demo"
version = "0.0.1-SNAPSHOT"

buildscript {
    repositories {
        mavenCentral()
        maven("https://repo.spring.io/milestone")
    }
    dependencies {
        classpath("org.springframework.boot:spring-boot-gradle-plugin:2.0.0.RELEASE")
        classpath("org.junit.platform:junit-platform-gradle-plugin:1.1.0")
    }
}

apply {
    plugin("org.springframework.boot")
    plugin("org.junit.platform.gradle.plugin")
}

plugins {
    kotlin("jvm") version "1.2.30"
    id("org.jetbrains.kotlin.plugin.spring") version "1.2.30"
    id("io.spring.dependency-management") version "1.0.4.RELEASE"
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:Finchley.M8")
        //mavenBom("org.springframework.cloud:spring-cloud-dependencies:Finchley.BUILD-SNAPSHOT")
    }
}

dependencies {
    compile("org.springframework.cloud:spring-cloud-stream-reactive")
    runtime("org.springframework.boot:spring-boot-starter-webflux")
    runtime("org.springframework.boot:spring-boot-starter-actuator")
    runtime("org.springframework.cloud:spring-cloud-starter-zipkin")

    compile("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    compile("org.jetbrains.kotlin:kotlin-reflect")

    testCompile("org.springframework.boot:spring-boot-starter-test")
    testCompile("org.junit.jupiter:junit-jupiter-api")
    testRuntime("org.springframework.cloud:spring-cloud-stream-test-support")
    testRuntime("org.junit.jupiter:junit-jupiter-engine")
}

repositories {
    mavenCentral()
    maven("https://repo.spring.io/milestone")
    maven("https://repo.spring.io/snapshot")
}

tasks {
    withType<KotlinCompile> {
        kotlinOptions {
            jvmTarget = "1.8"
            freeCompilerArgs = listOf("-Xjsr305=strict")
        }
    }
}
