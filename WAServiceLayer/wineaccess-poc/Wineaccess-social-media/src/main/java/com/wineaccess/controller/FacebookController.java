package com.wineaccess.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author abhishek.sharma1
 *
 */
@Controller
public class FacebookController {
	
	@RequestMapping(value="/postComment/{accessTocken}/{userID}", method=RequestMethod.POST)
	public @ResponseBody String postCommentsOnFacebook(@PathVariable String accessTocken, @PathVariable String userID ){
		String url = "https://graph.facebook.com/" + userID +"/feed";
		
		HttpClient httpClient = HttpClientBuilder.create().build();
		
		HttpPost post = new HttpPost(url);
		List<BasicNameValuePair> nameValuePairs = new ArrayList<BasicNameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("message", "Hello"));
		nameValuePairs.add(new BasicNameValuePair("access_token", accessTocken));
		
		try {
			post.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			HttpResponse response = httpClient.execute(post);
			return response.toString();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "hello";
		
	}
}
