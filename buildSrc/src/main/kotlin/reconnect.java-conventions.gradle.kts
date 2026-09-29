plugins {
    id("java")
}

val targetJavaVersion = 21

tasks {
    withType<JavaCompile>().configureEach {
        options.encoding = "UTF-8"
        options.release.set(targetJavaVersion)
    }

    java {
        withSourcesJar()

        sourceCompatibility = JavaVersion.toVersion(targetJavaVersion)
        targetCompatibility = JavaVersion.toVersion(targetJavaVersion)
    }
}