package no.hvl.dat100.prosjekt;

import java.util.*;
import java.text.SimpleDateFormat;

public class GPSUtils {

	public GPSUtils() {
	
	}
	
	// konverter sekunder til string på formen hh:mm:ss
	public static String printTime(int secs) {
		
		String timestr = "";
		
		// TODO
		// OPPGAVE - START
		
		Date d = new Date(secs * 1000L);
		SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
		df.setTimeZone(TimeZone.getTimeZone("GMT"));

		timestr = df.format(d);
		// OPPGAVE - SLUTT
		
		return timestr;
	}
	
	// beregn maximum av en tabell av doubles med minnst et element
	public static double findMax(double[] da) {

		double max = da[0];

		for (double d : da) {
			if (d > max) {
				max = d;
			}
		}

		return max;
	}

	// beregn minimum av en tabell av doubles med minnst et element
	public static double findMin(double[] da) {

		// fjern = "0.0" når metoden implementeres for ikke få forkert minimum
		double min = da[0]; 

		// TODO
		// OPPGAVE - START
		for (double d: da) {
			if (d < min) {
				min = d;
			}
		}
		// OPPGAVE - SLUT
		return min;
	}

	
	private static int R = 6371000; // jordens radius
	
	// Beregn avstand mellom to gps punkter ved bruk av Haversine formlen
	public static double distance(double latitude1, double longitude1, double latitude2, double longitude2) {

		double a,c,d; // fjern = 1.0
		
		// TODO:
		// OPPGAVE - START
		double la1 = Math.toRadians(latitude1);
		double lo1 = Math.toRadians(longitude1);
		double la2 = Math.toRadians(latitude2);
		double lo2 = Math.toRadians(longitude2);
		
		double diffLa = la2 - la1;
		double diffLo = lo2 - lo1;
		
		a = Math.pow(Math.sin(diffLa/2), 2) + (Math.cos(la1) * Math.cos(la2) * Math.pow(Math.sin(diffLo/2),2));
		c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt((1-a)));
		d = R * c;
		// OPPGAVE - SLUTT

		return d;
	}
	
	// beregn gjennomsnits hastighet i km/t mellom to gps punkter
	public static double speed(int secs, double latitude1, double longitude1, double latitude2, double longitude2) {

		double speed = 0.0;

		// TODO:
		// OPPGAVE - START
		double distanse = distance(latitude1, longitude1, latitude2, longitude2);
		int tid = secs;
		
		
		speed = (distanse/tid)*3.6;
		// OPPGAVE - SLUTT

		return speed;
	}
	
	private static int TEXTWIDTH = 10;
	
	// konverter double til string med 2 decimaler og streng lengde 10
	// eks. 1.346 konverteres til "      1.35" og enhet til slutt
	// Hint: se på String.format metoden
	
	public static String printDouble(double d) {
		
		String str = "";
		
		// TODO
		// OPPGAVE - START
		
		String d_2 = String.format("%.2f",d);
		str = String.format("%10s", d_2).replace(",", ".");
		
		
		// OPPGAVE - SLUTT
		
		return str;
	}
}
