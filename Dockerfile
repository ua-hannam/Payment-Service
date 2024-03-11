FROM openjdk:17-ea-11-jdk-slim
VOLUME /tmp
COPY build/libs/*.jar paymentService.jar
ENTRYPOINT ["java", "-jar", "paymentService.jar"]
