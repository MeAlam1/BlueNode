package BlueNode.UI.MainPanel;

import BlueNode.BlueNode;
import BlueNode.Logging.BaseLogger;
import BlueNode.Logging.ELogLevel;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class GridDrawer {

    private static final int GRID_SIZE = 10;
    private static final Color GRID_COLOR = Color.rgb(75, 75, 75);

    public void drawGrid(GraphicsContext pGraphics, double pWidth, double pHeight, double pZoomLevel, double pTranslateX, double pTranslateY) {
        pGraphics.setFill(BlueNode.BACKGROUND_COLOR);
        pGraphics.fillRect(0, 0, pWidth, pHeight);

        pGraphics.setStroke(GRID_COLOR);
        pGraphics.setLineWidth(1 / pZoomLevel);

        // Calculate the adjusted start positions to ensure grid aligns with zoom and pan
        double startX = -((pTranslateX / pZoomLevel) % GRID_SIZE) * pZoomLevel;
        double startY = -((pTranslateY / pZoomLevel) % GRID_SIZE) * pZoomLevel;

        // Draw grid squares adjusted to zoom and pan
        for (double x = startX; x < pWidth; x += GRID_SIZE * pZoomLevel) {
            for (double y = startY; y < pHeight; y += GRID_SIZE * pZoomLevel) {
                pGraphics.strokeRect(x, y, GRID_SIZE * pZoomLevel, GRID_SIZE * pZoomLevel);
            }
        }
        BaseLogger.log(ELogLevel.INFO, "Grid drawn");
    }

    public static int snapToGrid(double pCoord) {
        return (int) (Math.floor(pCoord / GRID_SIZE) * GRID_SIZE);
    }
}
