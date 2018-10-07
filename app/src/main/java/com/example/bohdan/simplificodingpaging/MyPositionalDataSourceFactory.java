package com.example.bohdan.simplificodingpaging;

import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.DataSource;
import android.arch.paging.PageKeyedDataSource;

public class MyPositionalDataSourceFactory extends DataSource.Factory {

    public MutableLiveData<PageKeyedDataSource<Integer, Item>> mutableLiveData = new MutableLiveData();


    @Override
    public DataSource<Integer,Item> create() {

        MyPagingDatasource myPositionalDataSource = new MyPagingDatasource();
        mutableLiveData.postValue(myPositionalDataSource);


        return myPositionalDataSource;
    }

    public MutableLiveData <PageKeyedDataSource<Integer,Item>>getMutableLiveData() {
        return mutableLiveData;
    }
}
