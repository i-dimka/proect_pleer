
import java.awt.Dimension;
import java.awt.event.ComponentListener;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import products_modul.CatalogNode;

/**
 *
 * @author Дима
 */
public class CatalogPanel extends JPanel{
    
    private JPanel jBackPanel;
    JTree jTreeCatalog;
    private JFormattedTextField jFindField;
    private int idRootCatalog;
    
    public CatalogPanel(){
        idRootCatalog = 1;
        initBackPanel();
        initComponentsTreeCatalog();
        packPanels();
    }
    
    private void initComponentsTreeCatalog(){
        DefaultMutableTreeNode treeNode;
        CatalogNode catalogNode = CatalogNode.getRootNode(idRootCatalog);
        treeNode = new DefaultMutableTreeNode(catalogNode, true);
        jTreeCatalog = new JTree(treeNode);
        addChildrensNode(treeNode, catalogNode);
        jTreeCatalog.setMaximumSize(new Dimension(1000, 32));
        jTreeCatalog.setPreferredSize(new Dimension(250, 32));
        jTreeCatalog.setToggleClickCount(1);
        jTreeCatalog.setVisibleRowCount(100);
        
        jFindField = new JFormattedTextField();
        jFindField.setText(" Поиск каталога ...");
        
        jTreeCatalog.getAccessibleContext().setAccessibleDescription("");
        jTreeCatalog.getAccessibleContext().setAccessibleParent(jTreeCatalog); 
    }
    
    
    private void addChildrensNode(DefaultMutableTreeNode treeNode, CatalogNode catalogNode){
        for(CatalogNode child : catalogNode.getChildrensList()){
            if(child.isExistsChildrens()){
                treeNode.add(new DefaultMutableTreeNode(child));
            }
            else {
                DefaultMutableTreeNode newTreeNode = new DefaultMutableTreeNode(child, true);
                treeNode.add(newTreeNode);
                addChildrensNode(newTreeNode, child);                
            }
        }
    }
    
    private void initBackPanel(){
        jBackPanel = new JPanel();
        javax.swing.GroupLayout jBackPanelLayout = new javax.swing.GroupLayout(jBackPanel);
        jBackPanel.setLayout(jBackPanelLayout);
        jBackPanelLayout.setHorizontalGroup(
            jBackPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jBackPanelLayout.setVerticalGroup(
            jBackPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        
        setMaximumSize(new Dimension(200, 3000));
        setPreferredSize(new Dimension(800, 600));
    }
    
    private void packPanels(){
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jFindField)
                    .addComponent(jTreeCatalog, javax.swing.GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE))                
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jFindField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jTreeCatalog, javax.swing.GroupLayout.PREFERRED_SIZE, 783, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }
    
    public JTree getTreeCatalog(){
        return jTreeCatalog;
    }
}
