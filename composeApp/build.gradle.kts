import org.jetbrains.compose.resources.ResourcesExtension
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.plugin.mpp.apple.XCFramework

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.ksp)
    alias(libs.plugins.roomDatabase)
}

// [Target](https://www.jetbrains.com/help/kotlin-multiplatform-dev/multiplatform-discover-project.html#targets)
// A Kotlin target defines the platform a project compiles to, specifying its binary format,
// language features, and dependencies.
kotlin {
    // 1.Targets declaration:
    // 1.1.Declares a JVM target
    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

    // 1.2.Declares a JVM target
    val xcFramework = XCFramework()
    listOf(
        iosX64(), // Declares a target that corresponds to X64 iPhone
        iosArm64(), // Declares a target that corresponds to 64-bit iPhones
        iosSimulatorArm64(), // Declares a target that corresponds to 64-bit iOS simulators
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
            xcFramework.add(this)
        }
    }

    // 2. Source set declaration
    // [Source set](https://www.jetbrains.com/help/kotlin-multiplatform-dev/multiplatform-discover-project.html#source-sets)
    // A Kotlin source set is a named group of code and resources with its own targets, dependencies,
    // and compiler options — commonMain is the default one shared across all platforms.
    sourceSets {
        androidMain.dependencies {
            implementation(compose.preview)
            implementation(libs.androidx.activity.compose)

            // Android-specific Koin integration
            implementation(libs.koin.android)
        }
        commonMain.dependencies {
            // Configure the commonMain source set
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(libs.androidx.lifecycle.viewmodelCompose)
            implementation(libs.androidx.lifecycle.runtimeCompose)

            // coroutines - https://github.com/Kotlin/kotlinx.coroutines/blob/master/README.md#using-in-your-projects
            implementation(libs.kotlinx.coroutines.core)

            // navigation-compose - https://www.jetbrains.com/help/kotlin-multiplatform-dev/compose-navigation.html?utm_source=chatgpt.com#basic-navigation-example
            implementation(libs.navigation.compose)

            // serialization
            implementation(libs.kotlinx.serialization.json)

            // ====================================
            // koin - https://insert-koin.io/docs/setup/koin/
            implementation(project.dependencies.platform(libs.koin.bom))
            implementation(libs.koin.core)
            // basic integration of Koin into Compose Multiplatform
            implementation(libs.koin.compose)
            // support for injecting ViewModel (CMP expect/actual)
            implementation(libs.koin.compose.viewmodel)
            // koin-compose-viewmodel-navigation → integrates ViewModel injection with navigation
            implementation(libs.koin.compose.viewmodel.navigation)


            // DataStore library - https://developer.android.com/topic/libraries/architecture/datastore
            implementation(libs.androidx.datastore)
            // The Preferences DataStore library
            implementation(libs.androidx.datastore.preferences)

            // Jetpack Compose shadow
            implementation(project.dependencies.platform("androidx.compose:compose-bom:2025.08.00"))

            // Room database
            implementation(libs.roomDatabase.runtime)
            implementation(libs.sqlite.bundled)

            // # KotlinX multiplatform date/time library - https://github.com/Kotlin/kotlinx-datetime
            implementation(libs.kotlinx.datetime)

        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
    // KSP Common sourceSet
    sourceSets.named("commonMain").configure {
        kotlin.srcDir("build/generated/ksp/metadata/commonMain/kotlin")
    }

    // this code configures turn off warning when use keyword `expected` & `actual` keyword
    compilerOptions {
        // Common compiler options applied to all Kotlin source sets
        freeCompilerArgs.add("-Xexpect-actual-classes")
    }
}

// 👇 Place it here, at top-level (not inside kotlin {}).
// Customizing accessor class generation - https://www.jetbrains.com/help/kotlin-multiplatform-dev/compose-multiplatform-resources-usage.html#customizing-accessor-class-generation
compose.resources {
    publicResClass = true // true makes the generated Res class public
    generateResClass = ResourcesExtension.ResourceClassGeneration.Auto // or Yes / No
}



android {
    namespace = "org.astronex.olyn"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "org.astronex.olyn"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    // Room database - set schema location
    room {
        schemaDirectory("$projectDir/schemas")
    }
}

dependencies {
    debugImplementation(compose.uiTooling)

    // Room compiler with KSP
    add("kspAndroid", libs.roomDatabase.compiler)
    add("kspIosSimulatorArm64", libs.roomDatabase.compiler)
    add("kspIosX64", libs.roomDatabase.compiler)
    add("kspIosArm64", libs.roomDatabase.compiler)
}

// Room database - set schema location
// For KSP, configure using KSP extension:
//ksp {
//    arg(RoomSchemaArgProvider(File(projectDir, "schemas")))
//}

// Room database - set schema location
//class RoomSchemaArgProvider(
//    @get:InputDirectory
//    @get:PathSensitive(PathSensitivity.RELATIVE)
//    val schemaDir: File
//) : CommandLineArgumentProvider {
//    override fun asArguments(): Iterable<String> {
//        // Note: If you're using KAPT and javac, change the line below to
//        // return listOf("-Aroom.schemaLocation=${schemaDir.path}").
//        return listOf("room.schemaLocation=${schemaDir.path}")
//    }
//}

tasks.register("packForXcode", Sync::class) {
    group = "build"
    val mode = System.getenv("CONFIGURATION") ?: "Debug"
    val framework = kotlin.iosSimulatorArm64().binaries.getFramework(mode)

    inputs.property("mode", mode)
    dependsOn(framework.linkTaskProvider)

    from({ framework.outputDirectory })
    into(File(buildDir, "xcode-frameworks"))
}