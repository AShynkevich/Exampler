# Exampler

### Get started
./gradlew clean build

java -jar ./build/libs/math-0.0.1-SNAPSHOT.jar

### To run
./gradlew clean bootRun


# Docker building
sudo docker login --username unclesania
sudo docker build -t unclesania/exampler:latest .
sudo docker push unclesania/exampler:latest
