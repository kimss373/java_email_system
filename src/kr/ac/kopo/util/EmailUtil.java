package kr.ac.kopo.util;

import java.util.List;

import kr.ac.kopo.ui.BaseUI;
import kr.ac.kopo.vo.MemberVO;
import kr.ac.kopo.vo.SelectEmailVO;

public class EmailUtil extends BaseUI {

	public List<SelectEmailVO> deleteMail(List<SelectEmailVO> list, MemberVO loginUser) {
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

		return list;

	}

	public List<SelectEmailVO> deleteMail(List<SelectEmailVO> list, MemberVO loginUser, int idx) {
		if (idx >= 0 && idx < list.size()) {
			String checkDelete = scanStr("정말 삭제하시겠습니까?(y/n)");
			if (checkDelete.equals("y") || checkDelete.equals("Y")) {
				emailService.toTrashCan(loginUser, list.get(idx));
				System.out.println("삭제 완료");
				list.remove(idx);
			}
		} else {
			System.out.println("없는 번호입니다.");
		}

		return list;

	}

	@Override
	public void execute() throws Exception {
		// TODO Auto-generated method stub

	}

}
