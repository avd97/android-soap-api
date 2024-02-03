package com.kb038.soapapi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.util.Log;

import com.kb038.soapapi.databinding.ActivityMainBinding;
import com.kb038.soapapi.network.RetrofitClient;
import com.kb038.soapapi.network.SoapApiService;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding mBinding;
    private String TAG = "mAiNaCtIvItY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        getXMLApiResponse();
    }

    void getXMLApiResponse() {
        try {
            SoapApiService service = RetrofitClient.getClient().create(SoapApiService.class);
            String rawSoapRequest = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:tem=\"http://tempuri.org/\">"
                    + "<soapenv:Header/>"
                    + "<soapenv:Body>"
                    + "<tem:AcerCallStatusList/>" // Add any required parameters here
                    + "</soapenv:Body>"
                    + "</soapenv:Envelope>";

            RequestBody requestBody = RequestBody.create(MediaType.parse("text/xml"), rawSoapRequest);

            service.getAcerCallStatusList(/*requestBody*/).enqueue(new Callback<String>() {
                @Override
                public void onResponse(@NonNull Call<String> call, @NonNull Response<String> response) {
                    String xmlResponse = "initial";
                    if (response.isSuccessful()) {
                        xmlResponse = response.body();
                        Log.w(TAG, "onResponse: "+xmlResponse);
                    } else {
                        xmlResponse = "FaIlEd";
                        Log.e(TAG, "onResponse: "+xmlResponse);
                    }
                    mBinding.tvMessage.setText(String.format("%s\n---", xmlResponse));
                }

                @Override
                public void onFailure(@NonNull Call<String> call, @NonNull Throwable t) {
                    mBinding.tvMessage.setText(String.format("onFailure\n%s\n---", t.getMessage()));
                    t.printStackTrace();
                }
            });
        } catch (Exception e) {
            mBinding.tvMessage.setText(String.format("%s\n", e.getMessage()));
            e.printStackTrace();
        }
    }
}