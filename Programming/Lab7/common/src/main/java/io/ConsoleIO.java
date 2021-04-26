package io;

import locale.CommonBundle;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Locale;

public class ConsoleIO implements UserIO {

    private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private final Console console = System.console();

    public String readLine() throws IOException {
        String readRes = reader.readLine();
        if (readRes == null) {
            throw new IOException();
        }
        return readRes;
    }

    public String readPasswordLine() throws IOException{
        if (console == null) {
            return readLine();
        }
        char[] passChar = console.readPassword();
        if (passChar == null) {
            throw new IOException();
        }
        return new String(passChar);
    }

    @Override
    public boolean askYesOrNo(String message) throws IOException {
        //TODO: непонятная проблема с кодировками, вероятнее всего, из-за java 1.8
        /*String yesStr = CommonBundle.getString("io.yes");
        String noStr = CommonBundle.getString("io.no");*/
        String yesStr = "yes";
        String noStr = "no";
        printLine(yesStr);
        while (true) {
            printLine(message + "(" + yesStr + "/" + noStr + ")");
            String ans = readLine();
            printLine(ans);
            if (ans.length() > 0) {
                if (ans.equals(yesStr) || ans.startsWith(String.valueOf(yesStr.charAt(0)))) {
                    return true;
                } else if (ans.equals(noStr) || ans.startsWith(String.valueOf(noStr.charAt(0)))) {
                    return false;
                }
            }
        }
    }

    public void printLine(String line) {
        System.out.println(line);
    }

    public void printErrorMessage(String line) {
        System.err.println(line);
    }

    public void printUserPrompt() {
        System.out.print(">");
    }
}
