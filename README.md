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

Using a timer within the system program, we can measure the amount of time it takes the client to process the entire query (i.e. the latency of the system).

### Samples

```
Type 1: Pattern that does not occur
[aruna2@fa22-cs425-6501 grep-client]$ java -cp "target/grepmp-1.0-SNAPSHOT.jar:target/dependency/*" com.grepmp.app.App
Enter GREP Command: 
grep -c 123.163.215.36
fa22-cs425-6505.cs.illinois.edu::3002 : 0
fa22-cs425-6504.cs.illinois.edu::3002 : 0
fa22-cs425-6508.cs.illinois.edu::3002 : 0
fa22-cs425-6501.cs.illinois.edu::3002 : 0
FILE MATCHES: 4
TIME ELAPSED: 1052

Type 2: Highly frequent patterns
[aruna2@fa22-cs425-6501 grep-client]$ java -cp "target/grepmp-1.0-SNAPSHOT.jar:target/dependency/*" com.grepmp.app.App
Enter GREP Command: 
grep -c GET
fa22-cs425-6504.cs.illinois.edu::3002 : 162590
fa22-cs425-6505.cs.illinois.edu::3002 : 162714
fa22-cs425-6501.cs.illinois.edu::3002 : 170372
fa22-cs425-6508.cs.illinois.edu::3002 : 164776
FILE MATCHES: 4
TIME ELAPSED: 965

Type 3: Infrequent pattern
[aruna2@fa22-cs425-6501 grep-client]$ java -cp "target/grepmp-1.0-SNAPSHOT.jar:target/dependency/*" com.grepmp.app.App
Enter GREP Command: 
grep -c http://thompson-roberts.com/
fa22-cs425-6505.cs.illinois.edu::3002 : 1
fa22-cs425-6508.cs.illinois.edu::3002 : 0
fa22-cs425-6504.cs.illinois.edu::3002 : 0
fa22-cs425-6501.cs.illinois.edu::3002 : 1
FILE MATCHES: 4
TIME ELAPSED: 1046

```


## Testing

The test cases exercise the distributed grep system.

### Steps

1. Execute the setup.sh script once for each machine in the VM cluster. The script copies the necessary server code, starts up the server process and also handles copying the log files. Note: you will need to change the parameters (like folder path, netid etc.) as required.

2. Modify the contents of the networkConfig.properties file as needed

3. Run `mvn package`