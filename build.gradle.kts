import info.solidsoft.gradle.pitest.PitestPluginExtension

plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.pitest)
    alias(libs.plugins.versions)
}

group = "nl.jjkester"
version = "1.0-SNAPSHOT"

dependencies {
    implementation(libs.kotlin.stdlib)
    implementation(libs.bundles.kotlinx.multik)
    implementation(libs.apache.commons.math)

    testImplementation(libs.junit)
    testImplementation(libs.assertk)
    testImplementation(libs.mockito)
}

tasks.test {
    useJUnitPlatform()
}

configure<PitestPluginExtension> {
    pitestVersion = libs.versions.test.pitest.tool.get()
    junit5PluginVersion = libs.versions.test.pitest.junit.get()
    outputFormats = setOf("HTML")
}

kotlin {
    jvmToolchain(17)
}
