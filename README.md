LogProcessor
============

An access log processor which produces a CSV file of key performance metrics for URIs.
It's reasonably quick - parsing 1M lines in ~7 seconds.



##Usage

Populate config.properties with the path to your logfile:

    logPath = /path/to/tomcat_access_sample.log
    csvFile = /path/to/yourreport.csv


##Compile Instructions

Requires:
* Google Guava >=15.0
* JDK8

JDK target is set to 1.8, as using Streams.
Under OSX at least need to provide path to JDK8 as a result.
e.g

    JAVA_HOME=$(/usr/libexec/java_home -version 1.8) mvn clean test

Compile with:

    JAVA_HOME=$(/usr/libexec/java_home -version 1.8) mvn compile



##TODO
* Redo logReader.read Method as currently it reads whole log into memory, which would make very large log files a proble
    * Possibly using the new Java8 java.util.function.Consumer stuff?
* allow override of paths using a run time argument.
    * e.g. -DlogPath="/var/log/tomcat/tomcat_access.log" -DcsvPath="/var/tmp/logStats.csv"
* enable LogParser to work with any typical access log format -perhaps to understand the grammar of an access log pattern
    * and thus make this portable to different access log formats.
    * e.g.

        pattern='"%{True-Client-IP}i" "%{X-Forwarded-For}i" %h %t "%r" %s %b %D "%{User-Agent}i" "%{SOAPAction}i" "%{Host}i" "%{Accept-Encoding}i" %p

* Add more Stats functions
    * produce histograms of response Code etc.
