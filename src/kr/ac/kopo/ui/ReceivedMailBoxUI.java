package kr.ac.kopo.ui;

import java.util.List;

import kr.ac.kopo.vo.MemberVO;
import kr.ac.kopo.vo.SelectEmailVO;
import kr.ac.kopo.util.EmailUtil;
import kr.ac.kopo.vo.EmailVO;

public class ReceivedMailBoxUI extends BaseUI {

	private MemberVO loginUser = null;
	private EmailUtil emailUtil = null;

	public ReceivedMailBoxUI(MemberVO memberVO) {
		this.loginUser = memberVO;
		this.emailUtil = new EmailUtil();
	}

	@Override
	public void execute() throws Exception {
		loop1: while (true) {
			List<SelectEmailVO> list = emailService.getReceivedEmail(loginUser);
			System.out.println(
					"<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<받은메일함>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			System.out.println(
					"-------------------------------------------------------------------------------------------------------------------");
			System.out.println("메일번호\t\t보낸 사람\t\t제목\t\t\t시간");
			System.out.println(
					"-------------------------------------------------------------------------------------------------------------------");
			for (int i = 0; i < list.size(); i++) {
				SelectEmailVO tmp = list.get(i);
				if (tmp.getTitle().length()>8) {
					if (tmp.getCheckRead().equals("N")) {
						System.out.println(i + "\t\t" + tmp.getSenderId() + "\t\t[" + tmp.getTitle().substring(0, 8) + "...]" + "\t\t" + tmp.getSendDate());
					} else {
						System.out.println(i + "\t\t" + tmp.getSenderId() + "\t\t" + tmp.getTitle().substring(0, 8) + "..." + "\t\t" + tmp.getSendDate());
					}
					
				}else {
					if (tmp.getCheckRead().equals("N")) {
						System.out.println(i + "\t\t" + tmp.getSenderId() + "\t\t[" + tmp.getTitle() + "]\t\t\t" + tmp.getSendDate());
					}else {
						System.out.println(i + "\t\t" + tmp.getSenderId() + "\t\t" + tmp.getTitle() + "\t\t\t" + tmp.getSendDate());
					}
				}
			}
			System.out.println(
					"-------------------------------------------------------------------------------------------------------------------");

			loop2: while (true) {
				System.out.print("1. 조회하기\t");
				System.out.print("2. 삭제하기\t");
				System.out.println("0. 회원 메뉴로 돌아가기\t");
				int type = scanInt("항목을 선택하세요 : ");
				switch (type) {
				case 1:
					int select = scanInt("조회할 번호를 입력하세요 : ");
					if (select >= 0 && select < list.size()) {
						emailService.updateCheckRead(list.get(select));
						System.out.println(
								"---------------------------------------------------------------------------------------------------------------------------------");
						System.out.println(list.get(select).getTitle());
						System.out.println("보낸사람 : " + list.get(select).getSenderId() + ", 받는사람 : "
								+ list.get(select).getReceiverId() + "\t" + list.get(select).getSendDate());
						System.out.println(
								"---------------------------------------------------------------------------------------------------------------------------------");
						System.out.println(list.get(select).getContent());
						System.out.println(
								"---------------------------------------------------------------------------------------------------------------------------------");
						int sel = scanInt("1. 삭제하기  2. 스팸신고  0. 돌아가기 : ");
						switch (sel) {
						case 1:
							list = emailUtil.deleteMail(list, loginUser, select);
							break;
						case 2:
							emailService.reportSpamMail(loginUser, list.get(select));
							list.remove(select);
							System.out.println("스팸신고 완료");
							break;
						case 0:
							break loop2;

						}
						break loop2;
					} else {
						System.out.println("없는 번호입니다.");
					}

					break;
				case 2:
					list = emailUtil.deleteMail(list, loginUser);
					break loop2;
				case 0:
					break loop1;
				}

			}
		}

	}

}
