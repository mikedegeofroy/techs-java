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
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-amqp")

    implementation("io.jsonwebtoken:jjwt-api:0.12.3")
    implementation("io.jsonwebtoken:jjwt-impl:0.12.3")
    implementation("io.jsonwebtoken:jjwt-jackson:0.12.3")
    implementation("io.jsonwebtoken:jjwt:0.12.3")

    implementation("org.postgresql:postgresql")
    implementation(project(":shared"))
}