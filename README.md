# 42-avaj-launcher
Implementation of a simple Java program according to a given class diagram written in UML.

## Description
The program takes a scenario file. It lists aircraft and their starting coordinates. 

The first line is the number of simulation runs. 

Each run, the Weather Tower provides updated weather conditions for every aircraft and their respective coordinates.

An aircraft react depending on it's current weather.

If the height of an aircraft reaches 0, it lands and unregisters from the Tower, stopping simulation for that aircraft.


## Usage

To compile and run with the default "scenario.txt" file :
```
make run
```


## Scenario example

```
25
Baloon B1 2 3 20
Baloon B2 1 8 66
JetPlane J1 23 44 32
Helicopter H1 654 33 20
Helicopter H2 22 33 44
Helicopter H3 98 68 99
Baloon B3 102 22 34
JetPlane J2 11 99 768
Helicopter H4 223 23 54
```
