FROM jetty:latest

ADD ./target/api.war /var/lib/jetty/webapps/
ADD ./target/api/WEB-INF/classes/conf/myfoodprint_db_config.json /var/lib/jetty/resources