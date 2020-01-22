<!DOCTYPE html>
<html>
  <head>
    <title>Animals</title>
  </head>
  <body>
    <h1>Animals</h1>

    <table>
      <tr>
        <th>Name</th>
        <th>Description</th>
      </tr>
    <?php foreach ($animals as $key => $value): ?>
      <tr>
        <td><?php echo $value->animalName ?></td>
        <td><?php echo $value->animalDescription ?></td>
        <td><a href="/animals/{{$value->id}}"/>edit</td>
      </tr>
    <?php endforeach; ?>
      </table>
  </body>
</html>
