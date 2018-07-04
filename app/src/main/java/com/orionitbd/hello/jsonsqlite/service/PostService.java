package com.orionitbd.hello.jsonsqlite.service;

import com.orionitbd.hello.jsonsqlite.response.PostResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PostService {

    @GET("posts")
    Call<List<PostResponse>> getAllPost();
}
