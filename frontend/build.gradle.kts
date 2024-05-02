import com.github.gradle.node.npm.task.NpmTask
import com.github.gradle.node.task.NodeTask

repositories {
    mavenCentral()
}

plugins {
    id("com.github.node-gradle.node") version "3.1.1"
    base
}

group = "com.flartrion.librapp.frontend"

node {
//    version = "20.12.2"
    download = true
}

tasks.register<NpmTask>("jsStatic") {
    group = "build"
    args = listOf("run", "esbuild")
}

tasks.register<Copy>("elevateOutputsFront") {
    group = "build"
    dependsOn(tasks.getByName("jsStatic"))
    from("out/index.js","out/index.html","out/index.js.map")
    into(project.relativeProjectPath("../out/static"))
}
