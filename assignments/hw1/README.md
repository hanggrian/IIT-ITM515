# [Lab 1](https://github.com/hanggrian/IIT-ITM515/blob/assets/assignments/hw1.pdf): Setup

This project contains an introductory Maven web application and a first-time
setup of MySQL database.

- JDK 17 with *Jakarta EE*, available with *IntelliJ IDEA Ultimate* I received
  for free with a student pack.
- Gradle with Kotlin scripts over Maven with XML configuration, essential for
  [The War Plugin](https://docs.gradle.org/current/userguide/war_plugin.html).
- Kotlin and [Kotlin DSL for HTML](https://github.com/Kotlin/kotlinx.html),
  write type-safe markup languages programmatically.
- *Payara Community Server* connected via [Gradle Cargo plugin](https://github.com/bmuschko/gradle-cargo-plugin).

> #### Note
>
> Future lab projects will be developed using plain **Java EE**. However, Gradle
  Kotlin DSL will still be used for build scripts.

## Building

Build task by Gradle will produce a WAR file in `build/libs/` directory.

```sh
./gradlew build
```

With the WAR file successfully generated, start Payara server and deploy the
application.

```sh
# initial build
./gradlew cargoRunLocal

# subsequent builds
./gradlew cargoRedeployLocal
```

## Screenshots

### MySQL Installation

<img width="480" src="https://github.com/hanggrian/IIT-ITM515/raw/assets/assignments/hw1/screenshot1_1.png"><br><small>Screenshot 1.1 &mdash; New user **itmd4515**</small>

<img width="480" src="https://github.com/hanggrian/IIT-ITM515/raw/assets/assignments/hw1/screenshot1_2.png"><br><small>Screenshot 1.2 &mdash; Grant all privileges to the new user</small>

<img width="480" src="https://github.com/hanggrian/IIT-ITM515/raw/assets/assignments/hw1/screenshot1_3.png"><br><small>Screenshot 1.3 &mdash; Non-root login with access to default schema</small>

### Introductory Maven Web Application

<img width="480" src="https://github.com/hanggrian/IIT-ITM515/raw/assets/assignments/hw1/screenshot2_1.png"><br><small>Screenshot 2.1 &mdash; Homepage</small>

<img width="480" src="https://github.com/hanggrian/IIT-ITM515/raw/assets/assignments/hw1/screenshot2_2.png"><br><small>Screenshot 2.2 &mdash; Repository page</small>

### Source View

<img width="640" src="https://github.com/hanggrian/IIT-ITM515/raw/assets/assignments/hw1/screenshot3_1.png"><br><small>Screenshot 3.1 &mdash; Source view 1</small>

<img width="640" src="https://github.com/hanggrian/IIT-ITM515/raw/assets/assignments/hw1/screenshot3_2.png"><br><small>Screenshot 3.2 &mdash; Source view 2</small>
