<!DOCTYPE html>
<html>
  <head>
    <title>Animals {{ $animal->id }}</title>
  </head>
  <body>
    <h1>Animal {{ $animal->id }}</h1>

    <form class="" action="{{route('animals./edit')}}" method="post">
      <input type="text" name="" value="{{$animal->animalName}}">
      <input type="text" name="" value="{{$animal->animalDescription}}">
      <input type="submit"value="submit">
    </form>
  </body>
</html>
