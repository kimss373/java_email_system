package kr.ac.kopo.vo;

public class MemberVO {

	private int memberCd;
	private String id;
	private String pwd;
	private String name;
	private String hp;
	private String joinDate;
	
	public MemberVO(String name, String id, String pwd, String hp) {
		super();
		this.id = id;
		this.pwd = pwd;
		this.name = name;
		this.hp = hp;
	}
	
	public MemberVO(int memberCd, String id, String pwd, String name, String hp, String joinDate) {
		super();
		this.memberCd = memberCd;
		this.id = id;
		this.pwd = pwd;
		this.name = name;
		this.hp = hp;
		this.joinDate = joinDate;
	}



	public int getMemberCd() {
		return memberCd;
	}
	public void setMemberCd(int memberCd) {
		this.memberCd = memberCd;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getHp() {
		return hp;
	}
	public void setHp(String hp) {
		this.hp = hp;
	}
	public String getJoinDate() {
		return joinDate;
	}
	public void setJoinDate(String joinDate) {
		this.joinDate = joinDate;
	}
	@Override
	public String toString() {
		return "MemberVO [memberCd=" + memberCd + ", id=" + id + ", pwd=" + pwd + ", name=" + name + ", hp=" + hp + ", joinDate="
				+ joinDate + "]";
	}

		
	
}
