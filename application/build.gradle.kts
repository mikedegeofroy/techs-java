plugins {
    id("java")
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("com.fasterxml.jackson.core:jackson-databind")
    implementation("org.postgresql:postgresql")
    implementation(project(":infrastructure"))
    implementation(project(":infrastructure"))
}
