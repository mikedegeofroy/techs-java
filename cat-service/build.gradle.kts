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

    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    implementation("org.postgresql:postgresql")
}

tasks.test {
    useJUnitPlatform()
}

subprojects {
    apply(plugin = "org.springframework.boot")
    apply(plugin = "io.spring.dependency-management")
    apply(plugin = "io.freefair.lombok")

    repositories {
        mavenCentral() // This applies Maven Central to all subprojects
    }
}
