# [Lab 8](https://github.com/hanggrian/IIT-ITM515/blob/assets/assignments/hw8.pdf): Web Application Security

Instead of continuing directly from the previous lab, this assignment jumps back
to the first lab to implement security features.

- Password hashing with [Pbkdf2PasswordHash](https://javaee.github.io/javaee-spec/javadocs/javax/security/enterprise/identitystore/Pbkdf2PasswordHash.html) that is
  injected to the `IdentityStore` implementation.
- Syncs the `IdentityStore` with the existing MySQL database schema.
- Ability to create a new account and log in with the new credentials.

## Building

Similar to the first lab, the project is built with Gradle and deployed with
Cargo plugin.

```sh
# initial build
./gradlew cargoRunLocal

# subsequent builds
./gradlew cargoRedeployLocal
```

## Diagram

<img width="480" src="https://github.com/hanggrian/IIT-ITM515/raw/assets/assignments/hw8/diagram1.svg"><br><small>Diagram 1 &mdash; *itmd4515* UML diagram</small>

The schema contains only the schemas relevant to the security features. They are
`users`, `roles` and their conjunction table `user_roles`. This is to indicate
that a user may have multiple roles and a role may be assigned to multiple
users.

## Screenshots

### Home page

<img width="640" src="https://github.com/hanggrian/IIT-ITM515/raw/assets/assignments/hw8/screenshot1.png"><br><small>Screenshot 1 &mdash; The `index.xhtml` page</small>

Upon visiting the home page, the user is greeted with default login credentials
to test the application. User may login with any of the listed accounts or
create a new one by clicking the **Create Account** button.

### Create account

<img width="320" src="https://github.com/hanggrian/IIT-ITM515/raw/assets/assignments/hw8/screenshot2_1.png">
<img width="320" src="https://github.com/hanggrian/IIT-ITM515/raw/assets/assignments/hw8/screenshot2_2.png"><br><small>Screenshot 2.1 & 2.2 &mdash; Sign-up input form and alert message</small>

Values entered in the form are validated using JavaScript, which controls the
button's state to prevent submission of invalid format. The servlet then checks
whether the user ID is already taken as a safeguarding measure. When an account
is successfully created, the user is redirected to the home page to log in.

### Log in

<img width="320" src="https://github.com/hanggrian/IIT-ITM515/raw/assets/assignments/hw8/screenshot3_1.png">
<img width="320" src="https://github.com/hanggrian/IIT-ITM515/raw/assets/assignments/hw8/screenshot3_2.png"><br><small>Screenshot 3.1 & 3.2 &mdash; Native sign-in dialog and redirect</small>

Password submitted in the dialog is hashed using the default algorithm provided
by Jakarta Web Security API. In an event of a matching combination, the browser
stores the session and prints a welcome page referring to the user associated
with the session.

### Error routing

<img width="640" src="https://github.com/hanggrian/IIT-ITM515/raw/assets/assignments/hw8/screenshot4.png"><br><small>Screenshot 4 &mdash; Error 404</small>

Using `web.xml` descriptor, the application is configured to route errors to a
custom error page.
