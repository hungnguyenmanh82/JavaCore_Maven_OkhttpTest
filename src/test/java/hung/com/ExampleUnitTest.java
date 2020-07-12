package hung.com;

import com.google.mockwebserver.MockResponse;
import com.google.mockwebserver.MockWebServer;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


import static org.junit.Assert.*;

/**
 *  guide all here:
 *  https://github.com/square/okhttp/wiki/Recipes
 *
 *  dung MockWebserver test rất tiện:
 *   https://www.programcreek.com/java-api-examples/index.php?api=com.google.mockwebserver.MockWebServer
 */
public class ExampleUnitTest {

    @Test
    public void test1_Sync_Get() throws Exception {

        // avoid creating several instances, should be singleon
        OkHttpClient client = new OkHttpClient();

        // url is
        Request request = new Request.Builder()
                .url("http://www.vogella.com/index.html")
                .build();


        System.out.println("====================== request header ====================");
        System.out.println(request.toString());  //show url here
        System.out.println(request.headers().toString()); //error: not show all header for example: url in header here

        //start send request here.
        Response response = client.newCall(request).execute();

        // synchronous receive response here
        // https://en.wikipedia.org/wiki/List_of_HTTP_status_codes
        System.out.println("====================== response code ================== " );
        System.out.println("response code = " + response.code());
        if( response.code() == 200){ //200 = 0k
            //do something here
        }

        if( response.isSuccessful() == true){ //response code = 200 to 300
            //do something here
        }

        if( response.isRedirect()){ //response code = 3xx
            //do something here
        }

        //================================ response header =================
        Headers headers = response.headers();
        Map<String, List<String>> map = headers.toMultimap();
        System.out.println("====================== response header ====================");
        System.out.print(headers.toString());

        //body of response
        String body = response.body().string();
        System.out.print(body);

        assertEquals(4, 2 + 2);
    }

    @Test
    public void test14_Syn_GET_Timeout() throws Exception {

        //=========================== TimeOut ===================================
        OkHttpClient  client = new OkHttpClient().newBuilder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build();

        // url is
        Request request = new Request.Builder()
                .url("http://www.vogella.com/index.html")
                .build();


        System.out.println("====================== request header ====================");
        System.out.println(request.toString());  //show url here
        System.out.println(request.headers().toString()); //error: not show all header for example: url in header here

        //start send request here.
        Response response = client.newCall(request).execute();

        // synchronous receive response here
        // https://en.wikipedia.org/wiki/List_of_HTTP_status_codes
        System.out.println("====================== response code ================== " );
        System.out.println("response code = " + response.code());
        if( response.code() == 200){ //200 = 0k
            //do something here
        }

        if( response.isSuccessful() == true){ //response code = 200 to 300
            //do something here
        }

        if( response.isRedirect()){ //response code = 3xx
            //do something here
        }

        //================================ response header =================
        Headers headers = response.headers();
        Map<String, List<String>> map = headers.toMultimap();
        System.out.println("====================== response header ====================");
        System.out.print(headers.toString());

        assertEquals(4, 2 + 2);
    }


    @Test
    public void test13_Sync_Get_requestHeader() throws Exception {

        // avoid creating several instances, should be singleon
        OkHttpClient client = new OkHttpClient();

        // url is
        Request request = new Request.Builder()
                .url("http://www.vogella.com/index.html")
                .addHeader("content-type","application/xhtml+xml")
                .addHeader("testName","valueName")             //an example:  user definition (not http standard parameter)
                .header("Authorization", "your token")         //an example: authorization (user definition)
                .build();


        System.out.println("====================== request header ====================");
        System.out.println(request.toString());  //show url here
        System.out.println(request.headers().toString()); //error: not show all header for example: url in header here

        //start send request here.
        Response response = client.newCall(request).execute();

        // synchronous receive response here
        // https://en.wikipedia.org/wiki/List_of_HTTP_status_codes
        System.out.println("====================== response code ================== " );
        System.out.println("response code = " + response.code());
        if( response.code() == 200){ //200 = 0k
            //do something here
        }

        if( response.isSuccessful() == true){ //response code = 200 to 300
            //do something here
        }

        if( response.isRedirect()){ //response code = 3xx
            //do something here
        }

        //================================ response header =================
        Headers headers = response.headers();
        Map<String, List<String>> map = headers.toMultimap();
        System.out.println("====================== response header ====================");
        System.out.print(headers.toString());

        assertEquals(4, 2 + 2);
    }

    @Test
    public void test12_Syn_Get_parameters_header() throws Exception {

        // avoid creating several instances, should be singleon
        OkHttpClient client = new OkHttpClient();

        //==================================== build URL of http GET =================
        HttpUrl.Builder urlBuilder = HttpUrl.parse("http://www.vogella.com/index.html").newBuilder()
        										.addQueryParameter("v", "1.0")
        										.addQueryParameter("user", "vogella");
        String url = urlBuilder.build().toString();
        // =============================

        Request request = new Request.Builder()
                .url(url)
                .addHeader("header1", "headerValue1")
                .build();

        System.out.println("====================== request header ====================");
        System.out.println(request.toString());  //show url here
        System.out.println(request.headers().toString()); //error: not show all header for example: url in header here

        //start send request here.
        Response response = client.newCall(request).execute();

        // synchronous receive response here
        // https://en.wikipedia.org/wiki/List_of_HTTP_status_codes
        System.out.println("====================== response code ================== " );
        System.out.println("response code = " + response.code());

        //================================ response header =================
        Headers responseHeaders = response.headers();
        Map<String, List<String>> map = responseHeaders.toMultimap();

        //header of response
        //System.out.print(headers.toString());

        //body of response
        String body = response.body().string();
        System.out.print(body);

        assertEquals(4, 2 + 2);
    }

    @Test
    public void test15_Syn_Get_responseHeader() throws Exception {

        // avoid creating several instances, should be singleon
        OkHttpClient client = new OkHttpClient();

        //==================================== build URL of http GET =================
        HttpUrl.Builder urlBuilder = HttpUrl.parse("https://api.github.help").newBuilder();
        urlBuilder.addQueryParameter("v", "1.0");
        urlBuilder.addQueryParameter("user", "vogella");
        String url = urlBuilder.build().toString();
        // =============================

        Request request = new Request.Builder()
                .url(url)
                .build();

        //start send request here.
        Response response = client.newCall(request).execute();

        //================================ response header =================
        Headers responseHeaders = response.headers();
        Map<String, List<String>> map = responseHeaders.toMultimap();

        System.out.println("======================== response header");
        System.out.println(map.toString());
        System.out.println("======================== response body");

        //header of response
        //System.out.print(headers.toString());

        //body of response
        String body = response.body().string();
        System.out.print(body);

        assertEquals(4, 2 + 2);
    }

    /**
     * use Okhttp asynchronouse with callback function
     */
    @Test
    public void test3_Asyn_Get() throws Exception {

        // avoid creating several instances, should be singleon
        OkHttpClient client = new OkHttpClient();

        // url is
        Request request = new Request.Builder()
                .url("http://www.vogella.com/index.html")
                .build();


        System.out.println("====================== request header ====================");
        System.out.println(request.toString());  //show url here
        System.out.println(request.headers().toString()); //error: not show all header for example: url in header here

        //start send request here.
        client.newCall(request).enqueue(new Callback() {
			public void onResponse(Call call, Response response) throws IOException {
                // synchronous receive response here
                // https://en.wikipedia.org/wiki/List_of_HTTP_status_codes
                System.out.println("====================== response code ================== " );
                System.out.println("response code = " + response.code());

                //================================ response header =================
                Headers responseHeaders = response.headers();
                Map<String, List<String>> map = responseHeaders.toMultimap();

                //header of response
                //System.out.print(headers.toString());

                //body of response
                String body = response.body().string();
                System.out.print(body);
				
			}
			
			public void onFailure(Call call, IOException e) {
				 e.printStackTrace();	
			}
		});

        Thread.sleep(5000); //waiting for Asynchrous worker thread end
        assertEquals(4, 2 + 2);
    }

    /**
     * use Okhttp asynchronouse with callback function
     */
    @Test
    public void test32_Asyn_Get_CancelRequest() throws Exception {

        // avoid creating several instances, should be singleon
        OkHttpClient client = new OkHttpClient();

        // url is
        Request request = new Request.Builder()
                .url("https://github.com/square/okhttp/wiki/Recipes")
                .build();


        System.out.println("====================== request header ====================");
        System.out.println(request.toString());  //show url here
        System.out.println(request.headers().toString()); //error: not show all header for example: url in header here

        //start send request here.
        Call call  = client.newCall(request);

        call.enqueue( new Callback() {
//            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

//            @Override
            public void onResponse(Call call, Response response) throws IOException {
                // synchronous receive response here
                // https://en.wikipedia.org/wiki/List_of_HTTP_status_codes
                System.out.println("====================== response code ================== " );
                System.out.println("response code = " + response.code());

                //================================ response header =================
                Headers responseHeaders = response.headers();
                Map<String, List<String>> map = responseHeaders.toMultimap();

                //header of response
                //System.out.print(headers.toString());

                //body of response
                String body = response.body().string();
                System.out.print(body);
            }
        });

        Thread.sleep(1000);
        //========================================= cancel request ====================
        System.out.print("==============call.cancel();");
        call.cancel();   //will show IOException
        //========================================

        Thread.sleep(5000); //waiting for Asynchrous worker thread end
        assertEquals(4, 2 + 2);
    }

    @Test
    public void test2_Sync_Post_String() throws Exception {

        // avoid creating several instances, should be singleon
        OkHttpClient client = new OkHttpClient();

        //============================== code POST body ================================
        //phần này chèn vào header request, ko phải ở body của request
        // text/x-markdown chính là content-type ở header giống như json thôi, ko có gì
        // Test with WireShare to see the header request: Content-Type: text/x-markdown; charset=utf-8\r\n
        MediaType MEDIA_TYPE_MARKDOWN = MediaType.parse("text/x-markdown; charset=utf-8");

        //phần này làm khó hiểu về http protocol (stupid): jump vào code sẽ rõ
        RequestBody requestBody = RequestBody.create(MEDIA_TYPE_MARKDOWN,"ko co vieec gi kho");
        //================================= end code Post body ==========================


        // url is
        Request request = new Request.Builder()
                .url("http://www.vogella.com/index.html")
                .addHeader("content-type","application/xhtml+xml")
                .addHeader("testName","valueName")             //an example:  user definition (not http standard parameter)
                .header("Authorization", "your token")         //an example: authorization (user definition)
                .post(requestBody)
                .build();


        System.out.println("====================== request header ====================");
        System.out.println(request.toString());  //show url here
        System.out.println(request.headers().toString()); //error: not show all header for example: url in header here

        //start send request here.
        Response response = client.newCall(request).execute();

        // synchronous receive response here
        // https://en.wikipedia.org/wiki/List_of_HTTP_status_codes
        System.out.println("====================== response code ================== " );
        System.out.println("response code = " + response.code());
        if( response.code() == 200){ //200 = 0k
            //do something here
        }

        if( response.isSuccessful() == true){ //response code = 200 to 300
            //do something here
        }

        if( response.isRedirect()){ //response code = 3xx
            //do something here
        }

        //================================ response header =================
        Headers headers = response.headers();
        Map<String, List<String>> map = headers.toMultimap();
        System.out.println("====================== response header ====================");
        System.out.print(headers.toString());

        assertEquals(4, 2 + 2);
    }


    @Test
    public void test23_Sync_Post_file() throws Exception {
        // avoid creating several instances, should be singleon
        OkHttpClient client = new OkHttpClient();

        //============================== code POST body ================================
        //phần này chèn vào header request, ko phải ở body của request
        // text/x-markdown chính là content-type ở header giống như json thôi, ko có gì
        MediaType MEDIA_TYPE_MARKDOWN = MediaType.parse("text/x-markdown; charset=utf-8");

        File file = new File("README.md");

        //phần này làm khó hiểu về http protocol (stupid): jump vào code sẽ rõ
        RequestBody requestBody = RequestBody.create(MEDIA_TYPE_MARKDOWN,file);
        //================================= end code Post body ==========================


        // url is
        Request request = new Request.Builder()
                .url("http://www.vogella.com/index.html")
                .addHeader("content-type","application/xhtml+xml")
                .addHeader("testName","valueName")             //an example:  user definition (not http standard parameter)
                .header("Authorization", "your token")         //an example: authorization (user definition)
                .post(requestBody)
                .build();

        System.out.println("====================== request header ====================");
        System.out.println(request.toString());  //show url here
        System.out.println(request.headers().toString()); //error: not show all header for example: url in header here

        //start send request here.
        Response response = client.newCall(request).execute();

        // synchronous receive response here
        // https://en.wikipedia.org/wiki/List_of_HTTP_status_codes
        System.out.println("====================== response code ================== " );
        System.out.println("response code = " + response.code());
        if( response.code() == 200){ //200 = 0k
            //do something here
        }

        if( response.isSuccessful() == true){ //response code = 200 to 300
            //do something here
        }

        if( response.isRedirect()){ //response code = 3xx
            //do something here
        }

        //================================ response header =================
        Headers headers = response.headers();
        Map<String, List<String>> map = headers.toMultimap();
        System.out.println("====================== response header ====================");
        System.out.print(headers.toString());

        assertEquals(4, 2 + 2);

    }

    /**
     * html Form: là định dạng http protocol chuẩn để người dùng nhập dữ liệu vào gửi lên server
     */
    @Test
    public void test22_Sync_Post_Form_Send_PNG_file() throws Exception {
        // avoid creating several instances, should be singleon
        OkHttpClient client = new OkHttpClient();

        //============================== code POST body ================================
        //phần này chèn vào header request, ko phải ở body của request
        // text/x-markdown chính là content-type ở header giống như json thôi, ko có gì
        MediaType MEDIA_TYPE_PNG = MediaType.parse("image/png");

        File file = new File("README.md");

        //phần này làm khó hiểu về http protocol (stupid): jump vào code creat()
        // Use the imgur image upload API as documented at https://api.imgur.com/endpoints/image
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("title", "Square Logo")
                .addFormDataPart("image", "logo-square.png",
                        RequestBody.create(MEDIA_TYPE_PNG, new File("website/static/logo-square.png")))
                .build();

        //================================= end code Post body ==========================


        // url is
        Request request = new Request.Builder()
                .url("http://www.vogella.com/index.html")
                .addHeader("content-type","application/xhtml+xml")
                .addHeader("testName","valueName")             //an example:  user definition (not http standard parameter)
                .header("Authorization", "your token")         //an example: authorization (user definition)
                .post(requestBody)
                .build();

        System.out.println("====================== request header ====================");
        System.out.println(request.toString());  //show url here
        System.out.println(request.headers().toString()); //error: not show all header for example: url in header here

        //start send request here.
        Response response = client.newCall(request).execute();

        // synchronous receive response here
        // https://en.wikipedia.org/wiki/List_of_HTTP_status_codes
        System.out.println("====================== response code ================== " );
        System.out.println("response code = " + response.code());
        if( response.code() == 200){ //200 = 0k
            //do something here
        }

        if( response.isSuccessful() == true){ //response code = 200 to 300
            //do something here
        }

        if( response.isRedirect()){ //response code = 3xx
            //do something here
        }

        //================================ response header =================
        Headers headers = response.headers();
        Map<String, List<String>> map = headers.toMultimap();
        System.out.println("====================== response header ====================");
        System.out.print(headers.toString());

        assertEquals(4, 2 + 2);

    }
}