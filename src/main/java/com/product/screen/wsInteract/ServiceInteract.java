package com.product.screen.wsInteract;

import com.google.common.io.CharStreams;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.stereotype.Component;

import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Component
public class ServiceInteract {
    private final CloseableHttpClient httpclient = HttpClients.createDefault();
    private final String endpointURL = "http://localhost:8733/ScreenService";

    public synchronized void resetServerIIS(){
        try {
            HttpClient client = HttpClients.createDefault();
            HttpGet resetIIS = new HttpGet("http://localhost:20012/resetServerIIS");
            client.execute(resetIIS);
        } catch (Exception e){e.printStackTrace();}
    }

    public synchronized void initAllVideo(Map<String, List<String>> videoMap1){
        String url = "http://localhost:20012/initAllVideo";
//        Map<String, String> testVideoMap = new HashMap();
//        testVideoMap.put("21", Arrays.asList("123", "234","345").toString());
//        testVideoMap.put("12", Arrays.asList("1234", "2345", "3456").toString());
        Map<String, String> videoMap = new HashMap<>();
        videoMap1.entrySet().forEach(item -> {
            videoMap.put(item.getKey(), item.getValue().toString());
        });
        doHttpMapPost(url, videoMap);
    }

    public synchronized void initAllWebPage(Map<String, List<String>> webMap1){
        String url = "http://localhost:20012/initAllWebPage";
//        Map<String, String> testWebMap = new HashMap();
//        testWebMap.put("111", Arrays.asList("123", "234","345").toString());
//        testWebMap.put("222", Arrays.asList("1234", "2345", "3456").toString());
        Map<String, String> webMap = new HashMap<>();
        webMap1.entrySet().forEach(item -> {
            webMap.put(item.getKey(), item.getValue().toString());
        });
        doHttpMapPost(url, webMap);
    }

    private void doHttpMapPost(String url, Map<String, String> videoMap){
        try{
            HttpClient client = HttpClients.createDefault();
            HttpPost restartAllVideo = new HttpPost(url);
            List<NameValuePair> list = new ArrayList<>();
            Iterator<Map.Entry<String, String>> iterator = videoMap.entrySet().iterator();
            while(iterator.hasNext()){
                Map.Entry<String, String> next = iterator.next();
                list.add(new BasicNameValuePair(next.getKey(), next.getValue()));
            }
            HttpEntity httpEntity = new UrlEncodedFormEntity(list, Charset.forName("UTF-8"));
            restartAllVideo.setEntity(httpEntity);
            System.out.println("MapResult ======" + videoMap);
            HttpResponse execute = client.execute(restartAllVideo);
            recordDispatchTrack(execute);
            String result = CharStreams.toString(new InputStreamReader(execute.getEntity().getContent(), StandardCharsets.UTF_8));
            System.out.println(result);
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public synchronized String reloadClientCfgFile(){
        String content = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:tem=\"http://tempuri.org/\">\n" +
                "   <soapenv:Header/>\n" +
                "   <soapenv:Body>\n" +
                "      <tem:LoadConfig/>\n" +
                "   </soapenv:Body>\n" +
                "</soapenv:Envelope>";
        return dispatch(endpointURL, content);
    }


    public String dispatch(String endpointURL, String content){
        String result = null;
        try{
            HttpPost httpPost = new HttpPost(endpointURL);
            String soapActionStr = "http://tempuri.org/IScreenService/LoadConfig";
            httpPost.setHeader("Content-Type", "text/xml;charset=UTF-8");
            httpPost.setHeader("SOAPAction", soapActionStr);
            httpPost.setEntity(new StringEntity(content, Charset.forName("UTF-8")));

            CloseableHttpResponse execute = httpclient.execute(httpPost);
            recordDispatchTrack(execute);

            result = CharStreams.toString(new InputStreamReader(execute.getEntity().getContent(), StandardCharsets.UTF_8));
            System.out.println(result);
            result = result.replaceAll("&lt;", "<");
            result = result.replaceAll("&gt;", ">");


        } catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    private void recordDispatchTrack(HttpResponse execute){
        if(execute == null)
            return;

        System.out.println(("++++++++++++++statusLine+++++++++++++++++++\n" + execute.getStatusLine()));
        System.out.println(("==============header=======================\n"));
        for(int i = 0; i < execute.getAllHeaders().length; i++){
            System.out.println(execute.getAllHeaders()[i] + "\t");
        }
        System.out.println();
        System.out.println("--------------contentLength----------------\n" + execute.getEntity().getContentLength());

    }

    public static void main(String[] args){
        ServiceInteract serviceInteract = new ServiceInteract();
//        serviceInteract.reloadClientCfgFile();
        serviceInteract.initAllVideo(null);
        serviceInteract.initAllWebPage(null);
    }

}
