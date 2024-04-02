FROM gcr.io/distroless/java21-debian12
LABEL maintainer=tech-thrive-catalyst
WORKDIR /usr/src/myapp

COPY build/libs/decision-maker.jar /usr/src/myapp/lib/decision-maker.jar

EXPOSE 8875

CMD ["-Djava.security.egd=file:/dev/./urandom", "-Dfile.encoding=UTF-8", "/usr/src/myapp/lib/decision-maker.jar"]
