package assignment.ques1.service;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicBoolean;

import assignment.ques1.entity.BarbarShop;
import assignment.ques1.tasks.CustomerTask;

public class BarbarShopService {

	private BarbarShop barbarShop;
	private Queue<CustomerTask> customerTaskQueue;
	private AtomicBoolean isBarbarOccupied;
	private static BarbarShopService barbarShopService;
	
	private BarbarShopService(BarbarShop barbarShop) {
		this.barbarShop = barbarShop;
		customerTaskQueue = new LinkedList<CustomerTask>();
		isBarbarOccupied = new AtomicBoolean(false);
	}
	
	public static BarbarShopService getInstance(BarbarShop barbarShop) {
		if(barbarShopService == null) {
			barbarShopService = new BarbarShopService(barbarShop);
		}
		return barbarShopService;
	}
	
	public BarbarShop getBarbarShop() {
		return barbarShop;
	}

	public void setBarbarShop(BarbarShop barbarShop) {
		this.barbarShop = barbarShop;
	}

	public Queue<CustomerTask> getCustomerTaskQueue() {
		return customerTaskQueue;
	}

	public void setCustomerTaskQueue(Queue<CustomerTask> customerTaskQueue) {
		this.customerTaskQueue = customerTaskQueue;
	}

	public AtomicBoolean getIsBarbarOccupied() {
		return isBarbarOccupied;
	}

	public void setIsBarbarOccupied(AtomicBoolean isBarbarOccupied) {
		this.isBarbarOccupied = isBarbarOccupied;
	}
	
	public void cutHair() {
		CustomerTask customerTask = null;
		synchronized(customerTaskQueue) {
			
			while(customerTaskQueue.isEmpty()) {
				System.out.println("Barber Sleeping : No Customers present.");
				try {
					customerTaskQueue.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			customerTask = customerTaskQueue.poll();
			System.out.println("Barber is awake for Customer : "+customerTask.getCustomer().getCustomerNumber());
		}
		
		isBarbarOccupied.compareAndExchange(false, true);
		long start = System.currentTimeMillis();
		System.out.println("Barber is cutting hair of Customer : "+customerTask.getCustomer().getCustomerNumber());
		try {
			Thread.sleep((long)(Math.random() * 1000));
			System.out.println("Barber is done cutting hair of Customer : "+customerTask.getCustomer().getCustomerNumber()+
					" time taken: "+(System.currentTimeMillis()-start)+" ms");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		isBarbarOccupied.compareAndExchange(true, false);
		
	}
	
	public void acquireSeat(CustomerTask customerTask) {
		if(customerTask == null) return;
		System.out.println("Customer : " + customerTask.getCustomer().getCustomerNumber()+" is entering");
		
		synchronized (customerTaskQueue) {
			if(customerTaskQueue.size() == barbarShop.getCapacity()) {
				System.out.println("Waiting Full : No chairs available");
				return;
			} else if(!isBarbarOccupied.get()) {
				customerTaskQueue.add(customerTask);
				customerTaskQueue.notify();
			} else {
				customerTaskQueue.add(customerTask);
				System.out.println("Customer : " + customerTask.getCustomer().getCustomerNumber()+" is waiting");
				if(customerTaskQueue.size() > 0) customerTaskQueue.notify();
			}
		}
	}
}
