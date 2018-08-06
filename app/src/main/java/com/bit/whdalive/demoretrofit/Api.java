package com.bit.whdalive.demoretrofit;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface Api {

    @GET("hotkey/json")
    Call<HotKey> getHotkey();

    @FormUrlEncoded
    @POST("article/query/{page}/json")
    Call<SearchResult> search(@Path("page") int page, @Field("k") String key);
}
