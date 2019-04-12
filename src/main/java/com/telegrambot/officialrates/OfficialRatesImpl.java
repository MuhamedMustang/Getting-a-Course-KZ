package com.telegrambot.officialrates;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

public class OfficialRatesImpl implements OfficialRates {
    public String courseUSD() {
        String nationalB = "Нац. Банк";
        try {
            URL url = new URL("http://www.nationalbank.kz/rss/rates_all.xml");
            URLConnection conn = url.openConnection();
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(conn.getInputStream());
            String course = "", pubDate = "", change = "";

            NodeList list = document.getElementsByTagName("item");

            for (int i = 0; i < list.getLength(); i ++){
                Element element = (Element)list.item(i);
                if (element.getElementsByTagName("title").item(0).getChildNodes().item(0).getNodeValue().equals("USD")){
                    course = element.getElementsByTagName("description").item(0).getFirstChild().getNodeValue();
                    pubDate = element.getElementsByTagName("pubDate").item(0).getFirstChild().getNodeValue();
                    change = element.getElementsByTagName("change").item(0).getFirstChild().getNodeValue();
                }
            }
            return "Доллар на\n"+pubDate+"\n"+course+" тенге\n"+"Изменение за сутки\n"+change;


        }catch (IOException | ParserConfigurationException | SAXException e){
            e.printStackTrace();
        }
        return nationalB;
    }

    public String courseEUR() {
        String nationalB = "Нац. Банк";
        try {
            URL url = new URL("http://www.nationalbank.kz/rss/rates_all.xml");
            URLConnection conn = url.openConnection();
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(conn.getInputStream());
            String course = "", pubDate = "", change = "";

            NodeList list = document.getElementsByTagName("item");

            for (int i = 0; i < list.getLength(); i ++){
                Element element = (Element)list.item(i);
                if (element.getElementsByTagName("title").item(0).getChildNodes().item(0).getNodeValue().equals("EUR")){
                    course = element.getElementsByTagName("description").item(0).getFirstChild().getNodeValue();
                    pubDate = element.getElementsByTagName("pubDate").item(0).getFirstChild().getNodeValue();
                    change = element.getElementsByTagName("change").item(0).getFirstChild().getNodeValue();
                }
            }
            return "Евро на\n"+pubDate+"\n"+course+" тенге\n"+"Изменение за сутки\n"+change;


        }catch (IOException | ParserConfigurationException | SAXException e){
            e.printStackTrace();
        }
        return nationalB;
    }

    public String courseRUB() {
        String nationalB = "Нац. Банк";
        try {
            URL url = new URL("http://www.nationalbank.kz/rss/rates_all.xml");
            URLConnection conn = url.openConnection();
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(conn.getInputStream());
            String course = "", pubDate = "", change = "";

            NodeList list = document.getElementsByTagName("item");

            for (int i = 0; i < list.getLength(); i ++){
                Element element = (Element)list.item(i);
                if (element.getElementsByTagName("title").item(0).getChildNodes().item(0).getNodeValue().equals("RUB")){
                    course = element.getElementsByTagName("description").item(0).getFirstChild().getNodeValue();
                    pubDate = element.getElementsByTagName("pubDate").item(0).getFirstChild().getNodeValue();
                    change = element.getElementsByTagName("change").item(0).getFirstChild().getNodeValue();
                }
            }
            return "Рублей на\n"+pubDate+"\n"+course+" тенге\n"+"Изменение за сутки\n"+change;


        }catch (IOException | ParserConfigurationException | SAXException e){
            e.printStackTrace();
        }
        return nationalB;
    }

    public String courseCNY() {
        String nationalB = "Нац. Банк";
        try {
            URL url = new URL("http://www.nationalbank.kz/rss/rates_all.xml");
            URLConnection conn = url.openConnection();
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(conn.getInputStream());
            String course = "", pubDate = "", change = "";

            NodeList list = document.getElementsByTagName("item");

            for (int i = 0; i < list.getLength(); i ++){
                Element element = (Element)list.item(i);
                if (element.getElementsByTagName("title").item(0).getChildNodes().item(0).getNodeValue().equals("CNY")){
                    course = element.getElementsByTagName("description").item(0).getFirstChild().getNodeValue();
                    pubDate = element.getElementsByTagName("pubDate").item(0).getFirstChild().getNodeValue();
                    change = element.getElementsByTagName("change").item(0).getFirstChild().getNodeValue();
                }
            }
            return "Юань на\n"+pubDate+"\n"+course+" тенге\n"+"Изменение за сутки\n"+change;


        }catch (IOException | ParserConfigurationException | SAXException e){
            e.printStackTrace();
        }
        return nationalB;
    }

    public String courseKGS() {
        String nationalB = "Нац. Банк";
        try {
            URL url = new URL("http://www.nationalbank.kz/rss/rates_all.xml");
            URLConnection conn = url.openConnection();
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(conn.getInputStream());
            String course = "", pubDate = "", change = "";

            NodeList list = document.getElementsByTagName("item");

            for (int i = 0; i < list.getLength(); i ++){
                Element element = (Element)list.item(i);
                if (element.getElementsByTagName("title").item(0).getChildNodes().item(0).getNodeValue().equals("KGS")){
                    course = element.getElementsByTagName("description").item(0).getFirstChild().getNodeValue();
                    pubDate = element.getElementsByTagName("pubDate").item(0).getFirstChild().getNodeValue();
                    change = element.getElementsByTagName("change").item(0).getFirstChild().getNodeValue();
                }
            }
            return "Сом на\n"+pubDate+"\n"+course+" тенге\n"+"Изменение за сутки\n"+change;


        }catch (IOException | ParserConfigurationException | SAXException e){
            e.printStackTrace();
        }
        return nationalB;
    }
}
