/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.Date;

/**
 *
 * @author DELL
 */
public class ControleSaisie {

    public boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public boolean isInte(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            int d = Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public boolean isAlpha(String ch) {
        if (ch == null) {
            return false;
        } else {
            int i = 0;
            while (i < ch.length()) {
                if (((int) ch.charAt(i) >= 65 && (int) ch.charAt(i) <= 90) || ((int) ch.charAt(i) >= 97 && (int) ch.charAt(i) <= 122) || ((int) ch.charAt(i) == 32)) {
                    i++;
                } else {
                    return false;
                }
            }

        }

        return true;

    }

    public boolean isValidEmailAddress(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }

    public boolean isDate(String ch) {
        if (ch == null) {
            return false;
        }
        try {
            Date d1 = Date.valueOf(ch);;
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;

    }

}
