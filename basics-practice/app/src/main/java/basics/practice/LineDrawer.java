package basics.practice;

public class LineDrawer {
    enum Direction {
        HORIZONTAL,
        VERTICAL
    }
    
    public static void main(String[] args) {
        System.out.println("=== Testing Line Drawer ===");
        
        drawLine(8, Direction.HORIZONTAL, '─');
        System.out.println(); 
        
        drawLine(4, Direction.VERTICAL, '│');
        System.out.println();
        
        System.out.println("Simple frame:");
        drawLine(20, Direction.HORIZONTAL, '-');
        drawLine(5, Direction.VERTICAL, '|');
        drawLine(20, Direction.HORIZONTAL, '-');
    }
    
    public static void drawLine(int length, Direction direction, char symbol) {
        if (length <= 0) {
            throw new IllegalArgumentException("Length must be positive!");
        }
        
        switch (direction) {
            case HORIZONTAL:
                drawHorizontal(length, symbol);
                break;
            case VERTICAL:
                drawVertical(length, symbol);
                break;
        }
    }
    
    private static void drawHorizontal(int length, char symbol) {
        for (int i = 0; i < length; i++) {
            System.out.print(symbol);
        }
        System.out.println();
    }
    
    private static void drawVertical(int length, char symbol) {
        for (int i = 0; i < length; i++) {
            System.out.println(symbol);
        }
    }
}