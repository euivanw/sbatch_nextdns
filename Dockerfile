FROM eclipse-temurin:17-jdk
WORKDIR /app
COPY target/*.jar sbatch_nextdns.jar
ENTRYPOINT ["java","-jar","/app/sbatch_nextdns.jar"]