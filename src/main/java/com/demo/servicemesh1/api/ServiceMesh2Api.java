package com.demo.servicemesh1.api;


import retrofit2.Call;
import retrofit2.http.GET;

public interface ServiceMesh2Api {
    @GET("/service-mesh2/")
    Call<Void> getIndex();
}
