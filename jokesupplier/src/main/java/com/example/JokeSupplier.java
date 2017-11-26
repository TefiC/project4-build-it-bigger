package com.example;

public class JokeSupplier {

    /*
     * Data
     */

    private static String[] jokes = new String[] {
            "There are 10 types of people in the world: those who understand binary, and those who don't",
            "How many programmers does it take to change a light bulb? \n" + "None. It's a hardware problem.",
            "A SEO couple had twins. For the first time they were happy with duplicate content.",
            "Why was the JavaScript developer sad? \n" + "Because he didn't Node how to Express himself"
    };

    /*
     * Fields
     */

    private static int mNumOfJokes = jokes.length;
    private static int mCurrentJokeIndex = 0;


    /*
     * Methods
     */

    public static String supplyJoke() {
        String joke = jokes[mCurrentJokeIndex];
        changeNextJoke();
        return joke;
    }

    public static void changeNextJoke() {
        if(mCurrentJokeIndex != mNumOfJokes - 1) {
            mCurrentJokeIndex += 1;
        } else {
            mCurrentJokeIndex = 0;
        }
    }
}
