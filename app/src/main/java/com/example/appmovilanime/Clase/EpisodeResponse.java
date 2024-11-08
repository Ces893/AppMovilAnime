package com.example.appmovilanime.Clase;

import java.util.List;

public class EpisodeResponse {
    private Pagination paginationE;
    private List<Episode> data;

    public Pagination getPagination() {
        return paginationE;
    }

    public void setPagination(Pagination pagination) {
        this.paginationE = pagination;
    }

    public List<Episode> getData() {
        return data;
    }

    public void setData(List<Episode> data) {
        this.data = data;
    }

    public static class Pagination {
        private int last_visible_page;
        private boolean has_next_page;

        public int getLast_visible_page() {
            return last_visible_page;
        }

        public void setLast_visible_page(int last_visible_page) {
            this.last_visible_page = last_visible_page;
        }

        public boolean isHas_next_page() {
            return has_next_page;
        }

        public void setHas_next_page(boolean has_next_page) {
            this.has_next_page = has_next_page;
        }
    }
}
