
# Install Java

remove old versions

https://askubuntu.com/questions/84483/how-to-completely-uninstall-java


install new

[sudo apt-get install openjdk-8-jre](https://learn.microsoft.com/en-us/java/openjdk/install#install-on-ubuntu)

sudo apt install maven

#  Create VM, EventHub, and Hub.

* Add User Managed identity to the VM
* Add 'Azure Event Hubs Data Sender' role on the 'Hub' scope to the Managed Identity
* update `App.java` with namespace and hub

# Run

```
mvn package
mvn exec:java -Dexec.mainClass="com.mycompany.app.App"
```



# How the Project was created

```
mvn archetype:generate -DgroupId=com.mycompany.app -DartifactId=java-eh -DarchetypeArtifactId=maven-archetype-quickstart -DarchetypeVersion=1.5 -DinteractiveMode=false
```

#  create dependencies & code

<see pom.xml  and App.java>


#  Build project

```
mvn package -Dmaven.test.skip
```

