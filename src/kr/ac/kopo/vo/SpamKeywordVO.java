package kr.ac.kopo.vo;

public class SpamKeywordVO {

	private int keywordCd;
	private int memberCd;
	private String keywordNm;
	
	public int getKeywordCd() {
		return keywordCd;
	}
	public void setKeywordCd(int keywordCd) {
		this.keywordCd = keywordCd;
	}
	public int getMemberCd() {
		return memberCd;
	}
	public void setMemberCd(int memberCd) {
		this.memberCd = memberCd;
	}
	public String getKeywordNm() {
		return keywordNm;
	}
	public void setKeywordNm(String keywordNm) {
		this.keywordNm = keywordNm;
	}
	public SpamKeywordVO(int keywordCd, int memberCd, String keywordNm) {
		super();
		this.keywordCd = keywordCd;
		this.memberCd = memberCd;
		this.keywordNm = keywordNm;
	} 
	
}
