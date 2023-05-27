# Genetic Algorithm Optimization - Java

Welcome to this simple implementation of a genetic algorithm in Java. The goal of this project is to maximize the fitness of a binary population across multiple generations using concepts of initialization, selection, crossover, mutation, and evaluation based on a defined fitness function (f(x) = x^2).

## What the Algorithm Does

The algorithm simulates the process of evolution across generations of a binary population. It initializes a population of binary strings (chromosomes) of a specific length. Each bit in the binary string has a 50% chance of being 1 or 0.

The algorithm selects pairs of parent chromosomes from the population, based on their fitness, to produce offspring. The offspring chromosomes are produced through a process called crossover, where sections of the parent chromosomes are swapped. After crossover, each bit in the offspring chromosomes has a chance to flip (change from 0 to 1 or 1 to 0), simulating mutation.

The algorithm evaluates the fitness of each individual in the population by converting its binary chromosome into a decimal number and then applying the fitness function (f(x) = x^2). It calculates the total, average, minimum, and maximum fitness of the population for each generation.

## How to Use

Make sure you have Java installed on your machine.

To compile and run the program:

1. Navigate to the directory containing the Java file (GA.java).

2. Compile the Java file using the following command:
   javac GA.java

3. Run the compiled Java program with this command:
   java GA

The program will prompt you to input the population size, crossover probability, mutation probability, chromosome length, and the number of generations.

## Further Notes

This project is a great starting point for learning about genetic algorithms and their applications. For those interested in AI and machine learning, exploring and tweaking this code could provide valuable insights.