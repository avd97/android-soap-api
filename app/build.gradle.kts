plugins {
    id("com.android.application")
}

android {
    namespace = "com.kb038.soapapi"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.kb038.soapapi"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    useLibrary("org.apache.http.legacy")

    buildFeatures {
        dataBinding = true
    }

    packaging {
        resources.excludes.add("META-INF/NOTICE.md")
        resources.excludes.add("META-INF/LICENSE.md")
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
}

dependencies {

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    implementation ("com.google.code.ksoap2-android:ksoap2-android:3.6.4")
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-scalars:2.9.0") // For string responses
    implementation ("com.squareup.okhttp3:okhttp:4.10.0")
    implementation ("com.squareup.okhttp3:logging-interceptor:4.10.0")
    implementation ("com.fasterxml.jackson.dataformat:jackson-dataformat-xml:2.12.3") // Jackson XML
    implementation ("javax.xml.stream:stax-api:1.0-2") // StAX API
    implementation ("com.fasterxml.woodstox:woodstox-core:6.2.4") // Woodstox StAX Implementation



}