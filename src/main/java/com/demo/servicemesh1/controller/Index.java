package com.demo.servicemesh1.controller;

import com.demo.servicemesh1.api.RetrofitApiSingleton;
import com.demo.servicemesh1.api.ServiceMesh2Api;
import com.demo.servicemesh1.common.ResponseMessage;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

@RestController
public class Index {

    @Value("${spring.application.name}")
    private String applicationName;

    @GetMapping("/")
    public ResponseMessage index(){
//        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
//        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
//        OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
//                .addInterceptor(logging);
//        Retrofit retrofit = new Retrofit.Builder()
//                //设置数据解析器
//                .addConverterFactory(GsonConverterFactory.create())
//                //设置网络请求的Url地址
//                .baseUrl("http://service-mesh2:8081/service-mesh2/")
//                .client(httpClient.build())
//                .build();
        ServiceMesh2Api service = RetrofitApiSingleton.getServiceMesh2ApiInstance().create(ServiceMesh2Api.class);
        Call<Void> callSync = service.getIndex();
        try {
            Response<Void> response = callSync.execute();
            System.out.println(response.code());
            System.out.println(response.body());
            System.out.println(new Gson().toJson(response.headers()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseMessage.ok(applicationName);
    }
}
