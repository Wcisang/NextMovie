package com.gwr.nextmovie.network;

/**
 * Created by willi on 09/07/2016.
 */
public class ConsURL {

    public static String imageURL = "http://image.tmdb.org/t/p/w300";

    private final static String API_KEY = "?api_key=69b7b9abff5c26bbe5f01669b74b9643";

    private static String domain = "https://api.themoviedb.org/";

    public final static String byPopular = domain + "3/movie/popular" + API_KEY;

    public final static String byRated = domain + "3/movie/top_rated" + API_KEY;

}
