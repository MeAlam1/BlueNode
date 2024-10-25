package BlueNode.UI.MainPanel;

import BlueNode.BlueNode;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class GridDrawer {

    private static final int GRID_SIZE = 10;
    private static final Color GRID_COLOR = Color.rgb(75, 75, 75);

    public void drawGrid(GraphicsContext pGraphics, double pWidth, double pHeight) {
        pGraphics.setFill(BlueNode.BACKGROUND_COLOR);
        pGraphics.fillRect(0, 0, pWidth, pHeight);

        pGraphics.setStroke(GRID_COLOR);

        final int offsetX = 2;
        final int offsetY = 11;

        for (int x = -100; x < pWidth + (pWidth / 2); x += GRID_SIZE) {
            for (int y = -100; y < pHeight + (pHeight / 2); y += GRID_SIZE) {
                pGraphics.strokeRect(x + offsetX, y + offsetY, GRID_SIZE, GRID_SIZE);
            }
        }
    }


    public static int snapToGrid(double pCoord) {
        return (int) (Math.floor(pCoord / GRID_SIZE) * GRID_SIZE);
    }
}
