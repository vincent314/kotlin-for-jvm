plugins {
    id("org.jetbrains.kotlin.js") version "1.6.10"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-js"))
    implementation("org.jetbrains.kotlinx:kotlinx-html-js:0.7.3")
    implementation("org.jetbrains.kotlin-wrappers:kotlin-extensions:1.0.1-pre.276-kotlin-1.6.0")
    implementation(npm("@picocss/pico", "1.4.2"))
    implementation(npm("dayjs", "1.10.7"))
    implementation(npm("camelcase", "6.3.0", generateExternals = true))
}

kotlin {
    js {
        browser {
            webpackTask {
                cssSupport.enabled = true
            }

            runTask {
                cssSupport.enabled = true
            }

            testTask {
                useKarma {
                    useChromeHeadless()
                    webpackConfig.cssSupport.enabled = true
                }
            }
        }
        useCommonJs()
        binaries.executable()
    }
}
