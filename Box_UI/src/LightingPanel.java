import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class LightingPanel extends JPanel {
    private JPanel lightPanel; // Panel to make modifications to
    private String lightName; // Name for panel
    private JButton previewButton;
    private JButton slidePreview;
    private static final String PATH_TO_LED_SCRIPT = "/home/pi/py/strip.py";
    private static final int RED_PIN = 16; // GPIO Pin
    private static final int GREEN_PIN = 20; // GPIO Pin
    private static final int BLUE_PIN = 21; // GPIO Pin
    private JSlider redSlider;
    private JSlider blueSlider;
    private JSlider greenSlider;
    private JSpinner redBox;
    private JSpinner blueBox;
    private JSpinner greenBox;
    private final String RED_LABEL_TEXT = "Red:   ";
    private final String GREEN_LABEL_TEXT = "Green:";
    private final String BLUE_LABEL_TEXT = "Blue:  ";
    private JButton advancedOptions;
    boolean redBoxFocus=false;
    
    private int REDval;
    private int GREENval;
    private int BLUEval;

    public LightingPanel() {

        try {
            Runtime.getRuntime().exec("sudo pigpiod");
        } catch (IOException error) {
            System.out.println("Error! Could not start LED Service");
        }
        // Initialize components
        lightPanel = new JPanel();
        // lightPanel.setLayout(new GridLayout(4, 1)); // Add a layout manager
        // to align components

        // Initialize Grid bag layout
        lightPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;

        lightName = "Lighting"; // Assign name

        // Setup sliders
        redSlider = new JSlider(0, 255);
        blueSlider = new JSlider(0, 255);
        greenSlider = new JSlider(0, 255);

        // Set paint components
        redSlider.setPaintTicks(true);
        blueSlider.setPaintTicks(true);
        greenSlider.setPaintTicks(true);

        redSlider.setMinorTickSpacing(5);
        blueSlider.setMinorTickSpacing(5);
        greenSlider.setMinorTickSpacing(5);

        redSlider.setMajorTickSpacing(10);
        blueSlider.setMajorTickSpacing(10);
        greenSlider.setMajorTickSpacing(10);

        // Setup spinners
        redBox = new JSpinner(new SpinnerNumberModel(redSlider.getValue(), 0,
                255, 1)); // value, min, max, step
        blueBox = new JSpinner(new SpinnerNumberModel(blueSlider.getValue(), 0,
                255, 1)); // Stick to slider value to avoid API changes
        greenBox = new JSpinner(new SpinnerNumberModel(greenSlider.getValue(),
                0, 255, 1));

        redBox.setBorder(BorderFactory
                .createEtchedBorder(EtchedBorder.RAISED));
        blueBox.setBorder(BorderFactory
                .createEtchedBorder(EtchedBorder.RAISED));
        greenBox.setBorder(BorderFactory
                .createEtchedBorder(EtchedBorder.RAISED));
        
        // if want to add keyboard
//      JSpinner.DefaultEditor editor = (JSpinner.DefaultEditor)redBox.getEditor();
//      final JTextField textField = editor.getTextField();
//      
//      textField.addFocusListener(new FocusListener(){
//
//          @Override
//          public void focusGained(FocusEvent e) {
//              if(redBoxFocus == false){ //TODO: improve click options
//                  redBoxFocus = true;
//                  new Keyboard("0123456789", textField, 4, 3, lightPanel);
//              }else{
//                  redBoxFocus = false;
//              }
//          }
//
//          @Override
//          public void focusLost(FocusEvent e) {
//
//              
//          }});
        
        // box for color preview based on RGB values set with sliders
        previewButton = new JButton("Current Setting");
        previewButton.setPreferredSize(new Dimension(40, 40));
        previewButton.setBackground(new Color(redSlider.getValue(), greenSlider
                .getValue(), blueSlider.getValue()));
        previewButton.setOpaque(true);
        // previewButton.setEnabled(false);
        previewButton.setName("Preview");

        slidePreview = new JButton("Current Setting");
        slidePreview.setPreferredSize(new Dimension(40, 40));
        slidePreview.setBackground(new Color(redSlider.getValue(), greenSlider
                .getValue(), blueSlider.getValue()));
        slidePreview.setOpaque(true);
        // slidePreview.setEnabled(false);
        slidePreview.setName("Preview");

        // advanced settings button
        advancedOptions = new JButton("<html><u>Advanced</u></html>");
        c.gridx = 2;
        c.gridy = 2;
        c.ipadx = 10;
//      c.ipady=-10;
        c.insets = new Insets(60, 10, 0, 0);
        c.anchor= GridBagConstraints.NORTH;
        advancedOptions.setFont(new Font("Arial", Font.PLAIN, 18));
        advancedOptions.setBorder(BorderFactory.createEmptyBorder());
        advancedOptions.setForeground(Color.MAGENTA);
        advancedOptions.setName("AdvancedButton");
        lightPanel.add(advancedOptions, c);

        advancedOptions.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                new CloseDialog("Advanced Lighting Settings" ,advancedSettings(), new JButton("Done"), 800,
                        430);
            }
        });

        JLabel inst = new JLabel("Select desired lighting color");
        inst.setFont(new Font("Arial", Font.BOLD, 20));
        // c.weightx = 1.0;
        // c.gridwidth = 1;
        c.gridx = 1;
        c.gridy = 0;
        c.anchor = GridBagConstraints.EAST;
        c.insets = new Insets(30, 150, 20, 0);
        lightPanel.add(inst, c);

        // Preset colors
        JPanel colorPresets = new JPanel();
        colorPresets.setLayout(new GridBagLayout());

        ArrayList<Color> colors = new ArrayList<Color>(Arrays.asList(new Color(
                255, 0, 0), new Color(0, 255, 0), new Color(0, 0, 255),
                new Color(255, 255, 0), new Color(255, 255, 255), new Color(0,
                    0, 0)));
        
    
        ArrayList<String> colorNames= new ArrayList<String>(Arrays.asList("Red","Green", "Blue", "Yellow", "White", "Black" ));
        Color [] foreground= {Color.white, Color.black, Color.white, Color.black, Color.black, Color.white};
        
        int count = 0;
        for (final Color col : colors) {
            // label each color with word
            final JButton Preset = new JButton();
            Preset.setText(colorNames.get(count));
            Preset.setForeground(foreground[count]);
            Preset.setPreferredSize(new Dimension(60, 60));
            Preset.setOpaque(true);
            Preset.setBackground(col);
            Preset.setFocusable(false);
            Preset.setBorder(BorderFactory
                    .createEtchedBorder(EtchedBorder.RAISED));

            Preset.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent a) {
                    
                    REDval=col.getRed();
                    BLUEval=col.getBlue();
                    GREENval=col.getGreen();
                    
                    redSlider.setValue(REDval);
                    blueSlider.setValue(BLUEval);
                    greenSlider.setValue(GREENval);
                    //setLights();
                    previewButton.setBackground(new Color(redSlider.getValue(),
                            greenSlider.getValue(), blueSlider.getValue()));
                    setButtonForeground(previewButton);

                }
            });

            if (count > 2) {
                c.gridx = count % 3;
                c.gridy = 1;
            } else {
                c.gridx = count;
                c.gridy = 0;
            }
            
            c.ipady = 50;
            c.ipadx = 70;
            c.insets = new Insets(10, 10, 10, 10);
            c.gridwidth = 1;
            count++;
            colorPresets.add(Preset, c);
        }
        c.insets = new Insets(0, 0, -20, 0);

        c.gridx = 1;
        c.gridy = 1;
        c.weighty = 1.0;
        c.gridwidth = 2;
        c.ipady = 0;
        c.fill = GridBagConstraints.VERTICAL;
        c.anchor = GridBagConstraints.SOUTH;
        // colorPresets.setBackground(Color.BLUE);
        lightPanel.add(colorPresets, c);
        // Add individual panels to main panel
        c.gridwidth = count;

        c.gridx = 0;

        // add preview Button
        c.gridy = 2;
        c.ipady = 0;
        c.ipadx = 100;
        c.insets = new Insets(40, 0, 20, -10);
        lightPanel.add(previewButton, c);
        advancedSettings();
    }

    public JPanel advancedSettings() {
        // Add update listeners to sliders
        redSlider.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent e) {
                REDval=redSlider.getValue();
              
                redBox.setValue(redSlider.getValue());
                previewButton.setBackground(new Color(redSlider.getValue(),
                        greenSlider.getValue(), blueSlider.getValue()));
                slidePreview.setBackground(new Color(redSlider.getValue(),
                        greenSlider.getValue(), blueSlider.getValue()));
                setButtonForeground(previewButton);
                setButtonForeground(slidePreview);
                //setLights();
            }
        });

        blueSlider.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent e) {
                BLUEval=blueSlider.getValue();
                blueBox.setValue(blueSlider.getValue());
                previewButton.setBackground(new Color(redSlider.getValue(),
                        greenSlider.getValue(), blueSlider.getValue()));
                slidePreview.setBackground(new Color(redSlider.getValue(),
                        greenSlider.getValue(), blueSlider.getValue()));
                setButtonForeground(previewButton);
                setButtonForeground(slidePreview);
                //setLights();
            }
        });

        greenSlider.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent e) {
                GREENval=greenSlider.getValue();
                greenBox.setValue(greenSlider.getValue());
                previewButton.setBackground(new Color(redSlider.getValue(),
                        greenSlider.getValue(), blueSlider.getValue()));
                slidePreview.setBackground(new Color(redSlider.getValue(),
                        greenSlider.getValue(), blueSlider.getValue()));
                setButtonForeground(previewButton);
                setButtonForeground(slidePreview);
                //setLights();
            }
        });

        // Add listeners to box
        redBox.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                REDval=(int)redBox.getValue();
                redSlider.setValue((int) (redBox.getValue()));
                previewButton.setBackground(new Color(redSlider.getValue(),
                        greenSlider.getValue(), blueSlider.getValue()));
                slidePreview.setBackground(new Color(redSlider.getValue(),
                        greenSlider.getValue(), blueSlider.getValue()));
                setButtonForeground(previewButton);
                setButtonForeground(slidePreview);
                setLights();
            }
        });

        blueBox.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent e) {
                BLUEval=(int)blueBox.getValue();
                blueSlider.setValue((int) (blueBox.getValue()));
                previewButton.setBackground(new Color(redSlider.getValue(),
                        greenSlider.getValue(), blueSlider.getValue()));
                slidePreview.setBackground(new Color(redSlider.getValue(),
                        greenSlider.getValue(), blueSlider.getValue()));
                setButtonForeground(previewButton);
                setButtonForeground(slidePreview);
                setLights();
            }
        });

        greenBox.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent e) {
                GREENval=(int)greenBox.getValue();
                greenSlider.setValue((int) (greenBox.getValue()));
                previewButton.setBackground(new Color(redSlider.getValue(),
                        greenSlider.getValue(), blueSlider.getValue()));
                slidePreview.setBackground(new Color(redSlider.getValue(),
                        greenSlider.getValue(), blueSlider.getValue()));
                setButtonForeground(previewButton);
                setButtonForeground(slidePreview);
                setLights();
            }
        });

        // ///////////////////////////////////////////////

        Font boldFont = new Font("Ariel", Font.BOLD, 26);

        // Make separate panels for easily adding /arranging to main panel
        JPanel redPanel = new JPanel(new BorderLayout());
        JLabel rl = new JLabel(RED_LABEL_TEXT);
        rl.setForeground(new Color(255, 0, 0));
        rl.setFont(boldFont);
        redPanel.add(rl, BorderLayout.WEST);
        redPanel.add(redSlider, BorderLayout.CENTER);
        redPanel.add(redBox, BorderLayout.EAST);

        JPanel greenPanel = new JPanel(new BorderLayout());
        JLabel gl = new JLabel(GREEN_LABEL_TEXT);
        gl.setForeground(new Color(0, 210, 0));
        gl.setFont(boldFont);
        greenPanel.add(gl, BorderLayout.WEST);
        greenPanel.add(greenSlider, BorderLayout.CENTER);
        greenPanel.add(greenBox, BorderLayout.EAST);

        JPanel bluePanel = new JPanel(new BorderLayout());
        JLabel bl = new JLabel(BLUE_LABEL_TEXT);
        bl.setForeground(new Color(0, 0, 255));
        bl.setFont(boldFont);
        bluePanel.add(bl, BorderLayout.WEST);
        bluePanel.add(blueSlider, BorderLayout.CENTER);
        bluePanel.add(blueBox, BorderLayout.EAST);

        final JPanel lightingSliders = new JPanel();
        lightingSliders.setLayout(new GridBagLayout());
        GridBagConstraints f= new GridBagConstraints();
        
        f.gridx=0;
        f.ipady=20;
        f.ipadx=400;
        f.insets= new Insets(10,10,10,10);
        f.fill=GridBagConstraints.HORIZONTAL;
        
        f.gridy=1;
        lightingSliders.add(redPanel,f);

        f.gridy=2;
        lightingSliders.add(greenPanel,f);
        
        f.gridy=3;
        lightingSliders.add(bluePanel,f);
        
        f.gridy=0;
        
        f.insets= new Insets(-10,0,-10,0);
        f.anchor= GridBagConstraints.SOUTH;
        lightingSliders.add(slidePreview,f );

        return lightingSliders;

    }

    // public void paintComponent(Graphics g){
    // System.out.println("hello");
    // super.paintComponent(g);
    // //Draw a Box to show color
    // g.drawRect(100, 300, 50, 50);
    // g.setColor(Color.BLACK);
    // g.fillRect(25, 55, 200, 100);
    // g.drawString("hello", 40, 40);
    // }
    public JPanel getPanel() {
        return this.lightPanel;
    }

    public String getPanelName() {
        return lightName;
    }

    public Color getLightColor() {
        return new Color(redSlider.getValue(), greenSlider.getValue(),
                blueSlider.getValue());
    }

    // tell if the light color is darker or lighter
    // used to tell if writing on top of color should be white or black
    public boolean lightOrDark() {
        // light= T
        // dark = F

        // Special case for solid green- green is so light on its own
        if (greenSlider.getValue() == 255 && redSlider.getValue() == 0
                && blueSlider.getValue() == 0) {
            return true;
        }
        if (redSlider.getValue() + greenSlider.getValue()
                + blueSlider.getValue() > 383) {
            return true;
        }
        return false;
    }

    public void setButtonForeground(JButton button) {
        if (lightOrDark() == false) {
            // dark background so need light writing
            button.setForeground(Color.WHITE);
        } else {
            button.setForeground(Color.BLACK);
        }
    }

    public void setLights() {
        try {
            Runtime.getRuntime().exec(
                    "sudo python " + PATH_TO_LED_SCRIPT + " " + RED_PIN + " "
                            + GREEN_PIN + " " + BLUE_PIN + " "
                            + String.valueOf(REDval) + " "
                            + String.valueOf(GREENval) + " "
                            + String.valueOf(BLUEval));
            System.out.println("setting lights");
            System.out.println(""+ redBox.getValue()+ " " + greenBox.getValue()+" "+ blueBox.getValue());
        } catch (IOException error) {
            System.out.println("Error! Could not set LED levels");
        }
    }
}