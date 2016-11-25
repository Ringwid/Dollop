package com.ringwid.dollop.website;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

/**
 * Project Dollop
 */
public class Ebay extends Website {

    private final String LOGIN_URL = "http://signin.ebay.com/ws/eBayISAPI.dll";

    @Override
    public String getName() {
        return "Ebay";
    }

    @Override
    public void handleLogin(String username, String password) {
        try {
            Connection.Response loginPage = Jsoup.connect(LOGIN_URL).method(Connection.Method.GET).timeout(20000).execute();
            Document loginForm = loginPage.parse();
            Element elementUsername = loginForm.select("input[placeholder=Email or username]").select("input[class=fld]").first();
            Element elementPassword = loginForm.select("input[placeholder=Password]").select("input[class=fld]").first();

            Document document = Jsoup.connect(LOGIN_URL)
                    .data("UsingSSL", "0")
                    .data("co_partnerId", "2")
                    .data("siteid", "0")
                    .cookies(loginPage.cookies())
                    .timeout(20000)
                    .post();
            System.out.println(document.html());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Ebay().handleLogin("", "");
    }

    @Override
    public String getAddress() {
        return "http://www.ebay.com";
    }

}
