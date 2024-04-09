# Pluralsight - Concurrent Programming in Java with Virtual Threads

VM Options to run examples
https://stackoverflow.com/a/53647605

```
# Has to be added when compiling (javac) and when running (java) the code :
--add-exports=java.base/jdk.internal.vm=ALL-UNNAMED
```

To compile/run examples :

* Project Structure > Project > SDK : 22
* Project Structure > Project > Language level : SDK default
* Maven compile plugin configuration :

```xml
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-compiler-plugin</artifactId>
    <configuration>
        <source>${maven.compiler.source}</source>
        <target>${maven.compiler.target}</target>
        <compilerArgs>
            <arg>--add-exports=java.base/jdk.internal.vm=ALL-UNNAMED</arg>
        </compilerArgs>
    </configuration>
</plugin>
```

For example C add VM Option : --add-exports=java.base/jdk.internal.vm=ALL-UNNAMED to run configuration