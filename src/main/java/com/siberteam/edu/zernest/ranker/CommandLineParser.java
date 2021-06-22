package com.siberteam.edu.zernest.ranker;

import org.apache.commons.cli.*;

import java.io.*;
import java.nio.file.FileAlreadyExistsException;

public class CommandLineParser {
    private final static Options options = new Options();
    private InputStream inputStream;
    private OutputStream outputStream;

    static {
        options.addRequiredOption("i", "inputFile", true,
                "Input file with sets of players cards");
        options.addRequiredOption("o", "outputFile", true,
                "Output file with sorted by strength sets of players cards");
    }

    public void parseCommandLine(String[] args) throws ParseException, FileAlreadyExistsException,
            FileNotFoundException {
        org.apache.commons.cli.CommandLineParser parser = new DefaultParser();
        CommandLine cmd = parser.parse(options, args);

        File inputFile = new File(cmd.getOptionValue("i"));
        File outputFile = new File(cmd.getOptionValue("o"));

        if (!inputFile.exists()) {
            throw new FileNotFoundException(inputFile.getName());
        }

//        if (outputFile.exists() && outputFile.isFile()) {
//            throw new FileAlreadyExistsException(outputFile.getName());
//        }

        inputStream = new FileInputStream(inputFile);
        outputStream = new FileOutputStream(outputFile);
    }

    public void printHelp() {
        HelpFormatter formatter = new HelpFormatter();

        String syntax = "Main";
        String usageHeader = "Example of Using poker-hands-ranker app";
        String usageFooter = "Usage example: -i inputFile.txt -o outputFile.txt";

        formatter.printHelp(syntax, usageHeader, options, usageFooter);
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public OutputStream getOutputStream() {
        return outputStream;
    }

    @Override
    public String toString() {
        return "CommandLineParser" + "[" +
                "inputStream=" + inputStream +
                ", outputStream=" + outputStream +
                ']';
    }
}
