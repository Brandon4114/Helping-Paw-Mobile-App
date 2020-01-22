<!DOCTYPE html>
<html>
  <head>
    <title>Animals {{ $animal->id }}</title>
  </head>
  <body>
    <h1>Animal {{ $animal->id }}</h1>
    <ul>
      <li>Make: {{ $animal->animal_name }}</li>
      <li>Model: {{ $animal->animal_description }}</li>

    </ul>
  </body>
</html>
