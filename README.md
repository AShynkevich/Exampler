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

# Official site
[https://uncle-sania.fun](https://uncle-sania.fun)
