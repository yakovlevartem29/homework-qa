# homework test framework

QA automation repository

## Links
 * [Github project](https://github.com/yakovlevartem29/homework-qa)

## Execution HOWTO
* To execute functional tests against heroku app use following command:
 `mvn clean test`
 * It is possible to override config parameters:
 `mvn clean test -DbaseUrl=http://localhost:5000`

## Test Reports
* To generate test report locally use following command:
`mvn alure:serve` <- it will start the reporting server locally and provide a link to html reports. 
VPN may cause some issues, please disable it before starting the reporting server.

