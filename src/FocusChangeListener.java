
import db_modul.ParsingSite;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.IOException;
import java.net.ProtocolException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextField;
import products_modul.Product;

/**
 *
 * @author Дима
 */
public class FocusChangeListener implements FocusListener{
    private Product product;   
    
    public FocusChangeListener(Product product){
        this.product = product;
    }

    @Override
    public void focusGained(FocusEvent e) {
        
    }

    @Override
    public void focusLost(FocusEvent e) {
            StringBuilder builder = new StringBuilder(((JTextField)e.getSource()).getName());
            String queryStr = "";
            if(builder.toString().equals(StrConstants.RUBPRICE)){
                builder.delete(0, builder.length());
                builder.append("pid=");
                builder.append(product.getIdProduct());
                builder.append("&oldPrice=");
                builder.append(product.getCost(8));
                builder.append("&newPrice=");
                builder.append(((JTextField)e.getSource()).getText());
                builder.append("&groupChange=0&ajax=1");
            }
            else if(builder.toString().equals(StrConstants.MORECOMMENT)){
                builder.delete(0, builder.length());
                builder.append("action=setNewOMComm");
                builder.append("&pid=");
                builder.append(product.getIdProduct());
                builder.append("&new_omc=");
                builder.append(((JTextField)e.getSource()).getText());
                builder.append("&myax_encoded=1");
                builder.append("&old_omc");
                builder.append(product.getMoreComment());
                builder.append("&show_info=1");
                queryStr = "";//Проверить, нужна ли вообще эта строка
            }
                    
                try {
                    ParsingSite.getAuthSite().sendPost(StrConstants.URLPOST + queryStr, builder.toString());
                    //добавить изменение цены в строке товара программы
                } catch (ProtocolException ex) {
                    Logger.getLogger(GUI_Products.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(GUI_Products.class.getName()).log(Level.SEVERE, null, ex);
                }
    
    }
    
}
