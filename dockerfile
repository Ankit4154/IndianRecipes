FROM openjdk:11
VOLUME /tmp
COPY target/indian-recipe-0.0.1-SNAPSHOT.jar IndianRecipe.jar
ENTRYPOINT ["java","-jar","IndianRecipe.jar"]
