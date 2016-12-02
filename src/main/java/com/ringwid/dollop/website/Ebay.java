package com.ringwid.dollop.website;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;
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

    public Ebay(WebClient webClient) {
        super(webClient);
    }

    @Override
    public String getName() {
        return "Ebay";
    }

    @Override
    public void handleLogin(String username, String password) {
        try {
            HtmlPage page = this.getWebClient().getPage(LOGIN_URL);
            HtmlForm form = page.getFormByName("SignInForm");

            HtmlInput emailField = null;
            HtmlInput passwordField = null;

            for (HtmlElement input : form.getElementsByTagName("input")) {
                if (input.getAttribute("placeholder").equals("Email or username") && input.getAttribute("class").equals("fld")) {
                    emailField = form.getInputByName(input.getAttribute("name"));
                } else if (input.getAttribute("placeholder").equals("Password") && input.getAttribute("class").equals("fld")) {
                    passwordField = form.getInputByName(input.getAttribute("name"));
                }
            }

            if (emailField == null || passwordField == null) {
                throw new IllegalStateException("Protocol outdated");
            }

            emailField.setValueAttribute(username);
            passwordField.setValueAttribute(password);

            HtmlSubmitInput submitInput = (HtmlSubmitInput) page.getElementById("sgnBt");
            HtmlPage reply = submitInput.click();

            if (reply.getTitleText().equals("eBay.com")) {
                setStatus(Status.LOGGED_IN);
            } else {
                setStatus(Status.LOGIN_ERROR);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getAddress() {
        return "http://www.ebay.com";
    }

}
