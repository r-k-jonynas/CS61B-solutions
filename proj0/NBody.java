public class NBody {
	public static double readRadius(String filename) {
		In in = new In(filename);
		in.readLine();
		return in.readDouble();
	}

	public static Body[] readBodies(String filename) {
		In in = new In(filename);
		int noOfPlanets = in.readInt();
		Body[] arrayOfBodies = new Body[noOfPlanets];
		/* Skips first two lines */
		in.readDouble();
		for (int i = 0; i < noOfPlanets; i++) {
			double xxPos = in.readDouble();
			double yyPos = in.readDouble();
			double xxVel = in.readDouble();
			double yyVel = in.readDouble();
			double mass = in.readDouble();
			String imgFileName = in.readString();

			arrayOfBodies[i] = new Body(xxPos, yyPos, xxVel, yyVel, mass, imgFileName);
		}
		return arrayOfBodies;
	}

	public static void main(String[] args) {
		double T = Double.parseDouble(args[0]); /* Converts from string to double */
		double dt = Double.parseDouble(args[0]); 
		String filename = args[2];

		Body[] bodies = readBodies(filename);
		double radius = readRadius(filename);

		/* First, set the scale so that it matches the radius 
		of the universe. Then draw the image starfield.jpg as the background */
		StdDraw.setScale(-radius, radius);
		StdDraw.picture(0, 0, "images/starfield.jpg");

		for (Body b : bodies) {
			b.draw();
		}
		StdDraw.pause(5000);
		StdDraw.clear();
		
		StdDraw.enableDoubleBuffering();
		StdDraw.setScale(-radius, radius);
		
		// double time = 0;
		for (double time = 0; time < T; time+=dt){
			double[] xForces = new double[bodies.length];
			double[] yForces = new double[bodies.length];

			for (int i=0; i < bodies.length; i++) {
				xForces[i] = bodies[i].calcNetForceExertedByX(bodies);
				yForces[i] = bodies[i].calcNetForceExertedByY(bodies);
			}

			for (int i=0; i < bodies.length; i++) {
				bodies[i].update(dt, xForces[i], yForces[i]);
			}
			
			StdDraw.clear();
			StdDraw.picture(0, 0, "images/starfield.jpg");
			
			for (Body b : bodies) {
				b.draw();
			}

			// StdDraw.clear();
			StdDraw.show();
			StdDraw.pause(20000000);
			// time += dt;
		}

		// StdDraw.clear();
		StdOut.printf("%d\n", bodies.length);
		StdOut.printf("%.2e\n", radius);
		for (int i = 0; i < bodies.length; i++) {
		    StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
		                  bodies[i].xxPos, bodies[i].yyPos, bodies[i].xxVel,
		                  bodies[i].yyVel, bodies[i].mass, bodies[i].imgFileName);
		}
	}
}