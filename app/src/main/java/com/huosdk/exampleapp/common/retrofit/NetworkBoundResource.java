/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.huosdk.exampleapp.common.retrofit;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;

import com.blankj.utilcode.util.AuthCodeUtil;
import com.blankj.utilcode.util.RSAUtils;
import com.google.gson.Gson;
import com.huosdk.exampleapp.BuildConfig;
import com.huosdk.exampleapp.SdkConstant;
import com.huosdk.exampleapp.api.EncryptResource;
import com.huosdk.exampleapp.api.Resource;

import org.json.JSONObject;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;

/**
 * A generic class that can provide a resource backed by both the sqlite database and the network.
 * <p>
 * You can read more about it in the <a href="https://developer.android.com/arch">Architecture
 * Guide</a>.
 * @param <ResultType>
 * @param <RequestType>
 */
public abstract class NetworkBoundResource<ResultType, RequestType> {
    private static final String TAG = NetworkBoundResource.class.getSimpleName();
    public static final int TRANSFORM_ERROR=2000;//默认的转换器转换失败
    private String authkey;
    private final MediatorLiveData<Resource<ResultType>> result = new MediatorLiveData<>();
    public void setAuthKey(String authKey){
        this.authkey=authKey;
    }
    public NetworkBoundResource() {
        LiveData<ApiResponse<RequestType>> netSource = getByNet();
        result.addSource(netSource, new Observer<ApiResponse<RequestType>>() {
            @Override
            public void onChanged(@Nullable ApiResponse<RequestType> requestTypeApiResponse) {
                result.removeSource(netSource);
                Resource<ResultType> data = transform(requestTypeApiResponse.body);
                setValue(data);
            }
        });
    }

    @MainThread
    private void setValue(Resource<ResultType> newValue) {
        result.setValue(newValue);
    }

    @NonNull
    @MainThread
    protected abstract LiveData<ApiResponse<RequestType>> getByNet();
    @NonNull
    @MainThread
    protected  Resource<ResultType> transform(RequestType requestType){
        Resource<ResultType> resourceData=null;
        try {
            if(requestType instanceof EncryptResource){//加密的
                EncryptResource<ResultType> encryptResource= (EncryptResource<ResultType>) requestType;
                if(encryptResource.getData()==null){
                    resourceData=new Resource(encryptResource.getCode(),null,encryptResource.getMsg());
                }else{
                    if(encryptResource.getCode()>=400){
                        resourceData=new Resource(encryptResource.getCode(),null,encryptResource.getMsg());
                    }else if(TextUtils.isEmpty(encryptResource.getData())||"null".equals(encryptResource.getData())){//数据是null的
                        resourceData=new Resource(encryptResource.getCode(),null,encryptResource.getMsg());
                    }
//                    L.d(TAG,"http_result_authkey="+authkey);
                    String decodeAuthData = AuthCodeUtil.authcodeDecode(encryptResource.getData(), authkey);
//                    L.e(TAG,"http_result_authd="+decodeAuthData);
                    //使用
                    JSONObject jsonObject= new JSONObject(decodeAuthData);
                    String sign = jsonObject.optString("sign");
                    String responcedata=jsonObject.optString("responcedata");
                    //验证签名
//                    L.d(TAG,"http_result_rsaKey="+ SdkConstant.RSA_PUBLIC_KEY);
                    Log.e(TAG,"responceData="+responcedata);
                    boolean verify = RSAUtils.verify(responcedata.getBytes(), SdkConstant.RSA_PUBLIC_KEY, sign);
                    if(verify){
                        ResultType dataObject = new Gson().fromJson(responcedata, getTClass());
                        resourceData=new Resource(encryptResource.getCode(),dataObject,encryptResource.getMsg());
                    }else{
                        Log.e(TAG,"sign verify error!,data="+responcedata);
                    }
                }
            }else{
                if(requestType instanceof Resource){
                    resourceData=(Resource<ResultType>) requestType;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(resourceData==null){//默认转换器失败了,需要自己实现
            if(BuildConfig.DEBUG){
                throw  new RuntimeException("default transform is error! should override transform!");
            }else{
                resourceData=new Resource<>(TRANSFORM_ERROR,null,"default transform is error! should override transform!");
            }
        }
        return resourceData;
    }
    private Class<ResultType> getTClass() {
        ParameterizedType type = (ParameterizedType) getClass().getGenericSuperclass();
        Type resultType = type.getActualTypeArguments()[0];
        if (resultType instanceof Class) {
            return (Class<ResultType>) resultType;
        } else {
            // 处理集合
            try {
                Field field = resultType.getClass().getDeclaredField("rawTypeName");
                field.setAccessible(true);
                String rawTypeName = (String) field.get(resultType);
                return (Class<ResultType>) Class.forName(rawTypeName);
            } catch (Exception e) {
                return (Class<ResultType>) Collection.class;
            }
        }
    }
    public LiveData<Resource<ResultType>> asLiveData() {
        return result;
    }
}
