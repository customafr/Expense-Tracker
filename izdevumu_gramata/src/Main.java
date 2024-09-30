import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run () {
                TestGUI app = new TestGUI();

                   app.setVisible(true);


            }
    });
    }
}

//        //insert
//        DataBaseFunct expenses = new DataBaseFunct();
//        expenses.insert("12.12.24", 1200.00, "parts for engine n54" );
//
//
////        //delete
////        DataBaseFunct expenses = new DataBaseFunct();
////        expenses.deletebyid(1);



