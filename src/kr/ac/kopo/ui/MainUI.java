package kr.ac.kopo.ui;

public class MainUI extends BaseUI {

	private int choiceMenu() {
		System.out.println();
		System.out.println("****************************** MENU ******************************");

		System.out.print("1. 로그인\t\t");
		System.out.print("2. 회원가입\t");
		System.out.print("3. 아이디 찾기\t");
		System.out.println("4. 비밀번호 찾기\t");
		System.out.print("0. 프로그램 종료");
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
				ui = new LoginUI();
				break;
			case 2:
				ui = new AddMemberUI();
				break;
			case 3:
				ui = new FindMemberIdUI();
				break;
			case 4:
				ui = new FindMemberPwdUI();
				break;
			case 0:
				ui = new ExitUI();
			}

			if (ui != null) {
				ui.execute();
			} else {
				System.out.println("잘 못 선택하셨습니다.");
			}
		}

	}
}
