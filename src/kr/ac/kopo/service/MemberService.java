package kr.ac.kopo.service;

import kr.ac.kopo.dao.MemberDAO;
import kr.ac.kopo.vo.MemberVO;

public class MemberService {

	private MemberDAO dao;
	
	public MemberService() {
		dao = new MemberDAO();
	}

	public MemberVO findOneById(String id) {
		return dao.selectOneMemberById(id);
		
	}

	public void addMember(MemberVO member) {
		dao.insertMember(member);
	}

	public MemberVO login(String id, String pwd) {
		return dao.selectOneMemberByIdPwd(id, pwd);
	}
	
	public MemberVO findOneByNameHp(String name, String hp) {
		MemberVO member = dao.selectOneMemberByNameHp(name, hp);
		return member;
	}

	public MemberVO findOneByIdNameHp(String id, String name, String hp) {
		MemberVO member = dao.selectOneMemberByIdNameHp(id, name, hp);
		return member;
	}

	public void updateMemberInfo(MemberVO memberVO) {
		dao.updateMember(memberVO);
	}
	
	
	
	
}
