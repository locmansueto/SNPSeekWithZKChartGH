FROM ubuntu:latest

RUN apt-get -y update && apt-get -y upgrade
RUN apt-get -y install openjdk-8-jdk wget
#RUN apt-get -y install git
RUN apt-get install -y maven

RUN mkdir /usr/local/tomcat
RUN wget http://www-us.apache.org/dist/tomcat/tomcat-8/v8.5.35/bin/apache-tomcat-8.5.35.tar.gz -O /tmp/tomcat.tar.gz
RUN cd /tmp && tar xvfz tomcat.tar.gz
RUN cp -Rv /tmp/apache-tomcat-8.5.35/* /usr/local/tomcat/

ENV JAVA_TOOL_OPTIONS -Dfile.encoding=UTF-8

ENV MAVEN_OPTS -Dfile.encoding=UTF-8

#RUN git clone https://dagsbarboza@bitbucket.org/irridev/iric_portal.git
COPY . /iric_portal


WORKDIR /iric_portal


#RUN git checkout SnpSeekMavenProj

RUN mvn clean install


ENV JAVA_HOME /usr/lib/jvm/java-8-openjdk-amd64
ENV CATALINA_HOME /usr/local/tomcat
ENV PATH $PATH:$CATALINA_HOME/bin

 

#RUN mkdir /usr/local/tomcat/webapps/iric_portal/
#RUN mkdir /usr/local/tomcat/webapps/iric_portal/WEB-INF

#RUN cp /iric_portal/iric-portal_10162018.war /usr/local/tomcat/webapps/iric_portal.war
#RUN cp -r /iric_portal/resources /usr/local/tomcat/webapps/iric-portal/WEB-INF/classes/
#RUN cp -r /iric_portal/target/classes /usr/local/tomcat/webapps/iric-portal/WEB-INF/classes/

RUN mv /iric_portal/target/iric-portal-0.0.1-SNAPSHOT.war $CATALINA_HOME/webapps/iric_portal.war

RUN mkdir /iric_portal_files

#COPY iric_portal_files /iric_portal

CMD ["catalina.sh", "run"]




EXPOSE 8080