package kr.ac.kopo.vo;

public class EmailVO {

	private int emailCd;
	private String title;
	private String content;
	private int senderCd;
	private int receiverCd;
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
	public int getSenderCd() {
		return senderCd;
	}
	public void setSenderCd(int senderCd) {
		this.senderCd = senderCd;
	}
	public int getReceiverCd() {
		return receiverCd;
	}
	public void setReceiverCd(int receiverCd) {
		this.receiverCd = receiverCd;
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
	public EmailVO(int emailCd, String title, String content, int senderCd, int receiverCd, String checkRead,
			String sendDate) {
		super();
		this.emailCd = emailCd;
		this.title = title;
		this.content = content;
		this.senderCd = senderCd;
		this.receiverCd = receiverCd;
		this.checkRead = checkRead;
		this.sendDate = sendDate;
	}
	
	public EmailVO(String title, String content, int senderCd, int receiverCd, String checkRead) {
		super();
		this.title = title;
		this.content = content;
		this.senderCd = senderCd;
		this.receiverCd = receiverCd;
		this.checkRead = checkRead;
	}
	
	
	
	
	
	
}
