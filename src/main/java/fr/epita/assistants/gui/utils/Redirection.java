package fr.epita.assistants.gui.utils;

import javax.swing.*;
import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class Redirection {

    private static ByteArrayOutputStream outStream;
    private static ByteArrayOutputStream errStream;
    private static StringBuilder outLog = new StringBuilder();
    private static StringBuilder errLog = new StringBuilder();
    private static PrintStream newOut = null;
    private static PrintStream oldOut = null;
    private static PrintStream newErr = null;
    private static PrintStream oldErr = null;

    public static void StartRedirectError()
    {
        outStream = new ByteArrayOutputStream();
        newOut = new PrintStream(outStream);
        oldOut = System.out;
        System.setOut(newOut);


        errStream = new ByteArrayOutputStream();
        newErr = new PrintStream(errStream);
        oldErr = System.err;
        System.setOut(newErr);
    }

    public static void StopRedirectError(Component frame, String name, int message_type)
    {
        outLog.setLength(0);
        errLog.setLength(0);

        System.out.flush();
        System.setOut(oldOut);
        outLog.append(outStream.toString());

        System.err.flush();
        System.setOut(oldErr);
        errLog.append(errStream.toString());
        JOptionPane.showMessageDialog(frame, outLog.toString() + "\n" + errLog.toString(), name,
                message_type);
    }
}
