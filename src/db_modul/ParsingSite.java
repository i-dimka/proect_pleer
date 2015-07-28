package db_modul;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.print.DocFlavor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;

/**
 *
 * @author Дима
 */
public class ParsingSite {
    private static URL url_node;
    private static Elements treeByTag;
    private static Document page;
    private static Elements path_in_catalog;
    private static String idCatalog;
    private static String name_catalog;
    private static String idParentCatalog;
    private static DbWorker db;
    private static AuthSite authSite;
    
    public ParsingSite(){
        authSite = new AuthSite("dima", "1234");
    }
    
    public static void startParsing(){
        db = new DbWorker(); 
        try {
            /**
             * Открытие страницы или файла для парсинга
             */
            Document main_page = Jsoup.parse(new File("D:\\Работа\\Проект\\Товары2\\src\\test_html.htm"), "windows-1251");
            /*url_node = new URL("http://www.pleer.ru/");
            Document doc = Jsoup.parse(url_node,500);*/            
                        
            System.out.println(main_page.title());
            
            /*
            Получение ссылок для обработки каталога
            */
            treeByTag = main_page.getElementsByClass("text").select("td").get(1).getElementsByAttribute("href");
            treeByTag.remove(0);            
            
            for(Element node : treeByTag){
                url_node = new URL(node.attr("href"));
                page = Jsoup.parse(url_node, 1500);
                
                //ДОБАВИТЬ ПРОВЕРКУ НА СУЩЕСТВОВАНИЕ ТАБЛИЦЫ ТОВАРА!!!
                page = main_page;
                /*
                Получение кода, имени каталога, места в нем 
                */
                path_in_catalog = page.getElementsByClass("text").select("table").get(2).getElementsByTag("a");
                name_catalog = path_in_catalog.get(path_in_catalog.size()-1).text();
                idCatalog = path_in_catalog.get(path_in_catalog.size()-1).attr("href");
                idCatalog = idCatalog.substring(idCatalog.indexOf("edit=") + 5, idCatalog.indexOf('&', idCatalog.indexOf("edit=")));
            
                idParentCatalog = path_in_catalog.get(path_in_catalog.size()-2).attr("href");
                idParentCatalog = idParentCatalog.substring(idParentCatalog.indexOf("edit=") + 5, idParentCatalog.indexOf('&', idParentCatalog.indexOf("edit=")));
                
                insertNodeCatalog(idCatalog, idParentCatalog, name_catalog);
                
                /*
                Парсинг списка товаров
                */
                parseTable(page.getElementsByClass("medium").select("tr"));
                break;
            }    
        } catch (IOException ex) {
            Logger.getLogger(ParsingSite.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    /**
    * Вытаскиваем все строки из основной таблицы с товаром
    **/    
    private static int parseTable(Elements rowsByTag){
            //Поля для таблицы Products
            String idProduct;
            String history2_link;
            String bonus_reiting;//int
            String name_product;
            String garant;
            String url_product;
            String url_yamarket;
            String current_count;//int
            int cnt_in_order;
            int cnt_in_courier;
            int cnt_in_opt_order;
            int cnt_in_mag_order;
            int cnt_in_return;
            int cnt_in_reserve;
            String url_conkurent;
            
            String provider;//Поставщик - пока не нужен     
            
            String prev_custom_correct_price;//дата и автор последнего прихода          
            
            String price_last_custom;//тбл Состав прихода - Входная цена
            String date_last_custom;// Дата прихода
            
            //Статистика продаж должна вычисляться из таблиц продажи и состав продажи
            int sales7day;
            int sales14day;
            int optsales7day;
            int optsales14day;
            
            int input_price;//последняя входна цена. ??Почему отличается от цены последней поставки??
            
            int price_product;//текущая цена
            int rrc;//цена ррц
            int preview_price;//предыдущая цена - вычисляется
            int price_in_mag;//цена в магазине
            int price_dostavka;//цена за доставку
            String date_korrect_price;//дата изменения цены, автор
            
            
            int i;
            int count_rows = 0;
            
            /**
             * Перебор строк в цикле
             */            
            for(Element element : rowsByTag){
                
                idProduct = element.getElementsByTag("td").get(0).select("a").attr("name");//Код товара
                if(!idProduct.isEmpty()){
                    count_rows++; //подсчет обработанных строк таблицы
                    
                    history2_link = element.getElementsByTag("td").get(0)
                            .select("span").get(1).attr("onclick");//ист2 - необходимо обрезать лишние символы 
                    
                    bonus_reiting = element.getElementsByTag("td").get(1)
                            .select("input").attr("value");//бонус рейтинг на товар
                    
                    name_product = element.getElementsByTag("td").get(2)
                            .getElementById("name_" + idProduct).attr("value");//название товара
                    
                    garant = element.getElementsByTag("td").get(2)
                            .getElementById("garant_" + idProduct).attr("value");//гарантия на товар
                    
                    provider = element.getElementsByTag("td").get(2)
                            .select("span").get(0).textNodes().get(0).text();//поставщик последней накладной
                    
                    i = 1;
                    if(element.getElementsByTag("td").get(2).select("span").size() == 11){
                        price_last_custom = element.getElementsByTag("td").get(2)
                                .select("span").get(i-1).getElementsByTag("b").get(0).text(); //цена по последней приходной накладаной
                        
                        date_last_custom = element.getElementsByTag("td").get(2)
                                .select("span").get(i-1).getElementsByTag("b").get(1).text(); //дата последнй накладной                   
                    }
                    else {
                        price_last_custom = null;
                        date_last_custom = null;
                        i = 0;
                    }
                    url_product = element.getElementsByTag("td").get(2)
                            .select("span").get(i).getElementsByAttributeValue("target", "_blank").attr("href");//ссылка на товар на сайте
                    
                    url_yamarket = element.getElementsByTag("td").get(2)
                            .select("span").get(i).getElementsByAttributeValue("target", "yaurl").attr("href");//ссылка на товар в яндекс маркет
                    
                    current_count = element.getElementsByTag("td").get(3).text(); //количество в наличии
                    
                    
                    /*
                     * Товары находящиеся в резервах
                     */
                    
                    i = 0;
                    cnt_in_order = 0;
                    cnt_in_courier = 0;
                    cnt_in_opt_order = 0;
                    cnt_in_mag_order = 0;
                    cnt_in_return = 0;
                    cnt_in_reserve = 0;
                    int tmp;
                    
                    for(Element el : element.getElementsByTag("td").get(4).getElementsByTag("font")){   
                        tmp = Integer.parseInt(el.text().substring(2));
                        switch(el.text().charAt(0)){
                            case 'з': cnt_in_order = tmp;
                            break;
                            case 'к': cnt_in_courier = tmp;
                            break;
                            case 'o': cnt_in_opt_order = tmp;
                            break;
                            case 'в': cnt_in_return = tmp;
                            break;
                            case 'м': cnt_in_mag_order = tmp;
                            break;
                            case 'р': cnt_in_reserve = tmp;
                            break;
                        }
                    }
                    
                    /*
                    Статистика розничных и оптовых продаж за 7 и 14 дней
                    */
                    
                    String stat = element.getElementsByTag("td").get(6)
                            .textNodes().get(0).text();   
                    
                    sales7day = Integer.parseInt(
                            stat.substring(0, stat.indexOf('|') - 1));    
                    
                    optsales7day = Integer.parseInt(
                            stat.substring(stat.indexOf('|') + 2, stat.length() - 1));
                    
                    stat = element.getElementsByTag("td").get(6)
                            .textNodes().get(1).text();
                    
                    sales14day = Integer.parseInt(
                            stat.substring(1, stat.indexOf('|') - 1));    
                    
                    optsales14day = Integer.parseInt(
                            stat.substring(stat.indexOf('|') + 2, stat.length() - 1));                    
                   
                    input_price = Integer.parseInt(
                            element.getElementsByTag("td").get(7).text().replace(".", ""));//последняя входная цена
                    
                    price_product = Integer.parseInt(
                            element.getElementsByTag("td").get(8)
                                    .getElementById("rubprice_" + idProduct).attr("value"));//текущая цена
                    
                    rrc = Integer.parseInt(
                            element.getElementsByTag("td").get(8)
                                    .getElementById("rrc_" + idProduct).attr("value"));//цена РРЦ
                    
                    date_korrect_price = element.getElementsByTag("td").get(8)
                                    .getElementById("rubprice_" + idProduct).attr("title");//дата изменения цены, автор
                    
            insertRowReestrPrice("Текущая цена", price_product, 
                            getDateInCorrectString(date_korrect_price), idProduct, 
                            getAutorInCorrectString(date_korrect_price));
                    
                    preview_price = Integer.parseInt(element.getElementsByTag("td").get(8)
                                    .getElementsByTag("div").get(1).text());//предыдущая цена
                    
                    prev_custom_correct_price = element.getElementsByTag("td").get(8)
                                    .getElementsByTag("div").get(1).attr("title");//дата и автор прихода и назнчения цены
                    
            insertRowReestrPrice("Предыдущая цена", preview_price, getDateInCorrectString(prev_custom_correct_price), idProduct, 
                            getAutorInCorrectString(prev_custom_correct_price));
                    
                    url_conkurent = element.getElementsByTag("td").get(8).getElementsByAttribute("target").get(0).attr("href");
                            
                    price_in_mag = Integer.parseInt(
                            element.getElementsByTag("td").get(9).text());//цена в магазине
                    
                    price_dostavka = Integer.parseInt(
                            element.getElementsByTag("td").get(10).text());//цена за доставку  
                    
                    System.out.println(name_product);
            insertRowProduct(idProduct, history2_link, bonus_reiting, 
                                    name_product, garant, url_product, 
                                    url_yamarket, current_count, cnt_in_order, 
                                    cnt_in_courier, cnt_in_opt_order, cnt_in_mag_order, 
                                    cnt_in_return, cnt_in_reserve, Integer.parseInt(idCatalog), 0, url_conkurent);
                    
                    
                }
            }
            return count_rows;
    }
    
    /**
     * Добавление узла каталога в БД
     * 
     * @param idCatalog
     * @param idParentCatalog
     * @param name 
     */
    private static void insertNodeCatalog(String idCatalog, String idParentCatalog, String name){
        db.insertRowCatalog(Integer.parseInt(idCatalog), Integer.parseInt(idParentCatalog), name);
    }
    
    /**
     * Добавление строки товара в БД
     * 
     * @param idProduct
     * @param history2_link
     * @param bonus_reiting
     * @param name_product
     * @param garant
     * @param url_product
     * @param url_yamarket
     * @param current_count
     * @param cnt_in_order
     * @param cnt_in_courier
     * @param cnt_in_opt_order
     * @param cnt_in_mag_order
     * @param cnt_in_return
     * @param cnt_in_reserve
     * @param idCatalog
     * @param idBrend 
     */
    private static void insertRowProduct(String idProduct, String history2_link, String bonus_reiting, 
                                         String name_product, String garant, String url_product,
                                        String url_yamarket, String current_count, int cnt_in_order,
                                        int cnt_in_courier, int cnt_in_opt_order, int cnt_in_mag_order, 
                                        int cnt_in_return, int cnt_in_reserve, int idCatalog, int idBrend, String url_conkurent){
        
        db.insertRowProduct(idProduct, history2_link, bonus_reiting, 
                name_product, garant, url_product, 
                url_yamarket, current_count, cnt_in_order, 
                cnt_in_courier, cnt_in_opt_order, cnt_in_mag_order, 
                cnt_in_return, cnt_in_reserve, idCatalog, idBrend, url_conkurent);
    }
    
    private static void insertRowReestrPrice(String name, double valuePrice, String datetime, String idProduct, String autor){
         SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
         try{
             Date date;
             if(datetime.equals(""))date = new Date();
             else date = formatter.parse(datetime);
             db.insertRowReestrPrice(name, valuePrice, date, idProduct, autor);
         }catch(ParseException e){
             e.printStackTrace();
         }
    }
    
    private static String getDateInCorrectString(String str){
        if(str.length() < 20) return "";
        return str.substring(0, 20);
    }
    
    private static String getAutorInCorrectString(String str){
        if(str.length() < 20) return "";
        return str.substring(str.indexOf("р.") + 3);
    }
}
