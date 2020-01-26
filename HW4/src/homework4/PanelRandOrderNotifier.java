package homework4;

import java.util.Arrays;
import java.util.Random;

public class PanelRandOrderNotifier implements PanelOrderNotifier {
    //  Abstraction function:
    //	A PanelRandOrderNotifier dictates the billboard's panels to changes
    //	their colors in random order.
    //
    //	Representation Invariant:
    //  1 <= nextPanel <= 25

    private final int BOARDSIZE = 25;
    private int[] panelOrder;
    private int nextPanel;

    public PanelRandOrderNotifier() {
        panelOrder = new int[BOARDSIZE];
        nextPanel = 1;
    }

    @Override
    public int getNextPanel() {

    }

    private void randOrder() {
        // initializing panel order array
        for(int i = 1; i <= BOARDSIZE; i++) {
            panelOrder[i-1] = i;
        }
        // randomizing array
        Random random = new Random();
        for (int i = BOARDSIZE-1; i >= 0; i--) {
            int index = random.nextInt(i+1);
            int temp = panelOrder[i];
            panelOrder[i] = panelOrder[index];
            panelOrder[index] = temp;
        }
        checkRandOrder();
    }

    private void checkRandOrder() {
        int[] checkArray = new int[BOARDSIZE];
        Arrays.fill(checkArray, 0);
        for (int i = 0; i < BOARDSIZE; i++) {
            checkArray[panelOrder[i]]++;
        }

        for (int i = 0; i < BOARDSIZE; i++) {
            assert checkArray[i] == 1 :
                    "There are repetitions in rand order created";
        }
    }

    /**
     * Verify next Panel to be updated has a valid index.
     *
     * @effects This method will fail if next panel index is out of range.
     */
    private void checkRep() {
        assert (1 <= nextPanel && nextPanel <= BOARDSIZE) :
                "Panel index is out of range!";
    }
}
