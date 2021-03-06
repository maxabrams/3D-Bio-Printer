
//import com.pi4j.io.gpio.*;
import java.awt.List;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.io.*;
import java.util.Date;
import java.text.*;
import javax.swing.JLabel;


public class TempControl implements Runnable {
    private volatile boolean running = true;
    private double threshold = .1;
    private double target = 20;
    private boolean isRelayOn = false;
    private static int RELAY_PIN = 26;
  //  private static int TEMP_PIN = 20;
  //  private GPIO_Pin relay;
   // private GpioController gpio;
   // private GpioPinDigitalOutput pin;
    private static final String PATH_TO_TEMP = "/home/pi/py/temp_bridge.py";
    private static final String PATH_TO_RELAY_OFF = "/home/pi/py/relay_off.py";
    private static final String PATH_TO_RELAY_ON = "/home/pi/py/relay_on.py";
    JLabel output;
    JLabel status;
    PrintWriter outFile;
    DateFormat dateFormat;
    
    public TempControl(JLabel status, JLabel label){
        this.status = status;
        output = label;
        // Write temps to a file
        dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        try{
        outFile = new PrintWriter("temps.txt");
    }catch(Exception io){
        System.out.println("Temp file error");
    }
    
        //relay = new GPIO_Pin(RELAY_PIN);
       /* gpio = GpioFactory.getInstance();
        pin = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_18, "Relay", PinState.LOW);
        pin.setShutdownOptions(true, PinState.LOW);
        */
    }
    
    @Override
    public void run() {

        while (running) {
            double currTemp = getTemp();
            //Timestamp currentTimestamp = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
//            System.out.println(currentTimestamp);
            //lines.add(currentTimestamp + ": "+ currTemp);
            System.out.println("curr Temp: "+ currTemp);
            
            if(currTemp==Integer.MIN_VALUE){   
                System.out.println("error temp sensor output!");
                output.setText("Error");
                status.setText("Status: Error!");
                heaterOff();
                running = false; //THIS WILL KILL THE THREAD
            }else{
                status.setText("Status: Running");
                if(currTemp< target - threshold){
                     heaterOn();
                }else{ //if(currTemp >= target)
                    heaterOff();
                }
                
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println("Could not sleep!");
                }
                output.setText("" + Math.round((currTemp*100.0)/100.0));
            }
        }
        heaterOff();
        outFile.close();
        System.out.println("done");
        status.setText("Stopped");
       // try {
            //Files.write(file, lines, Charset.forName("UTF-8"));
        //} catch (IOException e) {
            // TODO Auto-generated catch block
            //e.printStackTrace();
        //}
    }

    public void shutdown() {
        running = false;
    }
    
    
    private double getTemp(){
        try{
        Process p = Runtime.getRuntime().exec("sudo python " + PATH_TO_TEMP );//+ " 2302 " + TEMP_PIN);
        BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
        
        String consoleOutput = stdInput.readLine();//--WHAAAAAATTT? For some reason only 15 works with library
        System.out.println(consoleOutput);
        if(consoleOutput == null){
            System.out.println("Invalid input from temp sensor");
            return Integer.MIN_VALUE;
        }
        
        /*
        consoleOutput = consoleOutput.substring(5,9);
        if (consoleOutput.matches("\\d*\\.?\\d*")){
            return Double.parseDouble(consoleOutput);
        }else{//if invalid input
            System.out.println("Invalid input from temp sensor");
            return Integer.MIN_VALUE;
        }*/
        
        String tempVals[] = consoleOutput.split(",");
        double tempAvg = 0;
        try{
        if(tempVals.length == 7){
            if(Double.parseDouble(tempVals[5]) > 100){ //Saftey check at 100c
                //Force off the thread
                System.out.println("FAILURE! Temperature is too hot!! Shutting down.");
                heaterOff();
                running = false;
                return Integer.MIN_VALUE;
            }
            String tmps = "";
            for(int i = 1; i < 5; i++){
                tmps += tempVals[i] + ",";
                tempAvg += Double.parseDouble(tempVals[i]);
            }
            tempAvg = tempAvg / 4.0;
            try{
                outFile.println(dateFormat.format(new Date())+","+tmps);
            }catch(Exception ioE){
                System.out.println("Could not write to file");
            }
            return tempAvg;
        }else{
            System.out.println("Error could not read temp from bridge");
            return Integer.MIN_VALUE;
        }
    }catch(Exception ex){
        return getTemp();
    }
       
    }catch(IOException e){
        System.out.println("Error could not read temp");
        return Integer.MIN_VALUE;
    }
    }
    
    private void heaterOn(){
        if(!isRelayOn){
            //Turn relay on
            System.out.println("Heater is on");
            //relay.setHIGH();
            //pin.high();
        try{
            Process pOn = Runtime.getRuntime().exec("sudo python " + PATH_TO_RELAY_ON + " " + RELAY_PIN);
        }catch(IOException e){
              System.out.println("Error could not turn on");
        }
            isRelayOn = true;
        }
    }
    
    private void heaterOff(){
        if(isRelayOn){
            //turn Relay off
            System.out.println("Heater is off");
            //relay.setLOW();
            //pin.low();
            try{
                Process pOff = Runtime.getRuntime().exec("sudo python " + PATH_TO_RELAY_OFF + " " + RELAY_PIN);
             }catch(IOException e){
                 System.out.println("Error could not turn off");
             }
            isRelayOn = false;
        }
        
    }
    
    public void updateTarget(double newTarget){
        target = newTarget;
        System.out.println("new target:" + target);
    }
    
    public void updateThreshold(double newThreshold){
        threshold = newThreshold;
        System.out.println("new threshold:" + threshold);
    }
    
}
