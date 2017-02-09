
public class Dish {
	private int picsPerMinute;
	private String fileName;
	//IDK if time should be String or INT
	private int timeStart;
	private int picsTaken;
	
	//default picsPerMinute=1
	public Dish(){
		picsPerMinute=1;
		fileName="";
		timeStart=-1;
		picsTaken=0;
	}
	
	//every time take a picture, increment picsTaken count by 1
	public void takePic(){
		picsTaken+=1;
	}
	
	public int getPicsPerMinute() {
		return picsPerMinute;
	}
	public void setPicsPerMinute(int picsPerMinute) {
		this.picsPerMinute = picsPerMinute;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public int getTimeStart() {
		return timeStart;
	}
	public void setTimeStart(int timeStart) {
		this.timeStart = timeStart;
	}

	public int getPicsTaken() {
		return picsTaken;
	}

	public void setPicsTaken(int picsTaken) {
		this.picsTaken = picsTaken;
	}
	
}