A simple page object model based web application test automation framework using Selenium with java.
This is a simple end to end project starting right from Eclipse to Jenkins. 
Right click and run individual tests as TestNG Test from eclipse or execute only specific test from testng.xml file
The entire test suite can be executed from testng.xml file. 
For ease of use I used chrome driver. 
POM is a design pattern for creating object repository for web elements. 
Page factory is a built in class in selenium for maintaining object repository. 

PAGE OBJECT MODEL

WHAT IS POM?

POM is a design pattern which is very popular in test automation for enhancing test maintenance and reducing code duplication. 
Every UI element (page or fragment) of the application has a separate implementation of the methods. 
This allows us to write a reusable code that is easy to modify when necessary.
POM design pattern is to define and initialize WebElements and also to create respective methods to perform certain actions using defined web elements. 

WHY POM?

Allows us to write readable code that new team members can understand and write code faster.
With the right approach, it is possible to make an automation testing available to non-technical team members. 
It is best for application which has multiple pages. 

PURPOSE OF PAGE FACTORY: 

Page Factory is an optimized way to create an object repository in POM concept. 
It is a way of implementing the page object model.
It is a built-in class in selenium for maintaining object repository. 

ADVANTAGES: 

Makes the code more readable, maintainable and reusable.
Maintain object repository
Easier maintenance of the locators
If the UI changes, the tests don’t need to be changed. Only the code within the page object needs to be changed. 
Test cases are short and optimized as we can reuse the page object methods in the POM classes. 
Keeping separate repository for page objects helps us to use this repository for different purposes with different frameworks like 
integration with Junit, testNG, cucumber etc. 
Best applicable to web applications that contain multiple pages. 

DISADVANTAGES:  

Difficult to maintain if there are lots of pages
Need to be technically strong to implement constructors, oops concepts
The structure is not generic, it is application specific

TOOLS AND TECHNOLOGIES USED: 

1. API for automation: Selenium WebDriver 
2. Build Automation tool: Maven
3. Framework used: Test Driven Development using TestNG.
4. Programming language: Java
5. Page Object Model design pattern with POF for creating object repository. 
6. API to generate logs: Log4j
7. Report generation: Extent Reports API
8. Github
9. CI tool: Jenkins
10. Sample application used for automation is: https://www.etsy.com

VERSIONS INFO: 

1. Chrome browser - 79.0.3945.36
2. Eclipse IDE - Version: 2020-03 (4.15.0)
3. Selenium: 3.141.59
4. TestNG version - 7.0.0
5. Java version - 1.8.0_60
6. Operating System - Windows 10
7. Jenkins version - 2.204.2
8. GIT version - 2.25.0.windows.1
9. Extent reports - 2.41.2
10. Log4j - 2.12.1


* All the common properties like url, username, password that are used across the application are stored in src/main/java/com/etsy/Configuration/Config.properties
* Drivers information is stored under a separate folder called drivers at the project level. 
* All the reusable methods are included within src/main/java/com/etsy/Utilities/TestUtil.java.
* Object repository, initialization of page objects and methods that interact with the objects are placed in src/main/java/com/etsy/Pages package. 
* Test base class which is the super class for all the test pages, test classes, utilities is included in src/main/java/com/etsy/Base package
* Base class includes web driver initialization, all the common properties, launch the browser, maximize window, page timeouts, launching the url, wait times, delete cookies etc 
* Resources like log4j and extent-config.xml files are created under under src/main/Resources package. 
* All the test cases are included in the src/main/java/com/etsy/Tests package
* Logger implementation and extent reports implementation are done in the test base class.
* Logs are saved in Logs folder at the project level. 
* Extent reports are generated with the screenshots attached to the report. Extent report is saved as .html in the ExtentReports folder 
at the project level. 
* Screenshots are saved in Screenshots folder at the project level. 
* All dependencies are included in the pom.xml file
* An simple email is sent when a test case fails. 
* An email with attachment and screenshot is sent at the end of all the test case execution. 


**** NOTE: JUST TO SEE HOW THE LOGS ARE CREATED OR TO CHECK HOW THE MESSAGES ARE PRINTED USING DIFFERENT OUTPUT STYLES, WHEREVER I AM PRINTING THE MESSAGES, I HAVE USED 3 MODES OF PRINTING THE OUTPUT. 
1. USING SYSOUT --> THE MESSAGES ARE PRINTED TO THE CONSOLE
2. USING LOG.INFO --> THE LOGS ARE CREATED IN A SPECIFIED LOG FILE (IN LOG4J2.XML FILE --> THE OUTPUT CONSOLE IS SPECIFIED AS FILE, HENCE THE LOGS ARE CREATED IN THE SPECIFIED FILE. 
3. USING TEST.LOG(LOGSTATUS.INFO....) --> THE LOGS OR THE MESSAGES ARE CREATED IN THE EXTENT REPORT.




