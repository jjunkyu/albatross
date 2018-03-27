package kosta.albatross.controller;

public class HandlerMapping {
	public static HandlerMapping instance = new HandlerMapping();
	
	private HandlerMapping() {}
	
	public static HandlerMapping getInstance() {
		return instance;
	}
	
	public Controller create(String command) {
		Controller ctrl = null;
		if(command.equals("home")) {
			ctrl = new HomeController();
		} else if(command.equals("registerEmployee")) {
			ctrl = new RegisterEmployeeController();
		} else if(command.equals("employeeList")) {
			ctrl = new EmployeeListController();
		} else if(command.equals("registerDepartment")) {
			ctrl = new RegisterDepartmentController();
		}
		return ctrl;
	}
}
