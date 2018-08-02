package test;

import org.apache.http.*;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.*;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class httpclientTest {

	private static RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(10000)
			.setConnectionRequestTimeout(15000).build();

	public static String sendHttpGet(String url) throws Exception {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(url);
		httpGet.setConfig(requestConfig);
		httpGet.addHeader("User-Agent", "Mozilla");

		CloseableHttpResponse response = httpClient.execute(httpGet);
		System.out.println(response.getStatusLine().getStatusCode() + "\n");

		HttpEntity entity = response.getEntity();
		String responseContent = EntityUtils.toString(entity, "UTF-8"); // GBK
		System.out.println(responseContent);

		response.close();
		httpClient.close();
		return responseContent;
	}

	public static String sendHttpPost(String url, String body) throws Exception {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url);
		httpPost.setConfig(requestConfig);
		httpPost.addHeader("User-Agent", "Posttest");

		httpPost.setEntity(new StringEntity(body));
		
		CloseableHttpResponse response = httpClient.execute(httpPost);
		System.out.println(response.getStatusLine().getStatusCode() + "\n");

		HttpEntity entity = response.getEntity();
		String responseContent = EntityUtils.toString(entity, "UTF-8"); // GBK
		System.out.println(responseContent);

		response.close();
		httpClient.close();
		return responseContent;
	}

	public static String sendHttpPost(String url, Map<String, String> params) throws Exception {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url);
		httpPost.setConfig(requestConfig);
		httpPost.addHeader("User-Agent", "Posttest");

		List<NameValuePair> plist = new ArrayList<NameValuePair>();
		for (String pKey : params.keySet()) {
			plist.add(new BasicNameValuePair(pKey, params.get(pKey)));
        }
		httpPost.setEntity(new UrlEncodedFormEntity(plist));
		
		CloseableHttpResponse response = httpClient.execute(httpPost);
		System.out.println(response.getStatusLine().getStatusCode() + "\n");

		HttpEntity entity = response.getEntity();
		String responseContent = EntityUtils.toString(entity, "UTF-8"); // GBK
		System.out.println(responseContent);

		response.close();
		httpClient.close();
		return responseContent;
	}
}