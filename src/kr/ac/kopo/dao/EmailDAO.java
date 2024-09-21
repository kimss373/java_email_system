package kr.ac.kopo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.ac.kopo.util.ConnectionFactory;
import kr.ac.kopo.vo.MemberVO;
import kr.ac.kopo.vo.SelectEmailVO;
import kr.ac.kopo.vo.SpamKeywordVO;
import kr.ac.kopo.vo.EmailBinVO;
import kr.ac.kopo.vo.EmailVO;

public class EmailDAO {

	public void insertEmail(EmailVO sendEmailVO) {

		StringBuilder sql = new StringBuilder();
		sql.append("insert into email(email_cd, title, content, sender_cd, receiver_cd, check_read)");
		sql.append("  values(seq_tbl_email_email_cd.nextval, ?, ?, ?, ?, ?) ");

		try (Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {

			pstmt.setString(1, sendEmailVO.getTitle());
			pstmt.setString(2, sendEmailVO.getContent());
			pstmt.setInt(3, sendEmailVO.getSenderCd());
			pstmt.setInt(4, sendEmailVO.getReceiverCd());
			pstmt.setString(5, sendEmailVO.getCheckRead());
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public void insertEmail(EmailVO sendEmailVO, int lastSequence) {
		StringBuilder sql = new StringBuilder();
		sql.append("insert into email(email_cd, title, content, sender_cd, receiver_cd, check_read)");
		sql.append("  values(?, ?, ?, ?, ?, ?) ");

		try (Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {

			pstmt.setInt(1, lastSequence);
			pstmt.setString(2, sendEmailVO.getTitle());
			pstmt.setString(3, sendEmailVO.getContent());
			pstmt.setInt(4, sendEmailVO.getSenderCd());
			pstmt.setInt(5, sendEmailVO.getReceiverCd());
			pstmt.setString(6, sendEmailVO.getCheckRead());
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public int selectLastMailSequence() {
		
		int lastSequence = -1;
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT seq_tbl_email_email_cd.nextval ");
		sql.append("   FROM dual ");
		try (Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				lastSequence = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return lastSequence;
		
	}

	public List<SelectEmailVO> selectAllReceivedEmail(MemberVO loginUser) {

		List<SelectEmailVO> list = new ArrayList<SelectEmailVO>();

		StringBuilder sql = new StringBuilder();
		sql.append("select *");
		sql.append("  from email e ");
		sql.append("       JOIN MEMBER m ON m.MEMBER_CD = e.sender_cd ");
		sql.append(" where e.receiver_cd = ? ");
		sql.append("   and e.email_cd not in ( ");
		sql.append("                          select eb.email_cd ");
		sql.append("                            from email_bin eb");
		sql.append("                           where eb.member_cd = ?");
		sql.append("                         ) ");
		sql.append(" order by e.send_date");
		try (Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {

			pstmt.setInt(1, loginUser.getMemberCd());
			pstmt.setInt(2, loginUser.getMemberCd());
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				int getEmailCd = rs.getInt("email_cd");
				String getTitle = rs.getString("title");
				String getContent = rs.getString("content");
				String getSenderId = rs.getString("id");
				String getCheckRead = rs.getString("check_read");
				String getSendDate = rs.getString("send_date");

				list.add(new SelectEmailVO(getEmailCd, getTitle, getContent, getSenderId, loginUser.getId(),
						getCheckRead, getSendDate));

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public List<SelectEmailVO> selectAllSentEmail(MemberVO loginUser) {

		List<SelectEmailVO> list = new ArrayList<SelectEmailVO>();

		StringBuilder sql = new StringBuilder();
		sql.append("select *");
		sql.append("  from email e ");
		sql.append("       JOIN MEMBER m ON m.MEMBER_CD = e.receiver_cd ");
		sql.append(" where e.sender_cd = ? ");
		sql.append("   and e.email_cd not in ( ");
		sql.append("                          select eb.email_cd ");
		sql.append("                            from email_bin eb");
		sql.append("                           where eb.member_cd = ? ");
		sql.append("                         ) ");
		sql.append(" order by e.send_date");
		try (Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {

			pstmt.setInt(1, loginUser.getMemberCd());
			pstmt.setInt(2, loginUser.getMemberCd());
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				int getEmailCd = rs.getInt("email_cd");
				String getTitle = rs.getString("title");
				String getContent = rs.getString("content");
				String getReceiverId = rs.getString("id");
				String getCheckRead = rs.getString("check_read");
				String getSendDate = rs.getString("send_date");

				list.add(new SelectEmailVO(getEmailCd, getTitle, getContent, loginUser.getId(), getReceiverId,
						getCheckRead, getSendDate));

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public List<SelectEmailVO> selectAllEmail(MemberVO loginUser) {

		List<SelectEmailVO> list = new ArrayList<SelectEmailVO>();

		StringBuilder sql = new StringBuilder();
		sql.append("select *");
		sql.append("  from ( ");
		sql.append("        SELECT * ");
		sql.append("          FROM email e ");
		sql.append("         WHERE e.sender_cd = ? ");
		sql.append("            OR e.receiver_cd = ? ");
		sql.append("       ) m1 ");
		sql.append("       JOIN MEMBER m ON m.MEMBER_CD = m1.receiver_cd ");
		sql.append("       JOIN MEMBER m2 ON m2.MEMBER_CD = m1.sender_cd ");
		sql.append(" WHERE m1.email_cd NOT IN (SELECT eb.email_cd ");
		sql.append("                             FROM EMAIL_BIN eb");
		sql.append("                            WHERE eb.member_cd = ? ");
		sql.append("                          )");
		sql.append(" order by send_date");
		try (Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {

			pstmt.setInt(1, loginUser.getMemberCd());
			pstmt.setInt(2, loginUser.getMemberCd());
			pstmt.setInt(3, loginUser.getMemberCd());
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				int getEmailCd = rs.getInt("email_cd");
				String getTitle = rs.getString("title");
				String getContent = rs.getString("content");
				String getReceiverId = rs.getString(9);
				String getSenderId = rs.getString(15);
				String getCheckRead = rs.getString("check_read");
				String getSendDate = rs.getString("send_date");

				list.add(new SelectEmailVO(getEmailCd, getTitle, getContent, getSenderId, getReceiverId, getCheckRead,
						getSendDate));

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public void insertEmailTrashCan(int memberCd, int emailCd, String trashType) {

		StringBuilder sql = new StringBuilder();
		sql.append("insert into email_bin(bin_cd, member_cd, email_cd, status, empty)");
		sql.append("  values(seq_tbl_email_bin_bin_cd.nextval, ?, ?, ?, 'N') ");

		try (Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {
			pstmt.setInt(1, memberCd);
			pstmt.setInt(2, emailCd);
			pstmt.setString(3, trashType);
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public List<SelectEmailVO> selectAllTrashEmail(MemberVO loginUser) {

		List<SelectEmailVO> list = new ArrayList<SelectEmailVO>();

		StringBuilder sql = new StringBuilder();
		sql.append(" select eb2.bin_cd, e.EMAIL_CD, e.TITLE, e.CONTENT, m2.ID, m1.id, e.CHECK_READ, e.SEND_DATE ");
		sql.append("   FROM email e ");
		sql.append("        JOIN MEMBER m1 ON m1.MEMBER_CD = e.receiver_cd ");
		sql.append("        JOIN MEMBER m2 ON m2.MEMBER_CD = e.SENDER_CD ");
		sql.append("        JOIN EMAIL_BIN eb2 ON eb2.EMAIL_CD = e.EMAIL_CD ");
		sql.append("                          AND eb2.MEMBER_CD = ? ");
		sql.append("                          and eb2.empty != 'Y'");
		sql.append("                          and eb2.status = 'T'");
		sql.append("  order by e.send_date");
		try (Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {

			pstmt.setInt(1, loginUser.getMemberCd());
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				int getBinCd = rs.getInt(1);
				int getEmailCd = rs.getInt(2);
				String getTitle = rs.getString(3);
				String getContent = rs.getString(4);
				String getSenderId = rs.getString(5);
				String getReceiverId = rs.getString(6);
				String getCheckRead = rs.getString(7);
				String getSendDate = rs.getString(8);

				list.add(new SelectEmailVO(getBinCd, getEmailCd, getTitle, getContent, getSenderId, getReceiverId,
						getCheckRead, getSendDate));

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;

	}

	public void deleteEmailTrashCan(SelectEmailVO selectEmailVO) {

		StringBuilder sql = new StringBuilder();
		sql.append(" DELETE FROM EMAIL_BIN eb ");
		sql.append("  WHERE bin_cd = ? ");

		try (Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {
			pstmt.setInt(1, selectEmailVO.getBinCd());
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void updateEmailBinEmpty(SelectEmailVO selectEmailVO) {

		StringBuilder sql = new StringBuilder();
		sql.append(" update EMAIL_BIN eb ");
		sql.append("    set eb.empty = 'Y'");
		sql.append("  WHERE bin_cd = ? ");

		try (Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {
			pstmt.setInt(1, selectEmailVO.getBinCd());
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public List<SelectEmailVO> selectAllSpamMail(MemberVO loginUser) {

		List<SelectEmailVO> list = new ArrayList<SelectEmailVO>();

		StringBuilder sql = new StringBuilder();
		sql.append(" select eb2.bin_cd, e.EMAIL_CD, e.TITLE, e.CONTENT, m2.ID, m1.id, e.CHECK_READ, e.SEND_DATE ");
		sql.append("   FROM email e ");
		sql.append("        JOIN MEMBER m1 ON m1.MEMBER_CD = e.receiver_cd ");
		sql.append("        JOIN MEMBER m2 ON m2.MEMBER_CD = e.SENDER_CD ");
		sql.append("        JOIN EMAIL_BIN eb2 ON eb2.EMAIL_CD = e.EMAIL_CD ");
		sql.append("                          AND eb2.MEMBER_CD = ? ");
		sql.append("                          and eb2.empty != 'Y'");
		sql.append("                          and eb2.status = 'S'");
		sql.append("  order by e.send_date");
		try (Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {

			pstmt.setInt(1, loginUser.getMemberCd());
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				int getBinCd = rs.getInt(1);
				int getEmailCd = rs.getInt(2);
				String getTitle = rs.getString(3);
				String getContent = rs.getString(4);
				String getSenderId = rs.getString(5);
				String getReceiverId = rs.getString(6);
				String getCheckRead = rs.getString(7);
				String getSendDate = rs.getString(8);

				list.add(new SelectEmailVO(getBinCd, getEmailCd, getTitle, getContent, getSenderId, getReceiverId,
						getCheckRead, getSendDate));

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;

	}

	public void updateSpamtoTrash(SelectEmailVO selectEmailVO) {

		StringBuilder sql = new StringBuilder();
		sql.append(" update EMAIL_BIN eb ");
		sql.append("    set eb.status = 'T'");
		sql.append("  WHERE bin_cd = ? ");

		try (Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {
			pstmt.setInt(1, selectEmailVO.getBinCd());
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public List<SpamKeywordVO> selectAllSpamKeyword(int memberCd) {
		List<SpamKeywordVO> list = new ArrayList<SpamKeywordVO>();

		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT * ");
		sql.append("   FROM spam_keyword ");
		sql.append("  WHERE member_cd = ? ");
		try (Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {

			pstmt.setInt(1, memberCd);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				int getKeywordCd = rs.getInt(1);
				int getMemberCd = rs.getInt(2);
				String getKeywordNm = rs.getString(3);

				list.add(new SpamKeywordVO(getKeywordCd, getMemberCd, getKeywordNm));

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public void insertSpamKeyword(MemberVO loginUser, String keyword) {
		
		StringBuilder sql = new StringBuilder();
		sql.append("insert into spam_keyword(keyword_cd, member_cd, keyword_nm)");
		sql.append("  values(seq_tbl_spam_keyword_keyword_cd.nextval, ?, ?) ");

		try (Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {
			pstmt.setInt(1, loginUser.getMemberCd());
			pstmt.setString(2, keyword);
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public void deleteSpamKeyword(SpamKeywordVO spamKeywordVO) {
		
		StringBuilder sql = new StringBuilder();
		sql.append(" DELETE FROM spam_keyword ");
		sql.append("  WHERE keyword_cd = ? ");

		try (Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {
			pstmt.setInt(1, spamKeywordVO.getKeywordCd());
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public void updateBinReport(int memberCd, int emailCd) {
		
		StringBuilder sql = new StringBuilder();
		sql.append(" update EMAIL_BIN eb ");
		sql.append("    set eb.report = 1");
		sql.append("  WHERE member_cd = ? ");
		sql.append("    and email_cd = ?");

		try (Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {
			pstmt.setInt(1, memberCd);
			pstmt.setInt(2, emailCd);
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public void updateCheckRead(SelectEmailVO selectEmailVO) {
	
		StringBuilder sql = new StringBuilder();
		sql.append(" update EMAIL ");
		sql.append("    set check_read = 'Y'");
		sql.append("  WHERE email_cd = ?");

		try (Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {
			pstmt.setInt(1, selectEmailVO.getEmailCd());
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public int selectUnreadMailCnt(MemberVO loginUser) {
		
		int cnt = 0;
		
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT count(*) ");
		sql.append("   FROM email ");
		sql.append("  WHERE check_read = 'N' ");
		sql.append("    and email_cd not in (select email_cd ");
		sql.append("                           from email_bin ");
		sql.append("                          where member_cd = ?)  ");
		sql.append("    and receiver_cd = ? ");
		try (Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {

			pstmt.setInt(1, loginUser.getMemberCd());
			pstmt.setInt(2, loginUser.getMemberCd());
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				cnt = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return cnt;
	}

	

}
