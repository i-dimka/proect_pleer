/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package products_modul;

import db_modul.DbWorker;
import java.util.ArrayList;

/**
 *
 * @author Дима
 */
public class CatalogNode {
    private String name;
    private int idCatalog;
    private int idParent;
    private String href;
    private ArrayList<CatalogNode> childrensList;
    private static DbWorker db;
    
    public CatalogNode(int id, int idParent, String name, String href){
        this.name = name;
        this.idCatalog = id;
        this.idParent = idParent;
        this.href = href;       
        if(db == null){
            db = new DbWorker(false);
        }
        initChildrensList();        
    }
    
    public static CatalogNode getRootNode(int idRootCatalog){
        if(db == null){
            db = new DbWorker(false);
        }
        return db.selectCatalogNode(idRootCatalog);
    }
    
    
    public String toString(){
        return name;
    }

    public int getIdCatalog() {
        return idCatalog;
    }

    public int getIdParent() {
        return idParent;
    }

    public String getHref() {
        return href;
    }

    public ArrayList<CatalogNode> getChildrensList() {
        return childrensList;
    }
    
    public boolean isExistsChildrens(){
        return childrensList.isEmpty();
    }
    
    private void initChildrensList(){
        childrensList = db.selectChildrensCatalogNode(idCatalog);
    }
}
