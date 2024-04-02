FROM gcr.io/distroless/java21-debian12
LABEL maintainer=tech-thrive-catalyst

COPY build/libs/tech-thrive-catalyst.jar /lib/tech-thrive-catalyst.jar

EXPOSE 8875

CMD ["-Djava.security.egd=file:/dev/./urandom", "-Dfile.encoding=UTF-8", "/usr/src/myapp/lib/decision-maker.jar"]
