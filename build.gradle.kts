plugins {
    id("org.jetbrains.kotlin.jvm") version Versions.kotlin_plugin
    id("io.gitlab.arturbosch.detekt").version(Versions.detekt)
    id("io.github.gradle-nexus.publish-plugin") version Versions.nexus
    id("pl.allegro.tech.build.axion-release") version Versions.axion

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
        jvmTarget = LibConfig.jvmTarget
    }
}

dependencies {
    implementation(Libs.Jackson.core)
    implementation(Libs.Jackson.databind)
    implementation(Libs.ApacheCommons.math3)

    testImplementation(Libs.AssertJ.core)
    testImplementation(Libs.Junit.jupiter)
    testImplementation(Libs.Kotlin.junitTest)
}

java {
    withSourcesJar()
    withJavadocJar()
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(11))
    }
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

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
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