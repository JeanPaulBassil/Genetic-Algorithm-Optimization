import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class GA {
    // Population arrays for old and new generations
    private Individual[] oldPop;
    private Individual[] newPop;

    // Various parameters for the genetic algorithm
    private int popSize;
    private double fitnessSum;
    private double crossProba;
    private double mutationProba;
    private int lchrom;
    private double min;
    private double max;
    private double avg;
    private int maxGen;

    // Initialize population and parameters based on user input
    private void initialize(){
        Scanner scan = new Scanner(System.in);

        // Take various inputs from the user
        System.out.print("Enter population size ---- > ");
        popSize = scan.nextInt();
        System.out.println();
        System.out.print("Enter the cross probability (%) ---- > ");
        crossProba = scan.nextInt() / 100.0;
        System.out.println();
        System.out.print("Enter the mutation probability (%) ---- > ");
        mutationProba = scan.nextInt() / 100.0;
        System.out.println();
        System.out.print("Enter the length of the chromosome ---- > ");
        lchrom = scan.nextInt();
        System.out.println();

        // Initialize the populations
        oldPop = new Individual[popSize];
        newPop = new Individual[popSize];

        // Create individuals in the old population
        for (int i = 0; i < oldPop.length; i++){
            oldPop[i] = new Individual(lchrom);
            for (int j = 0; j < oldPop[i].getChromosome().length; j++){
                oldPop[i].getChromosome()[j] = flip(0.5);
            }
            // Initialize new population
            newPop[i] = new Individual(lchrom);
        }

        // Take the number of generations as input
        System.out.print("Enter the number of generations ---- > ");
        maxGen = scan.nextInt();
        System.out.println();
    }

    // Calculate statistics like total, min, max, average fitness
    private void statistics(){
        fitnessSum = newPop[0].getFitness();
        min = newPop[0].getFitness();
        max = newPop[0].getFitness();

        // Iterate through the population to calculate stats
        for (int i = 1; i < newPop.length; i++){
            fitnessSum += newPop[i].getFitness();
            min = Math.min(min, newPop[i].getFitness());
            max = Math.max(max, newPop[i].getFitness());
        }

        // Calculate the average fitness
        avg = fitnessSum / popSize;
    }

    // Generate a random number between 0 and 1
    private double random(){
        Random rand = new Random();
        int randomNum = rand.nextInt(101);
        return randomNum / 100.0;
    }

    // Simulate a biased coin flip
    private boolean flip(double probability){
        Random rand = new Random();
        int randomNum = rand.nextInt(101);
        return probability * 100 >= randomNum;
    }

    // Returns a random number between specified limits
    private int rnd(int start, int end){
        Random rand = new Random();
        return rand.nextInt(end - start + 1) + start;
    }

    // Roulette wheel selection
    private int select(){
        int partSum = 0;
        double rand = random() * fitnessSum;

        // Select an individual from the population
        for (int i = 0; i < popSize; i++){
            partSum += oldPop[i].getFitness();
            if (partSum >= rand) return i;
        }
        return popSize - 1;
    }

    // Mutation of a single allele
    private boolean mutation(boolean allele, double proba){
        if (flip(proba))
            return !allele;
        else return allele;
    }

    // Single point crossover
    private void crossOver(boolean[] parent1, boolean[] parent2, boolean[] child1, boolean[] child2){
        int crossIndex = parent1.length;

        // Check if crossover should occur
        if (flip(crossProba)){
            crossIndex = rnd(1, parent1.length);
        }

        // Perform crossover and mutation
        for (int i = 0; i < crossIndex; i++){
            child1[i] = parent1[i];
            child2[i] = parent2[i];
        }
        for (int i = crossIndex; i < parent1.length; i++){
            child1[i] = parent2[i];
            child2[i] = parent1[i];
        }
        for (int i = 0; i < child1.length; i++){
            child1[i] = mutation(child1[i], mutationProba);
            child2[i] = mutation(child2[i], mutationProba);
        }
    }

    // Decode binary chromosome to decimal
    private long decode(boolean[] allele){
        int total = 0;
        for (int i = 0; i < allele.length; i++){
            if (allele[allele.length - 1 - i])
                total += Math.pow(2, i);
        }
        return total;
    }

    // Fitness function
    private double fitness(long x){
        return Math.pow(x, 2);
    }

    // Generate a new population from the old population
    private void generation(){
        for (int i = 0; i < popSize; i += 2){
            int mate1 = select();
            int mate2 = select();
            crossOver(oldPop[mate1].getChromosome(), oldPop[mate2].getChromosome(), newPop[i].getChromosome(), newPop[i + 1].getChromosome());
            long x = decode(newPop[i].getChromosome());
            newPop[i].setX(x);
            double fitness = fitness(x);
            newPop[i].setFitness(fitness);
            x = decode(newPop[i + 1].getChromosome());
            newPop[i + 1].setX(x);
            fitness = fitness(x);
            newPop[i + 1].setFitness(fitness);
        }
    }

    // Execute the entire genetic algorithm
    public void mainProcess(){
        initialize();
        for (int i = 0; i < maxGen; i++){
            generation();
            statistics();
            report(i);
            oldPop = newPop;
        }
    }

    // Print report of each generation's stats
    public void report(int gen){
        System.out.println("                 Generation " + gen + ": ");
        System.out.println();
        System.out.println("- Total fitness: " + fitnessSum);
        System.out.println("- Average fitness: " + avg);
        System.out.println("- Max fitness: " + max);
        System.out.println("- Min fitness: " + min);
        System.out.println("---------------------------------------------------------------");
    }

    // Main method to run the program
    public static void main(String[] args){
        GA ga = new GA();
        ga.mainProcess();
    }
}
