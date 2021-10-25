package com.acn.FeatureToggleDemo.controller;

import java.lang.String;
import org.ff4j.FF4j;
import org.ff4j.core.Feature;
import org.ff4j.property.PropertyString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
@RequestMapping("/")
public class MainController {

    public static final String FEATURE_SHOW_WEBCONSOLE = "showWebConsoleURL";
    public static final String FEATURE_SHOW_REST_API = "showRestApiURL";
    public static final String FEATURE_SHOW_USERNAME = "showUserName";
    private static final String PROPERTY_USERNAME = "username";

    @Autowired
    public FF4j ff4j;

    @PostConstruct
    public void populateSampleFeatures(){
        if(!ff4j.exist(FEATURE_SHOW_WEBCONSOLE)){
            ff4j.createFeature(new Feature(FEATURE_SHOW_WEBCONSOLE, true));
        }
        if(!ff4j.exist(FEATURE_SHOW_REST_API)){
            ff4j.createFeature(new Feature(FEATURE_SHOW_REST_API, true));
        }
        if(!ff4j.exist(FEATURE_SHOW_USERNAME)){
            ff4j.createFeature(new Feature(FEATURE_SHOW_USERNAME, true));
        }
        if(!ff4j.getPropertiesStore().existProperty(PROPERTY_USERNAME)){
            ff4j.createProperty(new PropertyString(PROPERTY_USERNAME, "user"));
        }
    }

@RequestMapping(value = "/", method= RequestMethod.GET, produces = "text/html")
    public String getFeatureEnabledResponse(){
        StringBuilder htmlPage = new StringBuilder("<html><body><ul>");
        htmlPage.append("<h2>This is FF4j Demo Page. </h2>");
        htmlPage.append("<p> Below displayed links are enabled by FF4j Features."
        + "This illustrates the response according to features toggled</p>");
        htmlPage.append("<p><b>Here are list of resources:" +
                "</b><br/><ul>");
        if(ff4j.check(FEATURE_SHOW_WEBCONSOLE)){
            htmlPage.append("<li> To access the <b> REST API </b> "
            + "please goto <a href=\"./api/ff4j\" target=\"_blank\">ff4j-rest-api </a>");
        }
    if(ff4j.check(FEATURE_SHOW_USERNAME)){
        if(ff4j.getPropertiesStore().existProperty(PROPERTY_USERNAME)) {
            htmlPage.append("<li>" + PROPERTY_USERNAME + "value is ");
            htmlPage.append("<span style=\"color:blue;font-weight:bold\">");
            htmlPage.append(ff4j.getPropertiesStore().readProperty(PROPERTY_USERNAME).asString());
        }else{
            htmlPage.append("<li>" + PROPERTY_USERNAME + "does not exists.");
        }
    }
    htmlPage.append("</ul></body></html>");
    return htmlPage.toString();

}

}
