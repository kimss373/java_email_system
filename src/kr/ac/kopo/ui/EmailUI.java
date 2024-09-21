package kr.ac.kopo.ui;

import kr.ac.kopo.vo.MemberVO;

public class EmailUI extends BaseUI {

	private MemberVO loginUser = null;
	private int cnt;

	public EmailUI(MemberVO memberVO) {
		loginUser = memberVO;
	}

	private int choiceMenu() {
		System.out.println();
		System.out.println("****************************** MENU ******************************");
		System.out.println(loginUser.getName() + "(" + loginUser.getId() + ")" + "님 로그인중...");
		System.out.print("1. 전체메일함\t");
		System.out.print("2. 받은메일함[" + cnt + "]\t");
		System.out.print("3. 보낸메일함\t");
		System.out.println("4. 스팸메일함\t");
		System.out.print("5. 휴지통\t\t");
		System.out.print("6. 회원정보 수정\t");
		System.out.print("7. 스팸키워드\t");
		System.out.println("8. 메일보내기");
		System.out.print("0. 로그아웃");
		System.out.println();

		int type = scanInt("항목을 선택하세요 : ");
		return type;
	}

	@Override
	public void execute() throws Exception {
		while (true) {
			cnt = emailService.getUnreadMailCnt(loginUser);
			IEmailUI ui = null;
			int type = choiceMenu();

			switch (type) {
			case 1:
				ui = new AllEmailUI(loginUser);
				break;
			case 2:
				ui = new ReceivedMailBoxUI(loginUser);
				break;
			case 3:
				ui = new SentMailBoxUI(loginUser);
				break;
			case 4:
				ui = new SpamMailBoxUI(loginUser);
				break;
			case 5:
				ui = new TrashCanUI(loginUser);
				break;
			case 6:
				ui = new UpdateMemberUI(loginUser);
				break;
			case 7:
				ui = new SpamKeywordUI(loginUser);
				break;
			case 8:
				ui = new SendEmailUI(loginUser);
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
