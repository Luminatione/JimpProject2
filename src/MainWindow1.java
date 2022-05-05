import JimpProject2.GUI.GUIGraphElement;
import JimpProject2.GUI.GraphDrawer;
import JimpProject2.graph.Graph;
import JimpProject2.graph.graphFactory.RandomGraphGenerator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class MainWindow1 extends JFrame {

    private JPanel contentPane;
    private JTextField xTextField;
    private JTextField yTextField;
    private JButton generateButton;
    private JButton loadButton;
    private JButton BFSButton;
    private JButton beginButton;
    private JButton reloadButton;
    private JButton saveButton;
    private JTextArea ConsoleOutput;
    private JPanel graphPanel;
    private GUIGraphElement graphElement;

    public MainWindow1()
    {
        try
        {
            GraphDrawer graphDrawer = new GraphDrawer(new RandomGraphGenerator(10, 10, 0.1f, 2f).create(), graphPanel);
            graphDrawer.bindElementsAutoResizing(this);
            setContentPane(contentPane);
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            pack();
            setVisible(true);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        MainWindow1 dialog = new MainWindow1();

    }
}
