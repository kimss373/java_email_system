package kr.ac.kopo.ui;

import kr.ac.kopo.vo.MemberVO;

public class FindMemberPwdUI extends BaseUI{

	@Override
	public void execute() throws Exception {
		
		MemberVO memberVO = null;
		
		String id = scanStr("비밀번호를 찾고자하는 아이디를 입력하세요 : ");
		
		if(memberService.findOneById(id) != null) {
			String name = scanStr("이름을 입력하세요 : ");
			String hp = scanStr("휴대번호를 입력하세요 : ");
			memberVO = memberService.findOneByIdNameHp(id, name, hp);
			if (memberVO == null) {
				System.out.println("등록된 회원정보와 일치하지 않습니다.");
			} else {
				String newPwd = scanStr("새로운 비밀번호를 입력하세요 : ");
				String newPwd2 = scanStr("새로운 비밀번호를 다시 입력하세요 : ");
				if (newPwd.equals(newPwd2)) {
					memberVO.setPwd(newPwd);
					memberService.updateMemberInfo(memberVO);
					System.out.println("비밀번호 재설정 완료 !");
				} else {
					System.out.println("변경 실패 ..");
				}
			}
		} else {
			System.out.println("등록된 회원 정보가 없습니다.");
		}
		
		
		
		
		
	}

}
