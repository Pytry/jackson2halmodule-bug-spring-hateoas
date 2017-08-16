package org.xitikit.bugs.hal.client;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.HttpMethod;

public class BuggyHalClient
    extends AbstractClient
{
  public BuggyHalClient(final String serviceUrl) {
    super(serviceUrl, 30000);
  }

  public PagedResources<Hello> worldHelloList() {

    return restTemplate().exchange(
        serviceUrl(),
        HttpMethod.GET,
        httpEntity(),
        new HelloTypeReference()
    ).getBody();
  }

  private class HelloTypeReference
      extends ParameterizedTypeReference<PagedResources<Hello>> { }
}
