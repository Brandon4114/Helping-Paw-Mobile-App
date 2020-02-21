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
            <td><?php echo $value->animalPoint ?></td>
            <td><?php echo $value->progressDescription ?></td>
            <td><a href="/progress/{{$value->id}}/edit" class="btn btn-info"/>edit</td>

              <td>
                {{ Form::open(array('url' => 'progress/' . $value->id)) }}
                    {{ Form::hidden('_method', 'DELETE') }}
                    {{ Form::submit('Delete Progress Point', array('class' => 'btn btn-warning')) }}
                {{ Form::close() }}
              </td>
          </tr>
        @endforeach
          </table>
    </div>
  </body>
</html>
