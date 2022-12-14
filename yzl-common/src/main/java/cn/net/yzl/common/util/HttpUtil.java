package cn.net.yzl.common.util;

import com.alibaba.fastjson.JSON;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.image.BufferedImage;
import java.io.*;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.output.ByteArrayOutputStream;

public class HttpUtil {

    private static final Logger logger = LoggerFactory.getLogger(HttpUtil.class);
    private static CloseableHttpClient httpClient;
    private static PoolingHttpClientConnectionManager cm;

    static {
        init();
        closeExpiredConnectionsPeriodTask(60);
    }

    static void init() {
        cm = new PoolingHttpClientConnectionManager();
        // max connections
        cm.setMaxTotal(300);
        // max connections per route
        cm.setDefaultMaxPerRoute(60);
        // set max connections for a specified route
        cm.setMaxPerRoute(new HttpRoute(new HttpHost("locahost")), 100);

        final RequestConfig requestConfig = RequestConfig.custom()
                // the socket timeout (SO_TIMEOUT) in milliseconds
                .setSocketTimeout(4000)
                // the timeout in milliseconds until a connection is established.
                .setConnectTimeout(4000)
                // the timeout in milliseconds used when requesting a connection from the connection pool.
                .setConnectionRequestTimeout(1500)
                .build();
        httpClient = HttpClients.custom().setConnectionManager(cm).setDefaultRequestConfig(requestConfig).build();
    }

    public static String sendGetRequest(String url, Map<String, String> paramMap) {
        return sendGetRequestSetEncoding(url, paramMap, "UTF-8");
    }

    public static String sendGetRequest(String url, Map<String, String> paramMap,Map<String,String> headers) {
        return sendGetRequestSetEncoding(url, paramMap, "UTF-8",headers);
    }

    /**
     * ????????????
     * ???????????????Get????????????
     *
     * @author ???guowang
     * @date ???2019/8/7 15:55
     */
    public static String sendGetRequest(String url) {
        String requestUrl = url;
        HttpGet httpGet = new HttpGet(requestUrl);
        httpGet.setHeader("User-Agent", "");
        HttpResponse response;

        String startTime = null;
        try {
            startTime = DateHelper.getCurrentDateStr("yyyy-MM-dd HH:mm:ss");
            response = httpClient.execute(httpGet);
            return parseResponse(requestUrl, response, "UTF-8");
        } catch (ConnectTimeoutException e) {
           // logger.error("request url:" + requestUrl + "startTime:" + startTime + ",ConnectTimeout", e);
            return null;
        } catch (SocketTimeoutException e) {
            logger.error("request url:" + requestUrl + "startTime:" + startTime + ",SocketTimeout", e);
            return null;
        } catch (Exception e) {
            logger.error("request url:" + requestUrl + ",error", e);
            return null;
        }
    }

    public static String sendGetRequestSetEncoding(String url, Map<String, String> paramMap, String encoding) {
        StringBuilder urlBuffer = new StringBuilder(url);
        if (url.indexOf("?") < 0) {
            urlBuffer.append("?");
        }
        if (paramMap != null && !paramMap.isEmpty()) {
            Iterator<String> iterator = paramMap.keySet().iterator();
            while (iterator.hasNext()) {
                String paramName = iterator.next();
                if (urlBuffer.toString().endsWith("?")) {
                    urlBuffer.append(paramName).append("=").append(paramMap.get(paramName).toString());
                } else {
                    urlBuffer.append("&").append(paramName).append("=").append(paramMap.get(paramName).toString());
                }
            }
        }
        String requestUrl = urlBuffer.toString();
        HttpGet httpGet = new HttpGet(requestUrl);
        httpGet.setHeader("User-Agent", "");
        HttpResponse response;
        String startTime = null;
        try {
            startTime = DateHelper.getCurrentDateStr("yyyy-MM-dd HH:mm:ss");
            response = httpClient.execute(httpGet);
            return parseResponse(requestUrl, response, encoding);
        } catch (ConnectTimeoutException e) {
            logger.error("request url:" + requestUrl + "startTime:" + startTime + ",ConnectTimeout", e);
            return null;
        } catch (SocketTimeoutException e) {
            logger.error("request url:" + requestUrl + "startTime:" + startTime + ",SocketTimeout", e);
            return null;
        } catch (Exception e) {
            logger.error("request url:" + requestUrl + ",error", e);
            return null;
        }
    }

    public static String sendGetRequestSetEncoding(String url, Map<String, String> paramMap, String encoding,Map<String,String> headers) {
        StringBuilder urlBuffer = new StringBuilder(url);
        if (url.indexOf("?") < 0) {
            urlBuffer.append("?");
        }
        if (paramMap != null && !paramMap.isEmpty()) {
            Iterator<String> iterator = paramMap.keySet().iterator();
            while (iterator.hasNext()) {
                String paramName = iterator.next();
                if (urlBuffer.toString().endsWith("?")) {
                    urlBuffer.append(paramName).append("=").append(paramMap.get(paramName).toString());
                } else {
                    urlBuffer.append("&").append(paramName).append("=").append(paramMap.get(paramName).toString());
                }
            }
        }
        String requestUrl = urlBuffer.toString();
        HttpGet httpGet = new HttpGet(requestUrl);
        //httpGet.setHeader("User-Agent", "");
        if(headers!=null && headers.size()>0){
            Iterator<String> iter = headers.keySet().iterator();
            while (iter.hasNext()) {
                String key = iter.next();
                httpGet.setHeader(key, headers.get(key));
            }
        }
        HttpResponse response;
        String startTime = null;
        try {
            startTime = DateHelper.getCurrentDateStr("yyyy-MM-dd HH:mm:ss");
            response = httpClient.execute(httpGet);
            return parseResponse(requestUrl, response, encoding);
        } catch (ConnectTimeoutException e) {
          //  logger.error("request url:" + requestUrl + "startTime:" + startTime + ",ConnectTimeout", e);
            return null;
        } catch (SocketTimeoutException e) {
         //   logger.error("request url:" + requestUrl + "startTime:" + startTime + ",SocketTimeout", e);
            return null;
        } catch (Exception e) {
         //   logger.error("request url:" + requestUrl + ",error", e);
            return null;
        }
    }

    /**
     * post????????????
     *
     * @param url
     * @param paramMap
     * @return
     */

    public static String sendPostRequest(String url, Map<String, String> paramMap) {
        return sendPostRequest(url, paramMap, "UTF-8");
    }

    public static String sendPostRequest(String url, Map<String, String> paramMap,Map<String,String> headers) {
        return sendPostRequest(url, paramMap, "UTF-8",headers);
    }

    public static String sendPostRequestByJson(String url, Map<String, String> paramMap) {
        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader("Content-Type", "application/json;charset=UTF-8");
        StringEntity se = null;
        try {
            se = new StringEntity(JSON.toJSONString(paramMap), "UTF-8");
            se.setContentType("application/json;charset=UTF-8");

        } catch (Exception e) {
            e.printStackTrace();
        }
        httpPost.setEntity(se);
        HttpResponse response = null;
        try {
            response = httpClient.execute(httpPost);
        } catch (Exception e) {
            logger.error("request url:" + url + ",error", e);
            throw new RuntimeException(e);
        }
        return parseResponse(url, response, "UTF-8");
    }

    public static String sendPostRequestByJsonObject(String url, Map<String, Object> paramMap) {
        HttpPost httpPost = new HttpPost(url);
        //httpPost.setHeader("Content-Type", "application/json;charset=UTF-8");
        StringEntity se = null;
        try {
            se = new StringEntity(JSON.toJSONString(paramMap), "UTF-8");
            //se.setContentType("application/json;charset=UTF-8");

        } catch (Exception e) {
            e.printStackTrace();
        }
        httpPost.setEntity(se);
        HttpResponse response = null;
        try {
            response = httpClient.execute(httpPost);
        } catch (Exception e) {
           // logger.error("request url:" + url + ",error", e);
            throw new RuntimeException(e);
        }
        return parseResponse(url, response, "UTF-8");
    }

    /**
     * post????????????
     *
     * @param url
     * @param paramMap
     * @return
     */
    public static String sendPostRequest(String url, Map<String, String> paramMap, String enc,Map<String,String> headers) {
        HttpPost httpPost = new HttpPost(url);

        if(headers!=null && headers.size()>0){
            Iterator<String> iterator = headers.keySet().iterator();
            while (iterator.hasNext()){
                String key = iterator.next();
                httpPost.setHeader(key,headers.get(key));
            }
        }
//        httpPost.setHeader("pcpopclub", "551521767E62D76CB2A5CE5BACC7772E5BC73D0B47947C7B1F84A434A0A74FA89754DD632F25BCD867D9AD6BD27DCDE2E5AA36A609F398CC5948C80AB4957C30AD32453CA0BBFB1B97DCB5D54D118997D734E111D8A0B1B38AB7B6CB55DADD4FD3A9C3C7649F940238A4D1A023E4D174254BAD9DD9E3DE3FA46F6C4AAF84D2A96E6956FB750D91B6AB2B84478EC883A8EC02DE35D320DB91CA0EBA9B25E6C604C338845EB4540B22E88CCB5A44051AE17237B39BF3966E999FA0FF0B92112273771ABDE0AF84D11B4C03869FC0E803743AB680D17F2C6EF17EA73EEC11F46C9E1D66B6A8AE52FDEE8E81F7138277BC8700F341A7610A19667A5F855C6CF02EB62FB1FA76BC94C5D3EFDDEC58EBBFA62C5669C9BD5AB4BEEF069C6E47251733576F5C3DC82484A61CE1356E1269EDB0FED256A3AC1E1FFE12B43BDD0F".toUpperCase());
        if (paramMap != null && !paramMap.isEmpty()) {
            Iterator<String> iterator = paramMap.keySet().iterator();
            List<BasicNameValuePair> params = new ArrayList<>(paramMap.size());
            while (iterator.hasNext()) {
                String paramName = iterator.next();
                params.add(new BasicNameValuePair(paramName, paramMap.get(paramName).toString()));
            }
            try {
                httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
            } catch (Exception e) {
              //  logger.error("can not request url:" + url, e);
                throw new RuntimeException(e);
            }
        }
        HttpResponse response = null;
        try {
            response = httpClient.execute(httpPost);
        } catch (Exception e) {
          //  logger.error("request url:" + url + ",error", e);
            throw new RuntimeException(e);
        }
        return parseResponse(url, response, enc);
    }

    /**
     * post????????????
     *
     * @param url
     * @param paramMap
     * @return
     */
    public static String sendPostRequest(String url, Map<String, String> paramMap, String enc) {
        HttpPost httpPost = new HttpPost(url);
//        httpPost.setHeader("pcpopclub", "551521767E62D76CB2A5CE5BACC7772E5BC73D0B47947C7B1F84A434A0A74FA89754DD632F25BCD867D9AD6BD27DCDE2E5AA36A609F398CC5948C80AB4957C30AD32453CA0BBFB1B97DCB5D54D118997D734E111D8A0B1B38AB7B6CB55DADD4FD3A9C3C7649F940238A4D1A023E4D174254BAD9DD9E3DE3FA46F6C4AAF84D2A96E6956FB750D91B6AB2B84478EC883A8EC02DE35D320DB91CA0EBA9B25E6C604C338845EB4540B22E88CCB5A44051AE17237B39BF3966E999FA0FF0B92112273771ABDE0AF84D11B4C03869FC0E803743AB680D17F2C6EF17EA73EEC11F46C9E1D66B6A8AE52FDEE8E81F7138277BC8700F341A7610A19667A5F855C6CF02EB62FB1FA76BC94C5D3EFDDEC58EBBFA62C5669C9BD5AB4BEEF069C6E47251733576F5C3DC82484A61CE1356E1269EDB0FED256A3AC1E1FFE12B43BDD0F".toUpperCase());
        if (paramMap != null && !paramMap.isEmpty()) {
            Iterator<String> iterator = paramMap.keySet().iterator();
            List<BasicNameValuePair> params = new ArrayList<>(paramMap.size());
            while (iterator.hasNext()) {
                String paramName = iterator.next();
                params.add(new BasicNameValuePair(paramName, paramMap.get(paramName).toString()));
            }
            try {
                httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
            } catch (Exception e) {
               // logger.error("can not request url:" + url, e);
                throw new RuntimeException(e);
            }
        }
        HttpResponse response = null;
        try {
            response = httpClient.execute(httpPost);
        } catch (Exception e) {
          //  logger.error("request url:" + url + ",error", e);
            throw new RuntimeException(e);
        }
        return parseResponse(url, response, enc);
    }

    /**
     * ??????????????????
     *
     * @param url
     * @param response
     * @return
     */
    private static String parseResponse(String url, HttpResponse response, String encoding) {
        if (response == null) {
            logger.error("can not parse httpResponse,beacuse is null,url:" + url);
            return null;
        }
        InputStream inputStream = null;
        try {
            StatusLine status = response.getStatusLine();
            HttpEntity entity = response.getEntity();
            inputStream = entity.getContent();
            if (status.getStatusCode() != 200) {
                throw new RuntimeException("request url:" + url + ", status error, status is " + status.getStatusCode());
            }
            return IOUtils.toString(inputStream, encoding);
        } catch (Exception e) {
            logger.error("parse response error", e);
            throw new RuntimeException("parse response error", e);
        } finally {
            IOUtils.closeQuietly(inputStream);
        }
    }


    private static void closeExpiredConnectionsPeriodTask(int timeUnitBySecond) {
        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                while (!Thread.currentThread().isInterrupted()) {
                    try {
                        TimeUnit.SECONDS.sleep(timeUnitBySecond);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    cm.closeExpiredConnections();
                }
            }
        });
    }


    /**
     * post?????? raw ??????
     *
     * @param uri
     * @param content
     * @return
     */
    public static String postBodyRaw(String uri, String content) {

        String result = null;
        try {
            HttpPost httpPost = new HttpPost(uri);
            StringEntity requestEntity = new StringEntity(content, "UTF-8");
            requestEntity.setContentType("application/json");
            httpPost.setEntity(requestEntity);
            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            result = EntityUtils.toString(entity, "UTF-8");

        } catch (Exception e) {
            logger.error("uri={},content={},??????={}", uri, content, e);
        }

        return result;
    }


    /**
     * ????????? URL ??????POST???????????????
     *
     * @param url   ??????????????? URL
     * @param param ???????????????????????????????????? name1=value1&name2=value2 ????????????
     * @return ????????????????????????????????????
     */
    public static String sendPost(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // ?????????URL???????????????
            URLConnection conn = realUrl.openConnection();
            // ???????????????????????????
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("charset", "UTF-8");
            conn.setRequestProperty("Accept-Charset", "UTF-8");
            // ??????POST??????????????????????????????
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // ??????URLConnection????????????????????????
            out = new PrintWriter(conn.getOutputStream());
            // ??????????????????
            out.print(param);
            // flush??????????????????
            out.flush();
            // ??????BufferedReader??????????????????URL?????????
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("?????? POST ?????????????????????" + e);
            e.printStackTrace();
        }
        //??????finally?????????????????????????????????
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }


    //????????????url??????,????????????
    public static String returnImgWH(String imgurl) {
        BufferedImage bi = null;
        boolean imgwrong = false;
        try {
            URL url2 = new URL(imgurl);
            //????????????
            bi = javax.imageio.ImageIO.read(url2);
            try {
                //???????????????????????????????????????,???????????????????????????
                int i = bi.getType();
                imgwrong = true;
            } catch (Exception e) {
                imgwrong = false;
            }
        } catch (IOException ex) {
            String errorMsg = ExceptionUtils.getMessage(ex);
            logger.info("???????????????????????????" + errorMsg);
        }
        if (imgwrong) {
            return imgurl;
        } else {
            String tempUrl = "";
            tempUrl = imgurl.replace("500_", "");
            tempUrl = tempUrl.replace("400_", "");
            return tempUrl;
        }
    }


    public static void main(String[] args) {
//        HttpUtil i = new HttpUtil();
//        int[] a = i.returnImgWH("https://img2.autoimg.cn/heycardfs/g29/M08/E8/16/404x0_m811_autohomecar__ChsEn11k2oCAHRpYAAB5B8Ztk-U64.jpeg");
//        if (a == null) {
//            System.out.println("???????????????!");
//        } else {
//            System.out.println("??????" + a[0]);
//            System.out.println("??????" + a[1]);
//        }

//        Map<String,String> map=new HashMap<>();
//        map.put("userNo","12");
//        map.put("gateway","true");
//
//        Map<String,String> p=new HashMap<>();
//        p.put("id","1");
//
//        String result= sendGetRequest("http://192.168.32.94:8080/product/selectById",p,map);
//        System.out.println(result);
    }
}
