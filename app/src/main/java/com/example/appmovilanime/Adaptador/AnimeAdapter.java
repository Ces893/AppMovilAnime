package com.example.appmovilanime.Adaptador;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.appmovilanime.Clase.Anime;
import com.example.appmovilanime.EpisodesActivity;
import com.example.appmovilanime.R;

import java.util.ArrayList;
import java.util.List;

public class AnimeAdapter extends RecyclerView.Adapter<AnimeAdapter.AnimeViewHolder>{

    private List<Anime> animeList = new ArrayList<>();

    @NonNull
    @Override
    public AnimeAdapter.AnimeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_anime, parent, false);

        return new AnimeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AnimeAdapter.AnimeViewHolder holder, int position) {
        View view = holder.itemView;
        Anime item = animeList.get(position);

        TextView txTitulo = view.findViewById(R.id.episodeTitle);
        TextView txEspisodio = view.findViewById(R.id.episodeNumber);

        ImageView img = view.findViewById(R.id.episodeImage);

        txTitulo.setText(item.getTitle());
        txEspisodio.setText("Episodios : "+String.valueOf(item.getEpisodes()));

        String imageUrl = item.getImages().getJpg().getImage_url();

        Glide.with(holder.itemView.getContext())
                .load(imageUrl)
                .into(img);

        img.setOnClickListener(v -> {
            Intent intent = new Intent(holder.itemView.getContext(), EpisodesActivity.class);
            intent.putExtra("ANIME_ID", item.getMal_id());  // Pasamos el ID del anime
            holder.itemView.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return animeList != null ? animeList.size() : 0;
    }

    public void setAnimeList(List<Anime> animeList) {
        this.animeList = animeList;
        notifyDataSetChanged();
    }

    public static class AnimeViewHolder extends RecyclerView.ViewHolder {
        public AnimeViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
