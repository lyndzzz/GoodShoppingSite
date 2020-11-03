import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JPanel;

public class DrawingBoard extends JPanel {
	
	static Font comicsans = new Font("Comic Sans MS", Font.ITALIC, 16);
	static ArrayList<Point> points = new ArrayList<>();
	static Set<Product> products;
	Paintable basket, buybtn;
	
	public DrawingBoard() {
		basket = new Paintable("./images/basket.png", 275, 70);
		buybtn = new Paintable("./images/disabledbuy.png", 275, 220);
		products = new HashSet<>();
		products.add(new Product("./images/apple.png", 25, 10, "Apple", 0.8));
		products.add(new Product("./images/pear.png", 150, 10, "Pear", 1.2));
		products.add(new Product("./images/banana.png", 25, 135, "Banana", 1.6));
		products.add(new Product("./images/carrot.png", 150, 135, "Carrot", 0.6));
		products.add(new Product("./images/pepper.png", 25, 260, "Pepper", 0.3));
		Product.buttonprod = new Product("./images/premium.png", 150, 260, "Premium Feature", 20);
		products.add(Product.buttonprod);
		
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setFont(comicsans);
		basket.draw(g2);
		g2.drawString("Cost:", 275, 175);
		g2.drawString(Utils.randomCurrency(Product.totalcost), 275, 200);
		buybtn.draw(g2);
		for (Product prod: products) {
			prod.draw(g2);
		}
		g2.setColor(Color.BLUE);
		g2.setStroke(new BasicStroke(2));
		for (int i = 0; i < points.size()-1; i++) {
			g2.drawLine(points.get(i).x, points.get(i).y, points.get(i+1).x, points.get(i+1).y);;
		}
	}

	public Set<Product> productsOn(Point point) {
		Set<Product> result = new HashSet<>();
		for (Product prod: products) {
			if (prod.x <= point.x && point.x <= prod.x+100 && prod.y <= point.y && point.y <= prod.y+100) {
				result.add(prod);
			}
		}
		return result;
	}

}
