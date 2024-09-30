import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.sql.*;

public class TestGUI extends JFrame {

    private JTextField tfData;
    private JTextField tfSumma;
    private JButton btnInsert;
    private JButton btnDelete;
    //    private JPanel ExpensesPanel;
    private JLabel tfMoney;
    private JTextField tfApraksts;
    private JTable expensesTable;
    public DefaultTableModel tableModel;


    //Constructor
    public TestGUI() {
        tfMoney = new JLabel("Total Expenses: ");
        tfData = new JTextField(10);
        tfSumma = new JTextField(10);
        tfApraksts = new JTextField(10);
        btnInsert = new JButton("Insert");
        btnDelete = new JButton("Delete");

//Panel
        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));


//Add component to panel
        panel.add(new JLabel("Data: "));
        panel.add(tfData);
        panel.add(new JLabel("Summa: "));
        panel.add(tfSumma);
        panel.add(new JLabel("Apraksts:"));
        panel.add(tfApraksts);
        panel.add(btnInsert);
        panel.add(btnDelete);
        panel.add(tfMoney);

//Panel parameters
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        contentPanel.add(panel, BorderLayout.NORTH);


//Insert table model
        tableModel = new DefaultTableModel();
        expensesTable = new JTable(tableModel);

//Insert column for tablbe
        tableModel.addColumn("ID");
        tableModel.addColumn("Data");
        tableModel.addColumn("Summa");
        tableModel.addColumn("Apraksts");

        // Create RowSorter for row sort by summ or data
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(tableModel);
        expensesTable.setRowSorter(sorter);

//load data from DB
DataBaseFunct.loadExpensesFromDatabase(tableModel, tfMoney);
//Scroll panel
        JScrollPane tableScrollPane = new JScrollPane(expensesTable);
        contentPanel.add(tableScrollPane, BorderLayout.CENTER);
//Open panel parameters
        setTitle("Expenses Tracker");
        setSize(450, 300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setContentPane(contentPanel);
        setLocationRelativeTo(null); //when open program it start on center


//Insert button
        btnInsert.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String datums = tfData.getText();
                double summa;
                try {
                    summa = Double.parseDouble(tfSumma.getText());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid number for summa");
                    return;
                }
                String apraksts = tfApraksts.getText();
                // Send data to DB
                DataBaseFunct.insert(datums, summa, apraksts);

                //clear text field after insert
                tfData.setText("");
                tfSumma.setText("");
                tfApraksts.setText("");

                // Load updated data into the table
                DataBaseFunct.loadExpensesFromDatabase(tableModel, tfMoney);
            }
        });

//Delete Button
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int[] selectedRows = expensesTable.getSelectedRows(); // Получаем выделенные строки
                if (selectedRows.length > 0) { // Проверяем, есть ли выделенные строки
                    int confirm = JOptionPane.showConfirmDialog(null,
                            "Are you sure you want to delete the selected expenses?",
                            "Confirm Delete",
                            JOptionPane.YES_NO_OPTION);

                    if (confirm == JOptionPane.YES_OPTION) {
                        for (int i = selectedRows.length - 1; i >= 0; i--) { // Итерируем в обратном порядке
                            int id = (int) tableModel.getValueAt(selectedRows[i], 0); // Извлекаем ID
                            // Удалить запись из БД
                            DataBaseFunct.deleteById(id);
                        }
                        // Обновить таблицу после удаления
                        DataBaseFunct.loadExpensesFromDatabase(tableModel, tfMoney);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please select at least one expense to delete.");
                }
            }
        });






    }
}



