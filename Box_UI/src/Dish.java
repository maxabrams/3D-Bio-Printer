import java.util.Date;

public class Dish {
	private int captureRate;
	private String captureMetric;
	private String fileName;
	private Date timeStart;
	private Date lastPictureTaken;
	private int picsTaken;
	private boolean isEnabled;
	private int experimentTime;
	private String experimentMetric;
	private int totalImagesNeeded;
	public boolean isFinished;
	private int dishNum;
	private boolean isCleared;
	
	private String dishString;
	// default picsPerMinute=1
	public Dish() {
		captureRate = 1;
		fileName = "";
		picsTaken = 0;
		captureMetric= "mins";
		isEnabled = false;
		isFinished=false;
		setCleared(false);
	}
	public Dish(String string) {
		dishString=string;
		captureRate = 1;
		fileName = "";
		picsTaken = 0;
		captureMetric= "mins";
		isEnabled = false;
		isFinished=false;
		setCleared(false);
	}

	public void reset(){
		captureRate = 1;
		fileName = "";
		picsTaken = 0;
		captureMetric= "mins";
		isEnabled = false;
		isFinished=false;
	}
	public Date timeOfLastPic(){
		return lastPictureTaken;
	}
	
	public void setTimeOfLastPic(Date newDate){
		this.lastPictureTaken = newDate;
	}
	public int getDishNum(){
		return dishNum;
	}
	public void setDishNum(int num){
		dishNum=num;
	}
	public String getDishString(){
		return dishString;
	}
	// every time take a picture, increment picsTaken count by 1
	public void takePic() {
		picsTaken += 1;
	}

	public boolean isEnabled(){
		return isEnabled;
	}
	
	public void setEnabled(boolean enabled){
		isEnabled = enabled;
	}
	
	public boolean isFinished(){
		return isFinished;
	}
	public void setFinished(boolean finished){
		isFinished=finished;
	}
	public int getCaptureRate() {
		return captureRate;
	}

	public void setCaptureRate(int captureRate) {
		this.captureRate = captureRate;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getCaptureMetric() {
		return captureMetric;
	}

	public void setCaptureMetric(String captureMetric) {
		this.captureMetric= captureMetric;
	}

	public Date getTimeStart() {
		return timeStart;
	}

	public void setTimeStart(java.util.Date timeStamp) {
		this.timeStart = timeStamp;
	}

	public int getPicsTaken() {
		return picsTaken;
	}

	public void incrPicsTaken(){
		this.picsTaken++;
	}
	
	public void setPicsTaken(int picsTaken) {
		this.picsTaken = picsTaken;
	}
	public int getExperimentTime() {
		return experimentTime;
	}
	public void setExperimentTime(int experimentTime) {
		this.experimentTime = experimentTime;
	}
	public String getExperimentMetric() {
		return experimentMetric;
	}
	public void setExperimentMetric(String experimentMetric) {
		this.experimentMetric = experimentMetric;
	}
	public int getTotalImagesNeeded() {
		return totalImagesNeeded;
	}
	public void setTotalImagesNeeded(int totalImagesNeeded) {
		this.totalImagesNeeded = totalImagesNeeded;
	}
	public boolean isCleared() {
		return isCleared;
	}
	public void setCleared(boolean isCleared) {
		this.isCleared = isCleared;
	}

}
