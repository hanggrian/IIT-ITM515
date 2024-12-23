# [Final project](https://github.com/hanggrian/IIT-ITM515/blob/assets/assignments/proj.pdf): Comprehensive Multi-layered Enterprise Application

The selected topic for the final project is a student board website that lists
all students enrolled in a course. The website also holds additional information
regarding the university's department. Data presented on the website is stored
in a MySQL database and is accessed through Java Transaction API (JTA).

The website should incorporate every material learned throughout the semester,
including the object-relational mapped persistence layer, dependency-injected
service layer and password-hashed security layer. Logged-in users are allowed to
make modifications to the database, completing the requirements for CRUD
operations.

## Design

Users are greeted with a **welcome page** when they first visit the website.
To gain further access, they must log in or visit **signup page** to create a
new account. For testing purposes, use one of the students randomly generated by
the startup service. After successful login validation, users are redirected to
the **home page** where enrolled students are organized in a data table. From
this table, users can navigate to the **department page** to view details of
their faculty or **edit page** provided that the user is an admin.

The project utilizes several extra features:

- **EJB timer service:** During deployment, a timed action is set to trigger
  every minute to check if every student has a role. It is to accommodate a
  scenario where all roles are removed from a student either by accident or
  intentionally.
- **Bean validation:** Using Jakarta Bean Validation API, each column of an
  entity is measured against a set of constraints. This step ensures that
  objects participating in a transaction are valid.
- **PrimeFaces components:** PrimeFaces extends the standard JSF components with
  additional features like AJAX support and comprehensive themes. Notably, it
  has a built-in confirmation dialog to verify sensitive operations.

## Requirements

### Building

The project conforms to the Gradle build tool specification and is deployed
using [the Cargo plugin](https://github.com/bmuschko/gradle-cargo-plugin/).
Cargo creates a sub-directory in the system's temporary directory to store the
WAR file generated by [the War plugin](https://docs.gradle.org/current/userguide/war_plugin.html).
To show the absolute path of the deployment directory, add `--info` option to
the command.

```sh
./gradlew cargoRunLocal
```

Upon deployment, the application will attempt to connect to the MySQL database
and populate the tables with sample data as part of the startup process.

Property | Value
--- | ---
Database | `jdbc:mysql://localhost:3306/itmd4515`
Username | itmd4515
Password | itmd4515

When changes are made to the source code, the application needs to be redeployed
with a different task.

> Executing `cargoRunLocal` while the application is running will result in an
  error.

```sh
./gradlew cargoRedeployLocal
```

Stop the server by executing the following command, or by killing the Gradle
process.

```sh
./gradlew cargoStopLocal
```

### Diagram

<img width="480" src="https://github.com/hanggrian/IIT-ITM515/raw/assets/assignments/proj/diagram1.svg"><br><small>Diagram 1 &mdash; *itmd4515* UML</small>

Users are represented by the **Student** entity with personal identification and
login credentials. It also holds the **Department** column that the student is
registered, which is optional to indicate that recently created students may
not have a department yet. Some actions are limited to users with the highest
trust level determined by the **Role** entity. However, a student may be
classified as an admin and regular user, a teaching assistant is a good example
of this circumstance. To support this many-to-many relationship, the junction
table **StudentRole** is created.

<img width="640" src="https://github.com/hanggrian/IIT-ITM515/raw/assets/assignments/proj/diagram2.svg"><br><small>Diagram 2 &mdash; Workflow</small>

Most of the application's interface is restricted to logged-in users, as
illustrated by **the session group in** the workflow diagram above. New users
must fill out the form on the sign-up page before authenticating their
credentials back on the welcome page. To log out of the current session, click
the exit button on the header section to redirect the user to the welcome page.

## Screen captures

<img width="640" src="https://github.com/hanggrian/IIT-ITM515/raw/assets/assignments/proj/screenshot1.png"><br><small>Screenshot 1 &mdash; Welcome page</small>

The welcome page, or the index page, is the initial point of entry for the
website displaying the usernames of existing students. Each time the startup
service is initialized, it checks if the database is populated with sample data.
If the database is empty, the service will insert a several students randomly
generated by the [Faker](https://github.com/DiUS/java-faker/) library.

<img width="320" src="https://github.com/hanggrian/IIT-ITM515/raw/assets/assignments/proj/screenshot2_1.png">
<img width="320" src="https://github.com/hanggrian/IIT-ITM515/raw/assets/assignments/proj/screenshot2_2.png"><br><small>Screenshot 2.1 & 2.2 &mdash; Create account page</small>

The form on the sign-up page reflects the Student entity's SQL table columns
with few columns with default values excluded. Utilizing **plain JavaScript**
scripts in the resources directory, the submit button's disability is tuned to
adapt to the form's input based on the column's constraints. For example, a
username is all lowercase and an email address must contain a domain name.

<img width="320" src="https://github.com/hanggrian/IIT-ITM515/raw/assets/assignments/proj/screenshot3_1.png">
<img width="320" src="https://github.com/hanggrian/IIT-ITM515/raw/assets/assignments/proj/screenshot3_2.png"><br><small>Screenshot 3.1 & 3.2 &mdash; Home page</small>

Passwords stored in the database are obfuscated using the default hashing
algorithm provided by the Jakarta Platform artifact. When users log in, the
submitted password is hashed and compared to the stored value. The Identity
Store API will then save these credentials for the current session, or until the
user logs out.

<img width="480" src="https://github.com/hanggrian/IIT-ITM515/raw/assets/assignments/proj/screenshot4.png"><br><small>Screenshot 4 &mdash; Department page</small>

While the student's information is produced with arbitrary values, the
department sample data are static and based on real-world departments at the
*Illinois Institute of Technology*. The department page has a data table with
standard JSF notations instead of PrimeFaces components.

<img width="640" src="https://github.com/hanggrian/IIT-ITM515/raw/assets/assignments/proj/screenshot5.png"><br><small>Screenshot 5 &mdash; Documentation page</small>

The `javadoc` task in the [Java plugin](https://docs.gradle.org/current/userguide/java_plugin.html)
renders the Javadoc comments from the source code into HTML files. Because the
`war` task is derived from the [Copy task](https://docs.gradle.org/current/dsl/org.gradle.api.tasks.Copy.html),
we can include the generated Javadoc files in the WAR file by pointing the
directions in the Gradle DSL. This page is accessible by clicking the relevant
header button on any page of the session group.

```gradle
tasks.named<War>("war") {
    webAppDirectory = layout.projectDirectory.dir("src/main/webapp")
    dependsOn(tasks.javadoc)
    from(layout.buildDirectory.dir("docs/javadoc")) {
        into("docs")
    }
}
```

<img width="320" src="https://github.com/hanggrian/IIT-ITM515/raw/assets/assignments/proj/screenshot6_1.png">
<img width="320" src="https://github.com/hanggrian/IIT-ITM515/raw/assets/assignments/proj/screenshot6_2.png"><br><small>Screenshot 6.1 & 6.2 &mdash; Edit student page</small>

The edit page layout mirrors that of the sign-up page with an additional
drop-down list control to modify the student's department. However, while the
sign-up form is validated by JavaScript, the edit form has a modern approach
with **AJAX binding** in the server-side Java code. In my observation, this
approach feels more responsive whereas the JavaScript validation waits for the
control to lose focus.

<img width="320" src="https://github.com/hanggrian/IIT-ITM515/raw/assets/assignments/proj/screenshot7_1.png">
<img width="320" src="https://github.com/hanggrian/IIT-ITM515/raw/assets/assignments/proj/screenshot7_2.png"><br><small>Screenshot 7.1 & 7.2 &mdash; Delete confirmation dialog</small>

Since web browsers' native alerts are not customizable, the project requires an
extended implementation for common dialog boxes like confirmation and text input
dialog. The PrimeFaces library fits this purpose with widgets like
`p:confirmDialog` and its included stylesheets. Upon record deletion, a
temporary alert message appears at the corner of the screen and the data table
is updated without refreshing the page.

## Expected results/Known issues

The project is expected to build when instructed and pass all the unit tests,
this is usually done with the `build` task that has the `test` task as a
dependency. At the core of the project functionality, the user can perform CRUD
operations and records are persisted in the database according to the
specifications. The website should run on any latest web browser but is only
tested on *Google Chrome* and *Apple Safari*.

There are several known issues that I am aware of:

- **Invalidated records:** Although the ORM beans are validated using the Bean
  Validation API, more sophisticated constraint restrictions like regex pattern
  matching are performed on the client-side. These restrictions are not enforced
  if the user directly modifies the database records with RESTful services
  instead of the website's interface.
- **Limited documentation:** Several external artifacts integral to the project
  are poorly documented or less popular, making it difficult to find examples or
  support online. For instance, Jakarta EE is still overshadowed by the 
  [Spring Framework](https://spring.io/projects/spring-framework). But perhaps
  the most challenging part is integrating PrimeFaces considering the large
  scale of the library and different configurations for each platform (Jakarta,
  Angular, etc.).
- **Missing features:** I acknowledge some features are still missing and
  unlikely to be incorporated given the time limitation, namely the search
  function to filter data tables.

## Development insights

I learned many aspects of Java throughout the semester that I did not previously
explore, such as web Servlets, Faces user interface and EJB-based persistence
transactions. Most importantly, I now have a better understanding of how Context
and Dependency Injection (CDI) works as demonstrated by the latter assignment
entries. Dependency injection allows for a modular code base that is easier to
test. This pattern is only getting more popular with official support in many
popular frameworks like Android and Spring.

Despite being heavily in Java, it is still a website project with an emphasis on
web technologies. Here is where, in my opinion, the features start to overlap.
For example, scripting languages like JavaScript are used to manipulate the DOM,
but it can be achieved in server-side Java with AJAX Facelets. Database
transactions are performed using mapped Java objects with JPA, but JTA's
approach is a standard for Java EE and is a generally better choice for managing
transactions. However, overlapping features are not necessarily a bad thing.
Having more options gives developers the flexibility in deciding their preferred
tools.
