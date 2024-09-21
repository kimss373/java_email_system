package kr.ac.kopo.ui;

import java.util.Scanner;

import kr.ac.kopo.service.EmailServiceFactory;
import kr.ac.kopo.service.MemberService;
import kr.ac.kopo.service.MemberServiceFactory;
import kr.ac.kopo.util.EmailUtil;
import kr.ac.kopo.service.AdminService;
import kr.ac.kopo.service.AdminServiceFactory;
import kr.ac.kopo.service.EmailService;

public abstract class BaseUI implements IEmailUI{

	private Scanner sc;
	protected EmailService emailService;
	protected MemberService memberService;
	protected AdminService adminService;
	
	public BaseUI() {
		sc = new Scanner(System.in);
		emailService = EmailServiceFactory.getInstance();
		memberService = MemberServiceFactory.getInstance();
		adminService = AdminServiceFactory.getInstance();
	}
	
	protected String scanStr(String msg) {
		System.out.print(msg);
		return sc.nextLine();
	}

	protected int scanInt(String msg) {
		return Integer.parseInt(scanStr(msg));
	}
	
}
