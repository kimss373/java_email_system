package kr.ac.kopo.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import kr.ac.kopo.dao.AdminDAO;
import kr.ac.kopo.dao.MemberDAO;
import kr.ac.kopo.vo.EmailVO;
import kr.ac.kopo.vo.JailVO;
import kr.ac.kopo.vo.MemberVO;
import kr.ac.kopo.vo.SelectEmailVO;

public class AdminService {

	private AdminDAO dao;
	private MemberDAO memberDao;
	
	public AdminService() {
		dao = new AdminDAO();
		memberDao = new MemberDAO();
	}

	public List<MemberVO> getAllMember() {
		return dao.selectAllMember();
	}

	public void sendEmailToAll(EmailVO sendEmailVO) {
		List<MemberVO> list = dao.selectAllMember();
		
		for (int i = 0; i < list.size(); i++) {
			sendEmailVO.setReceiverCd(list.get(i).getMemberCd());
			dao.insertEmailToAll(sendEmailVO);
		}
	}

	public List<SelectEmailVO> getReportedMail() {
		return dao.selectAllReportedMail();
	}

	public void deleteReportedMail(int select) {
		dao.updateReportedMail(select);
	}

	public int sanctionMember(String senderId, int day) {
		MemberVO memberVO = memberDao.selectOneMemberById(senderId);
		JailVO jailVO = dao.selectJailMember(memberVO.getMemberCd());
		
		if (jailVO != null) {
			return 0;
		} else {
			dao.insertJailMember(memberVO, day);
			return 1;
		}
	}

	public JailVO isJailMember(int memberCd) {
		dao.deleteReleaseMember();
		return dao.selectJailMember(memberCd);
	}

	
	
	
}
