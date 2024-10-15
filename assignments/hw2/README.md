# [Lab 2](https://github.com/hanggrian/IIT-ITM515/blob/assets/assignments/hw2.pdf): Junit, JDBC and Bean Validation

This project performs CRUD operations on [Sakila Sample Database](https://dev.mysql.com/doc/sakila/en/),
it is a database about a DVD rental store.

- JavaFX plugin with bindings and observable collections.
- [Hibernate plugin](https://hibernate.org/orm/documentation/) as ORM using
  Jakarta EE annotations.
- MySQL database and JDBC connection.
- Few Google technologies: [Guava Core Libraries](https://github.com/google/guava),
  [Truth Testing Utilities](https://github.com/google/truth) and images obtained
  from [Material Icons](https://fonts.google.com/icons).

## Discussion

> What database did you select, and which table are you going to represent as a
  Java POJO? What fields did you select to map from table to Java class?

Out of several sample databases provided by MySQL, I choose Sakila for its
simplicity. Sakila is also extremely easy to import with one SQL script as
schema definition and another as data. MySQL other sample databases,
**airportdb**, for example, requires importing dump files.

The fields selected by this application are only the ones that are relevant to
the rental management aspect of the database. The UML diagram below shows the
the full list of tables and columns.

> Add the following dependencies to your *pom.xml* using a scope you feel is
  appropriate:
>
> 1.  **junit** (latest non-beta version of junit-jupiter-engine 5)
> 1.  **mysql-connector-java** (latest non-beta version of 8)
>
> What scope(s) did you select and why?

1.  The JUnit and other test dependencies are imported using
    `testImplementation` notation because the test classes are not intended for
    the production build.
1.  The MySQL connector is imported using [non-transitive](https://docs.gradle.org/current/userguide/java_library_plugin.html#sec:java_library_separation)
    `implementation` notation.
1.  Additionaly, the `compileOnly` notation is used for Jakarta EE annotations.
    They are just annotations and not needed in the runtime.

> Discuss any other issues or insights you had with Lab 2.

I had issues in the beginning with the Hibernate configuration. Although the
[Getting Started](https://docs.jboss.org/hibernate/orm/6.6/quickstart/html_single/)
guide is helpful, there are multiple ways to configure the database connection
that are not immediately clear (*hibernate.properties*, *hibernate.cfg.xml* and
*persistence.xml*). Moreover, most examples of Hibernate implementation online
are tightly coupled with [Spring framework](https://spring.io/).

> **Graduate students** &ndash; test your Java SE project on the command line
  using maven. Document your experience (with code block output) on your README,
  and discuss how command line Java relates to Maven (hint - think classpath and
  dependencies). What would you need to do (step by step) in order to run your
  project without maven, using only the Java SE provided java and javac
  binaries? In what ways does Maven help us?

I am using Gradle instead of Maven because the build script it more readable.
However, I believe the concept is similar in that both tools manage the
classpath and dependencies.

<img width="480" src="https://github.com/hanggrian/IIT-ITM515/raw/assets/assignments/hw2/screenshot1.png"><br><small>Screenshot 1.1 &mdash; Command-line test</small>

Without such dependency management tools, I would need to manually produce the
the `.class`  files and use the `junit-platform-console-standalone-$VERSION.jar`
to run the tests. I also need to manually include the `.jar` libraries in the
test command.

```sh
java -jar junit-platform-console-standalone-$VERSION.jar \
  -cp build/classes/java/main:build/classes/java/test:$LIBRARIES \
  --scan-class-path
```

## Building

The project cannot be executed using **Run &#8679;R** functionality in IntelliJ
IDEA. I suspect it is due to the JavaFX version 11 and later that requires a
modular project structure and run configuration.

```sh
java --module-path $PATH_TO_FX \
  --add-modules javafx.controls,javafx.fxml \
  -jar build/libs/uid-lab2-1.0-SNAPSHOT.jar
```

Instead, the project uses Application plugin that recognizes the JavaFX
classpath.

```sh
./gradlew run
```

> **Note**
>
> MySQL server must be running and the Sakila database must exist. Otherwise,
  the application will throw an exception.

## Diagrams

<img width="640" src="https://github.com/hanggrian/IIT-ITM515/raw/assets/assignments/hw2/diagram1.svg"><br><small>Diagram 1 &mdash; Sakila UML diagram</small>

## Screenshots

### Interface

<img width="480" src="https://github.com/hanggrian/IIT-ITM515/raw/assets/assignments/hw2/screenshot2_1.png"><br><small>Screenshot 2.1 &mdash; Main interface</small>

<img width="640" src="https://github.com/hanggrian/IIT-ITM515/raw/assets/assignments/hw2/screenshot2_2.png"><br><small>Screenshot 2.2 &mdash; Expanded state</small>

### CRUD Operations

<img width="480" src="https://github.com/hanggrian/IIT-ITM515/raw/assets/assignments/hw2/screenshot3_1.png"><br><small>Screenshot 3.1 &mdash; READ rental</small>

Rental data is filtered either by ID or dates associated with the rental. This
decision can be configured using controls enabled by radio buttons in the
toolbar.

<img width="480" src="https://github.com/hanggrian/IIT-ITM515/raw/assets/assignments/hw2/screenshot3_2.png"><br><small>Screenshot 3.2 &mdash; CREATE payment</small>

Option to create a new payment is available when selecting an item in the rental
table, then by clicking context menu or menu bar.

<img width="320" src="https://github.com/hanggrian/IIT-ITM515/raw/assets/assignments/hw2/screenshot3_3.png"><br><small>Screenshot 3.3 &mdash; UPDATE film</small>

Since the film table is too large, the search field is provided to filter the
film table.

<img width="320" src="https://github.com/hanggrian/IIT-ITM515/raw/assets/assignments/hw2/screenshot3_4.png"><br><small>Screenshot 3.4 &mdash; DELETE payment</small>

Confirm the deletion of a payment with an alert dialog.
