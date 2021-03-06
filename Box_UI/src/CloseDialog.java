package src;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

public class CloseDialog extends JDialog implements ActionListener {

	JButton button;
	JPanel panel;

	public CloseDialog(String dialogTitle, JPanel p, JButton closeButton, int width, int height) {
		this.setTitle(dialogTitle);
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		GridBagConstraints b = new GridBagConstraints();

		// buttonPane.setPreferredSize(new Dimension(800,480));
		buttonPane.setPreferredSize(new Dimension(width, height));
		this.setResizable(false);
		button = closeButton;
		panel = p;

		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				dispose();

			}

		});
		button.setPreferredSize(new Dimension(200, 50));

		c.gridx = 0;
		c.gridy = 0;
		c.fill= GridBagConstraints.BOTH;
		buttonPane.add(panel, c);

		b.gridx = 0;
		b.gridy = 1;
		buttonPane.add(button, b);

		add(buttonPane);
		pack();
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		dispose();

	};

	// public void paint(Graphics g) {
	// Graphics2D ga = (Graphics2D)g;
	// ga.setPaint(Color.red);
	// ga.drawOval(150,150,100,100);
	//
	// }
}
