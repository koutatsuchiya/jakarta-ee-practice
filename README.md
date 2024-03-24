## Company BE 

## Prerequisite:
* Local machine must have
  * Java: OpenJDK 17 (https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html
  * Maven: 3.9.6 (https://maven.apache.org/download.cgi)
  * WildFly 26.1.3 Final (https://www.wildfly.org/downloads/)
  * Docker (via WSL2):
    * Install WSL2: https://learn.microsoft.com/en-us/windows/wsl/install
    * Fully instructions: https://dataedo.com/docs/installing-docker-on-windows-via-wsl 

## Settings:
- Setting `[JAVA_HOME]` environment variable for your computer that points to your Java folder
- Setting `[MAVEN_HOME]` environment variable for your computer that points to your Maven folder
- Setting `[JBOSS_HOME]` environment variable for your computer that points to your WildFly folder
- Set system `PATH` for your computer that points to your Java, Maven and WildFly bin folder

# Test, build, deploy application
## Running unit test
- Clean the application and run available unit test 
```shell
mvn clean test
```
## Build and upload application
- Clean the application then create `company.war` file in the `target` folder
```shell
mvn clean package -DskipTests
```
## Deploy application on WildFly server
- Deploy app to WildFly
```shell
mvn deploy -DskipTests
```

## Fast run
```shell
mvn clean package deploy -DskipTests
```

## Run WildFly
```shell
standalone.bat
```
- WildFly will auto deploy the war file
- Navigate to http://localhost:8080 in your web browser to check
- If you want to check if your app is deployed, navigate to http://localhost:9990/console/index.html#deployments
- ***Note**: You can just run WildFly one time

# Set up database
- Create a postgres database
- Create a file flyway.properties in folder /src/resources/config/local as this
  - flyway.url=
  - flyway.user=
  - flyway.password=
```shell
mvn flyway:migrate -Plocal -X
```
