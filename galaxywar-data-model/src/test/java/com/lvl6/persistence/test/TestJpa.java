package com.lvl6.persistence.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-db.xml")
public class TestJpa {
	
	Logger log = LoggerFactory.getLogger(getClass());
	
	@Test
	public void testSetup() {
		log.info("Testing JPA setup");
	}
}
