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

	
	/*
	 * Adds an angle to the linked list or comparator. 
	 * If the comparator is null it sets it to the angle, 
	 * otherwise it is added to the list.
	 */
	@Override
	public boolean add(Angle ang)
	{
		if(_canonical == null)
		{
			_canonical = ang;
			return true;
		}
		
		if(ang == null) return false;
		if(contains(ang)) return false;
		if(!(belongs(ang))) return false;
		
		if(_comparator.compare(ang, _canonical)== -1); 
		{ 
			_rest.addToBack(_canonical);
			_canonical = ang;
		}
		_rest.addToBack(ang);

		return true;
	}

	
	//removes the canonical element and sets it to the smallest angle
	@Override
	public boolean removeCanonical()
	{
		if(_canonical == null || _rest.isEmpty()) return false;
		_canonical = getSmallestAngle(); 
		return true;
	}
	
	//removes an item from the linkedlist or canonical
	public boolean remove(Angle target)
	{
		if(target.equals(_canonical)) return removeCanonical(); 
		return _rest.remove(target);
	}
	
	//returns the smallest angle in the linked list
	private Angle getSmallestAngle()
	{
		if(_rest.isEmpty()) return null;
		Angle smallest = _rest.getIndex(0);
		
		for(int i = 0; i < _rest.size() - 1; i++)
		{
			if(_comparator.compare(_rest.getIndex(i), smallest) == -1) smallest = _rest.getIndex(i);
		}	
		return smallest;
	}

	//checks to see if an angle conforms to the comparator
	@Override 
	public boolean belongs(Angle ang)
	{
		if(ang == null) return false;
		if(_canonical == null) return true;
		return _comparator.compare(_canonical, ang) == -1;
		
	}
	
	
}