package kr.ac.kopo.ui;

import kr.ac.kopo.vo.MemberVO;

public class FindMemberIdUI extends BaseUI{

	@Override
	public void execute() throws Exception {
		
		String name = scanStr("이름을 입력하세요 : ");
		String hp = scanStr("휴대번호를 입력하세요 : ");
		
		MemberVO memberVO = null;
		// 아이디 비번 맞는지 확인
		memberVO = memberService.findOneByNameHp(name, hp);
		if(memberVO != null) {
			System.out.println("아이디 : " + memberVO.getId());
		} else {
			System.out.println("등록된 회원 정보가 없습니다.");
		}
		
		
		
		
		
	}

}
