import java.net.URI

pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven{url = URI.create("https://oss.sonatype.org/content/repositories/ksoap2-android-releases/")}
    }
}

rootProject.name = "soapapi"
include(":app")
