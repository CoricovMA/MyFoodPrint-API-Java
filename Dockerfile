FROM jetty:latest

ADD ./target/api.war /var/lib/jetty/webapps/
ADD ./target/api/WEB-INF/classes/food_json/all_ingredients.json /var/lib/jetty/resources