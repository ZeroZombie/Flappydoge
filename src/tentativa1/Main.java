package tentativa1;

import java.awt.EventQueue;

public class Main {
public static void main(String[] args) {
        
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                FlappyDoge ex = new FlappyDoge();
                ex.setVisible(true);
            }
        });
    }
}
