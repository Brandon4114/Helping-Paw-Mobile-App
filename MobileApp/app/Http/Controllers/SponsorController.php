<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Animals;
use App\Sponsor;
use App\Sponsor_Animals;
use View;
use Redirect;
use Session;
class SponsorController extends Controller
{
  public function index(){
    $sponsors = Sponsor::all();

    return View::make('sponsors.index')->with('sponsors', $sponsors);

  }

  public function create(){
    $animals = Animals::all();

    return View::make('sponsors.create')->with('animals', $animals);
  }
  public function store(Request $request){

    $rules = array(
      'sponsorName'  =>  'required',
      'sponsorEmail' => 'required',
    );

    $sponsor = new Sponsor;
    $sponsor->name = $request->sponsorName;
    $sponsor->email = $request->sponsorEmail;
    $sponsor->save();
    $animals = $request->animals;
    foreach ($animals as $key => $value) {
      $sponsor_animals = new Sponsor_Animals;
      $sponsor_animals->SponsorID = $sponsor->id;
      $sponsor_animals->animalID = $value;
      $sponsor_animals->save();
    }

    Session::flash('message','Sponsor successfully stored');
    return Redirect::to('/mobileapp/sponsors');

  }

  public function edit($id){

    $sponsor = Sponsor::where('id', $id)->first();
    $animals = Animals::all();
    $sponsored = Sponsor_Animals::where('SponsorID', $id)->pluck('animalID')->toArray();

    return View::make('sponsors.edit')->with(['sponsor'=> $sponsor, "animals" =>$animals,'sponsored'=>$sponsored]);
  }

  public function update(Request $request){

    $sponsor = Sponsor::find($request->id);

     $sponsor->name = $request->sponsorName;
     $sponsor->emial = $request->sponsorEmail;

    $sponsored = $request->animals;
    $sponsored_old = Sponsor_Animals::where('SponsorID', $request->id)->pluck('animalID','id')->toArray();

    foreach ($sponsored_old as $key => $value) {

      if(!in_array($value, $sponsored)){

        Sponsor_Animals::where('id',$key)->delete();

        Session::flash('message','Sponsor successfully deleted');
        return Redirect::to('/mobileapp/sponsors');

      } else{
        $update_pivot = new Sponsor_Animals();
        $update_pivot->SponsorID = $sponsor->id;
        $update_pivot->animalID = $key;
        $update_pivot->save();
      }
    }

  }

  public function destroy($id){
    $sponsored = Sponsor_Animals::where('SponsorID', $id)->pluck('id');

    foreach ($sponsored as $key => $value) {
      Sponsor_Animals::where('id', $value)->delete();
    }
    $sponsor= Sponsor::find($id);
    $sponsor->delete();
    Session::flash('message','Sponsor successfully deleted');
    return Redirect::to('/mobileapp/sponsors');
  }
}
