package com.redsun.multi.thread;

import java.util.List;
import java.util.concurrent.RecursiveAction;

public class Task extends RecursiveAction{

	private List<Product> products;
	
	private int first;
	private int last;
	
	/**
	 * 用来存储产品价格的增加额
	 */
	private double increment;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2533653563773695577L;

	@Override
	protected void compute() {
		if(last - first < 10){
			updatePrices();// 增加产品的价格
		} else {
			int middle = (last + first)/2;
			System.out.printf("Task: Pending tasks:%s\n", getQueuedTaskCount());
			Task task1 = new Task(products, first,middle + 1,increment);
			Task task2 = new Task(products, middle + 1,last,increment);
			invokeAll(task1, task2);
		}
		
	}

	private void updatePrices() {
		for (int i = first; i < last; i++) {
			Product product = products.get(i);
			product.setProce(product.getProce()*(1+increment));
		}
		
	}

	public Task(List<Product> products, int first, int last, double increment) {
		super();
		this.products = products;
		this.first = first;
		this.last = last;
		this.increment = increment;
	}
	
}
