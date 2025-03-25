dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-data-jdbc")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	implementation("org.flywaydb:flyway-database-postgresql")
	implementation("org.postgresql:postgresql")

	// QueryDSL
	implementation("com.querydsl:querydsl-jpa:5.0.0:jakarta")
	kapt("com.querydsl:querydsl-apt:5.0.0:jakarta")
	kapt("jakarta.annotation:jakarta.annotation-api")
	kapt("jakarta.persistence:jakarta.persistence-api")

	implementation("com.github.ulisesbocchio:jasypt-spring-boot-starter:3.0.5")
	// P6spy
	implementation("com.github.gavlyukovskiy:p6spy-spring-boot-starter:1.9.0")
}

tasks.named("bootJar") {
	enabled = false
}

tasks.named("jar") {
	enabled = true
}