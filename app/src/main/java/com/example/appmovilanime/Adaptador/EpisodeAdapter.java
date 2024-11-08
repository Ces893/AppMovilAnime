package com.example.appmovilanime.Adaptador;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appmovilanime.Clase.Episode;
import com.example.appmovilanime.R;

import java.util.List;

public class EpisodeAdapter extends RecyclerView.Adapter<EpisodeAdapter.EpisodeViewHolder>{

    private List<Episode> episodeList;

    public EpisodeAdapter(List<Episode> episodeList) {
        this.episodeList = episodeList;
    }

    @NonNull
    @Override
    public EpisodeAdapter.EpisodeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_espisodio, parent, false);
        return new EpisodeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EpisodeAdapter.EpisodeViewHolder holder, int position) {
        View view = holder.itemView;
        Episode episode = episodeList.get(position);

        String episodeNumber = "Episode " + (position + 1);
        String airedDate = episode.getAired();

        TextView textView = view.findViewById(R.id.episodesTitle);
        TextView textView1 = view.findViewById(R.id.episodeAired);

        textView.setText(episodeNumber);
        textView1.setText(airedDate);
    }

    @Override
    public int getItemCount() {
        return episodeList.size();
    }

    public class EpisodeViewHolder extends RecyclerView.ViewHolder {
        public EpisodeViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
