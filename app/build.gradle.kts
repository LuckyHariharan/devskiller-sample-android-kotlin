plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    compileSdk = 31
     buildToolsVersion = "30.0.3"
    defaultConfig {
        applicationId = "com.devskiller.calculator.calculator.kotlin"
        minSdk = 21
        targetSdk = 30
        versionCode = 1
        versionName = "1.0"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }
    lint {
        isAbortOnError = false
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {
    implementation("androidx.appcompat:appcompat:1.4.0")
    implementation("com.annimon:stream:1.2.1")

    testImplementation("com.squareup.assertj:assertj-android:1.2.0") {
        exclude(module = "support-annotations")
    }
    testImplementation("junit:junit:4.13.1")
    testImplementation("org.robolectric:robolectric:4.6.1")
}
