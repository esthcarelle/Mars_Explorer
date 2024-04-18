pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Mars Explorer"
include(":app")
include(":core")
include(":lib")
include(":data")
include(":data:model")
include(":data:remote")
include(":data:repository")
include(":domain")
include(":feature")
include(":feature:detailscreen")
include(":feature:listscreen")
include(":core:common")
include(":core:design")
include(":core:utils")
