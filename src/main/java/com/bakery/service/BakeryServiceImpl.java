package com.bakery.service;


import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.bakery.dtos.BakeryCmd;
import com.bakery.dtos.BakeryEntry;

@Service
public class BakeryServiceImpl implements BakeryService{
	
	//This is service method to get the order from user and process the payments
	public String getPrePackedItemPrice(BakeryCmd cmd) {
		
		if(cmd.getCode() == null || cmd.getOrderQuantity() == null)
			return "Please enter the correct input";
		
		
		Map<String,List<BakeryEntry>> bakery = addBakery();
		return getItemPrice(bakery.get(cmd.getCode()), cmd);
	}
	
	//This method add the bakery items in Map 
	private Map<String,List<BakeryEntry>> addBakery(){
		
		Map<String,List<BakeryEntry>> entry = new HashMap<String,List<BakeryEntry>>();
		
		List<BakeryEntry> vegamiteList = new ArrayList<BakeryEntry>();
		vegamiteList.add(new BakeryEntry("Vegemite Scroll", BigDecimal.valueOf(6.99), 3));
		vegamiteList.add(new BakeryEntry("Vegemite Scroll", BigDecimal.valueOf(8.99), 5));
		
		List<BakeryEntry> blueberryList = new ArrayList<BakeryEntry>();
		blueberryList.add(new BakeryEntry("Blueberry Muffin", BigDecimal.valueOf(9.95), 2));
		blueberryList.add(new BakeryEntry("Blueberry Muffin", BigDecimal.valueOf(16.95), 5));
		blueberryList.add(new BakeryEntry("Blueberry Muffin", BigDecimal.valueOf(24.95), 8));
		
		List<BakeryEntry> croissantList = new ArrayList<BakeryEntry>();
		croissantList.add(new BakeryEntry("Croissant", BigDecimal.valueOf(5.95), 3));
		croissantList.add(new BakeryEntry("Croissant", BigDecimal.valueOf(9.95), 5));
		croissantList.add(new BakeryEntry("Croissant", BigDecimal.valueOf(16.95), 9));
		
		entry.put("VS5", vegamiteList);
		entry.put("MB11", blueberryList);
		entry.put("CF", croissantList);

		return entry;
	}
	
	//This method call the price calculator according to given order
	private String getItemPrice(List<BakeryEntry> list, BakeryCmd cmd) {
		
		String priceWithDollar = "";
		int[] vegemiteArray = new int[] {3, 5};
		int[] BlueberryArray = new int[] {2, 5, 8};
		int[] croissantArray = new int[] {3, 5, 9};
		
		if(cmd.getCode().equals("VS5")) {
			priceWithDollar = priceCalculator(cmd.getOrderQuantity(),vegemiteArray,list);
		}
		
		if(cmd.getCode().equals("MB11")) {
			priceWithDollar = priceCalculator(cmd.getOrderQuantity(),BlueberryArray,list);
		}
		
		if(cmd.getCode().equals("CF")) {
			priceWithDollar = priceCalculator(cmd.getOrderQuantity(),croissantArray,list);
		}
		
		return cmd.getOrderQuantity() +" "+cmd.getCode() + " = "  + priceWithDollar;
	}

	//This method calculate the price of given prepacket item
	private String priceCalculator(Integer orderQuantity, int packedItem[], List<BakeryEntry> list) {
		
		BigDecimal totalCost = BigDecimal.ZERO;
		StringBuilder consumePackets = new StringBuilder(); 
		
		Arrays.sort(packedItem);
	       
        int[] packetCount = new int[packedItem.length];

        int min = packedItem[0];
        int q;
        int r;
       
        for (int i = packedItem.length-1; i >= 0; i--) {
        q = orderQuantity / packedItem[i];
        r = orderQuantity % packedItem[i];
       
        if (r == 0) {
        	packetCount[i] = q;
        break;
       
        } else if (r >= min) {
        	packetCount[i] = q;
           
            if (i > 0) {
            	orderQuantity = r;
            }

            } else {

            if (i > 0) {
            	packetCount[i] = 0;
            } else {
            Arrays.fill(packetCount, 0);
            }
            }
        }
        
        for(int i=0; i<packetCount.length; i++) {
        	if(packetCount[i] > 0) {
     		   totalCost = totalCost.add(list.get(i).getPrice().multiply(new BigDecimal(packetCount[i])));
     		   consumePackets.append("\n");
     		   consumePackets.append(packetCount[i] + " X " + packedItem[i] + " "+NumberFormat.getCurrencyInstance(new Locale("en", "US")).format(list.get(i).getPrice()));
     	   }
        }
		return NumberFormat.getCurrencyInstance(new Locale("en", "US")).format(totalCost) + consumePackets;
	}
}
