
class Dice {
    DiceStrategy DS;
    Dice(DiceStrategy DS) {
        this.DS = DS;
    }
    public int rollDice() {
        return DS.roll();
    }
}