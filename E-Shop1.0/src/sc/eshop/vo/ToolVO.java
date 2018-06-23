package sc.eshop.vo;

public class ToolVO implements VO{
	private String ToolID  ;
	private String  ToolName ;
	private String  ToolCash ;
	private String  ToolGold ;
	private String   JobID;
	private String  RegulationsInfo ;
	private String  ToolTypeID ;
	private String   ToolUrl;
	private String Tooltype;
	private String Job;
	
	public String getTooltype() {
		return Tooltype;
	}
	public void setTooltype(String tooltype) {
		Tooltype = tooltype;
	}
	public String getJob() {
		return Job;
	}
	public void setJob(String job) {
		Job = job;
	}
	public String getToolID() {
		return ToolID;
	}
	public void setToolID(String toolID) {
		ToolID = toolID;
	}
	public String getToolName() {
		return ToolName;
	}
	public void setToolName(String toolName) {
		ToolName = toolName;
	}
	public String getToolCash() {
		return ToolCash;
	}
	public void setToolCash(String toolCash) {
		ToolCash = toolCash;
	}
	public String getToolGold() {
		return ToolGold;
	}
	public void setToolGold(String toolGold) {
		ToolGold = toolGold;
	}
	public String getJobID() {
		return JobID;
	}
	public void setJobID(String jobID) {
		JobID = jobID;
	}
	public String getRegulationsInfo() {
		return RegulationsInfo;
	}
	public void setRegulationsInfo(String regulationsInfo) {
		RegulationsInfo = regulationsInfo;
	}
	public String getToolTypeID() {
		return ToolTypeID;
	}
	public void setToolTypeID(String toolTypeID) {
		ToolTypeID = toolTypeID;

	}
	public String getToolUrl() {
		return ToolUrl;
	}
	public void setToolUrl(String toolUrl) {
		ToolUrl = toolUrl;
	}
}
