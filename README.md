# chaseUK
The framework is intended to test the number of posts using a GET request.

The feature files in **src/test/resources/backend_test.feature** provide 6 scenarios for the project.

**Installation of Java and Maven is required.**

Following the completion of the execution, a report and log will be created in the folder listed below.
**Report: test-output**

Some of the framework's key features include:
It creates an Extent report with all of the step data. The report will be created in HTML format.
Generates execution logs, with detailed request and response details.
Validate the response body with a json schema.
The **testng.xml** file should be used to trigger test execution.

Command to perform a test:
**- git clone project - import as maven project in the appropriate IDE
- cd to your home directory - mvn clean test -DsuiteXmlFile=testng.xml**

A test report is generated as shown below.
 **/chaseUK/test-output/emailable-report.html**