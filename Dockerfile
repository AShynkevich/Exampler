# Use the official image as a parent image.
FROM azul/zulu-openjdk-alpine:11

# creating folder /usr/src/app/ssl
RUN mkdir ssl
# copying the self-sighed cert to image
COPY ssl/keystore.p12 /usr/src/app/ssl/keystore.p12
# Set the working directory.
WORKDIR /usr/src/app

COPY docker-entrypoint.sh /usr/local/bin/entrypoint
# make entrypoint as executable
RUN chmod +x /usr/local/bin/entrypoint
# Copy the file from your host to your current location.
COPY build/libs/math-0.0.1-SNAPSHOT.jar app.jar

# Inform Docker that the container is listening on the specified port at runtime.
EXPOSE 8080
ENTRYPOINT ["entrypoint"]
