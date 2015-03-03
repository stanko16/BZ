package guiFramework;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import structureDefinition.Cell;

public class ChooseNeighbour extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField upper;
	private JTextField left;
	private JTextField lower;
	private JTextField right;

	public ChooseNeighbour() {
		setTitle("Set neighbours");
		setBounds(30, 400, 303, 267);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		upper = new JTextField();
		upper.setBounds(104, 33, 86, 20);
		upper.setText(String.valueOf(Cell.getUpNeighbours()));
		contentPane.add(upper);
		upper.setColumns(10);
		
		left = new JTextField();
		left.setBounds(10, 76, 86, 20);
		left.setText(String.valueOf(Cell.getLeftNeighbours()));
		contentPane.add(left);
		left.setColumns(10);
		
		lower = new JTextField();
		lower.setBounds(104, 113, 86, 20);
		lower.setText(String.valueOf(Cell.getDownNeighbours()));
		contentPane.add(lower);
		lower.setColumns(10);
		
		right = new JTextField();
		right.setBounds(194, 76, 86, 20);
		right.setText(String.valueOf(Cell.getRightNeighbours()));
		contentPane.add(right);
		right.setColumns(10);
		
		JLabel lblLeft = new JLabel("Left");
		lblLeft.setHorizontalAlignment(SwingConstants.CENTER);
		lblLeft.setBounds(25, 51, 46, 14);
		contentPane.add(lblLeft);
		
		JLabel lblNewLabel = new JLabel("Up");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(125, 11, 46, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblRight = new JLabel("Right");
		lblRight.setHorizontalAlignment(SwingConstants.CENTER);
		lblRight.setBounds(217, 51, 46, 14);
		contentPane.add(lblRight);
		
		JLabel lblDown = new JLabel("Down");
		lblDown.setHorizontalAlignment(SwingConstants.CENTER);
		lblDown.setBounds(125, 88, 46, 14);
		contentPane.add(lblDown);
		
		JButton btnApply = new JButton("Update!");
		btnApply.setBounds(174, 155, 89, 23);
		btnApply.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
				int u = Integer.parseInt(upper.getText());
				int d = Integer.parseInt(lower.getText());
				int l = Integer.parseInt(left.getText());
				int r = Integer.parseInt(right.getText());
				if(-u<d && -l<r){
					Cell.setUpNeighbours(u);
					Cell.setDownNeighbours(d);
					Cell.setLeftNeighbours(l);
					Cell.setRightNeighbours(r);}
				else{JOptionPane.showMessageDialog(null,
						"Please note the following rules: -Up < Down, -Left < Right","?", 1);}
				} catch(Exception e){
					JOptionPane.showMessageDialog(null,"PLEASE INSERT ONLY INTEGER NUMBERS","!", 1);
				}
			}
		});
		contentPane.add(btnApply);
		
		JButton btnNewButton = new JButton("?");
		btnNewButton.setBounds(28, 155, 68, 23);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null,"Please set the number of neighbours" +
						" that you want a single cell to interact with. ","?", 1);
			}
		});
		contentPane.add(btnNewButton);
	}
}

