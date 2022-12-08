

import java.util.Random;
public class Airplane2 implements Comparable<Airplane2> 
{
	Random rand = new Random();
	public int fuel;
	public int runwayTimeLeft;
	public int planeID;
	public boolean emergency;
	public boolean inRunway;
	public Airplane2()
	{
		fuel = 7000;
		runwayTimeLeft = 60;
		planeID = rand.nextInt(900)+100;
		emergency = false;
	}
	
	/**
	 * if emergency is false (which is the case by default), generate a number. if number is <25 emergency = true
	 * @return emergency = false if randChance>25, true if otherwise
	 */
	public boolean Emergency()
	{
		if(emergency == false) 
		{
			int randChance = rand.nextInt(101)+1;
			
			if(randChance<7)
				emergency = true;	
		}
		return true;
	}
	
	/**
	 * gets priorities for the planes. If there is an emergency, decrease fuel by 500
	 * @return fuel - 500 if emergency = true, fuel if false.
	 */
	public int getPriority()
	{
		if(emergency)
			return fuel - 500;
		else
			return fuel;
	}
	
	/**
	 * updates plane values
	 */
	public void update()
	{
		if(inRunway)
		{
			runwayTimeLeft -= 5;
		}
		else
		{
			Emergency();
			fuel -= 1000;
			if(fuel<0)
				fuel = 0;
		}
	}

	/**
	 * compares priorities of 2 planes
	 * @param compPlane - plane being compared
	 * @return
	 */
	public int compareTo(Airplane2 compPlane) {
		return getPriority() - compPlane.getPriority();
	}
	
	/**
	 * displays plane details as a string
	 */
	public String toString()
	{
		if(inRunway)
		{
			return String.format("ID: %1d, emergency: %b, Time left on runway: %3d", planeID, emergency, runwayTimeLeft);
		}
		else
		{
			return String.format("ID: %1d, emergency: %b, fuel remaining: %3d", planeID, emergency, fuel);
		}
	}
}

