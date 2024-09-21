package kr.ac.kopo.ui;

import kr.ac.kopo.vo.MemberVO;

public class AddMemberUI extends BaseUI{

	@Override
	public void execute() throws Exception {
		
		System.out.println("<<< 회원가입 >>>");
		String name = scanStr("이름을 입력하세요 : ");
		String id;
		while (true) {
			String tmp = scanStr("아이디를 입력하세요 : ");
			
			if (memberService.findOneById(tmp) != null) {
				System.out.println("이미 사용중인 회원 아이디입니다. 다시 입력하세요.");
			} else {
				id = tmp;
				break;
			}
		}
		String pwd = scanStr("비밀번호를 입력하세요 : ");
		String hp = scanStr("휴대폰번호를 입력하세요 : ");
		
		// 회원가입
		MemberVO member = new MemberVO(name, id, pwd, hp);
		memberService.addMember(member);
		
		System.out.println("회원가입이 완료하였습니다.");
		
	}

}
