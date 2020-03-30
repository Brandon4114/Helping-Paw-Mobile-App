<?php

namespace App\Http\Controllers;


use App\Animals;
use App\Progress;
use View;
use Request;
use Session;
use Redirect;
use Illuminate\Support\Facades\Input;
use Illuminate\Support\Facades\Validator;
class AnimalController extends Controller
{
    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index()
    {
      $animals = Animals::all();
       return View::make('animals.index')->with('animals',$animals);
    }

    /**
   * Show the form for creating a new resource.
   *
   * @return \Illuminate\Http\Response
   */
  public function create()
  {
      return View::make('animals.create');
  }



    /**
     * Store a newly created resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @return \Illuminate\Http\Response
     */
    public function store()
    {
      $rules = array(
        'animalName'        => 'required',
        'animalDescription' => 'required'
      );

      $validator = Validator::make(Request::all(), $rules);

      if ($validator->fails()) {
        return Redirect::to('animals/create')
          ->withErrors($validator)
          ->withInput(Request::except('password'));
      } else {
        $animal = new Animals;
        $animal->animalName = Request::get('animalName');
        $animal->animalDescription = Request::get('animalDescription');
        $animal->save();


        Session::flash('message', 'Animal successfully created');
        return Redirect::to('animals');
      }
    }


    /**
     * Display the specified resource.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function show($id)
    {
        $animal = Animals::find($id);
        return View::make('animal.show')->with('animal',$animal);
    }



    /**
     * Show the form for editing the specified resource.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function edit($id)
    {
      $animal = Animals::find($id);
      $points = Progress::where('animalID', $id);
      return View::make('animals.edit')->with('animal',$animal);

    }

    public function update($id)
    {
      $rules = array(
        'animalName'        => 'required',
        'animalDescription' => 'required'
      );
      $validator = Validator::make(Request::all(), $rules);

      if ($validator->fails()) {
        return Redirect::to('animals'.$id.'/edit')
          ->withErrors($validator)
          ->withInput(Request::except('password'));
      } else {
        $animal = Animals::find($id);
        $animal->animalName = Request::get('animalName');
        $animal->animalDescription = Request::get('animalDescription');
        $animal->save();


        Session::flash('message', 'Animal successfully updated');
        return Redirect::to('animals');
      }
    }

    public function destroy($id)
    {
      // delete
      $animal = Animals::find($id);
      $animal->delete();

      // redirect
      Session::flash('message', 'Successfully deleted animal!');
      return Redirect::to('animals');
    }


}
