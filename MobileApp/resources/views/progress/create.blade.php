
<!DOCTYPE html>
<html>
<head>
    <title>Add Progress point</title>
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

<h1>Add an progress point</h1>

{{ Form::open(array('url' => 'progress')) }}

    <div class="form-group">
        {{ Form::label('animalID', 'Animal Name') }}
        {{ Form::select('animalID', $animals, array('class' => 'form-control')) }}
    </div>

    <div class="form-group">
        {{ Form::label('progressDescription', 'Progress Description') }}
        {{ Form::textarea('progressDescription', 'Enter Description', array('class' => 'form-control')) }}
    </div>

    {{ Form::submit('Add Progress Point', array('class' => 'btn btn-primary')) }}

{{ Form::close() }}

</div>
</body>
</html>
