package casim.model.wator;

import casim.model.abstraction.cell.AbstractCell;

/**
 * A cell of the {@link casim.model.wator.Wator} Automaton.
 */
public class WatorCell extends AbstractCell<CellState> {

    private static final int MIN_HEALTH = 0;
    private static final int MAX_HEALTH = 10;
    private static final int PRED_HEAL = 5;
    private static final int PREY_HEAL = 1;

    private final int maxHealth;
    private int health;
    private boolean moved = false;


    /**
     * Constructs a new {@link WatorCell}.
     * 
     * @param state the {@link CellState} of the cell.
     * @param health the int value representing the health of the cell.
     * @param maxHealth the int value representing the maximum health of the cell.
     */
    public WatorCell(final CellState state, final int health, final int maxHealth) {
        super(state);
        this.health = health;
        this.maxHealth = maxHealth;
    }


    /**
     * Returns the health value of the {@link WatorCell}.
     * @return integer value representing the current health
     * of the {@link WatorCell}
     */
    public int getHealth() {
        return this.health;
    }

    /**
     * Returns the maximum health value of the {@link WatorCell}.
     * 
     * @return the interger value representing the maximum health
     * of the {@link WatorCell}
     */
    public int getMaxHealth() {
        return this.maxHealth;
    }

    /**
     * Sets the {@link CellState} of the {@link WatorCell}.
     * 
     * @param state the {@link CellState} to be set as the new
     * state of the {@link WatorCell}
     */
    public void setState(final CellState state) {
        this.state = state;
    }

    /**
     * Sets the current health value of the {@link WatorCell}.
     * 
     * @param health the integer value to be set as
     * the current health value of the {@link WatorCell}.
     * Must be greater or equal to 0 and smaller or equal to 
     * the maximum health value.
     * 
     * @throws IllegalArgumentException if the value given as
     * argument is less than 0 or greater than the maximum health
     * value.
     */
    public void setHealth(final int health) {
        if (health < MIN_HEALTH || health > this.maxHealth) {
            throw new IllegalArgumentException("Heath value out of valid limits: MAX: " + this.maxHealth
                + " MIN: " + MIN_HEALTH + " GIVEN: " + health);
        }
        this.health = health;
    }

    /**
     * Returns true if the {@link WatorCell} has
     * reached minimum health value, false otherwise.
     * 
     * @return true if the {@link WatorCell} has run
     * out of health, false otherwise
     */
    public boolean isDead() {
        return this.health == MIN_HEALTH;
    }

    /**
     * Heals the {@link WatorCell} if health isn't at
     * maximum.
     */
    public void heal() {
        if (this.getState().equals(CellState.PREY)) {
            this.health += PREY_HEAL;
        } else {
            this.health += PRED_HEAL;
        }
        int overflow = (this.health > this.maxHealth) ? this.health - this.maxHealth : 0;
        this.health -= overflow;
    }

    /**
     * Diminishes the health of the {@link WatorCell} by
     * 1 if health isn't at minimum.
     */
    public void starve() {
        this.health -= this.isDead() ? 0 : 1;
    }

    /**
     * Returns the {@link WatorCell} that the cell calling
     * the method will leave behind if it moves and updates
     * health of calling cell accordingly.
     * 
     * @return a new {@link WatorCell} with the same state as
     * the one calling the method if it can reproduce, dead
     * otherwise.
     */
    public WatorCell reproduce() {
        if (this.health == this.maxHealth) {
            this.health = this.maxHealth / 2;
            return new WatorCell(this.getState(), this.maxHealth / 2, this.maxHealth);
        } else {
            return new WatorCell(CellState.DEAD, MIN_HEALTH, this.maxHealth);
        }
    }

    /**
     * Returns true if the {@link WatorCell} can move.
     * 
     * @return true if the cell can performe a movement,
     * false otherwise.
     */
    public boolean hasMoved() {
        return this.moved;
    }

    /**
     * Sets the moved state of the {@link WatorCell}.
     */
    public void move() {
        this.moved = true;
    }

    /**
     * Resets the moved state of the {@link WatorCell}.
     */
    public void resetMovement() {
        this.moved = false;
    }

}
