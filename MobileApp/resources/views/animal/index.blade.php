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
      <h1>All animals</h1>

        <table class="table table-striped table-bordered">
          <thead>
            <tr>
              <th>Name</th>
              <th>Description</th>
            </tr>

        @foreach ($animals as $key => $value)
          <tr>
            <td><?php echo $value->animalName ?></td>
            <td><?php echo $value->animalDescription ?></td>
            <td><a href="/animals/{{$value->id}}"/>edit</td>
          </tr>
        @endforeach
          </table>
    </div>
  </body>
</html>
