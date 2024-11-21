plugins {
    application
}

dependencies {
    implementation(project(":flow-graphics"))
    implementation(libs.vaadin.core)

    testImplementation(libs.junit)
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

application {
    mainClass = "demoapp.Main"
}
