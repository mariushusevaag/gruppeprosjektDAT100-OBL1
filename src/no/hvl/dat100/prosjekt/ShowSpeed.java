package no.hvl.dat100.prosjekt;

import javax.swing.JOptionPane;

import easygraphics.EasyGraphics;

public class ShowSpeed extends EasyGraphics {
	
	private static int[] times;
	private static double[] latitudes;
	private static double[] longitudes;
	private static double[] elevations;
		
	private static int MARGIN = 50;
	private static int BARHEIGHT = 200; // assume no speed above 200 km/t
	
	private static GPSComputer gpscomputer; 
	
	public ShowSpeed() {

		String filename = JOptionPane.showInputDialog("GPS data filnavn: ");
		
		GPSData gpsdata = GPSDataReaderWriter.readGPSFile(filename);
		 
		gpscomputer = new GPSComputer(gpsdata);
		
		times = gpscomputer.times;
		latitudes = gpscomputer.latitudes;
		longitudes = gpscomputer.longitudes;
		elevations = gpscomputer.elevations;
	}
	
	// read in the files and draw into using EasyGraphics
	public static void main(String[] args) {
		launch(args);
	}

	public void run() {

		int N = times.length-1; // number of data points
		
		makeWindow("Speed profile", 2*MARGIN + 2 * N, 2 * MARGIN + BARHEIGHT);
		
		showSpeedProfile(MARGIN + BARHEIGHT,N);
	}
	
	public void showSpeedProfile(int ybase, int N) {
		
		System.out.println("Angi tidsskalering i tegnevinduet ...");
		int timescaling = Integer.parseInt(getText("Tidsskalering"));
		
		// hent hastigheter på de ulike deler av ruten
		double[] speeds = gpscomputer.speeds();
		
		// TODO:
		
		// OPPGAVE - START		
		for (int i = 0; i < speeds.length; i++) {
			
			setColor(0, 0, 255);
			int x1, y1, x2, y2; // koordinator søylen 
			
			x1 = 2*MARGIN+2*N; //Bruker i*2 for å få litt mellomrom
			y1 = ybase;
			x2 = x1;
			y2 = ybase-(int)speeds[i]; //Må trekke fra pga. easygraphics origo
			
			int Linje = drawLine(x1,y1,x2,y2);
			moveLine(Linje, MARGIN+i*2, ybase);
			
			//Henter snittfart fra GPSComputer
			int snittfart = (int)gpscomputer.averageSpeed();
			setColor(0,255,0);
			
			int snittLinje = drawLine(0, ybase-snittfart, 2*MARGIN+2*N, ybase-snittfart);
			//Legger inn pause
			pause(times[i]/timescaling);
		}
		// OPPGAVE - SLUTT
	}
}
