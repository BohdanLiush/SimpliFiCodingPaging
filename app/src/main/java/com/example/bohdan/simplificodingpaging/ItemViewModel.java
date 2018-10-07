package com.example.bohdan.simplificodingpaging;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PageKeyedDataSource;
import android.arch.paging.PagedList;

public class ItemViewModel extends ViewModel {

    public LiveData<PagedList<Item>> modelPagedList;
    public LiveData<PageKeyedDataSource<Integer,Item>> liveDataSource;

    public ItemViewModel() {
        MyPositionalDataSourceFactory myPositionalDataSourceFactory = new MyPositionalDataSourceFactory();
        liveDataSource = myPositionalDataSourceFactory.getMutableLiveData();


        PagedList.Config pagedListConfig =
                (new PagedList.Config.Builder())
                        .setEnablePlaceholders(false)
                        .setPageSize(MyPagingDatasource.PAGE_SIZE).build();


        modelPagedList = (new LivePagedListBuilder(myPositionalDataSourceFactory, pagedListConfig))
                .build();



    }
}