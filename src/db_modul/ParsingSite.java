package db_modul;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    
    
    public static void main(String args[]){
        try {
            /**
             * Открытие страницы или файла для парсинга
             */
            Document doc = Jsoup.parse(new File("D:\\Работа\\Проект\\Товары2\\src\\test_html.htm"), "windows-1251");
                        
            System.out.println(doc.title());
            
            /**
             * Вытаскиваем из HTML файла все строки из основной таблицы
             */
            Elements elemntsByTag = doc.getElementsByClass("medium").select("tr");
            
            String idProduct;
            String history2_link;
            String bonus_reiting;//int
            String name_product;
            String garant;
            String provider;
            String price_last_custom;//int
            String date_last_custom;
            String url_product;
            String url_yamarket;
            String current_count;//int
            String date_korrect_price;
            String prev_custom_correct_price;
            String url_conkurent;
            int cnt_in_order;
            int cnt_in_courier;
            int cnt_in_opt_order;
            int cnt_in_mag_order;
            int cnt_in_return;
            int cnt_in_reserve;
            int sales7day;
            int sales14day;
            int optsales7day;
            int optsales14day;
            int input_price;
            int price_product;
            int rrc;
            int preview_price;
            int price_in_mag;
            int price_dostavka;
            
            int i;
            
            /**
             * Перебор строк в цикле
             */            
            for(Element element : elemntsByTag){
                
                idProduct = element.getElementsByTag("td").get(0).select("a").attr("name");//Код товара
                if(!idProduct.isEmpty()){
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
                    
                    preview_price = Integer.parseInt(element.getElementsByTag("td").get(8)
                                    .getElementsByTag("div").get(1).text());//предыдущая цена
                    
                    prev_custom_correct_price = element.getElementsByTag("td").get(8)
                                    .getElementsByTag("div").get(1).attr("title");//дата и автор прихода и назнчения цены
                    
                    url_conkurent = element.getElementsByTag("td").get(8).getElementsByAttribute("target").get(0).attr("href");
                            
                    price_in_mag = Integer.parseInt(
                            element.getElementsByTag("td").get(9).text());//цена в магазине
                    
                    price_dostavka = Integer.parseInt(
                            element.getElementsByTag("td").get(10).text());//цена за доставку                    
                    
                }
            }
            
            
        } catch (IOException ex) {
            Logger.getLogger(ParsingSite.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
