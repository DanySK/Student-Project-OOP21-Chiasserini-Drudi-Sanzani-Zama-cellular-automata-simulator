package casim.model.cell;

/**
 * An abstract which describes a generic 3D {@link Cell}, it doesn't specify the finite states type.
 * 
 * @param <T> the type of the finite states of the {@link Cell}.
 */
public abstract class Abstract3DCell<T> implements Cell<T> {

    /**
     * {@inheritDoc}
     * 
     */
    @Override
    public abstract CellAttributes<T> getAttributes();
}
