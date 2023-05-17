# five-hand-poker
This is a spring boot project with Gradle.

# Building project
Gradle is required to build the project.
Or just run in intelliJ or savage on with any other IDE

### Option 1

> run command
>
> **./gradlew bootRun**
>
> This command will build and start up the spring boot application

### Option 2

> run command
>
>**gradle build**
>
> Once build folder is created,
>
> run 'java -jar build/libs/five-hand-poker-0.0.1-SNAPSHOT.jar'


# Running the project

On start up and random shuffle happens. hand and rank are displayed on the logs

## keys.
> * C - clubs (♣)
> * D - diamonds (♦)
> * H - hearts (♥)
> * S - spades (♠)
> * T - 10

### Running it on Swagger
> http://localhost:8080/swagger-ui.html#/

> Once the project is up and running, one can access swagger through.
The controller contains two APIs.
One API that doesn't take any params. It randomly generates a hand and provide the generated hand, along with the rank.
Second API, requires a hand, which follows a specific format. For testing, data in test cases can be used to test
different
kinds of hands and ranks


### Running it on the Browser or postman
> Once project is up and running, should be able to shuffle and get the rank with this endpoint on either browser or post
man
> http://localhost:8080/v1/five-hand-shuffle

> retrieving rank by passing a valid hand
> with Letters:
> http://localhost:8080/v1/five-hand-shuffle/2H%20JH%20AH%20JH%20QH

> with actual symbols
> http://localhost:8080/v1/five-hand-shuffle/2%E2%99%A5%20J%E2%99%A5%20A%E2%99%A5%20J%E2%99%A5%20Q%E2%99%A5