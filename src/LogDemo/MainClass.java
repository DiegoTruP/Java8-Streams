package LogDemo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class MainClass {

	public static void main(String[] args) {
		readLog();

	}
	
	static void generateLog() {
		List<LocalDateTime> log = new ArrayList<LocalDateTime>();
		LocalDateTime start_date = LocalDateTime.of(2022, 3, 1, 0, 0);
		LocalDateTime end_date = LocalDateTime.of(2022, 3, 2, 0, 0);
		Random random = new Random();
		
		log = Stream.iterate(start_date, time -> time.plusMinutes(random.nextInt(60))).limit(ChronoUnit.HOURS.between(start_date, end_date)).collect(Collectors.toList());
		
		System.out.println("hola");
		log.stream().forEach(str->System.out.println(str));
		
		Path path = Paths.get("logFile.txt");
		
			File newFile = new File("logFile.txt");
			try {
				if(newFile.createNewFile()) {
					FileWriter writer = new FileWriter(newFile);
					
					log.stream().forEach(loging -> {
						try {
							writer.write(loging.toString() + "  " + "asfdsafff \n");
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					});
					writer.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}

	static List readLog() {
		
		String fileName = "logFile.txt";
		List<String> list = new ArrayList<>();

		try (Stream<String> stream = Files.lines(Paths.get(fileName))) {

			list = stream
					.collect(Collectors.toList());
			List<String> listFiltered = list.stream().filter(str -> str.contains("VISITID:")).collect(Collectors.toList());
			
			listFiltered.forEach(System.out::println);

			Map<Integer,Long> groupList = new HashMap<Integer, Long>();
			for(int i =0; i <= 23 ; i++) {
				int hour = i;
				
				long times = listFiltered.stream()
						.filter(str -> LocalDateTime.parse(str.substring(0, 16)).getHour()==hour)
						.count();
				
				System.out.println(hour +" " + times);
				groupList.put(hour, times);
			}
			
			Long higherValue=(long) 0;
			Integer hourValue =0 ;
			for(Integer hour : groupList.keySet()) {
				if(groupList.get(hour)>higherValue) {
					higherValue=groupList.get(hour);
					hourValue=hour;
				}
					
			}
			
			System.out.println("The hour with most traffic is " + hourValue + " with " + higherValue + " logs");
			
		} catch (IOException e) {
			e.printStackTrace();
		}

		//list.forEach(System.out::println);

		
		return null;
		
	}
	
}
