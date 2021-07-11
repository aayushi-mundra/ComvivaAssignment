package assignment.ques1.test;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.jupiter.api.Test;

import assignment.ques1.BarberShopImpl;
import assignment.ques1.entity.BarbarShop;
import assignment.ques1.entity.Customer;
import assignment.ques1.service.BarbarShopService;
import assignment.ques1.tasks.BarbarTask;
import assignment.ques1.tasks.CustomerTask;

class BarbarShopTest {

	@Test
	void test() {
			int chairQty = 2;	
			BarbarShop barbarShop =  new BarbarShop("Hair Affair", chairQty);
			BarbarShopService barbarShopService = BarbarShopService.getInstance(barbarShop);//new BarbarShopService(barbarShop);
			
			System.out.println("Welcome to Barber Shop : "+barbarShop.getShopName()+"!");
			
			//total n+1 threads, one thread for barbar and n for customer
			ExecutorService executorService = Executors.newFixedThreadPool(chairQty+1);
			
			BarbarTask barbarTask = BarbarTask.getInstance(barbarShopService);
	    	executorService.execute(barbarTask);
	    	
	    	//1.Initially barbar should not be occupied
	    	assertFalse(barbarShopService.getIsBarbarOccupied().get());
	    	
	    	//2. Customer Task Queue should be empty
	    	assertTrue(barbarShopService.getCustomerTaskQueue().size() == 0);
	    	
	    	int i = 1;
    		Customer customer = new Customer(i);
    		CustomerTask customerTask = new CustomerTask(customer, barbarShopService);
        	executorService.execute(customerTask);
	    	
	    	for (;i<=chairQty; i++) {
	    		Customer customer1 = new Customer(i);
	    		CustomerTask customerTask1 = new CustomerTask(customer1, barbarShopService);
	        	executorService.execute(customerTask1);
			}
	    	
	    	try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	    	
	    	try {
				Thread.sleep((long)(Math.random() * 1000));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	    	
	    	executorService.shutdown();												
	    	try {
				executorService.awaitTermination(30, SECONDS);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}	
	    	
	    	System.out.println("Thank You. Shop Closed!");
		
	}

}
