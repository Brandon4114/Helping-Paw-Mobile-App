<?php

use Illuminate\Http\Request;

/*
|--------------------------------------------------------------------------
| API Routes
|--------------------------------------------------------------------------
|
| Here is where you can register API routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| is assigned the "api" middleware group. Enjoy building your API!
|
*/

Route::middleware('auth:api')->get('/user', function (Request $request) {
    return $request->user();
});

Route::get('jsonData/{id}', 'APIController@index');
Route::get('jsonData/{Json}', 'APIController@show');
Route::post('jsonData', 'APIController@store');
Route::put('jsonData/{Json}', 'APIController@update');
Route::delete('jsonData/{Json}', 'APIController@delete');

Route::post('register', 'Auth\RegisterController@register');
