plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

group = "com.github.mahditeymoorianar" // Important!
version = "1.0.0" // Will match GitHub tag

android {
    namespace = "com.teymoorianar.anarbottomnavigationbar"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.teymoorianar.anarbottomnavigationbar"
        minSdk = 27
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}