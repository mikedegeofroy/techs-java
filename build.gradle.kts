plugins {
    id("java")
    id("org.springframework.boot") version "3.2.4"
}

apply(plugin = "io.spring.dependency-management")

group = "org.mikedegeofroy"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    implementation(project("application"))
    implementation(project("presentation"))
    implementation(project("infrastructure"))
}

tasks.test {
    useJUnitPlatform()
}