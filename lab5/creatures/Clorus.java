package creatures;

import huglife.Action;
import huglife.Creature;
import huglife.Direction;
import huglife.Occupant;

import java.awt.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;
import java.util.Random;

public class Clorus extends Creature {

    public Clorus(double e) {
        super("clorus");
        energy = e;
    }

    @Override
    public void move() {
        this.energy -= 0.03;
    }

    @Override
    public void attack(Creature c) {
        this.energy += c.energy();
    }

    @Override
    public Clorus replicate() {
        Clorus copy = new Clorus(this.energy / 2);
        this.energy /= 2;
        return copy;
    }

    @Override
    public void stay() {
        this.energy -= 0.01;
    }

    @Override
    /**
     * Rule 1: if there aren't empty squares, Clorus will STAY.
     * Rule 2 Otherwise, if any Plips arounds, Clorus will ATTACK one of them randomly.
     * Rule 3. Otherwise, if Clorus has energy >= 1, it'll replicate to a random empty square.
     * Rule 4. Otherwise, if Clorus will MOVE to a random empty square.
     */
    public Action chooseAction(Map<Direction, Occupant> neighbors) {
        // Rule 1
        Deque<Direction> emptyNeighbors = new ArrayDeque<>();
        Deque<Direction> nearbyPlips = new ArrayDeque<>();
        // (Google: Enhanced for-loop over keys of NEIGHBORS?)
        for (Direction i : neighbors.keySet()) {
            String value = neighbors.get(i).name();
            if (value.equals("empty")) {
                emptyNeighbors.addLast(i);
            } else if (value.equals("plip")) {
                nearbyPlips.addLast(i);
            }
        }

        if (emptyNeighbors.isEmpty()) {
            // System.out.println(Action.ActionType.STAY.toString());
            return new Action(Action.ActionType.STAY);
        }

        // Rule 2
        // ATTACK if see Plips
        if (!nearbyPlips.isEmpty()) {
            int k = new Random().nextInt(nearbyPlips.size());
            for(int i = 0; i < k - 1; i++) {
                nearbyPlips.removeFirst();
            }
            Direction dir = nearbyPlips.removeFirst();
            //System.out.println(Action.ActionType.MOVE.toString() + " " + dir.toString());
            return new Action(Action.ActionType.ATTACK, dir);
        }

        // Rule 3
        if(this.energy >= 1.0) {
            int k = new Random().nextInt(emptyNeighbors.size());
            for(int i = 0; i < k - 1; i++) {
                emptyNeighbors.removeFirst();
            }
            Direction dir = emptyNeighbors.removeFirst();
            // System.out.println(Action.ActionType.REPLICATE.toString() + " " + dir.toString());
            return new Action(Action.ActionType.REPLICATE, dir);

        }

        // Rule 4 (if none of the first 3)
        int k = new Random().nextInt(emptyNeighbors.size());
        for(int i = 0; i < k - 1; i++) {
            emptyNeighbors.removeFirst();
        }
        Direction dir = emptyNeighbors.removeFirst();
        // System.out.println(Action.ActionType.MOVE.toString()+ " " + dir.toString());
        return new Action(Action.ActionType.MOVE, dir);
    }

    @Override
    public Color color() {
        return color(34, 0, 231);
    }
}
