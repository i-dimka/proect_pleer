package products_modul;

import db_modul.DbWorker;
import java.util.ArrayList;

/**
 *
 * @author Дима
 */
public class Product {
    
    private int idProduct;//Код товара
    private int idCatalog;//Код каталога
    private int idBrend;//Код бренда
    private int cntInOrder;//Кол-во в заказах
    private int cntInCourier;//Кол-во в курьерах
    private int cntInOptOrder;//Кол-во в опт заказах
    private int cntInMagOrder;//Кол-во в маг розн
    private int cnt_in_return;//Кол-во возврата
    private int cnt_in_reserve;//Кол-во в резерве
    private String url_conkurent;
    private String name;//Название товара
    private String history2_link;//
    private String bonus_reiting;//int
    private String garant;
    private String url_product;
    private String url_yamarket;
    private String current_count;//int
    private String provider;
    private String oneComment;
    private String additionalComment;
    private String moreComment;
    private ArrayList<Cost> pricesList;
    private int sales7day;
    private int sales14day;
    private int optsales7day;
    private int optsales14day;

    public Product() {
        pricesList = new ArrayList<Cost>();
    }

    public int getIdProduct() {
        return idProduct;
    }

    public ArrayList<Cost> getPricesList() {
        return pricesList;
    }

    public void addCost(int num, String name, double valuePrice, String datetime, String autor){
        pricesList.add(new Cost(num, name, valuePrice, datetime, idProduct, autor));
    }
    
    public void saveCostsInDb(DbWorker db){
        for(Cost cost : pricesList){
            cost.saveInDb(db);
        }
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public int getIdCatalog() {
        return idCatalog;
    }

    public void setIdCatalog(int idCatalog) {
        this.idCatalog = idCatalog;
    }

    public int getIdBrend() {
        return idBrend;
    }

    public void setIdBrend(int idBrend) {
        this.idBrend = idBrend;
    }

    public int getCntInOrder() {
        return cntInOrder;
    }

    public void setCntInOrder(int cntInOrder) {
        this.cntInOrder = cntInOrder;
    }

    public int getCntInCourier() {
        return cntInCourier;
    }

    public void setCntInCourier(int cntInCourier) {
        this.cntInCourier = cntInCourier;
    }

    public int getCntInOptOrder() {
        return cntInOptOrder;
    }

    public void setCntInOptOrder(int cntInOptOrder) {
        this.cntInOptOrder = cntInOptOrder;
    }

    public int getCntInMagOrder() {
        return cntInMagOrder;
    }

    public void setCntInMagOrder(int cntInMagOrder) {
        this.cntInMagOrder = cntInMagOrder;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }   
        
    public String getCenturyBrend(){
        return String.valueOf(idBrend);
    }

    public int getCnt_in_return() {
        return cnt_in_return;
    }

    public void setCnt_in_return(int cnt_in_return) {
        this.cnt_in_return = cnt_in_return;
    }

    public int getCnt_in_reserve() {
        return cnt_in_reserve;
    }

    public void setCnt_in_reserve(int cnt_in_reserve) {
        this.cnt_in_reserve = cnt_in_reserve;
    }

    public String getUrl_conkurent() {
        return url_conkurent;
    }

    public void setUrl_conkurent(String url_conkurent) {
        this.url_conkurent = url_conkurent;
    }

    public String getHistory2_link() {
        return history2_link;
    }

    public void setHistory2_link(String history2_link) {
        this.history2_link = history2_link;
    }

    public String getBonus_reiting() {
        return bonus_reiting;
    }

    public void setBonus_reiting(String bonus_reiting) {
        this.bonus_reiting = bonus_reiting;
    }

    public String getGarant() {
        return garant;
    }

    public void setGarant(String garant) {
        this.garant = garant;
    }

    public String getUrl_product() {
        return url_product;
    }

    public void setUrl_product(String url_product) {
        this.url_product = url_product;
    }

    public String getUrl_yamarket() {
        return url_yamarket;
    }

    public void setUrl_yamarket(String url_yamarket) {
        this.url_yamarket = url_yamarket;
    }

    public String getCurrent_count() {
        return current_count;
    }

    public void setCurrent_count(String current_count) {
        this.current_count = current_count;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }
    
    /**
     * Получение значенния цены по его номерации
     * @param num
     * @return 
     */
    public double getCost(int num){
        for(Cost cost : pricesList){
            if(cost.getNum() == num)
                return cost.getValuePrice();
        }
        return 0;
    }    

    public int getSales7day() {
        return sales7day;
    }

    public void setSales7day(int sales7day) {
        this.sales7day = sales7day;
    }

    public int getSales14day() {
        return sales14day;
    }

    public void setSales14day(int sales14day) {
        this.sales14day = sales14day;
    }

    public int getOptsales7day() {
        return optsales7day;
    }

    public void setOptsales7day(int optsales7day) {
        this.optsales7day = optsales7day;
    }

    public int getOptsales14day() {
        return optsales14day;
    }

    public void setOptsales14day(int optsales14day) {
        this.optsales14day = optsales14day;
    }

    public String getOneComment() {
        return oneComment;
    }

    public void setOneComment(String oneComment) {
        this.oneComment = oneComment;
    }

    public String getAdditionalComment() {
        return additionalComment;
    }

    public void setAdditionalComment(String additionalComment) {
        this.additionalComment = additionalComment;
    }

    public String getMoreComment() {
        return moreComment;
    }

    public void setMoreComment(String moreComment) {
        this.moreComment = moreComment;
    }
    
    
}
