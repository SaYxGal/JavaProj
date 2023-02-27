package com.labwork01.app;

import com.labwork01.app.actions.service.ActionService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AppApplicationTests {
	@Autowired
	ActionService actionService;

	@Test
	void testMethodSumInt() {
		final String res = actionService.sum(1, 2, "int");
		Assertions.assertEquals("3", res);
	}

	@Test
	void testMethodSumString() {
		final String res = actionService.sum("1", "2", "string");
		Assertions.assertEquals("12", res);
	}

	@Test
	void testMethodMinusInt() {
		final String res = actionService.minus(100, 123, "int");
		Assertions.assertEquals("-23", res);
	}

	@Test
	void testMethodMinusString() {
		final String res = actionService.minus("21324", "123", "string");
		Assertions.assertEquals("24", res);
	}

	@Test
	void testMethodMultInt() {
		final String res = actionService.multiply(1, 2, "int");
		Assertions.assertEquals("2", res);
	}

	@Test
	void testMethodMultString() {
		final String res = actionService.multiply("afc", "ffda", "string");
		Assertions.assertEquals("afffcda", res);
	}

	@Test
	void testMethodExactInt() {
		final Boolean res = actionService.exact(123, 123, "int");
		Assertions.assertEquals(true, res);
	}

	@Test
	void testMethodExactString() {
		final Boolean res = actionService.exact("abc", "ab", "string");
		Assertions.assertEquals(false, res);
	}
}
