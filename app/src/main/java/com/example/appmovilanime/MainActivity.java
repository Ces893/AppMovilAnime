package com.example.appmovilanime;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appmovilanime.Adaptador.AnimeAdapter;
import com.example.appmovilanime.Clase.Anime;
import com.example.appmovilanime.Clase.AnimeResponse;
import com.example.appmovilanime.Service.JikaApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AnimeAdapter animeAdapter;
    private List<Anime> animeList = new ArrayList<>();
    private int currentPage = 1;
    private boolean isLoading = false;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progressBar);
        recyclerView = findViewById(R.id.recycle);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        animeAdapter = new AnimeAdapter();
        recyclerView.setAdapter(animeAdapter);

        // Agregar el listener de scroll para la paginación
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if (!isLoading && !recyclerView.canScrollVertically(1)) {
                    currentPage++;  // Incrementar la página
                    loadAnimeData(currentPage);
                }
            }
        });

        loadAnimeData(currentPage);
    }

    private void loadAnimeData(int page) {
        if (isLoading) return;
        isLoading = true;
        progressBar.setVisibility(View.VISIBLE);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.jikan.moe/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JikaApi jikaApi = retrofit.create(JikaApi.class);

        Call<AnimeResponse> call = jikaApi.getAnimeList(page);  // Pasamos la página

        call.enqueue(new Callback<AnimeResponse>() {
            @Override
            public void onResponse(Call<AnimeResponse> call, Response<AnimeResponse> response) {
                isLoading = false;
                progressBar.setVisibility(View.GONE);

                if (response.isSuccessful() && response.body() != null) {
                    List<Anime> newAnimeList = response.body().getData();
                    animeList.addAll(newAnimeList);  // Agregar los nuevos animes a la lista existente

                    animeAdapter.setAnimeList(animeList);  // Actualizar el adaptador
                } else {
                    Log.e("API_ERROR", "Error: " + response.code() + " - " + response.message());
                    Toast.makeText(MainActivity.this, "Error al obtener los datos", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AnimeResponse> call, Throwable t) {
                isLoading = false;
                progressBar.setVisibility(View.GONE);
                Log.e("API_FAILURE", "Error de conexión: " + t.getMessage());
                Toast.makeText(MainActivity.this, "Error de conexión", Toast.LENGTH_SHORT).show();
            }
        });
    }
}