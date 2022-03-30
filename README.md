# Exampler

### Get started
`./gradlew clean build`

`java -jar ./build/libs/math-0.0.1-SNAPSHOT.jar`

### To run
`./gradlew clean bootRun`

# Docker building
`sudo docker login --username unclesania`

`sudo docker build -t unclesania/exampler:latest .`

`sudo docker push unclesania/exampler:latest`

### Docker run
`sudo docker run --name exampler -d --rm -p 80:8080 -p 443:8443 -it unclesania/exampler:latest`

# Create self-singed certificate
### To create PKCS12 keystore
`keytool -genkeypair -alias exampler -keyalg RSA -keysize 2048 -storetype PKCS12 -keystore keystore.p12 -validity 3650 -storepass password`

# Import existing certificate

`cat cert.pem intermediate.pem root.pem > import.pem`

`openssl pkcs12 -export -in import.pem -inkey private-key.pem
 -name exampler > keystore.p12`

`keytool -importkeystore -srckeystore keystore.p12
-destkeystore keystore.keys -srcstoretype pkcs12 -alias exampler`

# Import letsencrypt certificate

`openssl pkcs12 -export -in /etc/letsencrypt/live/uncle-sania.fun/fullchain.pem 
-inkey /etc/letsencrypt/live/uncle-sania.fun/privkey.pem -out ./ssl/keystore.p12 -name exampler 
-CAfile /etc/letsencrypt/live/uncle-sania.fun/chain.pem -caname root -password pass:yourpassword`

# Official site
[https://uncle-sania.fun](https://uncle-sania.fun)
