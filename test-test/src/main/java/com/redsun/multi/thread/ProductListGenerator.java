package com.redsun.multi.thread;

import java.util.ArrayList;
import java.util.List;

public class ProductListGenerator {

	public List<Product> generate(int size) {
		List<Product> retList = new ArrayList<Product>();
		for(int i=0;i<size;i++){
			Product product = new Product(); 
			product.setName("Product" + i);
			product.setProce(10);
			retList.add(product);
		}
		return retList;
	}
}
