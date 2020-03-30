<?php
namespace App\Http\Controllers;
use App\Animals;
use App\Progress;
use App\Sponsor_Animals;
class APIController extends Controller{

  public function index($id)
      {

          $sponsor_animals = Sponsor_Animals::where('SponsorID',$id)->pluck('animalID');

          $animals = Animals::whereIn('id',$sponsor_animals)->get();

          $progress = Progress::whereIn('animalID', $sponsor_animals)->get();
          dd($progress);
          return response()->json($animals, 201);

      }

      public function show(Animals $animals)
      {
          return $animals;
      }

      public function store(Request $request)
      {
          $animals = Animals::create($request->all());

          return response()->json($animals, 201);
      }

      public function update(Request $request, Animals $animals)
      {
          $animals->update($request->all());

          return response()->json($animals, 200);
      }

      public function delete(Animals $animals)
      {
          $animals->delete();

          return response()->json(null, 204);
      }

}
