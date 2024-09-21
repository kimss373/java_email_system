package kr.ac.kopo.vo;

public class JailVO {

	private int jailCd;
	private int memberCd;
	private String releaseDay;
	public int getJailCd() {
		return jailCd;
	}
	public void setJailCd(int jailCd) {
		this.jailCd = jailCd;
	}
	public int getMemberCd() {
		return memberCd;
	}
	public void setMemberCd(int memberCd) {
		this.memberCd = memberCd;
	}
	public String getReleaseDay() {
		return releaseDay;
	}
	public void setReleaseDay(String releaseDay) {
		this.releaseDay = releaseDay;
	}
	public JailVO(int jailCd, int memberCd, String releaseDay) {
		super();
		this.jailCd = jailCd;
		this.memberCd = memberCd;
		this.releaseDay = releaseDay;
	}
	
	
	
}
