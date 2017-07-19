package de.nk360.jug;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.prometheus.client.spring.boot.EnablePrometheusEndpoint;
import io.prometheus.client.spring.boot.EnableSpringBootMetricsCollector;

@SpringBootApplication
@EnableSpringBootMetricsCollector
@EnablePrometheusEndpoint
public class JugApiApplication {
	public static void main(String[] args) {
		SpringApplication.run(JugApiApplication.class, args);
	}
}
