FROM java:8

MAINTAINER root

COPY bill.jar /app.jar

ENV JAVA_OPTS="\
-server \
-Xmx300m \
-Xms300m "

EXPOSE 10003
ENTRYPOINT ["java","-jar","/app.jar"]