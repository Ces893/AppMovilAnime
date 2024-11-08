package com.example.appmovilanime;

import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appmovilanime.Adaptador.EpisodeAdapter;
import com.example.appmovilanime.Clase.Episode;
import com.example.appmovilanime.Clase.EpisodeResponse;
import com.example.appmovilanime.Service.JikaApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EpisodesActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private EpisodeAdapter episodeAdapter;
    private List<Episode> episodeList;
    private int currentPage = 1;
    private boolean isLoading = false;
    private int animeId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_episodes);

        animeId = getIntent().getIntExtra("ANIME_ID", -1);

        recyclerView = findViewById(R.id.recyclerViewEpisodes);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        episodeList = new ArrayList<>();
        episodeAdapter = new EpisodeAdapter(episodeList);
        recyclerView.setAdapter(episodeAdapter);

        loadEpisodes(animeId);
    }

    private void loadEpisodes(int animeId) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.jikan.moe/v4/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JikaApi jikaApi = retrofit.create(JikaApi.class);

        Call<EpisodeResponse> call = jikaApi.getEpisodes(animeId);

        call.enqueue(new Callback<EpisodeResponse>() {
            @Override
            public void onResponse(Call<EpisodeResponse> call, Response<EpisodeResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    // Si la respuesta es exitosa, obtenemos los episodios y actualizamos la lista
                    episodeList.clear();
                    episodeList.addAll(response.body().getData());
                    episodeAdapter.notifyDataSetChanged();
                } else {
                    // Si hay un error en la respuesta, mostramos un mensaje
                    Toast.makeText(EpisodesActivity.this, "Error al obtener episodios", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<EpisodeResponse> call, Throwable t) {
                // Si hay un error de conexión o en la llamada
                Toast.makeText(EpisodesActivity.this, "Error de conexión", Toast.LENGTH_SHORT).show();
            }
        });
    }
}