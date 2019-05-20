package creatures;

import huglife.Creature;
import huglife.Direction;
import huglife.Action;
import huglife.Occupant;

import java.awt.Color;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;
import java.util.Random;

/**
 * An implementation of a motile pacifist photosynthesizer.
 *
 * @author Josh Hug
 */
public class Plip extends Creature {

    /**
     * red color.
     */
    private int r;
    /**
     * green color.
     */
    private int g;
    /**
     * blue color.
     */
    private int b;

    /**
     * creates plip with energy equal to E.
     */
    public Plip(double e) {
        super("plip");
        r = 0;
        g = 0;
        b = 0;
        energy = e;
    }

    /**
     * creates a plip with energy equal to 1.
     */
    public Plip() {
        this(1);
    }

    /**
     * Should return a color with red = 99, blue = 76, and green that varies
     * linearly based on the energy of the Plip. If the plip has zero energy,
     * it should have a green value of 63. If it has max energy, it should
     * have a green value of 255. The green value should vary with energy
     * linearly in between these two extremes. It's not absolutely vital
     * that you get this exactly correct.
     */
    public Color color() {
        g = 96* (int) Math.round(this.energy) + 63;
        int red = 99;
        int blue = 76;
        return color(red, g, blue);
    }

    /**
     * Do nothing with C, Plips are pacifists.
     */
    public void attack(Creature c) {
        // do nothing.
    }

    /**
     * Plips should lose 0.15 units of energy when moving. If you want to
     * to avoid the magic number warning, you'll need to make a
     * private static final variable. This is not required for this lab.
     */
    public void move() {
        this.energy -= 0.15;
        if (this.energy < 0) {
            this.energy = 0;
        }
    }


    /**
     * Plips gain 0.2 energy when staying due to photosynthesis.
     */
    public void stay() {
        this.energy += 0.2;
        if (this.energy > 2) {
            this.energy = 2;
        }
    }

    /**
     * Plips and their offspring each get 50% of the energy, with none
     * lost to the process. Now that's efficiency! Returns a baby
     * Plip.
     */
    public Plip replicate() {
        Plip copy = new Plip(this.energy / 2);
        this.energy /= 2;
        return copy;
    }

    /**
     * Plips take exactly the following actions based on NEIGHBORS:
     * 1. If no empty adjacent spaces, STAY.
     * 2. Otherwise, if energy >= 1, REPLICATE towards an empty direction
     * chosen at random.
     * 3. Otherwise, if any Cloruses, MOVE with 50% probability,
     * towards an empty direction chosen at random.
     * 4. Otherwise, if nothing else, STAY
     * <p>
     * Returns an object of type Action. See Action.java for the
     * scoop on how Actions work. See SampleCreature.chooseAction()
     * for an example to follow.
     */
    public Action chooseAction(Map<Direction, Occupant> neighbors) {
        // Rule 1
        Deque<Direction> emptyNeighbors = new ArrayDeque<>();
        boolean anyClorus = false;
        // (Google: Enhanced for-loop over keys of NEIGHBORS?)
        for (Direction i : neighbors.keySet()) {
            String value = neighbors.get(i).name();
            if (value.equals("empty")) {
                emptyNeighbors.addLast(i);
            } else if (value.equals("clorus")) {
                anyClorus = true;
            }
        }

        if (emptyNeighbors.isEmpty()) {
            // System.out.println(Action.ActionType.STAY.toString());
            return new Action(Action.ActionType.STAY);
        }

        // Rule 2
        // HINT: randomEntry(emptyNeighbors)
        if(this.energy >= 1.0) {
            int k = new Random().nextInt(emptyNeighbors.size());
            for(int i = 0; i < k - 1; i++) {
                emptyNeighbors.removeFirst();
            }
            Direction dir = emptyNeighbors.removeFirst();
            // System.out.println(Action.ActionType.REPLICATE.toString() + " " + dir.toString());
            return new Action(Action.ActionType.REPLICATE, dir);

        }
        // Rule 3
        // Move if see Clorus
        if (anyClorus) {
            int willMove = new Random().nextInt(1);
            if (willMove == 1) {
                int k = new Random().nextInt(emptyNeighbors.size());
                for(int i = 0; i < k - 1; i++) {
                    emptyNeighbors.removeFirst();
                }
                Direction dir = emptyNeighbors.removeFirst();
                //System.out.println(Action.ActionType.MOVE.toString() + " " + dir.toString());
                return new Action(Action.ActionType.MOVE, dir);
            }
        }

        // Rule 4 (if none of the first 3)
        // System.out.println(Action.ActionType.STAY.toString());
        return new Action(Action.ActionType.STAY);
    }
}
