# What is fluenttql.catalog?
<a href="https://fluenttql.github.io/fluenttql.catalog/" target="_blank">fluenttql.catalog</a> 
is a micro-benchmark project for the fluentTQL Domain-Specific Language.
This project contains example of the taint style vulnerabilities and
the respective fluentTQL specifications. 

This project contains below three packages. 
1. docs: This package contains the javadoc for all the security vulnerabilities
implemented in this project.
2. specification: This package contains the fluentTQL specification for 
all the security vulnerabilities implemented in this project.
3. src: This package contains the java source code of the security
vulnerabilities.

# How to run fluenttql.catalog project?
Step 1: Clone the fluenttql.catalog git repository.
```.shell script
git clone https://github.com/fluenttql/fluenttql.catalog.git
```

Step 2: Run the test cases to see the behaviour of the security vulnerabilities.
<br>If you want to run the test cases for all the vulnerabilities implemented in the 
fluenttql.catalog project then run the below command.
```.shell script
mvn test
```
If you want to run the test case for the particular vulnerability then run the below
command.
```.shell script
mvn -Dtest=<Test case class name>

#example to run the test case for the Command-Injection:
mvn -Dtest=CommandInjectionTest test
```