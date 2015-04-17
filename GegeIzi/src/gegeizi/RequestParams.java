/**©Awkbak BR, Bobjrsenior
 * º⌐⌐º
 * 
 * Sounds of URF
 * Goal: To create an interactive Application that generates a sound sequence based off the outcome of game IDs.
 * Description: Imports game data from a 'League of Legends' match using Riot Games API.
 * Then it proceeds to layout all game events in a sort of sheet music.
 * The Keys correspond to each champion/player in the selected match.
 * The Keys will be played according to their respective events in the match.
 * 
 * Start Date: 3/27/2015
 * End Date: 4/17/2015
 */
package gegeizi;

/**
 *
 * @author Awkbak BR, Bobjrsenior
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
	HashMap<String, String> params = new HashMap<>();
	
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
		
                params.keySet().stream().forEach((key) -> {
                    try {
                        String value = URLEncoder.encode(params.get(key), "UTF-8");
                        if(sb.length() > 0){
                            sb.append("&");
                        }
                        sb.append(key).append("=").append(value);
                    } catch (UnsupportedEncodingException e) {}
            });
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

