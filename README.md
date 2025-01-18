# Notes
This Project is Notes Online where you can Perform CRUD Operation on your notes (Create, Read, Update and Delete) also there are User System to Have your Notes Saved on the Database where ONLY YOU can View your Notes!
also added Openapi Functionality in order to use swagger to test the Apis

## Prerequisites
**Java:** The project is built using Java 17. Make sure you have Java version 17 or later installed.

**DataBase:** if you are not sure about it, just install and run PostgreSQL

**Maven:** install latest version of maven dependency management

**IDE (Optional)**: While the project was developed using IntelliJ IDEA, you can use any IDE that supports Java and Maven, or run the project directly from the command line.

## Setup Instructions
clone the project to your local repository or just download the project to your pc and follow the next steps:
### Preparing Database
1- create a new database in PostgreSQL or any database management.

2- download this database: [notes.sql](https://www.mediafire.com/file/p1zxbn9ep2b1pii/notes.sql/file)

3- restore the database in your postgresql (note: if you had erros, go to schemas and delete public before restoring to prevent coflicts)

4- open application.properties in project files and edit the url to match the database name, and also change the username and password to match your credentials.
### preparing the project
1- Open pom.xml file in your ide and let maven resolve and download all dependencies if needed.

2- open application.properties and under spring.mail.. put your gmail, and for password, you need to search how to get app password for your gmail and put it there.

3- Now you are ready to run the project and also make sure that Postgres is running.

