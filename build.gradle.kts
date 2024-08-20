plugins {
    id("java")
    id("io.papermc.paperweight.userdev") version "1.7.1"
    id("xyz.jpenilla.run-paper") version "2.3.0"
    id("net.minecrell.plugin-yml.bukkit") version "0.6.0"
    id("io.freefair.lombok") version "8.7.1"

}

group = "com.github.TheRealFaceless"
version = "1.0"
val libPath = "C:\\Users\\Faceless\\Desktop\\Devkit\\Libraries"

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
    paperweight.paperDevBundle("1.21.1-R0.1-SNAPSHOT")
    compileOnly("io.papermc.paper:paper-api:1.21.1-R0.1-SNAPSHOT")
}

tasks.assemble {
    dependsOn("sourcesJar")
}

tasks.jar {
    destinationDirectory.set(file(libPath))
}

tasks.runServer {
    minecraftVersion("1.21")

    downloadPlugins {
        modrinth("viaversion", "5.0.3")
        modrinth("viabackwards", "5.0.3")
    }
}

tasks.register<Jar>("sourcesJar") {
    archiveClassifier.set("sources")
    from(sourceSets.main.get().allSource)
    destinationDirectory.set(file(libPath))
}

bukkit {
    main = "dev.faceless.debug.Main"
    apiVersion = "1.21"
    author = "Faceless"
    description = "Plugin utilities to speed up production."
}