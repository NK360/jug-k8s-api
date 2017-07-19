FROM java:8-alpine
ADD jug-api.jar app.jar
RUN sh -c 'touch /app.jar'
ENV JAVA_OPTS=""
ENV APPLICATION_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar $APPLICATION_OPTS" ]
