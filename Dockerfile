FROM openjdk:21-slim

ENV APPLICATION="spring-data-jpa-criteria-example"
ENV APPLICATION_HOME="/application/${APPLICATION}"

RUN mkdir -p ${APPLICATION_HOME}
COPY ./target/${APPLICATION}.jar ${APPLICATION_HOME}

CMD java -jar ${JAVA_OPTS} ${APPLICATION_HOME}/${APPLICATION}.jar