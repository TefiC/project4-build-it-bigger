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

    /**
     * Method that supplies a joke by taking a joke
     * from the jokes array increasing the index by 1
     * until it reaches the end of the list and then
     * goes back to the first element again
     *
     * @return A Joke as a String
     */
    public static String supplyJoke() {
        String joke = jokes[mCurrentJokeIndex];
        changeNextJoke();
        return joke;
    }

    /**
     * Updates the index for the next joke that
     * is to be displayed to the user
     */
    public static void changeNextJoke() {
        if(mCurrentJokeIndex != mNumOfJokes - 1) {
            mCurrentJokeIndex += 1;
        } else {
            mCurrentJokeIndex = 0;
        }
    }
}
