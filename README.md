# Automation Practice WebApp - Selenium Framework

## Features
- Verifies e-commerce book purchase workflow
- Parallel test execution in Chrome and Firefox
- Scalable, maintainable framework (Page Object Model)
- Detailed reporting and screenshots
- Optional Dockerized test execution

## Setup Steps
1. Clone the repo
2. Install dependencies: `mvn clean install` or `mvn test`
3. (Optional) Start Docker Grid: `docker-compose up -d`

## Run Tests
- Standard: `mvn test -DsuiteXmlFile=testng.xml`
- Docker: Configure RemoteWebDriver and run using the Grid

## Reports
- Available in `allure-report` 
