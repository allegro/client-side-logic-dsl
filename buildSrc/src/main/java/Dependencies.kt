object LibConfig {
    const val group = "pl.allegro.mobile"
    const val repositoryUrl = "https://github.com/allegro/client-side-logic-dsl"
    const val name = "ClientSideLogicDSL"
    const val jvmTarget = "11"
}

object Versions {
    const val kotlin_plugin = "1.6.20"
    const val math = "3.6.1"
    const val assertj_core = "3.22.0"
    const val jackson_core = "2.13.3"
    const val jackson_databind = "2.13.3"
    const val junit_jupiter = "5.8.2"
    const val detekt = "1.19.0"
    const val nexus = "1.0.0"
    const val axion = "1.13.6"
}

object Libs {
    object Jackson {
        const val databind = "com.fasterxml.jackson.core:jackson-databind:${Versions.jackson_databind}"
        const val core = "com.fasterxml.jackson.core:jackson-core:${Versions.jackson_core}"
    }

    object ApacheCommons {
        const val math3 = "org.apache.commons:commons-math3:${Versions.math}"
    }

    object AssertJ {
        const val core = "org.assertj:assertj-core:${Versions.assertj_core}"
    }

    object Junit {
        const val jupiter = "org.junit.jupiter:junit-jupiter:${Versions.junit_jupiter}"
    }

    object Kotlin {
        const val junitTest = "org.jetbrains.kotlin:kotlin-test-junit"
    }
}