package io.berndruecker.rescheduletest.cloudrescheduletest;

import io.camunda.zeebe.client.ZeebeClient;
import io.camunda.zeebe.spring.client.EnableZeebeClient;
import io.camunda.zeebe.spring.client.annotation.ZeebeDeployment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@EnableZeebeClient
@ZeebeDeployment(classPathResources = "simpleProcess.bpmn")
public class CloudRescheduleTestApplication {

	private static Logger logger = LoggerFactory.getLogger(CloudRescheduleTestApplication.class);

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(CloudRescheduleTestApplication.class, args);
		ZeebeClient workflowEngineClient = context.getBean(ZeebeClient.class );

		startInstancesInALoop(workflowEngineClient);
	}

	public static void startInstancesInALoop(ZeebeClient workflowEngineClient) {
		while(true) {
			try {
				//logger.info(
						workflowEngineClient.newCreateInstanceCommand() //
								.bpmnProcessId("simple-process") //
								.latestVersion() //
								.send().join() //
								.toString();
			    //);
			} catch (Exception ex) {
				logger.error(ex.getMessage(), ex);
			}
		}
	}

}
