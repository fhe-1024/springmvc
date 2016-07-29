package http.commons_httpclient;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.http.entity.StringEntity;

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

	public static String post(String url, String transjson) throws Exception {
		HttpClient client = new HttpClient();
		client.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "utf-8");
		client.getHttpConnectionManager().getParams().setConnectionTimeout(1000 * 500);
		PostMethod postMethod = new PostMethod(url);
		RequestEntity re = new StringRequestEntity(transjson, "application/json", "utf-8");
		System.out.println(re.getContentType());
		postMethod.setRequestEntity(re);
		postMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());// 调用失败会重试
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
			System.out.println(HttpUtil.post("http://zj.callkr.com:8050/crm/ourJs/Ynotify/js/Ynotify.js", ""));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
