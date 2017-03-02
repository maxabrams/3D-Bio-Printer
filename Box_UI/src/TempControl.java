
//import com.pi4j.io.gpio.*;
import java.io.*;

import javax.swing.JLabel;


public class TempControl implements Runnable {
    private volatile boolean running = true;
    private double threshold = 2;
    private double target = 20;
    private boolean isRelayOn = false;
    private static int RELAY_PIN = 26;
    private static int TEMP_PIN = 20;
  //  private GPIO_Pin relay;
   // private GpioController gpio;
   // private GpioPinDigitalOutput pin;
    private static final String PATH_TO_TEMP = "/home/pi/Adafruit_Python_DHT/examples/AdafruitDHT.py";
    private static final String PATH_TO_RELAY_OFF = "/home/pi/py/relay_off.py";
    private static final String PATH_TO_RELAY_ON = "/home/pi/py/relay_on.py";
    JLabel output;
    JLabel status;
    
    public TempControl(JLabel status, JLabel label){
        this.status = status;
        output = label;
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
            System.out.println("curr Temp: "+ currTemp);
            
            if(currTemp==Integer.MIN_VALUE){   
                System.out.println("error temp sensor output!");
                output.setText("Error");
                status.setText("Status: Error! No temperature sensor!");
                heaterOff();
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
                output.setText("Current Temp: " + currTemp);
            }
        }
        heaterOff();
        System.out.println("done");
    }

    public void shutdown() {
        running = false;
    }
    
    
    private double getTemp(){
        try{
        Process p = Runtime.getRuntime().exec("sudo python " + PATH_TO_TEMP + " 2302 " + TEMP_PIN);
        BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
        
        String consoleOutput = stdInput.readLine();//For some reason only 15 works with library
        //System.out.println(consoleOutput);
        consoleOutput = consoleOutput.substring(5,9);
        if (consoleOutput.matches("\\d*\\.?\\d*")){
            return Double.parseDouble(consoleOutput);
        }else{//if invalid input
            System.out.println("Invalid input from temp sensor");
            //heaterOff();
            return Integer.MIN_VALUE;
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
