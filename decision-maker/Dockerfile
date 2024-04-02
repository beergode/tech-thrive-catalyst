FROM gcr.io/distroless/java21-debian12
LABEL maintainer=tech-thrive-catalyst

COPY build/libs/decision-maker.jar /lib/decision-maker.jar

EXPOSE 8875

CMD ["-Djava.security.egd=file:/dev/./urandom", "-Dfile.encoding=UTF-8", "lib/decision-maker.jar"]
