package com.even.input;


import com.even.exception.InvalidInputFormatException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BoundingBoxInputReader {

    /**
     * Reads the input and returns list of input lines, with Length checks and -* regex checks
     * @return
     * @throws IOException
     * @throws InvalidInputFormatException
     */
    public List<String> readInput() throws IOException, InvalidInputFormatException {
        BufferedReader reader =
                new BufferedReader(new InputStreamReader(System.in));
        ArrayList<String> inputLines = new ArrayList<>();
        String inputLine = reader.readLine();
        int length = inputLine.length();
        while( inputLine!=null && inputLine.length() != 0  ){
            if( regexMatcher( inputLine )) {
                if (length != inputLine.length()) {
                    throw new InvalidInputFormatException( InvalidInputFormatException.INVALID_LENGTH);
                }
                inputLines.add(inputLine);
                inputLine = reader.readLine();
            } else {
                throw new InvalidInputFormatException( InvalidInputFormatException.INVALID_CHARACTER );
            }
        }
        if( inputLines.size() == 0 ){
            throw new InvalidInputFormatException( InvalidInputFormatException.INVALID_LENGTH);
        }
        return inputLines;
    }

    public boolean regexMatcher( String str ){
        String patternString = "[\\-\\*]*";
        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }
}
