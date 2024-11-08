package com.example.appmovilanime.Clase;

import android.media.Image;

public class Anime {
    private int mal_id;
    private String title;
    private int episodes;
    private Images images;

    public Anime() {

    }
    public int getMal_id() {
        return mal_id;
    }

    public void setMal_id(int mal_id) {
        this.mal_id = mal_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getEpisodes() {
        return episodes;
    }

    public void setEpisodes(int episodes) {
        this.episodes = episodes;
    }

    public Images getImages() {
        return images;
    }

    public void setImages(Images images) {
        this.images = images;
    }

    public static class Images {
        private Jpg jpg;

        public Jpg getJpg() {
            return jpg;
        }

        public static class Jpg {
            private String image_url;

            public String getImage_url() {
                return image_url;
            }
        }
    }
}
