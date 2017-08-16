package org.xitikit.bugs.hal.client;

import java.util.Collections;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.hateoas.hal.Jackson2HalModule;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

abstract class AbstractClient
{


  private final HttpEntity<Object> httpEntity;

  ////In order to unit test, this needs to NOT be final.
  private RestTemplate restTemplate;

  private final String serviceUrl;

  AbstractClient(final String serviceUrl, final int timeout) {

    this.serviceUrl = serviceUrl;
    this.httpEntity = new HttpEntity<>(createHeaders());

    ObjectMapper mapper = new ObjectMapper();
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    mapper.registerModule(new Jackson2HalModule());

    MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
    converter.setSupportedMediaTypes(MediaType.parseMediaTypes("application/hal+json,application/json"));
    converter.setObjectMapper(mapper);

    this.restTemplate = new RestTemplate(new SimpleClientHttpRequestFactory());
    this.restTemplate.getInterceptors().add(new LoggingRequestInterceptor());
    this.restTemplate.setMessageConverters(Collections.singletonList(converter));

    HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
    requestFactory.setConnectTimeout(timeout);
    requestFactory.setReadTimeout(timeout);

    this.restTemplate.setRequestFactory(requestFactory);
  }

  private HttpHeaders createHeaders() {

    HttpHeaders headers = new HttpHeaders();
    headers.add("Content-Type", "application/json");
    headers.add("Accept", "application/json");

    return headers;
  }

  String serviceUrl() {

    return serviceUrl;
  }

  HttpEntity<Object> httpEntity() {

    return httpEntity;
  }

  RestTemplate restTemplate() {

    return restTemplate;
  }
}
