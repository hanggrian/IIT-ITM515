# [Lab 10](https://github.com/hanggrian/IIT-ITM515/blob/assets/assignments/hw10.pdf): Composite Components and Action Links from Data Tables

Further improvement of Lab 9 with composite components and action links.

- Delete user with the PrimeFaces confirmation dialog.
- Edit user in a view-scoped bean with AJAX input validation.
- Standard and JSF-based form tags.

## Building

The project is deployed with Cargo Gradle plugin, temporary deployment directory
is printed with `--info` options.

```sh
# initial build
./gradlew cargoRunLocal --info

# subsequent builds
./gradlew cargoRedeployLocal --info
```

## Diagram

<img width="480" src="https://github.com/hanggrian/IIT-ITM515/raw/assets/assignments/hw9/diagram1.svg"><br><small>Diagram 1 &mdash; *itmd4515* UML diagram</small>

No changes are made to the SQL schema.

## Screenshots

### Authentication

<img width="320" src="https://github.com/hanggrian/IIT-ITM515/raw/assets/assignments/hw10/screenshot1_1.png">
<img width="320" src="https://github.com/hanggrian/IIT-ITM515/raw/assets/assignments/hw10/screenshot1_2.png"><br><small>Screenshot 1.1 & 1.2 &mdash; User role login</small>

The user may log in using one of the randomly generated student accounts,
powered by the [Java Faker library](https://github.com/DiUS/java-faker). In the
home page, most JSF components have been rewritten in PrimeFaces specification
and stylesheets, resulting in a more modern look out-of-the-box.

```xml
<p:dataTable
  id="studentTable"
  value="#{homeController.students}"
  var="student">

  <p:column>
    <f:facet name="header">Student</f:facet>
    #{student.fullName}<br/>#{student.email}
  </p:column>

  <!-- other columns -->
</p:dataTable>
```

<img width="320" src="https://github.com/hanggrian/IIT-ITM515/raw/assets/assignments/hw10/screenshot1_3.png"><br><small>Screenshot 1.3 &mdash; Denied access</small>

In this instance, the user is denied access when clicking the **Edit** button
since the edit page is restricted to users with the **admin** role. This
restriction is established by `security-constraint` configuration in `web.xml`.

```xml
<security-constraint>
  <web-resource-collection>
    <web-resource-name>Admin access</web-resource-name>
    <url-pattern>/edit</url-pattern>
    <!-- other pages -->
  </web-resource-collection>
  <auth-constraint>
    <role-name>admin</role-name>
  </auth-constraint>
</security-constraint>
```

### Editing

<img width="320" src="https://github.com/hanggrian/IIT-ITM515/raw/assets/assignments/hw10/screenshot2_1.png">
<img width="320" src="https://github.com/hanggrian/IIT-ITM515/raw/assets/assignments/hw10/screenshot2_2.png"><br><small>Screenshot 2.1 & 2.2 &mdash; Edit student</small>

While previously the input validation is performed on JavaScript files, the
AJAX facet of PrimeFaces allows for Java-based binding. In this example, the
last name and department dropdown menu are changed to new values. If one of the
password fields is filled, both fields must match or the button will be
disabled.

### Deleting

<img width="320" src="https://github.com/hanggrian/IIT-ITM515/raw/assets/assignments/hw10/screenshot3_1.png">
<img width="320" src="https://github.com/hanggrian/IIT-ITM515/raw/assets/assignments/hw10/screenshot3_2.png"><br><small>Screenshot 3.1 & 3.2 &mdash; Delete student</small>

When removing a student record, the user is prompted with a PrimeFaces
confirmation dialog with pre-loaded animation and icons. Confirming this action
will result in removal from the database and immediate update on the table.
