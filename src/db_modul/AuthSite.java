/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db_modul;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.GZIPInputStream;
import javax.net.ssl.HttpsURLConnection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author Дима
 */
public class AuthSite {
    private List<String> cookies;
    private HttpURLConnection conn;
    private final String url = "http://monitor.pleer.ru/monitor/";
    private final String url_test = "http://test.branches.monitor.svn.pleer.ru/monitor/";
    private final String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/44.0.2403.107 Safari/537.36";   
    private String page;
    private String postParams;
   
    
    public AuthSite(String username, String password){        
        try {
            CookieHandler.setDefault(new CookieManager());
            page = getPageContent(url);
            postParams = getFormParams(page, username, password);
            sendPost("http://monitor.pleer.ru/monitor/", postParams);
        } catch (ProtocolException ex) {
            Logger.getLogger(AuthSite.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AuthSite.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void sendPost(String url, String postParams) throws MalformedURLException, ProtocolException, IOException{
        URL obj = new URL(url);
        conn = (HttpURLConnection) obj.openConnection();
        
        conn.setUseCaches(false);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Host", "monitor.pleer.ru");
        conn.setRequestProperty("User-Agent", USER_AGENT);
        conn.setRequestProperty("Accept",
		"text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
        conn.setRequestProperty("Charset", "windows-1251");
        conn.setRequestProperty("Accept-Language", "ru-RU,ru;q=0.8,en-US;q=0.6,en;q=0.4");
        conn.setRequestProperty("Cache-Control", "max-age=0");
            for(String cookie : cookies){
                conn.addRequestProperty("Cookie", cookie.split(";", 1)[0]);
            }
        conn.setRequestProperty("Connection", "keep-alive");
	conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
	conn.setRequestProperty("Content-Length", Integer.toString(postParams.length()));
        conn.setRequestProperty("Origin", "http://monitor.pleer.ru");
        conn.setRequestProperty("Referer", "http://monitor.pleer.ru/monitor/");
        conn.setRequestProperty("Upgrade-Insecure-Requests", "1");
        conn.setDoOutput(true);
        conn.setDoInput(true);
        
        DataOutputStream wr = new DataOutputStream((conn.getOutputStream()));
        wr.writeBytes(postParams);
        wr.flush();
        wr.close();
        
        int responseCode = conn.getResponseCode();
        System.out.println("\nSending 'POST' request to URL : " + url);
	System.out.println("Post parameters : " + postParams);
	System.out.println("Response Code : " + responseCode);
        
        
        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "windows-1251"));
        String inputLine;
        StringBuffer response = new StringBuffer();
        
        while((inputLine = in.readLine()) != null){
            response.append(inputLine);            
        }
        in.close();
        //System.out.println(response.toString());
    }
    
    public String getPageContent(String url) throws MalformedURLException, ProtocolException, IOException{
        URL obj = new URL(url);
        conn = (HttpURLConnection) obj.openConnection();
        
        conn.setRequestMethod("GET");
        conn.setUseCaches(false);
        
        conn.setRequestProperty("User-Agent", USER_AGENT);
        conn.setRequestProperty("Accept",
		"text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
        conn.setRequestProperty("Charset", "windows-1251");
        conn.setRequestProperty("Accept-Language", "ru-RU,ru;q=0.8,en-US;q=0.6,en;q=0.4");
        
        if(cookies != null){
            for(String cookie : cookies){
                conn.addRequestProperty("Cookie", cookie.split(";", 1)[0]);
            }
        }
        int responseCode = conn.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + url);
        
        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "windows-1251"));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while((inputLine = in.readLine()) != null){
            response.append(inputLine);
        }
        in.close();
        
        setCookies(conn.getHeaderFields().get("Set-Cookie"));
        if(cookies != null){
        for(String cook : cookies){
            System.out.println(cook);
        }}
        return response.toString();                
    }
    
    private String getFormParams(String html, String username, String password) throws UnsupportedEncodingException{
        System.out.println("Extracting form's data ...");
        Document doc = Jsoup.parse(html);
        
        Elements inputElements = doc.getElementsByAttributeValue("name", "login_form").get(0).getElementsByTag("input");
        inputElements.remove(2);
        List<String> paramList = new ArrayList<String>();
        for(Element inputElement : inputElements){
            String key = inputElement.attr("name");
            String value = inputElement.attr("value");
            
            if(key.equals("login"))
                value = username;
            else if (key.equals("password"))
                value = password;
            paramList.add(key + "=" + URLEncoder.encode(value, "UTF-8"));            
        }
        
        StringBuilder result = new StringBuilder();
        for(String param : paramList){
            if(result.length() == 0){
                result.append(param);
            }
            else {
                result.append("&" + param);
            }
        }
        
        return result.toString();
    }
    
    public void setCookies(List<String> cookies){
        if(cookies != null){
        this.cookies = new ArrayList<String>();        
        for(String cook : cookies){
            String str = cook.substring(0, cook.indexOf(";"));
            this.cookies.add(str);
        }
        }
    }
    
}
