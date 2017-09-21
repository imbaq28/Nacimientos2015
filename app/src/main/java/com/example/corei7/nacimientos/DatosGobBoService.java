package com.example.corei7.nacimientos;

import com.example.corei7.nacimientos.model.DatosResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Core i7 on 19/09/2017.
 */

public interface DatosGobBoService {
    @GET("action/datastore_search")
    Call<DatosResponse> Nacimientos(@Query("resource_id") String resourceId, @Query("limit") int limit);

    @GET("action/datastore_search")
    Call<DatosResponse> Nacimientos(@Query("resource_id") String resourceId, @Query("q") String query);
}
