# [Lab 7](https://github.com/hanggrian/IIT-ITM515/blob/assets/assignments/hw7.pdf): JSF

Building upon **Lab 3** regarding Sakila database and **Lab 6** which introduced
the EJB service layer. While previously the presentation is handled using JSP,
this project replaces it with *JavaServer Faces (JSF).*

- XHTML files with facelets templating.
- Redirect errors to a custom page based on the error code.
- New annotations from `jakarta.faces.annotation` package.

## Building

The web application runs in a GlassFish container deployed using the Cargo
plugin.

```sh
# initial build
./gradlew cargoRunLocal

# subsequent builds
./gradlew cargoRedeployLocal
```

## Diagram

<img width="480" src="https://github.com/hanggrian/IIT-ITM515/raw/assets/assignments/hw6/diagram1.svg"><br><small>Diagram 1 &mdash; Sakila UML diagram</small>

## Screenshots

### Interface

<img width="320" src="https://github.com/hanggrian/IIT-ITM515/raw/assets/assignments/hw3/screenshot2_1.png">
<img width="320" src="https://github.com/hanggrian/IIT-ITM515/raw/assets/assignments/hw3/screenshot3_1.png"><br><small>Screenshot 2.1 & 2.2 &mdash; Form input and output</small>

Where previously JSP was used to render the layout, the project now uses JSF
in XHTML file format. JSF provides several namespaces for declaring components,
enforcing input validation and facelets templating.

```xml
<!-- include.xhtml -->
<ui:composition xmlns:ui="http://xmlns.jcp.org/jsf/facelets">
  <!-- ... -->
</ui:composition>
```

```xml
<!-- main.xhtml -->
<html
  xmlns="http://www.w3.org/1999/xhtml"
  xmlns:h="jakarta.faces.html"
  xmlns:ui="http://xmlns.jcp.org/jsf/facelets"
  lang="en">

<h:head>
  <!-- ... -->
</h:head>

<h:body>
  <ui:insert>
    <ui:include src="include.xhtml"/>
  </ui:insert>
</h:body>

</html>
```

### Error Routing

<img width="480" src="https://github.com/hanggrian/IIT-ITM515/raw/assets/assignments/hw7/screenshot1.png"><br><small>Screenshot 3 &mdash; Error 404</small>

Errors are routed to a custom error page configured in Jakarta EE Web API
`web.xml` descriptor. In this instance, an error 404 page is served when user
enters a non-existent URL.
