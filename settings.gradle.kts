enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
	repositories {
		gradlePluginPortal()
		google()
		mavenCentral()
	}
}
rootProject.name = "TestPractice"

val modules = arrayOf(
	":app",
	":data",
	":common",
	":domain",
	":presentation",
	":features:item",
	":features:gallery"
)
include(*modules)