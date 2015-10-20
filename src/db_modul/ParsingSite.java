package db_modul;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.print.DocFlavor;
import javax.swing.JOptionPane;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;
import products_modul.Product;

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
    private static String hrefCatalog;
    private static DbWorker db;
    private static AuthSite authSite;
    private static Document main_page;
    
        
    public void authorisation(){
        String login = JOptionPane.showInputDialog("Введите ЛОГИН");
        String password = JOptionPane.showInputDialog("Введите ПАРОЛЬ");
        authSite = new AuthSite(login, password);
    }

    public void setDb(DbWorker db) {
        ParsingSite.db = db;
    }    

    public static AuthSite getAuthSite() {
        return authSite;
    }  
    
    public ArrayList<Product> startParsing(int idCatalog, String href){             
        ArrayList<Product> productArrayList = new ArrayList<Product>();        
        try {
            String response = authSite.getPageContent(href);
            System.out.println(href);
            main_page = Jsoup.parse(response, "windows-1251");
            //main_page = Jsoup.parse(new File("C:\\Users\\Дима\\Desktop\\milo.htm"), "windows-1251");
            System.out.println(main_page.title());                            
            /*
            Парсинг списка товаров
            */            
            parseTable(main_page.getElementsByClass("medium").select("tr"), idCatalog, productArrayList);  
            
        } catch (ProtocolException ex) {
            Logger.getLogger(ParsingSite.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ParsingSite.class.getName()).log(Level.SEVERE, null, ex);
        }
        return productArrayList;        
    }
    
    /**
     * Парсинг локального файла
     * @param idCatalog
     * @return 
     */
    public ArrayList<Product> startParsingFile() {
        ArrayList<Product> productArrayList = new ArrayList<Product>();        
        try {
            main_page = Jsoup.parse(new File("C:\\Users\\Дима\\Desktop\\milo.htm"), "windows-1251");
            System.out.println(main_page.title());                            
            /*
            Парсинг списка товаров
            */            
            parseTable(main_page.getElementsByClass("medium").select("tr"), 3496, productArrayList);  
            
        } catch (ProtocolException ex) {
            Logger.getLogger(ParsingSite.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ParsingSite.class.getName()).log(Level.SEVERE, null, ex);
        }
        return productArrayList;   
    }
    
    /**
     * Парсинг дерева каталога
     */
    public static void parseTreeCatalog(boolean isParsingFile){
            String response;
        try {
            if(!isParsingFile){
                response = authSite.getPageContent("http://monitor.pleer.ru/monitor/?menu=products");        
                main_page = Jsoup.parse(response, "windows-1251");
            } else {
                main_page = Jsoup.parse(new File("C:\\Users\\Дима\\Desktop\\milo.htm"), "windows-1251");
            }
            } catch (ProtocolException ex) {
            Logger.getLogger(ParsingSite.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ParsingSite.class.getName()).log(Level.SEVERE, null, ex);
        }
            Elements list = main_page.getElementsByClass("text").select("td").get(1).children();
            Pattern pat = Pattern.compile("\u00a0\\.");
            Matcher mat;
            int i = 0;
            int depthRoot = 1;
            insertNodeCatalog("1", "0", "Главное меню", "pleer.ru");
            
            Stack<Integer> stackParent = new Stack<Integer>();
            stackParent.push(1);
            
            for(Element el : list){                
                for(Node node : el.childNodes()){
                if(node instanceof TextNode){
                   mat = pat.matcher(((TextNode)node).text());                   
                   if(mat.find()){
                       i = 0;
                       mat.reset();
                   }
                   while(mat.find()){
                       i++;
                   }
                   
                }
                else {
                    if(!node.attr("title").equals("")){
                       if(i > depthRoot){
                            depthRoot = i;
                            idParentCatalog = Integer.toString(stackParent.push(Integer.parseInt(idCatalog)));
                       }
                       else if(i < depthRoot){
                        depthRoot = i;
                           if(stackParent.peek() != 1)stackParent.pop();
                           idParentCatalog = Integer.toString(stackParent.peek());
                       }
                       else if(i == depthRoot){
                           idParentCatalog = Integer.toString(stackParent.peek());
                       }
                    idCatalog = node.attr("title");
                    if(!idCatalog.equals("")) idCatalog = idCatalog.substring("номер ".length());
                    name_catalog = ((Element)node).text();
                    hrefCatalog = node.attr("href");
                    hrefCatalog = hrefCatalog.substring(0, hrefCatalog.indexOf('&', hrefCatalog.indexOf("edit=")));
                    //System.out.println(idParentCatalog + "->" + idCatalog + ": " + ((Element)node).text());  
                    insertNodeCatalog(idCatalog, idParentCatalog, name_catalog, hrefCatalog);
                    }
                }
                }
                stackParent.removeAllElements();
                stackParent.push(1);
                depthRoot = 1;
                i = 0;
            }          
            
    }
    
    /**
    * Вытаскиваем все строки из основной таблицы с товаром
    **/    
    protected static int parseTable(Elements rowsByTag, int idCatalog, ArrayList<Product> productArrayList){
        
            String prev_custom_correct_price;//дата и автор последнего прихода           
            
            String date_korrect_price;//дата изменения цены, автор            
            
            int i;
            int count_rows = 0;              
                        
            Product rowProduct;
            /**
             * Перебор строк в цикле
             */            
            for(Element element : rowsByTag){
                
                if(element.getElementsByTag("td").size() == 14){
                    
                    rowProduct = new Product();
                    try{
                        rowProduct.setIdProduct(Integer.parseInt(element.getElementsByTag("td").get(0).select("a").attr("name")));//Код товара
                    }catch(Exception e){
                        continue;
                    }
                    
                    count_rows++;
                    
                    rowProduct.addCost(8, "old_price", Double.parseDouble(element.getElementById("old_price_" + rowProduct.getIdProduct()).attr("title")), "", "");
                    
                    //Обрезать ссылку
                    //	window.open('/monitor/menus/products/muh.php?pid=201353&9161832160200197','FullScreen','toolbar=no,location=no,directories=no,status=yes,menubar=no,scrollbars=yes,resizable=yes,width=700,height=500');
                    rowProduct.setHistory2_link(element.getElementsByTag("td").get(0)
                            .select("span").get(1).attr("onclick"));//ист2 - необходимо обрезать лишние символы 
                    
                    rowProduct.setBonus_reiting(element.getElementsByTag("td").get(1)
                            .select("input").attr("value"));//бонус рейтинг на товар
                    
                    rowProduct.setName(element.getElementsByTag("td").get(2)
                            .getElementById("name_" + rowProduct.getIdProduct()).attr("value"));//название товара
                    
                    rowProduct.setGarant(element.getElementsByTag("td").get(2)
                            .getElementById("garant_" + rowProduct.getIdProduct()).attr("value"));//гарантия на товар
                    
                    rowProduct.setProvider(element.getElementsByTag("td").get(2)
                            .select("span").get(0).textNodes().get(0).text());//поставщик последней накладной
                    
                    i = 1;
                    //ОБрезть строку на входе
                    if(element.getElementsByTag("td").get(2).select("span").size() == 11){ 
                        String tmp_cost = element.getElementsByTag("td").get(2)
                                .select("span").get(i-1).getElementsByTag("b").get(0).text();
                        String tmp_data = element.getElementsByTag("td").get(2)
                                .select("span").get(i-1).getElementsByTag("b").get(1).text();
                        rowProduct.addCost(1, "По последней приходной накладной", Double.parseDouble(tmp_cost.substring(0, tmp_cost.length()-3)), tmp_data.substring(0, 10), "");
                    }
                    else {
                        i = 0;
                    }
                    rowProduct.setUrl_product(element.getElementsByTag("td").get(2)
                            .select("span").get(i).getElementsByAttributeValue("target", "_blank").attr("href"));//ссылка на товар на сайте
                    
                    rowProduct.setUrl_yamarket(element.getElementsByTag("td").get(2)
                            .select("span").get(i).getElementsByAttributeValue("target", "yaurl").attr("href"));//ссылка на товар в яндекс маркет
                    
                    rowProduct.setCurrent_count(element.getElementsByTag("td").get(3).text()); //количество в наличии
                    
                    
                    /*
                     * Товары находящиеся в резервах
                     */
                    
                    i = 0;                    
                    int tmp;
                    
                    for(Element el : element.getElementsByTag("td").get(4).getElementsByTag("font")){   
                        tmp = Integer.parseInt(el.text().substring(2));
                        switch(el.text().charAt(0)){
                            case 'з': rowProduct.setCntInOrder(tmp);
                            break;
                            case 'к': rowProduct.setCntInCourier(tmp);
                            break;
                            case 'o': rowProduct.setCntInOptOrder(tmp);
                            break;
                            case 'в': rowProduct.setCnt_in_return(tmp);
                            break;
                            case 'м': rowProduct.setCntInMagOrder(tmp);
                            break;
                            case 'р': rowProduct.setCnt_in_reserve(tmp);
                            break;
                        }
                    }
                    
                    /*
                    Статистика розничных и оптовых продаж за 7 и 14 дней
                    */
                    
                    String stat = element.getElementsByTag("td").get(6)
                            .textNodes().get(0).text();   
                    
                    rowProduct.setSales7day(Integer.parseInt(
                            stat.substring(0, stat.indexOf('|') - 1))); 
                    
                    
                    stat = stat.substring(stat.indexOf("|") + 1, stat.length());
                    stat = stat.replaceAll("\\u00A0", "");//удаляем пробелы
                    rowProduct.setOptsales7day(Integer.parseInt(stat));
                    
                    
                    stat = element.getElementsByTag("td").get(6)
                            .textNodes().get(1).text();
                    stat = stat.substring(0, stat.indexOf("|") - 1).replaceAll("\\u00A0", "");
                    
                    rowProduct.setSales14day(Integer.parseInt(stat));    
                    
                    stat = element.getElementsByTag("td").get(6)
                            .textNodes().get(1).text();
                    stat = stat.substring(stat.indexOf("|") + 1, stat.length());
                    stat = stat.replaceAll("\\D", "");
                    rowProduct.setOptsales14day(Integer.parseInt(stat));  
                    
                    date_korrect_price = element.getElementsByTag("td").get(8)
                                    .getElementById("rubprice_" + rowProduct.getIdProduct()).attr("title");//дата изменения цены, автор
            
            rowProduct.addCost(2, "Входная цена", Double.parseDouble(element.getElementsByTag("td").get(7).text().replace(".", "")),
                            getDateInCorrectString(date_korrect_price),
                            getAutorInCorrectString(date_korrect_price));
            
            rowProduct.addCost(3, "РРЦ", Double.parseDouble(element.getElementsByTag("td").get(8)
                                    .getElementById("rrc_" + rowProduct.getIdProduct()).attr("value")),
                            getDateInCorrectString(date_korrect_price), 
                            getAutorInCorrectString(date_korrect_price));
            
            rowProduct.addCost(4, "Текущая цена", Double.parseDouble(element.getElementsByTag("td").get(8)
                                    .getElementById("rubprice_" + rowProduct.getIdProduct()).attr("value")), 
                            getDateInCorrectString(date_korrect_price),
                            getAutorInCorrectString(date_korrect_price));
                    
                    prev_custom_correct_price = element.getElementsByTag("td").get(8)
                                    .getElementsByTag("div").get(1).attr("title");//дата и автор прихода и назанчения цены
                    
            rowProduct.addCost(5, "Предыдущая цена", Double.parseDouble(element.getElementsByTag("td").get(8)
                                    .getElementsByTag("div").get(1).text()), getDateInCorrectString(prev_custom_correct_price), 
                            getAutorInCorrectString(prev_custom_correct_price));
                    
                    rowProduct.setUrl_conkurent(element.getElementsByTag("td").get(8).getElementsByAttribute("target").get(0).attr("href"));                       
                                        
            rowProduct.addCost(6, "Цена в магазине", Double.parseDouble(element.getElementsByTag("td").get(9).text()), 
                            getDateInCorrectString(date_korrect_price),
                            getAutorInCorrectString(date_korrect_price));  
            
            rowProduct.addCost(7, "Цена за доставку", Double.parseDouble(element.getElementsByTag("td").get(10).text()), 
                            getDateInCorrectString(date_korrect_price),
                            getAutorInCorrectString(date_korrect_price));
            
            rowProduct.setOneComment(element.getElementsByTag("td").get(13).getElementById("comment_" + rowProduct.getIdProduct()).val());
            rowProduct.setAdditionalComment(element.getElementsByTag("td").get(13).getElementById("aditionalcomment_" + rowProduct.getIdProduct()).text());
            rowProduct.setMoreComment(element.getElementsByTag("td").get(13).getElementById("onemorecomm_" + rowProduct.getIdProduct()).text());
            
            productArrayList.add(rowProduct); 
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
    private static void insertNodeCatalog(String idCatalog, String idParentCatalog, String name, String href){
        db.insertRowCatalog(Integer.parseInt(idCatalog), Integer.parseInt(idParentCatalog), name, href);        
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
