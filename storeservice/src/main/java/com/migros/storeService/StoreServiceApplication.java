package com.migros.storeService;

import com.migros.storeService.service.StoreService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableDiscoveryClient
@EnableAspectJAutoProxy
public class StoreServiceApplication implements CommandLineRunner {
	private final StoreService storeService;

	public StoreServiceApplication(StoreService storeService) {
		this.storeService = storeService;
	}

	public static void main(String[] args) {
		SpringApplication.run(StoreServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		storeService.prepareStores();
	}
}
