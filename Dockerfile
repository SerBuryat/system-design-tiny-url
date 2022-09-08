FROM openjdk:17-jdk-alpine
ENV WD /usr/local/tiny-url
ENV JAVA_OPTS "-Xms128m -Xmx1024m"
WORKDIR $WD
COPY ./build/libs $WD
EXPOSE 8080
ENTRYPOINT java -jar $JAVA_OPTS \
                     -Dspring.datasource.url=$DB_URL \
                     -Dspring.datasource.username=$DB_USERNAME \
                     -Dspring.datasource.password=$DB_PASSWORD \
                     -Dspring.jpa.hibernate.ddl-auto=$DB_DDL_AUTO \
                     -Dapi.host=$API_HOST \
                     tiny-url.jar