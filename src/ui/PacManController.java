package ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.stage.StageStyle;
import model.*;
import threads.*;

public class PacManController {

	private Game gm;
	private List<PacManThread> pacThreads;
	
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
    private Arc pac;
    
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
    void level(ActionEvent event) throws ClassNotFoundException, IOException {
    	gm= new Game();
    	gm.importData("levels/lvl0");
    	List<PacMan> pacM=gm.getPacMans();
    	pacThreads= new ArrayList<PacManThread>(pacM.size());
    	for(int i=0;i<pacThreads.size();i++){
    		PacManThread pcT=new PacManThread(pacM.get(i).getDirection());
    		pacThreads.add(pcT);
    		pac = new Arc(pacM.get(i).getPosX(), pacM.get(i).getPosX(), pacM.get(i).getRadio(), pacM.get(i).getRadio(), 32, 300);
    		pac.setFill(Color.YELLOW);
    		pac.setStroke(Color.BLACK);
    		pac.setStrokeWidth(3);
    		pac.setType(ArcType.ROUND);
    		pane.getChildren().add(pac);
    		pacThreads.get(i).start();
    	}
    	
    }

    @FXML
    void save(ActionEvent event) throws IOException, ClassNotFoundException {
    	gm= new Game();
    	Scores[] can=gm.getScores();
    	int n=Integer.parseInt(nRebotes.getText());
    	for(int i=0;i<can.length && can[i]!=null;i++) {
    		if(n>can[i].getScore()) {
        		can[i].setScore(n);
        		gm.save();
        	}
    	}
    	for(int i=0;i<pacThreads.size();i++) {
    		pacThreads.get(i).s();
    	}
    	gm.exportCSV("data/save.txt");
    }

    @FXML
    void showScores(ActionEvent event) throws ClassNotFoundException, IOException {
    	gm= new Game();
    	Alert info = new Alert(AlertType.INFORMATION);
    	info.setTitle("PacMan");
    	info.setHeaderText(null);
    	info.initStyle(StageStyle.UTILITY);
    	info.setContentText(gm.showBestScores());
    	info.show();
    }
    
    public boolean rigth(boolean r) {
    	if(r) {
    		pac.setLayoutX(pac.getLayoutX()+6);
    		pac.setRotate(pac.getRotate()+6);
    		System.out.println("Derecha");
    	}
    	if(pac.getLayoutX()>=pane.getWidth()-pac.getRadiusX()) {
			r = false;
		}
		return r;
    }
    
    public boolean left(boolean r) {
    	if(!r) {
    		pac.setLayoutX(pac.getLayoutX()-6);
    		pac.setRotate(pac.getRotate()-6);
    		System.out.println("Izquierda");
    	}
    	if(pac.getLayoutX()<=0) {
			r = true;
		}	
		return r;
    }
    
    public boolean up(boolean r) {
    	if(!r) {
    		pac.setLayoutY(pac.getLayoutY()-6);
    		pac.setRotate(pac.getRotate()-6);
    		System.out.println("Izquierda");
    	}
    	if(pac.getLayoutY()<=0) {
			r = true;
		}	
		return r;
    }
    
    public boolean down(boolean r) {
    	if(r) {
    		pac.setLayoutY(pac.getLayoutY()+6);
    		pac.setRotate(pac.getRotate()+6);
    		System.out.println("Derecha");
    	}
    	if(pac.getLayoutY()>=pane.getHeight()-pac.getRadiusY()) {
			r = false;
		}
		return r;
    }
}


