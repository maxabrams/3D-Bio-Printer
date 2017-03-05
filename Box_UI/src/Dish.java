import java.util.Date;

public class Dish {
	private int captureRate;
	private String captureMetric;
	private String fileName;
	private Date timeStart;
	private int lastPictureTaken;
	private int picsTaken;
	
	private int experimentTime;
	private String experimentMetric;

	private String dishString;
	// default picsPerMinute=1
	public Dish() {
		captureRate = 1;
		fileName = "";
		picsTaken = 0;
		captureMetric= "mins";
	}
	public Dish(String string) {
		dishString=string;
		captureRate = 1;
		fileName = "";
		picsTaken = 0;
		captureMetric= "mins";
	}

	public String getDishString(){
		return dishString;
	}
	// every time take a picture, increment picsTaken count by 1
	public void takePic() {
		picsTaken += 1;
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

}
