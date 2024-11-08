package com.example.appmovilanime.Service;

import com.example.appmovilanime.Clase.Anime;
import com.example.appmovilanime.Clase.AnimeResponse;
import com.example.appmovilanime.Clase.Episode;
import com.example.appmovilanime.Clase.EpisodeResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface JikaApi {
    @GET("v4/anime")
    Call<AnimeResponse> getAnimeList();

    @GET("anime/{id}/episodes")
    Call<EpisodeResponse> getEpisodes(@Path("id") int animeId);

    @GET("v4/anime")
    Call<AnimeResponse> getAnimeList(@Query("page") int page);

    @GET("anime/{id}/episodes")
    Call<EpisodeResponse> getEpisodes(
            @Path("id") int animeId,
            @Query("page") int page
    );
}