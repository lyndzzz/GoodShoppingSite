import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Paintable {
	
	public Image image;
	public int x, y, dx, dy;
	
	public Paintable(Image image, int x, int y) {
		this.image = image;
		this.x = x;
		this.y = y;
		this.dx = image.getWidth(null);
		this.dy = image.getWidth(null);
	}
	
	public Paintable(String filename, int x, int y) {
		try {
			this.image = ImageIO.read(new File(filename));
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
		}
		this.x = x;
		this.y = y;
		this.dx = image.getWidth(null);
		this.dy = image.getWidth(null);
	}
	
	public void draw(Graphics2D g) {
		g.drawImage(image, x, y, null);
	}

}

class Product extends Paintable {
	
	static double totalcost;
	static boolean unlocked;
	static Product buttonprod;
	String name;
	double price;
	int bought;
	private final int defaultx, defaulty;
	
	Product(String filename, int x, int y, String name, double price) {
		super(filename, x, y);
		this.name = name;
		this.price = price;
		this.bought = 0;
		this.defaultx = x;
		this.defaulty = y;
	}
	
	@Override
	public void draw(Graphics2D g) {
		g.drawImage(image, x, y, null);
		g.drawString(name, x, y+dy);
		g.drawString(Utils.randomCurrency(price), x, y+dy+25);
	}
	
	public void buy() {
		this.x = defaultx;
		this.y = defaulty;
		this.bought++;
		if (this == Product.buttonprod) {
			unlocked = true;
			try {
				Main.board.buybtn.image = ImageIO.read(new File("./images/buy.png"));
			} catch (Exception e) {
				e.printStackTrace();
				System.exit(0);
			}
		}
	}
	
}
