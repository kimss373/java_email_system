package kr.ac.kopo.vo;

public class SelectEmailVO {

	private int emailCd;
	private int binCd;
	private String title;
	private String content;
	private String senderId;
	private String receiverId;
	private String checkRead;
	private String sendDate;
	public int getEmailCd() {
		return emailCd;
	}
	public void setEmailCd(int emailCd) {
		this.emailCd = emailCd;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getSenderId() {
		return senderId;
	}
	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}
	public String getReceiverId() {
		return receiverId;
	}
	public void setReceiverId(String receiverId) {
		this.receiverId = receiverId;
	}
	public String getCheckRead() {
		return checkRead;
	}
	public void setCheckRead(String checkRead) {
		this.checkRead = checkRead;
	}
	public String getSendDate() {
		return sendDate;
	}
	public void setSendDate(String sendDate) {
		this.sendDate = sendDate;
	}
	public SelectEmailVO(int emailCd, String title, String content, String senderId, String receiverId,
			String checkRead, String sendDate) {
		super();
		this.emailCd = emailCd;
		this.title = title;
		this.content = content;
		this.senderId = senderId;
		this.receiverId = receiverId;
		this.checkRead = checkRead;
		this.sendDate = sendDate;
	}
	public SelectEmailVO(int binCd, int emailCd, String title, String content, String senderId, String receiverId,
			String checkRead, String sendDate) {
		super();
		this.binCd = binCd;
		this.emailCd = emailCd;
		this.title = title;
		this.content = content;
		this.senderId = senderId;
		this.receiverId = receiverId;
		this.checkRead = checkRead;
		this.sendDate = sendDate;
	}
	public int getBinCd() {
		return binCd;
	}
	public void setBinCd(int binCd) {
		this.binCd = binCd;
	}
	
	
}
