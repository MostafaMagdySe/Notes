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

## QuickStart
1-as the project uses JWt, you need to get a token to start.. this is only possible by logging in.. so, to get started you need to create an account.. to hit the register endpoint go to register endpoint "/register" (eg: http://localhost:8080/register)

2- you can register with username and password only.. you can add phone and email as well or you can add them later it's totally up to you.

3- after registering, hit the login api (eg: http://localhost:8080/login) after providing valid credentials you will get a response containing a token you can use this token to access the other endpoints.

4- now, as you are a new user, you need to create your first note.. you can do that by hitting "/note" endpoint (eg:http://localhost:8080/note) and send the "content" you want.

5- now, you have to options to see your notes, you can view a specific note by hitting the note with its id (eg: http://localhost:8080/note/1) or you can simply view all notes you have made by going to the homepage(eg: http://localhost:8080/)

6- if you don't know what to write in your notes and want some ideas, you can get inspiration by visting an integerated endpoint that will provide you with a random quote for an anime character and it also tells you the anime name and the name of the character if you are intersted in the quote itself.. to get a random quote you can visit "/quote" endpoint, (eg: http://localhost:8080/quote)

7- it's also possible to save your note and continue writing later or even delete the note if you want.. by hitting the same endpoint but you need to change the Http Request type.. instead of post request use "patch" or "delete" for the same endpoint, (eg: http://localhost:8080/note/1) 
