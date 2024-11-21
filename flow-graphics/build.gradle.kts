plugins {
    `java-library`
}

dependencies {
    compileOnly(libs.vaadin.core)
    implementation(libs.jsoup) // we need jsoup 1.16.0+ because of namespace support
    testImplementation(libs.vaadin.core)

    testImplementation(libs.junit)
    testImplementation(libs.karibu.testing)
    testImplementation(libs.slf4j.simple)
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

val publishing = ext["publishing"] as (artifactId: String) -> Unit
publishing("flow-graphics")
