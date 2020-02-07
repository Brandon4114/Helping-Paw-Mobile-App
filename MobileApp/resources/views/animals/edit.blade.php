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

      <h1>Edit {{ $animal->animalName }}</h1>

        {{ Form::model($animal, array('route' => array('animals.update', $animal->id), 'method' => 'PUT')) }}

            <div class="form-group">
                {{ Form::label('animalName', 'Name') }}
                {{ Form::text('animalName', null, array('class' => 'form-control')) }}
            </div>

            <div class="form-group">
                {{ Form::label('animalDescription', 'Description') }}
                {{ Form::textarea('animalDescription',null, array('class' => 'form-control')) }}
            </div>

            {{ Form::submit('Edit Animal', array('class' => 'btn btn-primary')) }}

        {{ Form::close() }}

    </div>
  </body>
</html>
