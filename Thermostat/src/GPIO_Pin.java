package src;


/**
 * Write a description of class GPIO_PIN here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
 
public class GPIO_Pin {
 
    private String modeURI = "/sys/devices/virtual/misc/gpio/mode/";
    private String statusURI = "/sys/devices/virtual/misc/gpio/pin/";
    private int pin = 0;
    public static final String HIGH = "1", LOW = "0", INPUT = "0", OUTPUT = "1", INPUT_PU = "8";
 
    public GPIO_Pin(int pin) {
        modeURI += "gpio" + pin;
        statusURI += "gpio" + pin;
        this.pin = pin;
    }
 
    public GPIO_Pin(String pin) {
        // Finalize file paths
        modeURI += "gpio" + pin;
        statusURI += "gpio" + pin;
        this.pin =Integer.parseInt(pin);
    }
 
    public int getPin() {
        return pin;
    }
 
    public void overrideURI(String uri) {
        modeURI = uri + "mode/gpio" + pin;
        statusURI = uri + "pin/gpio" + pin;
    }
 
    public void setMode(String mode) {
        writeToFile(getModeURI(), mode);
    }
 
    public void set(String state) {
        writeToFile(getStatusURI(), state);
    }
 
    public void setHIGH() {
        writeToFile(getStatusURI(), HIGH);
    }
 
    public void setLOW() {
        writeToFile(getStatusURI(), LOW);
    }
 
    public void setModeINPUT() {
        writeToFile(getModeURI(), INPUT);
    }
 
    public void setModeOUTPUT() {
        writeToFile(getModeURI(), OUTPUT);
    }
 
    public void setModeINPUT_PU() {
        writeToFile(getModeURI(), INPUT_PU);
    }
 
    public String getModeURI() {
        return modeURI;
    }
 
    public String getStatusURI() {
        return statusURI;
    }
 
    public String getPinMode() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(getModeURI()));
            String data = reader.readLine();
            reader.close();
            return data;
        } catch (IOException e) {
        }
        return "";
    }
 
    public String getPinStatus() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(getStatusURI()));
            String data = reader.readLine();
            reader.close();
            return data;
        } catch (IOException e) {
        }
        return "";
    }
 
    private void writeToFile(String URI, String data) {
        try {
            File file = new File(URI);
            file.delete();
            File newFile = new File(URI);
            newFile.createNewFile();
            FileWriter writer = new FileWriter(URI);
            writer.write(data);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}