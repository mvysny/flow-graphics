plugins {
    alias(libs.plugins.vaadin)
    application
}

dependencies {
    implementation(project(":flow-graphics"))
    implementation(libs.vaadin.core) {
        if (vaadin.productionMode) {
            exclude(module = "vaadin-dev")
        }
    }
    implementation(libs.slf4j.simple)
    implementation(libs.vaadin.boot)

    testImplementation(libs.junit)
    testImplementation(libs.karibu.testing)
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

application {
    mainClass = "demoapp.Main"
}
