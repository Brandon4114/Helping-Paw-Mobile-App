<!DOCTYPE html>
<html>
<head>
    <title>Add Animals</title>
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

<h1>Add an animal</h1>

{{ Form::open(array('url' => 'animals')) }}

    <div class="form-group">
        {{ Form::label('animalName', 'Name') }}
        {{ Form::text('animalName', 'Enter Animal Name', array('class' => 'form-control')) }}
    </div>


    <div class="form-group">
        {{ Form::label('animalDescription', 'Animal Description') }}
        {{ Form::textarea('animalDescription', 'Enter Description', array('class' => 'form-control')) }}
    </div>

    {{ Form::submit('Add Animal', array('class' => 'btn btn-primary')) }}

{{ Form::close() }}

</div>
</body>
</html>
