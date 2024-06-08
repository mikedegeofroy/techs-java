plugins {
    id("java")
    id("org.springframework.boot") version "3.2.4"
    id("io.freefair.lombok") version "8.6"
}

apply(plugin = "io.spring.dependency-management")

group = "org.mikedegeofroy"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.fasterxml.jackson.core:jackson-databind")
    implementation("org.springframework.boot:spring-boot-starter-security")
}