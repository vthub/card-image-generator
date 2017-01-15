FROM java:8-jdk

RUN mkdir /dist
COPY . /dist
WORKDIR /dist/

EXPOSE 5050:5050

CMD ./gradlew run
