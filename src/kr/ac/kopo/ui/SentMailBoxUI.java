package kr.ac.kopo.ui;

import java.util.List;

import kr.ac.kopo.vo.MemberVO;
import kr.ac.kopo.vo.SelectEmailVO;

public class SentMailBoxUI extends BaseUI {

	private MemberVO loginUser = null;

	public SentMailBoxUI(MemberVO memberVO) {
		this.loginUser = memberVO;
	}

	@Override
	public void execute() throws Exception {
		List<SelectEmailVO> list = emailService.getSentEmail(loginUser);
		loop1: while (true) {
			System.out.println(
					"<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<보낸메일함>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			System.out.println(
					"-------------------------------------------------------------------------------------------------------------------");
			System.out.println("메일코드\t\t받는 사람\t\t제목\t\t\t시간");
			System.out.println(
					"-------------------------------------------------------------------------------------------------------------------");
			for (int i = 0; i < list.size(); i++) {
				SelectEmailVO tmp = list.get(i);
				if (tmp.getTitle().length()>8) {
					System.out.println(i + "\t\t" + tmp.getReceiverId() + "\t\t" + tmp.getTitle().substring(0, 8) + "..." + "\t\t" + tmp.getSendDate());
					
				}else {
					System.out.println(i + "\t\t" + tmp.getReceiverId() + "\t\t" + tmp.getTitle() + "\t\t\t" + tmp.getSendDate());
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
						scanStr("돌아가려면 아무거나 입력 : ");
						break loop2;
					} else {
						System.out.println("없는 번호입니다.");
					}

					break;
				case 2:
					int delete = scanInt("삭제할 번호를 입력하세요 : ");
					if (delete >= 0 && delete < list.size()) {
						String checkDelete = scanStr("정말 삭제하시겠습니까?(y/n)");
						if (checkDelete.equals("y") || checkDelete.equals("Y")) {
							emailService.toTrashCan(loginUser, list.get(delete));
							System.out.println("삭제 완료");
							list.remove(delete);
						} 
					} else {
						System.out.println("없는 번호입니다.");
					}
					break loop2;
				case 0:
					break loop1;
				}

			}
		}

	}

}
