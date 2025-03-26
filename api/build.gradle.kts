dependencies {
	implementation(project(":domain"))
	implementation(project(":infrastructure"))
	implementation("org.springframework.boot:spring-boot-starter-web")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	implementation("org.springframework.boot:spring-boot-starter-actuator")

	implementation("com.github.ulisesbocchio:jasypt-spring-boot-starter:3.0.4")

	implementation("org.springframework.boot:spring-boot-starter-security")
	testImplementation("org.springframework.security:spring-security-test")

	implementation("io.jsonwebtoken:jjwt-api:0.11.5")
	runtimeOnly("io.jsonwebtoken:jjwt-impl:0.11.5")
	runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.11.5")
}

tasks.named("bootJar") {
	enabled = true
}

tasks.named("jar") {
	enabled = false
}