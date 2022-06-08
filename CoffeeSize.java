package cafe;

/**
This enum class holds all the coffee sizes
Each size has a multiplier to help calculate the price of coffee.
@author Dharma Wijesinghe, Min Sun You
*/
public enum CoffeeSize {
    SHORT (0),
    TALL (1),
    GRANDE (2),
    VENTI (3);
    
    private final int multiplier;
    
    /**
    Sets the multipler to each respective size constant.
    @param multiplier, the multiplier.
    */
    CoffeeSize(int multiplier){
        this.multiplier = multiplier;
    }
    
    /**
    Returns the multiplier of a coffee size object.
    @return the multiplier.
    */
    public int getMultiplier() {
        return multiplier;
    }
}