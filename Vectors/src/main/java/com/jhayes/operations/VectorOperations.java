package main.java.com.jhayes.operations;

import main.java.com.jhayes.vector.Dimensions;
import main.java.com.jhayes.vector.Vector;
import main.java.com.jhayes.vector.VectorFactory;

public class VectorOperations {

	private VectorFactory vf = null;
	private Dimensions dim = null;
	public VectorOperations(Dimensions dimension) {
		vf = new VectorFactory(dimension.getNumDimensions());
		dim = vf.getDimension();
	}
	public VectorOperations() { this(Dimensions.TWODIM); }
	
	public Vector add(Vector vec1, Vector vec2) {
		switch(dim) {
		case ONEDIM:
			return vf.buildVector((vec1.getX() + vec2.getX()), 0.0, 0.0);
		case TWODIM:
			return vf.buildVector((vec1.getX() + vec2.getX()), (vec1.getY() + vec2.getY()), 0.0);
		case THREEDIM:
			return vf.buildVector((vec1.getX() + vec2.getX()), (vec1.getY() + vec2.getY()), (vec1.getZ() + vec2.getZ()));
		default:
			return vf.buildVector((vec1.getX() + vec2.getX()), (vec1.getY() + vec2.getY()), 0.0);
		}
	}
	public Vector subtract(Vector vec1, Vector vec2) { return add(vec1, negate(vec2)); }
	public Vector dotProduct(Vector vec1, Vector vec2) {
		switch(dim) {
		case ONEDIM:
			return vf.buildVector(vec1.getX() * vec2.getX(), 0.0, 0.0);
		case TWODIM:
			return vf.buildVector(vec1.getX() * vec2.getX(), vec1.getY() * vec2.getY(), 0.0);
		case THREEDIM:
			return vf.buildVector(vec1.getX() * vec2.getX(), vec1.getY() * vec2.getY(), vec1.getZ() * vec2.getZ());
		default:
			return vf.buildVector(vec1.getX() * vec2.getX(), vec1.getY() * vec2.getY(), 0.0);
		}
	}
	public Vector crossProduct(Vector vec1, Vector vec2) {
		switch(dim) {
		case ONEDIM:
			return null;
		case TWODIM:
			vf = new VectorFactory(3);
			this.dim = vf.getDimension();
			return vf.buildVector((vec1.getMagnitude() * vec2.getMagnitude()) *
					Math.sin(Math.acos(dotProduct(vec1, vec2).getMagnitude() /
					(vec1.getMagnitude() * vec2.getMagnitude()))), 0.0, 90.0, false);
		case THREEDIM:
			return vf.buildVector((vec1.getY() * vec2.getZ() - vec1.getZ() * vec2.getY()), (vec1.getX() * vec2.getZ() - vec1.getZ() * vec2.getX()), (vec1.getX() * vec2.getY() - vec1.getY() * vec2.getX()));
		default:
			vf = new VectorFactory(3);
			this.dim = vf.getDimension();
			return vf.buildVector((vec1.getY() * vec2.getZ() - vec1.getZ() * vec2.getY()), (vec1.getX() * vec2.getZ() - vec1.getZ() * vec2.getX()), 0.0);
		}
	}
	public Vector scalarProduct(Vector vec, double multiplier) {
		switch(dim) {
			case ONEDIM:
				return vf.buildVector(vec.getX() * multiplier, 0.0, 0.0);
			case TWODIM:
				return vf.buildVector(vec.getX() * multiplier, vec.getY() * multiplier, 0.0);
			case THREEDIM:
				return vf.buildVector(vec.getX() * multiplier, vec.getY() * multiplier, vec.getZ() * multiplier);
			default:
				return vf.buildVector(vec.getX() * multiplier, vec.getY() * multiplier, 0.0);
		}
	}
	public Vector scalarProduct(Vector vec, int multiplier) { return this.scalarProduct(vec, multiplier * 1.0); }
	private Vector negate(Vector vec1) { return scalarProduct(vec1, -1); }
}
