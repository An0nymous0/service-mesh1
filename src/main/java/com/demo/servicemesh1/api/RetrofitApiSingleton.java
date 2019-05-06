package com.demo.servicemesh1.api;

import lombok.Getter;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitApiSingleton {

    private RetrofitApiSingleton() {
    }

    public static Retrofit getServiceMesh2ApiInstance() {
        return ServiceMesh2ApiEnum.INSTANCE.getInstance();
    }

    @Getter
    private enum ServiceMesh2ApiEnum {
        INSTANCE;
        private Retrofit retrofit;

        ServiceMesh2ApiEnum() {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
                    .addInterceptor(logging);
            this.retrofit = new Retrofit.Builder()
                    //设置数据解析器
                    .addConverterFactory(GsonConverterFactory.create())
                    //设置网络请求的Url地址
                    .baseUrl("http://service-mesh2:8081/service-mesh2/")
                    .client(httpClient.build())
                    .build();
        }

        private Retrofit getInstance() {
            return this.retrofit;
        }
    }
}
