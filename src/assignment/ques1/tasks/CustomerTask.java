package assignment.ques1.tasks;

import assignment.ques1.entity.Customer;
import assignment.ques1.service.BarbarShopService;

public class CustomerTask extends Thread {

	private Customer customer;
	private BarbarShopService barbarShopService;
	
	public CustomerTask(Customer customer, BarbarShopService barbarShopService) {
		this.customer = customer;
		this.barbarShopService = barbarShopService;
	}
	
	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public BarbarShopService getBarbarShopService() {
		return barbarShopService;
	}

	public void setBarbarShopService(BarbarShopService barbarShopService) {
		this.barbarShopService = barbarShopService;
	}

	@Override
	public void run() {
		barbarShopService.acquireSeat(this);
	}
}
