package com.dio.soccernews.ui.news;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.loader.content.AsyncTaskLoader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.room.Room;

import com.dio.soccernews.MainActivity;
import com.dio.soccernews.data.local.AppDatabase;
import com.dio.soccernews.databinding.FragmentNewsBinding;
import com.dio.soccernews.ui.adapter.NewsAdapter;

import java.util.Objects;

public class NewsFragment extends Fragment {

    private FragmentNewsBinding binding;
    private AppDatabase db;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        NewsViewModel newsViewModel = new ViewModelProvider(this).get(NewsViewModel.class);

        binding = FragmentNewsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        binding.rvNews.setLayoutManager(new LinearLayoutManager(getContext()));
        newsViewModel.getNews().observe(getViewLifecycleOwner(), news -> {
            binding.rvNews.setAdapter(new NewsAdapter(news, updateNews -> {
                MainActivity activity = (MainActivity) getActivity();
                if (activity != null) {
                    activity.getDb().newsDao().save(updateNews);
                }

            }));

        });

        newsViewModel.getState().observe(getViewLifecycleOwner(),state -> {
            switch (state) {
                case DOING:
                    //TODO Iniciar swiperRefreshLayout (loading).
                    break;
                case DONE:
                    //TODO Finalizar swiperRefreshLayout (loading).
                    break;
                case ERROR:
                    //TODO Finalizar swiperRefreshLayout (loading).
                    //TODO Mostra um erro genérico.

            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}