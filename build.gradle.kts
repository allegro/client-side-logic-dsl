plugins {
    id("org.jetbrains.kotlin.jvm") version Versions.kotlin_plugin
    id("io.gitlab.arturbosch.detekt").version(Versions.detekt)
    id("io.github.gradle-nexus.publish-plugin") version Versions.nexus
    id("pl.allegro.tech.build.axion-release") version "1.13.6"

    `java-library`
    jacoco
    `maven-publish`
    signing
}

apply(from = "versionConfig.gradle")

group = LibConfig.group
version = scmVersion.version

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

    implementation("com.fasterxml.jackson.core:jackson-databind:${Versions.jackson_databind}")
    implementation("com.fasterxml.jackson.core:jackson-core:${Versions.jackson_core}")

    testImplementation("org.jetbrains.kotlin:kotlin-test-junit")
    testImplementation("org.assertj:assertj-core:${Versions.assertj_core}")
    testImplementation("org.junit.jupiter:junit-jupiter:${Versions.junit_jupiter}")

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

val javadocJar: TaskProvider<Jar> = tasks.register("javadocJar", Jar::class.java) {
    archiveClassifier.set("javadoc")
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            artifact(javadocJar)
            from(components["java"])

            pom {
                name.set(LibConfig.name)
                description.set("Kotlin JsonLogic interpreter")
                url.set(LibConfig.repositoryUrl)
                inceptionYear.set("2022")
                licenses {
                    license {
                        name.set("The Apache License, Version 2.0")
                        url.set("https://www.apache.org/licenses/LICENSE-2.0.txt")
                    }
                }
                developers {
                    developer {
                        id.set("allegro")
                        name.set("opensource@allegro.pl")
                    }
                }
                scm {
                    connection.set("scm:svn:${LibConfig.repositoryUrl}")
                    developerConnection.set("scm:git@github.com:allegro/client-side-logic-dsl.git")
                    url.set(LibConfig.repositoryUrl)
                }
            }
        }
    }
}

nexusPublishing {
    repositories {
        sonatype {
            username.set(System.getenv("SONATYPE_USERNAME"))
            password.set(System.getenv("SONATYPE_PASSWORD"))
        }
    }
}

System.getenv("GPG_KEY_ID")?.let { gpgKeyId ->
    signing {
        useInMemoryPgpKeys(
            gpgKeyId,
            System.getenv("GPG_PRIVATE_KEY"),
            System.getenv("GPG_PRIVATE_KEY_PASSWORD")
        )
        sign(publishing.publications)
    }
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