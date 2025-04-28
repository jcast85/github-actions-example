package com.jc.crud.example;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class CrudExampleApplicationTests {

	@Test
	void contextLoads() {
		try (MockedStatic<SpringApplication> springApplicationMockedStatic = Mockito.mockStatic(SpringApplication.class)) {
			String[] args = new String[0];
			springApplicationMockedStatic.when(() -> SpringApplication.run(Mockito.eq(CrudExampleApplication.class), Mockito.any(String[].class))).thenAnswer((Answer<Void>) invocation -> null);
			CrudExampleApplication.main(args);
			springApplicationMockedStatic.verify(() -> SpringApplication.run(CrudExampleApplication.class, args));
		}
	}

}
