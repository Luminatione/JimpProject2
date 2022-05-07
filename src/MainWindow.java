import JimpProject2.GUI.GUIGraphElement;
import JimpProject2.GUI.GraphDrawer;
import JimpProject2.algorithm.Algorithm;
import JimpProject2.algorithm.bfs.BFS;
import JimpProject2.graph.Graph;
import JimpProject2.graph.graphFactory.GraphFactory;
import JimpProject2.graph.graphFactory.GraphFileLoader;
import JimpProject2.graph.graphFactory.RandomGraphGenerator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;

public class MainWindow extends JFrame
{
    private Graph graph;
    private GraphFactory currentGraphFactory;
    private GraphDrawer graphDrawer;

    private JFileChooser fileChooser;

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
    private JLabel BFSOutput;
    private GUIGraphElement graphElement;

    public MainWindow()
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
            prepareFileDialogInstance();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private void bindButtons()
    {
        bindButton(generateButton, this::onGenerateClick);
        bindButton(loadButton, this::onLoadClick);
        bindButton(reloadButton, this::onReloadClick);
        bindButton(saveButton, this::onSaveClick);
        bindButton(BFSButton, this::onBFSClick);
    }

    private void bindButton(JButton button, Runnable func)
    {
        button.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                func.run();
            }
        });
    }

    private void prepareFileDialogInstance()
    {
        fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
    }

    private void displayGraph()
    {
        try
        {

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
        try
        {
            int x = Integer.parseInt(xTextField.getText());
            int y = Integer.parseInt(yTextField.getText());
            int minWeight = Integer.parseInt(minTextField.getText());
            int maxWeight = Integer.parseInt(maxTextField.getText());
            if(x == 0 || y == 0)
            {
                throw new IllegalArgumentException("Graphs with 0 as dimension are not supported");
            }
            currentGraphFactory = new RandomGraphGenerator(x, y, minWeight, maxWeight);
            displayGraph();
        }
        catch (NumberFormatException e)
        {
            consoleOutput.append("Wrong number format!\n");
        }
        catch (IllegalArgumentException e)
        {
            consoleOutput.append(e.getMessage() + '\n');
        }
    }

    private void onLoadClick()
    {
        if (fileChooser.showOpenDialog(contentPane) != JFileChooser.APPROVE_OPTION)
        {
            return;
        }
        currentGraphFactory = new GraphFileLoader(fileChooser.getSelectedFile().getAbsolutePath());
        displayGraph();
    }

    private void onReloadClick()
    {
        displayGraph();
    }

    private void onSaveClick()
    {
        if (fileChooser.showSaveDialog(contentPane) != JFileChooser.APPROVE_OPTION)
        {
            return;
        }
        try (BufferedWriter outputFile = new BufferedWriter(new FileWriter(fileChooser.getSelectedFile())))
        {
            outputFile.write(graph.toString());
        }
        catch (FileNotFoundException e)
        {
            consoleOutput.append("Unable to create file");
        }
        catch (IOException e)
        {
            consoleOutput.append("Unable to save to file");
        }
    }
    private void onBFSClick()
    {
        if(graph == null)
        {
            consoleOutput.append("Graph had to be loaded first");
            return;
        }
        boolean result = new BFS(graph).compute();
        BFSOutput.setText(result ? "Consistent" : "Not Consistent");
        BFSOutput.setForeground(result ? Color.green : Color.red);
    }
    public static void main(String[] args)
    {
        MainWindow dialog = new MainWindow();
    }
}
