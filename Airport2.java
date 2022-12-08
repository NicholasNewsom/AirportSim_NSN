
import java.util.*;
import java.io.IOException;

public class Airport2 
{
	public class runway {
		Airplane2[] runway;
		PriorityQueue<Airplane2> landingQueue;
		Queue<Airplane2> takeOffQueue;	
		public runway(int amountOfRunways)
		{
			runway = new Airplane2[amountOfRunways];
			landingQueue = new PriorityQueue<>();
			takeOffQueue = new LinkedList<>();
		}
		
		public void update()
		{
			/**
			 * take off queue iteration updates and moving to landing queue
			 */
			for(Airplane2 plane: takeOffQueue)
			{
				plane.update();
			}
			if(takeOffQueue.peek() != null && takeOffQueue.peek().fuel == 0)
			{
				Airplane2 airplane = takeOffQueue.poll();
				landingQueue.add(airplane);
			}
			
			/**
			 * landing queue updates
			 */
			for(Airplane2 plane: landingQueue)
			{
				plane.update();
			}
			
			for(int i = 0; i < runway.length; i++)
			{
				if(runway[i] != null && runway[i].runwayTimeLeft==0)
				{
					runway[i] = null;
				}
				else if(runway[i] != null)
				{
					runway[i].inRunway = true;
					runway[i].update();
				}
			}
			if(landingQueue.peek() != null)
			{
				for(int i = 0; i < runway.length; i++)
				{
					if(runway[i] == null)
					{
						runway[i] = landingQueue.poll();
						break;
					}
				}
			}
		}
	}
	
	/**
	 * making the airport
	 */
	runway runway = new runway(3);
	
	/**
	 * clearing screen on tick
	 */
	public static void clear()
	{
		try
		{
			if(System.getProperty("os.name").contains("Windows"))
				new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
			else
				Runtime.getRuntime().exec("clear");
		}
		catch (IOException | InterruptedException ex) 
		{
			
		}
	}
	
	/**
	 * creating an airport sim
	 * @param landFreq
	 */
	public void sim(double landFreq)
	{
		Timer timer = new Timer();
		TimerTask task = new TimerTask() {
			int time = 0;
			public void run()
			{
				time++;
				if(Math.random() < landFreq)
				{
					Airplane2 airplane = new Airplane2();
					runway.takeOffQueue.add(airplane);
				}
				runway.update();
				clear();
				
				System.out.println("Airport Sim Current time: " + time);
				System.out.println("\ntaking off:");
				for(Airplane2 plane: runway.takeOffQueue)
				{
					System.out.println(plane);
				}
				
				System.out.println("\nlanding: ");
				for(Airplane2 plane: runway.landingQueue)
				{
					System.out.println(plane);
				}
				
				System.out.println("\nRunways: ");
				for(Airplane2 plane: runway.runway)
				{
					if(plane != null)
						System.out.println(plane);
				}
			}
		};
		timer.schedule(task, 0, 3000);
		
	}
	

}

