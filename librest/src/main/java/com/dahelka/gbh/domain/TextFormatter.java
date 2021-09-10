package com.dahelka.gbh.domain;

import com.dahelka.gbh.entity.Page;
import java.util.List;
/**
 * Procesos para tratar los distintos formatos de textos.
 * @author su
 */
public class TextFormatter {

    List<Page> pages;
    EFormatType formatType;

    public TextFormatter(List<Page> pages, EFormatType formatType) {
        this.pages = pages;
        this.formatType = formatType;
    }

    public String getFormattedText() {

        String result = "";
        switch (this.formatType.toString().toLowerCase()) {
            case "html":
                result = getHtmlText();
                break;
            case "plain":
                result = getPlainText();
                break;
            default:
                result = "Unknown text format.";

        }

        return result;
    }

    public String getPlainText() {
        String output = "";
        Integer pageNumber=1;
        for (Page p : pages) {
            output += "\nPage number " + (++pageNumber).toString() + "\r\n\r\n";
            output += p.getBodyContent();
            output += "\r\n\r\n\r\n";
        }

        return output;
    }

    /**
     *
     * @return
     */
    public String getHtmlText() {
        String output = "";

        if (pages != null && pages.size() > 0) {
            output = "<html><header><title></title></head>";
            output += "<body>";
            for (Page p : pages) {

                output += "<b>Page number " + p.getIdPage() + "</b><br />";
                output += "<p>" + p.getBodyContent() + "</p>";
                output += "<br >";
            }
        }

        return output;
    }
}
