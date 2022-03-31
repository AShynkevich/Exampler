# Use the official image as a parent image.
FROM azul/zulu-openjdk-alpine:11

# copying the self-sighed cert to image
WORKDIR /usr/src/app
# creating folder /usr/src/app/ssl
RUN mkdir ssl

COPY docker-entrypoint.sh /usr/local/bin/entrypoint
# make entrypoint as executable
RUN chmod +x /usr/local/bin/entrypoint
RUN --mount=type=secret,id=CERT_PASSWORD

# Copy the file from your host to your current location.
COPY build/libs/math-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080
ENTRYPOINT ["entrypoint"]
