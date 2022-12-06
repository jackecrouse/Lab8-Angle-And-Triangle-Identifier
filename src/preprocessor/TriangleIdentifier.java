package preprocessor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import exceptions.FactException;
import geometry_objects.Segment;
import geometry_objects.Triangle;

public class TriangleIdentifier
{
	protected Set<Triangle>         _triangles;
	protected Map<Segment, Segment> _segments;

	public TriangleIdentifier(Map<Segment, Segment> segments)
	{
		_segments = segments;
	}

	/*
	 * Compute the figure triangles on the fly when requested;
	 * memoize results for subsequent calls.
	 */
	public Set<Triangle> getTriangles()
	{
		if (_triangles != null) return _triangles;

		_triangles = new HashSet<Triangle>();

		computeTriangles();

		return _triangles;
	}

	private void computeTriangles()
	{
		//loop for segment 1
		for(int i = 0; i < _segments.keySet().size(); i++) {
			//loop for segment 2
			for(int j = 1; j < _segments.keySet().size(); j++) {
				//loop for segment 3
				for(int k = 2; k < _segments.keySet().size(); k++) {
						
						//adding segments to a list to be made into a triangle
						ArrayList<Segment> l = new ArrayList<Segment>();
						l.add((Segment)_segments.keySet().toArray()[i]);
						l.add((Segment)_segments.keySet().toArray()[j]);
						l.add((Segment)_segments.keySet().toArray()[k]);
						try {
							//making new triangle and adding to _triangles 
							//if invalid in constructer wont be added and will fall through
							Triangle t = new Triangle(l);
							_triangles.add(t);
							
						} catch (FactException e) {
							
						}
					
				}
			}
		}
	}
}
