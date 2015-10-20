
import java.awt.AWTEvent;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;



/**
 *
 * @author Дима
 */
public class FrameTreeCatalog extends JFrame{
    public FrameTreeCatalog(){
        enableEvents(AWTEvent.WINDOW_EVENT_MASK);
        CatalogPanel catalogPanel = new CatalogPanel();
        this.getContentPane().add(catalogPanel);
    }
    protected void processWindowEvent(WindowEvent e){
        super.processWindowEvent(e);
        if(e.getID() == WindowEvent.WINDOW_CLOSED){
            System.exit(0);
        }
    }
            
}
