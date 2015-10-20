package products_modul;

import db_modul.DbWorker;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Дима
 */
public class Cost {
    private String name;
    private double valuePrice;
    private Date date;
    private int idProduct;
    private String autor;
    private int num;

    public Cost(int num, String name, double valuePrice, String datetime, int idProduct, String autor) {
        this.name = name;
        this.valuePrice = valuePrice;
        this.idProduct = idProduct;
        this.autor = autor;
        this.num = num;
        setDate(datetime);
    }    

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    } 
    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getValuePrice() {
        return valuePrice;
    }

    public void setValuePrice(double valuePrice) {
        this.valuePrice = valuePrice;
    }

    public Date getDate() {
        return date;
    }

    private void setDate(String datetime) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
         try{
             if(datetime.equals(""))date = new Date();
             else {
                if(datetime.length() == 10)
                     formatter = new SimpleDateFormat("yyyy-MM-dd");
                date = formatter.parse(datetime);
             }
             //db.insertRowReestrPrice(name, valuePrice, date, idProduct, autor);
         }catch(ParseException e){
             e.printStackTrace();
         }
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }
    
    public void saveInDb(DbWorker db){
        db.insertRowReestrPrice(name, valuePrice, date, Integer.toString(idProduct), autor);
    }    
}
