package com.kb038.soapapi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.util.Log;

import com.kb038.soapapi.databinding.ActivityMainBinding;
import com.kb038.soapapi.model.AcerCallStatus;
import com.kb038.soapapi.network.RetrofitClient;
import com.kb038.soapapi.network.SoapApiService;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

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

//        getXMLApiResponse();

        mBinding.btnCallApi.setOnClickListener(v -> getXMLApiResponse());
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
                        Log.w(TAG, "onResponse: " + xmlResponse);
//                        parseXMLResponse(xmlResponse);

                        if(xmlResponse != null && !xmlResponse.equals("")) {
                            parseXMLResponse(xmlResponse);
                        } else {
                            Log.d(TAG, "onResponse empty: ");
                        }
                    } else {
                        xmlResponse = "FaIlEd";
                        Log.e(TAG, "empty response: " + xmlResponse);
                    }
//                    mBinding.tvMessage.setText(String.format("%s\n---", xmlResponse));
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

    void parseXMLResponse(String xmlResponse) {
        try {
            // Initialize XmlPullParser
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = factory.newPullParser();

            // Set the input for the parser using an InputStream or a Reader
            // Here, we use a StringReader to convert your String XML into a Reader
            parser.setInput(new StringReader(xmlResponse));

            // Variables for storing the current event type and data you retrieve while parsing
            int eventType = parser.getEventType();
            ArrayList<AcerCallStatus> statuses = new ArrayList<>();
            AcerCallStatus currentStatus = null;

            // Loop through the XML document until the document end is reached
            while (eventType != XmlPullParser.END_DOCUMENT) {
                String tagName = null;

                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        tagName = parser.getName();
                        Log.i(TAG, "tagName: " + tagName);
                        // Check for the tag that starts an AcerCallStatus element
                        if ("AcerCallStatus".equals(tagName)) {
                            currentStatus = new AcerCallStatus();
                        } else if (currentStatus != null) {
                            // Check for tags of interest and set
                            // the corresponding data on currentStatus
                            if ("Id".equals(tagName)) {
                                currentStatus.setId(Integer.parseInt(parser.nextText()));
                            } else if ("StatusName".equals(tagName)) {
                                currentStatus.setStatusName(parser.nextText());
                            } else if ("ResponseCode".equals(tagName)) {
                                currentStatus.setResponseCode(parser.nextText());
                            }
                        }
                        break;

                    case XmlPullParser.END_TAG:
                        tagName = parser.getName();
                        // When closing tag of AcerCallStatus is found, add the object to the list
                        if ("AcerCallStatus".equals(tagName) && currentStatus != null) {
                            statuses.add(currentStatus);
                            currentStatus = null; // Reset for the next entry
                        }
                        break;
                }

                eventType = parser.next(); // Move to the next event
            }

            // At this point, 'statuses' contains all parsed data
            StringBuilder acerData = new StringBuilder();
            for (AcerCallStatus acerCallStatus : statuses) {
                acerData.append(acerCallStatus.getId()).append(") ")
                        .append(acerCallStatus.getStatusName()).append(" => ")
                        .append(acerCallStatus.getResponseCode()).append("\n");
            }
            mBinding.tvMessage.setText(acerData.toString());
        } catch (XmlPullParserException | IOException e) {
            e.printStackTrace();
        }

    }
}