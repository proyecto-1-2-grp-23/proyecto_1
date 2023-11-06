plugins {
    id("com.android.application")
}

android {
    namespace = "com.uniandes.abcjobsgrp23"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.uniandes.abcjobsgrp23"
        minSdk = 25
        targetSdk = 34
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildFeatures {
        viewBinding = true
    }

}

dependencies {
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.2")
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.okhttp3:okhttp:4.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    implementation("androidx.navigation:navigation-fragment:2.5.3")
    implementation("androidx.navigation:navigation-ui:2.5.3")
    implementation("androidx.annotation:annotation:1.6.0")

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("com.google.android.material:material:1.4.1")
    // ViewModel
    implementation("androidx.fragment:fragment-ktx:1.5.4")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
    // Testing
    implementation("androidx.test.espresso:espresso-contrib:3.5.1")
    testImplementation("junit:junit:4.13.2")
    testImplementation("org.mockito:mockito-core:3.12.4")
    testImplementation("org.robolectric:robolectric:4.7")

    androidTestImplementation("androidx.test:core:1.5.0")
    androidTestImplementation("org.mockito:mockito-core:3.12.4")
    androidTestImplementation("org.mockito:mockito-android:3.12.4")

    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("com.android.support.test:runner:1.0.2")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation("androidx.test:runner:1.5.2")
    androidTestImplementation("androidx.test:rules:1.5.0")

    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation("org.jacoco:org.jacoco.core:0.8.5")
}