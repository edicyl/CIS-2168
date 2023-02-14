public class Rabbit extends Animal
{
    public Rabbit(Model model, int row, int column)
    {
        super(model, row, column);
    }

    int decideMove()
    {
        boolean haveSeenFox = false;
        int directionToFox = Model.N;

        // If the rabbit looks around and sees the fox, log it and the direction
        for (int i = Model.MIN_DIRECTION; i <= Model.MAX_DIRECTION; i++)
        {
            if (look(i) == Model.FOX)
            {
                haveSeenFox = true;
                directionToFox = i;
            }
        }

        // Only move if you have seen the fox (meaning the fox saw you)
        // From the list of directions, turn that much away the fox (if possible)
        if (haveSeenFox)
        {
            int[] moveList = {Model.SW, Model.SE, Model.S, Model.W, Model.E, Model.NW, Model.NE};

            for (int i : moveList)
            {
                if (canMove(Model.turn(directionToFox, i)))
                {
                    return Model.turn(directionToFox, i);
                }
            }
        }

        return Model.STAY;
    }
}