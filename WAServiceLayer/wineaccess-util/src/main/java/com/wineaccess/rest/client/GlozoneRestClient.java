package com.wineaccess.rest.client;

import java.net.UnknownHostException;
import java.util.Map;

import org.apache.http.HttpHost;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

/**
 * @author jyoti.yadav@globallogic.com
 */
public class GlozoneRestClient {
	
	private static GlozoneRestClient glozoneRestClient = null;
	
	private static PoolingHttpClientConnectionManager cm = null;
	
	
	private GlozoneRestClient(){
	}
	
	public static synchronized GlozoneRestClient getInstance() {
		if (glozoneRestClient == null) {
			cm = new PoolingHttpClientConnectionManager();
			cm.setMaxTotal(200);
			cm.setDefaultMaxPerRoute(20);
			HttpHost localhost = new HttpHost("locahost", 80);
			cm.setMaxPerRoute(new HttpRoute(localhost), 50);
			glozoneRestClient = new GlozoneRestClient();
		}
		return glozoneRestClient;
	}
	
	
	public HttpResponse get(String apiUrl, Map <String, String> headerParam, Map <String, Object> params) throws Exception {
		CloseableHttpResponse closableHttpResponse = null;
		CloseableHttpClient closeableHttpClient = HttpClients.custom().setConnectionManager(cm).build();
		apiUrl = prepareUrl(apiUrl, params); 
		try {
			HttpGet httpGet = new HttpGet(apiUrl);
			prepareHeader(httpGet, headerParam);
			
			closableHttpResponse = closeableHttpClient.execute(httpGet);
			
			String bodyAsString = EntityUtils.toString(closableHttpResponse.getEntity());
			
			HttpResponse httpResponse = new HttpResponse(closableHttpResponse.getStatusLine().getStatusCode(), bodyAsString);
			
			return httpResponse;
		} catch(UnknownHostException e) {
			HttpResponse httpResponse = new HttpResponse(401, e.getMessage());
			return httpResponse;
		} finally {
			if (closableHttpResponse != null) {
				closableHttpResponse.close();
			}
			if (closeableHttpClient != null) {
				//closeableHttpClient.close();
			}
		}
	}
	
	public HttpResponse post(String apiUrl, Map <String, String> headerParam, Map<String, Object> urlPatameter,String postParameterString) throws Exception {
		CloseableHttpResponse closableHttpResponse = null;
		CloseableHttpClient closeableHttpClient = HttpClients.custom().setConnectionManager(cm).build();
		apiUrl = prepareUrl(apiUrl, urlPatameter); 
		try {
			HttpPost httpPost = new HttpPost(apiUrl);
			
			prepareHeader(httpPost, headerParam);
			
			StringEntity stringEntity = new StringEntity(postParameterString);
			
			httpPost.setEntity(stringEntity);
			
			closableHttpResponse = closeableHttpClient.execute(httpPost);
			
			String bodyAsString = EntityUtils.toString(closableHttpResponse.getEntity());
			
			HttpResponse httpResponse = new HttpResponse(closableHttpResponse.getStatusLine().getStatusCode(), bodyAsString);
			
			return httpResponse;
		} catch(UnknownHostException e) {
			HttpResponse httpResponse = new HttpResponse(401, e.getMessage());
			return httpResponse;
		} finally {
			if (closableHttpResponse != null) {
				closableHttpResponse.close();
			}
			if (closeableHttpClient != null) {
				//closeableHttpClient.close();
			}
		}
	}

	
	private void prepareHeader(HttpRequestBase httpRequestBase, Map <String, String> headerParam) {
		for (String key : headerParam.keySet()) {
			httpRequestBase.setHeader(key, String.valueOf(headerParam.get(key)));
		}
	}
	
	private String prepareUrl(String buffer, Map <String, Object> headerParam) {
		if (headerParam != null) {
			for (String key : headerParam.keySet()) {
				if (headerParam.get(key) != null) {
					buffer = buffer.replace("{" + key + "}", headerParam.get(key).toString());
				}
			}
		}
		return buffer;
	}
}
