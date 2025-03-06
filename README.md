
# Install Java

[sudo apt-get install openjdk-8-jre](https://learn.microsoft.com/en-us/java/openjdk/install#install-on-ubuntu)

sudo apt install maven


# Install Project

```
mvn archetype:generate -DgroupId=com.mycompany.app -DartifactId=java-eh -DarchetypeArtifactId=maven-archetype-quickstart -DarchetypeVersion=1.5 -DinteractiveMode=false
```

#  create dependencies & code

<see pom.xml  and App.java>


#  Build project

```
mvn package -Dmaven.test.skip
```

#  Create EventHub, and Hub.

Add 'Azure Event Hubs Data Sender'role on the 'Hub' scope to the Managed Identity

# Run

```
mvn exec:java -Dexec.mainClass="com.mycompany.app.App"
```
