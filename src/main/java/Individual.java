public class Individual {
    // Maximum length of the chromosome
    final int maxStringLength;

    // Binary chromosome array
    boolean[] chromosome;

    // Fitness and decoded value of the chromosome
    double fitness;
    long x;

    // Constructor
    public Individual(int i){
        maxStringLength = i;
        chromosome = new boolean[maxStringLength];
    }

    // Getters and setters for fitness and chromosome
    public double getFitness(){
        return fitness;
    }

    public boolean[] getChromosome(){
        return chromosome;
    }

    public void setFitness(double i){
        fitness = i;
    }

    // Set the decoded value of the chromosome
    public void setX(long i){
        x = i;
    }
}
