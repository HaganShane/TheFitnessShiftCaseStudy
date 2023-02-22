# The Fitness Shift
The Fitness Shift is an application designed for anyone who would like to take control of their health and wellness. Good health will always be a very crucial part to any life style. The Fitness Shift promotes workout tracking, meal tracking, water intake, and sleep tracking. It is a great way to stay organized and on top of your healthy lifestyle!

## Installing
Download the .zip file, extract the directory, and import it to the IDE of your choice - it is recommended to use Eclipse or IntelliJ.

After it is imported, the only thing you need to change is the information located in the application.properties file, under /src/main/resources. The following information will need to be modified to fit your system information for MySQL Workbench:
```
spring.datasource.url = URL
spring.datasource.username = Username
spring.datasource.password = Password
```

## Database Configuration
The database schema is as follows:

![Schema](https://github.com/HaganShane/TheFitnessShiftCaseStudy/blob/master/schema.png)

## How to run and use the application
Run the entire project as a Spring Boot Application by right clicking on the project, then run as Spring Boot App.

You can also run the class containing the main method - this is located under the com.shanehagan.fitnessshift package /src/main/java. You can also right click this class and chose run as Spring Boot App.

After the application is running, you are able to open a browser and navigate to "localhost:8080" to land on the home page. From there, you are able to sign up and login to get started using it.

Once logged in, you can navigate to different modules to add new entries, see past entries, edit past entries, or delete entries!

## Built Using
* Java
* Spring Boot
* MySQL
* Maven
* Thymeleaf
* HTML
* CSS
* JavaScript

## License
This project is licensed under the MIT License - see the LICENSE.md file for details
