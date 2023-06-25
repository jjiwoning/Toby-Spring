plugins {
	java
	id("org.springframework.boot") version "3.1.0"
	id("io.spring.dependency-management") version "1.1.0"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	implementation("org.projectlombok:lombok:1.18.26")
	implementation("org.springframework:spring-context:6.0.9")
	implementation("org.springframework:spring-context-support:6.0.6")
	implementation("com.h2database:h2")
	implementation("org.springframework:spring-jdbc:6.0.6")
	implementation("javax.mail:mail:1.4.7")
	implementation("javax.activation:activation:1.1.1")

	testImplementation("org.junit.jupiter:junit-jupiter:5.9.2")
	testImplementation("org.assertj:assertj-core:3.24.2")
	testImplementation("org.springframework:spring-test:6.0.6")

	annotationProcessor("org.projectlombok:lombok:1.18.26")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
