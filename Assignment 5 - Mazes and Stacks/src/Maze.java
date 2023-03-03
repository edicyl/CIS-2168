import javax.swing.JFrame;

public class Maze extends JFrame
{
    public Maze()
    {
        // Create the maze based on specifications
        this.add(new MazeGridPanel(20,20));
        this.setSize(800, 800);

        // Maze behavior
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
    }

    public static void main(String[] args)
    {
        new Maze();
    }
}

