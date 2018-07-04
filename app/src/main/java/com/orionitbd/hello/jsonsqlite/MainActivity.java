package com.orionitbd.hello.jsonsqlite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.orionitbd.hello.jsonsqlite.adapter.PostAdapter;
import com.orionitbd.hello.jsonsqlite.database.DatabaseSource;
import com.orionitbd.hello.jsonsqlite.response.PostResponse;
import com.orionitbd.hello.jsonsqlite.service.PostService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//SqliteOpenHelper ---> create database , version etc;
//SqliteDatabase------> crud operation
//ContentValues-------> insert/update
//Curson--------------> put quary data in cursor

public class MainActivity extends AppCompatActivity {


    private ArrayList<PostResponse> postList = new ArrayList<>();

    private RecyclerView recyclerView;
    private DatabaseSource databaseSource;


    ///user post post
    private List<PostResponse> postDatalist = new ArrayList<>();
    private PostService service;
    private PostAdapter adapter;

    // base url
    public static  final  String  BASE_URL = "https://jsonplaceholder.typicode.com/";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView =findViewById(R.id.recyclerView);
        databaseSource = new DatabaseSource(this);

        // get data from online
        getdataFromJSON();

        showPostFromSqlite();

    }



    public void getdataFromJSON(){
        Log.e("----testing----"," getjsondata called ");
        Retrofit retrofitMovie = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofitMovie.create(PostService.class);
        Call<List<PostResponse>> call = service.getAllPost();
        call.enqueue(new Callback<List<PostResponse>>() {
            @Override
            public void onResponse(Call<List<PostResponse>> call, Response<List<PostResponse>> response) {
                if(response.code() == 200){
                    Log.e("----testing----"," getjsondata successfully ");
                    postDatalist = response.body();
                       saveDataToSqlite();
                       showPostFromSqlite();
                }
            }

            @Override
            public void onFailure(Call<List<PostResponse>> call, Throwable t) {

            }
        });

    }


    public void saveDataToSqlite(){
        Log.e("----testing----"," save data  called ");
        int size = postDatalist.size();

        boolean status = false;
        for (int i= 0;i<size;i++){
             status = databaseSource.insertPost(postDatalist.get(i));
        }
        if(status){
            Log.e("----testing----"," insert data sucessfully  ");
        }
        else{
            Log.e("----testing----"," not insert data ");
        }


    }


    public void showPostFromSqlite(){

        postList = databaseSource.getAllPost();

        if(postList != null && postList.size() != 0){
            adapter = new PostAdapter(this,postList);
            LinearLayoutManager llm = new LinearLayoutManager(MainActivity.this);
            llm.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(llm);
            recyclerView.setAdapter(adapter);
        }

    }
}
