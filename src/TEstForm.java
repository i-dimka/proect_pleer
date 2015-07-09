
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;



public class TEstForm extends JPanel{
   
    public TEstForm(){
        JPanel parentPanel = new JPanel();
        JPanel panel = new JPanel();
        JScrollPane jScrollPane1 = new JScrollPane(panel);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        parentPanel.setLayout(new BoxLayout(parentPanel, BoxLayout.Y_AXIS));
        
        jScrollPane1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        for(int i = 0; i < 100; i++){
            JPanel rowPanel = new JPanel();
            rowPanel.setLayout(new GridLayout(1, 2));

            JLabel label = new JLabel(String.valueOf(i));
            rowPanel.add(label);
            label.setAlignmentX(JLabel.CENTER_ALIGNMENT);
            label.setFont(new Font("Verdana", Font.TRUETYPE_FONT, 24));

            JTextField field = new JTextField("jdhdg");
            rowPanel.add(field);
            field.setAlignmentX(JTextField.CENTER_ALIGNMENT);
            field.setFont(new Font("Verdana", Font.TRUETYPE_FONT, 24));
            
            panel.add(rowPanel);
        
            jScrollPane1.revalidate();
        }
        
        parentPanel.add(jScrollPane1, BorderLayout.CENTER);
        
        
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(parentPanel)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addComponent(parentPanel)
                .addGap(0, 0, 0))
        );
    }
   
}
