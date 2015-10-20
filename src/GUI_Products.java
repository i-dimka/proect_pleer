
import db_modul.DbWorker;
import db_modul.ParsingSite;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.IOException;
import java.net.ProtocolException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.JTree;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import products_modul.CatalogNode;
import products_modul.Product;


public class GUI_Products extends javax.swing.JPanel {

    /**
     * Панель модуля Товары
     */
    
    private ArrayList<Product> productArrayList;
    private static DbWorker db;
    private ParsingSite parsing;
    
    public GUI_Products() {        
        
        db = new DbWorker(false);     
        
        parsing = new ParsingSite();
        parsing.authorisation();
        parsing.setDb(db);        
        
        //parsing.parseTreeCatalog(false);
        initWindowTreeCatalog(); 
        productArrayList = new ArrayList<Product>();
        createFilterPanelsProduct();
        packPanelsProduct(createPanelRows());
        updatePanelRows(parsing.startParsingFile());//для теста
        
        //DbWorker.dbDisconnect();
        
    }
    
    private void initWindowTreeCatalog(){
        boolean packFrameTreeCatalog = false;
        FrameTreeCatalog frameTreeCatalog = new FrameTreeCatalog();
        //Упаковка менеджером 
        if(!packFrameTreeCatalog){
            frameTreeCatalog.pack();
        }
        else{
            frameTreeCatalog.validate();
        }
        //ЗАдание размера и положение формы с соответствием размера экрана
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameTreeSize = frameTreeCatalog.getSize();
        if(frameTreeSize.height > screenSize.height){
            frameTreeSize.height = screenSize.height;
        }
        if (frameTreeSize.width > screenSize.width){
            frameTreeSize.width = screenSize.width;
        }
        frameTreeCatalog.setLocation((screenSize.width - frameTreeSize.width) / 2, 
                 (screenSize.height - frameTreeSize.height) / 2);
        frameTreeCatalog.setVisible(true);
        JTree jTreeCatalog = ((CatalogPanel)frameTreeCatalog.getContentPane().getComponents()[0]).getTreeCatalog();
        jTreeCatalog.addTreeSelectionListener(new TreeSelectionListener() {

            @Override
            public void valueChanged(TreeSelectionEvent e) {
                DefaultMutableTreeNode node = (DefaultMutableTreeNode)jTreeCatalog.getLastSelectedPathComponent();
                if(node == null)return;
                CatalogNode catalogNode = (CatalogNode)node.getUserObject();
                System.out.println(catalogNode.toString());
                productArrayList = parsing.startParsing(catalogNode.getIdCatalog(), "http://monitor.pleer.ru/monitor/" + catalogNode.getHref());
                //productArrayList = parsing.startParsingFile();
                updatePanelRows(productArrayList);
            }
        });
    }

    private void createFilterPanelsProduct(){
        
      jPanel15 = new javax.swing.JPanel();
        jToggleButton1 = new javax.swing.JToggleButton();
        jToggleButton7 = new javax.swing.JToggleButton();
        jToggleButton10 = new javax.swing.JToggleButton();
        jButton8 = new javax.swing.JButton();
        jSeparator4 = new javax.swing.JSeparator();
        jToggleButton5 = new javax.swing.JToggleButton();
        jToggleButton8 = new javax.swing.JToggleButton();
        jToggleButton3 = new javax.swing.JToggleButton();
        jToggleButton9 = new javax.swing.JToggleButton();
        jToggleButton6 = new javax.swing.JToggleButton();
        jToggleButton4 = new javax.swing.JToggleButton();
        jToggleButton2 = new javax.swing.JToggleButton();
        jTextField23 = new javax.swing.JTextField();
        jTextField24 = new javax.swing.JTextField();
        jComboBox15 = new javax.swing.JComboBox();
        jButton70 = new javax.swing.JButton();
        jButton71 = new javax.swing.JButton();
        jButton72 = new javax.swing.JButton();
        jButton73 = new javax.swing.JButton();
        jButton74 = new javax.swing.JButton();
        jTextField25 = new javax.swing.JTextField();
        jComboBox16 = new javax.swing.JComboBox();
        jTextField26 = new javax.swing.JTextField();
        jTextField27 = new javax.swing.JTextField();
        jComboBox10 = new javax.swing.JComboBox();
        jTextField28 = new javax.swing.JTextField();
        jSeparator8 = new javax.swing.JSeparator();
        jPanel16 = new javax.swing.JPanel();
        jCheckBox5 = new javax.swing.JCheckBox();
        jCheckBox21 = new javax.swing.JCheckBox();
        jCheckBox20 = new javax.swing.JCheckBox();
        jCheckBox12 = new javax.swing.JCheckBox();
        jCheckBox24 = new javax.swing.JCheckBox();
        jCheckBox6 = new javax.swing.JCheckBox();
        jCheckBox26 = new javax.swing.JCheckBox();
        jCheckBox31 = new javax.swing.JCheckBox();
        jCheckBox27 = new javax.swing.JCheckBox();
        jCheckBox8 = new javax.swing.JCheckBox();
        jButton2 = new javax.swing.JButton();
        jCheckBox4 = new javax.swing.JCheckBox();
        jCheckBox1 = new javax.swing.JCheckBox();
        jCheckBox29 = new javax.swing.JCheckBox();
        jCheckBox17 = new javax.swing.JCheckBox();
        jCheckBox11 = new javax.swing.JCheckBox();
        jCheckBox3 = new javax.swing.JCheckBox();
        jCheckBox18 = new javax.swing.JCheckBox();
        jCheckBox13 = new javax.swing.JCheckBox();
        jCheckBox19 = new javax.swing.JCheckBox();
        jCheckBox28 = new javax.swing.JCheckBox();
        jCheckBox22 = new javax.swing.JCheckBox();
        jButton1 = new javax.swing.JButton();
        jCheckBox7 = new javax.swing.JCheckBox();
        jCheckBox10 = new javax.swing.JCheckBox();
        jCheckBox2 = new javax.swing.JCheckBox();
        jCheckBox32 = new javax.swing.JCheckBox();
        jCheckBox9 = new javax.swing.JCheckBox();
        jCheckBox15 = new javax.swing.JCheckBox();
        jCheckBox30 = new javax.swing.JCheckBox();
        jPanel9 = new javax.swing.JPanel();
        jCheckBox14 = new javax.swing.JCheckBox();
        jCheckBox37 = new javax.swing.JCheckBox();
        jComboBox2 = new javax.swing.JComboBox();
        jCheckBox23 = new javax.swing.JCheckBox();
        jCheckBox25 = new javax.swing.JCheckBox();
        jComboBox1 = new javax.swing.JComboBox();
        jPanel10 = new javax.swing.JPanel();
        jCheckBox34 = new javax.swing.JCheckBox();
        jCheckBox16 = new javax.swing.JCheckBox();
        jToggleButton12 = new javax.swing.JToggleButton();
        jButton7 = new javax.swing.JButton();
        jCheckBox33 = new javax.swing.JCheckBox();
        jLabel1 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jCheckBox35 = new javax.swing.JCheckBox();
        jButton3 = new javax.swing.JButton();
        jToggleButton11 = new javax.swing.JToggleButton();
        jCheckBox36 = new javax.swing.JCheckBox();
        

        setForeground(java.awt.SystemColor.window);
        setAutoscrolls(true);

        jPanel15.setBorder(javax.swing.BorderFactory.createTitledBorder("Фильтры и поиск"));

        jToggleButton1.setText("Продажи");
        jToggleButton1.setMargin(new java.awt.Insets(2, 4, 2, 4));
        jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton1ActionPerformed(evt);
            }

          private void jToggleButton1ActionPerformed(ActionEvent evt) {
              throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
          }
        });

        jToggleButton7.setText("Магазин");
        jToggleButton7.setMargin(new java.awt.Insets(2, 4, 2, 4));

        jToggleButton10.setText("Опт. огран");
        jToggleButton10.setMargin(new java.awt.Insets(2, 4, 2, 4));

        jButton8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton8.setForeground(new java.awt.Color(51, 51, 255));
        jButton8.setText("Сбросить");
        jButton8.setMargin(new java.awt.Insets(2, 4, 2, 4));

        jSeparator4.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jToggleButton5.setText("Рейтинг");
        jToggleButton5.setMargin(new java.awt.Insets(2, 4, 2, 4));

        jToggleButton8.setText("Доставка");
        jToggleButton8.setMargin(new java.awt.Insets(2, 4, 2, 4));
        jToggleButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton8ActionPerformed(evt);
            }

          private void jToggleButton8ActionPerformed(ActionEvent evt) {
              throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
          }
        });

        jToggleButton3.setText("Заказы");
        jToggleButton3.setMargin(new java.awt.Insets(2, 4, 2, 4));

        jToggleButton9.setText("РРЦ");
        jToggleButton9.setMargin(new java.awt.Insets(2, 7, 2, 7));

        jToggleButton6.setText("Вход");
        jToggleButton6.setMargin(new java.awt.Insets(2, 4, 2, 4));

        jToggleButton4.setText("Предзаказы");
        jToggleButton4.setMargin(new java.awt.Insets(2, 4, 2, 4));

        jToggleButton2.setText("Наличие");
        jToggleButton2.setMargin(new java.awt.Insets(2, 4, 2, 4));

        jTextField23.setText("Заменить на...");

        jTextField24.setText(" Найти...");

        jComboBox15.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Везде", "Наименование", "Артикул производителя", "Код поставщика", "Наличие", "Наличие удаленный склад", "Цена", "Цена 2", "Цена 3", "Скидка %", "РРЦ", "Производитель", "Гарантия", "Наш номер товара" }));
        jComboBox15.setToolTipText("Выбрать поле");

        jButton70.setText("Заменить");

        jButton71.setText("Пропустить");
        jButton71.setToolTipText("Пропустить найденный результат и перейти к следующему");
        jButton71.setMargin(new java.awt.Insets(2, 4, 2, 4));

        jButton72.setText("Найти далее");
        jButton72.setToolTipText("Поиск заданного значения");
        jButton72.setMargin(new java.awt.Insets(2, 4, 2, 4));

        jButton73.setText("Найти все");
        jButton73.setToolTipText("Показать результаты только с найденными значениями");
        jButton73.setMargin(new java.awt.Insets(2, 4, 2, 4));

        jButton74.setText("Заменить все");
        jButton74.setMargin(new java.awt.Insets(2, 4, 2, 4));

        jTextField25.setText("текст фильтра");
        jTextField25.setToolTipText("");

        jComboBox16.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Фильтр по полю", "Наименование", "Артикул производителя", "Код поставщика", "Наличие", "Наличие удаленный склад", "Цена", "Цена 2", "Цена 3", "Скидка %", "РРЦ", "Производитель", "Гарантия", "Наш номер товара" }));
        jComboBox16.setToolTipText("Выбрать поле");

        jTextField26.setText("Поиск товара");
        jTextField26.setToolTipText("");

        jTextField27.setText("Поиск в каталоге");
        jTextField27.setToolTipText("");

        jComboBox10.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Задать значение для поля ...", "Наименование", "Артикул производителя", "Код поставщика", "Наличие", "Наличие удаленный склад", "Цена", "Цена 2", "Цена 3", "Скидка %", "РРЦ", "Производитель", "Наш номер товара" }));
        jComboBox10.setToolTipText("Задать значение для поля, при выборе поля появится окошко для ввода значения");

        jTextField28.setText("Поле со значением");
        jTextField28.setToolTipText("");

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTextField24, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField23, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jComboBox15, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton70, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(3, 3, 3)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton72, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton71, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(3, 3, 3)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton73, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton74, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jComboBox16, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField25, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(5, 5, 5)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField28, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
                            .addComponent(jComboBox10, 0, 1, Short.MAX_VALUE))
                        .addGap(5, 5, 5)
                        .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 6, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField26, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField27, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel15Layout.createSequentialGroup()
                                .addComponent(jToggleButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jToggleButton2)
                                .addGap(3, 3, 3)
                                .addComponent(jToggleButton3)
                                .addGap(3, 3, 3)
                                .addComponent(jToggleButton4)
                                .addGap(3, 3, 3)
                                .addComponent(jToggleButton5)
                                .addGap(3, 3, 3)
                                .addComponent(jToggleButton6)
                                .addGap(3, 3, 3)
                                .addComponent(jToggleButton7)
                                .addGap(3, 3, 3)
                                .addComponent(jToggleButton8)
                                .addGap(3, 3, 3)
                                .addComponent(jToggleButton10)
                                .addGap(3, 3, 3)
                                .addComponent(jToggleButton9)
                                .addGap(3, 3, 3)
                                .addComponent(jButton8))
                            .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, 854, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jToggleButton1)
                    .addComponent(jToggleButton2)
                    .addComponent(jToggleButton3)
                    .addComponent(jToggleButton4)
                    .addComponent(jToggleButton5)
                    .addComponent(jToggleButton6)
                    .addComponent(jToggleButton7)
                    .addComponent(jToggleButton8)
                    .addComponent(jToggleButton10)
                    .addComponent(jToggleButton9)
                    .addComponent(jButton8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, 7, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton73)
                            .addComponent(jButton72)
                            .addComponent(jComboBox16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(1, 1, 1)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton70)
                            .addComponent(jButton74)
                            .addComponent(jButton71)
                            .addComponent(jTextField25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3))
        );

        jPanel16.setBorder(javax.swing.BorderFactory.createTitledBorder("Бренды в каталогах"));

        jCheckBox5.setForeground(new java.awt.Color(51, 51, 255));
        jCheckBox5.setText("A-Data");
        jCheckBox5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox5ActionPerformed(evt);
            }

          private void jCheckBox5ActionPerformed(ActionEvent evt) {
              throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
          }
        });

        jCheckBox21.setForeground(new java.awt.Color(51, 51, 255));
        jCheckBox21.setText("Buffalo");
        jCheckBox21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox21ActionPerformed(evt);
            }

          private void jCheckBox21ActionPerformed(ActionEvent evt) {
              throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
          }
        });

        jCheckBox20.setForeground(new java.awt.Color(51, 51, 255));
        jCheckBox20.setText("Hitachi");
        jCheckBox20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox20ActionPerformed(evt);
            }

          private void jCheckBox20ActionPerformed(ActionEvent evt) {
              throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
          }
        });       

        

        jButton2.setText("Показать все");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }

          private void jButton2ActionPerformed(ActionEvent evt) {
              throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
          }
        });

       

        jButton1.setText("Показать выбр");

        jCheckBox7.setForeground(new java.awt.Color(51, 51, 255));
        jCheckBox7.setText("Toshiba");
        jCheckBox7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox7ActionPerformed(evt);
            }

          private void jCheckBox7ActionPerformed(ActionEvent evt) {
              throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
          }
        });

        jCheckBox10.setForeground(new java.awt.Color(51, 51, 255));
        jCheckBox10.setText("Buffalo");
        jCheckBox10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox10ActionPerformed(evt);
            }

          private void jCheckBox10ActionPerformed(ActionEvent evt) {
              throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
          }
        });

        

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addComponent(jCheckBox29)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jCheckBox27)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox26)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox28)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox22)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox24)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox21)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox20)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox32)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox30)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox31))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addComponent(jCheckBox1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox15)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBox1)
                    .addComponent(jCheckBox2)
                    .addComponent(jCheckBox4)
                    .addComponent(jCheckBox3)
                    .addComponent(jCheckBox7)
                    .addComponent(jCheckBox8)
                    .addComponent(jCheckBox6)
                    .addComponent(jCheckBox5)
                    .addComponent(jCheckBox11)
                    .addComponent(jCheckBox12)
                    .addComponent(jCheckBox10)
                    .addComponent(jCheckBox9)
                    .addComponent(jCheckBox17)
                    .addComponent(jCheckBox15)
                    .addComponent(jButton1))
                .addGap(2, 2, 2)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jCheckBox27)
                    .addComponent(jCheckBox29)
                    .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jCheckBox30)
                        .addComponent(jCheckBox31)
                        .addComponent(jButton2))
                    .addComponent(jCheckBox32)
                    .addComponent(jCheckBox13)
                    .addComponent(jCheckBox18)
                    .addComponent(jCheckBox19)
                    .addComponent(jCheckBox20)
                    .addComponent(jCheckBox21)
                    .addComponent(jCheckBox22)
                    .addComponent(jCheckBox24)
                    .addComponent(jCheckBox26)
                    .addComponent(jCheckBox28))
                .addGap(0, 0, 0))
        );

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder("Каталоги"));

        jCheckBox14.setText("Жесткие диски");

        jCheckBox37.setText("USB Flash Drive");

        jComboBox2.setMaximumRowCount(20);
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Недавние каталоги", "Карты памяти", "Аксессуары для жестких дисков" }));
        jComboBox2.setPreferredSize(new java.awt.Dimension(134, 20));

        jCheckBox23.setText("Внешние");

        jCheckBox25.setText("Карты памяти");

        jComboBox1.setMaximumRowCount(20);
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Детские товары", "интерактивные игрушки", "видеоняни", "мыльные пузыри", "спортивные игры и развлечения", "аккумуляторы", "аспираторы", "бытовые термометры", "видеоняни", "радионяни", "говорящие игрушки", "головоломки", "детские весы", "игровые домики", "палатки", "сюжетно-ролевые игры", "железные дороги", "игрушки-грелки", "игрушки", "на управлении", "роботы", "бластеры", "детская бытовая техника", "микро-роботы", "настольные игры", "товары Hasbro", "Радиоуправляемые игрушки", "для малышей", "автотреки", "вертолеты", "самолеты", "катера", "машины", "НЛО", "роботы", "летающие рыбы", "птицы", "строительная техника", "военная техника", "Все игрушки", "интерактивные игрушки", "куклы", "тамагочи", "интерактивные глобусы", "подогреватели", "стерилизаторы", "молокоотсосы", "развивающие игрушки", "для малышей", "детские музыкальные инструменты", "детские планшеты", "чехлы для детских планшетов", "звуковые плакаты", "конструкторы", "наборы для творчества", "сборные модели", "напольные игры", "обучающие книги", "развивающие коврики", "сортеры", "танцевальные коврики", "Все развивающие игрушки", "фокусы и удивительное", "Квадрокоптеры и аксессуары", "Квадрокоптеры", "все для квадрокоптеров", "Часы наручные", "аналоговые", "цифровые", "Живой песок", "Жидкая кожа", "Нейроинтерфейсы", "Пластик для лепки и моделирования", "Подарки ВСЕМ!", "3D-пазлы", "аудио гаджеты", "брелоки", "домашние планетарии", "диски для домашних планетариев", "домашние энергосберегающие гаджеты", "жвачки для рук", "живые кристаллы", "звонки дверные", "зонты", "игрушки антистресс", "инновационные игрушки", "йо-йо", "копилки для денег", "кружки", "кружки-объективы", "кубики Рубика", "левитроны", "магниты NeoCube", "CrazyBalls", "многофункциональные часы & радио/будильники-гаджеты", "настольные наборы", "подарочные USB Flash Drive", "подарочные гаджеты", "подставки под кружку с подогревом", "сертификаты ПлеерРу", "символ 2015 года", "сувениры", "таймеры для всего", "теплые перчатки для сенсорных дисплеев", "фонтаны декоративные", "часы бинарные", "шары для принятия решений", "Все Подарки ВСЕМ", "Солнцезащитные очки Polaroid", "детские", "женские", "мужские", "спортивные", "коллекция Suncovers", "аксессуары для очков", "Снежкоделы", "снежкобластеры", "Термобелье", "детское", "боди", "колготки", "комбинезоны", "носки", "футболки", "шапочки", "штаны", "мужское", "кальсоны", "комплекты", "носки", "футболки", "шорты", "женское", "кальсоны", "комплекты", "носки", "футболки", "шорты", "тепловые маски", "Все термобелье", "Подарочные ручки PARKER", "Waterman", "Cross и все для них", "ручки", "расходные материалы", "Подарочные наборы PARKER", "Waterman", "Cross и др", "Браслеты POWER BALANCE", "Lifestrength", "C-PRIME и др", "Гаджеты для APPLE и Android", "Фото", "аксессуары", "студийное оборудование", "Фотоаппараты", "Смартографы", "Объективы", "Lensbaby", "объективы для Canon", "объективы для Canon M", "объективы для FujiFilm X Mount", "объективы для Nikon/FujiFilm", "объективы для Nikon 1", "объективы для Olympus 4/3", "объективы для Olympus Micro 4/3 / Panasonic", "объективы для Pentax", "объективы для Samsung NX", "объективы для Sony/Minolta", "объективы для Sony NEX и видео", "объективы универсальные", "Все Объективы", "док-станции для объективов", "Фотоаксессуары", "все для Samsung NX", "все для Sony NEX", "все для Fujifilm Instax", "все для Micro 4/3 / Micro Four Thirds", "все для Polaroid Instant", "аквабоксы", "сумки и чехлы для фото", "дорожные сумки", "чемоданы для техники", "всепогодные чехлы", "футляры для объективов", "рюкзаки для фото", "ремни кистевые", "ремни наплечные и нашейные", "внешние карт-ридеры", "светофильтры", "светофильтры 43мм", "светофильтры 46мм", "светофильтры 49мм", "светофильтры 52мм", "светофильтры 55мм", "светофильтры 58мм", "светофильтры 62мм", "светофильтры 67мм", "светофильтры 72мм", "светофильтры 77мм", "светофильтры 82мм", "светофильтры 86мм", "светофильтры 105мм", "крышки для объективов/держатели", "чехлы для светофильтров", "уход за оптикой", "уход за матрицей фотоаппаратов", "сжатый воздух", "кабели для фото", "модули GPS", "вспышки", "вспышки универсальные", "вспышки для Canon", "вспышки для FujiFilm", "вспышки для Nikon", "вспышки для Olympus", "вспышки для Panasonic", "вспышки для Pentax/Samsung", "вспышки для Sony/Minolta", "чехлы для вспышек", "держатели для вспышек", "радиосинхронизаторы", "адаптеры", "кабелидля вспышек", "Все вспышки", "рассеиватели", "все для портретной съемки / рефлекторы", "видоискатели", "видеовидоискатели", "флэшметры / экспонометры", "наглазники / фокусировочные экраны", "защитные пленки/накладки", "бленды/крышки на дисплей", "аккумуляторы AA/AAA и другие", "зарядки специальные", "футляры для карт памяти и батареек", "аккумуляторы специальные", "аккумBLE-9", "аккумBLM-1", "аккумBLN-1", "аккумBP-210E", "аккумBP-511/BP-511A", "аккумBP-88A", "аккумCGA-S005", "аккумCGA-S006", "аккумCGA-S007", "аккумD-Li68", "аккумD-Li72", "аккумD-Li109", "аккумDMW-BCF10", "аккумDMW-BCG10", "аккумDMW-BCL7", "аккумEN-EL1/EL2", "аккумEN-EL8", "аккумEN-EL10", "аккумEN-EL11", "аккумEN-EL12", "аккумEN-EL14", "аккумEN-EL15", "аккумEN-EL18/EN-EL18A", "аккумEN-EL19", "аккумEN-EL20", "аккумEN-EL21", "аккумEN-EL22", "аккумEN-EL23", "аккумEN-EL3e", "аккумEN-EL4", "аккумEN-EL5", "аккумEN-EL9", "аккумEN-EL9a", "аккумLi-10/12B", "аккумLi-40B/Li-42B", "аккумLi-50B", "аккумLi-70B", "аккумLi-90B", "аккумLP-E10", "аккумLP-E12", "аккумLP-E4N", "аккумLP-E5", "аккумLP-E6", "аккумLP-E8", "аккумNB-10L", "аккумNB-11L/11LH", "аккумNB-2L/2LH", "аккумNB-3L", "аккумNB-4L", "аккумNB-5L", "аккумNB-6L/6LH", "аккумNB-7L", "аккумNB-9L", "аккумNP-50/NP-60", "аккумNP-95", "аккумNP-BD1/FD1", "аккумNP-BG1/FG1", "аккумNP-BN1", "аккумNP-BX1", "аккумNP-BY1", "аккумNP-E3", "аккумNP-FH50/FH50AM", "аккумNP-FM500H", "аккумNP-FW50", "аккумNP-W126", "аккумNP-FM50", "блоки питания", "батарейные блоки", "бленды на объективы", "бленды для Canon", "бленды для FujiFilm", "бленды для Nikon", "бленды для Sony", "бленды универсальные", "системы стабилизации", "обвесы", "краны", "тележки", "штативы", "головки для штативов", "мини-штативы настольные", "мини-штативы универсальные", "моноподы", "площадки для штативов", "ручные штативы", "штативы Joby Gorillapod", "штативы трехногие", "Все штативы", "сумки и ремни для штативов", "конвертеры", "удлинительные кольца", "уровни для фотоаппаратов", "переходные кольца", "реверсивные кольца", "микрофоны универсальные", "микрофоны для Canon", "микрофоны для FujiFilm", "микрофоны для Nikon", "микрофоны для Sony", "микрофоны для Panasonic", "установка баланса белого", "пульты ДУ", "пульты ДУ универсальные", "пульты ДУ для Canon", "пульты ДУ для Nikon", "пульты ДУ для Olympus", "пульты ДУ для Panasonic", "пульты ДУ для Sony", "Все Фотоаксессуары", "Студийное фотооборудование", "3D студии", "вентиляторы студийные", "галогенные лампы", "импульсные лампы", "журавли", "зонты", "держатели для зонтов", "меха для фото", "фоны", "держатели фонов", "комплекты для макросъемки", "комплекты студийного света", "аксессуары для вспышек", "вспышки студийные", "осветители", "накамерный свет", "светоотражатели", "лайт-диски", "держатели светоотражателей", "софтбоксы", "спецэффекты", "стойки студийные", "системы управления студийным светом", "колеса для стоек", "сумки для студийного оборудования", "фотобоксы и фотостолы", "Все Студийное фотооборудование", "Калибраторы и мишени", "Телефоны", "умные часы(смарт) и браслеты", "VoIP", "аксессуары", "Сотовые телефоны", "смартфоны и фаблеты", "Аксессуары к сотовым", "адаптеры для SIM-карт", "Multisim", "антивирусы для мобильных устройств", "все для Acer", "все для Alcatel", "все для Alcatel OneTouch 4018D Pop D1", "все для Alcatel OneTouch 4033D Pop C3", "все для Alcatel OneTouch 4035D Pop D3", "все для Alcatel OneTouch 5036D Pop C5", "все для Alcatel OneTouch 5038D Pop D5", "все для Alcatel OneTouch 6012X Idol Mini", "все для Alcatel OneTouch 6014X Idol 2 Mini", "все для Alcatel OneTouch 6016X Idol 2 Mini", "все для Alcatel OneTouch 6032X Idol Alpha", "все для Alcatel OneTouch 6037Y Idol 2", "все для Alcatel OneTouch 7041D Pop C7", "все для Alcatel OneTouch 7045Y Pop S7", "все для Alcatel OneTouch 7047D Pop C9", "все для Alcatel OneTouch 7050Y Pop S9", "все для Alcatel OneTouch Pixi 7", "все для прочих моделей Alcatel", "кабели", "аккумуляторы", "зарядные устройства для Alcatel", "все для ASUS", "все для Effire", "все для Explay", "все для Fly", "все для Fly IQ436i Era Nano 9", "все для Fly IQ4401 Era Energy 2", "все для Fly IQ4405 Evo Chic 1", "все для Fly IQ4406 Era Nano 6", "все для Fly IQ4413 Evo Chic 3 Quad", "все для Fly IQ4414 Evo Tech 3 Quad", "все для Fly IQ4415 Quad Era 3", "все для Fly IQ4416 Era Life 5", "все для Fly IQ4417 Era Energy 3 Quad", "все для Fly IQ4418 Era 4", "все для Fly IQ4490i Era Nano 10", "все для Fly IQ4491 Era Life 3", "все для Fly IQ4502 Era Energy 1 Quad", "все для Fly IQ4503 Era Life 6 Quad", "все для Fly IQ4504 Evo Energy 5", "все для Fly IQ4505 Era Life 7 Quad", "все для Fly IQ4511 Tornado One Octa", "все для Fly IQ4512 Evo Chic 4 Quad", "все для Fly IQ4516 Tornado Slim Octa", "все для прочих моделей Fly", "кабели", "аккумуляторы", "зарядные устройства для Fly", "все для HTC", "все для HTC Desire Eye", "все для HTC Desire 820 mini", "все для HTC Desire 820 Dual Sim", "все для HTC Desire 610", "все для HTC Desire 616 / Desire 616 Dual Sim", "все для HTC Desire 816 Dual Sim / Desire 816G Dual Sim", "все для HTC One E9", "все для HTC One E8", "все для HTC One M9", "все для HTC One M8", "все для HTC One Max", "все для HTC One", "все для HTC One Mini", "все для HTC One Mini 2", "все для HTC One Dual SIM", "все для HTC Desire 700 / Desire 700 Dual", "все для HTC Desire 601 / Desire 601 Dual Sim", "все для HTC Desire 600 / 606w Dual SIM", "все для HTC Desire 516", "все для HTC Desire 510", "все для HTC Desire 501 / 501 Dual SIM", "все для HTC Desire 500 / 500 Dual SIM", "все для HTC Desire 400", "все для HTC Desire 300", "все для HTC Desire 310", "все для HTC Desire 210", "все для HTC Desire 200", "все для HTC Windows Phone 8X", "все для HTC Windows Phone 8S", "все для HTC Desire C", "все для HTC Desire SV", "все для HTC One V", "все для прочих моделей HTC", "кабели", "переходники", "адаптеры", "док-станции", "мультимедийные блоки для HTC", "все для Highscreen", "все для Huawei", "все для iPhone 4", "4S", "3G", "3Gs", "аккумуляторы для iPhone", "держатели для iPhone", "док-станции для iPhone", "защитные пленки для iPhone", "чехлы для iPhone", "все для iPhone 5 / 5S", "аккумуляторы", "чехлы-аккумуляторы для iPhone 5 / 5S", "держатели для iPhone 5 / 5S", "док-станции для iPhone 5 / 5S", "защитные пленки для iPhone 5 / 5S", "кабели", "переходники", "адаптеры для iPad mini", "iPad Air", "iPphone 5 / 5S / 5C / 6", "чехлы для iPhone 5 / 5S", "все для iPhone 5C", "аккумуляторы", "чехлы-аккумуляторы для iPhone 5C", "док-станции для iPhone 5C", "защитные пленки для iPhone 5C", "чехлы для iPhone 5C", "все для iPhone 6", "защитные пленки для iPhone 6", "чехлы-аккумуляторы для iPhone 6", "чехлы для iPhone 6", "все для iPhone 6 Plus", "защитные пленки для iPhone 6 Plus", "чехлы для iPhone 6 Plus", "все для KENEKSI", "все для Lenovo", "все для LG", "все для LG D285 L65", "все для LG D295 L Fino", "все для LG D325 L70", "все для LG D335 L Bello", "все для LG D410 L90", "все для LG D618 G2 mini", "все для LG D690 G3 Stylus", "все для LG D722 G3 S / D724 G3 S", "все для LG D821 Nexus 5", "все для LG D856 G3 Dual LTE", "все для LG E455 Optimus L5 II Dual", "все для LG X145 L60", "все для прочих моделей LG", "кабели", "аккумуляторы", "зарядные устройства для LG", "все для Meizu", "все для Micromax", "все для Microsoft", "все для Microsoft Lumia 435 / 532", "все для Microsoft Lumia 535", "все для Microsoft Lumia 640", "все для Microsoft Lumia 640 XL", "все для Nokia", "все для Nokia Asha 206", "все для Nokia 225", "все для Nokia Asha 230", "все для Nokia Asha 500", "все для Nokia Asha 501", "все для Nokia Asha 502", "все для Nokia Asha 503", "все для Nokia Lumia 1020", "все для Nokia Lumia 1320", "все для Nokia Lumia 1520", "все для Nokia Lumia 530", "все для Nokia Lumia 520 / 525", "все для Nokia Lumia 620", "все для Nokia Lumia 625", "все для Nokia Lumia 630 / 635 / 630 Dual SIM", "все для Nokia Lumia 636", "все для Nokia Lumia 720", "все для Nokia Lumia 730 / 735", "все для Nokia Lumia 820", "все для Nokia Lumia 830", "все для Nokia Lumia 920", "все для Nokia Lumia 925", "все для Nokia Lumia 930", "все для Nokia X / Nokia X+", "все для Nokia XL", "все для Nokia X2", "все для прочих моделей Nokia", "кабели", "аккумуляторы", "зарядные устройства для Nokia", "все для Philips", "все для Samsung", "все для Samsung SM-N9150 Galaxy Note Edge", "все для Samsung SM-A300 Galaxy A3", "все для Samsung SM-A500F Galaxy A5", "все для Samsung Galaxy A7", "все для Samsung SM-E700 Galaxy E7", "все для Samsung Galaxy E5", "все для Samsung SM-G350E Galaxy Star Advance", "все для Samsung SM-G357FZ Galaxy Ace LTE", "все для Samsung SM-G850 / SM-G850F Galaxy Alpha", "все для Samsung SM-G530H Galaxy Grand Prime", "все для Samsung G3812B Galaxy S III Slim", "все для Samsung SM-G360H Galaxy Core Prime", "все для Samsung SM-G355 Galaxy Core 2", "все для Samsung SM-G386F Galaxy Core LTE", "все для Samsung SM-G130 Galaxy Young 2", "все для Samsung SM-J100F Galaxy J1", "все для Samsung GT-S7390 Galaxy Trend / GT-S7572/S7570 Galaxy Trend II / SM-G3502U Galaxy Trend III", "все для Samsung GT-i8260 / GT-i8262 Galaxy Core", "все для Samsung GT-I8580 Galaxy Core Advance", "все для Samsung GT-I8160 Galaxy Ace II", "все для Samsung GT-S5830 Galaxy Ace", "все для Samsung SM-C1150 Galaxy K Zoom", "все для Samsung GT-S7260 Galaxy Star Pro / GT-S7262 Galaxy Star Plus", "все для Samsung SM-G7508 Galaxy Mega 2", "все для Samsung SM-G7200 Galaxy Grand 3", "все для Samsung SM-G7100 Galaxy Grand 2 / SM-G7102 Galaxy Grand 2 Duos", "все для Samsung SM-G925 Galaxy S6 Edge", "все для Samsung SM-G920 Galaxy S6", "все для Samsung SM-G8508S Galaxy S5 Neo", "все для Samsung GT-i900F Galaxy S5 / SM-G900F Galaxy S5 / SM-G900H Galaxy S5 / SM-G900FD Galaxy S5 Duos", "все для Samsung SM-G800 Galaxy S5 mini / SM-G800H Galaxy S5 mini Duos", "все для Samsung GT-i9500 Galaxy S4 / GT-i9505 Galaxy S4 LTE", "все для Samsung GT-i9192 Galaxy S4 mini DUOS / GT-i9190 Galaxy S4 mini", "все для Samsung GT-i9295 Galaxy S4 Active", "все для Samsung GT-i9300 Galaxy S III / GT-I9300I S III Duos", "все для Samsung GT-i8552 Galaxy Win", "все для Samsung GT-i9152 Galaxy Mega 58 DUOS / GT-i9150 Galaxy Mega 58", "все для Samsung GT-i9200 Galaxy Mega 63 / GT-i9205 Galaxy Mega 63 LTE", "все для Samsung SM-C101 Galaxy S4 Zoom", "все для Samsung GT-i9260 Galaxy Premier", "все для Samsung SM-N910 / SM-N9106 Galaxy Note 4", "все для Samsung GT-N9000 Galaxy Note III / SM-N7505 Galaxy Note III Neo", "все для Samsung GT-N7100 Galaxy Note II", "все для Samsung GT-i8190 Galaxy S III Mini / GT-i8200 Galaxy S III mini Value Edition", "все для Samsung GT-i9082 Galaxy Grand Duos", "все для Samsung GT-i8750 ATIV S", "все для Samsung GT-i9105 Galaxy S II Plus / GT-i9100 Galaxy S II", "все для Samsung SM-G313 Galaxy Ace 4", "все для Samsung GT-S7270 Galaxy Ace 3 / GT-S7272 Galaxy Ace 3 Dual", "все для Samsung SM-G3815 Galaxy Express 2", "все для Samsung GT-i9060 Galaxy Grand Neo", "все для Samsung GT-S6790 Galaxy Fame Lite", "все для Samsung GT-S7582 Galaxy Trend Plus", "все для прочих моделей Samsung", "кабели", "переходники", "аккумулятор", "адаптеры", "док-станции", "NFC-маркеры для Samsung", "все для Sharp", "все для Sony", "все для Sony S39h C2305 Xperia C", "все для Sony Xperia C4", "все для Sony D2533 Xperia C3 / D2502 Xperia C3 Dual Sim", "все для Sony L39h C6903 Xperia Z1", "все для Sony D5503 Xperia Z1 Compact", "все для Sony D6503 Xperia Z2", "все для Sony D6603 Xperia Z3 / D6633 Xperia Z3 Dual", "все для Sony D5803 Xperia Z3 Compact", "все для Sony Xperia Z4", "все для Sony L36h C6603 Xperia Z LTE / Sony C6602 Xperia Z", "все для Sony C6503 Xperia ZL LTE / Sony C6502 Xperia ZL", "все для Sony C5303 Xperia SP", "все для Sony LT25i Xperia V", "все для Sony C6833 Xperia Z Ultra LTE / Sony C6802 Xperia Z Ultra", "все для Sony ST26i Xperia J", "все для Sony S36h C2105 Xperia L / Sony S36 C2104 Xperia L", "все для Sony C2005 Xperia M Dual / Sony C1905 Xperia M", "все для Sony C1605 Xperia E Dual / Sony Xperia E C1505", "все для Sony Xperia E1 / E1 Dual", "все для Sony Xperia E2 / E2 Dual", "все для Sony Xperia M4 Aqua", "все для Sony D2303 Xperia M2", "все для Sony Xperia E3 / D2212 Xperia E3 Dual Sim", "все для Sony Xperia E4 / Sony Xperia E4 Dual", "все для Sony Xperia E4g / Sony Xperia E4g Dual", "все для Sony Xperia T3", "все для Sony D5303/D5306/D5322 Xperia T2 Ultra Dual", "все для прочих моделей Sony", "кабели", "аккумуляторы", "переходники", "адаптеры", "док-станции", "метки для Sony", "все для Sony Ericsson", "все для Xiaomi", "все для YotaPhone", "все для Zopo", "держатели в авто", "телескопические ручные штативы для Селфи", "мини-штативы для сотовых", "объективы", "конвертеры", "микроскопы для сотовых", "пульты управления камерой", "стилусы универсальные", "чехлы универсальные", "Все аксессуары к сотовым", "Умные часы / смарт-часы", "Браслеты умные", "Подавители сотовых телефонов", "Гаджеты на солнечных батареях", "Стационарные GSM телефоны", "Детские сотовые телефоны", "Skylink", "телефоны Skylink", "DECT телефония и аксессуары", "DECT телефоны", "аксессуары для DECT", "Мини АТС", "Оборудование VoIP (IP телефоны)", "трубки для Skype", "Планшеты", "ноутбуки", "моноблоки", "неттопы", "мини ПК", "Планшетные компьютеры", "аксессуары для планшетных компьютеров", "Автодержатели для планшетов", "держатели для планшетов", "универсальные аксессуары", "универсальные чехлы", "до 7 дюймов", "от 79 до 9 дюймов", "от 97 до 11 дюймов", "все для 3Q", "все для Acer Iconia", "все для Alcatel Tablet", "все для Archos IT", "все для ASUS Pad", "все для DELL Tablet", "все для Explay Tablet", "все для Etuline Tablet", "все для Fly Tablet", "все для Google Nexus", "все для Huawei Tablet", "все для HTC Tablet", "виниловые наклейки для iPad", "все для iPad 2 / iPad 3 / iPad 4", "держатели для iPad", "защитные пленки для iPad", "кабели", "переходники", "адаптеры для iPad 2 / 3", "iPhone 4 / 4S", "клавиатуры для iPad", "чехлы для iPad", "все для iPad Air", "защитные пленки для iPad Air / iPad Air 2", "клавиатуры для iPad Air", "чехлы для iPad Air", "все для iPad Air 2", "чехлы для iPad Air 2", "все для iPad mini / iPad mini 2 / iPad mini 3", "держатели для iPad mini", "защитные пленки для iPad mini", "клавиатуры для iPad mini", "чехлы для iPad mini", "все для Lenovo Tablet", "все для LG Tablet", "все для Microsoft Surface", "все для MIReader", "все для Nokia Tablet", "все для NVIDIA Shield Tablet", "все для PiPO", "все для PocketBook", "все для Prestigio MultiPad", "все для Samsung Tab", "все для Samsung Galaxy Tab S 84 SM-T700 / SM-T705", "все для Samsung Galaxy Tab S 105 SM-T800 / SM-T805", "все для Samsung Galaxy Tab 4 101 SM-T530 / SM-T531", "все для Samsung Galaxy Tab 4 80 SM-T330 / SM-T331", "все для Samsung Galaxy Tab 4 70 SM-T230 / SM-T231", "все для Samsung Galaxy Tab Pro 122 T900 / Galaxy Note Pro 122 P9000 / P9050", "все для Samsung Galaxy Tab 3 70 Lite SM-T110 / SM-T111", "все для Samsung Galaxy Tab Pro 84 SM-T325 / SM-T320", "все для Samsung Galaxy Tab Pro 101 SM-T520 / SM-T525", "все для Samsung Galaxy Tab 2 101 P5100 / Galaxy Tab 2 101 P5110", "все для Samsung Galaxy Note 101 N8000 / Galaxy Note 101 N8010", "все для Samsung Galaxy Note 80 N5100 / Galaxy Note 80 N5110", "все для Samsung Galaxy Tab 2 70 P3100 / Galaxy Tab 2 70 P3110", "все для Samsung Galaxy Tab 3 80 SM-T310 / Galaxy Tab 3 80 SM-T311", "все для Samsung Galaxy Tab 3 101 P5200 / Galaxy Tab 3 101 P5210", "все для Samsung Galaxy Tab 3 70 SM-T2100 / Galaxy Tab 3 70 SM-T2110 / Samsung Galaxy Tab 3 70 SM-T2105 Kids", "все для Samsung ATIV Smart PC XE500T1C-A02", "все для Samsung ATIV Smart PC Pro XE700T1C-H02", "все для Samsung SM-P601 Galaxy Note 101 2014 Edition / Galaxy Note 101 2014 Edition P6000 / Galaxy Note 101 2014 Edition P6010 / Galaxy Note 101 LTE P6050", "все для прочих моделей Samsung Tab", "кабели", "переходники", "адаптеры", "клавиатуры", "док-станции для Samsung Tab", "все для Sony Tablet", "все для Tesla", "все для VOYO", "все для Wexler Tab", "герметичные чехлы для планшетов", "элкниг", "Apple", "фото", "телефонов", "документов и тд", "пульты управления камерой", "Все аксессуары для планшетных компьютеров", "Нетбуки & ноутбуки", "Аксессуары для нетбуков", "ноутбуков", "неттопов", "моноблоков", "GPRS приемники", "модемы", "уход за дисплеем", "клавиатурой", "Акустические системы", "колонки", "аккумуляторы для буков", "аккумуляторы MacBook", "аккумуляторы Acer", "аккумуляторы ASUS", "аккумуляторы Dell", "аккумуляторы Fujitsu-Siemens", "аккумуляторы HP", "аккумуляторы LENOVO", "аккумуляторы MSI", "аккумуляторы Samsung", "аккумуляторы Sony", "аккумуляторы Toshiba", "блоки питания 220В", "блоки питания 220В универсальные", "блоки питания 220В Acer", "блоки питания 220В APPLE", "блоки питания 220В ASUS", "блоки питания 220В Dell", "блоки питания 220В HP", "блоки питания 220В Lenovo", "блоки питания 220В MSI", "блоки питания 220В Samsung", "блоки питания 220В Sony", "блоки питания 220В Toshiba", "блоки питания в авто", "блоки питания в авто универсальные", "блоки питания в авто APPLE", "блоки питания в авто ASUS", "блоки питания в авто Samsung", "док-станции для MacBook", "замки", "тросы для ноутбуков", "защитные пленочки", "кабели и переходники", "кабели и переходники для нетбуков", "ноутбуков", "PC", "клавиатуры беспроводные", "клавиатуры проводные", "цифронабиратели", "наборы клавиатура+мышь", "коврики для мышонышей", "лампочки USB", "мышки", "мышки Bluetooth", "мышки беспроводные", "мышки подарочные", "мышки проводные", "ножки для игровых мышей", "подставки для ноутбуков", "пульты дистанционного управления", "рюкзаки для ноутбука и фототехники", "сумки и чехлы", "до 12 дюймов", "от 13 до 14 дюймов", "от 15 до 16 дюймов", "от 17 дюймов и больше", "цифровые ручки", "Все аксессуары для нетбуков", "ноутбуков", "неттопов", "моноблоков", "Мини ПК", "Неттопы", "Wi-Fi / CDMA / 3G / LTE / TV антенны", "усилители сигнала", "Моноблоки", "Мониторы", "Кронштейны", "для мониторов", "телевизоров и акустических систем", "для проекторов", "для СВЧ-печей", "полки универсальные", "блоки питания для мониторов", "Bluetooth передатчики", "Хабы USB", "Графические планшеты и аксессуары", "Графические планшеты", "аксессуары для графических планшетов", "Сетевое оборудование", "оргтехника", "ПО", "периферия", "Документ-камеры", "Источники бесперебойного питания", "сетевые фильтры", "стабилизаторы", "Калькуляторы", "Принтеры", "сканеры", "картриджи", "бумага", "бумага для принтеров", "картриджи", "тонеры", "ламинаторы", "пленки для ламинаторов", "МФУ", "принтеры", "3D ручки", "3D принтеры", "лазерные", "светодиодные", "портативные", "принадлежности для 3D-принтеров и 3D-ручек", "резаки для бумаги", "сканеры", "сумки для принтеров", "фотобумага для принтеров", "шредеры", "Вся оргтехника", "картриджи", "бумага", "Программное обеспечение", "антивирусы", "продление гарантии APPLE", "ABBYY", "Microsoft Office", "Microsoft Windows", "Все Программное обеспечение", "Сетевое оборудование", "Powerline адаптеры", "Wi-Fi адаптеры", "Wi-Fi роутеры", "точки доступа", "xDSL модемы", "голосовые шлюзы", "коммутаторы Ethernet", "коммутационные панели", "межсетевые экраны", "переключатели KVM", "портативные роутеры 3G/4G", "принт-серверы", "проводные роутеры", "сетевые карты", "сетевые хранилища", "тестеры", "аксессуары для сетевого оборудования", "Все сетевое оборудование", "Банковское оборудование", "детекторы валют", "счетчики банкнот", "монет", "Электронные книги и все для них", "электронные книги", "чехлы для книг", "универсальные чехлы", "защитные пленки для книг", "лампочки для книг", "Кабели и переходники", "DVI", "HDMI", "Jack 25/35/63mm", "RCA", "SCART", "USB A/B/Micro/Mini", "для iPhone/iPad/iPod", "кабели питания", "оптические", "Все кабели и переходники", "Зарядные устройства", "батарейки", "аккумуляторы", "внешние аккумуляторы", "автомобильные", "беспроводные", "от сети 220В", "мобильные", "внешние аккумуляторы", "зарядки для AA/AAA/C/D/КРОНА/ 18500/18650/RCR123", "аккумуляторы", "тип AA", "тип AAA", "тип 18650", "тип C/D/КРОНА/RCR123", "батарейки различные", "аксессуары для аккумуляторов", "Все зарядные устройства", "батарейки", "аккумуляторы", "внешние аккумуляторы", "Компьютерные комплектующие", "корпуса", "Optibay", "материнские платы", "для процессоров AMD", "Socket AM3+", "Socket FM2/FM2+", "для процессоров Intel", "Socket LGA1150", "Socket LGA1155", "Socket LGA2011", "Socket 478 / LGA775", "с предустановленным процессором", "процессоры", "AMD", "Socket AM3", "Socket AM3+", "Socket FM2/FM2+", "Intel", "Socket LGA1150", "Socket LGA1155", "Socket LGA2011", "кулеры", "системы охлаждения", "вентиляторы для корпуса", "водяное охлаждение", "кулеры", "все для кулеров", "термопаста", "термоинтерфейсы", "видеокарты", "системы охлаждения для видеокарт", "приводы", "внешние", "внутренние", "звуковые карты", "FireWire", "PCI", "PCI-E", "USB", "внутренние карт-ридеры", "функциональные панели", "3", "5 дюйма", "5", "25 дюйма", "контроллеры", "TV-тюнеры", "блоки питания для компьютеров", "модули памяти", "для компьютеров", "DDR1", "DDR2", "DDR3", "DDR4", "для ноутбуков", "DDR2", "DDR3", "системы охлаждения памяти", "Все модули памяти", "автотрансформаторы", "источники бесперебойного питания", "сетевые фильтры", "стабилизаторы", "автотрансформаторы (ЛАТР)", "источники бесперебойного питания (ИБП)", "аккумуляторы для ИБП", "сетевые фильтры и удлинители", "удлинители на катушке", "на рамке", "в бухте", "стабилизаторы", "шлейфы", "кабели", "удлинители", "переходники", "кабели и переходники SATA / eSATA / IDE", "кабели и переходники VGA / SVGA / DisplayPort", "патч-корды", "переходники и адаптеры", "разветвители", "удлинители", "Все компьютерные комплектующие", "Жесткие диски", "аксессуары для жестких", "HDD", "внешние", "для компьютеров", "для ноутбуков", "SSD", "внешние", "для компьютеров", "для ноутбуков", "Все Жесткие диски", "USB Flash Drive", "Карты памяти", "Compact Flash (CF) карты", "Secure Digital (SD) карты", "Memory Stick Pro Duo карты", "Micro SD карты", "xD-Picture Card карты", "для MacBook", "Все Карты памяти", "Видео- камеры", "регистраторы", "веб", "очки", "маски", "экшн", "аксессуары", "Видеокамеры", "Аксессуары для видеокамер", "сумки и чехлы для видеокамер", "светофильтры для видеокамер", "светофильтры до 305мм", "светофильтры 37мм", "светофильтры 405мм", "микрофоны для JVC", "штативы", "зарядки специальные", "аккумуляторы специальные", "аккумBN-V416", "аккумBN-V428", "аккумBP-2L24H", "аккумBP-535", "аккумBP-709", "аккумBP-718", "аккумBP-727", "аккумBP-808", "аккумBP-809", "аккумBP-820", "аккумBP-827", "аккумBP-828", "аккумCGA-DU14", "аккумCGA-DU21", "аккумDB-L90", "аккумIA-BH130", "аккумNP-F960/F970", "аккумNP-FA70", "аккумNP-FF51", "аккумNP-FV100", "аккумNP-FV50", "аккумNP-FV70", "аккумSB-L160", "аккумSB-P90A", "аккумVW-VBG6", "аккумVW-VBK180", "аккумVW-VBK360", "аккумVW-VBN130", "аккумVW-VBN260", "аккумNP-FH100", "аккумNP-FH70", "видеоконвертеры", "Все аксессуары для видеокамер", "Камеры с интервальной съемкой", "камеры Brinno", "аксессуары для камер Brinno", "3D и видео-очки", "3D-очки", "видео-очки", "Вебкамеры", "Экшн камеры и все для них", "экшн камеры", "все для экшн камер", "Системы безопасности и видеонаблюдения", "биометрические замки", "детекторы", "доводчики дверные", "домофоны", "трубки для подъездных домофонов", "видеоглазки", "готовые комплекты видеодомофонов", "видеодомофоны", "вызывные панели", "замки и фурнитура", "видеонаблюдение", "готовые комплекты видеонаблюдения", "IP камеры", "комнатные", "купольные", "корпусные", "поворотно-наклонные", "уличные", "видеорегистраторы для IP камер", "аналоговые камеры", "видеорегистраторы для аналоговых камер", "муляжи камер", "камеры с интервальной съемкой", "сигнализации и датчики", "пожарные извещатели", "готовые комплекты сигнализаций", "системы контроля доступа", "аксессуары для систем охраны и наблюдения", "Фотоловушки", "аксессуары для фотоловушек", "все системы охраны и слежения", "Цифровые диктофоны", "диктофоны Edic-mini", "все для Edic-mini", "диктофоны цифровые", "Оборудование и аксессуары ZOOM", "Плееры (аудио) и аксессуары", "MP3-flash плееры", "MP3 плееры на 2Gb", "MP3 плееры на 4Gb", "MP3 плееры на 8Gb", "MP3 плееры на 16Gb", "MP3 плееры на 32Gb", "MP3 плееры на 64Gb", "все MP3 плееры", "Аудиоаксессуары", "все для iPod", "все для Classic", "все для Touch", "все для Nano", "все для Creative", "все для Cowon iAudio", "все для Fiio", "все для Hidizs", "все для iRiver", "защитные пленки", "кабели и переходники", "держатели для проводов", "АВТО-разветвители", "микрофоны", "проводные", "радиомикрофоны", "все для радиосистем", "системы персонального мониторинга", "Все аудиоаксессуары", "FM-Трансмиттеры", "Наушники и усилители", "гарнитуры и аксессуары", "гарнитуры с одним наушником", "проводные", "для ПК", "для телефона", "беспроводные", "для ПК", "bluetooth", "гарнитуры с двумя наушниками", "проводные", "для ПК", "для телефона", "беспроводные", "для ПК", "bluetooth", "наушники", "проводные", "мониторные", "вставные", "накладные", "беспроводные", "мониторные", "вставные", "накладные", "амбушюры и вставки", "аксессуары для Parrot Zik", "держатели для наушников", "чехлы для наушников", "Все Наушники", "Радиоприемники", "Акустические системы", "колонки", "мегафоны", "аксессуары для Audioengine", "аксессуары для Podspeakers", "встраиваемая акустика", "для iPhone/iPad/iPod", "мини-колонки", "20", "2012015", "50", "мегафоны", "все акустические системы", "колонки", "Музыкальное оборудование & для DJ", "бас-машины", "драм-машины", "микшерные пульты", "проигрыватели", "все для микшерных пультов", "MIDI-клавиатуры", "MIDI-контроллеры", "клавишные аксессуары", "метрономы", "настольные барабаны", "портативные комбо-усилители", "проигрыватели виниловых дисков", "синтезаторы", "аксессуары для гитар", "эквалайзеры", "Все музыкальное оборудование", "Усилители стационарные и для наушников", "усилители для наушников", "усилители стационарные", "ресиверы", "CD-проигрыватели", "Аудио и видео цифровые конвертеры", "Портативные DVD плееры", "медиаплееры и Blu-ray плееры", "Blu-ray плееры", "DVD плееры", "DVD плееры портативные", "медиаплееры", "аксессуары для медиаплееров", "Все Blu-ray", "DVD", "медиаплееры и аксессуары", "Мультимедийные проекторы", "карманные", "портативные", "аксессуары для проекторов", "экраны", "Все мультимедийные проекторы", "Цифровые фоторамки", "Бинокли и оптические приборы", "бинокли", "бинокли театральные", "аксессуары для биноклей", "дальномеры", "зрительные трубы", "микроскопы", "аксессуары для микроскопов", "телескопы", "монокуляры", "оптические лупы", "приборы ночного видения", "прицелы", "Все Оптические приборы", "Автотехника", "навигаторы", "регистраторы", "детекторы", "автомобильные GPS навигаторы и аксессуары", "GPS навигаторы", "аксессуары для навигаторов", "GPS приемники", "авто эмоции", "автосвет", "галогенные лампы", "1156 (P21W)", "1157 (P21/5W)", "C10W", "C5W", "H1", "H3", "H4", "H7", "H8", "H9", "H11", "H13", "H27", "HB1 (9004)", "HB3", "HB4", "HB5 (9007)", "P21W", "T10 (W5W)", "весь ближний и дальний свет", "дневные ходовые огни", "дополнительные фары", "дальнего света", "противотуманного света", "рабочего света", "светодиодные лампы", "1156 (P21W)", "1157 (P21/5W)", "7440 (W21W)", "7443 (W21/5W)", "C5W", "H1", "H3", "H4", "H7", "H8", "H10", "H11", "H27 (880", "881)", "HB3 (9005)", "HB4 (9006)", "HB5 (9007)", "T3", "T4W (BA9S)", "T5", "T10 (W5W)", "T15 (W16W)", "T20", "подсветка автомобиля", "блоки розжига для ксенона", "антенны в авто", "бортовые компьютеры", "видеорегистраторы", "все для видеорегистраторов", "держатели очков", "зарядные устройства для автомобильных аккумуляторов", "камеры заднего вида авто", "коврики на торпедо", "крепления для камер заднего вида", "лебедки", "тали", "тельферы", "лебедки", "тали", "мониторы в авто", "в подголовниках", "на торпедо", "потолочные", "салонные зеркала", "очки водительские", "парковочные радары", "провода пусковые", "противоугонные устройства", "GPS-GSM трекеры", "маяки", "блокираторы капота", "блокираторы руля", "иммобилайзеры", "сигнализации", "аксессуары для автосигнализаций", "радар-детекторы", "аксессуары для радар-детекторов", "холодильники автомобильные", "аксессуары для автохолодильников", "цепи противоскольжения", "чехлы для хранения колес и шин", "автомобильные крепления", "стяжки грузов", "багажники на авто", "щетки", "лопаты", "скребки", "Вся Автотехника и аксессуары", "чип-тюнинг", "Светоотражатели на одежду", "Автозвук", "автомагнитолы", "05 DIN", "1 DIN", "2 DIN", "аксессуары для магнитол", "Все автомагнитолы", "акустика автомобильная", "акустика влагозащищенная", "конденсаторы", "сабвуферы автомобильные", "усилители автомобильные", "установочные комплекты для усилителей", "Автомобильные гаджеты", "Инструмент", "компрессоры", "пылесосы", "минимойки", "канистры и многое другое", "автоинверторы с 12 / 24 на 220V", "авточехлы на сиденья", "коврики в салон", "наборы инструмента", "алкотестеры", "анимированные игрушки Flip-Flap", "вешалки на подголовник", "гайковерты", "гель-масса для очистки", "датчики давления и температуры", "домкраты", "измерители", "ионизаторы в авто", "канистры", "ключи динамометрические", "компрессоры", "насосы", "компрессометры", "манометры", "паяльные лампы", "бензиновые", "газовые", "Мелкая бытовая техника в авто", "минимойки / мойки высокого давления", "накидки с вентиляцией", "органайзеры", "подогрев сидений", "пылесосы автомобильные", "тенты для авто и мото", "толщиномеры", "тросы буксировочные", "устройства громкой связи", "шторки", "Все инструменты", "компрессоры", "пылесосы", "канистры и многое другое", "Товары для здоровья и красоты", "анализаторы крови", "аппараты виброакустического воздействия", "аппараты для лечения насморка", "аппараты для электрофореза", "аппараты лазерной терапии", "аппараты магнитотерапии", "аппараты для стабилизации давления", "аппараты слуховые", "все для слуховых аппаратов", "аппараты ультразвуковые терапевтические", "аппараты физиотерапии", "аппараты фототерапии", "бытовая нехимия", "глюкометры", "дарсонвали", "дополнительные принадлежности", "для глюкометров и анализаторов крови", "для аппаратов ДиаДэнс", "для ингаляторов", "для тонометров", "жироанализаторы", "ингаляторы", "детские", "компрессорные", "паровые", "солевые", "ультразвуковые", "инфракрасные лампы", "ирригаторы", "для ирригаторов", "жидкости для ирригаторов", "кислородное оборудование", "баллончики", "коктейлеры и миксеры", "комплекты для приготовления коктейлей", "пенообразователи", "концентраторы", "косметологические зеркала", "магнитные апликаторы", "маникюрно-педикюрные наборы", "массажеры", "для головы", "глаз", "для лица", "для тела", "для шеи", "для ног", "коврики", "матрасы", "накидки на кресла", "подушки", "пояса", "Все массажеры", "массажные кресла", "массажные обручи ХулаХуп", "матрасы-кондиционеры", "медицинские термометры (градусники)", "миостимуляторы", "невотон", "ортопедические изделия", "очки для компьютера", "пульсоксиметры", "пульсометры", "сауны для лица", "солевые грелки", "термокомпрессы", "стетоскопы", "тонометры", "тренажеры дыхательные", "ультрафиолетовые облучатели / рециркуляторы", "устройства антихрап", "электроактиваторы воды", "электрокардиографы", "вся медтехника", "Все для спорта", "все для фитнеса и пилатеса", "гироциклы", "тренажеры кистевые", "шагомеры", "тренажеры", "эспандеры", "утяжелители", "Моноколеса и аксессуары", "моноколеса", "аксессуары", "джамперы", "Самокаты", "скейты", "беговелы", "беговелы", "самокаты", "взрослые", "городские", "с надувными колесами", "инерционные", "трюковые", "самокат-чемодан", "детские", "до 4-х лет", "старше 4-х лет", "инерционные", "электросамокаты", "аксессуары для самокатов", "скейты", "лонгборды", "маунтиборды", "пенниборды", "роллерсерфы", "скейтборды", "электроскейты", "охлаждающие гелевые пакеты", "Все самокаты", "Тестеры нитратов", "радона и дозиметры радиации", "Электрогрелки", "электросушилки", "водонагреватели", "теплый пол", "подогрев грунта и труб", "водонагреватели", "накопительные", "проточные", "генераторы тепла для рук и ног", "грелки от USB", "обогреватели", "инфракрасные", "керамические", "конвекторы", "масляные", "микатермические", "настенные", "пленочные", "тепловентиляторы", "тепловые пушки", "все для обогревателей", "обогреватели в автомобиль", "системы подогрева грунта", "системы подогрева труб", "электрогрелки", "доброе тепло", "электросушилки", "Все водонагреватели", "теплый пол", "подогрев грунта и труб", "электрогрелки", "электросушилки", "Мелкая бытовая техника", "вентиляторы", "весы и многое другое", "аксессуары для бытовой техники", "аэрогрили", "блендеры", "блинницы", "бутербродницы", "вакуумные контейнеры и пакеты", "вакуумные упаковщики", "вафельницы", "вентиляторы", "вытяжные", "напольные осевые", "настольные", "весы дорожные", "весы карманные", "весы кухонные", "весы напольные электронные", "воскоплавы", "вспениватели молока", "выпрямители волос", "гладильные системы", "диспенсеры", "зубные электрощетки", "принадлежности для электрощеток", "измельчители пищевых отходов", "йогуртницы", "кексницы", "кофеварки", "кофемашины", "кофемолки", "кремеры", "крышки-аэрогрили", "кувшины", "ланч-боксы", "ломтерезки", "маринаторы", "машинки для стрижки волос", "машинки для удаления катышков", "миксеры", "мороженицы", "мультиварки", "скороварки", "принадлежности для мультиварок", "мясорубки", "ножеточки", "орешницы", "пароварки", "паровые швабры", "пароочистители и отпариватели", "плиты", "продукция Dyson", "пылесосы беспроводные aккумуляторные", "пылесосы вертикальные", "пылесосы промышленные", "пылесосы-роботы и аксессуары", "сифоны для газирования воды", "соковыжималки", "стайлеры", "стеклоочистители", "сушилки для овощей", "фруктов", "грибов", "термокружки", "термопоты", "термосы", "тостеры", "триммеры женские", "триммеры мужские", "увлажнители", "очистители", "озонаторы воздуха", "ароматизаторы", "ионизаторы воздуха", "озонаторы воздуха", "очистители воздуха", "очистители и увлажнители воздуха в одном", "увлажнители воздуха", "аксессуары для климатического оборудования", "ультразвуковые очистители и машины", "утюги", "фены", "фены настенные", "фильтры для воды", "кувшины", "насадки", "предфильтры", "проточные", "сменные картриджи", "фритюрницы", "хлебопечи", "чайники", "швейные машинки", "электробигуди", "электробритвы", "принадлежности для бритв", "электровеники", "электрогрили", "электромельницы", "электроножи", "электрооткрывалки", "электроперечницы", "электросамовары", "электросушилки", "для белья", "для обуви", "для рук", "электрошашлычницы", "эпиляторы", "яйцеварки", "Вся мелкая бытовая техника", "Метеостанции цифровые и барометры", "барометры", "метеостанции", "Фильтры для воды Барьер", "Дача и сад", "воздуходувки", "все для полива", "дождеватели", "разбрызгиватели", "системы полива", "таймеры подачи воды", "Универсальные шланги", "высоторезы", "кусторезы", "насосы", "поверхностные насосы", "погружные насосы", "опрыскиватели", "парники", "Аэросады и проращиватели", "умные растения", "аэросады и проращиватели", "умные растения", "Светодиодные лампы", "встраиваемые светильники", "ленты", "прожекторы", "датчики движения", "фотосенсоры", "лампочки для солевых ламп", "холодильников", "швейных машин", "светодиодные лампы и все для них", "E14", "E27", "G4", "G9", "GX53", "GX70", "GY635", "MR11 GU4", "MR16 GU10", "MR16 GU53", "контроллеры", "Все Светодиодные лампы", "потолочные и настенные светильники", "прожекторы", "светодиодные ленты и все для них", "светодиодные ленты", "источники питания", "контроллеры", "Измерительное и диагностическое оборудование", "амперметры", "анализаторы качества воды", "анемометры", "влагомеры", "вольтметры", "газоанализаторы", "гигрометры", "детекторы проводки", "измерительные колеса / курвиметры", "измерители освещенности / люксометры", "лазерные рулетки", "логгеры данных температуры", "микрометры", "мультиметры", "нивелиры / построители плоскостей", "лазерные", "оптические", "осциллографы", "пирометры / измерители температуры", "принадлежности для нивелиров", "рулетки", "счетчики воды", "счетчики электроэнергии", "тахометры", "тепловизоры", "токоизмерительные клещи", "трассоискатели", "угломеры", "уровни", "штангенциркули", "шумомеры", "эндоскопы", "все измерительное оборудование", "Аппараты для выжигания", "Пневмоинструмент", "Телескопические лестницы", "Электроинструмент", "винтоверты", "граверы", "дрели", "шуруповерты", "аккумуляторные", "от электросети", "дрели-миксеры", "краскораспылители", "лобзики", "ножницы по металлу", "отвертки", "очки защитные", "паяльники", "принадлежности для паяльников", "перфораторы", "пилы", "бензопилы", "дисковые", "сабельные", "торцовочные", "цепные", "плиткорезы", "рубанки", "сварочные аппараты", "аксессуары для сварочного оборудования", "маски сварочные", "степлеры", "термоклеевые пистолеты", "термопистолеты", "фрезеры", "шлифовальные машины", "штроборезы", "электроточила", "аксессуары и расходные материалы для электроинструмента", "универсальные наборы и оснастка для электроинструмента", "для дрелей и шуруповертов", "для перфораторов", "Все Электроинструменты", "Роботы различные!", "массажеры", "для мойки окон", "для чистки бассейнов", "Беспроводное управление домом", "автоматика для дома и дачи", "контроллеры", "модули расширения", "исполнители", "выключатели", "диммеры", "управление жалюзи и шторами", "датчики задымления", "температуры", "движения", "открытия двери и окна", "измерения мощности и мультисенсоры", "панели управления и пульты ДУ", "системы контроля протечки воды", "сирены", "термостаты", "терморегуляторы", "контроллеры", "коллекторы и фильтры для отопления", "реле контроля напряжения", "сервоприводы", "теплый пол", "Электронная сантехника", "автоматические смывные устройства", "аксессуары для электронной сантехники", "краны мгновенного нагрева воды", "крышки-биде", "сенсорные дозаторы", "сенсорные смесители", "Вся электронная сантехника", "Электронные переводчики", "Электронные товары для животных", "GPS-трекеры", "автогамаки", "автоматические кормушки", "автоматические поилки", "автоматические туалеты", "аквариумистика", "аквариумы и креветкарии", "компрессоры", "обогреватели", "аксессуары", "игрушки", "инкубаторы", "машинки для стрижки", "ошейники для собак", "антилай", "для дрессировки", "светодиодные", "расчески для животных", "светящиеся аксессуары", "электронные ограды и заборы", "Все электронные товары для животных", "Натуральная косметика и средства гигиены", "натуральный горчичный порошок", "гели для душа", "детская серия", "зубные пасты", "мужская серия", "солевые дезодоранты", "уход за волосами", "уход за лицом", "уход за руками", "уход за телом", "Бытовая нехимия", "для стирки", "для уборки", "для мытья посуды", "Все для рыбалки", "удилища", "спиннинги телескопические", "спиннинги штекерные", "удилища болонские", "удилища карповые", "удилища кастинговые", "удилища маховые", "удилища телескопические", "удилища троллинговые", "удилища фидерные", "удилища сборные", "подставки для удилищ", "подводные видеокамеры", "всё для оснастки", "блесны", "воблеры", "грузила", "джиг-головки", "катушки", "крючки", "кормушки", "леска", "поводки", "поплавки", "противозакручиватели", "шнуры плетёные", "багорики", "подсачки", "приманки", "инструменты и подручные средства", "кораблики для прикормки", "весы", "ящики", "наборы рыбака", "садки", "сигнализаторы поклевки", "складная мебель", "спасательные жилеты", "чехлы и сумки", "эхолоты", "аксессуары для эхолотов", "Все все для рыбалки", "зимняя рыбалка", "балансиры", "блесны", "горелки", "грили-барбекю", "инструменты и подручные средства", "коробки для приманок", "ледобуры и аксессуары к ним", "леска для зимней рыбалки", "палатки для рыбалки", "поводки", "приманки и мормышки", "удочки зимние", "ящики зимние", "Все для туризма и отдыха", "аксессуары для пикника", "посуда для туризма", "наборы для пикника", "мангалы", "аксессуары для гриля", "шампуры", "решетки-гриль", "инструменты для похода", "компасы", "ножи", "лопаты", "топоры", "лампы бензиновые и газовые", "бензиновые", "газовые", "кемпинговая мебель", "гамаки", "раскладные кровати", "столы", "стулья", "шезлонги", "коврики", "палатки", "1-2-х местные", "3-х местные", "4-х местные", "5-ти местные и более", "Все палатки", "пледы", "полезные мелочи", "рюкзаки и сумки", "городские", "с колонками", "туристические", "спальники", "тенты", "походный душ", "термопродукция", "термосумки", "термоконтейнеры", "аккумуляторы холода", "шатры", "Все для пикника и туризма", "GPS туристические", "металлоискатели", "рации", "рации", "автомобильные радиостанции", "портативные", "речные", "антенны для рации", "гарнитуры для раций", "динамики для рации", "зарядки для раций", "Все рации и антенны", "металлоискатели", "наушники для металлоискателей", "аксессуары для металлоискателей", "Ручные досмотровые металлоискатели", "GPS туристические", "Фонари", "светильники", "ночники", "свечи", "настольные и солевые лампы", "светильники и ночники", "светильники садово-парковые", "На солнечных батареях", "От сети 220В", "светодиодные свечи", "солевые лампы", "настольные лампы", "Фонари", "автомобильные", "брелоки", "для дайвинга и подводной охоты", "для кемпинга", "налобные", "небесные фонарики", "прожекторы", "тактические", "подствольные", "карманные", "универсальные", "аксессуары", "Все фонари", "светильники", "ночники", "свечи", "настольные и солевые лампы", "Мультитулы", "Точила", "Надувные матрасы", "кресла", "круги", "бассейны", "батуты", "водные дорожки", "батуты", "детские бассейны", "аксессуары для бассейнов", "надувные жилеты", "игровые центры", "игрушки для плавания", "кресла", "круги", "лодки", "аксессуары для лодок", "матрасы", "кровати", "насосы для надувных изделий", "подушки", "ремкомплекты", "тюбинги / ватрушки", "Все надувные матрасы", "кресла", "круги", "бассейны", "Защита от собак", "грызунов", "насекомых", "от животных", "от грызунов", "от змей", "от птиц", "от собак", "от кротов", "от насекомых", "от клещей", "от клопов", "от комаров", "от моли", "от муравьев", "от мух", "от ос", "от слепней", "от тараканов", "Вся защита от собак", "грызунов", "насекомых", "Насосное оборудование", "поверхностные насосы", "насосные станции", "обычные", "погружные насосы", "дренажные", "колодезные", "скважинные", "Все насосное оборудование", "Велосипедные гаджеты", "GPS велосипедные", "фляги", "крепления для фляг", "светодиодная подсветка", "велофары и габариты", "грипсы", "держатели", "зеркала", "багажники", "крепления для перевозки на автомобиле", "велокомпьютеры", "велокресла / сидения", "велосумки", "замки", "шлемы", "насосы", "наборы велоинструментов", "системы хранения", "Все велосипедные гаджеты", "Гаджеты на солнечных батареях", "аккумуляторы", "солнечные панели", "зарядные устройства", "конструкторы", "Воздушные змеи и аксессуары", "воздушные змеи", "аксессуары для воздушных змеев", "Водные бластеры", "Тестирование товаров", "услуги", "подарочная упаковка", "скотч", "Альбомы для фотографий", "Воздушные шары", "Подарочная упаковка", "скотч", "Тестирование и услуги нетбуки", "ноутбуки", "Тестирование и услуги телефона", "смартфона", "Тестирование и услуги фото и видео", "Тестирование товаров и услуги", "Упаковка товаров", "Экспресс обслуживание", "Уценка товаров", "прочих товаров", "MP3 плееров", "видеорегистраторов", "вспышек", "гарнитур", "колонок", "мелкой бытовой техники", "навигаторов", "наушников", "объективов", "ноутбуков", "нетбуков", "погодных станций", "планшетов", "радар-детекторов", "роутеров", "телефонов", "смартфонов", "фотоаксессуаров", "фотоаппаратов", "цифровых фоторамок", "чехлов и сумок", "штативов", "электронных книг", "солнцезащитных очков Polaroid", "очков детских", "очков женских", "очков мужских", "очков спортивных", "коллекция Suncovers", "все уцененные товары", "Все Игровые приставки", "Все пневматическое оружие до 3 Дж", "Все страйкбольное оружие", "Товары для тестирования", "Защита личных вещей от потерь", "Курьерские сумки от PLEERRU", "Благое дело!", "Доставка - дополнительные опции" }));

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jCheckBox14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBox23)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBox25)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBox37)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jCheckBox14)
                        .addComponent(jCheckBox23)
                        .addComponent(jCheckBox25)
                        .addComponent(jCheckBox37))
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(2, 2, 2))
        );

        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder("Общие фильтры"));

        jCheckBox34.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jCheckBox34.setForeground(new java.awt.Color(51, 51, 255));
        jCheckBox34.setText("Не особые");
        jCheckBox34.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox34ActionPerformed(evt);
            }

          private void jCheckBox34ActionPerformed(ActionEvent evt) {
              throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
          }
        });

        jCheckBox16.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jCheckBox16.setForeground(new java.awt.Color(51, 51, 255));
        jCheckBox16.setText("Новинки");
        jCheckBox16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox16ActionPerformed(evt);
            }

          private void jCheckBox16ActionPerformed(ActionEvent evt) {
              throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
          }
        });

        jToggleButton12.setText("Делить по брендам");

        jButton7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton7.setForeground(new java.awt.Color(51, 51, 255));
        jButton7.setText("Добавить новый товар");
        jButton7.setMargin(new java.awt.Insets(2, 4, 2, 4));

        jCheckBox33.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jCheckBox33.setForeground(new java.awt.Color(51, 51, 255));
        jCheckBox33.setText("Особые");
        jCheckBox33.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox33ActionPerformed(evt);
            }

          private void jCheckBox33ActionPerformed(ActionEvent evt) {
              throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
          }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Показать:");

        jButton4.setText("Выбр.");

        jCheckBox35.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jCheckBox35.setForeground(new java.awt.Color(51, 51, 255));
        jCheckBox35.setText("Глоб. Откл.");
        jCheckBox35.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox35ActionPerformed(evt);
            }

          private void jCheckBox35ActionPerformed(ActionEvent evt) {
              throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
          }
        });

        jButton3.setText("Все");

        jToggleButton11.setText("Вкл. Стат. Сорт");

        jCheckBox36.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jCheckBox36.setForeground(new java.awt.Color(51, 51, 255));
        jCheckBox36.setText("Активные");
        jCheckBox36.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox36ActionPerformed(evt);
            }

          private void jCheckBox36ActionPerformed(ActionEvent evt) {
              throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
          }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jCheckBox33)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBox34)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBox36)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBox35)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBox16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3)
                .addGap(18, 18, 18)
                .addComponent(jToggleButton11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jToggleButton12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBox33)
                    .addComponent(jCheckBox34)
                    .addComponent(jCheckBox36)
                    .addComponent(jCheckBox35)
                    .addComponent(jCheckBox16)
                    .addComponent(jLabel1)
                    .addComponent(jButton3)
                    .addComponent(jButton4)
                    .addComponent(jButton7)
                    .addComponent(jToggleButton11)
                    .addComponent(jToggleButton12))
                .addGap(0, 0, 0))
        );         

    }   
    
    private JPanel createPanelRows(){
        
        jPanelRowProduct = new JPanel();
        jPanelStatSales = new JPanel();
        jlabStat7dayRetail = new JLabel();
        jlabStat7dayOpt = new JLabel();
        jlabCntPreOrder = new JLabel();
        jSeparatorStatSalesPanel = new JSeparator();
        jlabStat14dayRetail = new JLabel();
        jlabStat14dayOpt = new JLabel();
        jPanelPrices = new JPanel();
        jTextFieldPriceForDelivery = new JTextField();
        jLabMidlInPrice = new JLabel();
        jLabPriceRetail = new JLabel();
        jLabPreviewPrice = new JLabel();
        jTextFieldMinPriceForOpt = new JTextField();
        jLabMarkupPriceRetail = new JLabel();
        jTextFieldPriceRRC = new JTextField();
        jLabelDileviryPrice = new JLabel();
        jButtonCompetitionList = new JButton();
        jPanelPurchase = new JPanel();
        jButtonDileviryDatePlus1 = new JButton();
        jButtonDileviryDatePlus2 = new JButton();
        jButtonDileviryDatePlus3 = new JButton();
        jComboBoxDayDileviry = new JComboBox();
        jComboBoxMonthDileviry = new JComboBox();
        jTextFieldCntWait = new JTextField();
        jTextFieldCommentPrice = new JTextField();
        jTextFieldUpPricePreviewDileviry = new JTextField();
        jButtonDateQuery = new JButton();
        jTextFieldSecondComment = new JTextField();
        jFormattedTextFieldThirdComment = new JFormattedTextField();
        jPanelProduct = new JPanel();
        jButtonStatSale14Day = new JButton();
        jButtonHistory2 = new JButton();
        jButtonEP34 = new JButton();
        jToggleButtonPriceLook = new JToggleButton();
        jToggleButton16 = new JToggleButton();
        jToggleButton14 = new JToggleButton();
        jButtonOpenHistori = new JButton();
        jButtonLinkYA = new JButton();
        jToggleButtonLInkPlusMinus = new JToggleButton();
        jButtonStatSale28Day = new JButton();
        jFormattedTextFieldLinkNaklOrder = new JFormattedTextField();
        jFormattedTextFieldCenturyBrend = new JFormattedTextField();
        jToggleButtonPriceUpDown = new JToggleButton();
        jButton14 = new JButton();
        jButton16 = new JButton();
        jButtonMarkerBrend = new JButton();
        jButtonIdProduct = new JButton();
        jButton13 = new JButton();
        jFormattedTextFieldGarant = new JFormattedTextField();
        jToggleButtonDear = new JToggleButton();
        jButton17 = new JButton();
        jButtonRobot = new JButton();
        jFormattedTextFieldNameProduct = new JFormattedTextField();
        jButton69 = new JButton();
        jTextFieldReiting = new JTextField();
        jPanelSklad = new JPanel();
        jLabel34 = new JLabel();
        jLabel35 = new JLabel();
        jLabel36 = new JLabel();
        jLabel37 = new JLabel();
        jLabel38 = new JLabel();
        
        JPanel jPanelListProducts = new JPanel();
        
        jPanelListProducts.setBorder(javax.swing.BorderFactory.createTitledBorder("Товары"));
        jPanelListProducts.setName(""); // NOI18N
        JPanel panel = new JPanel();
        JScrollPane jScrollPane1 = new JScrollPane(panel);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));       
        jScrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
       
        
        for(Product product : productArrayList){
            
        FocusChangeListener focusChangeListener = new FocusChangeListener(product);
        
        JPanel jPanelRowProduct = new JPanel();
        
        jPanelRowProduct.setBackground(new java.awt.Color(217, 244, 217));
        jPanelRowProduct.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 204, 255), 1, true));
        jPanelRowProduct.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanelRowProduct.setMinimumSize(new java.awt.Dimension(10, 10));

        JPanel jPanelStatSales = new JPanel();
        
        jPanelStatSales.setBackground(new java.awt.Color(217, 244, 217));
        jPanelStatSales.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 204, 51), 1, true));
        
        JLabel jlabStat7dayRetail = new JLabel();

        jlabStat7dayRetail.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jlabStat7dayRetail.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlabStat7dayRetail.setText(Integer.toString(product.getSales7day()));
        jlabStat7dayRetail.setToolTipText("Продажи товара за 7 дней");
        jlabStat7dayRetail.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 204)));
        
        JLabel jlabStat7dayOpt = new JLabel();

        jlabStat7dayOpt.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jlabStat7dayOpt.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlabStat7dayOpt.setText(Integer.toString(product.getOptsales7day()));
        jlabStat7dayOpt.setToolTipText("Оптовые продажи товара за 7 дней");
        jlabStat7dayOpt.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 0, 0)));
        
        JLabel jlabCntPreOrder = new JLabel();

        jlabCntPreOrder.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jlabCntPreOrder.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlabCntPreOrder.setText("x");
        jlabCntPreOrder.setToolTipText("Количество пред заказов на товар");
        jlabCntPreOrder.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 51)));
        
        JPanel jSeparatorStatSalesPanel = new JPanel();

        jSeparatorStatSalesPanel.setForeground(new java.awt.Color(0, 0, 0));
        
        JLabel jlabStat14dayRetail = new JLabel();

        jlabStat14dayRetail.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jlabStat14dayRetail.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlabStat14dayRetail.setText(Integer.toString(product.getSales14day()));
        jlabStat14dayRetail.setToolTipText("Продажи товара за 14 дней");
        jlabStat14dayRetail.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 204)));
        
        JLabel jlabStat14dayOpt = new JLabel();

        jlabStat14dayOpt.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jlabStat14dayOpt.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlabStat14dayOpt.setText(Integer.toString(product.getOptsales14day()));
        jlabStat14dayOpt.setToolTipText("Оптовые продажи товара за 14 дней");
        jlabStat14dayOpt.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 0, 0)));
        
        

        javax.swing.GroupLayout jPanelStatSalesLayout = new javax.swing.GroupLayout(jPanelStatSales);
        jPanelStatSales.setLayout(jPanelStatSalesLayout);
        jPanelStatSalesLayout.setHorizontalGroup(
            jPanelStatSalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelStatSalesLayout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(jPanelStatSalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelStatSalesLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanelStatSalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanelStatSalesLayout.createSequentialGroup()
                                .addComponent(jlabStat7dayRetail, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(2, 2, 2)
                                .addComponent(jlabStat7dayOpt, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanelStatSalesLayout.createSequentialGroup()
                                .addComponent(jlabStat14dayRetail, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(2, 2, 2)
                                .addComponent(jlabStat14dayOpt, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanelStatSalesLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jlabCntPreOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jSeparatorStatSalesPanel))
                .addGap(2, 2, 2))
        );
        jPanelStatSalesLayout.setVerticalGroup(
            jPanelStatSalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelStatSalesLayout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(jPanelStatSalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jlabStat7dayRetail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jlabStat7dayOpt, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addComponent(jSeparatorStatSalesPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addGroup(jPanelStatSalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlabStat14dayRetail, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlabStat14dayOpt, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addComponent(jlabCntPreOrder)
                .addGap(2, 2, 2))
        );
        
        JPanel jPanelPrices = new JPanel();

        jPanelPrices.setBackground(new java.awt.Color(217, 244, 217));
        jPanelPrices.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        
        JTextField jTextFieldPriceForDelivery = new JTextField();

        jTextFieldPriceForDelivery.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextFieldPriceForDelivery.setText(Double.toString(product.getCost(4)));
        jTextFieldPriceForDelivery.setToolTipText("Текущая цена в инт маг");
        jTextFieldPriceForDelivery.setName("rubprice");
        jTextFieldPriceForDelivery.addFocusListener(focusChangeListener);
        
        JLabel jLabMidlInPrice = new JLabel();
            
        jLabMidlInPrice.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabMidlInPrice.setText(Double.toString(product.getCost(2)));
        jLabMidlInPrice.setToolTipText("Разбавленный входной ценник");
        jLabMidlInPrice.setPreferredSize(new java.awt.Dimension(44, 20));
        
        JLabel jLabPriceRetail = new JLabel();

        jLabPriceRetail.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabPriceRetail.setText(String.valueOf(product.getCost(6)));
        jLabPriceRetail.setToolTipText("Цена в розничном магазине");
        
        JLabel jLabPreviewPrice = new JLabel();

        jLabPreviewPrice.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabPreviewPrice.setText(String.valueOf(product.getCost(5)));
        jLabPreviewPrice.setToolTipText("Предыдущая цена на товар");
        jLabPreviewPrice.setPreferredSize(new java.awt.Dimension(44, 20));
        
        JTextField jTextFieldMinPriceForOpt = new JTextField();

        jTextFieldMinPriceForOpt.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextFieldMinPriceForOpt.setText("Мин_ц_опт");
        jTextFieldMinPriceForOpt.setToolTipText("Минимальная цена для продажи оптовиками (ограничитель)");
        jTextFieldMinPriceForOpt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldMinPriceForOptActionPerformed(evt);
            }

            private void jTextFieldMinPriceForOptActionPerformed(ActionEvent evt) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        
        JLabel jLabMarkupPriceRetail = new JLabel();

        jLabMarkupPriceRetail.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabMarkupPriceRetail.setText("Ц_Нац_маг");
        jLabMarkupPriceRetail.setToolTipText("Наценка розничного магазина");
        
        JTextField jTextFieldPriceRRC = new JTextField();

        jTextFieldPriceRRC.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextFieldPriceRRC.setText(String.valueOf(product.getCost(3)));
        jTextFieldPriceRRC.setToolTipText("Рекомендованная розничная цена");
        jTextFieldPriceRRC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldPriceRRCActionPerformed(evt);
            }

            private void jTextFieldPriceRRCActionPerformed(ActionEvent evt) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        
        JLabel jLabelDileviryPrice = new JLabel();

        jLabelDileviryPrice.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelDileviryPrice.setText(String.valueOf(product.getCost(7)));
        jLabelDileviryPrice.setToolTipText("Стоимость доставки товара");
        
        JButton jButtonCompetitionList = new JButton();

        jButtonCompetitionList.setFont(new java.awt.Font("Tahoma", 0, 10)); 
        jButtonCompetitionList.setText("Конкуренты");
        jButtonCompetitionList.setToolTipText(product.getUrl_conkurent());
        jButtonCompetitionList.setMargin(new java.awt.Insets(2, 1, 2, 1));
        jButtonCompetitionList.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(product.getUrl_conkurent());
            }
        });

        javax.swing.GroupLayout jPanelPricesLayout = new javax.swing.GroupLayout(jPanelPrices);
        jPanelPrices.setLayout(jPanelPricesLayout);
        jPanelPricesLayout.setHorizontalGroup(
            jPanelPricesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelPricesLayout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(jPanelPricesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelPricesLayout.createSequentialGroup()
                        .addComponent(jLabMidlInPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldPriceForDelivery, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabPriceRetail, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelPricesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanelPricesLayout.createSequentialGroup()
                            .addComponent(jTextFieldPriceRRC, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(4, 4, 4)
                            .addComponent(jButtonCompetitionList, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabelDileviryPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanelPricesLayout.createSequentialGroup()
                            .addComponent(jTextFieldMinPriceForOpt, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabPreviewPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(4, 4, 4)
                            .addComponent(jLabMarkupPriceRetail, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(3, 3, 3))
        );
        jPanelPricesLayout.setVerticalGroup(
            jPanelPricesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelPricesLayout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(jPanelPricesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldPriceForDelivery, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabMidlInPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabPriceRetail, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addGroup(jPanelPricesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldMinPriceForOpt, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabPreviewPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabMarkupPriceRetail, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addGroup(jPanelPricesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldPriceRRC, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelDileviryPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonCompetitionList, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3))
        );
        
        JPanel jPanelPurchase = new JPanel();

        jPanelPurchase.setBackground(new java.awt.Color(131, 180, 255));
        jPanelPurchase.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jPanelPurchase.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        
        JButton jButtonDileviryDatePlus1 = new JButton();

        jButtonDileviryDatePlus1.setFont(new java.awt.Font("Times New Roman", 0, 11)); // NOI18N
        jButtonDileviryDatePlus1.setText("+1");
        jButtonDileviryDatePlus1.setToolTipText("Доставка на следующий день");
        jButtonDileviryDatePlus1.setMargin(new java.awt.Insets(2, 1, 2, 1));
        
        JButton jButtonDileviryDatePlus2 = new JButton();

        jButtonDileviryDatePlus2.setFont(new java.awt.Font("Times New Roman", 0, 11)); // NOI18N
        jButtonDileviryDatePlus2.setText("+2");
        jButtonDileviryDatePlus2.setToolTipText("Доставка через день");
        jButtonDileviryDatePlus2.setMargin(new java.awt.Insets(2, 1, 2, 1));
        
        JButton jButtonDileviryDatePlus3 = new JButton();

        jButtonDileviryDatePlus3.setFont(new java.awt.Font("Times New Roman", 0, 11)); // NOI18N
        jButtonDileviryDatePlus3.setText("+3");
        jButtonDileviryDatePlus3.setToolTipText("Доставка через 2 дня");
        jButtonDileviryDatePlus3.setMargin(new java.awt.Insets(2, 1, 2, 1));
        
        JComboBox jComboBoxDayDileviry = new JComboBox();

        jComboBoxDayDileviry.setFont(new java.awt.Font("Times New Roman", 0, 11)); // NOI18N
        jComboBoxDayDileviry.setMaximumRowCount(31);
        jComboBoxDayDileviry.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        jComboBoxDayDileviry.setToolTipText("Дата поставки");
        
        JComboBox jComboBoxMonthDileviry = new JComboBox();

        jComboBoxMonthDileviry.setFont(new java.awt.Font("Times New Roman", 0, 11)); // NOI18N
        jComboBoxMonthDileviry.setMaximumRowCount(12);
        jComboBoxMonthDileviry.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
        jComboBoxMonthDileviry.setToolTipText("Месяц поставки");
        
        JTextField jTextFieldCntWait = new JTextField();

        jTextFieldCntWait.setText("x");
        jTextFieldCntWait.setToolTipText("Ожидаемое количество товара");
        
        JTextField jTextFieldCommentPrice = new JTextField();

        jTextFieldCommentPrice.setText(product.getOneComment());
        jTextFieldCommentPrice.setToolTipText("Цена по которой ожидается поставка");
        
        JTextField jTextFieldUpPricePreviewDileviry = new JTextField();

        jTextFieldUpPricePreviewDileviry.setText("повыш");
        jTextFieldUpPricePreviewDileviry.setToolTipText("Повышение закупочной цены");
        
        JButton jButtonDateQuery = new JButton();

        jButtonDateQuery.setBackground(new java.awt.Color(255, 153, 153));
        jButtonDateQuery.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButtonDateQuery.setText("x");
        jButtonDateQuery.setToolTipText("Дата последнего запроса товара");
        jButtonDateQuery.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButtonDateQuery.setMargin(new java.awt.Insets(2, 1, 2, 1));
        
        JTextField jTextFieldSecondComment = new JTextField();

        jTextFieldSecondComment.setText(product.getAdditionalComment());
        jTextFieldSecondComment.setToolTipText("Строка для комментариев");
        
        JFormattedTextField jFormattedTextFieldThirdComment = new JFormattedTextField();

        jFormattedTextFieldThirdComment.setText(product.getMoreComment());
        jFormattedTextFieldThirdComment.setToolTipText("Расширяемая строка для комментариев");
        jFormattedTextFieldThirdComment.setName("moreComment");
        jFormattedTextFieldThirdComment.addFocusListener(focusChangeListener);

        javax.swing.GroupLayout jPanelPurchaseLayout = new javax.swing.GroupLayout(jPanelPurchase);
        jPanelPurchase.setLayout(jPanelPurchaseLayout);
        jPanelPurchaseLayout.setHorizontalGroup(
            jPanelPurchaseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelPurchaseLayout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(jPanelPurchaseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanelPurchaseLayout.createSequentialGroup()
                        .addGroup(jPanelPurchaseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jButtonDateQuery, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
                            .addComponent(jTextFieldUpPricePreviewDileviry, javax.swing.GroupLayout.Alignment.LEADING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelPurchaseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextFieldSecondComment, javax.swing.GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE)
                            .addComponent(jFormattedTextFieldThirdComment)))
                    .addGroup(jPanelPurchaseLayout.createSequentialGroup()
                        .addComponent(jComboBoxDayDileviry, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addComponent(jComboBoxMonthDileviry, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addComponent(jButtonDileviryDatePlus1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)
                        .addComponent(jButtonDileviryDatePlus2)
                        .addGap(1, 1, 1)
                        .addComponent(jButtonDileviryDatePlus3)
                        .addGap(3, 3, 3)
                        .addComponent(jTextFieldCntWait, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addComponent(jTextFieldCommentPrice, javax.swing.GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE)))
                .addGap(3, 3, 3))
        );
        jPanelPurchaseLayout.setVerticalGroup(
            jPanelPurchaseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelPurchaseLayout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(jPanelPurchaseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonDileviryDatePlus1)
                    .addComponent(jButtonDileviryDatePlus2)
                    .addComponent(jButtonDileviryDatePlus3)
                    .addComponent(jComboBoxDayDileviry, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxMonthDileviry, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldCntWait, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldCommentPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(jPanelPurchaseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldUpPricePreviewDileviry, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldSecondComment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(jPanelPurchaseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonDateQuery)
                    .addComponent(jFormattedTextFieldThirdComment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3))
        );
        
        JPanel jPanelProduct = new JPanel();

        jPanelProduct.setBackground(new java.awt.Color(217, 244, 217));
        jPanelProduct.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        
        JButton jButtonStatSale14Day = new JButton();

        jButtonStatSale14Day.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        jButtonStatSale14Day.setText("14");
        jButtonStatSale14Day.setToolTipText("Продажи товара за 14 дней");
        jButtonStatSale14Day.setMargin(new java.awt.Insets(2, 4, 2, 4));
        jButtonStatSale14Day.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonStatSale14DayActionPerformed(evt);
            }

            private void jButtonStatSale14DayActionPerformed(ActionEvent evt) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });

        JButton jButtonHistory2 = new JButton();
        jButtonHistory2.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        jButtonHistory2.setText("ист.2");
        jButtonHistory2.setToolTipText(product.getHistory2_link());
        jButtonHistory2.setContentAreaFilled(false);
        jButtonHistory2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonHistory2.setMargin(new java.awt.Insets(2, 4, 2, 4));
        jButtonHistory2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(product.getHistory2_link());
            }        
            
        });
        
        JButton jButtonEP34 = new JButton();

        jButtonEP34.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bep34_1.png"))); // NOI18N
        jButtonEP34.setToolTipText("включить/отключить слежение за товаром");
        jButtonEP34.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jButtonEP34.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEP34ActionPerformed(evt);
            }

            private void jButtonEP34ActionPerformed(ActionEvent evt) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        
        JToggleButton jToggleButtonPriceLook = new JToggleButton();

        jToggleButtonPriceLook.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jToggleButtonPriceLook.setForeground(new java.awt.Color(51, 153, 0));
        jToggleButtonPriceLook.setText("2%");
        jToggleButtonPriceLook.setToolTipText("Запросить протекцию цены товара");
        jToggleButtonPriceLook.setContentAreaFilled(false);
        jToggleButtonPriceLook.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButtonPriceLook.setIconTextGap(1);
        jToggleButtonPriceLook.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jToggleButtonPriceLook.setMaximumSize(new java.awt.Dimension(29, 21));
        jToggleButtonPriceLook.setMinimumSize(new java.awt.Dimension(29, 21));
        
        JToggleButton jToggleButton16 = new JToggleButton();

        jToggleButton16.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jToggleButton16.setForeground(new java.awt.Color(51, 153, 0));
        jToggleButton16.setToolTipText("поставить/снять безплатную доставку");
        jToggleButton16.setContentAreaFilled(false);
        jToggleButton16.setMargin(new java.awt.Insets(2, 4, 2, 4));
        jToggleButton16.setMaximumSize(new java.awt.Dimension(25, 21));
        jToggleButton16.setMinimumSize(new java.awt.Dimension(25, 21));
        jToggleButton16.setPreferredSize(new java.awt.Dimension(25, 21));
        jToggleButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton16ActionPerformed(evt);
            }

            private void jToggleButton16ActionPerformed(ActionEvent evt) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        
        JToggleButton jToggleButton14 = new JToggleButton();

        jToggleButton14.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jToggleButton14.setForeground(new java.awt.Color(51, 153, 0));
        jToggleButton14.setToolTipText("поставить/снять мульти-линк в группах");
        jToggleButton14.setContentAreaFilled(false);
        jToggleButton14.setMargin(new java.awt.Insets(2, 4, 2, 4));
        jToggleButton14.setMaximumSize(new java.awt.Dimension(25, 21));
        jToggleButton14.setMinimumSize(new java.awt.Dimension(25, 21));
        jToggleButton14.setPreferredSize(new java.awt.Dimension(25, 21));
        jToggleButton14.setRequestFocusEnabled(false);
        
        JButton jButtonOpenHistori = new JButton();

        jButtonOpenHistori.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        jButtonOpenHistori.setText("история");
        jButtonOpenHistori.setToolTipText("ссылка на историю");
        jButtonOpenHistori.setContentAreaFilled(false);
        jButtonOpenHistori.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jButtonOpenHistori.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonOpenHistoriActionPerformed(evt);
            }

            private void jButtonOpenHistoriActionPerformed(ActionEvent evt) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        
        JButton jButtonLinkYA = new JButton();

        jButtonLinkYA.setFont(new java.awt.Font("Arial Narrow", 1, 11)); // NOI18N
        jButtonLinkYA.setText("link 1/1");
        jButtonLinkYA.setToolTipText("переход по линку товара (в скобках наше место на маркете)");
        jButtonLinkYA.setContentAreaFilled(false);
        jButtonLinkYA.setMargin(new java.awt.Insets(2, 1, 2, 1));
        jButtonLinkYA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLinkYAActionPerformed(evt);
            }

            private void jButtonLinkYAActionPerformed(ActionEvent evt) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        
        JToggleButton jToggleButtonLInkPlusMinus = new JToggleButton();

        jToggleButtonLInkPlusMinus.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        jToggleButtonLInkPlusMinus.setText("+");
        jToggleButtonLInkPlusMinus.setToolTipText("установить link на +/-");
        jToggleButtonLInkPlusMinus.setContentAreaFilled(false);
        jToggleButtonLInkPlusMinus.setMargin(new java.awt.Insets(2, 1, 2, 1));
        
        JButton jButtonStatSale28Day = new JButton();

        jButtonStatSale28Day.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        jButtonStatSale28Day.setText("28");
        jButtonStatSale28Day.setToolTipText("Продажи товара за 28 дней");
        jButtonStatSale28Day.setMargin(new java.awt.Insets(2, 4, 2, 4));
        jButtonStatSale28Day.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonStatSale28DayActionPerformed(evt);
            }

            private void jButtonStatSale28DayActionPerformed(ActionEvent evt) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        
        JFormattedTextField jFormattedTextFieldLinkNaklOrder = new JFormattedTextField();

        jFormattedTextFieldLinkNaklOrder.setForeground(new java.awt.Color(51, 51, 51));
        jFormattedTextFieldLinkNaklOrder.setText("Бест Диджитал Плаза Корпорейшн Накладная: 4749");
        jFormattedTextFieldLinkNaklOrder.setToolTipText("Последняя накладная по которой пришел товар");
        jFormattedTextFieldLinkNaklOrder.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jFormattedTextFieldLinkNaklOrder.setMinimumSize(new java.awt.Dimension(6, 15));
        jFormattedTextFieldLinkNaklOrder.setPreferredSize(new java.awt.Dimension(257, 16));
        jFormattedTextFieldLinkNaklOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFormattedTextFieldLinkNaklOrderActionPerformed(evt);
            }

            private void jFormattedTextFieldLinkNaklOrderActionPerformed(ActionEvent evt) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        
        JFormattedTextField jFormattedTextFieldCenturyBrend = new JFormattedTextField();

        jFormattedTextFieldCenturyBrend.setText(product.getCenturyBrend());
        jFormattedTextFieldCenturyBrend.setToolTipText("Страна производитель");
        jFormattedTextFieldCenturyBrend.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        
        JToggleButton jToggleButtonPriceUpDown = new JToggleButton();

        jToggleButtonPriceUpDown.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        jToggleButtonPriceUpDown.setText("+ наценка");
        jToggleButtonPriceUpDown.setToolTipText("включить/выключить наценку магазина");
        jToggleButtonPriceUpDown.setContentAreaFilled(false);
        jToggleButtonPriceUpDown.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jToggleButtonPriceUpDown.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButtonPriceUpDownActionPerformed(evt);
            }

            private void jToggleButtonPriceUpDownActionPerformed(ActionEvent evt) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        
        JButton jButton14 = new JButton();

        jButton14.setToolTipText("фиксация товара");
        jButton14.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jButton14.setContentAreaFilled(false);
        
        JButton jButton16 = new JButton();

        jButton16.setToolTipText("запросить товар на выведение");
        jButton16.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jButton16.setContentAreaFilled(false);
        
        JButton jButtonMarkerBrend = new JButton();

        jButtonMarkerBrend.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bepr_.png"))); // NOI18N
        jButtonMarkerBrend.setToolTipText("включить/отключить ограничение понижающего робота");
        jButtonMarkerBrend.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jButtonMarkerBrend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonMarkerBrendActionPerformed(evt);
            }

            private void jButtonMarkerBrendActionPerformed(ActionEvent evt) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        
        JButton jButtonIdProduct = new JButton();

        jButtonIdProduct.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButtonIdProduct.setForeground(new java.awt.Color(0, 51, 255));
        jButtonIdProduct.setText(String.valueOf(product.getIdProduct()));
        jButtonIdProduct.setToolTipText("Номер товара");
        jButtonIdProduct.setContentAreaFilled(false);
        jButtonIdProduct.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jButtonIdProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonIdProductActionPerformed(evt);
            }

            private void jButtonIdProductActionPerformed(ActionEvent evt) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        
        JButton jButton13 = new JButton();

        jButton13.setToolTipText("Включить/выключить товар глобально");
        jButton13.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jButton13.setContentAreaFilled(false);
        
        JFormattedTextField jFormattedTextFieldGarant = new JFormattedTextField();

        jFormattedTextFieldGarant.setText(product.getGarant());
        jFormattedTextFieldGarant.setToolTipText("Срок гарантии");
        jFormattedTextFieldGarant.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        
        JToggleButton jToggleButtonDear = new JToggleButton();

        jToggleButtonDear.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        jToggleButtonDear.setText("дорого");
        jToggleButtonDear.setToolTipText("снять/поставить отметку \"дорого\" на товар");
        jToggleButtonDear.setContentAreaFilled(false);
        jToggleButtonDear.setMargin(new java.awt.Insets(2, 1, 2, 1));
        
        JButton jButton17 = new JButton();

        jButton17.setToolTipText("Товар новинка");
        jButton17.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jButton17.setContentAreaFilled(false);
        
        JButton jButtonRobot = new JButton();

        jButtonRobot.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bep.png"))); // NOI18N
        jButtonRobot.setToolTipText("Пометить товар/бренд");
        jButtonRobot.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        
        JFormattedTextField jFormattedTextFieldNameProduct = new JFormattedTextField();

        jFormattedTextFieldNameProduct.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jFormattedTextFieldNameProduct.setText(String.valueOf(product.getName()));
        jFormattedTextFieldNameProduct.setToolTipText("Полное название товара");
        
        JButton jButton69 = new JButton(); 

        jButton69.setToolTipText("Запретить парсинг товара через yaAPI");
        jButton69.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jButton69.setContentAreaFilled(false);
        
        JTextField jTextFieldReiting = new JTextField();

        jTextFieldReiting.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jTextFieldReiting.setForeground(new java.awt.Color(70, 100, 0));
        jTextFieldReiting.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextFieldReiting.setText(product.getBonus_reiting());
        jTextFieldReiting.setToolTipText("Бонус менеджера");

        javax.swing.GroupLayout jPanelProductLayout = new javax.swing.GroupLayout(jPanelProduct);
        jPanelProduct.setLayout(jPanelProductLayout);
        jPanelProductLayout.setHorizontalGroup(
            jPanelProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelProductLayout.createSequentialGroup()
                .addGroup(jPanelProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jButtonHistory2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonOpenHistori, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(jButtonIdProduct, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE))
                    .addGroup(jPanelProductLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jTextFieldReiting, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(2, 2, 2)
                .addGroup(jPanelProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelProductLayout.createSequentialGroup()
                        .addComponent(jFormattedTextFieldLinkNaklOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonRobot)
                        .addGap(2, 2, 2)
                        .addComponent(jButtonEP34)
                        .addGap(2, 2, 2)
                        .addComponent(jButtonMarkerBrend)
                        .addGap(2, 2, 2)
                        .addComponent(jButtonStatSale14Day)
                        .addGap(2, 2, 2)
                        .addComponent(jButtonStatSale28Day))
                    .addGroup(jPanelProductLayout.createSequentialGroup()
                        .addComponent(jFormattedTextFieldGarant, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(jFormattedTextFieldCenturyBrend, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addComponent(jToggleButtonPriceUpDown, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)
                        .addComponent(jToggleButton14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)
                        .addComponent(jToggleButton16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)
                        .addComponent(jToggleButtonPriceLook, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)
                        .addComponent(jToggleButtonLInkPlusMinus)
                        .addGap(0, 0, 0)
                        .addComponent(jButtonLinkYA, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)
                        .addComponent(jToggleButtonDear, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelProductLayout.createSequentialGroup()
                        .addComponent(jFormattedTextFieldNameProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 323, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(jButton13)
                        .addGap(1, 1, 1)
                        .addComponent(jButton17)
                        .addGap(1, 1, 1)
                        .addComponent(jButton14)
                        .addGap(1, 1, 1)
                        .addComponent(jButton16)
                        .addGap(0, 0, 0)
                        .addComponent(jButton69)))
                .addGap(2, 2, 2))
        );
        jPanelProductLayout.setVerticalGroup(
            jPanelProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelProductLayout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(jPanelProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelProductLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jButtonOpenHistori, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jButtonHistory2, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jTextFieldReiting, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelProductLayout.createSequentialGroup()
                        .addGroup(jPanelProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jFormattedTextFieldNameProduct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jButtonIdProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanelProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jButton13, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jButton14, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jButton16, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jButton69, javax.swing.GroupLayout.Alignment.TRAILING))
                            .addComponent(jButton17))
                        .addGap(3, 3, 3)
                        .addGroup(jPanelProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jToggleButtonPriceLook, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanelProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jToggleButtonLInkPlusMinus, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButtonLinkYA, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jToggleButtonDear, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jToggleButtonPriceUpDown, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jToggleButton14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jToggleButton16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanelProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jFormattedTextFieldCenturyBrend, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jFormattedTextFieldGarant, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(2, 2, 2)
                        .addGroup(jPanelProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanelProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jButtonRobot, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jButtonEP34, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jButtonMarkerBrend)
                            .addGroup(jPanelProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jButtonStatSale14Day, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jButtonStatSale28Day, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanelProductLayout.createSequentialGroup()
                                .addComponent(jFormattedTextFieldLinkNaklOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(1, 1, 1)))))
                .addGap(2, 2, 2))
        );
        
        JPanel jPanelSklad = new JPanel();

        jPanelSklad.setBackground(new java.awt.Color(217, 244, 217));
        jPanelSklad.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        
        JLabel jLabel34 = new JLabel();

        jLabel34.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel34.setText(product.getCurrent_count());
        jLabel34.setToolTipText("Наличие товара на складе");
        
        JLabel jLabel35 = new JLabel();

        jLabel35.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(0, 153, 0));
        jLabel35.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel35.setText("З-" + product.getCntInOrder());
        jLabel35.setToolTipText("Количество товара находящегося в заказах");
        
        JLabel jLabel36 = new JLabel();

        jLabel36.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(0, 153, 0));
        jLabel36.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel36.setText("К-" + product.getCntInCourier());
        jLabel36.setToolTipText("Количество товара находящегося на курьерах");
        
        JLabel jLabel37 = new JLabel();

        jLabel37.setBackground(new java.awt.Color(217, 244, 217));
        jLabel37.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(0, 0, 204));
        jLabel37.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel37.setText("М-" + product.getCntInMagOrder());
        jLabel37.setToolTipText("Количество товара заказанные на розницу");
        
        JLabel jLabel38 = new JLabel();

        jLabel38.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(204, 0, 0));
        jLabel38.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel38.setText("О-" + product.getCntInOptOrder());
        jLabel38.setToolTipText("Количество товара находящегося в оптовых накладных");

        javax.swing.GroupLayout jPanelSkladLayout = new javax.swing.GroupLayout(jPanelSklad);
        jPanelSklad.setLayout(jPanelSkladLayout);
        jPanelSkladLayout.setHorizontalGroup(
            jPanelSkladLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSkladLayout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(jPanelSkladLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel35, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel36, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel38, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel37, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(2, 2, 2))
        );
        jPanelSkladLayout.setVerticalGroup(
            jPanelSkladLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSkladLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jLabel34)
                .addGap(2, 2, 2)
                .addComponent(jLabel35)
                .addGap(0, 0, 0)
                .addComponent(jLabel36)
                .addGap(0, 0, 0)
                .addComponent(jLabel38)
                .addGap(0, 0, 0)
                .addComponent(jLabel37))
        );

        javax.swing.GroupLayout jPanelRowProductLayout = new javax.swing.GroupLayout(jPanelRowProduct);
        jPanelRowProduct.setLayout(jPanelRowProductLayout);
        jPanelRowProductLayout.setHorizontalGroup(
            jPanelRowProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelRowProductLayout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(jPanelProduct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanelSklad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(jPanelStatSales, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(jPanelPrices, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(jPanelPurchase, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3))
        );
        jPanelRowProductLayout.setVerticalGroup(
            jPanelRowProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelRowProductLayout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(jPanelRowProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelProduct, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelSklad, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanelRowProductLayout.createSequentialGroup()
                        .addGroup(jPanelRowProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanelPurchase, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanelStatSales, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanelPrices, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(2, 2, 2))
        );
            
            panel.add(jPanelRowProduct);
        
            jScrollPane1.revalidate();
        }    
        
        
        //jPanelListProducts.setPreferredSize(new Dimension(1000, 500));
        jPanelListProducts.setLayout(new BoxLayout(jPanelListProducts, BoxLayout.Y_AXIS));
        jPanelListProducts.add(jScrollPane1);
        
        return jPanelListProducts;       
    }

    private void packPanelsProduct(JPanel jPanelListProducts){
        JPanel panel2 = new JPanel();
        panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));
        panel2.add(jPanel15);
        panel2.add(jPanel16);
        panel2.add(jPanel9);
        panel2.add(jPanel10);
         
         this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
         this.add(panel2);
         this.add(jPanelListProducts);
    }
    
    public void updatePanelRows(ArrayList<Product> productArrayList){
        this.remove(1);
        this.productArrayList = productArrayList;
        createPanelRows();
        this.add(createPanelRows());
    }
    
    /*public class FocusChangeListener implements FocusListener{

        @Override
        public void focusGained(FocusEvent e) {
        }

        @Override
        public void focusLost(FocusEvent e) {
            StringBuilder builder = new StringBuilder(((JTextField)e.getSource()).getName());
            String idPr = builder.substring(builder.indexOf("_") + 1);
            String nameElement = builder.substring(0, builder.indexOf("_"));
            if(nameElement.equals("rubprice")){
                builder.delete(0, builder.length());
                builder.append("pid=");
                builder.append(idPr);
                builder.append("&oldPrice=");
                builder.append("");
                builder.append("&newPrice=");
                builder.append(((JTextField)e.getSource()).getText());
                builder.append("&groupChange=0&ajax=1");
                    
                try {
                    ParsingSite.getAuthSite().sendPost("http://monitor.pleer.ru/monitor/classes/product/ajax.php", builder.toString());
                } catch (ProtocolException ex) {
                    Logger.getLogger(GUI_Products.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(GUI_Products.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }*/
    
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton69;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton70;
    private javax.swing.JButton jButton71;
    private javax.swing.JButton jButton72;
    private javax.swing.JButton jButton73;
    private javax.swing.JButton jButton74;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButtonCompetitionList;
    private javax.swing.JButton jButtonDateQuery;
    private javax.swing.JButton jButtonDileviryDatePlus1;
    private javax.swing.JButton jButtonDileviryDatePlus2;
    private javax.swing.JButton jButtonDileviryDatePlus3;
    private javax.swing.JButton jButtonEP34;
    private javax.swing.JButton jButtonHistory2;
    private javax.swing.JButton jButtonIdProduct;
    private javax.swing.JButton jButtonLinkYA;
    private javax.swing.JButton jButtonMarkerBrend;
    private javax.swing.JButton jButtonOpenHistori;
    private javax.swing.JButton jButtonRobot;
    private javax.swing.JButton jButtonStatSale14Day;
    private javax.swing.JButton jButtonStatSale28Day;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox10;
    private javax.swing.JCheckBox jCheckBox11;
    private javax.swing.JCheckBox jCheckBox12;
    private javax.swing.JCheckBox jCheckBox13;
    private javax.swing.JCheckBox jCheckBox14;
    private javax.swing.JCheckBox jCheckBox15;
    private javax.swing.JCheckBox jCheckBox16;
    private javax.swing.JCheckBox jCheckBox17;
    private javax.swing.JCheckBox jCheckBox18;
    private javax.swing.JCheckBox jCheckBox19;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JCheckBox jCheckBox20;
    private javax.swing.JCheckBox jCheckBox21;
    private javax.swing.JCheckBox jCheckBox22;
    private javax.swing.JCheckBox jCheckBox23;
    private javax.swing.JCheckBox jCheckBox24;
    private javax.swing.JCheckBox jCheckBox25;
    private javax.swing.JCheckBox jCheckBox26;
    private javax.swing.JCheckBox jCheckBox27;
    private javax.swing.JCheckBox jCheckBox28;
    private javax.swing.JCheckBox jCheckBox29;
    private javax.swing.JCheckBox jCheckBox3;
    private javax.swing.JCheckBox jCheckBox30;
    private javax.swing.JCheckBox jCheckBox31;
    private javax.swing.JCheckBox jCheckBox32;
    private javax.swing.JCheckBox jCheckBox33;
    private javax.swing.JCheckBox jCheckBox34;
    private javax.swing.JCheckBox jCheckBox35;
    private javax.swing.JCheckBox jCheckBox36;
    private javax.swing.JCheckBox jCheckBox37;
    private javax.swing.JCheckBox jCheckBox4;
    private javax.swing.JCheckBox jCheckBox5;
    private javax.swing.JCheckBox jCheckBox6;
    private javax.swing.JCheckBox jCheckBox7;
    private javax.swing.JCheckBox jCheckBox8;
    private javax.swing.JCheckBox jCheckBox9;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox10;
    private javax.swing.JComboBox jComboBox15;
    private javax.swing.JComboBox jComboBox16;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JComboBox jComboBoxDayDileviry;
    private javax.swing.JComboBox jComboBoxMonthDileviry;
    private javax.swing.JFormattedTextField jFormattedTextFieldCenturyBrend;
    private javax.swing.JFormattedTextField jFormattedTextFieldGarant;
    private javax.swing.JFormattedTextField jFormattedTextFieldLinkNaklOrder;
    private javax.swing.JFormattedTextField jFormattedTextFieldNameProduct;
    private javax.swing.JFormattedTextField jFormattedTextFieldThirdComment;
    private javax.swing.JLabel jLabMarkupPriceRetail;
    private javax.swing.JLabel jLabMidlInPrice;
    private javax.swing.JLabel jLabPreviewPrice;
    private javax.swing.JLabel jLabPriceRetail;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabelDileviryPrice;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel9;    
    private javax.swing.JPanel jPanelPrices;
    private javax.swing.JPanel jPanelProduct;
    private javax.swing.JPanel jPanelPurchase;
    private javax.swing.JPanel jPanelRowProduct;
    private javax.swing.JPanel jPanelSklad;
    private javax.swing.JPanel jPanelStatSales;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparatorStatSalesPanel;
    private javax.swing.JTextField jTextField23;
    private javax.swing.JTextField jTextField24;
    private javax.swing.JTextField jTextField25;
    private javax.swing.JTextField jTextField26;
    private javax.swing.JTextField jTextField27;
    private javax.swing.JTextField jTextField28;
    private javax.swing.JTextField jTextFieldCntWait;
    private javax.swing.JTextField jTextFieldCommentPrice;
    private javax.swing.JTextField jTextFieldMinPriceForOpt;
    private javax.swing.JTextField jTextFieldPriceForDelivery;
    private javax.swing.JTextField jTextFieldPriceRRC;
    private javax.swing.JTextField jTextFieldReiting;
    private javax.swing.JTextField jTextFieldSecondComment;
    private javax.swing.JTextField jTextFieldUpPricePreviewDileviry;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JToggleButton jToggleButton10;
    private javax.swing.JToggleButton jToggleButton11;
    private javax.swing.JToggleButton jToggleButton12;
    private javax.swing.JToggleButton jToggleButton14;
    private javax.swing.JToggleButton jToggleButton16;
    private javax.swing.JToggleButton jToggleButton2;
    private javax.swing.JToggleButton jToggleButton3;
    private javax.swing.JToggleButton jToggleButton4;
    private javax.swing.JToggleButton jToggleButton5;
    private javax.swing.JToggleButton jToggleButton6;
    private javax.swing.JToggleButton jToggleButton7;
    private javax.swing.JToggleButton jToggleButton8;
    private javax.swing.JToggleButton jToggleButton9;
    private javax.swing.JToggleButton jToggleButtonDear;
    private javax.swing.JToggleButton jToggleButtonLInkPlusMinus;
    private javax.swing.JToggleButton jToggleButtonPriceLook;
    private javax.swing.JToggleButton jToggleButtonPriceUpDown;
    private javax.swing.JLabel jlabCntPreOrder;
    private javax.swing.JLabel jlabStat14dayOpt;
    private javax.swing.JLabel jlabStat14dayRetail;
    private javax.swing.JLabel jlabStat7dayOpt;
    private javax.swing.JLabel jlabStat7dayRetail;         
}
