package org.xitikit.bugs.hal.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.xitikit.bugs.hal.client.BuggyHalClient;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = BuggyHalApp.class, webEnvironment = RANDOM_PORT)
public class BuggyHalClientTest
{
  @Value("${local.server.port}")
  String serverPort;

  private BuggyHalClient buggyHalClient;

  @Before
  public void setup() throws Exception {

    buggyHalClient = new BuggyHalClient("http://localhost:" + serverPort);
  }

  @Test
  public void worldHelloListSuccess() {
    System.out.print("\n**********\nThe SUCCESS test ran.\n**********\n");
    buggyHalClient.worldHelloList();
  }
}
