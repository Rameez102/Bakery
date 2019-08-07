package com.bakery.assignment;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class BakeryApplicationTest {
	
	@Test
	public void test() throws Exception{
		BakeryApplication.main(new String[] {"10","VS5"});
	}

}
