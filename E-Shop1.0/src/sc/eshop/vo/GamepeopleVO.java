package sc.eshop.vo;

public class GamepeopleVO implements VO{

	private String GameID;
	private String NickName;
	private String PackID;
	private String JobID;
	private String Job;
	private String Experience;
	private int GameLevel;
	private int LoveLiness;
	public String getGameID() {
		return GameID;
	}
	public void setGameID(String gameID) {
		GameID = gameID;
	}
	public String getNickName() {
		return NickName;
	}
	public void setNickName(String nickName) {
		NickName = nickName;
	}
	public String getPackID() {
		return PackID;
	}
	public void setPackID(String packID) {
		PackID = packID;
	}
	public String getJobID() {
		return JobID;
	}
	public void setJobID(String jobID) {
		JobID = jobID;
	}
	public String getExperience() {
		return Experience;
	}
	public void setExperience(String experience) {
		Experience = experience;
	}
	public int getGameLevel() {
		return GameLevel;
	}
	public void setGameLevel(int gameLevel) {
		GameLevel = gameLevel;
	}
	public int getLoveLiness() {
		return LoveLiness;
	}
	public void setLoveLiness(int i) {
		LoveLiness = i;
	}
	public String getJob() {
		return Job;
	}
	public void setJob(String job) {
		Job = job;
	}

}
