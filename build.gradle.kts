plugins {
    // Apply the java-library plugin to add support for Java Library
    `java-library`
    id("io.freefair.lombok") version "8.12.1"
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.11.4")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.11.4")
}

val test by tasks.getting(Test::class) {
    useJUnitPlatform()
}