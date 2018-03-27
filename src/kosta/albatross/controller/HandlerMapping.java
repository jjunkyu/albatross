package kosta.albatross.controller;

public class HandlerMapping {
	public static HandlerMapping instance = new HandlerMapping();
	
	private HandlerMapping() {}
	
	public static HandlerMapping getInstance() {
		return instance;
	}
	
	public Controller create(String command) {
		Controller ctrl = null;
		if(command.equals("")) {
			//ctrl = new Controller();
		}
		return ctrl;
	}
}
