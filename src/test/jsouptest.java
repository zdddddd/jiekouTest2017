package test;

import org.jsoup.Connection;
import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.Map;

public class jsouptest {
	
	public static Map<String, String> cookie;
	
	public static String Get(String url) throws Exception {
		Document document = Jsoup.connect(url)
				.proxy("127.0.0.1", 9999)
				.get();
		// System.out.println(document.toString());
		return document.toString();
	}
	
	public static String Get(String url, Map<String, String> data) throws Exception {
		Connection conn = Jsoup.connect(url);
		conn.proxy("127.0.0.1", 9999);
		conn.data(data);
		conn.method(Method.GET);
		Response response = conn.execute();
		cookie = response.cookies();
		return response.body();
	}
	
	public static String Post(String url, Map<String, String> data) throws Exception {
		Connection conn = Jsoup.connect(url);
		conn.proxy("127.0.0.1", 9999);
		conn.data(data);
		conn.cookies(cookie); //设置cookie
		Document document = conn.post();
		return document.toString();
		
	}
}
