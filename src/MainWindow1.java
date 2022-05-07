import JimpProject2.GUI.GUIGraphElement;
import JimpProject2.GUI.GraphDrawer;
import JimpProject2.graph.Graph;
import JimpProject2.graph.graphFactory.GraphFactory;
import JimpProject2.graph.graphFactory.GraphFileLoader;
import JimpProject2.graph.graphFactory.RandomGraphGenerator;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.util.function.Consumer;
import java.util.function.Function;

public class MainWindow1 extends JFrame
{

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

    JFileChooser fileChooser;


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

            currentGraphFactory = new RandomGraphGenerator(x, y, minWeight, maxWeight);
            displayGraph();
        }
        catch (NumberFormatException e)
        {
            consoleOutput.append("Wrong number format!\n");
        }
    }

    private void onLoadClick()
    {
        if (fileChooser.showOpenDialog(contentPane) == JFileChooser.APPROVE_OPTION)
        {
            currentGraphFactory = new GraphFileLoader(fileChooser.getSelectedFile().getAbsolutePath());
            displayGraph();
        }
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

    public static void main(String[] args)
    {
        MainWindow1 dialog = new MainWindow1();

    }
}
