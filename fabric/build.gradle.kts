plugins {
    alias(libs.plugins.loom)
}

base {
    archivesName.set("${project.property("archive_base_name")}-fabric")
}

loom {
    runs {
        named("client") {
            client()
            ideConfigGenerated(true)
            runDir("run")
            configName = "Fabric/Client"
        }
    }
}

dependencies {
    implementation(project(":common"))

    minecraft(libs.minecraft)
    implementation(libs.fabric.loader)
    implementation(libs.fabric.api)

    compileOnly(libs.lombok)
    annotationProcessor(libs.lombok)

    runtimeOnly(libs.devauth.fabric)
}

tasks {
    jar {
        from(sourceSets["main"].output)
        from(project(":common").sourceSets["main"].output.classesDirs)
    }

    processResources {
        filteringCharset = "UTF-8"
        duplicatesStrategy = DuplicatesStrategy.EXCLUDE

        from(project(":common").sourceSets.main.get().resources)

        // TODO: update minecraft version range on fabric.mod.json once 26.1 releases
        filesMatching("fabric.mod.json") {
            expand(
                "mod_id" to rootProject.property("mod_id").toString(),
                "mod_name" to rootProject.property("mod_name").toString(),
                "mod_description" to rootProject.property("mod_description").toString(),
                "mod_version" to project.version,
                "mod_license" to rootProject.property("mod_license").toString(),

                "minecraft_version" to libs.versions.minecraft.get(),
                "loader_version" to libs.versions.fabric.loader.get(),
            )
        }
    }
}