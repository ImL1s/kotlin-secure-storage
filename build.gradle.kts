plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    `maven-publish`
}

group = "io.github.iml1s"
version = "1.0.0"

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "17"
            }
        }
        publishLibraryVariants("release")
    }

    jvm() // Desktop/Server target (File-based encryption placeholder)

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "securestorage"
        }
    }

    sourceSets {
        commonMain.dependencies {
            implementation(libs.kotlinx.coroutines.core)
        }
        
        commonTest.dependencies {
            implementation(kotlin("test"))
        }

        androidMain.dependencies {
            implementation("androidx.security:security-crypto:1.1.0-alpha06") // EncryptedSharedPreferences
        }

        iosMain.dependencies {
            // Keychain access usually requires native interop or a wrapper. 
            // We'll write native cinterop or simple wrapper if possible, 
            // or assume we use platform.Security framework directly.
        }
    }
}

android {
    namespace = "io.github.iml1s.storage"
    compileSdk = 35
    defaultConfig {
        minSdk = 26 // EncryptedSharedPreferences requires 23+, we set 26 for safety with full KMP stack
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}
