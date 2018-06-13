package test.ndk.ndktestmodule.http.Okhttp;

import android.os.Environment;
import android.view.View;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.concurrent.TimeUnit;

import me.jessyan.retrofiturlmanager.RetrofitUrlManager;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.POST;
import test.ndk.basecommonmodule.base.BaseLinearActivity;
import timber.log.Timber;

/**
 * Created by Administrator on 2018/6/13.
 */

public class OkhttpTestActivty extends BaseLinearActivity {
    private static final String TAG = "OkhttpTestActivty";

    public static final int HTTP_RESPONSE_DISK_CACHE_MAX_SIZE = 10 * 1024 * 1024;//缓存文件最大值为10Mb
    private OkHttpClient ohttpClient;
    private Retrofit retrofit;

    @Override
    public void initView() {
        mLlRoot.addView(generateTextButton("http://192.168.0.59:8081/elk-tourist-develop/", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Request.Builder builder = new Request.Builder().url("http://192.168.0.59:8081/experience/detail_2_2_0 ");
                Request requst = builder.build();
//                Call call =ohttpClient.newCall(requst);
//                call.enqueue(new Callback() {
//                    @Override
//                    public void onFailure(Call call, IOException e) {
//
//                    }
//
//                    @Override
//                    public void onResponse(Call call, Response response) throws IOException {
//                        Timber.d(response.body().string());
//                    }
//                });
            }
        }));



    }

    @Override
    public void initData() {
      ohttpClient =  initOkHttp();
              retrofit=      initRetrofit();
    }



    private OkHttpClient initOkHttp() {
        String cachePath =  Environment.getDownloadCacheDirectory() + "/OkhttpCach";
        File cachFile =new File(cachePath);
        Cache cache = new Cache(cachFile,HTTP_RESPONSE_DISK_CACHE_MAX_SIZE);
        OkHttpClient.Builder build = new OkHttpClient.Builder();

        RequestIntercept intercept = new RequestIntercept(new RequestIntercept.RequestHandler() {
            @Override
            public Request onHttpRequestBefore(Interceptor.Chain chain, Request request) {

                return null;
            }

            @Override
            public Response onHttpResultResponse(String responseBodyString, Interceptor.Chain chain, Response response) {
                String contentType = response.headers().get("Content-Type");
                if (contentType.startsWith("application/json")) {

                    JSONObject jsonObject;
                    try {
                        jsonObject = new JSONObject(responseBodyString);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }


                return response;
            }
        });

        build.connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30,TimeUnit.SECONDS)
                .addNetworkInterceptor(intercept)
                .cache(cache);

        RetrofitUrlManager.getInstance().setUrlParser(new ElkUrlPareser());
            RetrofitUrlManager.getInstance().setDebug(true);
       return RetrofitUrlManager.getInstance().with(build)
                .build();
    }

    private Retrofit initRetrofit() {
        Retrofit.Builder builder =new Retrofit.Builder();
      return  builder.baseUrl("http://192.168.0.59:8081/")
//              .addConverterFactory(GsonConverterFactory.create())
                .client(ohttpClient)//设置okhttp
                .build();

    }

    @Override
    public void initListener() {
        final Service service = retrofit.create(Service.class);



        mLlRoot.addView(generateTextButton("http://192.168.0.59:8081/elk-tourist-develop/", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RetrofitUrlManager.getInstance().setGlobalDomain("http://192.168.0.59:8081/elk-tourist-develop/");
            }
        }));

        mLlRoot.addView(generateTextButton("http://192.168.0.59:8081/", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RetrofitUrlManager.getInstance().setGlobalDomain("http://192.168.0.59:8081/");
            }
        }));

        mLlRoot.addView(generateTextButton("request home", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call call = service.getHomePageData2_0_0();
                call.enqueue(new retrofit2.Callback() {
                    @Override
                    public void onResponse(Call call, retrofit2.Response response) {
                        Timber.d(response.body().toString());
                    }

                    @Override
                    public void onFailure(Call call, Throwable t) {

                    }
                });
            }
        }));
    }

    interface Service{
        /**
         * @desc 首页列表
         */

        @POST("question/list_2_0_0 ")
        Call<ResponseBody> getHomePageData2_0_0(
//            @Header("Cache-Control") String cacheControl,
        );
    }
}
