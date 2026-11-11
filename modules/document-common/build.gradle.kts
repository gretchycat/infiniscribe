plugins { id("com.android.library"); id("org.jetbrains.kotlin.android") }
android {
    namespace = "com.infiniscribe.documents"
    compileSdk = 36
    defaultConfig { minSdk = 26 }
    compileOptions { sourceCompatibility = JavaVersion.VERSION_17; targetCompatibility = JavaVersion.VERSION_17 }
    kotlinOptions { jvmTarget = "17" }
    testOptions { unitTests.isReturnDefaultValues = true }
}
dependencies {
    api("io.github.gretchycat.libinfiniui:ui-android:0.1.0")
    api("io.github.gretchycat.libinfiniscribe:scribe-office:0.1.0")
}
