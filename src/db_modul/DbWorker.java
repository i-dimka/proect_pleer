package db_modul;

import com.mysql.fabric.jdbc.FabricMySQLDriver;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import products_modul.Product;

/**
 *
 * @author Дима
 */
public class DbWorker {
    
    private static final String URL = "jdbc:mysql://localhost:3306/db_temp";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    
    
    private static final String COLUMN_ID_PRODUCT = "id_product";
    private static final String COLUMN_ID_BREND = "id_brend";
    private static final String COLUMN_ID_CATALOG = "id_catalog";
    private static final String COLUMN_ID_HISTORY = "id_history";
    private static final String COLUMN_ID_PROVIDER = "id_provider";
    private static final String COLUMN_ID_PRICE = "id_price";
    private static final String COLUMN_ID_COMING = "id_coming";
    private static final String COLUMN_ID_WORKER = "id_worker";
    private static final String COLUMN_ID_QUERY = "id_query";
    private static final String COLUMN_ID_QUERY_ROW = "id_row";
    private static final String COLUMN_ID_COMING_ROW = "id_row";
    private static final String COLUMN_ID_ORDER = "id_order";
    private static final String COLUMN_ID_KLIENT = "id_klient";
    private static final String COLUMN_ID_ORDER_ROW = "id_row";
    private static final String COLUMN_ID_SALE = "id_sale";
    private static final String COLUMN_ID_SALE_ROW = "id_row";
    private static final String COLUMN_ID_CONFIG = "id_config";
    private static final String COLUMN_ID_REESTR_PRICE = "id_price";
    
    
    private static final String TABLE_PRODUCTS = "tbl_products";
    private static final String COLUMN_NAME_PRODUCT = "name";
    private static final String COLUMN_IN_ORDER_CNT = "in_order_cnt";
    private static final String COLUMN_IN_COURIER_CNT = "in_courier_cnt";
    private static final String COLUMN_IN_OPT_ORDER_CNT = "in_opt_order_cnt";
    private static final String COLUMN_IN_MAG_ORDER_CNT = "in_mag_order_cnt";
    
    private static final String CREATE_TABLE_PRODUCTS = "CREATE TABLE IF NOT EXISTS " + TABLE_PRODUCTS +
            "( " + COLUMN_ID_PRODUCT + " int(10) NOT NULL PRIMARY KEY, " +
            COLUMN_NAME_PRODUCT + " varchar(255), " +
            COLUMN_IN_ORDER_CNT + " int(10), " +
            COLUMN_IN_COURIER_CNT + " int(10), " +
            COLUMN_IN_OPT_ORDER_CNT + " int(10), " +
            COLUMN_IN_MAG_ORDER_CNT + " int(10), " +
            COLUMN_ID_CATALOG + " int(10), " +
            COLUMN_ID_BREND + " int(10))";
    
    
    private static final String TABLE_REESTR_PRICES = "tbl_reestr_prices";
    private static final String COLUMN_NAME_PRICE = "name";
    private static final String COLUMN_DATE_UPDATE_PRICE = "updated";
    
    private static final String CREATE_TABLE_REESTR_PRICES = "CREATE TABLE IF NOT EXISTS " + TABLE_REESTR_PRICES +
            "( " + COLUMN_ID_REESTR_PRICE + " int(10) NOT NULL PRIMARY KEY AUTO_INCREMENT, " +
            COLUMN_NAME_PRICE + " varchar(255), " + 
            COLUMN_ID_PRODUCT + " int(10), " +
            COLUMN_DATE_UPDATE_PRICE + " datetime)";
    
    
    private static final String TABLE_BRENDS = "tbl_brends";
    private static final String COLUMN_NAME_BREND = "name";
    
    private static final String CREATE_TABLE_BRENDS = "CREATE TABLE IF NOT EXISTS " + TABLE_BRENDS +
            "( " + COLUMN_ID_BREND + " int(10) NOT NULL PRIMARY KEY AUTO_INCREMENT, " +
            COLUMN_NAME_BREND + " varchar(255))";
    
    
    private static final String TABLE_CATALOGS = "tbl_catalogs";
    private static final String COLUMN_PARENT = "parent";
    private static final String COLUMN_CATALOG_NAME = "name";
    
    private static final String CREATE_TABLE_CATALOGS = "CREATE TABLE IF NOT EXISTS " + TABLE_CATALOGS +
            "( " + COLUMN_ID_CATALOG + " int(10) NOT NULL PRIMARY KEY AUTO_INCREMENT, " +
            COLUMN_PARENT + " int(10), " + 
            COLUMN_CATALOG_NAME + " varchar(255))";
    
    
    private static final String TABLE_HISTORY = "tbl_history";
    private static final String COLUMN_HISTORY_DATE = "date";
    
    private static final String CREATE_TABLE_HISORY = "CREATE TABLE IF NOT EXISTS " + TABLE_HISTORY +
            "( " + COLUMN_ID_HISTORY + " int(10) NOT NULL PRIMARY KEY AUTO_INCREMENT, " +
            COLUMN_ID_PRODUCT + " int(10), " + 
            COLUMN_HISTORY_DATE + " datetime)";
    
    
    private static final String TABLE_PROVIDERS = "tbl_providers";
    private static final String COLUMN_PROVIDER_SITY = "sity";
    private static final String COLUMN_PROVIDER_NAME = "name";
    
    private static final String CREATE_TABLE_PROVIDERS = "CREATE TABLE IF NOT EXISTS " + TABLE_PROVIDERS +
            "( " + COLUMN_ID_PROVIDER + " int(10) NOT NULL PRIMARY KEY AUTO_INCREMENT, " +
            COLUMN_PROVIDER_SITY + " varchar(255), " + 
            COLUMN_PROVIDER_NAME + " varchar(255))";
     
     
    private static final String TABLE_PRICES = "tbl_prices";
    private static final String COLUMN_URL_PRICE = "url";
     
    private static final String CREATE_TABLE_PRICES = "CREATE TABLE IF NOT EXISTS " + TABLE_PRICES +
            "( " + COLUMN_ID_PRICE + " int(10) NOT NULL PRIMARY KEY AUTO_INCREMENT, " +
            COLUMN_ID_PROVIDER + " int(10), " + 
            COLUMN_URL_PRICE + " varchar(255))"; 
     
     
    private static final String TABLE_COMING = "tbl_coming";
    private static final String COLUMN_COMING_DATE = "date";
    
    private static final String CREATE_TABLE_COMING = "CREATE TABLE IF NOT EXISTS " + TABLE_COMING +
            "( " + COLUMN_ID_COMING + " int(10) NOT NULL PRIMARY KEY AUTO_INCREMENT, " +
            COLUMN_ID_PROVIDER + " int(10), " + 
            COLUMN_ID_WORKER + " int(10), " +
            COLUMN_COMING_DATE + " datetime)"; 
    
    
    private static final String TABLE_COMING_ROWS = "tbl_coming_rows";
    
    private static final String CREATE_TABLE_COMING_ROWS = "CREATE TABLE IF NOT EXISTS " + TABLE_COMING_ROWS +
            "( " + COLUMN_ID_COMING_ROW + " int(10) NOT NULL PRIMARY KEY AUTO_INCREMENT, " +
            COLUMN_ID_COMING + " int(10), " + 
            COLUMN_ID_PRODUCT + " int(10))"; 
    
    
    private static final String TABLE_WORKERS = "tbl_workers";
    private static final String COLUMN_WORKER_FIRSTNAME = "firstname";
    private static final String COLUMN_WORKER_POST = "post";
    
    private static final String CREATE_TABLE_WORKERS = "CREATE TABLE IF NOT EXISTS " + TABLE_WORKERS +
            "( " + COLUMN_ID_WORKER + " int(10) NOT NULL PRIMARY KEY AUTO_INCREMENT, " +
            COLUMN_WORKER_FIRSTNAME + " varchar(255), " + 
            COLUMN_WORKER_POST + " varchar(255))"; 
   
    
    private static final String TABLE_QUERYS_FOR_PROVIDER = "tbl_querys";
    
    private static final String CREATE_TABLE_QUERYS_FOR_PROVIDER = "CREATE TABLE IF NOT EXISTS " + TABLE_QUERYS_FOR_PROVIDER +
            "( " + COLUMN_ID_QUERY + " int(10) NOT NULL PRIMARY KEY AUTO_INCREMENT, " +
            COLUMN_ID_PROVIDER + " int(10), " + 
            COLUMN_ID_PRICE + " int(10))"; 
    
    
    private static final String TABLE_QUERY_ROWS = "tbl_query_rows";
    
    private static final String CREATE_TABLE_QUERY_ROWS = "CREATE TABLE IF NOT EXISTS " + TABLE_QUERY_ROWS +
            "( " + COLUMN_ID_QUERY_ROW + " int(10) NOT NULL PRIMARY KEY AUTO_INCREMENT, " +
            COLUMN_ID_QUERY + " int(10), " + 
            COLUMN_ID_PRODUCT + " int(10))"; 
    
    
    private static final String TABLE_ORDERS = "tbl_orders";
    private static final String COLUMN_ORDER_DATE = "date";
    
    private static final String CREATE_TABLE_ORDERS = "CREATE TABLE IF NOT EXISTS " + TABLE_ORDERS +
            "( " + COLUMN_ID_ORDER + " int(10) NOT NULL PRIMARY KEY AUTO_INCREMENT, " +
            COLUMN_ID_KLIENT + " int(10), " + 
            COLUMN_ORDER_DATE + " datetime)"; 
    
    
    private static final String TABLE_ORDER_ROWS = "tbl_order_rows";
    
    private static final String CREATE_TABLE_ORDER_ROWS = "CREATE TABLE IF NOT EXISTS " + TABLE_ORDER_ROWS +
            "( " + COLUMN_ID_ORDER_ROW + " int(10) NOT NULL PRIMARY KEY AUTO_INCREMENT, " +
            COLUMN_ID_ORDER + " int(10), " + 
            COLUMN_ID_PRODUCT + " int(10))"; 
    
    
    private static final String TABLE_KLIENTS = "tbl_klients";
    private static final String COLUMN_KLIENT_FIRSTNAME = "firstname";
    private static final String COLUMN_KLIENT_TELEPHON = "telephon";
    
    private static final String CREATE_TABLE_KLIENTS = "CREATE TABLE IF NOT EXISTS " + TABLE_KLIENTS +
            "( " + COLUMN_ID_KLIENT + " int(10) NOT NULL PRIMARY KEY AUTO_INCREMENT, " +
            COLUMN_KLIENT_FIRSTNAME + " varchar(255), " + 
            COLUMN_KLIENT_TELEPHON + " varchar(15))"; 
    
    
    private static final String TABLE_SALES = "tbl_sales";
    private static final String COLUMN_SALE_DATE = "date";
    
    private static final String CREATE_TABLE_SALES = "CREATE TABLE IF NOT EXISTS " + TABLE_SALES +
            "( " + COLUMN_ID_SALE + " int(10) NOT NULL PRIMARY KEY AUTO_INCREMENT, " +
            COLUMN_ID_KLIENT + " int(10), " + 
            COLUMN_SALE_DATE + " datetime)";
    
    
    private static final String TABLE_SALE_ROWS = "tbl_sale_rows";
    
    private static final String CREATE_TABLE_SALE_ROWS = "CREATE TABLE IF NOT EXISTS " + TABLE_SALE_ROWS +
            "( " + COLUMN_ID_SALE_ROW + " int(10) NOT NULL PRIMARY KEY AUTO_INCREMENT, " +
            COLUMN_ID_SALE + " int(10), " + 
            COLUMN_ID_PRODUCT + " int(10))";
    
    
    private static final String TABLE_CONFIG_MARKETING = "tbl_marketing";
    private static final String COLUMN_CONFIG_NAME = "name";
    private static final String COLUMN_CONFIG_VALUE = "value";
    
    private static final String CREATE_TABLE_CONFIG_MARKETING = "CREATE TABLE IF NOT EXISTS " + TABLE_CONFIG_MARKETING +
            "( " + COLUMN_ID_CONFIG + " int(10) NOT NULL PRIMARY KEY AUTO_INCREMENT, " +
            COLUMN_CONFIG_NAME + " varchar(255), " + 
            COLUMN_CONFIG_VALUE + " int(10))";
    
    
    
    private static Connection connection;
    
    public DbWorker(){
        this.dbConnect();
        this.createDB();
    }
    
    /**
     *Соединение с серверов MySQL
     */
    public static void dbConnect(){
        try{
            Driver driver = new FabricMySQLDriver();
            DriverManager.registerDriver(driver);
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }  
    
    /**
     * Завершение соединения с сервером MySQL
     */
    public static void dbDisconnect(){
        try{
        if(connection != null)
            connection.close();
        }catch (SQLException e){
                e.printStackTrace();
        }
    }
    
    /**
     * Создаются таблицы в базе данных, если ранее не созданы
     */
    public static void createDB(){
        try{
           if(!connection.isClosed()){
            Statement statement = connection.createStatement();
            
            statement.addBatch(CREATE_TABLE_BRENDS);
            statement.addBatch(CREATE_TABLE_CATALOGS);
            statement.addBatch(CREATE_TABLE_COMING);
            statement.addBatch(CREATE_TABLE_COMING_ROWS);
            statement.addBatch(CREATE_TABLE_CONFIG_MARKETING);
            statement.addBatch(CREATE_TABLE_HISORY);
            statement.addBatch(CREATE_TABLE_KLIENTS);
            statement.addBatch(CREATE_TABLE_ORDERS);
            statement.addBatch(CREATE_TABLE_ORDER_ROWS);
            statement.addBatch(CREATE_TABLE_PRICES);
            statement.addBatch(CREATE_TABLE_PRODUCTS);
            statement.addBatch(CREATE_TABLE_PROVIDERS);
            statement.addBatch(CREATE_TABLE_QUERYS_FOR_PROVIDER);
            statement.addBatch(CREATE_TABLE_QUERY_ROWS);
            statement.addBatch(CREATE_TABLE_SALES);
            statement.addBatch(CREATE_TABLE_SALE_ROWS);
            statement.addBatch(CREATE_TABLE_WORKERS);
            statement.addBatch(CREATE_TABLE_REESTR_PRICES);
            
            statement.executeBatch();            
           }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    
    /**
     * Формирование списка всех товаров
     * @return ArrayList
     */
    public ArrayList<Product> selectAllProducts(){        
        String SELECT_PRODUCTS = "SELECT * FROM " + TABLE_PRODUCTS;
        ArrayList<Product> productArrayList = null;
        try {       
            Statement statement = connection.createStatement();
            ResultSet res = statement.executeQuery(SELECT_PRODUCTS);
            productArrayList = new ArrayList<Product>();
            if(res.first()){
                do{
                    productArrayList.add(new Product(res.getInt(COLUMN_ID_PRODUCT), 
                            res.getInt(COLUMN_ID_CATALOG), 
                            res.getInt(COLUMN_ID_BREND), 
                            res.getInt(COLUMN_IN_ORDER_CNT), 
                            res.getInt(COLUMN_IN_COURIER_CNT), 
                            res.getInt(COLUMN_IN_OPT_ORDER_CNT), 
                            res.getInt(COLUMN_IN_MAG_ORDER_CNT), 
                            res.getString(COLUMN_NAME_PRICE)));
                }while(res.next());
            }
        } catch (SQLException ex) {
            Logger.getLogger(DbWorker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return productArrayList;
    }

   
}
