plugins {
    `java-library`
    `maven-publish`
    id("com.gradleup.shadow") version "8.3.2"
}

group = "cn.dreeam.caeruleum"
version = "1.0.0-SNAOSHOT"
description = "A utility plugin used to allocate lang permission for i18n environment server"

repositories {
    mavenCentral()

    // PaperMC
    maven {
        name = "papermc-repo"
        url = uri("https://repo.papermc.io/repository/maven-public/")
    }
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.21.1-R0.1-SNAPSHOT")
    implementation("space.arim.dazzleconf:dazzleconf-ext-snakeyaml:1.3.0-M2")
    implementation("org.bstats:bstats-bukkit:3.0.3")

    compileOnly("net.luckperms:api:5.4")
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

configure<JavaPluginExtension> {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

tasks.build.configure {
    dependsOn("shadowJar")
}

tasks.withType<com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar> {
    archiveFileName = "${project.name}-${project.version}.${archiveExtension.get()}"
    exclude("META-INF/**") // Dreeam - Avoid to include META-INF/maven in Jar
    relocate("space.arim.dazzleconf", "cn.dreeam.caeruleum.libs.dazzleconf")
    relocate("org.yaml.snakeyaml", "cn.dreeam.caeruleum.libs.snakeyaml")
    relocate("org.bstats", "cn.dreeam.caeruleum.libs.bstats")
}

tasks {
    processResources {
        filesMatching("**/plugin.yml") {
            expand(
                "version" to project.version,
                "description" to project.description
            )
        }
    }
}

publishing {
    publications.create<MavenPublication>("maven") {
        from(components["java"])
    }
}
