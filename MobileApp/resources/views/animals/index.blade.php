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
      <h1>All animals</h1>
      <h2 class="float"><a href="/animals/create">Add animal</a></h2>

        <table class="table table-striped table-bordered">
          <thead>
            <tr>
              <th>Name</th>
              <th>Description</th>
              <th></th>
              <th></th>
            </tr>

        @foreach ($animals as $key => $value)
          <tr>
            <td><?php echo $value->animalName ?></td>
            <td><?php echo $value->animalDescription ?></td>
            <td><a href="/animals/{{$value->id}}/edit" class="btn btn-info"/>edit</td>
              <td>
                {{ Form::open(array('url' => 'animals/' . $value->id)) }}
                    {{ Form::hidden('_method', 'DELETE') }}
                    {{ Form::submit('Delete Animal', array('class' => 'btn btn-warning')) }}
                {{ Form::close() }}</td>
          </tr>
        @endforeach
          </table>
    </div>
  </body>
</html>
