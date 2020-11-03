import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Set;

import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JLabel;

public class MouseTracker implements MouseListener, MouseMotionListener {
	
	public Set<Product> held;

	@Override
	public void mouseMoved(MouseEvent e) {
		DrawingBoard.points.add(Utils.add(e.getPoint(), new Point(-8, -31)));
		Main.board.repaint();
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		Point mousePoint = Utils.add(e.getPoint(), new Point(-8, -31));
		ArrayList<Point> points = DrawingBoard.points;
		int dx = mousePoint.x - points.get(points.size()-1).x;
		int dy = mousePoint.y - points.get(points.size()-1).y;
		for (Product prod: held) {
			prod.x += dx;
			prod.y += dy;
		}
		DrawingBoard.points.add(mousePoint);
		Main.board.repaint();
	}

	public void mouseClicked(MouseEvent e) {
		Point mousePoint = Utils.add(e.getPoint(), new Point(-8, -31));
		if (Utils.isOnPaintable(mousePoint, Main.board.buybtn)) {
			JDialog dialog;
			JLabel dtext1, dtext2;
			if (Product.unlocked) {
				dialog = new JDialog(Main.frame, "Thanks!", true);
				dialog.setLayout(new BoxLayout(dialog.getContentPane(), BoxLayout.Y_AXIS));
				dialog.setSize(250, 90);
				dtext1 = new JLabel("Your purchase was a success.");
				dtext2 = new JLabel("Thanks for shopping with us!");
			} else {
				dialog = new JDialog(Main.frame, "NO", true);
				dialog.setLayout(new BoxLayout(dialog.getContentPane(), BoxLayout.Y_AXIS));
				dialog.setSize(320, 90);
				dtext1 = new JLabel("The buy button is a premium feature.");
				dtext2 = new JLabel("Please buy the buy button to proceed");
				DrawingBoard.products.remove(Product.buttonprod);
				Product.buttonprod = new Product("./images/storebuy.png", 150, 260, "\"Buy\" Button", 20);
				DrawingBoard.products.add(Product.buttonprod);
				Main.board.repaint();
			}
			dtext1.setFont(DrawingBoard.comicsans);
			dtext2.setFont(DrawingBoard.comicsans);
			dialog.add(dtext1);
			dialog.add(dtext2);
			dialog.setLocationRelativeTo(Main.frame);
			dialog.setVisible(true);
		}
	}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	
	@Override
	public void mousePressed(MouseEvent e) {
		Point mousePoint = Utils.add(e.getPoint(), new Point(-8, -31));
		held = Main.board.productsOn(mousePoint);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (Utils.isOnPaintable(e.getPoint(), Main.board.basket)) {
			for (Product prod: held) {
				prod.buy();
				Product.totalcost += prod.price;
			}
		}
		held = null;
	}
	
	

}
