### ChaseUK - Exercise 
## Author - Shamili Srinivasan 
## version 1.0 

## Java test automation for backend API

## framework

TestNG - Testing framework.

## Scenarios 

BDD
  - Cucumber-JVM - A pure Java implementation of Cucumber that supports the most popular programming languages for the JVM.
  
## API test automation

REST-Assured - A library for testing and validation of REST services in Java.

## Build Tools

Apache Maven - Apache Maven is a software project management and comprehension tool. Based on the concept of a project object model (POM), Maven can manage a project's build, reporting and documentation from a central piece of information.

## Continuous Integration
 GitHub Actions 

## Reporting
 Extend cucumber-reporting
     - This is a Java report publisher primarily created to publish cucumber reports on the Jenkins build server. It publishes pretty html reports with charts showing the results of cucumber runs. It has been split out into a standalone package so it can be used for Jenkins and maven command line as well as any other packaging that might be useful. Generated report has no dependency so can be viewed offline.

## Editors, IDE, consoles

IntelliJ IDEA - A free and open-source IDE for Java, Groovy, Scala and Android development.

## Test Data
 JSON Format

## Scenarios 

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

