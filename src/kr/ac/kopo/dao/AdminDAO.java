package kr.ac.kopo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.ac.kopo.util.ConnectionFactory;
import kr.ac.kopo.vo.EmailVO;
import kr.ac.kopo.vo.JailVO;
import kr.ac.kopo.vo.MemberVO;
import kr.ac.kopo.vo.SelectEmailVO;

public class AdminDAO {

	public List<MemberVO> selectAllMember() {
		
		List<MemberVO> list = new ArrayList<MemberVO>();

		StringBuilder sql = new StringBuilder();
		sql.append(" select * ");
		sql.append("   from member ");
		sql.append("  order by member_cd ");

		try (Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				int getMemberCd = rs.getInt("member_cd");
				String getId = rs.getString("id");
				String getPwd = rs.getString("pwd");
				String getName = rs.getString("name");
				String getHp = rs.getString("hp");
				String getJoinDate = rs.getString("join_date");

				list.add(new MemberVO(getMemberCd, getId, getPwd, getName, getHp, getJoinDate));

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
		
	}

	public void insertEmailToAll(EmailVO sendEmailVO) {
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

	public List<SelectEmailVO> selectAllReportedMail() {
		
		List<SelectEmailVO> list = new ArrayList<SelectEmailVO>();

		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT eb2.BIN_CD, e.EMAIL_CD, e.TITLE, e.CONTENT, m2.ID, m1.id, e.CHECK_READ, e.SEND_DATE  ");
		sql.append("   FROM email e ");
		sql.append("   JOIN MEMBER m1 ON m1.MEMBER_CD = e.receiver_cd ");
		sql.append("   JOIN MEMBER m2 ON m2.MEMBER_CD = e.SENDER_CD  ");
		sql.append("   JOIN EMAIL_BIN eb2 ON eb2.EMAIL_CD = e.EMAIL_CD  ");
		sql.append("                     AND eb2.report = 1  ");

		try (Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {

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

	public void updateReportedMail(int select) {
	
		StringBuilder sql = new StringBuilder();
		sql.append(" update email_bin ");
		sql.append("    set report = 0 ");
		sql.append("  where bin_cd = ? ");

		try (Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {

			pstmt.setInt(1, select);
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public void insertJailMember(MemberVO memberVO, int day) {
		
		StringBuilder sql = new StringBuilder();
		sql.append(" insert into jail(jail_cd, member_cd, release_day) ");
		sql.append(" values(seq_tbl_jail_jail_cd.nextval, ?, sysdate + ?) ");

		try (Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {

			pstmt.setInt(1, memberVO.getMemberCd());
			pstmt.setInt(2, day);
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public JailVO selectJailMember(int memberCd) {
		JailVO jailVO = null;
		StringBuilder sql = new StringBuilder();
		sql.append(" select * ");
		sql.append("   from jail ");
		sql.append("  where member_cd = ? ");

		try (Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {
			
			pstmt.setInt(1, memberCd);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				int getJailCd = rs.getInt("jail_cd");
				int getMemberCd = rs.getInt("member_cd");
				String getReleaseDay = rs.getString("release_day");
				

				jailVO = new JailVO(getJailCd, getMemberCd, getReleaseDay);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jailVO;
	}

	public void deleteReleaseMember() {
		StringBuilder sql = new StringBuilder();
		sql.append(" delete from jail ");
		sql.append("  where release_day < sysdate ");

		try (Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {
			
			pstmt.executeUpdate();

			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	

}
