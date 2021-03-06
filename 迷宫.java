import java.io.*;
import java.util.*;
import java.util.List;


public class Maze {
    enum Block {
        EMPTY(0),WALL(1), IMPASSE(2), PACE(3),DOT(4),DEST(5);
        int code;
        Block(int code) {
            this.code = code;
        }
    }
    enum Direction {
        UP(0), DOWN(1), LEFT(2), RIGHT(3);
        int code;
        Direction(int code) {
            this.code = code;
        }
        public int getCode(){
            return code;
        }
        public Direction getOpposite() {
            switch (this) {
                case UP:
                    return DOWN;
                case DOWN:
                    return UP;
                case LEFT:
                    return RIGHT;
                case RIGHT:
                    return LEFT;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        public static Direction getDir(int code) {
            switch (code) {
                case 0:
                    return UP;
                case 1:
                    return DOWN;
                case 2:
                    return LEFT;
                case 3:
                    return RIGHT;
                default:
                    throw new UnsupportedOperationException();
            }
        }
    }

    int width;
    int height;
    Block[][] map;
    Map<Block,int[]> location = new HashMap<>();

    private void writeMap(boolean flag) throws IOException {
        if (flag) {
            File map = new File(".\\default_map.txt");
            BufferedWriter out = new BufferedWriter(new FileWriter(map));
            for (int i = height-1; i >= 0; i--) {
                out.write(Arrays.toString(this.map[i]));
                out.write("\n");
            }
            out.flush();
            out.close();
        } else {
            File map = new File(".\\paced_map.txt");
            BufferedWriter out = new BufferedWriter(new FileWriter(map));
            for (int i = height-1; i >= 0; i--) {
                out.write(Arrays.toString(this.map[i]));
                out.write("\n");
            }
            out.flush();
            out.close();
        }

    }
    public Maze(int width, int height, int blockNum) throws IOException {
        this.height = height;
        this.width = width;
        map = new Block[width][height];
        for (int i = 1; i < width - 1; i++) {
            for(int j = 1; j < height - 1; j++) {
                map[i][j] = Block.EMPTY;
            }
        }
        for (int i = 0; i < width; i++) {
            map[i][0] = Block.WALL;
            map[i][height-1] = Block.WALL;
        }
        for (int i = 0; i < height; i++) {
            map[0][i] = Block.WALL;
            map[width-1][i] = Block.WALL;
        }
        Random rand = new Random();
        for (int i = 0; i < blockNum; i++) {
            var w = rand.nextInt(width);
            var h = rand.nextInt(height);
            map[w][h] = Block.WALL;
        }
        {
            var w = rand.nextInt(width);
            var h = rand.nextInt(height);
            if (map[w][h] != Block.EMPTY) {
                w = rand.nextInt(width-1);
                h = rand.nextInt(height-1);
            }
            location.put(Block.DOT, new int[]{w, h});
        }
        {
            var w = rand.nextInt(width);
            var h = rand.nextInt(height);
            if (map[w][h] != Block.EMPTY) {
                w = rand.nextInt(width-1);
                h = rand.nextInt(height-1);
            }
            location.put(Block.DEST,new int[]{w,h});
        }
        writeMap(true);

    }
    public void findWay() throws IOException {
        var coords = location.get(Block.DOT);
        var i = coords[0];
        var j = coords[1];
        System.out.printf("Dot at( %d, %d) ",i,j);
        System.out.println();
        System.out.printf("Dest at( %d, %d) ",location.get(Block.DEST)[0],location.get(Block.DEST)[1]);
        System.out.println();
        try {
            getWay(i,j);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        writeMap(false);
    }
    private Direction randomChooseDirection(List<Direction> dirs) {
        Random rand = new Random();
        int code = rand.nextInt(4);
        while (!dirs.contains(Direction.getDir(code)))
            code = rand.nextInt(4);
        return Direction.getDir(code);
    }

    private boolean getWay(int i, int j) throws InterruptedException {
        location.put(Block.DOT, new int[]{i, j});
        System.out.printf("( %d, %d )",location.get(Block.DOT)[0],location.get(Block.DOT)[1]);
        System.out.println();
        var coordsDest = location.get(Block.DEST);
        if (Arrays.equals(coordsDest,location.get(Block.DOT))) {
            return true;
        }
        if (map[i][j] != Block.EMPTY)
            return false;
        map[i][j] = Block.PACE;
        boolean isPassable = false;
        List<Direction> dirs = new LinkedList<>(Arrays.asList(Direction.UP,Direction.DOWN,Direction.LEFT,Direction.RIGHT));
        while (!dirs.isEmpty()) {
            Direction dir = randomChooseDirection(dirs);
            dirs.remove(dir);
            switch (dir) {
                // up
                case UP:
                    isPassable = getWay(i - 1,j);
                    break;
                // down
                case DOWN:
                    isPassable = getWay(i + 1,j);
                    break;
                // left
                case LEFT:
                    isPassable = getWay(i, j - 1);
                    break;
                // right
                case RIGHT:
                    isPassable = getWay(i, j + 1);
                    break;
            }
            if (isPassable)
                break;
        }
        return isPassable;
    }

    public static void main(String[] args) throws IOException {
        Maze maze = new Maze(10,10,15);
        maze.findWay();
    }
}
