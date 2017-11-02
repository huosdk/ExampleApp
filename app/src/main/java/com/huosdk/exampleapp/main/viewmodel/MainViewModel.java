package com.huosdk.exampleapp.main.viewmodel;

import android.app.Application;
import android.arch.core.util.Function;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.support.annotation.NonNull;

import com.huosdk.exampleapp.api.Resource;
import com.huosdk.exampleapp.main.repository.MainRepository;
import com.huosdk.exampleapp.main.vo.AppInitVO;

/**
 * Created by liuhongliang on 2017/10/31.
 */

public class MainViewModel extends AndroidViewModel {
    private final MutableLiveData mutableLiveData=new MutableLiveData();
    private LiveData<Resource<AppInitVO>> appinit;
    public MainRepository mainRepository=new MainRepository();

    public MainViewModel(@NonNull Application application) {
        super(application);
        appinit=Transformations.switchMap(mutableLiveData, new Function<Object, LiveData<Resource<AppInitVO>>>() {
            @Override
            public LiveData<Resource<AppInitVO>> apply(Object input) {
                return mainRepository.appinit();
            }
        });
    }
    public  LiveData<Resource<AppInitVO>> appinit(){
        return appinit;
    }

    public void refresh(){
        mutableLiveData.setValue(null);
    }
}
