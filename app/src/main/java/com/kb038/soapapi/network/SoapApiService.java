package com.kb038.soapapi.network;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface SoapApiService {
    @GET("/mobileapp/webservices/mobileapp.asmx/AcerCallStatusList")
    @Headers({
            "Content-Type: text/xml; charset=utf-8",
            "SOAPAction: http://tempuri.org/AcerCallStatusList"
    })
    Call<String> getAcerCallStatusList(/*@Body RequestBody body*/);
}
