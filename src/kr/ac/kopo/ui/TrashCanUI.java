package kr.ac.kopo.ui;

import java.util.List;

import kr.ac.kopo.vo.MemberVO;
import kr.ac.kopo.vo.SelectEmailVO;

public class TrashCanUI extends BaseUI{

	private MemberVO loginUser = null;

	public TrashCanUI(MemberVO memberVO) {
		this.loginUser = memberVO;
	}
	
	@Override
	public void execute() throws Exception {
		
		List<SelectEmailVO> list = emailService.getAllTrashEmail(loginUser);
		
		loop1: while (true) {
			System.out.println(
					"<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<휴지통>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			System.out.println(
					"-------------------------------------------------------------------------------------------------------------------");
			System.out.println("\t메일번호\t\t보낸 사람\t\t\t제목\t\t\t시간");
			System.out.println(
					"-------------------------------------------------------------------------------------------------------------------");
			for (int i = 0; i < list.size(); i++) {
				SelectEmailVO tmp = list.get(i);
				if (tmp.getReceiverId().equals(loginUser.getId())) {
					if (tmp.getTitle().length() > 8) {
						System.out.println("[받은 메일] " + i + "\t\t" + tmp.getSenderId() + "\t\t" + tmp.getTitle().substring(0, 8) + "..." + "\t\t" + tmp.getSendDate());
					} else {
						System.out.println("[받은 메일] " + i + "\t\t" + tmp.getSenderId() + "\t\t" + tmp.getTitle() + "\t\t" + tmp.getSendDate());
					}
				} else if (tmp.getSenderId().equals(loginUser.getId())) {
					if (tmp.getTitle().length() > 8) {
						System.out.println("[보낸 메일] " + i + "\t\t" + tmp.getReceiverId() + "\t\t" + tmp.getTitle().substring(0, 8) + "\t\t" + tmp.getSendDate());
					} else {
						System.out.println("[보낸 메일] " + i + "\t\t" + tmp.getReceiverId() + "\t\t" + tmp.getTitle() + "\t\t" + tmp.getSendDate());
					}
				}
			}
			System.out.println(
					"-------------------------------------------------------------------------------------------------------------------");

			loop2: while (true) {
				System.out.print("1. 조회하기\t");
				System.out.print("2. 휴지통 비우기\t");
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
						System.out.println("1. 복원하기\t 2. 삭제하기 \t 0. 돌아가기");
						int sel = scanInt("항목을 선택하세요 : ");
						switch(sel) {
						case 1:
							emailService.restoreEmail(list.get(select));
							System.out.println("복원 완료 !");
							list.remove(select);
							break loop2;
						case 2:
							emailService.emptyOneBin(list.get(select));
							System.out.println("삭제 완료 !");
							list.remove(select);
							break loop2;
						case 0:
							break loop2;
						}
						
					} else {
						System.out.println("없는 번호입니다.");
					}
						
					break;
				case 2:
					emailService.emptyAllBin(list);
					list.clear();
					break loop2;
				case 0:
					break loop1;
				}

			}
		}
		
		
		
	}

}
