package kr.ac.kopo.ui;

import java.util.List;

import kr.ac.kopo.vo.MemberVO;
import kr.ac.kopo.vo.SelectEmailVO;
import kr.ac.kopo.util.EmailUtil;
import kr.ac.kopo.vo.EmailVO;

public class MemberManagementUI extends BaseUI {


	@Override
	public void execute() throws Exception {
		List<MemberVO> list = adminService.getAllMember();
		loop1: while (true) {
			System.out.println(
					"<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<전체 회원 조회>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			System.out.println(
					"--------------------------------------------------------------------------------------------");
			System.out.println("회원번호\t\t회원ID\t\t이름\t\t전화번호\t\t가입일자");
			System.out.println(
					"--------------------------------------------------------------------------------------------");
			for (int i = 0; i < list.size(); i++) {
				MemberVO tmp = list.get(i);
				System.out.println(tmp.getMemberCd() + "\t\t" + tmp.getId() + "\t\t" + tmp.getName() + "\t\t"
						+ tmp.getHp() + "\t\t" + tmp.getJoinDate());
				System.out.println(
						"--------------------------------------------------------------------------------------------");
			}
			System.out.println(
					"--------------------------------------------------------------------------------------------");

			System.out.println("0. 관리자 메뉴로 돌아가기\t");
			int type = scanInt("항목을 선택하세요 : ");
			break;
		}

	}

}
