package com.common.core.http.net;

import android.support.annotation.NonNull;

import org.json.JSONObject;

import java.io.EOFException;
import java.io.IOException;
import java.nio.charset.Charset;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;

/**
 * @author by wuYang
 * @date 2019/8/8
 * @describe token 过期 请求新的token 并重新请求此接口
 */
public class TokenInterceptor implements Interceptor {
    private static final int tokenExpiredCode = 401000;
    private static final Charset UTF8 = Charset.forName("UTF-8");

    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request request = chain.request();
        Response response = chain.proceed(request);
        if (isTokenExpiredByBody(response)) {//根据和服务端的约定判断token过期
            //同步请求方式，获取最新的Token
            String newSession = getNewToken();
            //使用新的Token，创建新的请求
            Request newRequest = chain.request()
                    .newBuilder()
                    .header("accesstoken", newSession)
                    .build();
            //重新请求
            return chain.proceed(newRequest);
        }
        return response;
    }

    private boolean bodyEncoded(Headers headers) {
        String contentEncoding = headers.get("Content-Encoding");
        return contentEncoding != null && !contentEncoding.equalsIgnoreCase("identity");
    }

    private boolean isPlaintext(Buffer buffer) {
        try {
            Buffer prefix = new Buffer();
            long byteCount = buffer.size() < 64 ? buffer.size() : 64;
            buffer.copyTo(prefix, 0, byteCount);
            for (int i = 0; i < 16; i++) {
                if (prefix.exhausted()) {
                    break;
                }
                int codePoint = prefix.readUtf8CodePoint();
                if (Character.isISOControl(codePoint) && !Character.isWhitespace(codePoint)) {
                    return false;
                }
            }
            return true;
        } catch (EOFException e) {
            return false; // Truncated UTF-8 sequence.
        }
    }

    /**
     * 根据Response的body，判断Token是否失效
     */
    private boolean isTokenExpiredByBody(Response response) {
        ResponseBody responseBody = response.body();
        if (responseBody == null) {
            return false;
        }
        long contentLength = responseBody.contentLength();

        if (!bodyEncoded(response.headers())) {
            BufferedSource source = responseBody.source();
            try {
                source.request(Long.MAX_VALUE); // Buffer the entire body.

                Buffer buffer = source.buffer();

                Charset charset = UTF8;
                MediaType contentType = responseBody.contentType();
                if (contentType != null) {
                    charset = contentType.charset(UTF8);
                }

                if (!isPlaintext(buffer)) {
                    return false;
                }

                if (contentLength != 0) {
                    String result = buffer.clone().readString(charset);
                    //得到所需的string，开始判断是否异常
                    JSONObject object = new JSONObject(result);
                    int code = object.optInt("code");
                    if (code == tokenExpiredCode) {
                        return true;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * 同步请求方式，获取最新的Token
     *
     * @return 最新的Token
     */
    private String getNewToken() throws IOException {
        // 通过一个特定的接口获取新的token，此处要用到同步的retrofit请求，返回新的token

        return "";
    }
}