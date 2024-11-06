# [Lab 6](https://github.com/hanggrian/IIT-ITM515/blob/assets/assignments/hw6.pdf): EJB Service Layer

This project contains the same presentation layer as **Lab 3** with the addition
of a service layer using Enterprise JavaBeans (EJB).

- **JTA** transactions with automatic EJB injection in the main servlet.
- Manually inject **RESOURCE_LOCAL**-based `EntityManager` to DAO classes in the
  unit tests.
- A startup singleton EJB to partially populate the database, just enough for
  the unit tests to pass.

## Building

Because there is a startup service initializing sample data, the *Sakila*
database schema must be imported but data can be empty. Then, execute the
following Gradle command to deploy the Jakarta EE web application:

```sh
# initial build
./gradlew cargoRunLocal

# subsequent builds
./gradlew cargoRedeployLocal
```

Tests are handled by Jupiter and printed using Gradle logging abilities.

<img width="480" src="https://github.com/hanggrian/IIT-ITM515/raw/assets/assignments/hw6/screenshot1.png"><br><small>Screenshot 1 &mdash; Command-line test</small>

## Diagram

<img width="480" src="https://github.com/hanggrian/IIT-ITM515/raw/assets/assignments/hw6/diagram1.svg"><br><small>Diagram 1 &mdash; Sakila UML diagram</small>

`FilmText` is not related to existing entities `Film` and `Language`. However,
there is a trigger in the `sakila-schema.sql` that creates a copy of `FilmText`
whenever a new `Film` is inserted. These entries need to be removed as part of
the CRUD operations.

## Screenshots

### Input validation

<img width="320" src="https://github.com/hanggrian/IIT-ITM515/raw/assets/assignments/hw3/screenshot2_1.png">
<img width="320" src="https://github.com/hanggrian/IIT-ITM515/raw/assets/assignments/hw3/screenshot2_2.png"><br><small>Screenshot 2.1 & 2.2 &mdash; Input validation in action</small>

There is no major difference in the user interface and experience. Similar to
*Lab 3*, the button state is disabled until all fields and checks are proper.

### Form output

<img width="320" src="https://github.com/hanggrian/IIT-ITM515/raw/assets/assignments/hw3/screenshot3_1.png">
<img width="320" src="https://github.com/hanggrian/IIT-ITM515/raw/assets/assignments/hw3/screenshot3_2.png"><br><small>Screenshot 3.1 & 3.2 &mdash; Form output message</small>

Previously without EJB service layers, the `EntityManager` was created by the
`EntityManagerFactory` in the utility class and passed on to the DAO classes.
In contrast, the `EntityManager` is now injected by the DAO classes using the
`@PersistenceContext` annotation. Then, the DAO classes are further injected
into the servlet using the `@EJB` annotation.

```java
@Stateless
public class Languages {
    @PersistenceContext(unitName = "sakila") public EntityManager manager;

    // ...
}
```

```java
@WebServlet(name = "submit", value = {"/submit"})
public class SubmitServlet extends HttpServlet {
    @EJB Films films;
    @EJB Languages languages;

    // ...
}
```
