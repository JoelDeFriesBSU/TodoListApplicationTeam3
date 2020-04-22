package ui;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.util.Rotation;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import utils.HTTPUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class SwingMain extends JFrame implements ActionListener {

    JLabel displayLabel;
    JLabel currentOwnerLabel;
    JTextField owner;

    JTextField addTodoField;
    JTextField addDueDateField;

    JTextField completeTodoField;

    JTextField deleteTodoField;

    JTextArea status;

    HTTPUtils httpUtils;

    public SwingMain() {
        super("Todo Manager");
        JPanel panel = new JPanel(new GridLayout(8,8));
        GridBagLayout gridBag = new GridBagLayout();
        panel.setLayout(gridBag);
        setContentPane(panel);


        displayLabel = new JLabel("TODO Manager");
        displayLabel.setHorizontalAlignment(SwingConstants.CENTER);
        var displayLabelConstraints = new GridBagConstraints(0, 0, 8, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(20, 20, 20, 20), 0, 0);
        panel.add(displayLabel, displayLabelConstraints);

        currentOwnerLabel = new JLabel("Please Set the Owner");
        var currentOwnerJLabelConstraints = new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(20, 20, 20, 20), 0, 0);
        panel.add(currentOwnerLabel, currentOwnerJLabelConstraints);

        owner = new JTextField("team3");
        var ownerTextFieldConstraints = new GridBagConstraints(1, 1, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(20, 20, 20, 20), 0, 0);
        panel.add(owner, ownerTextFieldConstraints);

        //add
        JLabel addLabel = new JLabel("Add Todo Item");
        var addLabelConstraints = new GridBagConstraints(0, 2, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(20, 20, 20, 20), 0, 0);
        panel.add(addLabel, addLabelConstraints);

        String addTodoFieldDefaultText = "Todo item.";
        addTodoField = new JTextField(addTodoFieldDefaultText);
        var todoFieldConstraints = new GridBagConstraints(0, 3, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(20, 20, 20, 20), 0, 0);
        panel.add(addTodoField,todoFieldConstraints);

        String addDueDateFieldDefaultText = "4/20";
        addDueDateField = new JTextField(addDueDateFieldDefaultText);
        var dueDateFieldConstraints = new GridBagConstraints(0, 4, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(20, 20, 20, 20), 0, 0);
        panel.add(addDueDateField,dueDateFieldConstraints);

        //complete
        JLabel completedLabel = new JLabel("Enter Title of Todo to Complete: ");
        var completedLabelConstraints = new GridBagConstraints(1, 2, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(20, 20, 20, 20), 0, 0);
        panel.add(completedLabel, completedLabelConstraints);

        String completeTodoFieldDefaultText = "Enter Completed Todo Item: ";
        completeTodoField = new JTextField(completeTodoFieldDefaultText);
        var completeTodoFieldConstraints = new GridBagConstraints(1, 3, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(20, 20, 20, 20), 0, 0);
        panel.add(completeTodoField, completeTodoFieldConstraints);

        //delete
        JLabel deleteLabel = new JLabel("Delete Todo Item");
        var deleteLabelConstraints = new GridBagConstraints(2, 2, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(20, 20, 20, 20), 0, 0);
        panel.add(deleteLabel, deleteLabelConstraints);

        String deleteTodoFieldDefaultText = "Enter Title of Todo to Delete: ";
        deleteTodoField = new JTextField(deleteTodoFieldDefaultText);
        var deleteTodoFieldConstraints = new GridBagConstraints(2, 3, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(20, 20, 20, 20), 0, 0);
        panel.add(deleteTodoField, deleteTodoFieldConstraints);


        JButton ownerButton = new JButton("Set Owner");
        var ownerButtonConstraints = new GridBagConstraints(2, 1, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(20, 20, 20, 20), 0, 0);
        ownerButton.addActionListener(e -> {
            String setOwner = owner.getText();
            httpUtils = new HTTPUtils(setOwner);
            currentOwnerLabel.setText("Current Owner: " + setOwner);
            status.setText("'" + setOwner + "'" + " has been set as the current owner.");
        });
        panel.add(ownerButton, ownerButtonConstraints);

        JButton TodoItemTitleButton = new JButton("Create New Todo Item");
        TodoItemTitleButton.setPreferredSize(new Dimension(30,50));
        var TodoItemTitleButtonConstraints = new GridBagConstraints(0, 5, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(1, 1, 1, 1), 0, 0);
        TodoItemTitleButton.addActionListener(e ->{
            String todoTextAdd = addTodoField.getText();
            String dueDateText = addDueDateField.getText();
            try {
                String s = httpUtils.addTodoItem(todoTextAdd, dueDateText);
                status.setText("New todo item made and stored to cloud!\n"+s);
                addTodoField.setText(addTodoFieldDefaultText);
                addDueDateField.setText(addDueDateFieldDefaultText);
            } catch (IOException ex) {
                ex.printStackTrace();
                status.setText("Todo item was not made due to error.");
            }
        });
        panel.add(TodoItemTitleButton, TodoItemTitleButtonConstraints);

        JButton completeTodoItemButton = new JButton("Complete Todo Item");
        completeTodoItemButton.setPreferredSize(new Dimension(30,50));
        var completeTodoItemTitleButtonConstraints = new GridBagConstraints(1, 5, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(1, 1, 1, 1), 0, 0);
        completeTodoItemButton.addActionListener(e -> {
            //TODO Needs Functionality
            completeTodoField.setText(completeTodoFieldDefaultText);
        });
        panel.add(completeTodoItemButton, completeTodoItemTitleButtonConstraints);

        JButton deleteTodoItemButton = new JButton("Delete Todo Item");
        deleteTodoItemButton.setPreferredSize(new Dimension(30,50));
        var deleteTodoItemTitleButtonConstraints = new GridBagConstraints(2, 5, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(1, 1, 1, 1), 0, 0);
        deleteTodoItemButton.addActionListener(e -> {
            String todoTextDelete = deleteTodoField.getText();
            try {
                if(httpUtils.deleteTodoItemByTitle(todoTextDelete)){
                    status.setText(todoTextDelete + " was successfully deleted from the cloud.");
                    deleteTodoField.setText(deleteTodoFieldDefaultText);
                } else{
                    status.setText(todoTextDelete + " was not deleted from the cloud.");
                }
            } catch (IOException ex) {
                ex.printStackTrace();
                status.setText("Todo Item was not delete due to an IOException.");
            }
        });
        panel.add(deleteTodoItemButton, deleteTodoItemTitleButtonConstraints);

        JButton syncToCloudButton = new JButton("Sync Todo Items to Local Storage.");
        var SyncItemButtonConstraints = new GridBagConstraints(4, 1, 2, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(1, 1, 1, 1), 0, 0);
        syncToCloudButton.addActionListener(e -> {
            try {
                if (httpUtils.setLocalSaveArrayList(httpUtils.getAllTodoItems())) {
                    status.setText("Todo Items all saved successfully!");
                }
            }catch (IOException ex) {
                status.setText("An IOException has occurred.\nSaving to Local not successful or nothing to save.");
            } catch (NullPointerException ex){
                status.setText("Saving to Local not successful or there was nothing to save.");
            }
        });
        panel.add(syncToCloudButton, SyncItemButtonConstraints);


        status = new JTextArea("------Status: Working------");
        status.setEditable(false);
        status.setLineWrap(true);
        status.setWrapStyleWord(true);
        var statusAreaConstraints = new GridBagConstraints(6, 1, 1, 4, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(20, 20, 20, 20), 0, 0);
        panel.add(status, statusAreaConstraints);

        PieDataset dataset = createDataset();
        JFreeChart chart = createChart(dataset,"Todo Pie Chart");
        ChartPanel chartPanel = new ChartPanel(chart);
        var chartPanelConstraints = new GridBagConstraints(4, 2, 2, 2, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(1, 1, 1, 1), 0, 0);
        panel.add(chartPanel,chartPanelConstraints);

        setPreferredSize(new Dimension(1080, 720));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    private PieDataset createDataset() {
        DefaultPieDataset result = new DefaultPieDataset();
        result.setValue("TBD", 33);
        result.setValue("TBD2", 33);
        result.setValue("TBD3", 34);
        return result;

    }

    private JFreeChart createChart(PieDataset dataset, String title) {

        JFreeChart chart = ChartFactory.createPieChart3D(
                title,
                dataset,
                true,
                true,
                false
        );

        PiePlot3D plot = (PiePlot3D) chart.getPlot();
        plot.setDirection(Rotation.CLOCKWISE);
        return chart;

    }



    public static void main(String[] args) {
        new SwingMain();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }
}