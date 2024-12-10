plugins {
  alias(libs.plugins.android.library)
  alias(libs.plugins.kotlin.android)
  alias(libs.plugins.ksp)
  alias(libs.plugins.androidx.room)
}

android {
  namespace = "com.jim.crypto.core.database"
  compileSdk = 35

  defaultConfig {
    minSdk = 26

    consumerProguardFiles("consumer-rules.pro")

    room {
      schemaDirectory("$projectDir/schemas")
    }

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
  packaging {
    resources.excludes.add("META-INF/*")
  }
}

dependencies {

  implementation(libs.androidx.core.ktx)
  // Koin
  implementation(libs.koin.android)
  implementation(libs.koin.test)
  // RoomDB
  implementation(libs.androidx.room.runtime)
  implementation(libs.core.ktx)
  ksp(libs.androidx.room.compiler)
  implementation(libs.androidx.room.ktx)
  testImplementation(libs.androidx.room.testing)

  testImplementation(libs.junit)
  androidTestImplementation(libs.androidx.junit)
  androidTestImplementation(libs.kotlinx.coroutines.test)
  androidTestImplementation(libs.turbine)
  androidTestImplementation(libs.androidx.test.runner)
}