logProcessor
============

An Access Log Processor which produces a CSV file of key performance metrics for URIs.



##Usage

Populate config.properties with the path to your logfile:

    logPath = /Users/anmulholland/work/github/warlus/tomcat_access_2014-05-10.log
    csvPath = /Users/anmulholland/work/github/warlus/output

##TODO
* Redo ReadLine Method as currently it reads whole log into memory, which would make very large log files a proble
    * Possibly using the new Java8 java.util.function.Consumer stuff?
* allow override of paths using a run time argument.
    * e.g. -DlogPath="/var/log/tomcat/tomcat_access.log" -DcsvPath="/var/tmp/logStats.csv"
* Add more logStats functions
    * e.g. stdDeviation.
    * produce histograms of response Code etc.
