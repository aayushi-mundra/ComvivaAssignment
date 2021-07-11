package assignment.ques1;

import static java.util.concurrent.TimeUnit.SECONDS;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import assignment.ques1.entity.BarbarShop;
import assignment.ques1.entity.Customer;
import assignment.ques1.service.BarbarShopService;
import assignment.ques1.tasks.BarbarTask;
import assignment.ques1.tasks.CustomerTask;

public class BarberShopImpl {

	private ExecutorService executorService;
	private BarbarShop barbarShop;
	private BarbarShopService barbarShopService;
	
	public ExecutorService getExecutorService() {
		return executorService;
	}

	public void setExecutorService(ExecutorService executorService) {
		this.executorService = executorService;
	}


	public BarbarShop getBarbarShop() {
		return barbarShop;
	}


	public void setBarbarShop(BarbarShop barbarShop) {
		this.barbarShop = barbarShop;
	}


	public BarbarShopService getBarbarShopService() {
		return barbarShopService;
	}


	public void setBarbarShopService(BarbarShopService barbarShopService) {
		this.barbarShopService = barbarShopService;
	}


	public static void main(String[] args) {
		int chairQty = 10;	
		BarberShopImpl barberShopImpl = new BarberShopImpl();
		barberShopImpl.init(chairQty);
		barberShopImpl.terminate();
	}
	
	
	public void init(int capacity) {
		int chairQty = 10;	
		barbarShop =  new BarbarShop("Hair Affair", chairQty);
		barbarShopService = BarbarShopService.getInstance(barbarShop);//new BarbarShopService(barbarShop);
		
		System.out.println("Welcome to Barber Shop : "+barbarShop.getShopName()+"!");
		
		//total n+1 threads, one thread for barbar and n for customer
		executorService = Executors.newFixedThreadPool(chairQty+1);
		
		BarbarTask barbarTask = BarbarTask.getInstance(barbarShopService);
    	executorService.execute(barbarTask);
    	
    	for (int i = 1; i <=chairQty; i++) {
    		Customer customer = new Customer(i);
    		CustomerTask customerTask = new CustomerTask(customer, barbarShopService);
        	executorService.execute(customerTask);
		}
    	
    	try {
			Thread.sleep((long)(Math.random() * 1000));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void terminate() {
		executorService.shutdown();												
    	try {
			executorService.awaitTermination(30, SECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}	
    	
    	System.out.println("Thank You. Shop Closed!");
	}
}
