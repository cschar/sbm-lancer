package com.oceanboa.dnc.summoner2service;

import com.oceanboa.dnc.summoner2service.OriTest;
import com.oceanboa.dnc.summoner2service.model.SumLog;
import com.oceanboa.dnc.summoner2service.repo.SumLogRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;


@SpringBootApplication
public class Summoner2ServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(Summoner2ServiceApplication.class, args);
	}


	@Bean
	public CommandLineRunner demo(SumLogRepository repository) {
		return (args) -> {

			repository.save(new SumLog("Jack", "Bauer"));

			List<String> sums = OriTest.getTopSums();


			sums.stream()
					.forEach( sumName ->{
						repository.save(new SumLog(sumName, "-"));
					});

		};
	}
}
