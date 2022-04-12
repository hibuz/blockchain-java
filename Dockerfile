FROM openjdk:11-jdk-slim as build
WORKDIR /workspace/app
COPY . /workspace/app
RUN ./gradlew :demo-web:clean :demo-web:bootJar
RUN mkdir -p build/dependency && (cd build/dependency; jar -xf ../../demo-web/build/libs/*.jar)


FROM openjdk:11-jre-slim
LABEL org.opencontainers.image.source https://github.com/hibuz/blockchain-java
LABEL org.opencontainers.image.authors "hibuz@hibuz.com"

VOLUME /tmp
ENV spring.profiles.active prod
ARG DEPENDENCY=/workspace/app/build/dependency
COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app
EXPOSE 8080
ENTRYPOINT ["java","-cp","app:app/lib/*","com.hibuz.blockchain.DemoApplication"]
