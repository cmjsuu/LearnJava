package sc.eshop.vo;

import java.sql.Date;

/**
 * 商品评价类
 * userId外键约束于用户表的userId
 * @author yxf
 *
 */
public class MessageBoardVO implements VO {

	// 留言用户编号
	private String UserID;
	
	// 留言编号
	private String MessageID;
	
	// 留言标题
	private String MessageTitle;
	
	// 留言内容
	private String MessageContent;
	
	// 留言时间
	private Date MessagePubilshTime;
	
	//回复状态
	private int MessageState;
	
	//回复内容
	private String MessageReply;
	
	//用户账号
	private String Accounts;

	public String getUserID() {
		return UserID;
	}

	public void setUserID(String userID) {
		UserID = userID;
	}

	public String getMessageID() {
		return MessageID;
	}

	public void setMessageID(String messageID) {
		MessageID = messageID;
	}

	public String getMessageTitle() {
		return MessageTitle;
	}

	public void setMessageTitle(String messageTitle) {
		MessageTitle = messageTitle;
	}

	public String getMessageContent() {
		return MessageContent;
	}

	public void setMessageContent(String messageContent) {
		MessageContent = messageContent;
	}

	public Date getMessagePubilshTime() {
		return MessagePubilshTime;
	}

	public void setMessagePubilshTime(Date messagePubilshTime) {
		MessagePubilshTime = messagePubilshTime;
	}

	public int getMessageState() {
		return MessageState;
	}

	public void setMessageState(int messageState) {
		MessageState = messageState;
	}

	public String getMessageReply() {
		return MessageReply;
	}

	public void setMessageReply(String messageReply) {
		MessageReply = messageReply;
	}

	public String getAccounts() {
		return Accounts;
	}

	public void setAccounts(String accounts) {
		Accounts = accounts;
	}



}
