plugins {
    id("java")
    id("war")
}

group = "mkkz7"
version = "app"

repositories {
    mavenCentral()
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

dependencies {
    // Jakarta EE Web Profile API
    compileOnly("jakarta.platform:jakarta.jakartaee-api:10.0.0")

    // Для компиляции
    implementation("at.favre.lib:bcrypt:0.10.2")
    implementation("jakarta.servlet:jakarta.servlet-api:6.0.0")
    implementation("jakarta.ws.rs:jakarta.ws.rs-api:3.1.0")
    implementation("jakarta.persistence:jakarta.persistence-api:3.1.0")

    implementation("org.hibernate.orm:hibernate-core:6.4.4.Final")
    implementation("org.postgresql:postgresql:42.7.3")

    compileOnly("org.projectlombok:lombok:1.18.30")
    annotationProcessor("org.projectlombok:lombok:1.18.30")

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}