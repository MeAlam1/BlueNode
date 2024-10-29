package BlueNode.UI.MainPanel;

import BlueNode.BlueNode;
import BlueNode.Logging.BaseLogger;
import BlueNode.Logging.ELogLevel;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class GridDrawer {

    private static final int GRID_SIZE = 10;
    private static final Color GRID_COLOR = Color.rgb(75, 75, 75);

    public void redraw(GraphicsContext pGraphics, double pWidth, double pHeight, double pTranslateX, double pTranslateY) {
        drawGrid(pGraphics, pWidth, pHeight, pTranslateX, pTranslateY);
    }

    public void drawGrid(GraphicsContext pGraphics, double pWidth, double pHeight, double pTranslateX, double pTranslateY) {
        pGraphics.setFill(BlueNode.BACKGROUND_COLOR);
        pGraphics.fillRect(0, 0, pWidth, pHeight);

        pGraphics.setStroke(GRID_COLOR);
        pGraphics.setLineWidth(1);

        double startX = -((pTranslateX) % GRID_SIZE);
        double startY = -((pTranslateY) % GRID_SIZE);

        for (double x = startX -100; x < pWidth; x += GRID_SIZE) {
            for (double y = startY -100; y < pHeight; y += GRID_SIZE) {
                pGraphics.strokeRect(x, y, GRID_SIZE, GRID_SIZE);
            }
        }

        BaseLogger.log(ELogLevel.INFO, "Grid drawn");
    }

    public static int snapToGrid(double pCoord) {
        return (int) (Math.floor(pCoord / GRID_SIZE) * GRID_SIZE);
    }
}
