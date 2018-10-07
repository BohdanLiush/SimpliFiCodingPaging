package com.example.bohdan.simplificodingpaging;

import android.arch.paging.PageKeyedDataSource;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyPagingDatasource extends PageKeyedDataSource<Integer,Item> {

    public final String URL = "https://content.guardianapis.com/";

    public Item model;
    List<Item> models = new ArrayList<>();
    public Call<StackApiResponse> idsCall;

    public static final int PAGE_SIZE = 10;

    //we will start from the first page which is 1
    private static final int FIRST_PAGE = 1;
    private static final String SITE_NAME = "stackoverflow";


    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull final LoadInitialCallback<Integer, Item> callback) {

        RetrofitClient.getInstance().getApi().getAnswers(FIRST_PAGE, PAGE_SIZE, SITE_NAME)
                .enqueue(new Callback<StackApiResponse>() {
                    @Override
                    public void onResponse(Call<StackApiResponse> call, Response<StackApiResponse> response) {
                        if (response.body() != null) {
                            callback.onResult(response.body().getItems(), null, FIRST_PAGE + 1);
                        }
                    }

                    @Override
                    public void onFailure(Call<StackApiResponse> call, Throwable t) {

                    }
                });


        /*
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api idsApi = retrofit.create(Api.class);
        idsCall = idsApi.getAnswers(FIRST_PAGE,PAGE_SIZE,SITE_NAME);*/



        /*try {
            models = idsCall.execute().body().getItems();
            callback.onResult(models,  null,FIRST_PAGE+1);
        } catch (IOException e) {
            e.printStackTrace();
        }*/

    }

    @Override
    public void loadBefore(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, Item> callback) {

        RetrofitClient.getInstance().getApi().getAnswers(params.key,PAGE_SIZE,SITE_NAME).enqueue(new Callback<StackApiResponse>() {
            @Override
            public void onResponse(Call<StackApiResponse> call, Response<StackApiResponse> response) {
                Integer adjacentKey = (params.key > 1) ? params.key - 1 : null;
                if (response.body()!=null){
                    callback.onResult(response.body().getItems(),adjacentKey);
                }
            }

            @Override
            public void onFailure(Call<StackApiResponse> call, Throwable t) {

            }
        });


        /*Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api idsApi = retrofit.create(Api.class);
        idsCall = idsApi.getAnswers(FIRST_PAGE,PAGE_SIZE,SITE_NAME);

        final Integer adjacentKey = (params.key > 1) ? params.key - 1 : null;*/

//        try {
//            models = idsCall.execute().body().getItems();
//            callback.onResult(models,  adjacentKey);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


        /*idsCall.enqueue(new Callback<StackApiResponse>() {
            @Override
            public void onResponse(Call<StackApiResponse> call, Response<StackApiResponse> response) {

                if (response.body() != null) {
                    //models = response.body().getItems();
                    callback.onResult(response.body().getItems(),adjacentKey);
                }
            }
            @Override
            public void onFailure(Call<StackApiResponse> call, Throwable t) {

            }
        });*/

    }

    @Override
    public void loadAfter(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, Item> callback) {


        RetrofitClient.getInstance().getApi().getAnswers(params.key,PAGE_SIZE,SITE_NAME).enqueue(new Callback<StackApiResponse>() {
            @Override
            public void onResponse(Call<StackApiResponse> call, Response<StackApiResponse> response) {
                if (response.body()!=null){
                    Integer key = response.body().getHasMore() ? params.key + 1 : null;
                    callback.onResult(response.body().getItems(),key);
                }
            }

            @Override
            public void onFailure(Call<StackApiResponse> call, Throwable t) {

            }
        });


        /*Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api idsApi = retrofit.create(Api.class);
        idsCall = idsApi.getAnswers(FIRST_PAGE,PAGE_SIZE,SITE_NAME);*/

       /* try {
            Integer key = idsCall.execute().body().getHasMore() ? params.key + 1 : null;

            models = idsCall.execute().body().getItems();
            callback.onResult(models,  key);
        } catch (IOException e) {
            e.printStackTrace();
        }*/


        /*idsCall.enqueue(new Callback<StackApiResponse>() {
            @Override
            public void onResponse(Call<StackApiResponse> call, Response<StackApiResponse> response) {
                Integer key = response.body().getHasMore() ? params.key + 1 : null;

                if (response.body() != null) {
                    //models = response.body().getItems();
                    callback.onResult(response.body().getItems(),key);
                }
            }
            @Override
            public void onFailure(Call<StackApiResponse> call, Throwable t) {

            }
        });*/

    }
}
