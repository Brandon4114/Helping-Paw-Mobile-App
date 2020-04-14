<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Images;
use View;
class ImageController extends Controller
{
    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index()
    {
        $images = Images::all();
        return View::make('images.index')->with('images', $images);
    }

    /**
     * Show the form for creating a new resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function create()
    {
        return View::make('images.create');
    }

    /**
     * Store a newly created resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @return \Illuminate\Http\Response
     */
    public function store(Request $request)
    {
        //Storage::put('$request->image');
        $rules = array(
          'image' => 'required|image|mimes:jpeg,png,jpg,gif,svg|max:2048',
        );

        $validator = Validator::make(Input::all(), $rules);

        if ($validator->fails()){
          return Redirect::to('images/create')
            ->withErrors($validator)
            ->withInput(Input::except('password'));
        } else {
          // $image = new Images;
          // $image->imageName = Input::get('imageName');
          // $image->imageType = Input::get('imageType');
          // $image->save();
          $imageName = time().'.'.request()->image->getClientOriginalExtension();



        request()->image->move(public_path('images'), $imageName);

          Session::flash('message','Image successfully stored');
          return Redirect::to('images');
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
        $image = Images::find($id);
        return View::make('image.show')->with('image',$image);
    }

    /**
     * Show the form for editing the specified resource.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function edit($id)
    {
      $image = Images::find($id);
      return View::make('image.edit')->with('image',$image);
    }

    /**
     * Update the specified resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function update( $id)
    {
      $validator = Validator::make(Input::all(), $rules);

      if ($validator->fails()){
        return Redirect::to('images/'.$id.'create')
          ->withErrors($validator)
          ->withInput(Input::except('password'));
      } else {
        $image = Images::find($id);
        $image->imageName = Input::get('imageName');
        $image->imageType = Input::get('imageType');
        $image->save();

        Session::flash('message','Image successfully edited');
        return Redirect::to('images');
      }
    }

    /**
     * Remove the specified resource from storage.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function destroy($id)
    {
      // delete
      $image = Images::find($id);
      $image->delete();

      // redirect
      Session::flash('message', 'Successfully deleted image');
      return Redirect::to('images');
    }
}
