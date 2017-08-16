package org.xitikit.bugs.hal.service;

import java.util.Arrays;

import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.PagedResources.PageMetadata;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.xitikit.bugs.hal.client.Hello;

@RepositoryRestController
public class BuggyHalController
{
  @RequestMapping(value = "/", method = RequestMethod.POST)
  public PagedResources<Hello> hello() {

    return new PagedResources<>(
        Arrays.asList(
            new Hello("small"),
            new Hello("medium"),
            new Hello("large")),
        new PageMetadata(
            3L,
            1L,
            3L,
            1L));
  }
}
