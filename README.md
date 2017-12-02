# Build it Bigger

### Project 4 - Udacity Android Developer Nanodegree

---

In this project, I created an app with paid and free flavors that uses
multiple libraries and Google Cloud Endpoints.

- The app consists of four modules. 
      - A Java library that provides jokes 
      - A Google Cloud Endpoints (GCE) project that serves those jokes
      - An Android Library containing an activity for displaying jokes 
      - An Android app that fetches jokes from the Google Cloud Endpoints module and passes them to the Android Library to be displayed.

## Features

- When the user launches the app, an Activity with a message prompting the user to click a button to tell a joke will be displayed.
- An Ad is displayed below this button using Google AdMob.
- If the user clicks on the button, an activity is launched and a tech joke is displayed.
- Jokes change every time the user clicks on the "Tell Joke" button until they reach the end of the list, in which case the list starts over.

## Free vs. Paid Flavors

* The free flavor displays an Ad on the Main Activity and an interstitial Ad when the user clicks on the "Tell Joke" button. When the user closes the Ad, the Activity that displays the joke will be launched.
* If the internet connection is slow and the user clicks on the button before the interstitial Ad is loaded, the Activity is launched without any inconvenient. If the user then returns to the Main Activity and requests another joke, if the Ad has been loaded, it will be displayed.

## Gradle Tasks

- There are **four custom Gradle tasks** on the build.gradle file on the project's root directory.

    - **runAllConnectedTests**: runs all the connected tests by fetching the necessary data from a local server.
    - **startAppEngine**: starts the local server that will provide the data.
    - **runTests**: runs the connected tests for each corresponding flavor.
    - **stopAppEngine**: stops the local server.

## Skills

* I learned the role of Gradle in building Android Apps and how to use
Gradle to manage apps of increasing complexity. 
* Added free and paid flavors to an app.
* Factored reusable code into a Java library.
* Factored reusable Android code into an Android library.
* Configured a multi project build to compile the app and libraries.
* Used the Gradle App Engine plugin to deploy a backend.
* Configured an integration test suite that runs against the local App Engine development server.

## Attributions

These resources were very helpful during my project:

* The Jokes displayed on this app were taken from [The geekiest tech jokes on the internet](https://www.techrepublic.com/article/the-geekiest-tech-jokes-on-the-internet/)
* Udacity Android Developer Nanodegree Courses
* [Tasks ordering](http://trickyandroid.com/gradle-tip-3-tasks-ordering/)
* Udacity Forums
* [Closing interstitial Ad with Espresso](https://stackoverflow.com/questions/37843039/how-to-handleclose-interstitial-ad-during-espresso-tests)
* [Udacity Forum Post for Gradle Tasks Structure (1)](https://discussions.udacity.com/t/last-optional-step-how-to-start-an-external-module-task-from-the-root-build-gradle/24211/9)
* [Udacity Forum Post for Gradle Tasks Structure (2)](https://discussions.udacity.com/t/last-optional-step-how-to-start-an-external-module-task-from-the-root-build-gradle/24211/12)
* [Google Cloud Endpoints (code and documentation)](https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloEndpoints)