plugins {
  alias(libs.plugins.android.application)
  alias(libs.plugins.kotlin.android)
  alias(libs.plugins.kotlin.compose)
  kotlin("plugin.serialization") version "2.0.21"
}

android {
  namespace = "com.jim.crypto"
  compileSdk = 35

  defaultConfig {
    applicationId = "com.jim.crypto"
    minSdk = 26
    targetSdk = 34
    versionCode = 1
    versionName = "1.0"

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
  }

  buildTypes {
    release {
      isMinifyEnabled = false
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
    }
  }
  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
  }
  kotlinOptions {
    jvmTarget = "11"
  }
  buildFeatures {
    compose = true
  }
}

dependencies {

  implementation(project(":core:database"))
  implementation(project(":core:data"))
  implementation(project(":core:domain"))

  implementation(libs.androidx.core.ktx)
  implementation(libs.androidx.lifecycle.runtime.ktx)
  implementation(libs.androidx.lifecycle.viewmodel.ktx)
  implementation(libs.androidx.activity.compose)
  implementation(platform(libs.androidx.compose.bom))
  implementation(libs.androidx.ui)
  implementation(libs.androidx.ui.graphics)
  implementation(libs.androidx.ui.tooling.preview)
  implementation(libs.androidx.material3)
  // Koin
  implementation(libs.koin.android)
  implementation(libs.koin.test)
  implementation(libs.koin.androidx.compose)
  // Jetpack Compose integration
  implementation(libs.androidx.navigation.compose)
  // Views/Fragments integration
  implementation(libs.androidx.navigation.fragment)
  implementation(libs.androidx.navigation.ui)
  // Testing Navigation
  androidTestImplementation(libs.androidx.navigation.testing)
  implementation(libs.androidx.paging.runtime)  // Paging 3
  implementation(libs.androidx.paging.compose) // Paging for Jetpack Compose
//  implementation(libs.androidx.room.paging)       // Room Paging support
  // JSON serialization library, works with the Kotlin serialization plugin
  implementation(libs.kotlinx.serialization.json)

  testImplementation(libs.junit)
  androidTestImplementation(libs.androidx.junit)
  androidTestImplementation(libs.androidx.espresso.core)
  androidTestImplementation(platform(libs.androidx.compose.bom))
  androidTestImplementation(libs.androidx.ui.test.junit4)
  debugImplementation(libs.androidx.ui.tooling)
  debugImplementation(libs.androidx.ui.test.manifest)
}