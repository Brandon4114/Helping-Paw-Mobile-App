# Helping Paw: MObile Application

This is an android mobile application which aims to create a better experiecne for thoese who sponsor animals from different charities. To do this I have created
a mobile application for android devices whick will connect to a  web server in order to collect all the necessary data about the animals the user sponsors.
This mixture of technologies aims to show how a mixture of digital and physical mediums can be digitized into a single application.
## Getting Started (Software Based)


### Prerequisites
in order to run the project fully the user will need working versions of:
* Java 1.8  (or later)
* XAMPP (Apache and SQL modules downloaded)
* Android Studio
* Laravel (6.18.8 or later)
* Composer (version 1.9.1)

### Installing
In order to run the web application the user must install XAMPP, once this has been done extract the MobileApp and paste into
the htdocs folder where XAMPP is installed.

once XAMPP have been installed open the control panel and selct MySQL Admin.
from here create a new database called "mobile_app_db"


The rest of the project should be placed within Android studios projects folder called "CE301CharityApp"

To run the application in android studio you must install  a virtual device using Android Studio's AVD manager
the current build of the project uses the Pixel 2 and must use API 29 or higher.


#### Edit the configuration file
In the MobileApp folder the .env file, the first 14 lines should appear as
```
APP_NAME=Laravel
APP_ENV=local
APP_KEY=base64:puZRhrumT0Gox4I5JlnRddaXcEAzGKI4XQGwgdOXJ9Q=
APP_DEBUG=true
APP_URL=http://localhost

LOG_CHANNEL=stack

DB_CONNECTION=mysql
DB_HOST=127.0.0.1:3307
DB_PORT=3306
DB_DATABASE= mobile_app_db
DB_USERNAME=root
DB_PASSWORD=
```

From the XAMPP control panel > MySQL >  config > my.ini
replace all occurances of 3306 with 3307
This will allow the web applicaiton to acces the databse

#### Install libraries and run program

In CLI navigate to the MobileApp folder inside XAMPP and 
user "composer install or upadte to make sure it is installed"
use "php artisan migrate" to run all migration

Once the migrations have been made, start the Apache and MySQL modules and use "localhost/MobileApp" 
to acces the web page

After the web application is running use Android studios make project feature (Ctr+ F9) 
and run the build (Shift + F10)

### Running Tests
How do I run your tests? Make it as easy as possible to do this!

```
pip install -r requirements.txt
./tests.py
```


## Authors

* Brandon Hull


