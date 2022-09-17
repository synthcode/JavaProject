# JavaProject
This is the repository for the QA Software Development Bootcamp Spring Boot Java Project

## Why are we doing this?
This project implements CRUD functionality for a small relational database of computing books. Most organisations use relational databases such as SQL to store various types of records

## How I expected the challenge to go
Early on in the Bootcamp I was quite worried about the final project. This eased somewhat as we learnt more about Java and Spring Boot, however I was still concerned how to implement joins and integration testing into the project. Some features I have left out such as Lombok.

## Outline of the project
The project simulates a specialist computing books shop with three tables: a book table with columns "title", "isbn" and "publication_date"; an authors table with columns "first_name", "middle_name" and "last_name"; and a stock table with column "quantity". The need to separate the authors table from the books table is because there can be multiple authors for a book, and it is considered incorrect practice to use multiple columns for the authors in the books table. The stock table is a property of the shop and not the book.

## What went well? / What didn't go as planned?
It was fairly straightforward to convert the simple "Account" project we had been working on as an exercise prior to starting the final project. Instead of an accounts a books database was chosen. Deciding on what fields to include for the books and whether to link to separate tables required some thought. I found it difficult to work out how to test parts of the code using Mockito and integration testing. I had difficulty understanding the JPA and how to join tables together... I found myself a bit rushed in the final week since it was only then that we had all of the knowledge required to start the project.

## Possible improvements for future revisions of the project
A password could be added when creating/updating/deleting objects to prevent unwanted alterations to the database by others. At the moment the DTO holds the same information as the original object. Features that weren't used such as Lombok or stretch goals such as 80% test coverage. More tables could be created to better simulate a small book shop. A front end could be developed instead of using Postman to make using the database easier. The books relation to the authors is through a Many-to-One mapping, which works for this small collection of books since no author is an author of more than book. However for a larger database a Many-to-Many mapping will be needed, which will require an additional "BooksAuthors" table.

## Risk Assessment
(Security, lack of internet connection in a team, someone ill in the team with deadline to meet, power cuts, )

## Screenshots showing postman requests and the output from the API
To do

![ScreenShot](/screenshots/Test_Book.png)

## Screenshots of database to prove that data is being persisted
To do

## Screenshot of test results, including coverage report
To do. (coverage report might be missing)


### Link to Jira board
[Jira](https://synth.atlassian.net/jira/software/projects/JAV/boards/2)

### Link to GitHub repository
[GitHub](https://github.com/synthcode/JavaProject/)

### .jar file
The file Project-0.0.1-SNAPSHOT.jar in the /target folder is the packaged build of the application, and can be run from the command line with the command "java -jar Project-0.0.1-SNAPSHOT.jar"

### Feature branch
The GitHub repository has several branches, "main", "feature", "feature2", ..."". Commits were pushed regularly to feature branches which were frequently merged into the main branch.

