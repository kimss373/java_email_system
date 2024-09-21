package kr.ac.kopo.ui;

import kr.ac.kopo.vo.MemberVO;
import kr.ac.kopo.vo.EmailVO;

public class SendEmailUI extends BaseUI{
	
	private MemberVO loginUser = null;

	public SendEmailUI(MemberVO memberVO) {
		this.loginUser = memberVO;
	}
	
	@Override
	public void execute() throws Exception {
		
		String receiver = null; 
		MemberVO receivedMemberVO = null;
		while (true) {
			
			String tmp = scanStr("받는사람 입력(되돌아가려면 'exit'입력) : ");
			String[] tmpArr = tmp.split("@");
			receivedMemberVO = memberService.findOneById(tmpArr[0]);
			
			if (tmp.length() >= 4 && tmp.substring(tmp.length()-4,tmp.length()).equals("exit")) {
				return;
			}
			
			if (tmpArr.length != 2) {
				System.out.println("이메일 형식이 아닙니다.");
			} else if (receivedMemberVO == null || !tmpArr[1].equals("beaver.com")) {
				System.out.println("등록된 사용자가 없습니다.");
			} else {
				receiver = tmp;
				break;
			}
		
		}
		
		String title = scanStr("제목을 입력하세요(되돌아가려면 'exit'입력) : ");
		if (title.length() >= 4 && title.substring(title.length()-4,title.length()).equals("exit")) {
			return;
		}
		String content = scanStr("내용을 입력하세요(되돌아가려면 'exit'입력) : ");
		if (content.length() >= 4 && content.substring(content.length()-4,content.length()).equals("exit")) {
			return;
		}
		
		EmailVO sendEmailVO = new EmailVO(title, content, loginUser.getMemberCd(), receivedMemberVO.getMemberCd(), "N");
		
		emailService.sendEmail(sendEmailVO, receivedMemberVO);
		
		System.out.println("메일 전송이 성공했습니다.");
		
	}

}
