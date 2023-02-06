package humans;

import generator.Generator;

public abstract class Human {

    public String name;
    public Double price;

    public Boolean isSick;
    public int sickDays;

    Generator generator = new Generator();
    public boolean sick ()
    {
        if (sickDays == 0) {
            generator.dice = generator.rollDice(100);
            if (generator.dice <= 5) {
                sickDays += 5;
                return true;

            }
        }return false;
    }
}

