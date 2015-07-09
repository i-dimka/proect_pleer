package products_modul;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Дима
 */
public class Product {
    
    private int idProduct;
    private int idCatalog;
    private int idBrend;
    private int cntInOrder;
    private int cntInCourier;
    private int cntInOptOrder;
    private int cntInMagOrder;
    private String name;

    public Product(int idProduct, int idCatalog, int idBrend, int cntInOrder, int cntInCourier, int cntInOptOrder, int cntInMagOrder, String name) {
        this.idProduct = idProduct;
        this.idCatalog = idCatalog;
        this.idBrend = idBrend;
        this.cntInOrder = cntInOrder;
        this.cntInCourier = cntInCourier;
        this.cntInOptOrder = cntInOptOrder;
        this.cntInMagOrder = cntInMagOrder;
        this.name = name;
    }

    public int getIdProduct() {
        return idProduct;
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
}
