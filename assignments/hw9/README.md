# [Lab 9](https://github.com/hanggrian/IIT-ITM515/blob/assets/assignments/hw9.pdf): JSF Data Tables

A direct improvement from the previous lab regarding JSF components and
security, this project makes use of `@Named` beans to control XHTML content.

- Restrict access to specific webpages as configured in `web.xml`.
- Render unordered list and data tables using JSF components and values provided
  by the backing beans.
- Store HTML code in base64-encoded strings to avoid misinterpretation by the
  browser. It also obfuscates the original value like password hashing from the
  previous lab.

## Building

The project is built with Gradle and deployed with Cargo plugin. When
encountering an error during deployment, append `--info` to the Gradle command
to see the temporary directory where `server.log` is written.

```sh
# initial build
./gradlew cargoRunLocal

# subsequent builds
./gradlew cargoRedeployLocal
```

## Diagram

<img width="480" src="https://github.com/hanggrian/IIT-ITM515/raw/assets/assignments/hw9/diagram1.svg"><br><small>Diagram 1 &mdash; *itmd4515* UML diagram</small>

Entity **user** has been restrucutred into **student** to reflect the purpose of
this web application, which lists students enrolled in a course. There is a new
entity **department** to represent academic institutions of the university.

## Screenshots

### Index page

<img width="640" src="https://github.com/hanggrian/IIT-ITM515/raw/assets/assignments/hw9/screenshot1.png"><br><small>Screenshot 1 &mdash; The `index.xhtml` page</small>

While previously the default login credentials are hardcoded in the servlet,
this time the startup service generates random student data with the help of
[Java Faker library](https://github.com/DiUS/java-faker). The library works by
declaring sets of common names and creating permutations of them to simulate
realistic data. User may log in with any of the listed accounts or create a new
one by filling an account creation form.

### Home page

<img width="320" src="https://github.com/hanggrian/IIT-ITM515/raw/assets/assignments/hw9/screenshot2_1.png">
<img width="320" src="https://github.com/hanggrian/IIT-ITM515/raw/assets/assignments/hw9/screenshot2_2.png"><br><small>Screenshot 2.1 & 2.2 &mdash; Sign-up dialog and student table</small>

The student table is rendered using JSF data components in `home.xhtml`.

```xml
<h:dataTable value="#{homeController.students}" var="student">
  <h:column>
    <f:facet name="header">Name</f:facet>
    #{student.fullName}
  </h:column>
  <h:column>
    <f:facet name="header">Email</f:facet>
    #{student.email}
  </h:column>
  <h:column>
    <f:facet name="header">Department</f:facet>
    <h:link outcome="department" value="#{student.departmentName}">
      <f:param name="name" value="#{student.departmentName}"/>
    </h:link>
  </h:column>
</h:dataTable>
```

The attributes are then routed to `HomeController` to populate the table.

```java
@Named("homeController")
@SessionScoped
public class HomeController implements Serializable {
  @EJB Students students;

  public List<Student> getStudents() {
    return students.selectAllExceptAdmin();
  }
}
```

### Department detail

<img width="640" src="https://github.com/hanggrian/IIT-ITM515/raw/assets/assignments/hw9/screenshot3.png"><br><small>Screenshot 3 &mdash; The `department.xhtml` page</small>

The department cell in the student table is a hyperlink that forwards the user
to the department detailed view. Note that the header with **Logout** button is
visible since this is one of the restricted pages.

### Error routing

<img width="320" src="https://github.com/hanggrian/IIT-ITM515/raw/assets/assignments/hw9/screenshot4_1.png">
<img width="320" src="https://github.com/hanggrian/IIT-ITM515/raw/assets/assignments/hw9/screenshot4_2.png"><br><small>Screenshot 4.1 & 4.2 &mdash; Error pages</small>

Utilizing facelets templating, the error pages share the same layout as other
error pages. Jakarta EE will automatically route the client to this page when
a corresponding error code is thrown.
