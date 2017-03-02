package src;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSlider;

public class LightingPanel {
    private JPanel cameraPanel; //Panel to make modifications to 
    private String cameraName; //Name for panel 
    private JButton captureButton;
    private final String LIGHTING_BUTTON_TEXT = "Toggle Lights";
    private boolean isOn = false;
    private static final String PATH_TO_RELAY_OFF = "/home/pi/py/relay_off.py";
    private static final String PATH_TO_RELAY_ON = "/home/pi/py/relay_on.py";
    
    public LightingPanel(){
        //Initialize components
        cameraPanel = new JPanel();
        cameraPanel.setLayout(new GridLayout(1,2)); //Add a layout manager to align buttons as we resize
        cameraName = "Lighting Control"; //Assign name

        //Setup and add buttons
        captureButton = new JButton(LIGHTING_BUTTON_TEXT);
        captureButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                try {
                    if(isOn){
                        isOn = false;
                        Process lOn = Runtime.getRuntime().exec("sudo python " + PATH_TO_RELAY_OFF + " " + 21);
                    }else{
                        isOn = true;
                        Process lOn = Runtime.getRuntime().exec("sudo python " + PATH_TO_RELAY_ON + " " + 21);
                    }
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                
            } //Add action listener to respond to button
            
        });
        /*new JSlider(0,100);
        captureButton.setPaintTicks(true);
        captureButton.setMajorTickSpacing(10);
        captureButton.setMinorTickSpacing(5);
        captureButton.setPaintLabels(true);
        */
        
        cameraPanel.add(captureButton);
    }
    
    public JPanel getPanel(){
        return this.cameraPanel;
    }

    public String getPanelName() {
        return cameraName;
    }
}
