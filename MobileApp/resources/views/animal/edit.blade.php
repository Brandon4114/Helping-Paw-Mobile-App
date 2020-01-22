<!DOCTYPE html>
<html>
  <head>
    <title>Animals {{ $animal->id }}</title>
  </head>
  <body>
    <h1>Animal {{ $animal->id }}</h1>

        {{Form::model($animal, array('animals.update', $animal->id, 'method'=> 'PUT'))}}


        <div class="form-group">
          {{Form::label('animalName', 'Name') }}<br>
          {{ Form::text('animalName', null, array('class' => 'form-control')) }}
        </div>

        <div class="form-group">
            {{ Form::label('animalDescription', 'animalDescription') }}<br>
            {{ Form::text('animalDescription', null, array('class' => 'form-control')) }}
        </div>

        {{ Form::submit('Save', array('class' => 'btn btn-primary')) }}
        {{ Form::close() }}
  </body>
</html>
