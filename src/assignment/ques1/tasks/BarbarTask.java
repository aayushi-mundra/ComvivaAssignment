package assignment.ques1.tasks;

import assignment.ques1.service.BarbarShopService;

public class BarbarTask extends Thread {
	
	private static BarbarTask barbarTask  = null;
	private BarbarShopService barbarShopService;
	
	private BarbarTask(BarbarShopService barbarShopService) {
		this.barbarShopService = barbarShopService;
	}
	
	public static BarbarTask getInstance(BarbarShopService barbarShopService) {
		if(barbarTask == null) {
			barbarTask = new BarbarTask(barbarShopService);
		} 
		return barbarTask;
	}
	
	@Override
	public void run() {
		while(true) {
			barbarShopService.cutHair();
		}
	}
	
}
