plugins {
    `java-library`
    `maven-publish`
    id("com.gradleup.shadow") version "9.4.0"
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
    compileOnly("io.papermc.paper:paper-api:1.21.11-R0.1-SNAPSHOT")
    // TODO: Waiting for dazzleconf 2.0 complete and mature then migrate to it
    implementation("space.arim.dazzleconf:dazzleconf-ext-snakeyaml:1.3.0-RC1")
    implementation("org.bstats:bstats-bukkit:3.2.1")

    compileOnly("net.luckperms:api:5.5")
}

tasks {
    withType<JavaCompile> {
        options.encoding = "UTF-8"
    }

    configure<JavaPluginExtension> {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }

    build {
        dependsOn(shadowJar)
    }

    shadowJar {
        archiveFileName.set("${project.name}-${project.version}.${archiveExtension.get()}")
        exclude("META-INF/**") // Dreeam - Avoid to include META-INF/maven in Jar
        relocate("space.arim.dazzleconf", "${project.group}.libs.dazzleconf")
        relocate("org.yaml.snakeyaml", "${project.group}.libs.snakeyaml")
        relocate("org.bstats", "${project.group}.libs.bstats")
    }

    processResources {
        filesMatching("**/plugin.yml") {
            expand(
                mapOf(
                    "version" to project.version,
                    "description" to description
                )
            )
        }
    }
}

publishing {
    publications.create<MavenPublication>("maven") {
        from(components["java"])
    }
}
