package casim.model.codi.cell.builder;

import java.util.EnumMap;
import java.util.Optional;

import casim.model.codi.cell.CoDiCell;
import casim.model.codi.cell.attributes.CellState;
import casim.model.codi.cell.attributes.Direction;

/**
 * A builder for {@link CoDiCell}.
 */
public interface CoDiCellBuilder {

    /**
     * Set the {@link CellState}.
     * 
     * @param state the state of the cell.
     * @return the current {@link CoDiCellBuilder}. 
     */
    CoDiCellBuilder state(CellState state);

    /**
     * Set the gate.
     * 
     * @param gate the gate to set.
     * @return the current {@link CoDiCellBuilder}. 
     */
    CoDiCellBuilder gate(Optional<Direction> gate);

    /**
     * Set the chromosome.
     * 
     * @param chromosome the value so set.
     * @return the current {@link CoDiCellBuilder}. 
     */
    CoDiCellBuilder chromosome(EnumMap<Direction, Boolean> chromosome);

    /**
     * Set the activation counter.
     * 
     * @param activationCounter the value of the activationCounter to set.
     * @return the current {@link CoDiCellBuilder}. 
     */
    CoDiCellBuilder activationCounter(int activationCounter);

    /**
     * Set the {@link EnumMap} containing the values of the previous neighbors inputs.
     * 
     * @param neighborsPreviousInput the {@link enumMap} top set as previous inputs.
     * @return the current {@link CoDiCellBuilder}. 
     */
    CoDiCellBuilder neighborsPreviousInput(EnumMap<Direction, Integer> neighborsPreviousInput);

    /**
     * Build the {@link CoDiCell}.
     * 
     * @return the new {@link sCoDiCell}.
     */
    CoDiCell build();

}
