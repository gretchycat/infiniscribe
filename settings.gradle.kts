pluginManagement {
    resolutionStrategy.eachPlugin {
        if (requested.id.id.startsWith("com.android.")) useModule("com.android.tools.build:gradle:8.9.2")
        if (requested.id.id.startsWith("org.jetbrains.kotlin.")) useModule("org.jetbrains.kotlin:kotlin-gradle-plugin:2.2.20")
    }
    repositories { google(); mavenCentral(); gradlePluginPortal() }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google(); mavenCentral()
        maven("https://jitpack.io") { content { includeGroup("com.github.omicronapps") } }
    }
}
rootProject.name = "InfiniScribe"

// Read only this checkout's SDK configuration. Composite builds share the resolved SDK.
if (System.getenv("ANDROID_HOME").isNullOrBlank() && System.getenv("ANDROID_SDK_ROOT").isNullOrBlank() && System.getProperty("android.home").isNullOrBlank()) {
    val local = file("local.properties")
    if (local.isFile) java.util.Properties().apply { local.inputStream().use { load(it) } }
        .getProperty("sdk.dir")?.let { System.setProperty("android.home", file(it).absolutePath) }
}
val dependencyRoot = file("libraries")
System.setProperty("infini.dependencies", dependencyRoot.canonicalPath)
includeBuild(dependencyRoot.resolve("libinfiniui"))
includeBuild(dependencyRoot.resolve("libinfinistream"))
includeBuild(dependencyRoot.resolve("libinfinicore"))
includeBuild(dependencyRoot.resolve("libinfiniselect"))
includeBuild(dependencyRoot.resolve("libinfinicanvas"))
includeBuild(dependencyRoot.resolve("libinfiniscribe"))
includeBuild(dependencyRoot.resolve("libinfinisound"))
include(":document-common")
project(":document-common").projectDir = file("modules/document-common")
include(":viewer-text")
project(":viewer-text").projectDir = file("modules/viewer-text")
include(":viewer-markdown")
project(":viewer-markdown").projectDir = file("modules/viewer-markdown")
include(":viewer-html")
project(":viewer-html").projectDir = file("modules/viewer-html")
include(":viewer-epub")
project(":viewer-epub").projectDir = file("modules/viewer-epub")
include(":viewer-csv")
project(":viewer-csv").projectDir = file("modules/viewer-csv")
include(":viewer-pdf")
project(":viewer-pdf").projectDir = file("modules/viewer-pdf")
include(":viewer-font")
project(":viewer-font").projectDir = file("modules/viewer-font")
include(":viewer-mindmap")
project(":viewer-mindmap").projectDir = file("modules/viewer-mindmap")
