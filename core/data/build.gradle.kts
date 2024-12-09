plugins {
  alias(libs.plugins.android.library)
  alias(libs.plugins.kotlin.android)
}

android {
  namespace = "com.jim.crypto.core.data"
  compileSdk = 35

  defaultConfig {
    minSdk = 26

    consumerProguardFiles("consumer-rules.pro")
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
}

dependencies {

  api(project(":core:database"))
  api(project(":core:model"))

  implementation(libs.androidx.core.ktx)
  implementation(libs.androidx.paging.runtime.ktx)
  // Koin
  implementation(libs.koin.android)
  implementation(libs.koin.test)
  // Gson
  implementation(libs.gson)

  testImplementation(libs.junit)
  androidTestImplementation(libs.androidx.junit)
  testImplementation(libs.kotlinx.coroutines.test)
  testImplementation(libs.mockk)
  testImplementation(libs.turbine)
  testImplementation(libs.androidx.paging.testing)
}