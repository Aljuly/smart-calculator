FROM tomcat:9.0.12
LABEL key aljuly
ADD smart-calculator.war /usr/local/tomcat/webapps/
ADD tomcat-users.xml /usr/local/tomcat/conf/
ADD jaas.config /usr/local/tomcat/conf/
EXPOSE 8080
CMD chmod +x /usr/local/tomcat/bin/catalina.sh
CMD ["catalina.sh", "run"]