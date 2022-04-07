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

    @Test
    void testHeal() {
        prey.heal();
        Assert.assertEquals(PREY_HEAL + PREY_HEAL, prey.getHealth());
        pred.heal();
        Assert.assertEquals(PREY_HEAL + PRED_HEAL, pred.getHealth());
        dead.heal();
        Assert.assertEquals(DEAD_HEALTH, dead.getHealth());
    }

    @Test
    void testSetHealth() {
        prey.setHealth(PRED_HEAL);
        Assert.assertEquals(PRED_HEAL, prey.getHealth());
        Assert.assertThrows(IllegalArgumentException.class, () -> {
            prey.setHealth(MAX_HEALTH + PREY_HEAL);
        });
    }
}
