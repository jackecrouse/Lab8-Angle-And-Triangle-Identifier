package geometry_objects.angle;

import geometry_objects.angle.comparators.AngleStructureComparator;

import java.util.Comparator;

import equivalence.EquivalenceClasses;

/**
 * Given the figure below:
 * 
 *    A-------B----C-----------D
 *     \
 *      \
 *       \
 *        E
 *         \
 *          \
 *           F
 * 
 * Equivalence classes structure we want:
 * 
 *   canonical = BAE
 *   rest = BAF, CAE, DAE, CAF, DAF
 */

public class AngleEquivalenceClasses extends EquivalenceClasses<Angle>
{
	public AngleEquivalenceClasses(Comparator<Angle> comparator) 
	{
		super(comparator);
	}	
	
	public AngleEquivalenceClasses()
	{
		super(new AngleStructureComparator());
	}
}
