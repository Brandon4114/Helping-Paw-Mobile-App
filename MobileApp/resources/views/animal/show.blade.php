<!DOCTYPE html>
<html>
  <head>
    <title>View all animals</title>
    <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
  </head>
  <body>

    <div class="container">
      <nav class="navbar navbar-inverse">
        <ul class="nav navbar-nav">
          <li><a href="{{URL::to('animals')}}">View all animals</li>
          <li><a href="{{URL::to('progress')}}">View all progress points</li>
          <li><a href="{{URL::to('images')}}">View all images</li>
        </ul>

      </nav>


      <h1>Showing {{ $animal->name }}</h1>

          <div class="jumbotron text-center">
              <h2>{{ $animal->animalName }}</h2>
              <p>
                  <strong>Description:</strong> {{ $animal->animalDescription }}
              </p>
          </div>
    </div>
  </body>
</html>
