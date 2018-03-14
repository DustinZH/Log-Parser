# Log parser
The log parser project is provided by wallethub. The goal is to write a parser in Java that parses web server access log file, loads the log to MySQL and checks if a given IP makes more than a certain number of requests for the given duration.

## Structure of Log Parser
- .idea
- out
- src
  - main
    - java
      - com.ef.common
      - com.ef.db
        - mysql (include mysql setting file: MySQLDBUtil.class)
      - com.ef.model
      - com.ef.serivce
    - resources (include MySQL schema/query file)
  - test (JUnit 4)
- target

## Java 
### Argument
The arguments are startTime, duration, threshold, accesslog(option). The format of startTime is : yyyy-MM-dd.HH:mm:ss. Duration only can be "DAILY" or "HOURLY". And the threshold is number. for example:
```sh
java -cp "parser.jar" com.ef.Parser --startDate=2017-01-01.13:00:00 --duration=hourly --threshold=100
```
The tool will find any IPs that made more than 100 requests starting from 2017-01-01.13:00:00 to 2017-01-01.14:00:00 (one hour) and print them to console.
you also can run the tool with 
```sh
java -cp "parser.jar" com.ef.Parser --startDate=2017-01-01.00:00:00 --duration=daily --threshold=500
```
The tool will save file into db and search the request

## Database
I only use MySQL to do the project, But I also leave an interface(DBConnection.java) to support Mutiple db. Polymophism is most wonderful thing in java.
As I mentioned, the MySQL schema is in the resources directory. Before Run tool, you must set up MySQL.

database name: wallethub
username: root
password: root
