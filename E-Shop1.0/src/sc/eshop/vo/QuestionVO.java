package sc.eshop.vo;

/**
 * 密保问题VO
 * 
 * @author Administrator
 * 
 */
public class QuestionVO implements VO {

	private String uName;// 用户编号
	private String question;// 问题
	private String answer;// 回答
	
	public String getuName() {
		return uName;
	}
	public void setuName(String uName) {
		this.uName = uName;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	

}
