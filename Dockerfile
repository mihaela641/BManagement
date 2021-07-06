# we are extending everything from tomcat:8.0 image ...
FROM tomcat:9.0
MAINTAINER vladimir
# COPY path-to-your-application-war path-to-webapps-in-docker-tomcat
ADD business-management-ws/target/business-management-ws-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/business-management.war
EXPOSE 8080
CMD ["catalina.sh", "run"]
