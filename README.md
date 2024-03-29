# BlockChain Demo [![Run on Ainize](https://ainize.ai/images/run_on_ainize_button.svg)](https://ainize.web.app/redirect?git_repo=https://github.com/hibuz/blockchain-java)
This is a Block Chain Demo Application

## Development

To start your application in the dev profile, simply run:

    ./gradlew :chain-core:bootRun


## Building for production
To optimize the blockchain demo application for production, run:
[![Actions Status](https://github.com/hibuz/blockchain-java/workflows/Java%20CI/badge.svg)](https://github.com/hibuz/blockchain-java/actions)

    ./gradlew :demo-web:clean :demo-web:bootJar

To ensure everything worked, run:

    java -jar -Dspring.profiles.active=prod demo-web/build/libs/*.jar


## Testing

To launch your application's tests, run:

    ./gradlew :chain-core:test


## Using Docker to simplify development (optional)

You can also fully dockerize your application and all the services that it depends on.
To achieve this, first build a docker image of your app by running:
- chain-core
```bash
docker build --build-arg JAR_FILE=chain-core/build/libs/\*.jar -t hibuz/chain-core -f chain-core/Dockerfile .
```
- demo-web
```bash
docker build --build-arg JAR_FILE=demo-web/build/libs/\*.jar -t hibuz/demo-web -f demo-web/Dockerfile .
```
You can also dockerize in the subfolder
```bash
cd demo-web
docker build -t hibuz/demo-web .
```

Then run:
- chain-core
```bash
docker run --rm -p 6565:6565 hibuz/chain-core
```
- demo-web
```bash
docker run --rm --network host hibuz/demo-web
```
To use Github Container Registry\`s `arm64` image with `prod` profile, run:
```bash
docker run --rm -e "SPRING_PROFILES_ACTIVE=prod" -p 8080:8080 ghcr.io/hibuz/demo-web
```


## Rest APIs

Method	| Path	| Description	|  
------------- | ------------------------- | ------------- |
GET	| /	| Redirect to /swagger-ui/index.html
GET	| /api/	| Get block chain info
POST	| /api/?msg={msg}	| create newBlock with msg
POST	| /api/recruit	| create newBlock with generated recruit email


## For demo

 * Blockchain Demo web server(demo-web): https://ainize-blockchain-java-hibuz.endpoint.ainize.ai
 * Blockchain Demo grpc server(chain-core): hibuz.com:6565
