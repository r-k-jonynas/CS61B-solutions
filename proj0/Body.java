public class Body {
	/* Constants */
	static final double GConst = 6.67e-11;
	/* Parameters */
	/* Its current x position */
	public double xxPos;
	/* Its current y position */
	public double yyPos;
	/* Its current velocity in the x direction */
	public double xxVel;
	/* Its current velocity in the y direction */
	public double yyVel;
	/* Its mass */
	public double mass;
	/* The name of the file that corresponds to the image that depicts 
	the body (for example [jupyter.gif]*/
	public String imgFileName;

	/* Creates an instance of the Body class which can represent 
	a planet, star, or various objects in this universe */
	public Body(double xP, double yP, double xV,
              double yV, double m, String img) {
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}

	/** Creates an identical Body object copy */
	public Body(Body b) {
		xxPos = b.xxPos;
		yyPos = b.yyPos;
		xxVel = b.xxVel;
		yyVel = b.yyVel;
		mass = b.mass;
		imgFileName = b.imgFileName;
	}
	/** Helpers for calcDistance,
		Calculate Dx and Dy, respectively */
	public double calcDx(Body b) {
		return this.xxPos - b.xxPos;
	}

	public double calcDy(Body b) {
		return this.yyPos - b.yyPos;
	}

	/** Takes in a single Body and should return a double
	 equal to the distance between the supplied body and 
	 the body that is doing the calculation */
	public double calcDistance(Body b) {
		double dX = calcDx(b);
		double dY = calcDy(b);
		return Math.sqrt(dX * dX + dY * dY);
	}

	/** Takes in a Body, and returns a double describing the 
	force exerted on this body by the given body */
	public double calcForceExertedBy(Body b) {
		double dist = this.calcDistance(b);
		return (GConst * this.mass * b.mass) / (dist * dist);
	}

	/** Describes the force exerted in the X direction */
	public double calcForceExertedByX(Body b) {
		double r = this.calcDistance(b);
		double dx = (-1) * this.calcDx(b); 
		double F1 = calcForceExertedBy(b);
		return F1 * dx / r;
	}

	/** Describes the force exerted in the Y direction */
	public double calcForceExertedByY(Body b) {
		double r = this.calcDistance(b);
		double dy = (-1) * this.calcDy(b); 
		double F1 = calcForceExertedBy(b);
		return F1 * dy / r;
	}
	
	public double calcNetForceExertedByX(Body[] BodArray) {
		double totalForce = 0;
		for (Body a: BodArray) {
			if (this.equals(a)) {
				continue;
			}
			totalForce += calcForceExertedByX(a);
		}
		return totalForce;
	}

	public double calcNetForceExertedByY(Body[] BodArray) {
		double totalForce = 0;
		for (Body a: BodArray) {
			if (this.equals(a)) {
				continue;
			}
			totalForce += calcForceExertedByY(a);
		}
		return totalForce;
	}
	
	public void update(double dt, double xFnet, double yFnet) {
		double xA_net = xFnet / this.mass;
		double yA_net = yFnet / this.mass;

		this.xxVel +=  dt * xA_net;
		this.yyVel +=  dt * yA_net;
		this.xxPos += dt * this.xxVel;
		this.yyPos += dt * this.yyVel;
	}

	public void draw() {
		StdDraw.picture(this.xxPos, this.yyPos, this.imgFileName);
	}

	// public static void main(String[] args) {
	// 	Body samh = new Body(1,0,0,0,10,"samh");
	// 	Body aegir = new Body(3,3,0,0,5,"aegir");
	// 	Body rocinante = new Body(5,-3,0,0,50, "rocinante");
	// 	Body[] allBodys = {samh, rocinante, aegir};
	// 	double valueX = samh.calcNetForceExertedByX(allBodys);
	// 	System.out.println(valueX);
	// 	double valueY = samh.calcNetForceExertedByY(allBodys);
	// 	System.out.println(valueY);
	// }
}