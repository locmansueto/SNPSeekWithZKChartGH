FROM tomcat:8.0.52-jre7

ADD iric_portal_files /iric_portal_files

COPY /WebRoot /usr/local/tomcat/webapps/iric-portal/
COPY /resources /usr/local/tomcat/webapps/iric-portal/WEB-INF/classes/
COPY /target/classes /usr/local/tomcat/webapps/iric-portal/WEB-INF/classes/

CMD ["catalina.sh", "run"]

EXPOSE 8080