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

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * A generic class that holds a value with its loading status.
 * @param <T>
 */
public class Resource<T> {

    @NonNull
    public final int code;

    @Nullable
    public final String msg;

    @Nullable
    public final T data;

    public Resource(@NonNull int status, @Nullable T data, @Nullable String msg) {
        this.code = status;
        this.data = data;
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "Resource{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
