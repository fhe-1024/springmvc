package http.httpclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

public class HttpUtil {

	// httpÀ¹½ØÆ÷ request response
	public static void httpProtocolInterceptors() throws ClientProtocolException, IOException {
		CloseableHttpClient httpclient = HttpClients.custom().addInterceptorLast(new HttpRequestInterceptor() {
			@Override
			public void process(HttpRequest request, HttpContext context) throws HttpException, IOException {
				// TODO Auto-generated method stub
				AtomicInteger count = (AtomicInteger) context.getAttribute("count");
				request.addHeader("Count", Integer.toString(count.getAndIncrement()));
				System.out.println(context.getAttribute("count"));
			}
		}).build();
		AtomicInteger count = new AtomicInteger(1);
		HttpClientContext context = HttpClientContext.create();
		context.setAttribute("count", count);
		HttpGet httpget = new HttpGet("http://baidu.com");
		for (int i = 0; i < 10; i++) {
			CloseableHttpResponse response = httpclient.execute(httpget, context);
			try {
				HttpEntity entity = response.getEntity();
				System.out.println(response.getStatusLine());
				EntityUtils.consume(entity);
			} finally {
				response.close();
			}
		}
	}

	// recommend way to execute http
	public static void get(String url) throws Exception {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			HttpGet httpget = new HttpGet(url);
			RequestConfig config = RequestConfig.custom().setConnectTimeout(5000).setSocketTimeout(5000)
					.setConnectionRequestTimeout(5000).build();
			httpget.setConfig(config);
			ResponseHandler<String> responseHandler = new ResponseHandler<String>() {
				@Override
				public String handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
					// TODO Auto-generated method stub
					int status = response.getStatusLine().getStatusCode();
					if (status >= 200 && status < 300) {
						HttpEntity entity = response.getEntity();
						return entity != null ? EntityUtils.toString(entity) : null;
					} else {
						throw new ClientProtocolException("Unexpected response status: " + status);
					}
				}
			};
			String responsebody = httpclient.execute(httpget, responseHandler);
			System.out.println("-----------------------------------------------------");
			System.out.println(responsebody);
		} finally {
			httpclient.close();
		}
	}

	public static void post(String url, String jsondata) throws Exception {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			HttpPost httppost = new HttpPost(url);
			StringEntity entity = new StringEntity(jsondata, ContentType.APPLICATION_JSON);
			httppost.setEntity(entity);
			ResponseHandler<String> responseHandler = new ResponseHandler<String>() {
				@Override
				public String handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
					// TODO Auto-generated method stub
					int status = response.getStatusLine().getStatusCode();
					if (status >= 200 && status < 300) {
						HttpEntity entity = response.getEntity();
						return entity != null ? EntityUtils.toString(entity) : null;
					} else {
						throw new ClientProtocolException("Unexpected response status: " + status);
					}
				}
			};
			String responsebody = httpclient.execute(httppost, responseHandler);
			System.out.println("-------------------");
			System.out.println(responsebody);
		} finally {
			httpclient.close();
		}
	}

	public static String manualPost(String url, String jsondata) throws Exception {
		StringBuilder sb = new StringBuilder();
		CloseableHttpClient httpClient = HttpClients.createDefault();
		try {
			HttpPost httppost = new HttpPost(url);
			System.out.println("Executing request " + httppost.getRequestLine());
			StringEntity entity = new StringEntity(jsondata, ContentType.APPLICATION_JSON);
			httppost.setEntity(entity);
			CloseableHttpResponse response = httpClient.execute(httppost);
			try {
				System.out.println(response.getStatusLine());
				HttpEntity httpEntity = response.getEntity();
				if (httpEntity != null) {
					InputStream instream = httpEntity.getContent();
					try {
						InputStreamReader isr = new InputStreamReader(instream, "utf-8");
						BufferedReader reader = new BufferedReader(isr);
						String line = null;
						while ((line = reader.readLine()) != null) {
							sb.append(line);
							System.out.println(line);
						}
					} finally {
						instream.close();
					}
				}
			} finally {
				response.close();
			}
		} finally {
			httpClient.close();
		}

		return sb.toString();
	}

	public static String manualGet(String url) throws Exception {
		StringBuilder sb = new StringBuilder();
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			HttpGet httpget = new HttpGet(url);
			System.out.println("Executing request " + httpget.getRequestLine());
			CloseableHttpResponse response = httpclient.execute(httpget);
			try {
				System.out.println("----------------------------------------");
				System.out.println(response.getStatusLine());
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					InputStream instream = entity.getContent();
					try {
						InputStreamReader isr = new InputStreamReader(instream, "utf-8");
						BufferedReader reader = new BufferedReader(isr);
						String line = null;
						while ((line = reader.readLine()) != null) {
							sb.append(line);
							System.out.println(line);
						}
					} catch (Exception ex) {
						throw ex;
					} finally {
						instream.close();
					}
				}
			} finally {
				response.close();
			}
		} finally {
			httpclient.close();
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		try {
			HttpUtil.manualPost(
					"http://hc.apache.org/httpcomponents-client-4.5.x/httpclient/examples/org/apache/http/examples/client/ClientConnectionRelease.java",
					"");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
