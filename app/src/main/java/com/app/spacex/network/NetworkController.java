package com.app.spacex.network;

import com.app.spacex.model.Launch;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by sinansondas on 08/02/18.
 */

public class NetworkController {
    private SpaceXAPI spaceXAPI;

    public NetworkController(String baseUrl, boolean enableLogging) {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .setLenient()
                .create();

        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
        if (enableLogging) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            clientBuilder.addInterceptor(logging);
        }

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(clientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        spaceXAPI = retrofit.create(SpaceXAPI.class);
    }

    private <T> void sendRequest(final Call<T> call, final RequestListener<T> requestListener) {
        Callback<T> callback = new Callback<T>() {
            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                if (response.isSuccessful() && response.body() != null) {
                    requestListener.onSucceed(response.body());
                } else {
                    if (response.errorBody() != null) {
                        String errorMessage;
                        try {
                            errorMessage = response.errorBody().string();
                        } catch (IOException e) {
                            errorMessage = "Request failed: " + String.valueOf(response.code());
                            e.printStackTrace();
                        }

                        Throwable throwableResponse = new Throwable(errorMessage);
                        requestListener.onFailed(throwableResponse);
                    }
                }
            }

            @Override
            public void onFailure(Call<T> call, Throwable throwable) {
                requestListener.onFailed(throwable);
            }
        };

        call.enqueue(callback);
    }

    public void loadLaunches(RequestListener<List<Launch>> requestListener) {
        Call<List<Launch>> call = spaceXAPI.loadLaunches();
        sendRequest(call, requestListener);
    }
}
