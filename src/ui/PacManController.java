package ui;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.*;
import threads.*;

public class PacManController {

	private Game gm;
	private Stage stage;
    private List<PacMan> pacMans;
    private MoveThread pcT;
	@FXML
    private Pane pane;
	
    @FXML
    private MenuItem lvl0;

    @FXML
    private MenuItem lvl1;

    @FXML
    private MenuItem lvl2;

    @FXML
    private MenuItem saveGame;
    
    @FXML
    private ArrayList<Arc> arcPac;
    
    @FXML
    private Label rebotes;
    
    @FXML
    private Label nRebotes;
    
    @FXML
    private MenuItem bestScores;

    @FXML
    private MenuItem information;

    @FXML
    void gameInformation(ActionEvent event) {
    	Alert info = new Alert(AlertType.INFORMATION);
    	info.setTitle("PacMan");
    	info.setHeaderText(null);
    	info.initStyle(StageStyle.UTILITY);
    	info.setContentText("To win you have to stop all the pacmans \n in the less time you can");
    	info.show();
    }

    @FXML
    void save(ActionEvent event){
    	Scores[] can=gm.getScores();
    	int n=Integer.parseInt(nRebotes.getText());
    	for(int i=0;i<can.length && can[i]!=null;i++) {
    		if(n>can[i].getScore()) {
        		can[i].setScore(n);
        		try {
					gm.save();
				} catch (IOException e) {
					e.printStackTrace();
				}
        	}
    	}
    	try {
			gm.exportCSV("data/save.txt");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
    }

    @FXML
    void showScores(ActionEvent event){
    	Alert info = new Alert(AlertType.INFORMATION);
    	info.setTitle("PacMan");
    	info.setHeaderText(null);
    	info.initStyle(StageStyle.UTILITY);
    	info.setContentText(gm.showBestScores());
    	info.show();
    }
    
    
    @FXML
    void initialize() {
    	try {
    		arcPac= new ArrayList<Arc>();
			gm= new Game();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	ControlThread pacmt = new ControlThread(this);
    	pacmt.setDaemon(true);
    	pacmt.start();
    }
    @FXML
    void level0(ActionEvent event) {
    	try {

			gm.importData("levels/lvl0");
		} catch (IOException e) {
			e.printStackTrace();
		}
    	pacMans=gm.getPacMans();
    	for(int i=0;i<pacMans.size();i++){
    		Arc pac = new Arc(pacMans.get(i).getPosX(), pacMans.get(i).getPosY(), pacMans.get(i).getRadio(), pacMans.get(i).getRadio(), 32, 300);
    		arcPac.add(pac);
    		pac.setFill(Color.YELLOW);
    		pac.setStroke(Color.BLACK);
    		pac.setStrokeWidth(3);
    		pac.setType(ArcType.ROUND);
    	}
    	
    	for(int i=0;i<pacMans.size();i++){
    		pane.getChildren().add(arcPac.get(i));
    		pcT=new MoveThread(this,pacMans.get(i),true);
    		pcT.setDaemon(true);
    		pcT.start();
    	}
    }

    @FXML
    void level1(ActionEvent event) {
    	try {

			gm.importData("levels/lvl1");
		} catch (IOException e) {
			e.printStackTrace();
		}
    	pacMans=gm.getPacMans();
    	for(int i=0;i<pacMans.size();i++){
    		Arc pac = new Arc(pacMans.get(i).getPosX(), pacMans.get(i).getPosY(), pacMans.get(i).getRadio(), pacMans.get(i).getRadio(), 32, 300);
    		arcPac.add(pac);
    		pac.setFill(Color.YELLOW);
    		pac.setStroke(Color.BLACK);
    		pac.setStrokeWidth(3);
    		pac.setType(ArcType.ROUND);
    	}
    	
    	for(int i=0;i<pacMans.size();i++){
    		pane.getChildren().add(arcPac.get(i));
    		pcT=new MoveThread(this,pacMans.get(i),true);
    		pcT.setDaemon(true);
    		pcT.start();
    	}
    }

    @FXML
    void level2(ActionEvent event) {
    	try {
			gm.importData("levels/lvl2");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	pacMans=gm.getPacMans();
    	for(int i=0;i<pacMans.size();i++){
    		Arc pac = new Arc(pacMans.get(i).getPosX(), pacMans.get(i).getPosY(), pacMans.get(i).getRadio(), pacMans.get(i).getRadio(), 32, 300);
    		arcPac.add(pac);
    		pac.setFill(Color.YELLOW);
    		pac.setStroke(Color.BLACK);
    		pac.setStrokeWidth(3);
    		pac.setType(ArcType.ROUND);
    	}
    	
    	for(int i=0;i<pacMans.size();i++){
    		pane.getChildren().add(arcPac.get(i));
    		pcT=new MoveThread(this,pacMans.get(i),true);
    		pcT.setDaemon(true);
    		pcT.start();
    	}
    }
    public double getWith() {
		return pane.getWidth();
	}
    
    public double getHeigth() {
		return pane.getHeight();
	}
    
    public void setStage(Stage st) {
    	stage = st;
    }
    
    public void upThreads() throws ClassNotFoundException, IOException{  	
    	int bounces=0;
    	int suma=0;
    	for (int id = 0; id < arcPac.size(); id++) {
    		arcPac.get(id).setLayoutX(pacMans.get(id).getPosX());
    		arcPac.get(id).setLayoutY(pacMans.get(id).getPosY());	
    		arcPac.get(id).setLength(pacMans.get(id).getLength());	
    		arcPac.get(id).setRotate(pacMans.get(id).getAngle());
    	     suma=pacMans.get(id).getBounces();
		}
		bounces+=suma;
		nRebotes.setText(""+bounces);

    }
   
    @FXML
    void stopPacMan(MouseEvent event) {
        for(int i=0;i<pacMans.size();i++) {
        	if(event.getX()<=pacMans.get(i).getPosX()+(pacMans.get(i).getRadio()*2)&& event.getX()>=pacMans.get(i).getPosX()) {
        		pcT.stopsThread();
            }
        }
    }
}


