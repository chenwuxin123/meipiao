FROM openjdk:11-jdk
VOLUME /tmp
ADD target/message-1.0.0-SNAPSHOT.jar /Message.jar
ENTRYPOINT ["java","-Xms256m","-Xmx512m","-jar","/Message.jar","--spring.profiles.active=pro"]