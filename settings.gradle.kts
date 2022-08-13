pluginManagement {
	repositories {
		gradlePluginPortal()
		google()
		mavenCentral()
	}
}
rootProject.name = "TestPractice"
include(":app")
include(":domain")
include(":data")
include(":presentation")
include(":features:gallery")
include(":features:item")