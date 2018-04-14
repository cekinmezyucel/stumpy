# Stumpy
A URL Shortener Application.

## Objective
The main objective of this project; build a complete application with various technologies and approaches.

### Tech
Stumpy developed with Java 8, a number of technologies and open source projects:

* [SpringBoot]
* [Redis]
* [Docker]
* [Maven]
* [JUnit]
* [Swagger]
* [Asciidoctor]
* [Swagger2Markup]
* [Apache Maven Checkstyle Plugin]

## For Developers
To avoid build failures you must import these files (eclipse-code-formatter.xml, eclipse.importorder) to your IDE. The files are under the codequality folder.

### Installation
Stumpy requires [JDK8]

This is a maven project. Before the Docker initialization please run maven install command.
```sh
$ cd ${project.basedir}
$ mvn clean install
```

#### Documentation
After building the project. You can check out the documentation of project html file at this path.
```sh
file://${project.basedir}/target/generated-docs/html/en/index.html
```

#### Docker
For log volume please create stumpy-service-log-repo
```sh
$ docker volume create --name=stumpy-service-log-repo
```

Composing containers
```sh
$ cd ${project.basedir}/docker/
$ docker-compose up --build
```

[SpringBoot]: <https://projects.spring.io/spring-boot/>
[Redis]: <https://redis.io/>
[Docker]: <https://www.docker.com/>
[JUnit]: <https://junit.org/>
[Swagger]: <https://swagger.io/>
[Asciidoctor]: <https://asciidoctor.org/>
[Swagger2Markup]: <https://github.com/Swagger2Markup/swagger2markup>
[Maven]: <https://maven.apache.org/>
[Apache Maven Checkstyle Plugin]: <https://maven.apache.org/plugins/maven-checkstyle-plugin/>
[JDK8]: <http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html>