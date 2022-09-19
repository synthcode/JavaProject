# JavaProject
This is the repository for the QA Software Development Bootcamp Spring Boot Java Project

## Why are we doing this?
The aim of the project is to implement CRUD functionality for a small relational database . Spring Boot will be used and the databse will be accessed via Postman. Relational databases are used extensively by organisations to store all types of records.

## How I expected the challenge to go
Early on in the Bootcamp I was concerned about the final project. This eased somewhat as we learnt more about Java and Spring Boot. I was unsure how to join tables and insert records using only Postman, and this proved to be very tricky.

## Outline of the project
The project simulates a (very small) specialist computing books shop. There are four tables. A 'Book' table with five columns: primary key, title, ISBN number, publication date and a foreign key which links to a 'Publisher' table. This table has a one-to-many relationship with the Book table - one publisher can publish many books but books can't have more than one publisher. The Publisher table has two columns: primary key and name. There is a 'Stock' table with two columns: the book primary key and quantity. This table is in a one-to-one relationship with the Book table. A column named 'quantity' could have been created in the Book table instead, however the quantity is not an inherent property of the book. Finally there is an 'Author' table with columns: primary key, first name, middle name and last name. This table is in a many-to-many relationship with the Book table - an author can write more than one book and a book can have more than one author. To acheive this relationship a fourth table is needed - 'author_book' which uses two columns as primary keys, one from the Book table and the second from the Author table.

## ERD Diagram

![ScreenShot](/screenshots/java_project_uxf.jpg)

## What went well? / What didn't go as planned?
It was fairly straightforward to convert the simple "Account" project we had been working on as an exercise prior to starting the final project. Deciding on what fields to include for the books and how to link to separate tables required some thought, and created some major headaches at the end of the project, which were solved in a way but . Because of this I was unable to get integration testing to work in time. It was only in the final week that we had all of the knowledge required to start the project so I felt somewhat rushed to complete it.

## Postman requests
With the four tables (Publisher, Book, Stock, Author) empty, we can build the database entirely with Postman requests (GET, POST, PUT, DELETE). Let's create a publisher:

![ScreenShot](/screenshots/Postman_create_publisher.png)

The id field is automatically populated. Note that the books field is currently blank because we haven't added any books yet. We can continue in this way to build up our Publisher table. If we make a mistake we can update the publisher:

![ScreenShot](/screenshots/Postman_update_publisher.png)

Or even delete it (the output 'true' indicates a successful deletion):

![ScreenShot](/screenshots/Postman_delete_publisher.png)

We can then read back from the table a particular publisher id:

![ScreenShot](/screenshots/Postman_read_publisher_id.png)

Or read all the publishers:

![ScreenShot](/screenshots/Postman_read_publisher_id.png)

We can check the records have been successfully created in the database with MySQL Workbench:

![ScreenShot](/screenshots/Workbench_all_publishers.png)

Now let's add a record to our Book table:

![ScreenShot](/screenshots/Postman_create_book.png)

As well as our Book table fields 'title', 'isbn' and 'publicationDate', we are also specifying the publisher id so that we know which publisher published the book. A quirk of the code shows in the response that the publisher name is 'null', however the book has been successfully linked with the publisher, as we'll see. The Stock table is also populated with a quantity field. We should provide one since the book and stock are in a one-to-one relationship

Reading books by id, reading all of them, and deleting them work in the same way as the 'Publisher' table. Update looks like this:

![ScreenShot](/screenshots/Postman_update_book.png)

Another quirk here is that the stock id field has to be given, and it must match the book id in the URI.

Once our Book table is populated, we can check persistence with MySQL Workbench:

![ScreenShot](/screenshots/Workbench_all_books.png)

Our Stock table should have been populated too, let's check:

![ScreenShot](/screenshots/Workbench_all_stocks.png)

Creating a Stock record or deleting a Stock record will fail due to the one-to-one relationship with the Book table. If we want to delete a Stock record we must remove the Book record. However we can update a Stock record:

![ScreenShot](/screenshots/Postman_update_stock.png)

Now let's add a record to our Author table:

![ScreenShot](/screenshots/Postman_create_author.png)

Since we haven't linked authors to books yet, the books field is blank.
When we have finished adding records, we check MySQL Workbench:

![ScreenShot](/screenshots/Workbench_all_authors.png)

Now to link the books to the authors:

![ScreenShot](/screenshots/Postman_update_book_author.png)

Afterwards we can check MySQL Workbench:

![ScreenShot](/screenshots/Workbench_all_author_book.png)

Now the database is complete, we can search it with some pre-made custom queries. To search a book with a 'keyword':

![ScreenShot](/screenshots/Postman_read_books_keyword.png)

To search a book by author id:

![ScreenShot](/screenshots/Postman_read_book_author.png)

More screenshots are available in the 'screenshots' directory.


## Possible improvements for future revisions of the project
A password could be added when creating/updating or deleting objects to prevent unwanted alterations to the database. At the moment the DTO holds the same information as the original object, so this could be changed. Features that weren't used in the project such as Lombok could be used and the code should be tested more to achieve 80% or higher test coverage. More tables could be created to better simulate a small book shop. A front end could be developed instead of using Postman to make using the database easier to search and see the results in a nicer form than JSON.

## Risk Assessment
Since this database is stored on a local machine, it is vulnerable to data loss or corruption. Backups should be made and it should be stored securely in the cloud for access to other employers of the bookstore. There are no permissions set up in the database, therfore creating, updating and deleting of records is allowed when only read permissions are wanted. If the data is stored online, backup connections to the internet should be on hand to ensure access to the database at all times.

## Testing
Mockito unit testing was done for the service classes in the package. There was a failure of testUpdateBookRepo() due to an alteration to the setStock() method in the Book class. This test has been commented out in order to highlight the successful tests:

![ScreenShot](/screenshots/Mockito_unit_tests.png)

Integration testing was attempted but not completed due to problems with the code and time constraints. The priority was to get the code running before. Many ad hoc tests were run with dummy data in the columns.


### Link to Jira board
[Jira](https://synth.atlassian.net/jira/software/projects/JAV/boards/2)

### Link to GitHub repository
[GitHub](https://github.com/synthcode/JavaProject/)

### .jar file
The file Project-0.0.1-SNAPSHOT.jar in the /target folder is the packaged build of the application, and can be run from the command line with the command "java -jar Project-0.0.1-SNAPSHOT.jar"

### Feature branch
The GitHub repository has several branches, "main", "feature", "feature2", ..."". Commits were pushed regularly to feature branches which were frequently merged into the main branch.

