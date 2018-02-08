package com.app.spacex.network;

import com.app.spacex.model.Launch;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by sinansondas on 08/02/18.
 */

public interface SpaceXAPI {
    @GET("launches/")
    Call<List<Launch>> loadLaunches();
}