package kr.ac.kopo.ui;

import kr.ac.kopo.vo.MemberVO;

public class UpdateMemberUI extends BaseUI {

	private MemberVO loginUser = null;

	public UpdateMemberUI(MemberVO memberVO) {
		this.loginUser = memberVO;
	}

	@Override
	public void execute() throws Exception {

		loop1: while (true) {
			System.out.print("1. 비밀번호 변경\t");
			System.out.print("2. 이름 변경\t");
			System.out.println("3. 휴대폰 번호 변경\t");
			System.out.println("0. 회원 메뉴로 돌아가기\t");
			int type = scanInt("항목을 선택하세요 : ");
			switch (type) {
			case 1:
				String newPwd1 = scanStr("새 비밀번호를 입력하세요 : ");
				String newPwd2 = scanStr("새 비밀번호를 다시 입력하세요 : ");
				if (newPwd1.equals(newPwd2)) {
					loginUser.setPwd(newPwd1);
					memberService.updateMemberInfo(loginUser);
					System.out.println("비밀번호 변경 완료 ! ");
				} else {
					System.out.println("비밀번호를 다르게 입력하셨습니다.");
				}
				break;
			case 2:
				String newName = scanStr("새 이름을 입력하세요 : ");
				loginUser.setName(newName);
				memberService.updateMemberInfo(loginUser);
				System.out.println("이름 변경 완료 ! ");
				break;
			case 3:
				String newHp = scanStr("새 휴대폰 번호를 입력하세요 : ");
				loginUser.setHp(newHp);
				memberService.updateMemberInfo(loginUser);
				System.out.println("휴대폰번호 변경 완료 ! ");
				break;
			case 0:
				break loop1;
			}

		}

	}

}
