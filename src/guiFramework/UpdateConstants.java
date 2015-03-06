package guiFramework;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import structureDefinition.Cell;
import java.awt.Font;

@SuppressWarnings("serial")
public class UpdateConstants extends JFrame {

	private JPanel contentPane;
	private JTextField k1;
	private JTextField k2;
	private JTextField g;
	private JLabel lblPleaseInsertThe;
	private JLabel lblDefaultKK;
	
	public UpdateConstants() {
		setTitle("Constants");
		setBounds(40, 100, 234, 260);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		k1 = new JTextField();
		k1.setBounds(83, 53, 89, 20);
		k1.setText(String.valueOf((int)Cell.getK1()));
		contentPane.add(k1);
		k1.setColumns(10);
		
		k2 = new JTextField();
		k2.setBounds(83, 84, 89, 20);
		k2.setText(String.valueOf((int)Cell.getK2()));
		contentPane.add(k2);
		k2.setColumns(10);
		
		g = new JTextField();
		g.setBounds(83, 115, 89, 20);
		g.setText(String.valueOf((int)Cell.getG()));
		contentPane.add(g);
		g.setColumns(10);
		
		JLabel lblK = new JLabel("k1");
		lblK.setHorizontalAlignment(SwingConstants.LEFT);
		lblK.setBounds(45, 59, 28, 14);
		contentPane.add(lblK);
		
		JLabel lblK_1 = new JLabel("k2");
		lblK_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblK_1.setBounds(45, 90, 28, 14);
		contentPane.add(lblK_1);
		
		JLabel lblG = new JLabel("g");
		lblG.setHorizontalAlignment(SwingConstants.LEFT);
		lblG.setBounds(45, 121, 28, 14);
		contentPane.add(lblG);
		
		lblPleaseInsertThe = new JLabel("Please insert the constant values");
		lblPleaseInsertThe.setHorizontalAlignment(SwingConstants.CENTER);
		lblPleaseInsertThe.setBounds(10, 28, 194, 14);
		contentPane.add(lblPleaseInsertThe);
		
		JButton update = new JButton("Update!");
		update.addActionListener(new ActionListener() {
			/**
			 * We take the coefficients from the text fields and apply them to the Cell class, 
			 * so then they'll be applied to the whole matrix
			 */
			public void actionPerformed(ActionEvent arg0) {
				try{
					Cell.setK1(Double.parseDouble(k1.getText()));
					Cell.setK2(Double.parseDouble(k2.getText()));
					Cell.setG(Double.parseDouble(g.getText()));
				}catch(Exception souldBeOnlyInteger){
					JOptionPane.showMessageDialog(null,"ERROR - Please insert only integer numbers > 0","About", 1);
				}
			}
		});
		update.setBounds(76, 170, 110, 23);
		contentPane.add(update);
		
		lblDefaultKK = new JLabel("Default k1=1, k2=1, g=7");
		lblDefaultKK.setFont(new Font("Tahoma", Font.PLAIN, 8));
		lblDefaultKK.setHorizontalAlignment(SwingConstants.CENTER);
		lblDefaultKK.setBounds(35, 145, 127, 14);
		contentPane.add(lblDefaultKK);
		
		JButton question = new JButton("?");
		question.setBounds(27, 170, 46, 23);
		question.addActionListener(new ActionListener() {
			/**
			 * Brief explanation of what each coefficient is used for 
			 */
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null,"k1 divides the influence of infected cells, "+"\n"+
						"k2 divides the influence of the ill cells and"+"\n"+
						"g determines how fast an infected cell becomes ill.","?", 1);
			}
		});
		contentPane.add(question);
	}
}
