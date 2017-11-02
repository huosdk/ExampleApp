package com.huosdk.exampleapp.main;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.huosdk.exampleapp.R;
import com.huosdk.exampleapp.api.Resource;
import com.huosdk.exampleapp.main.viewmodel.MainViewModel;
import com.huosdk.exampleapp.main.vo.AppInitVO;

/**
 * Created by liuhongliang on 2017/10/31.
 */

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MainViewModel mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        mainViewModel.appinit().observe(this, new Observer<Resource<AppInitVO>>() {
            @Override
            public void onChanged(@Nullable Resource<AppInitVO> appInitVOResource) {
                Log.e("hongliang", "code=" + appInitVOResource.code);
                Log.e("hongliang", "body=" + appInitVOResource.data);
                Log.e("hongliang", "message=" + appInitVOResource.msg);
            }
        });
        mainViewModel.refresh();
        findViewById(R.id.btn_refresh).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainViewModel.refresh();
            }
        });

    }
}
