package cn.androidminds.springstudy;

import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

public class MyAcceptHeaderLocaleResolver extends AcceptHeaderLocaleResolver {
    private Locale myLocal;

    public Locale resolveLocale(HttpServletRequest request) {
        return myLocal;
    }

    public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {
        myLocal = locale;
    }

}
