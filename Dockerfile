FROM java:8
MAINTAINER eric
RUN echo "Asia/Taipei" > /etc/timezone
RUN dpkg-reconfigure -f noninteractive tzdata
RUN mkdir /docker_api
WORKDIR /docker_api
ADD fat-test-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","app.jar","--spring.profiles.active=dev"]
