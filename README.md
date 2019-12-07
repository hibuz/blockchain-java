# BlockChain Demo [![Run on Ainize](https://ainize.ai/static/images/run_on_ainize_button.svg)](https://ainize.web.app/redirect?git_repo=github.com/hibuz/blockchain-java)
This is a Block Chain Demo Application

## Development

To start your application in the dev profile, simply run:

    ./gradlew


## Building for production

To optimize the blockchain demo application for production, run:

    ./gradlew -Pprod :chain-core:clean :chain-core:build

To ensure everything worked, run:

    java -jar chain-core/build/libs/*.jar


## Testing

To launch your application's tests, run:

    ./gradlew :chain-core:test


## Using Docker to simplify development (optional)

You can also fully dockerize your application and all the services that it depends on.
To achieve this, first build a docker image of your app by running:

    ./gradlew :demo-web:jar
    cd demo-web
    docker build -t hibuz/blockchain-java .

Then run:

    docker run --rm -p 80:80 hibuz/blockchain-java


## Rest APIs


Method	| Path	| Description	|  
------------- | ------------------------- | ------------- |
GET	| /	| Redirect to /swagger-ui.html
GET	| /api/	| Get block chain info
POST	| /api/?msg={msg}	| create newBlock with msg
POST	| /api/recruit	| create newBlock with generated recruit email


## For demo

 * block chain demo web server#1: https://hibuz.com
 * block chain demo web server#2: http://grpc.hibuz.com:8080
 * block chain grpc server: grpc.hibuz.com:6565
