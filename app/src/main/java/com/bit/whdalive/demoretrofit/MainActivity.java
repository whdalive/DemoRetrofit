package com.bit.whdalive.demoretrofit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        request();
    }

    public void request(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.wanandroid.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api request = retrofit.create(Api.class);

        Call<HotKey> call = request.getHotkey();

        call.enqueue(new Callback<HotKey>() {
            @Override
            public void onResponse(Call<HotKey> call, Response<HotKey> response) {
                for(HotKey.DataBean dataBean : response.body().getData()){
                    Log.d(TAG, "id: "+ dataBean.getId()
                            +"; link: "+ dataBean.getLink()
                            +"; name: "+ dataBean.getName()
                            +"; order: "+ dataBean.getOrder()
                            +"; visible: "+ dataBean.getVisible());

                }
            }

            @Override
            public void onFailure(Call<HotKey> call, Throwable t) {
                Log.d(TAG, "onFailure: 连接失败");
            }
        });

        Call<SearchResult> call2 = request.search(0,"面试");
        call2.enqueue(new Callback<SearchResult>() {
            @Override
            public void onResponse(Call<SearchResult> call, Response<SearchResult> response) {
                for (SearchResult.DataBean.DatasBean dataBean:response.body().getData().getDatas()){
                    Log.d(TAG, "author: "+ dataBean.getAuthor()
                            +"; chapterName: "+ dataBean.getChapterName()
                            +"; title: "+ dataBean.getTitle());
                }
            }

            @Override
            public void onFailure(Call<SearchResult> call, Throwable t) {
                Log.d(TAG, "onFailure: 连接失败");
            }
        });

    }
}
