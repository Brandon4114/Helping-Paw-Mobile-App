

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

<h1>Add an image</h1>

<form action="{{ route('image.upload.post') }}" method="POST" enctype="multipart/form-data">

            @csrf

            <div class="row">



                <div class="col-md-6">

                    <input type="file" name="image" class="form-control">

                </div>



                <div class="col-md-6">

                    <button type="submit" class="btn btn-success">Upload</button>

                </div>



            </div>

        </form>

</div>
</body>
</html>
