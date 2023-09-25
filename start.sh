DB_URL="jdbc:mysql://$MYSQL_HOST/$MYSQL_NAME?sessionVariables=sql_mode='NO_ENGINE_SUBSTITUTION'&jdbcCompliantTruncation=false"

echo "database name: $MYSQL_NAME"; 
echo "host: $MYSQL_HOST"; 
echo "user: $MYSQL_USER"; 
echo "password: $MYSQL_PASSWORD"; 

java -Dspring.datasource.url=$DB_URL \
     -Dspring.datasource.username=$MYSQL_USER \
     -Dspring.datasource.password=$MYSQL_PASSWORD \
     -jar /app.jar;