<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <title>ITMD 4/515 &mdash; Lab 6</title>

  <meta http-equiv="X-UA-Compatible" content="chrome=1">

  <link rel="stylesheet" href="styles/main.css">
  <link rel="stylesheet" href="styles/sakila.css">
  <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">

  <!-- Primary meta tags -->
  <meta name="title" content="IIT ITM515">
  <meta name="description" content="Adv Software Programming at Illinois Tech &mdash; Fall 2024">
</head>

<body>
<div class="wrapper">
  <header>
    <h1>ITMD 4/515 &mdash; Lab 6</h1>
    <p>Hendra Wijaya</p>
    <p class="view"><a href="http://github.com/hanggrian/IIT-ITM515/">
      View the Project on GitHub <small>hanggrian/IIT-ITM515</small></a>
    </p>
    <ul>
      <li>
        <a href="https://github.com/hanggrian/IIT-ITM515/zipball/main/">
          Download <strong>ZIP File</strong>
        </a>
      </li>
      <li>
        <a href="https://github.com/hanggrian/IIT-ITM515/tarball/main/">
          Download <strong>TAR Ball</strong>
        </a>
      </li>
      <li>
        <a href="http://github.com/hanggrian/IIT-ITM515/">View On <strong>GitHub</strong></a>
      </li>
    </ul>
  </header>
  <section>
    <h2 style="text-align: center;">Sakila Film Registrar</h2>
    <p>
    <div class="form-container">
      <form action="submit" method="POST">
        <label for="title-text">Title:</label>
        <input
          class="grid-span4"
          id="title-text"
          name="title"
          type="text"
          placeholder="Title"
          title="Title must be present and no longer than 128 characters."/>

        <label for="description-textarea">Description:</label>
        <textarea
          class="grid-span4"
          id="description-textarea"
          name="description"
          placeholder="Description"
          title="Description can be empty."></textarea>

        <label for="release-year-text">Release Year:</label>
        <input
          class="grid-span4"
          id="release-year-text"
          name="release-year"
          type="text"
          inputmode="numeric"
          oninput="this.value = this.value.replace(/\D+/g, '')"
          placeholder="Release year"
          title="Release year input is numeric-only, can be empty."/>

        <label for="language-select">Language:</label>
        <select
          class="grid-span4"
          id="language-select"
          name="language"
          title="Pick one language.">
          <option value="english">English</option>
          <option value="italian">Italian</option>
          <option value="japanese">Japanese</option>
          <option value="mandarin">Mandarin</option>
          <option value="french">French</option>
          <option value="german">German</option>
        </select>

        <label for="rental-duration-range">Rental Duration:</label>
        <input
          class="grid-span2"
          id="rental-duration-range"
          type="range"
          min="1"
          max="9"
          value="5"
          title="Rental duration is between 1 to 9 days."/>
        <input
          class="grid-span2"
          id="rental-duration-text"
          name="rental-duration"
          type="text"
          placeholder="Days"
          title="Rental duration is between 1 to 9 days."/>

        <label for="rental-rate-text">Rental Rate:</label>
        <label>$</label>
        <input
          class="grid-span2"
          id="rental-rate-text"
          name="rental-rate"
          type="text"
          inputmode="decimal"
          oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1')"
          placeholder="Rental rate"
          title="Rental rate input is decimal-only, cannot be zero.">
        <label></label>

        <label for="length-text">Length:</label>
        <label></label>
        <input
          class="grid-span2"
          id="length-text"
          name="length"
          type="text"
          inputmode="numeric"
          oninput="this.value = this.value.replace(/\D+/g, '')"
          placeholder="Length"
          title="Length input is numeric-only, cannot be zero."/>
        <label>min.</label>

        <label for="replacement-cost-text">Replacement Cost:</label>
        <label>$</label>
        <input
          class="grid-span2"
          id="replacement-cost-text"
          name="replacement-cost"
          type="text"
          inputmode="decimal"
          oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1')"
          placeholder="Replacement cost"
          title="Replacement cost input is decimal-only, cannot be zero."/>
        <label></label>

        <label>Rating:</label>
        <div class="grid-radios" style="flex-direction: row;">
          <input
            id="g-radio"
            name="rating"
            value="G"
            type="radio"
            title="Pick one rating."/>
          <label
            for="g-radio"
            title="Pick one rating.">
            G
          </label>

          <input
            id="pg-radio"
            name="rating"
            value="PG"
            type="radio"
            title="Pick one rating."/>
          <label
            for="pg-radio"
            title="Pick one rating.">
            PG
          </label>

          <input
            id="pg13-radio"
            name="rating"
            value="PG-13"
            type="radio"
            title="Pick one rating."/>
          <label
            for="pg13-radio"
            title="Pick one rating.">
            PG-13
          </label>

          <input
            id="r-radio"
            name="rating"
            value="R"
            type="radio"
            title="Pick one rating."/>
          <label
            for="r-radio"
            title="Pick one rating.">
            R
          </label>

          <input
            id="nc17-radio"
            name="rating"
            value="NC-17"
            type="radio"
            title="Pick one rating."/>
          <label
            for="nc17-radio"
            title="Pick one rating.">
            NC-17
          </label>
        </div>

        <label>Special Features:</label>
        <div class="grid-radios" style="flex-direction: column;">
          <div>
            <input
              id="behind-the-scenes-radio"
              name="behind-the-scenes"
              value="Behind the Scenes"
              type="checkbox"
              title="Pick multiple special features or nothing."/>
            <label
              for="behind-the-scenes-radio"
              title="Pick multiple special features or nothing.">
              Behind the Scenes
            </label>
          </div>

          <div>
            <input
              id="commentaries-radio"
              name="commentaries"
              value="Commentaries"
              type="checkbox"
              title="Pick multiple special features or nothing."/>
            <label
              for="commentaries-radio"
              title="Pick multiple special features or nothing.">
              Commentaries
            </label>
          </div>

          <div>
            <input
              id="deleted-scenes-radio"
              name="deleted-scenes"
              value="Deleted Scenes"
              type="checkbox"
              title="Pick multiple special features or nothing."/>
            <label
              for="deleted-scenes-radio"
              title="Pick multiple special features or nothing.">
              Deleted Scenes
            </label>
          </div>

          <div>
            <input
              id="trailers-radio"
              name="trailers"
              value="Trailers"
              type="checkbox"
              title="Pick multiple special features or nothing."/>
            <label
              for="trailers-radio"
              title="Pick multiple special features or nothing.">
              Trailers
            </label>
          </div>
        </div>

        <input class="grid-span5" id="submit" type="submit" value="Add Film" disabled="disabled"/>
      </form>
    </div>
    </p>

    <h4 style="margin-top: 60px;">About</h4>
    <p>
      Welcome to the Sakila Film Registrar, a simple servlet that executes POST request to insert a
      new film into the Sakila sample database. The web application validates user input and will
      only enable the submit button if the form is filled out correctly.
    </p>

    <h6>Webpage Theme</h6>
    <p>
      This landing webpage was created using my
      <a href="https://github.com/hanggrian/materialist-theme/">website theme</a>. It is a fork of
      <a href="https://github.com/orderedlist/modernist">modernist</a> theme that swaps the classic
      view with a colorful material design style.
    </p>
  </section>
</div>
<footer>
  <p>Project maintained by <a href="http://github.com/hanggrian/">Hendra Anggrian</a></p>
  <p>
    Hosted on GitHub Pages &mdash; Theme by
    <a href="https://github.com/orderedlist/">orderedlist</a>
  </p>
</footer>
<script src="scripts/scale.fix.js"></script>
<script src="scripts/sakila.js"></script>
</body>

</html>
