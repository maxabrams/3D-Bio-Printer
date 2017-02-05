
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import java.io.*;


public class TempControl implements Runnable {
    private volatile boolean running = true;
    private double threshold = 2;
    private double target = 20;
    private boolean isRelayOn = false;
    private static int RELAY_PIN = 15;
   // private GPIO_Pin relay;
    private GpioController gpio;
    private GpioPinDigitalOutput pin;
    private static final String PATH_TO_TEMP = "/home/pi/Adafruit_Python_DHT/examples/AdafruitDHT.py";
    
    public TempControl(){
        //relay = new GPIO_Pin(RELAY_PIN);
        gpio = GpioFactory.getInstance();
        pin = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_15, "Relay", PinState.LOW);
        pin.setShutdownOptions(true, PinState.HIGH);
        
    }
    
    @Override
    public void run() {

        while (running) {
            double currTemp = getTemp();
            
            if(currTemp< target - threshold){
                heaterOn();
            }else if(currTemp >= target){
                heaterOff();
            }
            
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("Could not sleep!");
            }
            
            System.out.println("Current Temp: " + currTemp);
        }
        heaterOff();
        System.out.println("done");
    }

    public void shutdown() {
        running = false;
    }
    
    
    private double getTemp(){
        try{
	        Process p = Runtime.getRuntime().exec("sudo python " + PATH_TO_TEMP + " 2302 15");
	        BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
	        
	        String consoleOutput = stdInput.readLine();//For some reason only 15 works with library
	        //System.out.println(consoleOutput);
	        consoleOutput = consoleOutput.substring(5,9);//Work for everything except 1.1=1.1* or 110.1
	        return Double.parseDouble(consoleOutput);
        }catch(IOException e){
	        System.out.println("Error could not read temp");
	        return -1;
        }
    }
    
    private void heaterOn(){
        if(!isRelayOn){
            //Turn relay on
            System.out.println("Heater is on");
            //relay.setHIGH();
            pin.high();
            isRelayOn = true;
        }
    }
    
    private void heaterOff(){
        if(isRelayOn){
            //turn Relay off
            System.out.println("Heater is off");
            //relay.setLOW();
            pin.low();
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
