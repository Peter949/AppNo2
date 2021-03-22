package com.example.watch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainContent extends AppCompatActivity {
    RecyclerView recyclerView;
    List<KinoParam> mKino;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_content);
        recyclerView = findViewById(R.id.recyclerView);
        mKino = new ArrayList<>();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true);
        recyclerView.setLayoutManager(layoutManager);
        KinoAdapter adapter = new KinoAdapter(mKino);
        recyclerView.setAdapter(adapter);
        KinoAPI kinoAPI = KinoAPI.retrofit.create(KinoAPI.class);
        final Call<List<KinoParam>> call = kinoAPI.getData();
        call.enqueue(new Callback<List<KinoParam>>() {
            @Override
            public void onResponse(Call<List<KinoParam>> call, Response<List<KinoParam>> response)
            {
                if(response.isSuccessful())
                {
                    mKino.addAll(response.body());
                    recyclerView.getAdapter().notifyDataSetChanged();
                }
                else
                {
                    ResponseBody errorBody = response.errorBody();
                    try
                    {
                        Toast.makeText(MainContent.this, errorBody.string(), Toast.LENGTH_LONG).show();
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
            @Override
            public void onFailure(Call<List<KinoParam>> call, Throwable t)
            {
                Toast.makeText(MainContent.this, "Что-то пошло не так", Toast.LENGTH_SHORT).show();
            }
        });
    }
}