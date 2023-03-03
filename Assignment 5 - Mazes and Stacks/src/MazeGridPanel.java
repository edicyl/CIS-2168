import javax.swing.*;
import java.awt.*;
import java.util.Stack;

public class MazeGridPanel extends JPanel
{
    private final int rows;
    private final int cols;
    private final Cell[][] maze;

    // Extra Credit: Generate a maze using depth-first search
    public void genDFSMaze()
    {
        boolean[][] visited;
        Stack<Cell> stack  = new Stack<Cell>();
        Cell start = maze[0][0];
        stack.push(start);
    }

    // Main Assignment: Solve the maze
    public void solveMaze()
    {
        // Create the stack to solve the maze
        Stack<Cell> stack  = new Stack<Cell>();

        // Set the start and finish cells
        Cell start = maze[0][0];
        start.setBackground(Color.GREEN);
        Cell finish = maze[rows - 1][cols - 1];
        finish.setBackground(Color.RED);

        // Push the start position to the stack
        stack.push(start);

        // While not at the finish cell, and the stack is not empty
        while (!stack.isEmpty() && !stack.peek().equals(finish))
        {
            // Get the current cell
            Cell current = stack.peek();

            // If able to move north and have not visited, move north
            if (!current.northWall && !visited(current.row - 1, current.col))
            {
                current.setBackground(Color.GREEN);
                stack.push(maze[current.row - 1][current.col]);
            }

            // Else if able to move south and have not visited, move south
            else if (!current.southWall && !visited(current.row + 1, current.col))
            {
                current.setBackground(Color.GREEN);
                stack.push(maze[current.row + 1][current.col]);
            }

            // Else if able to move east and have not visited, move east
            else if (!current.eastWall && !visited(current.row, current.col + 1))
            {
                current.setBackground(Color.GREEN);
                stack.push(maze[current.row][current.col + 1]);
            }

            // Else if able to move west and have not visited, move west
            else if (!current.westWall && !visited(current.row, current.col - 1))
            {
                current.setBackground(Color.GREEN);
                stack.push(maze[current.row][current.col - 1]);
            }

            // Else, at a dead end
            else
            {
                // Mark the current cell as dead
                maze[current.row][current.col].setBackground(Color.GRAY);

                // Pop the current cell off the stack
                stack.pop();
            }
        }

        // Mark the finish cell as visited
        finish.setBackground(Color.GREEN);
    }

    public boolean visited(int row, int col)
    {
        Cell c = maze[row][col];
        Color status = c.getBackground();

        return !status.equals(Color.WHITE) && !status.equals(Color.RED);
    }


    public void genNWMaze()
    {
        for (int row = 0; row  < rows; row++)
        {
            for (int col = 0; col < cols; col++)
            {
                // If in the corner, do nothing
                if(row == 0 && col ==0)
                {
                    continue;
                }

                // The top row is always open
                else if (row ==0)
                {
                    maze[row][col].westWall = false;
                    maze[row][col-1].eastWall = false;
                }

                // The left column is always open
                else if(col == 0)
                {
                    maze[row][col].northWall = false;
                    maze[row-1][col].southWall = false;
                }

                // Otherwise, randomly remove walls
                else
                {
                    boolean north = Math.random() < 0.5;

                    if (north)
                    {
                        maze[row][col].northWall = false;
                        maze[row-1][col].southWall = false;
                    }

                    else
                    {
                        maze[row][col].westWall = false;
                        maze[row][col-1].eastWall = false;
                    }

                    maze[row][col].repaint();
                }
            }
        }

        this.repaint();
    }

    public MazeGridPanel(int rows, int cols)
    {
        // Construct the maze
        this.setPreferredSize(new Dimension(800,800));
        this.rows = rows;
        this.cols = cols;

        // The maze is a grid of cells
        this.setLayout(new GridLayout(rows,cols));
        this.maze =  new Cell[rows][cols];

        for(int row = 0 ; row  < rows ; row++)
        {
            for(int col = 0; col < cols; col++)
            {
                // Create a new cell, add it to the array, add it to maze
                maze[row][col] = new Cell(row,col);
                this.add(maze[row][col]);
            }
        }

        this.genNWMaze();
        this.solveMaze();
    }
}