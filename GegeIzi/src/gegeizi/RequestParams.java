/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gegeizi;

/**
 *
 * @author user
 */
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import javax.net.ssl.HttpsURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;

public class RequestParams {
	
	String method, baseUrl;
	HashMap<String, String> params = new HashMap<String, String>();
	
	public RequestParams(String method, String baseUrl) {
		super();
		this.method = method;
		this.baseUrl = baseUrl;
	}
	
	public void addParams(String key, String value){
		params.put(key, value);
	}
	
	public String getEncodedParams(){
		StringBuilder sb = new StringBuilder();
		
		for(String key : params.keySet()){
			try {
				String value = URLEncoder.encode(params.get(key), "UTF-8");
				if(sb.length() > 0){
					sb.append("&");
				}
				sb.append(key + "=" + value);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}
	
	public String getEncodedUrl(){
		return this.baseUrl + "?" + getEncodedParams();
	}
	
	public HttpsURLConnection setUpConnection() throws IOException{
		if(method.equals("GET")){
			URL url = new URL(getEncodedUrl());
			HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			return con;
		}
		else{
			URL url = new URL(this.baseUrl);
			HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
			con.setRequestMethod("POST");
			con.setDoOutput(true);
			OutputStreamWriter writer = new OutputStreamWriter(con.getOutputStream());
			writer.write(getEncodedParams());
			writer.flush();
			return con;
		}
	}

}

