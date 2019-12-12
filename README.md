
# Automation Assignment ExpenseTracker (Selenium-WebDriver + Java)

## Project Structure

```
pom.xml
testng.xml
html-config.xml - (For Extent report configurations)

src
└── ExtentReports (It will contain folder with reports for every execution cycle)

src
  └──test
        └──java
               └──com
                     └──Mobiquity
                  	       └── base
                                ├── BaseClass.java : Base class with driver configuration
    
                  	       └── constants
                                ├── LoggingMessageContants.java : Class to keep defined Constant messages 

                  	       └── model
                                ├── User.java : User Model that stores user properties. 
       
                  	       └── pages
                                ├── BasePage.java : Class common functions needed to perform action on pages.
                                ├── CreateUserPage.java : Class for Create user form WebElements and functions
                                ├── EditUserPage.java : Class for EditUser form WebElements and functions
                                ├── HomePage.java : Class for Home page WebElements and functions
                                ├── LoginPage.java : Class for Login page WebElements and functions
 
                  	       └── testcases
                  	          ├── CreateUserTestCase.java : Class common functions needed to perform action on pages.
                                ├── DeleteUserTestCase.java : Class for Create user form WebElements and functions
                                ├── EditUserTestCase.java : Class for EditUser form WebElements and functions
                                ├── LogInTestCase.java : Class for Home page WebElements and functions
                                ├── LogOutTestCase.java : Class for Login page WebElements and functions
                                ├── ViewUserTestCase.java : Class for CheckOut TestCases 
                                             
                  	       └── util
                                ├── AssertionUtil.java : Class for Assertion code. 
                                ├── ExtentReportLog.java : Class for Extent Report Logging.
                                ├── ScreenshotUtil.java : Class for Screenshot functionality.
                                ├── PropertiesReader.java : Class for reading data from file.
                                ├── Users.java :  Class to create get user details.
                                ├── UtililtyFunctions.java :  Class for generating random data at run time.
                                                                       
src
  └──test
        └──resources
               ├── usernamepassword.properties : properties file to define existing user details.
    

```

## Libraries:

- testng 6.14.3
- selenium webDriver 3.141.59
- webdrivermanager 3.7.1
- extentreports 4.0.9


## Requirements

- Chrome browser to be installed (Other browser should be installed if you want to run test on other browser e.g. Firefox)
- Maven 3.0 installed

## Framework
 - Maven build tool, maintaining all the library definition in POM.xml 
 - Page Object Model design pattern for maintaining the separate class for each feature
 - Page Factory Model for defining and locating web elements
 - Extent Report for effective reporting


## Java
 -java version "1.8.0_112"
 -Java(TM) SE Runtime Environment (build 1.8.0_112-b16)
 -Java HotSpot(TM) 64-Bit Server VM (build 25.112-b16, mixed mode)
 -Java 8 is used as "Selenium webDriver : 3.141.59" requires java 8.
 
## Start the tests

Open terminal, browse to root directory of project, run the test suite:

mvn test
    
## Test Report Location

Please find test report under below path 

/src/ExtentReports/{currentTestExecutionfolder}/

Note: {currentTestExecutionfolder} is created for every new test with current date and time stamp.

## Comments in code 

Entire code styling is influenced by Clean Code principle - Robert Martin
Which says
'Truth can only be found in one place: the code’.
So you may not find any comments anywhere in the project.
Keeping in mind that Git can be used to versioning of file and method, class names should be kept as self explanatory.

However, if you need comments on each file. I can do that too.

## Design principles used in Project :

- DRY(Don’t repeat yourself)
- KISS(Keep it simple, stupid)
- POM (Page Object Model)

## Thoughts behind building framework.

- Used Page Object Model(POM) framework,So Its easy to maintain page Objects (i.e. Modify,Delete,Add). eg: if there are changes on any Login page, So we don't need to update all test cases we just need to update Login page.
- Used property reader to read user credentials from file, Its easy to access wherever required and easy to maintain(i.e. Modify,Delete,Add)
- Used Extent report for good HTML format report with image (wherever required), graphs, filters and dashboard.(Easy to share with others and stake holders)
- Created Util package where all common classes and function added.

