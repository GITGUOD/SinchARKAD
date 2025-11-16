import java.util.*;

public class DancingWorms {
    private static Scanner scanner = new Scanner(System.in);
    private static Random random = new Random();

    public static void main(String[] args) {
        
        System.out.println("Enter width, and height game parameters:");
        int width = scanner.nextInt();
        int height = scanner.nextInt();
        scanner.nextLine();
        
        // KOmmenterad ut eftersom vi testade med exempel inputen
        
        // if (width < 1 || width > 25 || height < 1 || height > 25) {
        //     System.out.println("Width and height must be between 1 and 25");
        //     return;
        // }
        System.out.println("Enter number of spotlights and their rounds:");
        int numberOfSpotlights = scanner.nextInt();
        ArrayList<Integer> spotlightRounds = new ArrayList<>();
        for (int i = 0; i < numberOfSpotlights; i++) {
            spotlightRounds.add(scanner.nextInt());
        }
        scanner.nextLine();

        // KOmmenterad ut eftersom vi testade med exempel inputen

        // if(!checkSpotLightData(spotlightRounds, numberOfSpotlights)) {
        //     return;
        // }
        System.out.println("Enter number of worms:");
        int numberOfWorms = scanner.nextInt();
        scanner.nextLine();

        // KOmmenterad ut eftersom vi testade med exempel inputen

        // if(numberOfWorms < 3 || numberOfWorms > 10) {
        //     System.out.println("Number of worms must be between 3 and 10 on the dance floor");
        //     return;
        // }

        
        String[][] colorsOnTheBoard = new String[height][width];
        System.out.println("Enter colors on the board R B R etc: ");

        for (int i = 0; i < height; i++) {
            String[] row = scanner.nextLine().split(" ");

            if(row.length != width) {
                System.out.println("Invalid number of colors in row ");
                return;
            }
            for (int j = 0; j < width; j++) {
                colorsOnTheBoard[i][j] = row[j];
            }
        }

        Board board = new Board(width, height);
        board.initializeBoard(colorsOnTheBoard);
            System.out.println("Enter worm coordinates:");

        ArrayList<Worm> worms = new ArrayList<>();
        for (int i = 0; i < numberOfWorms; i++) {
            Worm worm = new Worm();
            String[] wormCordinates = scanner.nextLine().split(" ");
            for (String wormPart : wormCordinates) {
                String[] xyPosition = wormPart.split(",");
                int x = Integer.parseInt(xyPosition[0]);
                int y = Integer.parseInt(xyPosition[1]);
                if(x < 0 || x >= width || y < 0 || y >= height) {
                    System.out.println("Worm coordinates out of bounds!");
                    return;
                }
                worm.addSegment(new Position(x, y, null));
            }
            worms.add(worm);
        }

        int totalBeats = spotlightRounds.get(spotlightRounds.size() - 1);

        
        for (int beat = 0; beat <= totalBeats; beat++) {
            if (spotlightRounds.contains(beat)) {
                System.out.println("x");
            } else {
                int wormIndex = random.nextInt(numberOfWorms);
                String[] directions = {"l", "r"};
                String direction = directions[random.nextInt(2)];
                System.out.println(wormIndex + " " + direction);
            }
        }

        int score = calculateScore(board, worms);
        System.out.println("Score: " + score);
    }

    private static int calculateScore(Board board, ArrayList<Worm> worms) {
        int score = 0;
        for (Worm worm : worms) {
            ArrayList<Position> segments = worm.getSegments();
            for (int i = 0; i < segments.size(); i++) {
                Position pos = segments.get(i);
                Colour color = board.getColourAt(pos.getX(), pos.getY());
                if ((i < 3 && color == Colour.BLUE) || (i >= 3 && color == Colour.RED)) {
                    score++;
                }
            }
        }
        return score;
    }

    /*Collisions metoden, används inte här än men framtida utveckling */
    private static boolean collision(ArrayList<Worm> worms) {
        for (int i = 0; i < worms.size(); i++) {
            for (int j = i + 1; j < worms.size(); j++) {
                for (Position segA : worms.get(i).getSegments()) {
                    for (Position segB : worms.get(j).getSegments()) {
                        if (segA.getX() == segB.getX() && segA.getY() == segB.getY()) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    private static boolean checkSpotLightData(ArrayList<Integer> spotlightRounds, int numberOfSpotlights ) {
        if (numberOfSpotlights < 4 || numberOfSpotlights > 10) {
            System.out.println("Invalid number of spotlights");
            return false;
        }

        for (int i = 0; i < numberOfSpotlights; i++) {
            int beat = spotlightRounds.get(i);
            if (beat < 0 || beat >= 100) {
                System.out.println("Invalid beat value");
                return false;
            }
            if (i > 0 && beat <= spotlightRounds.get(i - 1)) {
                System.out.println("Beat values must be strictly increasing");
                return false;
            }
        }
        
        return true;

    }

}