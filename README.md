LogProcessor
============

An access log processor which produces a CSV file of key performance metrics for URIs.
It's reasonably quick - parsing 1M lines in ~7 seconds.



##Usage

Populate config.properties with the path to your logfile:

    logPath = tomcat_access_sample.log
    csvFile = report.csv


##Compile Instructions

Requires:
* Google Guava >=15.0
* JDK8

JDK target is set to 1.8, as using Streams.
Under OSX at least need to provide path to JDK8 as a result.
e.g

    JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk1.8.0_05.jdk/Contents/Home/ mvn clean test

Compile with:

    JAVA_HOME=//Library/Java/JavaVirtualMachines/jdk1.8.0_05.jdk/Contents/Home/ mvn compile



##TODO
* Redo ReadLine Method as currently it reads whole log into memory, which would make very large log files a proble
    * Possibly using the new Java8 java.util.function.Consumer stuff?
* allow override of paths using a run time argument.
    * e.g. -DlogPath="/var/log/tomcat/tomcat_access.log" -DcsvPath="/var/tmp/logStats.csv"
* Add more logStats functions
    * e.g. stdDeviation.
    * produce histograms of response Code etc.
