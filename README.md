LogProcessor
============

An access log processor which produces a CSV file of key performance metrics for URIs.
It's reasonably quick - parsing 1M lines in ~7 seconds.
Now reworked to use the java.util.function.Consumer interface, so can deal with very large files - as it no longer
reads the whole file into memory.



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
* allow override of paths using a run time argument.
    * e.g. -DlogPath="/var/log/tomcat/tomcat_access.log" -DcsvPath="/var/tmp/logStats.csv"
    * this could be with system.getProperty("logPath)" or similar
* enable LogParser to work with any typical access log format -perhaps to understand the grammar of an access log pattern
    * and thus make this portable to different access log formats.
    * e.g.

        pattern='"%{True-Client-IP}i" "%{X-Forwarded-For}i" %h %t "%r" %s %b %D "%{User-Agent}i" "%{SOAPAction}i" "%{Host}i" "%{Accept-Encoding}i" %p
    * possibly use antlr or similar? maybe that's overkill

* Add more Stats functions
    * produce histograms of response Code etc.
