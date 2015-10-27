package com.carstenscholz.robospicemoa;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpHeaders;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.json.jackson.JacksonFactory;
import com.octo.android.robospice.request.googlehttpclient.GoogleHttpClientSpiceRequest;

/**
 * Created by Carsten on 9/10/2015.
 */
public class MoaRequest extends GoogleHttpClientSpiceRequest<MoaObject>{

    private String baseUrl = "http://testing.moacreative.com/job_interview/event.php";

    public MoaRequest() {
        super(MoaObject.class);
    }


    @Override
    public MoaObject loadDataFromNetwork() throws Exception {

        //Using the Get Method
        HttpRequest request = getHttpRequestFactory().buildGetRequest(new GenericUrl(baseUrl));

        //Using the Post Method
        /*MoaObject moa = new MoaObject();
        JsonHttpContent content = new JsonHttpContent(new JacksonFactory(),moa);
        HttpRequest request = getHttpRequestFactory().buildPostRequest(new GenericUrl(baseUrl),content);*/

        //Setting headers
        HttpHeaders headers = new HttpHeaders();
        headers.setUserAgent("MyTestClient : X-Signiture=1Uhi8g9A91");
        headers.set("X-Key", "697381b065bbfe4a714cd14cf394978e");
        headers.setContentType("application/json");

        //Adding Headers to the HTTP Request
        request.setHeaders(headers);
        request.setParser(new JacksonFactory().createJsonObjectParser());

        return request.execute().parseAs(getResultType());
    }
}
