<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class Animals extends Model
{
   protected $fillable = [
     'animalName',
     'animalDescription'
   ];


}
