package kr.ac.kopo.ui;

import kr.ac.kopo.vo.JailVO;
import kr.ac.kopo.vo.MemberVO;

public class LoginUI extends BaseUI{

	@Override
	public void execute() throws Exception {
		
		String id = scanStr("아이디를 입력하세요 : ");
		String pwd = scanStr("비밀번호를 입력하세요 : ");
		
		// 아이디 비번 맞는지 확인
		MemberVO memberVO = memberService.login(id, pwd);
		if(memberVO != null) {
			if (id.equals("admin")) {
				System.out.println("관리자 모드입니다.");
				new AdminUI(memberVO).execute();
			} else {
				JailVO jailVO = adminService.isJailMember(memberVO.getMemberCd());
				if (jailVO != null) {
					System.out.println("정지된 아이디 입니다. 석방 : " + jailVO.getReleaseDay());
				} else {
					System.out.println("로그인을 완료하였습니다.");
					new EmailUI(memberVO).execute();
				}
			}
		} else {
			System.out.println("아이디와 비밀번호를 확인하세요.");
		}
		
	}

}
