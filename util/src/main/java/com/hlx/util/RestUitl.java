package com.hlx.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Map;


public class RestUitl {

    private static  Logger LOGGER= LoggerFactory.getLogger(RestUitl.class);
    /**
     *
     * 发送POST请求
     */

    public static <T>T postJSON(RestTemplate restTemplate,String Url,Object param,Class<T> responseType){
        HttpEntity<String> formEntity=makePostJSONEntity(param);
        T result=restTemplate.postForObject(Url,formEntity,responseType);
        LOGGER.info("请求响应信息",JsonUtil.objectTojson(result));
        return result;
    }


    /**
     * 生成JSON请求
     * @param param
     * @return
     */
    public static HttpEntity<String> makePostJSONEntity(Object param) {

        HttpHeaders httpHeaders=new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
        httpHeaders.add("Accpet",MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<String> httpEntity= new HttpEntity<>(JsonUtil.objectTojson(param),httpHeaders);
        LOGGER.info("rest-post-json请求参数{}",httpEntity.toString());
        return httpEntity;
    }

    public static HttpEntity<String> makePostTextJSONEntity(Map<String,? extends Object> param){
        HttpHeaders httpHeaders=new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        httpHeaders.add("Accpet",MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<String> httpEntity=new HttpEntity<>(makeGetParamContent(param),httpHeaders);
        LOGGER.info("rest-Get-json请求参数={}",httpEntity.toString());
        return httpEntity;
    }

    /**
     * 生成GET请求头
     * @param param
     * @param excluedes
     * @return
     */
    public static String makeGetParamContent(Map<String,? extends Object> param,String ...excluedes){
        StringBuffer stringBuffer=new StringBuffer();
        List<String> list= Arrays.asList(excluedes);
        param.forEach((key, v) -> {
            stringBuffer.append(key).append("=").append(v).append("&");
        });
        if (stringBuffer.length() > 0) {
            stringBuffer.deleteCharAt(stringBuffer.length() - 1);
        }
        return stringBuffer.toString();
    }
}
