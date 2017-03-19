import java.io.*;
import java.util.*;

public class EuclidDistance{

	public static void main(String[] args)throws IOException
	{
		long start = System.currentTimeMillis();

		EuclidDistance d = new EuclidDistance("point.txt");

		long end = System.currentTimeMillis();
		
		System.out.println("rum time : " + ( end - start )/1000.0 );
		System.out.println();
	}

	public EuclidDistance(String file) throws IOException
	{
		List<Double> distance = new ArrayList<Double>();
		distance = point_distance(file);
	}

	public List<String> Point(String file) throws IOException
	{
		String line;
		Integer count_line = 0;
		List<String> point = new ArrayList<String>();
		BufferedReader reader = new BufferedReader(new FileReader(file));

		while((line = reader.readLine()) != null)
		{
			String[] st = line.split(",");
			point.add(st[0]);
			point.add(st[1]);

			count_line++;
		}
		return point;
	}

	public List<Double> point_distance(String file) throws IOException
	{
		List<Double> distance = new ArrayList<Double>();
		List<String> point = Point(file);
		List<Integer> point_array = new ArrayList<Integer>();

		int j = 0;
		for(int i = 0 ; i < point.size() ; i++){
			String s = point.get(i);
			j = (int) Double.parseDouble(s);
			point_array.add(j);
		}

		String filename = "EuclidDistance_x_y_d.txt";
		for(int n = 0 ; n <= point_array.size()-2 ; n+=2){
			double x, y;
			double next_x, next_y;
			double dist;
			x = point_array.get(n);
			y = point_array.get(n+1);

			for(int m = n+2 ; m < point_array.size() ; m+=2){
				next_x = point_array.get(m);
				next_y = point_array.get(m+1);
				dist = Math.sqrt((next_x-x)*(next_x-x)+(next_y-y)*(next_y-y));
				dist = (Math.round(dist*100))/100.0;
				distance.add(dist);
				BufferedWriter write_distance = new BufferedWriter(new FileWriter(filename, true));
				write_distance.write((int)x + "," + (int)y + "," + (int)next_x + "," + (int)next_y + "," + dist);
				write_distance.newLine();
				write_distance.flush();
				write_distance.close();
			}
		}
		return distance;
	}
}