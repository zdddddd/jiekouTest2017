package test;

import org.apache.http.client.fluent.*;
import org.apache.http.entity.*;

import java.util.Map;

public class httpFluent {
	public static String Get(String url) throws Exception {
		return Request.Get(url)
			.execute()
			.returnContent()
			.asString();
	}

	public static String Post(String url, String body) throws Exception {
		return Request.Post(url)
			.bodyString(body, ContentType.parse("application/x-www-form-urlencoded"))
			.addHeader("test", "header")
			.addHeader("test2", "header2")
			.execute()
			.returnContent()
			.asString();
	}
	
	public static String Post(String url, Map<String, String> params) throws Exception {
		Form formParams=Form.form(); 
		//Form.form().add("username",  "vip").add("password",  "secret")
		for (String pKey : params.keySet()) {
			formParams.add(pKey, params.get(pKey));
        }		
		return Request.Post(url)
			.bodyForm(formParams.build())
			.execute()
			.returnContent()
			.asString();
	}
}
