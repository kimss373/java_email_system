package kr.ac.kopo.service;

public class AdminServiceFactory {

	private static AdminService service;

	public static AdminService getInstance() {
		if (service == null) {
			service = new AdminService();
		}
		return service;
	}

}
