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
          <li><a href="{{URL::to('animals')}}">View all animals</a></li>
          <li><a href="{{URL::to('progress')}}">View all progress points</a></li>
          <li><a href="{{URL::to('images')}}">View all images</a></li>
        </ul>

      </nav>
      <h1>All progress points</h1>
      <h2><a href="/progress/create">Add Progress point</a></h2>

        <table class="table table-striped table-bordered">
          <thead>
            <tr>
              <th>Progress Point</th>
              <th>Description</th>
              <th></th>
              <th></th>
            </tr>
        </thead>

        @foreach ($progress as $key => $value)
          <tr>
            <td><?php echo $value->imageName ?></td>
            <td><?php echo $value->imageType ?></td>
            <td><a href="/images/{{$value->id}}"/>edit</td>
          </tr>
        @endforeach
          </table>
    </div>
  </body>
</html>
