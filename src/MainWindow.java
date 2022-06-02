import JimpProject2.GUI.DijkstraController;
import JimpProject2.GUI.GraphGUIWrapper;
import JimpProject2.GUI.GraphDrawer;
import JimpProject2.GUI.Threading.AlgorithmWorker;
import JimpProject2.GUI.Threading.GraphFactoryWorker;
import JimpProject2.algorithm.bfs.BFS;
import JimpProject2.graph.Graph;
import JimpProject2.graph.graphFactory.GraphFactory;
import JimpProject2.graph.graphFactory.GraphFileLoader;
import JimpProject2.graph.graphFactory.RandomGraphGenerator;
import JimpProject2.utility.Logger;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;

public class MainWindow extends JFrame
{
    private Graph graph;
    private GraphFactory currentGraphFactory;
    private GraphDrawer graphDrawer;

    private DijkstraController dijkstraController;

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
    private JTextField nodeSizeJText;
    private GraphGUIWrapper graphElement;

    public MainWindow()
    {
        try
        {
            graphDrawer = new GraphDrawer(null, graphPanel);
            setContentPane(contentPane);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            pack();
            setVisible(true);
            bindButtons();
            prepareFileDialogInstance();
            bindNodeSizeChaneListener();
            Logger.setOutput(consoleOutput::append);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    private void bindNodeSizeChaneListener()
    {
        nodeSizeJText.getDocument().addDocumentListener(new DocumentListener()
        {
            @Override
            public void insertUpdate(DocumentEvent e)
            {
                nodeSizeJTextValueChanged();
            }

            @Override
            public void removeUpdate(DocumentEvent e)
            {
                nodeSizeJTextValueChanged();
            }

            @Override
            public void changedUpdate(DocumentEvent e)
            {
                nodeSizeJTextValueChanged();
            }
        });
    }

    private void nodeSizeJTextValueChanged()
    {
        try
        {
            graphDrawer.setGraphGUINodeSize(Integer.parseInt(nodeSizeJText.getText()));
            repaintGraph();
        }
        catch (NumberFormatException e)
        {
            SwingUtilities.invokeLater(() ->
            {
                try
                {
                    Integer.parseInt(nodeSizeJText.getText());
                }
                catch (NumberFormatException e1)
                {
                    nodeSizeJText.setText("1");
                }
                repaintGraph();
            });
        }
    }

    private void bindButtons()
    {
        bindButton(generateButton, this::onGenerateClick);
        bindButton(loadButton, this::onLoadClick);
        bindButton(reloadButton, this::onReloadClick);
        bindButton(saveButton, this::onSaveClick);
        bindButton(BFSButton, this::onBFSClick);
        bindButton(beginButton, this::onBeginClick);
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

    private void repaintGraph()
    {
        contentPane.repaint();
        contentPane.revalidate();
    }
    private void onGraphReady(Graph graph)
    {
        try
        {
            this.graph = graph;
            graphDrawer.setGraph(graph);
            nodeSizeJTextValueChanged();
            dijkstraController = new DijkstraController(graph, graphDrawer.getGraphGUI(), this::repaintGraph);

        }
        catch (Exception e)
        {
            Logger.log("Exception occurred during graph creation: " + e.getMessage() + "\n");
        }
        repaintGraph();
    }
    private void displayGraph()
    {
        new GraphFactoryWorker(currentGraphFactory, this::onGraphReady).execute();
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
                throw new IllegalArgumentException("Graphs with 0 as dimension are not supported\n");
            }
            currentGraphFactory = new RandomGraphGenerator(x, y, minWeight, maxWeight);
            displayGraph();
        }
        catch (NumberFormatException e)
        {
            Logger.log("Wrong number format!\n");
        }
        catch (IllegalArgumentException e)
        {
            Logger.log(e.getMessage() + '\n');
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
        dijkstraController.start();
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
            Logger.log("Unable to create file\n");
        }
        catch (IOException e)
        {
            Logger.log("Unable to save to file\n");
        }
    }
    private void onBFSComplete(Boolean result)
    {
        BFSOutput.setText(result ? "Consistent" : "Not Consistent");
        BFSOutput.setForeground(result ? Color.green : Color.red);
    }
    private void onBFSClick()
    {
        if(graph == null)
        {

            return;
        }
        new AlgorithmWorker<>(new BFS(graph), this::onBFSComplete).execute();
    }

    private void onBeginClick()
    {
        if(graph == null)
        {
            Logger.log("Graph had to be loaded first\n");
            return;
        }
        dijkstraController.start();
    }

    public static void main(String[] args)
    {
        MainWindow dialog = new MainWindow();
    }
}

