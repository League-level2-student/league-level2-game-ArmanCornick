import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.List;

public class CourseGenerator {
	
	static ArrayList<Obstacle> course(int courseNumber){
		if(courseNumber == 1) {
			return course1();
		}
		else if(courseNumber == 2) {
			return course2();
		}
		else if(courseNumber == 3) {
			return course3();
		} else {
			return course4();
		}
	}
	
	
	static ArrayList<Obstacle> course1() {
		ArrayList<Obstacle> obstacles = new ArrayList<Obstacle>();
		obstacles.add(new Obstacle(1000,0,500,450));
		obstacles.add(new Obstacle(1000,620,500,380));
		obstacles.add(new Obstacle(1800,0,500,200));
		obstacles.add(new Obstacle(1800,370,500,630));
		obstacles.add(new Obstacle(2600,-30,50,50));
		obstacles.add(new Obstacle(2600,170,50,50));
		obstacles.add(new Obstacle(2600,370,50,50));
		obstacles.add(new Obstacle(2600,570,50,50));
		obstacles.add(new Obstacle(2600,770,50,50));
		obstacles.add(new Obstacle(2600,970,50,50));
		obstacles.add(new Obstacle(2900,120,50,50));
		obstacles.add(new Obstacle(2900,320,50,50));
		obstacles.add(new Obstacle(2900,520,50,50));
		obstacles.add(new Obstacle(2900,720,50,50));
		obstacles.add(new Obstacle(2900,920,50,50));
		obstacles.add(new Obstacle(3200,20,50,50));
		obstacles.add(new Obstacle(3200,220,50,50));
		obstacles.add(new Obstacle(3200,420,50,50));
		obstacles.add(new Obstacle(3200,620,50,50));
		obstacles.add(new Obstacle(3200,820,50,50));
		obstacles.add(new Obstacle(3500,970,50,50));
		obstacles.add(new Obstacle(3500,770,50,50));
		obstacles.add(new Obstacle(3500,570,50,50));
		obstacles.add(new Obstacle(3500,370,50,50));
		obstacles.add(new Obstacle(3500,170,50,50));
		obstacles.add(new Obstacle(3500,-30,50,50));
		return obstacles;
	}
	static ArrayList<Obstacle> course2() {
		ArrayList<Obstacle> obstacles = new ArrayList<Obstacle>();
		obstacles.add(new Obstacle(1000, -200,50,775));
		obstacles.add(new Obstacle(1000, 750,50,750));
		for(int i=1;i<11;i++) {
			obstacles.add(new Obstacle(1000+(i*50),800-(i*50),50,50));
			obstacles.add(new Obstacle(1000+(i*50),575-(i*50),50,50));
		}
		for(int i=1;i<11;i++) {
			obstacles.add(new Obstacle(1500+(i*50),300+(i*50),50,50));
			obstacles.add(new Obstacle(1500+(i*50),75+(i*50),50,50));
		}
		for(int q=0;q<5;q++) {
			for(int i=1;i<3;i++) {
				obstacles.add(new Obstacle(2000+(i*50)+(q*200),850-(i*50),50,50));
				obstacles.add(new Obstacle(2000+(i*50)+(q*200),575-(i*50),50,50));
			}
			for(int i=1;i<3;i++) {
				obstacles.add(new Obstacle(2100+(i*50)+(q*200),750+(i*50),50,50));
				obstacles.add(new Obstacle(2100+(i*50)+(q*200),475+(i*50),50,50));
			}
		}
		obstacles.add(new Obstacle(3050, 0,500,650));
		obstacles.add(new Obstacle(3050, 800,500,200));
		
		return obstacles;
	}
	static ArrayList<Obstacle> course3() {
		ArrayList<Obstacle> obstacles = new ArrayList<Obstacle>();
		for(int i=1;i<20;i++) {
			obstacles.add(new Obstacle(1000+(i*50)+(200),850-(i*20),50,50));
			obstacles.add(new Obstacle(1000+(i*50)+(200),640-(i*20),50,50));
		}
		for(int i=1;i<20;i++) {
			obstacles.add(new Obstacle(1900+(i*50)+(200),450+(i*20),50,50));
			obstacles.add(new Obstacle(1900+(i*50)+(200),240+(i*20),50,50));
		}
		return obstacles;
	}
	static ArrayList<Obstacle> course4() {
		ArrayList<Obstacle> obstacles = new ArrayList<Obstacle>();
		for(int i=1;i<10;i++) {
			obstacles.add(new Obstacle(1000+(i*50)+(200),1130-(i*80),50,50));
			obstacles.add(new Obstacle(1000+(i*50)+(200),640-(i*80),50,50));
		}
		for(int i=1;i<10;i++) {
			obstacles.add(new Obstacle(1400+(i*50)+(200),330+(i*80),50,50));
			obstacles.add(new Obstacle(1400+(i*50)+(200),-160+(i*80),50,50));
		}
		for(int i=1;i<10;i++) {
			obstacles.add(new Obstacle(1800+(i*50)+(200),1130-(i*80),50,50));
			obstacles.add(new Obstacle(1800+(i*50)+(200),640-(i*80),50,50));
		}
		for(int i=1;i<10;i++) {
			obstacles.add(new Obstacle(2200+(i*50)+(200),330+(i*80),50,50));
			obstacles.add(new Obstacle(2200+(i*50)+(200),-160+(i*80),50,50));
		}
		for(int i=1;i<10;i++) {
			obstacles.add(new Obstacle(2600+(i*50)+(200),1130-(i*80),50,50));
			obstacles.add(new Obstacle(2600+(i*50)+(200),640-(i*80),50,50));
		}
		for(int i=1;i<10;i++) {
			obstacles.add(new Obstacle(3000+(i*50)+(200),330+(i*80),50,50));
			obstacles.add(new Obstacle(3000+(i*50)+(200),-160+(i*80),50,50));
		}
		return obstacles;
	}

}
