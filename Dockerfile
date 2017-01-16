FROM java:8-jdk

RUN apt-get update && apt-get install fonts-freefont-ttf

RUN mkdir /dist
COPY . /dist
WORKDIR /dist/

EXPOSE 5050:5050

CMD ./gradlew run
