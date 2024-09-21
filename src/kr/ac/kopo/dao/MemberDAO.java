package kr.ac.kopo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import kr.ac.kopo.util.ConnectionFactory;
import kr.ac.kopo.vo.MemberVO;

public class MemberDAO {

	public MemberVO selectOneMemberById(String id) {

		MemberVO member = null;

		StringBuilder sql = new StringBuilder();
		sql.append("select *");
		sql.append("  from member ");
		sql.append(" where id = ? ");

		try (Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {

			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				int getMemberCd = rs.getInt("member_cd");
				String getId = rs.getString("id");
				String getPwd = rs.getString("pwd");
				String getName = rs.getString("name");
				String getHp = rs.getString("hp");
				String getJoinDate = rs.getString("join_date");

				member = new MemberVO(getMemberCd, getId, getPwd, getName, getHp, getJoinDate);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return member;
	}

	public MemberVO selectOneMemberByIdPwd(String id, String pwd) {

		MemberVO member = null;

		StringBuilder sql = new StringBuilder();
		sql.append("select *");
		sql.append("  from member ");
		sql.append(" where id = ? ");
		sql.append("   and pwd = ? ");

		try (Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {

			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				int getMemberCd = rs.getInt("member_cd");
				String getId = rs.getString("id");
				String getPwd = rs.getString("pwd");
				String getName = rs.getString("name");
				String getHp = rs.getString("hp");
				String getJoinDate = rs.getString("join_date");

				member = new MemberVO(getMemberCd, getId, getPwd, getName, getHp, getJoinDate);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return member;

	}
	
	public MemberVO selectOneMemberByNameHp(String name, String hp) {

		MemberVO member = null;

		StringBuilder sql = new StringBuilder();
		sql.append("select *");
		sql.append("  from member ");
		sql.append(" where name = ? ");
		sql.append("   and hp = ? ");

		try (Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {

			pstmt.setString(1, name);
			pstmt.setString(2, hp);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				int getMemberCd = rs.getInt("member_cd");
				String getId = rs.getString("id");
				String getPwd = rs.getString("pwd");
				String getName = rs.getString("name");
				String getHp = rs.getString("hp");
				String getJoinDate = rs.getString("join_date");

				member = new MemberVO(getMemberCd, getId, getPwd, getName, getHp, getJoinDate);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return member;

	}

	public MemberVO selectOneMemberByIdNameHp(String id, String name, String hp) {

		MemberVO member = null;

		StringBuilder sql = new StringBuilder();
		sql.append("select *");
		sql.append("  from member ");
		sql.append(" where id = ?");
		sql.append("   and name = ? ");
		sql.append("   and hp = ? ");

		try (Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {

			pstmt.setString(1, id);
			pstmt.setString(2, name);
			pstmt.setString(3, hp);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				int getMemberCd = rs.getInt("member_cd");
				String getId = rs.getString("id");
				String getPwd = rs.getString("pwd");
				String getName = rs.getString("name");
				String getHp = rs.getString("hp");
				String getJoinDate = rs.getString("join_date");

				member = new MemberVO(getMemberCd, getId, getPwd, getName, getHp, getJoinDate);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return member;

	}

	public void insertMember(MemberVO member) {

		StringBuilder sql = new StringBuilder();
		sql.append("insert into member(member_cd, id, pwd, name, hp)");
		sql.append("  values(seq_tbl_member_member_cd.nextval, ?, ?, ?, ?) ");

		try (Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {

			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getPwd());
			pstmt.setString(3, member.getName());
			pstmt.setString(4, member.getHp());
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void updateMember(MemberVO memberVO) {
		
		StringBuilder sql = new StringBuilder();
		sql.append("update member");
		sql.append("   set pwd = ? ");
		sql.append("     , name = ? ");
		sql.append("     , hp = ? ");
		sql.append(" where member_cd = ? ");

		try (Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {

			pstmt.setString(1, memberVO.getPwd());
			pstmt.setString(2, memberVO.getName());
			pstmt.setString(3, memberVO.getHp());
			pstmt.setInt(4, memberVO.getMemberCd());
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
