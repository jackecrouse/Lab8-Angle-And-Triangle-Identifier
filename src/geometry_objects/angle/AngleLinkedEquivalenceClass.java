package geometry_objects.angle;

import geometry_objects.angle.comparators.AngleStructureComparator;

import java.util.Comparator;

import equivalence.LinkedEquivalenceClass;

/**
 * This implementation requires greater knowledge of the implementing Comparator.
 * 
 * According to our specifications for the AngleStructureComparator, we have
 * the following cases:
 *
 *    Consider Angles A and B
 *    * Integer.MAX_VALUE -- indicates that A and B are completely incomparable
                             STRUCTURALLY (have different measure, don't share sides, etc. )
 *    * 0 -- The result is indeterminate:
 *           A and B are structurally the same, but it is not clear one is structurally
 *           smaller (or larger) than another
 *    * 1 -- A > B structurally
 *    * -1 -- A < B structurally
 *    
 *    We want the 'smallest' angle structurally to be the canonical element of an
 *    equivalence class.
 * 
 * @author Jack Crouse and Ian Cho
 */
public class AngleLinkedEquivalenceClass extends LinkedEquivalenceClass<Angle>
{	
	public AngleLinkedEquivalenceClass() 
	{
		super(new AngleStructureComparator()); //CHECK
	}

	@Override
	public boolean add(Angle ang)
	{
		if(ang == null) return false;
		if(contains(ang)) return false;
		if(!(belongs(ang))) return false;
		
		if(_canonical == null) _rest.addToFront(ang);
		if(_canonical.compareTo(ang) == -1) 
		{
			_rest.addToBack(_canonical);
			_canonical = ang;
		}
		_rest.addToBack(ang);

		return true;
	}

	@Override
	public boolean removeCanonical()
	{
		if(_canonical == null || _rest.isEmpty()) return false;
		_canonical = getSmallestAngle(); 
		return true;
	}
	
	public boolean remove(Angle target)
	{
		if(target.equals(_canonical)) return removeCanonical(); 
		return _rest.remove(target);
	}
	
	private Angle getSmallestAngle()
	{
		if(_rest.isEmpty()) return null;
		Angle smallest = _rest.getIndex(0);
		
		for(int i = 0; i < _rest.size() - 1; i++)
		{
			if(_rest.getIndex(i).compareTo(smallest) == -1) smallest = _rest.getIndex(i);
		}	
		return smallest;
	}

	@Override public boolean belongs(Angle ang)
	{
		return _comparator.compare(_canonical, ang) == 1;
	}
}