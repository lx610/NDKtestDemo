package test.ndk.ndktestmodule.http.Okhttp;

import android.text.TextUtils;
import android.util.Log;

import me.jessyan.retrofiturlmanager.parser.UrlParser;
import okhttp3.HttpUrl;

/**
 * 类 名: ElkUrlPareser
 * 说 明:
 * 修 改 记 录:
 * 版 权 所 有:   Copyright  2016
 * 公       司:   深圳市旅联网络科技有限公司
 * version   0.1
 * date   2018/1/16
 * author lixuan
 */

public class ElkUrlPareser implements UrlParser {
    private static final String TAG = "ElkUrlPareser";
    @Override
    public HttpUrl parseUrl(HttpUrl domainUrl, HttpUrl url) {


        // 如果 HttpUrl.parse(url); 解析为 null 说明,url 格式不正确,正确的格式为 "https://github.com:443"
        // http 默认端口 80,https 默认端口 443 ,如果端口号是默认端口号就可以将 ":443" 去掉
        // 只支持 http 和 https
//        Log.d(TAG, "parseUrl:  "+      " domainUrl.scheme(): = " +  domainUrl.scheme());
//        Log.d(TAG, "parseUrl:  "+   " domainUrl.host(): = " +  domainUrl.host ());
//        Log.d(TAG, "parseUrl:  "+   " domainUrl.port(): = " +  domainUrl.port());
//        Log.d(TAG, "parseUrl:  "+   " domainUrl.query(): = " +  domainUrl.query());
//        Log.d(TAG, "parseUrl:  "+   " domainUrl.encodedPath(): = " +  domainUrl.encodedPath());
//        Log.d(TAG, "parseUrl:  "+   " domainUrl.encodedFragment(): = " +  domainUrl.encodedFragment());
//        Log.d(TAG, "parseUrl:  "+   " domainUrl.redact(): = " +  domainUrl.redact());
//        Log.d(TAG, "parseUrl:  "+   " domainUrl.username(): = " +  domainUrl.username());
//        Log.d(TAG, "parseUrl:  "+   " domainUrl.encodedPassword(): = " +  domainUrl.encodedPassword());
//        Log.d(TAG, "parseUrl:  "+   " domainUrl.encodedQuery(): = " +  domainUrl.encodedQuery());

        if (null == domainUrl) return url;

//        Log.d(TAG, "parseUrl: " + url.newBuilder()
//                .scheme(domainUrl.scheme())
//                .host(domainUrl.host())
//                .port(domainUrl.port())
//                .build().toString());
//
//        Log.d(TAG, "parseUrl: addEncodedPathSegment" + url.newBuilder()
//                .scheme(domainUrl.scheme())
//                .host(domainUrl.host())
//                .port(domainUrl.port())
//                .addEncodedPathSegment(domainUrl.encodedPath())
//                .build().toString());
//
//        Log.d(TAG, "parseUrl: addPathSegment" + url.newBuilder()
//                .scheme(domainUrl.scheme())
//                .host(domainUrl.host())
//                .port(domainUrl.port())
//                .addPathSegment(domainUrl.encodedPath())
//                .build().toString());
//
//        Log.d(TAG, "parseUrl: encodedPath" + url.newBuilder()
//                .scheme(domainUrl.scheme())
//                .host(domainUrl.host())
//                .port(domainUrl.port())
//                .encodedPath(domainUrl.encodedPath())
//                .build().toString());
//
//        Log.d(TAG, "parseUrl: setEncodedPathSegment" + url.newBuilder()
//                .scheme(domainUrl.scheme())
//                .host(domainUrl.host())
//                .port(domainUrl.port())
//                .setEncodedPathSegment(0, domainUrl.encodedPath().replace("/",""))
//                .build().toString());
//
//        Log.d(TAG, "parseUrl: setEncodedPathSegment" + url.newBuilder()
//                .scheme(domainUrl.scheme())
//                .host(domainUrl.host())
//                .port(domainUrl.port())
//                .password(domainUrl.encodedPath())
//                .build().toString());
        HttpUrl.Builder builder = url.newBuilder();
        Log.d(TAG, "parseUrl: " + url.encodedPath());
        Log.d(TAG, "parseUrl: " + url.encodedQuery());
        Log.d(TAG, "parseUrl: " + url.encodedPathSegments());
        if (TextUtils.isEmpty(domainUrl.encodedPath().replace("/",""))){
            builder .scheme(domainUrl.scheme())
                    .host(domainUrl.host())
                    .port(domainUrl.port())
//                     .setEncodedPathSegment()
                    .setPathSegment(0, domainUrl.encodedPath().replace("/",""))
                    .encodedPath(url.encodedPath());
        }else {
            builder .scheme(domainUrl.scheme())
                    .host(domainUrl.host())
                    .port(domainUrl.port())
                    .encodedPath(domainUrl.encodedPath() + url.encodedPath().substring(1))
                  ;
        }
        return builder
                .build();
    }
}
