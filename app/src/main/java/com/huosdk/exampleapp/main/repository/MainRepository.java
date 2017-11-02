package com.huosdk.exampleapp.main.repository;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.huosdk.exampleapp.api.EncryptResource;
import com.huosdk.exampleapp.api.HuoSuService;
import com.huosdk.exampleapp.api.Resource;
import com.huosdk.exampleapp.common.retrofit.ApiResponse;
import com.huosdk.exampleapp.common.retrofit.HttpParamsBuild;
import com.huosdk.exampleapp.common.retrofit.NetworkBoundResource;
import com.huosdk.exampleapp.common.retrofit.RetrofitClient;
import com.huosdk.exampleapp.main.vo.AppInitVO;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by liuhongliang on 2017/11/1.
 */

public class MainRepository {

    private String key="fsHUaCRFu3ZCCIfaL4Ppc4GxOGM0k8jkyyYXp0nM9o9sdGViOjZGn5yGCFsZpRI0acYjWj+QgLEBfXdaTMAOZE/gU958wixdBNHM9GHzNNOMMpx4HPuoBFjK0mO26AreQewyyuiiaeSLuT0aMYk22OxXedjWo9YgWFTGCuHbOVA=";
    private String data="u5ukJ09zGx5IC2Y0DgQLWnt5Bwt5RhYRDVMlIld5F1hbdXhiKV8RbT8FZgNXdzIZQiBzVmc6CHUFA2gnbi0IfUN6SDEZdWBlXQ9LfiZzXgseU2tSXAQ4CFsTQSFqWmAKSHA5Jm1rTEItHhIqTBEEPkcpehs1CGRzKVYsMVwQPk8LKmcyXT96FTIEZxROJFslX0I9djRBFklWTQoCPwpCdSVsd0o8Vl9bezpcLVxGHnAETwY5FSYeLxVme05WWxURAjpsUjp+S0pQFVwuICtacRJxAEYfNVA3ez9RMW8YAwsScgBQE1MMY2l/cRlKOHRmdhFaJm5eaHlsDjVlOwcXIiRjTCIeCDYgDRQyOgdrMVEcC2xAZHQiAnRPWSkYU2NqCFYbJHI/Q39xXU8yLl0oSjtvGhgMTGETOhhWVRB3U1N9aFBDL2wVImBkJ0McNnwVRWRyWAdKLSZiDCQqPz5CdgVAXWhhTT92QkwtM3ZvOmNSa3NaNDYqJX4xHWJjMHcDfwgdeR4sZQBhGCh9VEInHww7RhAta3lic3FPeit1KVsCSHoYfC9LH31xOlQFMzkaWEZINVVdXG9BAiY2RHYIE30PGH9GeyVlJRhJOR83e0l7d3ktdld8Ph1KWlZ6P0AsTF8WcA9ZZk5hIEV5Tk8pUkBpAkZ+Sk04VgEuKSJAGHxYVCseTSZQaxJWIWtIRUV8ag1ABGAI";

    private String authKey="d45af82cc5109f17a0b81a2106ad7296ZJHMpr9TljE3x1zJ";
    public LiveData<Resource<AppInitVO>> appinit() {
        return new NetworkBoundResource<AppInitVO,EncryptResource<AppInitVO>>(){
            @NonNull
            @Override
            protected LiveData<ApiResponse<EncryptResource<AppInitVO>>> getByNet() {
                HuoSuService huoSuService = RetrofitClient.getRetrofit().create(HuoSuService.class);
                HttpParamsBuild paramsBuild=new HttpParamsBuild("");
                setAuthKey(authKey);
                Map<String,Object> map=new HashMap<>();
                map.put("key",key);
                map.put("data",data);
                return huoSuService.appinit(map);
            }
        }.asLiveData();
    }
}
