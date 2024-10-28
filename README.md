# random-calendar-test
A test script for random.org's calendar date generator

## Execution
To execute the random calendar tests, you can choose any of the following options.

- Open Intellij IDEA and run the tests
  - Intellij IDEA has the necessary tools for the execution (kotlin compiler, junit, etc)
  - This assumes you have the gecko driver in your path
  - This is the best option if you want to explore the solution in depth, debug or change it
- Run maven (`mvn test`)
  - This assumes you have the gecko driver in your path
  - This option is a middle term
- Run docker (`docker-compose up`)
  - This is the best option if you don't have maven or the gecko driver in your path
  - This option will leave your computer clean

## The Script
This test script is written in 
- Kotlin as programming language, 
- with maven as build tool, 
- junit as test manager, 
- log4j for logging, 
- Selenium webdriver as the main web testing tool, and 
- docker for containerized deploy.
