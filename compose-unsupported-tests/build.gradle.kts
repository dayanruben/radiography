import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  id("com.android.library")
  kotlin("android")
}

android {
  compileSdk = 35

  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
  }

  defaultConfig {
    minSdk = 23
    targetSdk = 35
    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
  }

  buildFeatures {
    buildConfig = false
    compose = true
  }

  composeOptions {
    kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get()
  }

  packaging {
    resources.excludes += listOf(
      "META-INF/AL2.0",
      "META-INF/LGPL2.1"
    )
  }
    namespace = "com.squareup.radiography.test.compose.unsupported.empty"
    testNamespace = "com.squareup.radiography.test.compose.unsupported"
}

tasks.withType<KotlinCompile> {
  kotlinOptions {
    jvmTarget = "1.8"
    freeCompilerArgs += listOf(
      "-Xopt-in=kotlin.RequiresOptIn"
    )
  }
}

dependencies {
  androidTestImplementation(project(":radiography"))
  androidTestImplementation(libs.appCompat)
  androidTestImplementation(libs.compose.old.activity)
  androidTestImplementation(libs.compose.old.material)
  androidTestImplementation(libs.compose.testing)
  androidTestImplementation(libs.test.androidx.rules)
  androidTestImplementation(libs.test.junit)
  androidTestImplementation(libs.test.androidx.runner)
  androidTestImplementation(libs.test.truth)
}
