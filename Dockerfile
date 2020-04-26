FROM jetty:latest

ADD ./target/api.war /var/lib/jetty/webapps/