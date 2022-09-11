# grep-client

Contains logic for the client portion of the distributed grep.

## Building the project

Execute the following steps in sequence for a new, clean build
```
$ mvn clean
$ mvn install
$ mvn dependency:copy-dependencies
$ mvn package
```

And finally to run the Jar, execute
```
$ java -cp "target/grepmp-1.0-SNAPSHOT.jar:target/dependency/*" com.grepmp.app.App
```

## Performance metrics

Using a timer within the system program, we can measure the amount of time it takes the client to process the entire query.

### Samples

```
Type 1: Counts of frequent patterns
> grep -c GET
TIME ELAPSED: 1492

> grep -c PUT
TIME ELAPSED: 1413

Type 2: Counts of infrequent patterns
> grep -c 123.163.215.36
TIME ELAPSED: 1249

Type 3: Strings of somewhat frequent patterns
> grep Opera
TIME ELAPSED: 10593

> grep -v Mozilla
TIME ELAPSED: 14637

Type 4: Strings of infrequent patterns
> grep -n api
TIME ELAPSED: 1491

Type 5: Strings of infrequent pattern
> grep 123.163.215.36
TIME ELAPSED: 1285

Type 6: Strings of very frequent patterns
> grep HTTP/1.0
TIME ELAPSED: 37077

Type 7: Strings of somewhat infrequent patterns
> grep http://thompson-roberts.com/
TIME ELAPSED: 2406
Number of matches = 4
```


## Testing

The test cases exercise the distributed grep system.

### Steps

1. Execute the setup.sh script once for each machine in the VM cluster. The script copies the necessary server code, starts up the server process and also handles copying the log files. Note: you will need to change the parameters (like folder path, netid etc.) as required.

2. Modify the contents of the networkConfig.properties file as needed

3. Run `mvn package`