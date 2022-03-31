#!/usr/bin/env sh

echo "PASS: $CERT_PASSWORD"

java \
-Xmx1024m \
-DREDIRECT_PORT=443 \
-jar /usr/src/app/app.jar \
--server.ssl.key-store=/usr/src/app/ssl/keystore.p12
