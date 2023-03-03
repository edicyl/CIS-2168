import java.awt.*;
import javax.swing.*;

public class Cell extends JPanel
{
    // Boolean values to determine if a wall is drawn or not
    boolean northWall = true;
    boolean southWall = true;
    boolean eastWall  = true;
    boolean westWall  = true;

    // The cell's coordinates
    int row;
    int col;

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.setColor(Color.BLACK);

        int y = this.getHeight();
        int x = this.getWidth();
        g.setColor(Color.BLACK);

        // Statements to draw each wall
        if (northWall)
        {
            g.drawLine(0,0,x, 0);
        }

        if (southWall)
        {
            g.drawLine(0,y,x, y);
        }

        if (eastWall)
        {
            g.drawLine(x,0,x, y);
        }
        if (westWall)
        {
            g.drawLine(0,0,0, y);
        }
    }

    public Cell(int row, int col)
    {
        this.setBackground(Color.WHITE);
        this.row = row;
        this.col = col;
        northWall = true;
        southWall = true;
        eastWall = true;
        westWall = true;
    }
}