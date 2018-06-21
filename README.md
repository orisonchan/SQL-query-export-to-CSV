# Export SQL Query to CSV

This tools uses SpringBoot and needs JDK1.7.

You can compile and package using Maven command:

> mvn package -DskipTests

When you wanna start it, please use java -jar with this two parameters:

+ -i input SQL file absolute path

+ -o CSV output directory

Run command example:

> java -jar sql-exporter-1.0-SNAPSHOT.jar -o /home/orison/ -i /home/orison/sql.sql

Supports Oracle、MySQL、SQL Server、DB2. You should modify ***application.properties*** file to adapt different data sources.

Input file can support more than 1 sql but different sqls will be divided by lines.

File Example:

~~~sql
select * from tableA;
SELECT 
ID
FROM tableB;
~~~

And the file must exists, otherwise, tools will throw *FileNotFoundException*.

Output CSV file(s) will overwrite itself(ves).