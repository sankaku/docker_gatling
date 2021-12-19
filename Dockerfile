FROM adoptopenjdk:8-jre-hotspot
ADD https://repo1.maven.org/maven2/io/gatling/highcharts/gatling-charts-highcharts-bundle/3.7.2/gatling-charts-highcharts-bundle-3.7.2-bundle.zip /tmp/gatling-charts-highcharts-bundle-3.7.2-bundle.zip
RUN apt-get update && apt-get install -y unzip

RUN unzip /tmp/gatling-charts-highcharts-bundle-3.7.2-bundle.zip -d /tmp
RUN mkdir /gatling
RUN mv /tmp/gatling-charts-highcharts-bundle-3.7.2/* /gatling/
RUN rm -rf /tmp/gatling-charts-highcharts-bundle-3.7.2*

WORKDIR /gatling
ENV GATLING_HOME /gatling
ENTRYPOINT ["./bin/gatling.sh"]
