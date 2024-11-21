plugins {
    `java-library`
}

dependencies {
    compileOnly(libs.vaadin.core)
    testImplementation(libs.vaadin.core)

    testImplementation(libs.junit)
    testImplementation(libs.karibu.testing)
    testImplementation(libs.slf4j.simple)
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

val publishing = ext["publishing"] as (artifactId: String) -> Unit
publishing("flow-graphics")
