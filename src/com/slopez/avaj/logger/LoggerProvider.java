package com.slopez.avaj.logger;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class LoggerProvider {
    static LoggerProvider loggerProvider;

    private FileWriter fileWriter;
    private BufferedWriter bufferedWriter;

    final static String fileName = "simulation.txt";

    public LoggerProvider() {
        File file = new File(LoggerProvider.fileName);

        try {
            if (file.exists()) {
                file.delete();
            }
            file.createNewFile();
        } catch (SecurityException e) {
            System.out.println("Cannot check if log file exists.");
        } catch (IOException e) {
            System.out.println("Cannot create log file.");
        }

        try {
            this.fileWriter = new FileWriter(file, true);
        } catch (IOException e) {
            System.out.println("Cannot create FileWriter.");
        }
        this.bufferedWriter = new BufferedWriter(this.fileWriter);
    }

    static public LoggerProvider getProvider() {
        if (LoggerProvider.loggerProvider == null) {
            LoggerProvider.loggerProvider = new LoggerProvider();
        }

        return LoggerProvider.loggerProvider;
    }

    public void log(String message) {
        try {
            this.bufferedWriter.write(message);
            this.bufferedWriter.flush();
        } catch (IOException e) {
            System.out.println("Cannot append to log file.");
            System.exit(1);
        }
    }

    public void closeHandle() {
        try {
            this.bufferedWriter.close();
            this.fileWriter.close();
        } catch (IOException e) {
            System.out.println("Cannot close file handlers.");
        }
    }
}
