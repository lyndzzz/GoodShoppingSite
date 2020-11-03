import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Utils {
	
	static final List<Currency> currencies; //Symbol and equivalent of one dollar
	static final Random rnd = new Random();
	
	static {
		currencies = new ArrayList<>();
		currencies.add(new Currency("USD", 1.));
		currencies.add(new Currency("EUR", 0.85));
		currencies.add(new Currency("GBP", 0.77));
		currencies.add(new Currency("INR", 74.43));
		currencies.add(new Currency("AUD", 1.40));
		currencies.add(new Currency("CAD", 1.31));
		currencies.add(new Currency("SGD", 1.36));
		currencies.add(new Currency("CHF", 0.91));
		currencies.add(new Currency("MYR", 4.16));
		currencies.add(new Currency("JPY", 104.69));
		currencies.add(new Currency("CNY", 6.68));
		currencies.add(new Currency("IDR", 14559.71));
	}
	
	public static Point add(Point p1, Point p2) {
		return new Point(p1.x+p2.x, p1.y+p2.y);
	}
	
	public static boolean isOnPaintable(Point point, Paintable obj) {
		return (obj.x <= point.x && point.x <= obj.x+obj.dx && obj.y <= point.y && point.y <= obj.y+obj.dy);
	}

	public static String randomCurrency(double price) {
		Currency curr = currencies.get(rnd.nextInt(currencies.size()));
		return String.format("%.2f %s", price*curr.invvalue, curr.name);
	}

}

class Currency {
	
	final String name;
	final double invvalue;
	
	public Currency(String name, double invvalue) {
		this.name = name;
		this.invvalue = invvalue;
	}
	
}
