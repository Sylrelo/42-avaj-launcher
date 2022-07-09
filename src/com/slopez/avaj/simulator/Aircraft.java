package com.slopez.avaj.simulator;

import java.util.HashMap;
import java.util.Map;

import com.slopez.avaj.Coordinates;
import com.slopez.avaj.logger.LoggerProvider;

public class Aircraft {
    protected long id;
    protected String name;
    protected Coordinates coordinates;

    protected Map<String, Coordinates> modifiers;
    protected String type;

    private static long idCounter;

    protected Aircraft(String name, Coordinates coordinates) {
        this.name = name;
        this.coordinates = coordinates;
        this.id = this.nextId();
        this.modifiers = new HashMap<String, Coordinates>(4);
    }

    private long nextId() {
        Aircraft.idCounter += 1;
        return Aircraft.idCounter;
    }

    public String getName() {
        return this.name;
    }

    public Boolean hasLanded() {
        return this.coordinates.getHeight() <= 0;
    }

    public void printMessage(String message) {
        LoggerProvider.getProvider().log(String.format("%s#%s(%d) : %s\n", this.type, this.name, this.id, message));
        // System.out.printf("%s#%s(%d) : %s\n", this.type, this.name, this.id,
        // message);
    }
}
