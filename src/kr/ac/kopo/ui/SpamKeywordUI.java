package kr.ac.kopo.ui;

import java.util.List;

import kr.ac.kopo.vo.MemberVO;
import kr.ac.kopo.vo.SelectEmailVO;
import kr.ac.kopo.vo.SpamKeywordVO;

public class SpamKeywordUI extends BaseUI {

	private MemberVO loginUser = null;

	public SpamKeywordUI(MemberVO memberVO) {
		this.loginUser = memberVO;
	}

	@Override
	public void execute() throws Exception {

		while (true) {

			List<SpamKeywordVO> list = emailService.getAllSpamKeyword(loginUser);

			System.out.println("<<<<<<<<<<<<<<<<<<스팸메일 키워드>>>>>>>>>>>>>>>>>>");
			System.out.println("------------------------------------------------");
			System.out.println("키워드번호\t\t\t\t키워드");
			System.out.println("------------------------------------------------");
			for (int i = 0; i < list.size(); i++) {
				System.out.println(i + "\t\t\t\t" + list.get(i).getKeywordNm());
			}
			System.out.println("------------------------------------------------");

			loop1: while (true) {
				int select = scanInt("1. 키워드 등록  2. 키워드 삭제  0. 돌아가기 : ");
				switch (select) {
				case 1:
					String keyword = scanStr("키워드를 입력하세요 : ");
					emailService.createSpamKeyword(loginUser, keyword);
					list = emailService.getAllSpamKeyword(loginUser);
					break loop1;
				case 2:
					int delete = scanInt("삭제할 키워드 번호 입력 : ");
					emailService.deleteSpamKeyword(list.get(delete));
					list.remove(delete);
					break loop1;
				case 0:

					return;
				}
			}

		}
	}

}
