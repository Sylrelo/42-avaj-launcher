package com.slopez.avaj;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import com.slopez.avaj.exceptions.AircraftCreationError;
import com.slopez.avaj.exceptions.InvalidAircraft;
import com.slopez.avaj.exceptions.InvalidAircraftCoordinates;
import com.slopez.avaj.exceptions.InvalidMd5Hash;
import com.slopez.avaj.exceptions.SimulationError;
import com.slopez.avaj.logger.LoggerProvider;
import com.slopez.avaj.simulator.AircraftFactory;
import com.slopez.avaj.simulator.Flyable;
import com.slopez.avaj.tower.WeatherTower;

public class Main {
    public static void main(String[] args) throws AircraftCreationError, InvalidAircraft, InvalidAircraftCoordinates {

        try {
            if (args.length == 0) {
                return;
            }

            if (args.length == 2) {

                byte[] fileBytes = Files.readAllBytes(Paths.get(args[0]));
                byte[] hash = MessageDigest.getInstance("MD5").digest(fileBytes);
                StringBuffer sb = new StringBuffer();

                for (int i = 0; i < hash.length; i++) {
                    sb.append(Integer.toString((hash[i] & 0xff) + 0x100, 16).substring(1));
                }

                if (!sb.toString().equalsIgnoreCase(args[1])) {
                    throw new InvalidMd5Hash(args[1], sb.toString());
                }
            }

            try (BufferedReader reader = new BufferedReader(new FileReader(args[0]))) {
                String line = reader.readLine();

                if (line == null) {
                    return;
                }

                int simulationCount = Integer.parseInt(line.split(" ")[0]);

                if (simulationCount <= 0) {
                    return;
                }

                WeatherTower tower = new WeatherTower();
                List<Flyable> flyables = new ArrayList<>();

                while ((line = reader.readLine()) != null) {
                    String[] lineValues = line.split(" ");

                    if (lineValues.length != 5) {
                        throw new Error("Wrong number of values.");
                    }

                    flyables.add(AircraftFactory.newAircraft(
                            lineValues[0],
                            lineValues[1],
                            Integer.parseInt(lineValues[2]),
                            Integer.parseInt(lineValues[3]),
                            Integer.parseInt(lineValues[4])));

                }

                for (Flyable flyable : flyables) {
                    flyable.registerTower(tower);
                }

                for (int i = 0; i < simulationCount; i++) {
                    tower.changeWeather();
                }

                System.out.printf("Simulation count : %d\nAircraft count : %d\n", simulationCount, flyables.size());
                flyables.clear();
            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        } catch (IOException e) {
            System.out.println("Cannot read line.");
        } catch (NumberFormatException e) {
            System.out.println("Cannot parse Integer from text-file.");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Invalid access.");
        } catch (Error e) {
            System.out.println(e.getMessage());
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Invalid selected algorithm.");
        } catch (InvalidMd5Hash e) {
            System.out.println(e);
        } catch (SimulationError e) {
            System.out.println(e);
        } finally {
            LoggerProvider.getProvider().closeHandle();
        }
    }
}