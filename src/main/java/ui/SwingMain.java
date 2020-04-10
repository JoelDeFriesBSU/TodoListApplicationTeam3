package ui;

import utils.HTTPUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class SwingMain extends JFrame implements ActionListener {

    JLabel displayLabel;
    JTextArea status;
    JTextField owner;
    JTextField dueDate;
    JTextField todo;

    public SwingMain() {
        super("Todo Manager");
        JPanel panel = new JPanel(new GridLayout(8,8));
        GridBagLayout gridBag = new GridBagLayout();
        panel.setLayout(gridBag);
        setContentPane(panel);


        displayLabel = new JLabel("TODO MANAGER (Rough Draft UI)");
        var displayLabelConstraints = new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(20, 20, 20, 20), 0, 0);
        panel.add(displayLabel, displayLabelConstraints);

        owner = new JTextField("Enter todo owner:");
        var constraints1 = new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(20, 20, 20, 20), 0, 0);
        panel.add(owner,constraints1);

        dueDate = new JTextField("Enter due date:");
        var constraints2 = new GridBagConstraints(0, 2, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(20, 20, 20, 20), 0, 0);
        panel.add(dueDate,constraints2);

        todo = new JTextField("Enter thing to-do:");
        var constraints3 = new GridBagConstraints(0, 3, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(20, 20, 20, 20), 0, 0);
        panel.add(todo,constraints3);

        JButton button = new JButton("Create New Todo Item");
        button.setPreferredSize(new Dimension(30,50));
        var buttonconstraints = new GridBagConstraints(0, 4, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(1, 1, 1, 1), 0, 0);
        button.addActionListener(this);
        panel.add(button, buttonconstraints);

        status = new JTextArea("Status: Working");
        status.setPreferredSize(new Dimension(350,400));
        status.setEditable(false);
        var statusConstraints = new GridBagConstraints(1, 1, 1, 3, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(20, 20, 20, 20), 0, 0);
        panel.add(status, statusConstraints);


        setPreferredSize(new Dimension(600, 400));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    public static void main(String[] args) {
        new SwingMain();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        HTTPUtils httpUtils = new HTTPUtils();
        var todoText = todo.getText();
        var ownerText = owner.getText();
        var dueDateText = dueDate.getText();

        try {
            String s = httpUtils.addTodoItem(todoText,ownerText,dueDateText);
            status.setText("New todo item made and stored to cloud!\n"+s);
        } catch (IOException ex) {
            ex.printStackTrace();
            status.setText("Todo item was not made due to error.");
        }

    }

}