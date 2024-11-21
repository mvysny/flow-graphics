import org.gradle.api.tasks.testing.logging.TestExceptionFormat

plugins {
    java
    `java-library`
    `maven-publish`
    signing
}

defaultTasks("clean", "build")

group = "com.github.mvysny.flow-graphics"
version = "1.0.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    compileOnly(libs.vaadin.core)
    implementation(libs.jsoup) // we need jsoup 1.16.0+ because of namespace support
    testImplementation(libs.vaadin.core)

    testImplementation(libs.junit)
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

// following https://dev.to/kengotoda/deploying-to-ossrh-with-gradle-in-2020-1lhi
java {
    withJavadocJar()
    withSourcesJar()
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

tasks.withType<Javadoc> {
    isFailOnError = false
}

publishing {
    repositories {
        maven {
            setUrl("https://oss.sonatype.org/service/local/staging/deploy/maven2/")
            credentials {
                username = project.properties["ossrhUsername"] as String? ?: "Unknown user"
                password = project.properties["ossrhPassword"] as String? ?: "Unknown user"
            }
        }
    }
    publications {
        create("mavenJava", MavenPublication::class.java).apply {
            groupId = project.group.toString()
            this.artifactId = "flow-graphics"
            version = project.version.toString()
            pom {
                description = "A cross-browser vector graphics library for Vaadin Flow"
                name = "Konsume-XML"
                url = "https://github.com/mvysny/flow-graphics"
                licenses {
                    license {
                        name = "The MIT License"
                        url = "https://opensource.org/licenses/MIT"
                        distribution = "repo"
                    }
                }
                developers {
                    developer {
                        id = "mavi"
                        name = "Martin Vysny"
                        email = "martin@vysny.me"
                    }
                }
                scm {
                    url = "https://github.com/mvysny/flow-graphics"
                }
            }
            from(components["java"])
        }
    }
}

signing {
    sign(publishing.publications["mavenJava"])
}

tasks.withType<Test> {
    useJUnitPlatform()
    testLogging {
        // to see the stacktraces of failed tests in the CI console.
        exceptionFormat = TestExceptionFormat.FULL
    }
}

if (JavaVersion.current() > JavaVersion.VERSION_11 && gradle.startParameter.taskNames.contains("publish")) {
    throw GradleException("Release this library with JDK 11 or lower, to ensure JDK8 compatibility; current JDK is ${JavaVersion.current()}")
}
