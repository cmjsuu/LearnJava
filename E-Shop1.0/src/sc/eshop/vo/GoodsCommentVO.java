package sc.eshop.vo;

import java.sql.Date;

/**
 * 商品评论的实体类，用户编号联合商品编号组成主键
 * @author yxf
 *
 */
public class GoodsCommentVO {

	// 用户编号
	private String nickname;
	
	// 商品编号
	private String gId;
	
	// 商品评论
	private String cComment;
	
	// 评论时间
	private Date cTime;

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getgId() {
		return gId;
	}

	public void setgId(String gId) {
		this.gId = gId;
	}

	public String getcComment() {
		return cComment;
	}

	public void setcComment(String cComment) {
		this.cComment = cComment;
	}

	public Date getcTime() {
		return cTime;
	}

	public void setcTime(Date cTime) {
		this.cTime = cTime;
	}
	
}
