/**
 * Write a succinct, meaningful description of the class here. You should avoid wordiness    
 * and redundancy. If necessary, additional paragraphs should be preceded by <p>,
 * the html tag for a new paragraph.
 *
 * <p>Bugs: (a list of bugs and / or other problems)
 *
 * @author <your name>
 * @date   <date of completion>
 */

package geometry_objects.angle.comparators;

import java.util.Comparator;

import geometry_objects.Segment;
import geometry_objects.angle.Angle;
import geometry_objects.points.Point;
import utilities.math.MathUtilities;
import utilities.math.analytic_geometry.GeometryUtilities;

public class AngleStructureComparator implements Comparator<Angle>
{
	public static final int STRUCTURALLY_INCOMPARABLE = Integer.MAX_VALUE;
	
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
	 * What we care about is the fact that angle BAE is the smallest angle (structurally)
	 * and DAF is the largest angle (structurally). 
	 * 
	 * If one angle X has both rays (segments) that are subsegments of an angle Y, then X < Y.
	 * 
	 * If only one segment of an angle is a subsegment, then no conclusion can be made.
	 * 
	 * So:
	 *     BAE < CAE
   	 *     BAE < DAF
   	 *     CAF < DAF

   	 *     CAE inconclusive BAF
	 * 
	 * @param left -- an angle
	 * @param right -- an angle
	 * @return -- according to the algorithm above:
 	 *            Integer.MAX_VALUE will refer to our error result
 	 *            0 indicates an inconclusive result
	 */
	@Override
	public int compare(Angle left, Angle right)
	{
        //return Integer.MAX_VALUE if structurally incomparable  
		//make sure center point is the same
		// make sure both rays are colinear
		if(!(left.getRay1().isCollinearWith(right.getRay1()) && 
		   left.getRay2().isCollinearWith(right.getRay2()) &&
		   left.getVertex().equals(right.getVertex()))) {
			return Integer.MAX_VALUE;
		}
		//return 1 if both rays for left are larger then those of right
		if(left.getRay1().HasSubSegment(right.getRay1()) && left.getRay2().HasSubSegment(right.getRay2())) {
			return 1;
		}
		//return -1 if both rays for the right are larger then those of the left
		if(right.getRay1().HasSubSegment(left.getRay1()) && right.getRay2().HasSubSegment(left.getRay2())) {
			return -1;
		}
		return 0;
	}
}
