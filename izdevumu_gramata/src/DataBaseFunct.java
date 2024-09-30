import javax.swing.*;
import java.sql.*;
import javax.swing.table.DefaultTableModel;

public class DataBaseFunct {

    // Метод для вставки данных в базу данных
    public static void insert(String datums, double summa, String apraksts) {
        String insertQuery = "INSERT INTO izdevumi (datums, summa, apraksts) VALUES (?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(
                DataBaseManager.getDbUrl(),  // Получение URL базы данных
                DataBaseManager.getDbUser(), // Получение имени пользователя
                DataBaseManager.getDbPassword()); // Получение пароля
             PreparedStatement pstmt = conn.prepareStatement(insertQuery)) {

            pstmt.setString(1, datums);
            pstmt.setDouble(2, summa);
            pstmt.setString(3, apraksts);
            pstmt.executeUpdate();

            System.out.println("Expenses inserted...");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Метод для удаления записи по ID
    public static void deleteById(int id) {
        String deleteQuery = "DELETE FROM izdevumi WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(
                DataBaseManager.getDbUrl(),
                DataBaseManager.getDbUser(),
                DataBaseManager.getDbPassword());
             PreparedStatement pstmt = conn.prepareStatement(deleteQuery)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("Expense deleted...");

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error deleting expense!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Метод для загрузки данных из базы данных в таблицу
    public static void loadExpensesFromDatabase(DefaultTableModel tableModel, JLabel totalLabel) {
        String query = "SELECT * FROM izdevumi";


        try (Connection conn = DriverManager.getConnection(
                DataBaseManager.getDbUrl(),  // Получение URL базы данных
                DataBaseManager.getDbUser(), // Получение имени пользователя
                DataBaseManager.getDbPassword()); // Получение пароля
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            // Очистка существующих строк в модели таблицы
            tableModel.setRowCount(0);

            double totalSum = 0.0; // Переменная для хранения общей суммы

            // Добавление строк из ResultSet в модель таблицы
            while (rs.next()) {
                Object[] row = {
                        rs.getInt("id"),
                        rs.getString("datums"),
                        rs.getDouble("summa"),
                        rs.getString("apraksts")
                };
                tableModel.addRow(row);

                // Суммируем значения в поле summa
                totalSum += rs.getDouble("summa");
            }
            // Обновляем JLabel с общей суммой
            totalLabel.setText("Total Expenses: " + totalSum);

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error loading data from the database!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
