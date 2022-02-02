FROM openjdk:11
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} fin-api-0.0.1.jar
EXPOSE 8888
ENTRYPOINT ["java","-jar","/fin-api-0.0.1.jar"]