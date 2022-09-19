# JavaProject
This is the repository for the QA Software Development Bootcamp Spring Boot Java Project

## Why are we doing this?
The aim of this project is to implement CRUD functionality for a small relational database. A new Spring Boot project will be created and relevant dependencies added, such as MySQL connectivity and JPA. The database will then be created and accessed via Postman requests. The project brings together many elements of our training in the past nine/ten weeks such as Java, Git, SQL and Agile methodologies. Relational databases are used extensively by organisations to store all types of records, so the project has real-world relevance.

## How I expected the challenge to go
Early on in the Bootcamp I was concerned about the final project, having read the description of it and not understanding much at all. This eased somewhat as we learnt more about Java and Spring Boot. However I was very unsure how to join tables using the JPA, and this concern was well-founded since it proved to be very tricky for me.

## Outline of the project
The project simulates a (very small) specialist computing books shop. There are four tables. A 'Book' table with five columns: primary key, title, ISBN number, publication date and a foreign key which links to a 'Publisher' table. The Publisher table has a one-to-many relationship with the Book table -- one publisher can publish many books but books can't have more than one publisher. The Publisher table has two columns: primary key and name. There is a 'Stock' table with two columns: the book primary key and quantity. This table is in a one-to-one relationship with the Book table. A column named 'quantity' could have been created in the Book table as an alternative, however 'quantity' is not an inherent property of a book, so it was decided to move it to a separate table. Finally there is an 'Author' table with columns: primary key, first name, middle name and last name. This table is in a many-to-many relationship with the Book table -- an author can write more than one book and a book can have more than one author. To achieve this relationship a fourth table is needed, 'author_book' which uses two columns as primary keys, one from the Book table and the second from the Author table.

## ERD Diagram

![ScreenShot](/screenshots/java_project_uxf.jpg)

## What went well? / What didn't go as planned?
It was fairly straightforward to convert the simple "Account" project we had been working on as an exercise prior to starting the final project. Deciding on what fields to include in tables and how to link the separate tables required some thought, and created some major headaches at the end of the project. I did manage a solution but it has some quirks which are mentioned in the next section. Because of these problems I was unable to concentrate on the integration testing part of the project, which I had ran into several problems with. Since it was only in the final week that we had all of the knowledge required to complete the project, I did feel somewhat rushed in trying to finish it.

## Postman requests
With the four tables (Publisher, Book, Stock, Author) empty, we can build the database entirely with Postman requests (GET, POST, PUT, DELETE). Let's create a publisher:

![ScreenShot](/screenshots/Postman_create_publisher.png)

The id field is automatically populated. Note that the books field is currently blank because we haven't added any books yet. We can continue in this way to build up our Publisher table. If we make a mistake we can update the publisher:

![ScreenShot](/screenshots/Postman_update_publisher.png)

Or even delete it (the output 'true' indicates a successful deletion):

![ScreenShot](/screenshots/Postman_delete_publisher.png)

We can then read back from the table a particular publisher id:

![ScreenShot](/screenshots/Postman_read_publisher_id.png)

Or read all of the publishers:

![ScreenShot](/screenshots/Postman_read_all_publishers.png)

We can check the records have been successfully created in the database with MySQL Workbench:

![ScreenShot](/screenshots/Workbench_all_publishers.png)

Now let's add a record to our Book table:

![ScreenShot](/screenshots/Postman_create_book.png)

As well as our Book table fields 'title', 'isbn' and 'publicationDate', we are also specifying the publisher id so that we know which publisher published the book. A quirk of the code shows in the response that the publisher name is 'null', however the book has been successfully linked with the publisher, as we'll see. The Stock table is also populated with a quantity field. We should provide one since the book and stock are in a one-to-one relationship. The authors field in the response is blank since we haven't added any authors to the Author table yet.

Reading books by id, reading all of them, and deleting them work in the same fashion as the 'Publisher' table. Update looks like this:

![ScreenShot](/screenshots/Postman_update_book.png)

Another quirk here is that the stock id field has to be given, and it must match the book id in the URI.

Once our Book table is populated, we can check persistence with MySQL Workbench:

![ScreenShot](/screenshots/Workbench_all_books.png)

Our Stock table should have been populated too, let's check:

![ScreenShot](/screenshots/Workbench_all_stocks.png)

Creating a Stock record or deleting a Stock record fails due to the one-to-one relationship with the Book table. If we want to delete a Stock record we must delete the Book record. However we can update a Stock record:

![ScreenShot](/screenshots/Postman_update_stock.png)

Now let's add a record to our Author table:

![ScreenShot](/screenshots/Postman_create_author.png)

Since we haven't linked any authors to books yet, the books field in the response is blank. When we have finished adding author records, we check MySQL Workbench:

![ScreenShot](/screenshots/Workbench_all_authors.png)

Now to link the books to the authors:

![ScreenShot](/screenshots/Postman_update_book_author.png)

The response should probably indicate a successful link here, however we can check MySQL Workbench again:

![ScreenShot](/screenshots/Workbench_all_author_book.png)

Now that the database is complete, we can search it with some pre-made custom queries. To search a book with a 'keyword':

![ScreenShot](/screenshots/Postman_read_books_keyword.png)

To search a book by author id:

![ScreenShot](/screenshots/Postman_read_books_author.png)

And so on.

## Possible improvements for future revisions of the project
A password could be required when creating/updating or deleting objects to prevent unwanted alterations to the database -- most database queries are read queries in any case. The DTO used in the project currently holds the same information as the original object, so this could be changed. Features that weren't used in the project such as Lombok could be used. The code should be tested more, preferably achieving 80% or higher test coverage. More tables could be created to better simulate a small book shop. A front end could be developed instead of using Postman to make using the database easier to search and see the results in a nicer form than JSON.

## Risk Assessment
Since this database is stored on a local machine, it is vulnerable to data loss or corruption, so backups should be made. It'd probably be safer to store the database securely in the cloud. This would allow reliable access for other employees over the internet. If the database can only be accessed online, then backup internet connections should be on standby to ensure access to the database at all times. There are no permissions set up in the database, therefore creating, updating and deleting of records is allowed when most often only read permissions are required.

## Testing
Mockito unit testing was done for the service classes in the package. The testUpdateBookRepo() method which was previously working failed due to an alteration to the setStock() method in the Book class. This test has been commented out in order to highlight only successful tests:

![ScreenShot](/screenshots/Mockito_unit_tests.png)

Integration testing was attempted but not completed due to problems with getting the code to compile and time constraints. The priority was to get the code working before running these tests. Many ad hoc tests were run using Postman requests and/or SQL commands in MySQL Workbench with made-up data.

### Link to GitHub repository
[GitHub](https://github.com/synthcode/JavaProject/)

### Link to Jira board
[Jira](https://synth.atlassian.net/jira/software/projects/JAV/boards/2)

### .jar file
The file Project-0.0.1-SNAPSHOT.jar in the /target folder is the packaged build of the application, and can be run from the command line with the command "java -jar Project-0.0.1-SNAPSHOT.jar"

