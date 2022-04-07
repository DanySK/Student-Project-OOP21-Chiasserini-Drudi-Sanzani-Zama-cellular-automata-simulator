package casim.wator;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import casim.model.wator.WatorCell;
import casim.model.wator.WatorCellState;

/**
 * Test class for {@link WatorCell}.
 */
class WatorCellTest {
    private static final int MAX_HEALTH = 10;
    private static final int DEAD_HEALTH = 0;
    private static final int PREY_HEAL = 1;
    private static final int PRED_HEAL = 5;
    private static final WatorCell prey = new WatorCell(WatorCellState.PREY, PREY_HEAL);
    private static final WatorCell pred = new WatorCell(WatorCellState.PREDATOR, PREY_HEAL);
    private static final WatorCell dead = new WatorCell(WatorCellState.DEAD, DEAD_HEALTH);

    /**
     * Test for {@link WatorCell#heal} method.
     */
    @Test
    void testHeal() {
        prey.heal();
        Assert.assertEquals(PREY_HEAL + PREY_HEAL, prey.getHealth());
        pred.heal();
        Assert.assertEquals(PREY_HEAL + PRED_HEAL, pred.getHealth());
        dead.heal();
        Assert.assertEquals(DEAD_HEALTH, dead.getHealth());
    }

    /**
     * Test for {@link WatorCell#setHealth(int)} method.
     */
    @Test
    void testSetHealth() {
        prey.setHealth(PRED_HEAL);
        Assert.assertEquals(PRED_HEAL, prey.getHealth());
        Assert.assertThrows(IllegalArgumentException.class, () -> {
            prey.setHealth(MAX_HEALTH + PREY_HEAL);
        });
    }

    /**
     * Test for {@link WatorCell#starve} method.
     */
    @Test
    void testStarve() {
        final var initHealth = pred.getHealth();
        prey.starve();
        Assert.assertEquals(initHealth - PREY_HEAL, prey.getHealth());
    }

    /**
     * Test for {@link WatorCell#reproduce()} method.
     */
    @Test
    void testReproduce() {
        final var deadPreySpawn = prey.reproduce();
        Assert.assertTrue(deadPreySpawn.isDead());
        Assert.assertEquals(WatorCellState.DEAD, deadPreySpawn.getState());
        prey.setHealth(MAX_HEALTH);
        final var preySpawn = prey.reproduce();
        Assert.assertEquals(PREY_HEAL, prey.getHealth());
        Assert.assertEquals(WatorCellState.PREY, preySpawn.getState());
        Assert.assertEquals(PREY_HEAL, preySpawn.getHealth());
        final var deadPredSpawn = pred.reproduce();
        Assert.assertTrue(deadPredSpawn.isDead());
        Assert.assertEquals(WatorCellState.DEAD, deadPredSpawn.getState());
        pred.setHealth(MAX_HEALTH);
        final var predSpawn = pred.reproduce();
        Assert.assertEquals(MAX_HEALTH / 2, predSpawn.getHealth());
        Assert.assertEquals(WatorCellState.PREDATOR, predSpawn.getState());
        Assert.assertEquals(MAX_HEALTH / 2, pred.getHealth());
    }

    /**
     * Test for {@link WatorCell#clone(WatorCell)} method.
     */
    @Test
    void testClone() {
        dead.clone(prey);
        Assert.assertEquals(prey.getState(), dead.getState());
        Assert.assertEquals(prey.getHealth(), dead.getHealth());
        Assert.assertEquals(prey.hasMoved(), dead.hasMoved());
    }
}
