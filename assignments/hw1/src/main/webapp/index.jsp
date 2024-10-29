<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<script>
  function onRepositorySelected(tag, id) {
    tag.href = "repository?id=" + id + "&dark-mode=" + isDarkMode()
  }
</script>
<html>

<head>
  <meta charset="UTF-8">
  <title>ITMD 4/515 - Lab 1</title>

  <meta name="viewport" content="width=device-width">
  <meta http-equiv="X-UA-Compatible" content="chrome=1">


  <link rel="stylesheet" href="styles/minimal.css">
  <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Sharp:opsz,wght,FILL,GRAD@48,400,1,0">
  <script src="scripts/minimal.js"></script>

  <!-- Primary meta tags -->
  <meta name="title" content="IIT ITM515">
  <meta name="description" content="Adv Software Programming at Illinois Tech &mdash; Fall 2024">
</head>

<body>
  <div class="wrapper">
    <header>
      <h1>Introduction</h1>
      <p>Adv Software Programming at Illinois Tech &mdash; Fall 2024</p>
      <p class="view"><a href="https://github.com/hanggrian/IIT-ITM515/">View the Project on GitHub <small>hanggrian/IIT-ITM515</small></a></p>
      <ul>
        <li><a href="https://github.com/hanggrian/IIT-ITM515/">View on<strong>GitHub</strong></a></li>
        <li><a href="https://github.com/hanggrian/IIT-ITM515/zipball/main/">Download<strong>ZIP File</strong></a></li>
        <li><a href="https://github.com/hanggrian/IIT-ITM515/tarball/main/">Download<strong>TAR Ball</strong></a></li>
      </ul>
    </header>
    <section>
      <h1>About Me</h1>
      <img alt="My profile picture." src="https://github.com/hanggrian/hanggrian.github.io/raw/main/images/logo.png">
      <p>Greetings, friends! I'm <strong>Hendra</strong>, a graduate student from Indonesia. &#127470;&#127465;</p>
      <p>Welcome to my first JSP files. Although I am comfortable with Java, web development is not my strong suit. I'm looking forward to learning something new in this course! &#127891;</p>
      <table>
        <tr>
          <th>Information</th>
          <th></th>
        </tr>
        <tr>
          <td>CWID</td>
          <td>A20529195</td>
        </tr>
        <tr>
          <td>Full Name</td>
          <td>Hendra Anggrianto Wijaya</td>
        </tr>
        <tr>
          <td>Campus Email</td>
          <td><a href = "mailto: hwijaya@hawk.iit.edu">hwijaya@hawk.iit.edu</a></td>
        </tr>
      </table>

      <h2>Latest Work</h2>
      <p>The purpose of this section is to exhibit servlet requests and handle responses appropriately, using my latest open-source libraries as sample data. In this example, the <b>dark mode preference</b> will be applied to the next page.</p>
      <ul>
        <li><a onclick="onRepositorySelected(this, 'rulebook')">Rulebook</a></li>
        <li><a onclick="onRepositorySelected(this, 'javapoet-dsl')">JavaPoet DSL</a></li>
        <li><a onclick="onRepositorySelected(this, 'ktfx')">KtFX</a></li>
      </ul>

      <h2>Webpage Theme</h2>
      <p>This website uses <a href="https://github.com/hanggrian/minimal-dark-theme/">Minimal Dark</a> and <a href="https://github.com/hanggrian/cayman-dark-theme/">Cayman Dark</a> website themes. They were originally forked from <a href="https://github.com/orderedlist/minimal">Minimal</a> and <a href="https://github.com/jasonlong/cayman-theme">Cayman</a> to include dark mode settings.</p>
    </section>
    <footer>
      <p><button class="material-symbols-sharp" id="theme-toggle" title="Toggle dark mode" onclick="toggleDarkMode()"></button></p>
      <p>This project is maintained by <a href="https://github.com/hanggrian/">Hendra Anggrian</a></p>
      <p><small>Hosted on GitHub Pages &mdash; Theme by <a href="https://github.com/orderedlist/">orderedlist</a></small></p>
    </footer>
  </div>
  <script src="scripts/scale.fix.js"></script>
</body>

</html>
