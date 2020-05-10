#!/usr/bin/env sh

java \
-REDIREECT_PORT=443
-Xmx1024m \
-jar /usr/src/app/app.jar \
--server.ssl.key-store=/usr/src/app/ssl/keystore.p12
