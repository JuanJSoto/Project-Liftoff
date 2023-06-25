web: java -Dserver.port=$PORT $JAVA_OPTS -jar build/libs/project-liftoff-0.0.1-SNAPSHOT.jar
web: node --inspect=9090 index.js
web: java -agentlib:jdwp=transport=dt_socket,server=y,address=9090,suspend=n -jar target/myapp.jar