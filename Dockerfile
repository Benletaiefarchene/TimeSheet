FROM java:8
EXPOSE 8091
COPY target/Timesheet-spring-boot-core-data-jpa-mvc-REST-1-2.0.war app.war
ENTRYPOINT ["java","-jar","app.war"]
