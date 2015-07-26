import javax.swing.*;

/**
 * Created by Erwan on 20/07/2015.
 */
public class Main {

    private static int witdh = 1024;
    private static int height = 800;

    public static void main(String[] args)
    {
        Player player = new Player();

        JFrame frame = new JFrame();
        MainPanel panel = new MainPanel(player, witdh, height);

        frame.setContentPane(panel);
        frame.setTitle("3D Engine");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(witdh, height);
        frame.setVisible(true);

        Engine engine = new Engine(player, panel, args[0], witdh, height);
        engine.startSimulation();
    }
}
