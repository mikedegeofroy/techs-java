plugins {
    id("java")
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation(project(":application"))
    implementation(project(":infrastructure"))
}
