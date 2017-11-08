/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.text.ParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.text.DefaultFormatter;

/**
 *
 * @author User
 */
public class RegExFormatter extends DefaultFormatter {
        protected Matcher matcher;
        public RegExFormatter(String regex) {
        setOverwriteMode(false);
        Pattern p = Pattern.compile(regex);
        matcher = p.matcher(""); // initial field contents
    }

    @Override
    public Object stringToValue(String string) throws ParseException {
        if (string == null || string.trim().equals(""))
            return null;
        matcher.reset(string);

        if (!matcher.matches()) {
            throw new ParseException("Input did not match regex", 0);
        }

        return super.stringToValue(string);  // default returns this string; see docs!
    }

}
