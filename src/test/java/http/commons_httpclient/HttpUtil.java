package http.commons_httpclient;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

public class HttpUtil {

	public static String get(String url) throws Exception {
		
		HttpClient client = new HttpClient();
		client.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
		client.getHttpConnectionManager().getParams().setConnectionTimeout(1000 * 500);
		GetMethod getMethod = new GetMethod(url);
		int statuscode = client.executeMethod(getMethod);
		if (statuscode != HttpStatus.SC_OK) {
			throw new RuntimeException(getMethod.getStatusLine().toString());
		}
		InputStream is = getMethod.getResponseBodyAsStream();
		InputStreamReader isr = new InputStreamReader(is, "utf-8");
		StringBuilder sb = new StringBuilder();
		BufferedReader reader = new BufferedReader(isr);
		String line = null;
		while ((line = reader.readLine()) != null) {
			sb.append(line);
		}
		reader.close();
		isr.close();
		is.close();
		return sb.toString();
	}

	public static String post(String url, Map<String, Object> map) throws Exception {
		HttpClient client = new HttpClient();
		client.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "utf-8");
		client.getHttpConnectionManager().getParams().setConnectionTimeout(1000 * 500);
		PostMethod postMethod = new PostMethod(url);
		// postMethod.addParameter("hello", "world");

		int statuscode = client.executeMethod(postMethod);
		if (statuscode != HttpStatus.SC_OK) {
			throw new RuntimeException(postMethod.getStatusLine().toString());
		}
		InputStream is = postMethod.getResponseBodyAsStream();
		InputStreamReader isr = new InputStreamReader(is, "utf-8");
		StringBuilder sb = new StringBuilder();
		BufferedReader reader = new BufferedReader(isr);
		String line = null;
		while ((line = reader.readLine()) != null) {
			sb.append(line);
		}
		reader.close();
		isr.close();
		is.close();
		return sb.toString();
	}

	public static void main(String[] args) throws NoSuchAlgorithmException {
		try {
			// System.out.println(HttpUtil.get("https://baidu.com"));
			System.out.println(HttpUtil.post("http://zj.callkr.com:8050/crm/ourJs/Ynotify/js/Ynotify.js",
					new HashMap<String, Object>()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
