enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
	repositories {
		gradlePluginPortal()
		google()
		mavenCentral()
		maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
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