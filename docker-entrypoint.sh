#!/usr/bin/env sh

openssl pkcs12 -export -in ./cert/fullchain.pem -inkey ./cert/privkey.pem -out ./cert/keystore.p12 -name exampler -CAfile ./cert/chain.pem -caname root

java \
-Xmx1024m \
-DREDIRECT_PORT=443 \
-jar /usr/src/app/app.jar \
--server.ssl.key-store=/usr/src/app/cert/keystore.p12
