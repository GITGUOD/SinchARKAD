import java.util.*;

public class Worm {
    private ArrayList<Position> segments;

    public Worm() {
        this.segments = new ArrayList<>();
    }

    public void addSegment(Position position) {
        segments.add(position);
    }

    public ArrayList<Position> getSegments() {
        return segments;
    }
    
}
