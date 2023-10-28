package com.pacifique;

import com.pacifique.book.Book;
import com.pacifique.book.IBookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
@EnableConfigurationProperties(ProjectConfigProperties.class)
public class DemoawsfilesApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoawsfilesApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(IBookRepository repository){
	return  args -> {
		List<Book> bookList = List.of(
				Book.builder().price(12).name("book 1").build(),
				Book.builder().price(23).name("book 2").build(),
				Book.builder().price(34).name("book 3").build(),
				Book.builder().price(45).name("book 4").build()
		);
		repository.saveAll(bookList);
	};
	}

}
