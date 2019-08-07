package com.bakery.assignment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.bakery.dtos.BakeryCmd;
import com.bakery.service.BakeryService;


@ComponentScan(basePackages = "com.bakery")
@SpringBootApplication
public class BakeryApplication implements CommandLineRunner {

	@Autowired
	private BakeryService bakeryService;
	
	public static void main(String[] args) {
		SpringApplication.run(BakeryApplication.class, args);
	}
	
	@Override
    public void run(String... args) throws Exception {
		
		BakeryCmd cmd = new BakeryCmd();
		
		if(args[0].equals("") || args[1].equals("")) {
			System.out.println("Invalid Arguments");
		} else {
			cmd.setOrderQuantity(Integer.parseInt(args[0]));
			cmd.setCode(args[1]);
			System.out.println(bakeryService.getPrePackedItemPrice(cmd));
		}
	}

}
