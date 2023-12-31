plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.dagger.hilt.android")
    kotlin("kapt")
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.msa.trackingapp"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.msa.trackingapp"
        minSdk = 26
        targetSdk = 30
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    buildFeatures {
        viewBinding = true
    }

    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.10.1")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")


    // nav_version
    val nav_version = "2.6.0"
    implementation("androidx.navigation:navigation-fragment-ktx:$nav_version")
    implementation("androidx.navigation:navigation-ui-ktx:$nav_version")

    //Hilt Dagger
    implementation("com.google.dagger:hilt-android:2.47")
    kapt("com.google.dagger:hilt-android-compiler:2.47")

    //Room DataBase
    val room_version = "2.5.2"
    implementation("androidx.room:room-runtime:$room_version")
    ksp("androidx.room:room-compiler:$room_version")
    implementation ("androidx.room:room-ktx:2.5.2")

    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")

    // Lifecycle Scopes
    val lifecycle_version = "2.6.1"
    val arch_version = "2.2.0"
    // ViewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version")
    // Lifecycles only (without ViewModel or LiveData)
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:$lifecycle_version")
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1")
    implementation("androidx.lifecycle:lifecycle-service:2.6.1")


    // Glide
    implementation ("com.github.bumptech.glide:glide:4.15.1")
   // kapt ("com.github.bumptech.glide:compiler:4.11.0")

    // Google Maps Location Services
    implementation ("com.google.maps.android:maps-utils-ktx:3.4.0")
    implementation ("com.google.maps.android:android-maps-utils:3.5.2")
    implementation ("com.google.android.gms:play-services-location:17.0.0")
    implementation ("com.google.android.gms:play-services-maps:17.0.0")
    // For developers using AndroidX in their applications
    implementation ("pub.devrel:easypermissions:3.0.0")

    //timber
    implementation ("com.jakewharton.timber:timber:5.0.1")


    val activity_version = "1.7.2"
    // Kotlin
    implementation ("androidx.activity:activity-ktx:$activity_version")

    implementation ("com.github.PhilJay:MPAndroidChart:v3.1.0")


    implementation ("androidx.work:work-runtime-ktx:2.8.1")


}

// Allow references to generated code
kapt {
    correctErrorTypes = true
}
