package casim.utils.grid;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import casim.utils.Empty;
import casim.utils.Result;
import casim.utils.coordinate.Coordinates;

/**
 * Utility class for {@link Grid}.
 */
public final class Grids<T> implements Grid<T>{
    
	private List<List> list;
	
    public Grids() {
    		
    }

    /**
     * Return an unmodifiable copy of the source {@link Grid}.
     * 
     * @param <T> the type of the {@link Grid}.
     * @param source the source {@link Grid}.
     * @return a {@link Grid}, an unmodifiable copy of source.
     */
    public static <T> Grid<T> getUnmodifiableCopy(final Grid<T> source) {
        return null; //TODO
    }

	@Override
	public int getWidth() {
		return this.list.size();
	}

	@Override
	public int getHeight() {
		return this.list.get(0).size();
	}

	@Override
	public Result<T> get(int row, int column) {
		Result<Object> result;
		if(this.list.contains(this.list.get(row).get(column)))
			result=Result.of(this.list.get(row).get(column));
		else 
			result=Result.error(new Exception("IndexOutOfBoundException") );
		return (Result<T>) result;			
	}

	@Override
	public Result<T> get(Coordinates<Integer> coord) {
		Result<Object> result;
		if(this.list.contains(this.list.get(coord.getX()).get(coord.getY())))
			result=Result.of(this.list.get(coord.getX()).get(coord.getY()));
		else 
			result=Result.error(new Exception("IndexOutOfBoundException") );
		return (Result<T>) result;
	}

	@Override
	public Result<Empty> set(int row, int column, T value) {
	    list = new ArrayList <>();
	    for(int i=0;i<row;i++) {
	    	list.add(this.of(column, value));
	    }
		return null;
	}	
	
	private List<T> of(int length, T value) {
	    final List <T> temp = new ArrayList <>();
	    for(int i=0;i<length;i++) {
	    	temp.add(value);
	    }
		return temp;
	}
	
	@Override
	public Stream<List<T>> stream() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Stream<T> flatStream() {
		// TODO Auto-generated method stub
		return null;
	}
}
