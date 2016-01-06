package com.example.assistant_3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

public class Communication {
	public ArrayList<String> getTagId(String lecroom_id) {
		ArrayList<String> tagList = new ArrayList<String>();
		String resultstr = "";
		InputStream is = null;
		String url = "http://220.124.25.180:8080/Total2/view/assistant/sendTagData.jsp";
		// String url = "http://192.168.0.2:8080/login.jsp";
		// 서버에 접속하기위한 서버주소등록
		HttpClient client = new DefaultHttpClient();
		try {
			ArrayList<NameValuePair> nameValueArr = new ArrayList<NameValuePair>();
			nameValueArr.add(new BasicNameValuePair("flag", "room"));
			nameValueArr.add(new BasicNameValuePair("lecroom_id", lecroom_id));
			// 받아온문자들을 배열에 저장
			HttpPost post = new HttpPost(url);
			UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(
					nameValueArr, "UTF-8");
			post.setEntity(urlEncodedFormEntity);
			HttpResponse response = client.execute(post);
			HttpEntity entityResponse = response.getEntity();
			is = entityResponse.getContent();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					is, "UTF-8"), 8);
			StringBuilder sb = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null) {
				// sb.append(line).append("\n");
				sb.append(line);
			}
			is.close();
			resultstr = sb.toString(); //받아온 문자열
			String[] tagArray = resultstr.split("\\|\\|");
			for(String splited: tagArray){
				tagList.add(lecroom_id+"|"+splited	);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		
		return tagList;
	}
	public String getTagId(String lecroom_id, String width, String height) {
		String tagData = "";
		String resultstr = "";
		InputStream is = null;
		String url = "http://220.124.25.180:8080/Total2/view/assistant/sendTagData.jsp";
		// String url = "http://192.168.0.2:8080/login.jsp";
		// 서버에 접속하기위한 서버주소등록
		HttpClient client = new DefaultHttpClient();
		try {
			ArrayList<NameValuePair> nameValueArr = new ArrayList<NameValuePair>();
			nameValueArr.add(new BasicNameValuePair("flag", "seat"));
			nameValueArr.add(new BasicNameValuePair("lecroom_id", lecroom_id));
			nameValueArr.add(new BasicNameValuePair("width", width));
			nameValueArr.add(new BasicNameValuePair("height", height));
			// 받아온문자들을 배열에 저장
			HttpPost post = new HttpPost(url);
			UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(
					nameValueArr, "UTF-8");
			post.setEntity(urlEncodedFormEntity);
			HttpResponse response = client.execute(post);
			HttpEntity entityResponse = response.getEntity();
			is = entityResponse.getContent();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					is, "UTF-8"), 8);
			StringBuilder sb = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null) {
				// sb.append(line).append("\n");
				sb.append(line);
			}
			is.close();
			resultstr = sb.toString(); //받아온 문자열
			tagData = lecroom_id+"|"+resultstr;
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		
		return tagData;
	}
}
