package com.pacifique;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class DemoawsfilesApplicationTests {
	private static final String APP_NAME = "books";

	@Test
	void contextLoads() {
		assertEquals(APP_NAME,"books");
	}

}
