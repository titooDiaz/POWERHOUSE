package shop.swing;
import java.awt.*;
import javax.swing.*;
import shop.CSVwriter.*;
import shop.models.PaidMethod;

public class AddPaidMethod extends JDialog {
    private JTextField nameField;
    private JTextField codeField;   
    private JButton saveButton;
    private JButton cancelButton;

    public AddPaidMethod(Frame parent) {
        super(parent, "Agregar Método de Pago", true);
        setLayout(new GridLayout(4, 1, 10, 10));

        add(new JLabel("Nombre del método de pago:"));
        nameField = new JTextField();
        add(nameField);

        saveButton = new JButton("Guardar");
        cancelButton = new JButton("Cancelar");

        add(saveButton);
        add(cancelButton);

        saveButton.addActionListener(e -> {
            String name = nameField.getText();
            JOptionPane.showMessageDialog(this, "Método de pago guardado:\n" + name);
            PaidMethod newPago = new PaidMethod(name);
            WriterPaidMethod.appendPaidCSV(newPago);
            dispose(); // Cierra la ventana
        });

        cancelButton.addActionListener(e -> dispose());

        setSize(200, 150);
        setLocationRelativeTo(parent); // Centra respecto a la ventana padre
    }
}
