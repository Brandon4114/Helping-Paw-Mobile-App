<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Animals;
use View;
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
       return View::make('animal.index')->with('animals',$animals);
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

      $validator = Validator::make(Input::all(), $rules);

      if ($validator->fails()) {
        return Redirect::to('animals/create')
          ->withErrors($validator)
          ->withInput(Input::except('password'));
      } else {
        $animal = new Animals;
        $animal->animalName = Input::get('animalName');
        $animal->animalDescription = Input::get('animalDescription');
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

      return View::make('animal.edit')->with('animal',$animal);

    }

    public function update($id)
    {
      $validator = Validator::make(Input::all(), $rules);

      if ($validator->fails()) {
        return Redirect::to('animals'.$id.'/edit')
          ->withErrors($validator)
          ->withInput(Input::except('password'));
      } else {
        $animal = Animals::find($id);
        $animal->animalName = Input::get('animalName');
        $animal->animalDescription = Input::get('animalDescription');
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
