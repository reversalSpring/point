FROM openjdk:11-jdk

ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} springbootapp.jar

ENTRYPOINT ["java", "-jar", "-Duser.timezone=Asia/Seoul", "/springbootapp.jar"]