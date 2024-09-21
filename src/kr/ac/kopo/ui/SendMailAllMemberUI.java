package kr.ac.kopo.ui;

import kr.ac.kopo.vo.EmailVO;

public class SendMailAllMemberUI extends BaseUI {

	@Override
	public void execute() throws Exception {
		
		String title = scanStr("제목을 입력하세요(되돌아가려면 'exit'입력) : ");
		if (title.length() >= 4 && title.substring(title.length()-4,title.length()).equals("exit")) {
			return;
		}
		title = "[공지]" + title;
		String content = scanStr("내용을 입력하세요(되돌아가려면 'exit'입력) : ");
		if (content.length() >= 4 && content.substring(content.length()-4,content.length()).equals("exit")) {
			return;
		}
		
		EmailVO sendEmailVO = new EmailVO(title, content, 1, -1, "N");
		
		adminService.sendEmailToAll(sendEmailVO);
		
		System.out.println("메일 전송이 성공했습니다.");

	}

}
