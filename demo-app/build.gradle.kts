plugins {
    alias(libs.plugins.vaadin)
    application
}

dependencies {
    implementation(project(":flow-graphics"))
    implementation(libs.vaadin.core)
    implementation(libs.slf4j.simple)

    testImplementation(libs.junit)
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

application {
    mainClass = "demoapp.Main"
}
