package src;

import java.util.Date;

public class CameraThread implements Runnable {
    private static final String CAMERA_SCRIPT = "/home/pi/py/camera.py";
    Dish[] dishes;
    static final String SECS = "secs";
    static final String MINS = "mins";
    static final String HOURS = "hours";
    static final String DAYS = "days";
    static final int POLL_RATE = 1000; //loop every 1000 ms. This will depend on how fast camera is at taking photos.
    
    public CameraThread(Dish[] dishes){
        this.dishes = dishes;
    }
    
    @Override
    public void run() {
        Date currTime;
        boolean hasTakenPhoto;
        while(true){ //Loop forever in background
            
            currTime = new Date(); //For now, just take time at start of loop. Will modify after testing if needed
            hasTakenPhoto = false; //Reuse photo if taken in the same loop
            for(Dish expr : dishes){
                if(expr.isEnabled()){ //Check if dish is "enabled" for taking photos
                    double timeVal = Double.MAX_VALUE; //Fake value to override
                    
                    switch(expr.getCaptureMetric()){ //TODO: testing to see if this is fast enough. To optimize, we can save calculation when dish object is updated
                        case SECS:
                            timeVal = expr.getCaptureRate() * 1000;//1000 to convert to seconds, rate for num of seconds
                            break;
                        case MINS:
                            timeVal = expr.getCaptureRate() * 60 * 1000;
                            break;
                        case HOURS:
                            timeVal = expr.getCaptureRate() * 60 * 60 * 1000;
                            break;
                        case DAYS:
                            timeVal = expr.getCaptureRate() * 24 * 60 * 60 * 1000;
                            break;
                    }

                    if(currTime.getTime() - expr.timeOfLastPic().getTime() >= timeVal){
                        //Time to save another photo. Check to see if we can reuse one from a previous dish
                        if(hasTakenPhoto){
                            //Reuse photo
                            reusePhoto(expr);
                        }else{
                            //First dish to take photo this loop
                            hasTakenPhoto = true;
                            takePhoto(expr);
                        }
                        
                    }
                    //Stop taking pictures for the dish once the experiment is over
                    if(expr.getPicsTaken()== expr.getTotalImagesNeeded()){
                        expr.setEnabled(false);
                        expr.setFinished(true);
                    }
                }//end of check for if dish is enabled
            }//end of for loop for all dishes
        
            try {
                 //Sleep for the poll rate
                Thread.sleep(POLL_RATE);
            } catch (InterruptedException e) {
                System.out.println("Error! Photo thread could not sleep");
            }
        
        }//end of while loop (runs for duration of program) 
    }
    
    //MAJOR TODO: need to make sure we have enough room to take photos
    
    private void takePhoto(Dish dish){
        Date currT = new Date(); 
        System.out.println("sudo python " +CAMERA_SCRIPT + " /home/pi/Desktop/"+dish.getFileName()+"/"+dish.getFileName()+dish.getPicsTaken()+".jpg"); //Simple message for now
         
        try{
              
            Process takePic = Runtime.getRuntime().exec("sudo python " +CAMERA_SCRIPT + " /home/pi/Desktop/"+dish.getFileName()+"/"+dish.getFileName()+dish.getPicsTaken()+".jpg");
        }catch(Exception e){
              System.out.println("Error could not turn take a picture");
        }
        dish.setTimeOfLastPic(currT);
        dish.incrPicsTaken();
    }
    
    private void reusePhoto(Dish dish){
        //This will just "copy" the photo previously taken to a file with this dish's name 
        System.out.println("Photo resued! " + dish.getFileName());
        dish.setTimeOfLastPic(new Date());
        dish.incrPicsTaken();
    }

}
