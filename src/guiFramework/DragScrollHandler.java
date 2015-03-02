package guiFramework;


import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JComponent;
import javax.swing.SwingUtilities;

public class DragScrollHandler extends MouseAdapter {

	private JComponent component;
	private Point pressed, here;
	private Rectangle visiRect;

	public DragScrollHandler(JComponent component) {
		this.component = component;
		component.addMouseListener(this);
		component.addMouseMotionListener(this);
	}

	public static void createDragScrollHandlerFor(JComponent component) {
		new DragScrollHandler(component);
	}

	public void dispose() {
		component.removeMouseListener(this);
		component.removeMouseMotionListener(this);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		pressed = e.getPoint();
		visiRect = component.getVisibleRect();
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		here = e.getPoint();
		visiRect.x += (pressed.x - here.x);
		visiRect.y += (pressed.y - here.y);
		component.scrollRectToVisible(visiRect);
		SwingUtilities.invokeLater(new Runnable() {

			public void run() {
				Rectangle newRect = component.getVisibleRect();
				pressed.x += newRect.x - visiRect.x;
				pressed.y += newRect.y - visiRect.y;
				visiRect = newRect;
			}
		});
	}
}
