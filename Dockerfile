FROM azul/zulu-openjdk-alpine:11
VOLUME /tmp
COPY /build/libs/user-contact.jar /app.jar
EXPOSE 10001
ENTRYPOINT ["java","-jar", "/app.jar"]