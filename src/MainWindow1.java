import JimpProject2.GUI.GUIGraphElement;
import JimpProject2.GUI.GraphDrawer;
import JimpProject2.graph.Graph;
import JimpProject2.graph.graphFactory.GraphFactory;
import JimpProject2.graph.graphFactory.GraphFileLoader;
import JimpProject2.graph.graphFactory.RandomGraphGenerator;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

public class MainWindow1 extends JFrame {

    Graph graph;
    GraphFactory currentGraphFactory;
    GraphDrawer graphDrawer;

    private JPanel contentPane;
    private JTextField xTextField;
    private JTextField yTextField;
    private JButton generateButton;
    private JButton loadButton;
    private JButton BFSButton;
    private JButton beginButton;
    private JButton reloadButton;
    private JButton saveButton;
    private JTextArea consoleOutput;
    private JPanel graphPanel;
    private JTextField minTextField;
    private JTextField maxTextField;
    private GUIGraphElement graphElement;

    public MainWindow1()
    {
        try
        {
            graphDrawer = new GraphDrawer(null, graphPanel);
            graphDrawer.bindElementsAutoResizing(this);
            setContentPane(contentPane);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            pack();
            setVisible(true);
            bindButtons();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    private void bindButtons()
    {
        generateButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                onGenerateClick();
            }
        });
        loadButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                onLoadClick();
            }
        });
    }
    private void displayGraph()
    {
        try {
            graph = currentGraphFactory.create();
            graphDrawer.setGraph(graph);
        }
        catch (Exception e)
        {
            consoleOutput.append("Exception occurred during graph creation: " + e.getMessage() + "\n");
        }
        contentPane.repaint();
        contentPane.revalidate();
        graphDrawer.onWindowResize();
    }
    private void onGenerateClick()
    {
        int x, y, minWeight, maxWeight;
        try
        {
            x = Integer.parseInt(xTextField.getText());
            y = Integer.parseInt(yTextField.getText());
            minWeight = Integer.parseInt(minTextField.getText());
            maxWeight = Integer.parseInt(maxTextField.getText());

            currentGraphFactory = new RandomGraphGenerator(x, y, minWeight, maxWeight);
            displayGraph();
        }
        catch(NumberFormatException e)
        {
            consoleOutput.append("Wrong number format!\n");
        }
    }

    private void onLoadClick()
    {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
        if(fileChooser.showOpenDialog(contentPane) == JFileChooser.APPROVE_OPTION)
        {
            currentGraphFactory = new GraphFileLoader(fileChooser.getSelectedFile().getAbsolutePath());
            displayGraph();
        }
    }


    public static void main(String[] args) {
        MainWindow1 dialog = new MainWindow1();

    }
}
