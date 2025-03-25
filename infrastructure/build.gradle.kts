dependencies {
	implementation(project(":domain"))
	implementation("org.springframework.boot:spring-boot-starter-web")
}

tasks.named("bootJar") {
	enabled = true
}

tasks.named("jar") {
	enabled = false
}