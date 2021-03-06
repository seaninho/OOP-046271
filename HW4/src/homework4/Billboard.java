package homework4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

/**
 * Billboard represents an billboard of size (WINDOW_HEIGHT x WINDOW_WIDTH).
 * Billboard class is a JFrame and it contain a grid of
 * (NUM_OF_HEIGHT_PANELS x NUM_OF_WIDTH_PANELS) panels instances.
 * The panels color is set by colorGenerator
 */
public class Billboard extends JFrame {
    //	 Abstraction function:
    //	 A billboard is represented a set of BOARDSIZE panels, arranged in a
    //	 (NUM_OF_HEIGHT_PANELS X NUM_OF_WIDTH_PANELS) grid.
    //
    //	 Representation Invariant:
    //	 there are BOARDSIZE panels.


    private static final int BOUND_OF_UPDATE_ORDERS = 4;
    private static final int WINDOW_HEIGHT = 600;
    private static final int WINDOW_WIDTH = 600;
    private static final int NUM_OF_HEIGHT_PANELS = 5;
    private static final int NUM_OF_WIDTH_PANELS = 5;
    private final int BOARDSIZE = NUM_OF_HEIGHT_PANELS*NUM_OF_WIDTH_PANELS;
    private final int FREQUENCY = 2000; // 40 milliseconds delay
    private int time = 0;

    JPanel billboard = new JPanel();
    Panel panels[] = new Panel[BOARDSIZE];
    ColorGenerator colorGenerator = ColorGenerator.getInstance();


    /**
     * Billboard constructor.
     *
     * @modifies this
     * @effects Instantiates a new billboard.
     *
     */
    public Billboard() {
        super("Billboard");
        setSize(WINDOW_WIDTH,WINDOW_HEIGHT);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        billboard.setLayout(new
                GridLayout(NUM_OF_WIDTH_PANELS, NUM_OF_HEIGHT_PANELS));

        for(int i = 0; i < BOARDSIZE; i++) {
            panels[i] = new Panel();
            colorGenerator.addObserver(panels[i]);
            billboard.add(panels[i]);
        }
        add(billboard);
        setVisible(true);

        Timer timer = new Timer(colorGenerator.DELAY, new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (time % FREQUENCY == 0) {
                    Random newOrder = new Random();
                    switch (newOrder.nextInt(BOUND_OF_UPDATE_ORDERS)) {
                        case 0:
                            colorGenerator.setPanelOrderNotifier(new PanelAscOrderNotifier());
                            break;
                        case 1:
                            colorGenerator.setPanelOrderNotifier(new PanelColsOrderNotifier());
                            break;
                        case 2:
                            colorGenerator.setPanelOrderNotifier(new PanelOddEvenOrderNotifier());
                            break;
                        case 3:
                            colorGenerator.setPanelOrderNotifier(new PanelRandOrderNotifier());
                            break;
                        default:
                            break;
                    }
                    colorGenerator.setColor();
                }
                time += colorGenerator.DELAY;
                repaint();
            }
        });
        timer.start();
    }

    /**
     * @effects: main method that instantiates a Billboard
     */
    public static void main(String args[]) {
        new Billboard();
    }
}
