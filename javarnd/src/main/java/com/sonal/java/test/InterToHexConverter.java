package com.sonal.java.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InterToHexConverter {
	public String convertToHex(int decimalVal)
    {
        String hexValue = "";
        int reminder = 0;
        do
        {
            reminder = decimalVal % 16;
            hexValue = (reminder <= 9 && reminder >= 0) ? ((char) ('0' + reminder) + hexValue) : ((char) ('A' + (reminder - 10)) + hexValue);
            decimalVal = decimalVal / 16;

        } while (decimalVal != 0);
        return hexValue;
    }

    public static void main(String args[]) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String intValInStr;
        System.out.println("Enter the integer val: ");
        intValInStr = reader.readLine();
        InterToHexConverter converter = new InterToHexConverter();
        Integer intVal = Integer.parseInt(intValInStr);
        if (intVal.intValue() < 0)
        {
            System.out.println("Negative Value can't be converted");
            return;
        }
        System.out.println("HexValue = " + converter.convertToHex(intVal));
    }
}
