import java.util.Locale;

public class Main {
	
	static MyFrame frame;
	static DrawingBoard board;

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		frame = new MyFrame("Good Shopping Site");
		board = frame.board;
		frame.setVisible(true);
	}

}
