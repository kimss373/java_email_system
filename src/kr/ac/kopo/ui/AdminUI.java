package kr.ac.kopo.ui;

import kr.ac.kopo.vo.MemberVO;

public class AdminUI extends BaseUI {

	private MemberVO loginUser = null;

	public AdminUI(MemberVO memberVO) {
		loginUser = memberVO;
	}

	private int choiceMenu() {
		System.out.println();
		System.out.println("****************************** MENU ******************************");

		System.out.println(loginUser.getName() + "(" + loginUser.getId() + ")" + "관리자 모드...");
		System.out.print("1. 전체 회원 조회\t");
		System.out.print("2. 스팸메일 제보함\t\t");
		System.out.println("3. 전체에게 메일 전송\t");
		System.out.print("0. 로그아웃");
		System.out.println();

		int type = scanInt("항목을 선택하세요 : ");
		return type;
	}

	@Override
	public void execute() throws Exception {
		while (true) {
			IEmailUI ui = null;
			int type = choiceMenu();

			switch (type) {
			case 1:
				ui = new MemberManagementUI();
				break;
			case 2:
				ui = new ReportedSpamMailBoxUI();
				break;
			case 3:
				ui = new SendMailAllMemberUI();
				break;
			case 0:
				System.out.println("로그아웃 성공!");
				return;
			}

			if (ui != null) {
				ui.execute();
			} else {
				System.out.println("잘 못 선택하셨습니다.");
			}
		}

	}
}
