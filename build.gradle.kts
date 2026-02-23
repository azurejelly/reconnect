val minecraft: String = libs.versions.minecraft.get()

subprojects {
    apply(plugin = "reconnect.java-conventions")
    apply(plugin = "reconnect.publishing-conventions")

    version = "${rootProject.version}+${minecraft}"

    repositories {
        mavenLocal()
        mavenCentral()

        maven {
            name = "azurejelly"
            url = uri("https://repo.azuuure.dev/repository/maven-public/")
        }

        maven {
            name = "Terraformers"
            url = uri("https://maven.terraformersmc.com/")
        }

        maven {
            name = "DevAuth"
            url = uri("https://pkgs.dev.azure.com/djtheredstoner/DevAuth/_packaging/public/maven/v1")
        }
    }
}