plugins {
    // Apply the java plugin for better toolchain detection support.
    id("java")
    // Apply the application plugin to add support for building a CLI application in Java.
    id("application")
    // Apply javafxplugin for JavaFX support.
    id("org.openjfx.javafxplugin") version "0.0.13"
    // Apply jlink for building the app.
    id("org.beryx.jlink") version "2.26.0"
    // Apply lombok.
    id("io.freefair.lombok") version "8.0.1"
}

version = extra["appVersion"] as String
val osName: String = System.getProperty("os.name")

ext {
    // Links for downloading jdks.
    set("jdkLinks", mapOf(
        "linux-aarch64" to "https://github.com/adoptium/temurin17-binaries/releases/download/jdk-17.0.7%2B7/OpenJDK17U-jdk_aarch64_linux_hotspot_17.0.7_7.tar.gz",
        "linux-x64"     to "https://github.com/adoptium/temurin17-binaries/releases/download/jdk-17.0.7%2B7/OpenJDK17U-jdk_x64_linux_hotspot_17.0.7_7.tar.gz",
        "macos-aarch64" to "https://github.com/adoptium/temurin17-binaries/releases/download/jdk-17.0.7%2B7/OpenJDK17U-jdk_aarch64_mac_hotspot_17.0.7_7.tar.gz",
        "macos-x64"     to "https://github.com/adoptium/temurin17-binaries/releases/download/jdk-17.0.7%2B7/OpenJDK17U-jdk_x64_mac_hotspot_17.0.7_7.tar.gz",
        "win-x64"       to "https://github.com/adoptium/temurin17-binaries/releases/download/jdk-17.0.7%2B7/OpenJDK17U-jdk_x64_windows_hotspot_17.0.7_7.zip"
    ))
}

repositories {
    // Use Maven Central for resolving dependencies.
    mavenCentral()
}

dependencies {
    implementation("eu.iamgio:animated:0.7.0")
    implementation("io.github.typhon0:AnimateFX:1.2.3")
    implementation("com.google.guava:guava:31.1-jre")
    implementation("org.kordamp.ikonli:ikonli-javafx:12.3.1")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.14.2")
    implementation("org.apache.logging.log4j:log4j-api:2.20.0")
    implementation("org.apache.logging.log4j:log4j-core:2.20.0")
    implementation("io.github.palexdev:materialfx:11.15.0")

    testImplementation("org.junit.jupiter:junit-jupiter:5.9.2")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

application {
    mainModule.set("org.pixellauncher")
    mainClass.set("org.pixellauncher.App")
}

javafx {
    version = extra["javafxVersion"] as? String
    modules = listOf("javafx.controls", "javafx.fxml")
}

jlink {
    addOptions(
        "--strip-debug",
        "--compress", "2",
        "--no-header-files",
        "--no-man-pages"
    )

    // Remove the following as soon as the dependencies are fixed (upstream)
    // forceMerge is usually needed when some non-modular artifacts in the
    // dependency graph use code that was previously part of the JDK, but it was
    // removed in the newer releases.
    // The pom.xml associated with such a non-modular artifact does not mention
    // that the artifact depends on the removed code (because the artifact was
    // published when this code was still available in the JDK).
    forceMerge("jackson", "log4j-api")

    // Some libraries using JavaFX do not specify their JavaFX dependencies
    // because JavaFX was part of the JDK before being removed in Java 11.
    // Including addExtraDependencies("javafx") into the jlink block solves
    // this problem.
    // It Can be removed once AnimateFX and animated become modular.
    addExtraDependencies("javafx")

    launcher {
        noConsole = true
    }
    imageZip.set(file("$buildDir/PixelLauncher.zip"))

    val javafxVersion = extra["javafxVersion"] as String
    val jdkLinks = extra["jdkLinks"] as Map<String, String>
    if (extra["jlinkTargetPlatform"] as String == "all") {
        // Run all cases.
        jdkLinks.forEach { (key, value) ->
            targetPlatform(key) {
                setJdkHome(jdkDownload(value))
                addExtraModulePath("jmods/$javafxVersion/${key.replace("-", "/")}")
            }
        }
    } else {
        val jlinkTargetPlatform = extra["jlinkTargetPlatform"] as String
        targetPlatform(jlinkTargetPlatform) {
            setJdkHome(jdkDownload(jdkLinks.getValue(jlinkTargetPlatform)))
            addExtraModulePath("jmods/$javafxVersion/${jlinkTargetPlatform.replace("-", "/")}")
        }
    }

    jpackage {
        targetPlatformName = extra["jpackageTargetPlatform"] as? String
        imageName = "Pixel Launcher"
        installerName = "Pixel Launcher"
        vendor = "Brayden Chan"

        if (extra["jpackageOutputType"] as? String != "default") {
            installerType = extra["jpackageOutputType"] as? String
        }

        installerOptions = mutableListOf<String>()
        if (osName.lowercase().contains("windows")) {
            resourceDir = file("src/main/resources/assets")
            installerOptions.plusAssign(
                listOf(
                    "--win-dir-chooser",
                    "--win-menu",
                    "--win-menu-group", "Pixel Launcher",
                    "--win-per-user-install",
                    "--win-shortcut",
                    "--win-shortcut-prompt",
                    "--win-update-url", "https://github.com/megabyte6/PixelLauncher/releases/latest"
                )
            )
        } else if (osName.lowercase().contains("linux")) {
            resourceDir = file("src/main/resources/assets")
            installerOptions.plusAssign(
                listOf(
                    "--linux-package-name", "pixel-launcher",
                    "--linux-menu-group", "Pixel Launcher",
                    "--linux-shortcut"
                )
            )
        } else {
            resourceDir = file("src/main/resources/assets")
        }
    }
}

tasks.named<Test>("test") {
    useJUnitPlatform()
}
