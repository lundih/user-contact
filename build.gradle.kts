plugins {
	java
	id("org.springframework.boot") version "2.7.10"
	id("io.spring.dependency-management") version "1.1.0"
	jacoco
}

group = "com.lundih"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
	maven { url = uri("https://repo.spring.io/milestone") }
	maven { url = uri("https://repo.spring.io/snapshot") }
}

val mapstructVersion by extra { "1.5.3.Final" }

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jdbc")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springdoc:springdoc-openapi-ui:1.6.15")
	implementation("org.mapstruct:mapstruct:$mapstructVersion")

	compileOnly("org.projectlombok:lombok")

	annotationProcessor("org.projectlombok:lombok")
	annotationProcessor("org.mapstruct:mapstruct-processor:$mapstructVersion")

	runtimeOnly("org.postgresql:postgresql")
	runtimeOnly("com.h2database:h2")

	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.security:spring-security-test")
}

jacoco {
	toolVersion = "0.8.9"
}

tasks {
	withType<Test> {
		useJUnitPlatform()
		reports {
			junitXml.required.set(false)
			html.required.set(true)
			html.outputLocation.set(File("$buildDir/reports/junit/html"))
		}
	}

	jacocoTestReport {
		reports {
			xml.required.set(false)
			csv.required.set(false)
			html.outputLocation.set(layout.buildDirectory.dir("reports/jacoco/html"))
		}
	}

	jacocoTestCoverageVerification {
		dependsOn("jacocoTestReport")
		mustRunAfter("jacocoTestReport")
		violationRules {
			rule {
				limit {
					minimum = "0.8".toBigDecimal()
				}
			}
		}
	}

	getByName("check").dependsOn += "jacocoTestCoverageVerification"

	bootJar {
		archiveFileName.set("user-contact.jar")
	}
}
