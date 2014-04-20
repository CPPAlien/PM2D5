package com.pm2d5.net;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;

public class StaticHttpClient {

	public static String doPost(String url , Map<String,String> mapParams) throws ClientProtocolException, IOException
	{
		String strResult = null;
		HttpPost httpRequest = new HttpPost(url);
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		for(String key : mapParams.keySet()){
			params.add(new BasicNameValuePair(key, mapParams.get(key)));
		}
		
		HttpEntity httpEntity = new UrlEncodedFormEntity(params,"utf-8");
		httpRequest.setEntity(httpEntity);
		HttpClient httpClient = new DefaultHttpClient();
		httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 10000);//连接超时
		httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 20000);//读超时
		HttpResponse httpResponse = httpClient.execute(httpRequest);
		
		if(httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK)
		{
			byte[] byteResult = EntityUtils.toByteArray(httpResponse.getEntity());
			strResult = new String(byteResult,"utf-8");
		}
		return strResult;
	}
	
	public static String doPost(String url , String sessionId , Map<String ,String> mapParams) throws ClientProtocolException, IOException
	{
		String strResult = null;
		HttpPost httpRequest = new HttpPost(url);
		httpRequest.setHeader("Cookie", "JSESSIONID="+sessionId );
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		for(String key : mapParams.keySet()){
			params.add(new BasicNameValuePair(key, mapParams.get(key)));
		}
		HttpEntity httpEntity = new UrlEncodedFormEntity(params,"utf-8");
		httpRequest.setEntity(httpEntity);
		HttpClient httpClient = new DefaultHttpClient();
		httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 10000);//连接超时
		httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 20000);//读超时
		HttpResponse httpResponse = httpClient.execute(httpRequest);
		if(httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK)
		{
			byte[] byteResult = EntityUtils.toByteArray(httpResponse.getEntity());
			strResult = new String(byteResult,"utf-8");
		}
		return strResult;
	}
	
	public static String doGet(String url, Map<String ,String> mapParams)throws ClientProtocolException, IOException{
		String getUrl = url + "?";
		for(String key : mapParams.keySet())
		{
			getUrl = getUrl + key + "=" + mapParams.get(key) + "&";
		}
		getUrl = getUrl.substring(0, getUrl.lastIndexOf("&"));
		getUrl= getUrl.replaceAll(" ", "%20");
		String strResult = null;
		HttpGet httpRequest = new HttpGet(getUrl);
		HttpClient httpClient = new DefaultHttpClient();
		httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 10000);//连接超时
		httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 20000);//读超时
		HttpResponse httpResponse = httpClient.execute(httpRequest);
		if(httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK)
		{
			byte[] byteResult = EntityUtils.toByteArray(httpResponse.getEntity());
			strResult = new String(byteResult,"utf-8");
		}
		return strResult;
	} 
}
