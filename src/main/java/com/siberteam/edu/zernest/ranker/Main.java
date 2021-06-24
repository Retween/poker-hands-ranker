package com.siberteam.edu.zernest.ranker;

import com.siberteam.edu.zernest.ranker.interfaces.IErrorHandler;

import java.util.Collections;
import java.util.List;

public class Main {
    private static final IErrorHandler errorHandler = new IErrorHandler() {
        @Override
        public void handleException(Exception e) {
            IErrorHandler.super.handleException(e);
        }
    };

    public static void main(String[] args) {
        CommandLineParser parser = new CommandLineParser();
        try {
            parser.parseCommandLine(args);

            List<PokerHand> hands = IOHandsListOperator.readHandsList(parser.getInputStream());

            Collections.sort(hands);

            IOHandsListOperator.writeHandsList(parser.getOutputStream(), hands);
        } catch (Exception e) {
            parser.printHelp();
            errorHandler.handleException(e);
        }
    }

}
