package humans;

import generator.Generator;

public abstract class Human {

    public String name;
    public Double price;

    public Boolean isSick;
    public int sickDays;

    Generator generator = new Generator();
    public void sick ()
    {
        if (!isSick) {
            generator.dice = generator.rollDice(101);
            if (generator.dice <= 30) {
                isSick = true;
                sickDays += 5;

            }
        }
    }
}
