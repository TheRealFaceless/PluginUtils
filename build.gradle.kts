plugins {
    id("java")
    id("io.papermc.paperweight.userdev") version "2.0.0-beta.18"
    //id("xyz.jpenilla.run-paper") version "2.3.1"
    //id("de.eldoria.plugin-yml.bukkit") version "0.7.1"
    id("io.freefair.lombok") version "8.14"
}

group = "com.github.TheRealFaceless"
version = "1.0"
val libPath = "C:\\Users\\Faceless\\Documents\\Libraries"

paperweight.reobfArtifactConfiguration = io.papermc.paperweight.userdev.ReobfArtifactConfiguration.MOJANG_PRODUCTION

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(21))
}

repositories {
    mavenCentral()
    maven {
        url = uri("https://papermc.io/repo/repository/maven-public/")
    }
}

dependencies {
    paperweight.paperDevBundle("1.21.5-R0.1-SNAPSHOT")
    compileOnly("io.papermc.paper:paper-api:1.21.5-R0.1-SNAPSHOT")
}

tasks.assemble {
    dependsOn("sourcesJar")
}

tasks.jar {
    destinationDirectory.set(file(libPath))
    exclude("META-INF/**")
}

/*
tasks.runServer {
    minecraftVersion("1.21.5")

    downloadPlugins {
        modrinth("viaversion", "5.4.2")
        modrinth("viabackwards", "5.4.2")
    }
}
 */

tasks.register<Jar>("sourcesJar") {
    archiveClassifier.set("sources")
    from(sourceSets.main.get().allSource)
    destinationDirectory.set(file(libPath))
}

/*
bukkit {
    main = "dev.faceless.debug.Main"
    name = "PluginUtils"
    version = "${project.version}"
    apiVersion = "1.21"
    author = "Faceless"
    description = "Plugin utilities to speed up production."
}
 */
