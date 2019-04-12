package com.telegrambot.officialrates;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.net.ssl.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;

public class OverTime {
    private String datecourse;
    private String currency;

    public void setDatecourse(String datecourse) {
        this.datecourse = datecourse;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String courseovertime() throws NoSuchAlgorithmException, KeyManagementException {
        TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }

            public void checkClientTrusted(X509Certificate[] certs, String authType) {
            }

            public void checkServerTrusted(X509Certificate[] certs, String authType) {
            }

        }};

        SSLContext sc = SSLContext.getInstance("SSL");
        sc.init(null, trustAllCerts, new SecureRandom());
        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

        HostnameVerifier allHostsValid = new HostnameVerifier() {
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        };

        HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);

        String nationalB = "Нац. Банк";
        try {
            URL url = new URL("https://nationalbank.kz/rss/get_rates.cfm?fdate=" + datecourse + "&switch=kazakh");
            URLConnection conn = url.openConnection();
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(conn.getInputStream());
            String description = "";

            NodeList list = document.getElementsByTagName("item");

            for (int i = 0; i < list.getLength(); i++) {
                Element element = (Element) list.item(i);
                if (element.getElementsByTagName("fullname").item(0).getChildNodes().item(0).getNodeValue().equals(currency.toUpperCase())) {
                    description = element.getElementsByTagName("description").item(0).getFirstChild().getNodeValue();
                }
            }
            return currency.toUpperCase() + "\n" + description;

        } catch (IOException | ParserConfigurationException | SAXException e) {
            e.printStackTrace();
        }
        return nationalB;
    }
}
