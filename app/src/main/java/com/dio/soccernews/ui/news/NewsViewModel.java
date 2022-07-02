package com.dio.soccernews.ui.news;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.dio.soccernews.domain.News;

import java.util.ArrayList;
import java.util.List;

public class NewsViewModel extends ViewModel {

    private final MutableLiveData<List<News>> news;

    public NewsViewModel() {
        this.news = new MutableLiveData<>();

        //TODO REMOVER MOCK DE NOTÍCIAS
        List<News> news = new ArrayList<>();
        news.add(new News("Ferroviária Tem Desfalque Importante", "Golpe fazia parte de uma quadrilha especializada."));
        news.add(new News("Ferrinha Joga No Sábado", "Jogo será repleto de emoção"));
        news.add(new News("Copa Do MUndo Feminina Está Terminando", "Um das últimas partidas terá Brasil e Argetina"));

        this.news.setValue(news);

    }

    public LiveData<List<News>> getNews() {

        return this.news;
    }
}