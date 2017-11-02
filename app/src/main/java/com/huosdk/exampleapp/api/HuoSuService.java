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

package com.huosdk.exampleapp.api;

import android.arch.lifecycle.LiveData;

import com.huosdk.exampleapp.common.retrofit.ApiResponse;
import com.huosdk.exampleapp.main.vo.AppInitVO;

import java.util.Map;

import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * REST API access points
 */
public interface HuoSuService {
    @POST("system/appinit")
    @FormUrlEncoded
    LiveData<ApiResponse<EncryptResource<AppInitVO>>> appinit(@FieldMap Map<String,Object> map);

}
