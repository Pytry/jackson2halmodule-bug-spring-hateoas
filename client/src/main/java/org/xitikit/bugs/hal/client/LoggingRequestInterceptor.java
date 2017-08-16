package org.xitikit.bugs.hal.client;

import java.io.IOException;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

public class LoggingRequestInterceptor
    implements ClientHttpRequestInterceptor
{

  @Override
  public ClientHttpResponse intercept(
      final HttpRequest request,
      final byte[] body,
      final ClientHttpRequestExecution execution) throws IOException
  {
    System.out.println(
        "Taskman WS request issued:" +
            " " + request.getMethod().toString() +
            " " + request.getURI().toString());

    return execution.execute(
        request,
        body);
  }
}
