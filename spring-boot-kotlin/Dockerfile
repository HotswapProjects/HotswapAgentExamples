FROM openjdk:8-jre-slim

MAINTAINER Jiří Bubník <bubnik@datalite.cz>

VOLUME /tmp
WORKDIR /app/tsm
ADD target/tsm-dms*.jar /app/tsm/tsm-dms.jar
EXPOSE 8082/tcp
ENTRYPOINT ["java","-XX:MaxRAM=300m","-Djava.security.egd=file:/dev/./urandom","-jar","tsm-dms.jar", "--spring.profiles.active=kubernetes"]
