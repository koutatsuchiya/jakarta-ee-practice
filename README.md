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

***
# Running the app using docker 

### Run docker on local machine

1. Use this command to build image api ::
```shell
docker compose build api
```
- ***Note**: If any changes in source code, need to run to rebuild the image.

2. After build image, run this command (Make sure no process is running on ports **8081** and **9991**):
```shell
docker compose up -d
```
3. Check:
  * Navigate to http://localhost:8081 in your web browser to check
  * Navigate to http://localhost:9991 to go to WildFly console
