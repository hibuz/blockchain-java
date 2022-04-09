# BlockChain Demo [![Run on Ainize](https://ainize.ai/static/images/run_on_ainize_button.svg)](https://ainize.web.app/redirect?git_repo=github.com/hibuz/blockchain-java)
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

    ./gradlew :chain-core:jibDockerBuild --image=hibuz/chain-core
    ./gradlew :demo-web:jibDockerBuild --image=hibuz/demo-web

Then run:

    docker run --rm -p 6565:6565 hibuz/chain-core
    docker run --rm -p 8080:8080 hibuz/demo-web


## Rest APIs


Method	| Path	| Description	|  
------------- | ------------------------- | ------------- |
GET	| /	| Redirect to /swagger-ui.html
GET	| /api/	| Get block chain info
POST	| /api/?msg={msg}	| create newBlock with msg
POST	| /api/recruit	| create newBlock with generated recruit email


## For demo

 * block chain demo web api test: https://v1.ainize.ai/hibuz/blockchain-java
 * block chain demo web server: https://blockchain-java-hibuz.endpoint.ainize.ai
 * block chain grpc server: hibuz.com:6565
