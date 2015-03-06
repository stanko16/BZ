/**
 * 
 */
package guiFramework;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.Timer;

import structureDefinition.Cell;
import structureDefinition.Matrix;

/**
 * @author Stanko Ovkaric
 * 
 */
public class GuiManagerBZ {
	
	//Attributes needed inside and outside the this class. Those ones needed in the classes inside this one are private, the public ones may be used outside.
	public static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private static int WIDTH = 5000;
	private static int HEIGHT = 5000;
	private static CanvasManager cm;
	private static Timer timer = new Timer(0, null);
	private static boolean began = false;
	public static JFrame frame;
	
	private static int red = 0;
	private static int green = 0;
	private static int blue = 0;

	private static JButton stop;
	private static JButton step;
	private static JButton start;
	private static JMenuItem speed;

	public static void main(String[] args) {
		//Frame and Canvas creation
		cm = new CanvasManager(WIDTH, HEIGHT);
		frame = new JFrame("Belousov-Zhabotinsky reactions generator");
		cm.setSize(WIDTH / 10, HEIGHT / 10);
		frame.setSize(WIDTH/10 +24, HEIGHT / 10+74);
		cm.addMouseListener(new MouseAdapter() {

			/**
			 * When a point in the canvas is clicked, the state of that cell increases and the color of that point is also changed
			 */
			public void mouseClicked(MouseEvent arg0) {
				//We get the x and y of the point we  pressed with respect to the canvas
				Point point = MouseInfo.getPointerInfo().getLocation();
				Point point2 = cm.getLocationOnScreen();
				int x = (int) ((point.x - point2.x) / CanvasManager.n); //we divide the value by the number of pixels considered to be a Cell
				int y = (int) ((point.y - point2.y) / CanvasManager.n);
				//We get the state of the cell in that position and change the color of that space in the canvas
				int state = Matrix.getMatrix()[x][y].getState();
				if (state < Cell.n) {
					Color colr = new Color(255-state, 255-state, 255-state);
					cm.fill(x, y, colr);
				} else {
					Color colr = new Color(0, 0, 0);
					cm.fill(x, y, colr);
				}
				//at the end we increment the state of the cell
				Matrix.incrementCellState(x, y);
			}
			
		});

		JScrollPane scroll = new JScrollPane(cm);
		DragScrollHandler.createDragScrollHandlerFor(cm);

		//Buttons
		step = new JButton("STEP");
		step.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				stepTimer();
			}
		});

		stop = new JButton("STOP");
		stop.setVisible(false);
		stop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				stopTimer();
				stop.setVisible(false);
				start.setVisible(true);
				step.setVisible(true);
			}
		});
		start = new JButton("START");
		start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!began) {
					beginTimer();
				} else {
					startTimer();
				}
				step.setVisible(false);
				start.setVisible(false);
				stop.setVisible(true);
			}
		});

		
		//Menu
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("Options");
		JMenu operations = new JMenu("Operations");
		JMenu help = new JMenu("Help");

		JMenuItem slow = new JMenuItem("SlowDown");
		slow.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				timer.setDelay(timer.getDelay() + 40);
				if(timer.getDelay()>0) {
					speed.setEnabled(true);
				}
			}
		});

		speed = new JMenuItem("SpeedUp");
		if(timer.getDelay()==0) {
			speed.setEnabled(false);
		}else {
			speed.setEnabled(true);
		}
		speed.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (timer.getDelay() > 0) {
					timer.setDelay(timer.getDelay() - 40);
				}
			}
		});

		JMenuItem color = new JMenuItem("Change Color");
		color.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JFrame f = new JFrame();

				Color color = JColorChooser.showDialog(f, "Select a Color",
						Color.white);
				if (color != null) {
					red = color.getRed();
					green = color.getGreen();
					blue = color.getBlue();
					repaintCanvas();
				}
			}
		});

		JMenuItem rand = new JMenuItem("Generate Random Matrix");
		rand.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Matrix.GenerateRandomMatrix(WIDTH / 10, HEIGHT / 10);
				repaintCanvas();
				stopTimer();
				stop.setVisible(false);
				start.setVisible(true);
				step.setVisible(true);
			}
		});
		JMenuItem empt = new JMenuItem("Generate Empty Matrix");
		empt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Matrix.GenerateVoidMatrix(WIDTH / 10, HEIGHT / 10);
				repaintCanvas();
				stopTimer();
				stop.setVisible(false);
				start.setVisible(true);
				step.setVisible(true);
			}
		});

		JMenuItem newrule = new JMenuItem("Apply new rule");
		newrule.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				UpdateConstants uc = new UpdateConstants();
				uc.setVisible(true);
			}
		});

		JMenuItem chooseNeighbor = new JMenuItem("Choose Neighbours");
		chooseNeighbor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ChooseNeighbour cn = new ChooseNeighbour();
				cn.setVisible(true);
			}
		});

		JMenuItem exit = new JMenuItem("Exit");
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});

		JMenuItem aboutitem = new JMenuItem("About");
		aboutitem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane
						.showMessageDialog(
								null,
								"Author :    Stanko Ovkaric\n"
										+ "Webpage:	 www.VicariousMirror.com",
								"About", 1);

			}
		});


		menuBar.add(menu);
		menuBar.add(operations);
		menuBar.add(help);
		menuBar.add(start);
		menuBar.add(step);
		menuBar.add(stop);

		help.add(aboutitem);
		menu.add(rand);
		menu.add(empt);
		menu.addSeparator();
		menu.add(exit);

		operations.add(newrule);
		operations.add(chooseNeighbor);
		operations.addSeparator();
		operations.add(slow);
		operations.add(speed);
		operations.addSeparator();
		operations.add(color);

		frame.setJMenuBar(menuBar);
		frame.setLocation(400, 10);
		frame.add(scroll, BorderLayout.CENTER);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Matrix.GenerateVoidMatrix(WIDTH / 10, HEIGHT / 10);
		repaintCanvas();
		
		
		//Here we're telling the user how to begin
		JOptionPane.showMessageDialog(null,"To begin, click anywhere on the white space and press 'Start' \n Don't forget to check the operations menu!","Tutorial", 1);
		
		
		
	}
	
	/**
	 * If the timer is running, it is stopped
	 */
	public static void stopTimer() {
		if (timer.isRunning())
			timer.stop();
	}

	/**
	 * Apply only once the update on all the cells
	 */
	public static void stepTimer() {
		checkUpdate();
	}

	/**
	 * Starts the timer
	 */
	public static void startTimer() {
		timer.start();
	}

	/**
	 * The timer is set with 0 delay (full speed) and starts the updating the cells in the matrix
	 */
	public static void beginTimer() {
		began = true;
		timer = new Timer(0, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				checkUpdate();
			}
		});
		timer.start();
	}

	/**
	 * This method checks the cells in the matrix, setting the next state for each one of them and then updates their value.
	 * Finally the canvas is repaint to see the changes.
	 */
	public static void checkUpdate() {
		Matrix.checkMatrix();
		Matrix.updateMatrix();
		repaintCanvas();
	}

	/**
	 * Each cell is painted in the canvas based on it's current state and the color decided by the user.
	 */
	public static void repaintCanvas() {
		for (int j = 0; j < WIDTH / 10; j++) {
			for (int i = 0; i < HEIGHT / 10; i++) {
				int state = Matrix.getMatrix()[j][i].getState();
				while (state < 0) {
					state += 255;
				}
				while (state > 255) {
					state = state - 255;
				}
				Color colr = new Color(red, green, blue, state);
				if(state==0){colr=Color.white;}
				cm.fill(j, i, colr);
			}
		}

	}

}
