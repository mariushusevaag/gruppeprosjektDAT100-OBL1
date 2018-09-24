package no.hvl.dat100.prosjekt;

import easygraphics.EasyGraphics;
import javax.swing.JOptionPane;

public class ShowProfile extends EasyGraphics {

	private static int[] times;
	private static double[] latitudes;
	private static double[] longitudes;
	private static double[] elevations;

	private static int MARGIN = 50;
	private static int BARHEIGHT = 500; // assume no height above 500 meters

	private static GPSData gpsdata;

	public ShowProfile() {

		String filename = JOptionPane.showInputDialog("GPS data filnavn: ");
		gpsdata = GPSDataReaderWriter.readGPSFile(filename);

		GPSDataConverter converter = new GPSDataConverter(gpsdata);
		converter.convert();

		times = converter.times;
		latitudes = converter.latitudes;
		longitudes = converter.longitudes;
		elevations = converter.elevations;
	}

	// read in the files and draw into using EasyGraphics
	public static void main(String[] args) {
		launch(args);
	}

	public void run() {

		int N = elevations.length; // number of data points

		makeWindow("Height profile", 2 * MARGIN + 3 * N, 2 * MARGIN + BARHEIGHT);

		// top margin + height of drawing area
		showHeightProfile(MARGIN + BARHEIGHT); 
	}

	public void showHeightProfile(int ybase) {

		int N = elevations.length;
		System.out.println("Angi tidsskalering i tegnevinduet ...");
		int timescaling = Integer.parseInt(getText("Tidsskalering"));

		drawString ("500 meter", 0, MARGIN);
		drawString ("0 meter", 0, ybase);
		drawString ("250 meter", 0, ybase/2);
		setColor(0, 0, 255);

		// elevations tabellen innholder alle høydedata
		for (int i = 0; i < elevations.length; i++) {

			int x1, y1, x2, y2; // koordinator søylen 

			// TODO
			// OPPGAVE - START

			// regn ut koordinator for søylen / linjen
			// (x1,y1) er startpunkt for søylen (linjen)
			// (x2,y2) er slutt punkt.

			x1 = 2*MARGIN+3*N; //Bruker i*2 for å få litt mellomrom
			y1 = ybase;
			x2 = x1;
			y2 = ybase-(int)elevations[i]; //Må trekke fra pga. easygraphics origo
			int Linje = drawLine(x1,y1,x2,y2);
			moveLine(Linje, MARGIN+i*2, ybase);
			
			pause(times[i]/timescaling);
			
			// OPPGAVE - SLUTT
			
		}	
	}

}
