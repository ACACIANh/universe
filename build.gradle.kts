import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot")
    id("io.spring.dependency-management")
    id("jacoco")
    kotlin("jvm")
    kotlin("plugin.spring")
    kotlin("plugin.jpa")
}

group = "kr.bomiza"
version = "0.0.2-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
    mavenCentral()
}

extra["springCloudVersion"] = "2021.0.1"

dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
    }
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-oauth2-client")

    implementation("io.jsonwebtoken:jjwt-api:0.11.5")
    implementation("io.jsonwebtoken:jjwt-impl:0.11.5")
    implementation("io.jsonwebtoken:jjwt-jackson:0.11.5")

    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.0.2")

    implementation("com.fasterxml.jackson.core:jackson-databind")
    implementation("com.google.code.gson:gson:2.8.9")

    implementation("com.h2database:h2")

    implementation("io.github.microutils:kotlin-logging:3.0.4")
    implementation("org.springframework.boot:spring-boot-starter-mustache")

    testImplementation("org.springframework.boot:spring-boot-starter-test")

    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
//    finalizedBy("jacocoTestReport")
}

// jacoco start
tasks.jacocoTestReport {
    reports {
        // html로 report 생성하기
        // 빌드경로/jacoco/report.html 폴더 내부로 경로 설정
        html.destination = file("$buildDir/jacoco/report.html")
    }
    dependsOn("test")
    finalizedBy("jacocoTestCoverageVerification")
}

// jacoco 커버리지 검증 설정
tasks.jacocoTestCoverageVerification {
    violationRules {
        rule {
            isEnabled = true // 커버리지 적용 여부
            element = "CLASS" // 커버리지 적용 단위

            // 라인 커버리지 설정
            // 적용 대상 전체 소스 코드들을 한줄 한줄 따졌을 때 테스트 코드가 작성되어 있는 줄의 빈도
            // 테스트 코드가 작성되어 있는 비율이 0% 이상이어야 함
            limit {
                counter = "LINE"
                value = "COVEREDRATIO"
                minimum = "0.00".toBigDecimal()
            }

            // 브랜치 커버리지 설정
            // if-else 등을 활용하여 발생되는 분기들 중 테스트 코드가 작성되어 있는 빈도
            // 테스트 코드가 작성되어 있는 비율이 0% 이상이어야 함
            limit {
                counter = "BRANCH"
                value = "COVEREDRATIO"
                minimum = "0.00".toBigDecimal()
            }

            // 라인 최대 갯수 설정
            // 빈 줄을 제외하고 하나의 자바 파일에서 작성될 수 있는 최대 라인 갯수
            // 한 파일에 최대 500줄까지 작성되어야 함
            limit {
                counter = "LINE"
                value = "TOTALCOUNT"
                maximum = "500".toBigDecimal()
            }
        }
    }
}
// jacoco end

val jar: Jar by tasks
jar.enabled = false

tasks.withType<org.springframework.boot.gradle.tasks.bundling.BootJar> {
    enabled = true
    archiveFileName.set("app.jar")
}