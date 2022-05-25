version = "0.1.1"
apply(plugin = "java-library")

plugins {
    id("org.jetbrains.kotlin.jvm") version Versions.kotlin_plugin
    id("io.gitlab.arturbosch.detekt").version(Versions.detekt)

    `java-library`
    jacoco
}

repositories {
    mavenCentral()
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {
    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))

    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    implementation("com.google.guava:guava:${Versions.guava}")
    implementation("org.junit.jupiter:junit-jupiter:${Versions.junit_jupiter}")
    implementation("com.navelplace:jsemver:${Versions.jsemver}")
    implementation("com.fasterxml.jackson.core:jackson-databind:${Versions.jackson_databind}")
    implementation("com.fasterxml.jackson.core:jackson-core:${Versions.jackson_core}")

    testImplementation("org.jetbrains.kotlin:kotlin-test-junit")
    testImplementation("org.assertj:assertj-core:${Versions.assertj_core}")

    testImplementation("org.jetbrains.kotlin:kotlin-test-junit")

    api("org.apache.commons:commons-math3:${Versions.math}")
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

detekt {
    buildUponDefaultConfig = true
    autoCorrect = true
    source = files("src/")
    ignoreFailures = false
    config = files("$projectDir/config/detekt/detekt.yml")
}

tasks.withType<io.gitlab.arturbosch.detekt.Detekt>().configureEach {
    reports {
        html.required.set(true)
        xml.required.set(true)
        txt.required.set(true)
        sarif.required.set(true)
    }
}

tasks.test {
    useJUnitPlatform()
    finalizedBy(tasks.jacocoTestReport)
}

tasks.jacocoTestReport {
    dependsOn(tasks.test)
}

//Create a single Jar with all dependencies
tasks.jar {
    manifest.attributes["Main-Class"] = "pl.allegro.logic.ClientLogicModule"
    val dependencies = configurations
        .runtimeClasspath
        .get()
        .map(::zipTree)
    from(dependencies)
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}