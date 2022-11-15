pluginManagement {
	repositories {
		gradlePluginPortal()
		google()
		mavenCentral()
	}
}
rootProject.name = "TestPractice"

val modules = arrayOf(
	":app", ":domain", ":data", ":presentation", ":features:gallery", ":features:item", ":common"
)
include(*modules)
