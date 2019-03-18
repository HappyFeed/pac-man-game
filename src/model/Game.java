package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Game {
    //Attributes
	private List<PacMan> pacMans;
	private Scores[] scores;
	
	//Constants
	public static final String SER_FILE="data/scores.sc";
	public static final String SCOR_FILE="data/scores.txt";
	//Methods
	public Game() throws ClassNotFoundException, IOException {
		load();
		pacMans= new ArrayList<PacMan>();
	}
	
	private void load() throws IOException, ClassNotFoundException {
		  File archivo= new File(SER_FILE);
		  if(archivo.exists()) {
			  ObjectInputStream ois= new ObjectInputStream(new FileInputStream(archivo));
			  scores=(Scores[]) ois.readObject();
			  ois.close();
		  }else {
			  scores= new Scores[10];
			  Scores s= new Scores(12);
			  Scores a= new Scores(20);
			  scores[0]=s;
			  scores[1]=a;
		  }
	}
	public void save() throws IOException {
		  ObjectOutputStream oos= new ObjectOutputStream(new FileOutputStream(SER_FILE));
		  oos.writeObject(scores);
		  oos.close();
	}
	
	public String showBestScores() {
		 String information="BestScores: \n";
    	 ordenar();
		 for(int i=0;i<scores.length && scores[i]!=null;i++) {
    		information+=(i+1)+". "+scores[i].getScore()+"\n";
      		information+="\n";
    	 } 	
    	 return information;
	}
	public void ordenar() {
		Scores best=null;
		for(int i = 0; i < scores.length; i++){
	 		for(int j=i+1; j < scores.length && scores[j]!=null; j++){
	 			if(scores[i].getScore() < scores[j].getScore()){
	 				best = scores[i];
	 				scores[i]=scores[j];
	 				scores[j]=best;
	 			}
	 		}
	 	}
	}

	public void importData(String path) throws IOException {
   	 File f= new File(path);
   	 FileReader fr= new FileReader(f);
   	 BufferedReader br= new BufferedReader(fr);
   	 String line=br.readLine();
   	 String nameFile=f.getName();
   	 if(nameFile.startsWith("d")) {
   		while(line!=null) {
     	    String[] points=line.split(";");
            for(int i=0;i<points.length;i++) {
            	int n= Integer.parseInt(points[i]);
            	Scores s= new Scores(n);
            	scores[i]=s;
            }
     	 }
   	 }else {
   		while(line!=null) {
      		 if(line.startsWith("#")) {
      			 line=br.readLine();
      		 }else {
      			 String[] parts=line.split("\t");
      			 int radio=Integer.parseInt(parts[0]);
      			 int posX=Integer.parseInt(parts[1]);
      			int posY=Integer.parseInt(parts[2]);
      			int speed=Integer.parseInt(parts[3]);
      			int direction=0;
      			if(parts[4].equalsIgnoreCase("IZQUIERDA")) {
      				direction=1;
      			}else if(parts[4].equalsIgnoreCase("DERECHA")) {
      				direction=2;
      			}else if(parts[4].equalsIgnoreCase("ARRIBA")) {
      				direction=3;
      			}else if(parts[4].equalsIgnoreCase("ABAJO")) {
      				direction=4;
      			}
      			boolean state=Boolean.parseBoolean(parts[5]);
      			 PacMan pc= new PacMan(radio, posX, posY, speed, direction, state);
      			 pacMans.add(pc);
      			 line=br.readLine();
      		 } 		 
      	 }
   	 }
   	 br.close();
   	 fr.close();
    }
	
	public void exportCSV(String path) throws FileNotFoundException {
		  PrintWriter pw= new PrintWriter(new File(path));
		  String evalu="";
		  File f=new File(path);
		  String nameFile=f.getName();
		  if(nameFile.equals("data/save.txt")) {
			  
		  }else {
			  for(int i=0;i<scores.length;i++) {
				  evalu+=""+(scores[i].getScore())+";";
			  }
		  } 
		  pw.println(evalu);
		  pw.close();
	  }

    public List<PacMan> getPacMans(){
    	return pacMans;
    }
 
    public Scores[] getScores(){
    	return scores;
    }

    
}
