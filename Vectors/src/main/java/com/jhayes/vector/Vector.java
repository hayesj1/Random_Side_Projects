package main.java.com.jhayes.vector;

public class Vector {

	protected Double x = null;
	protected Double y = null;
	protected Double z = null;
	protected Double magnitude = null;
	protected Double theta = null;
	protected Double phi = null;
	
	public Vector(Double magnitude, Double theta, Double phi, boolean inRads) {
		Dimensions dimension = ((theta == null) ? Dimensions.ONEDIM : ((phi == null) ? Dimensions.TWODIM : Dimensions.THREEDIM));
		if(!inRads) {
			theta = (theta != null) ? Math.toRadians(theta) : null;
			phi = (phi != null) ? Math.toRadians(phi) : null;
		}
		switch(dimension) {
			case THREEDIM:
				this.x = Math.cos(phi) * (Math.cos(theta) * magnitude);
				this.y = Math.sin(theta) * magnitude;
				this.z = Math.cos(90 - phi) * (Math.cos(theta) * magnitude);
				break;
			case TWODIM:
				this.x = Math.cos(theta) * magnitude;
				this.y = Math.sin(theta) * magnitude;
				this.z = 0.0;
				break;
			case ONEDIM:
				this.x = magnitude;
				this.y = 0.0;
				this.z = 0.0;
				break;
			default:
				break;
		}
		this.magnitude = magnitude;
		this.theta = theta;
		this.phi = phi;
	}
	public Vector(Dimensions dimension, Double x, Double y, Double z) {
		switch(dimension) {
		case THREEDIM:
			this.z = z;
		case TWODIM:
			this.y = y;
		case ONEDIM:
			this.x = x;
			break;
		default:
			break;
		}
		switch(dimension) {
		case THREEDIM:
			double temp = Math.hypot(this.x, this.z);
			magnitude = Math.hypot(temp, this.y);
			theta = Math.acos(temp / this.magnitude);
			phi = Math.acos(this.x / temp);
			break;
		case TWODIM:
			magnitude = Math.hypot(this.x, this.y);
			theta = Math.acos(this.x / this.magnitude);
			phi = null;
			break;
		case ONEDIM:
			magnitude = x;
			theta = null;
			phi = null;
			break;
		default:
			break;
		}
	}

	public void validateAngles() {
		if(this.theta != null) { this.theta = Math.acos(this.x / this.magnitude); }
		if(this.phi != null) { this.phi = Math.acos(this.x / Math.hypot(this.x, this.z)); }
	}
	
	public Double getX() { return x; }
	public Double getY() { return y; }
	public Double getZ() { return z; }
	public Double getMagnitude() { return magnitude; }
	public Double getTheta() { return theta; }
	public Double getPhi() { return phi; }

	@Override
	public String toString() { return String.format("Magnitude: %.4f, Theta: %.4f degrees, Phi: %.4f degrees", magnitude, Math.toDegrees(theta), Math.toDegrees(phi)); }
}
