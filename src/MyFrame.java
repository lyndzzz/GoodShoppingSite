import java.awt.Dimension;
import javax.swing.JFrame;

public class MyFrame extends JFrame {
	
	DrawingBoard board;
	
	public MyFrame(String name) {
		super(name);
		setPreferredSize(new Dimension(400, 425));
		setSize(new Dimension(400, 425));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		board = new DrawingBoard();
		MouseTracker tracker = new MouseTracker();
		addMouseListener(tracker);
		addMouseMotionListener(tracker);
		add(board);
	}

}
