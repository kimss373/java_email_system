package kr.ac.kopo.service;

import java.util.List;

import kr.ac.kopo.dao.EmailDAO;
import kr.ac.kopo.dao.MemberDAO;
import kr.ac.kopo.vo.MemberVO;
import kr.ac.kopo.vo.SelectEmailVO;
import kr.ac.kopo.vo.SpamKeywordVO;
import kr.ac.kopo.vo.EmailBinVO;
import kr.ac.kopo.vo.EmailVO;

public class EmailService {

	private EmailDAO dao;
	
	public EmailService() {
		dao = new EmailDAO();
	}

	public void sendEmail(EmailVO sendEmailVO, MemberVO receivedMemberVO) {
		
		
		List<SpamKeywordVO> list = dao.selectAllSpamKeyword(receivedMemberVO.getMemberCd());
		for (int i = 0; i < list.size(); i++) {
			if (sendEmailVO.getTitle().contains(list.get(i).getKeywordNm()) || sendEmailVO.getContent().contains(list.get(i).getKeywordNm())) {
				int lastSequence = dao.selectLastMailSequence();
				dao.insertEmail(sendEmailVO, lastSequence);
				dao.insertEmailTrashCan(receivedMemberVO.getMemberCd(), lastSequence, "S");
				return;
			}
		}
		
		dao.insertEmail(sendEmailVO);
		
		
	}

	public List<SelectEmailVO> getReceivedEmail(MemberVO loginUser) {
		return dao.selectAllReceivedEmail(loginUser);
	}
	
	public List<SelectEmailVO> getSentEmail(MemberVO loginUser) {
		return dao.selectAllSentEmail(loginUser);
	}
	
	public List<SelectEmailVO> getAllEmail(MemberVO loginUser) {
		return dao.selectAllEmail(loginUser);
	}
	

	public void toTrashCan(MemberVO loginUser, SelectEmailVO receivedEmailVO) {
		
		dao.insertEmailTrashCan(loginUser.getMemberCd(), receivedEmailVO.getEmailCd(), "T");
	}
	
	public void reportSpamMail(MemberVO loginUser, SelectEmailVO receivedEmailVO) {
		dao.insertEmailTrashCan(loginUser.getMemberCd(), receivedEmailVO.getEmailCd(), "S");
		dao.updateBinReport(loginUser.getMemberCd(), receivedEmailVO.getEmailCd());
	}

	public List<SelectEmailVO> getAllTrashEmail(MemberVO loginUser) {
		return dao.selectAllTrashEmail(loginUser);
	}

	public void restoreEmail(SelectEmailVO selectEmailVO) {
		dao.deleteEmailTrashCan(selectEmailVO);
	}

	public void emptyOneBin(SelectEmailVO selectEmailVO) {
		dao.updateEmailBinEmpty(selectEmailVO);
	}

	public void emptyAllBin(List<SelectEmailVO> list) {
		for (int i = 0; i < list.size(); i++) {
			dao.updateEmailBinEmpty(list.get(i));
		}
	}

	public List<SelectEmailVO> getAllSpamEmail(MemberVO loginUser) {
		return dao.selectAllSpamMail(loginUser);
	}

	public void spamToTrash(SelectEmailVO selectEmailVO) {
		dao.updateSpamtoTrash(selectEmailVO);
	}

	public void spamToTrashAll(List<SelectEmailVO> list) {
		
		for (int i = 0; i < list.size(); i++) {
			dao.updateSpamtoTrash(list.get(i));
		}
		
	}

	public List<SpamKeywordVO> getAllSpamKeyword(MemberVO loginUser) {
		return dao.selectAllSpamKeyword(loginUser.getMemberCd());
	}

	public void createSpamKeyword(MemberVO loginUser, String keyword) {
		dao.insertSpamKeyword(loginUser, keyword);
	}

	public void deleteSpamKeyword(SpamKeywordVO spamKeywordVO) {
		dao.deleteSpamKeyword(spamKeywordVO);
	}

	public void updateCheckRead(SelectEmailVO selectEmailVO) {
		dao.updateCheckRead(selectEmailVO);
	}

	public int getUnreadMailCnt(MemberVO loginUser) {
		return dao.selectUnreadMailCnt(loginUser);
	}


}
