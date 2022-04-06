package game.engine;
java.awt.Dimension;

import java.awt.Toolkit;
import java.awt.geom.Rectangle2D;

import game.ui.GameScene;
import game.ui.ScoreScene;
import game.util.Leaderboard;
import game.util.ScoreCalc;
import javafx.application.Application;
import javafx.stage.Stage;
import test.TestScoreScene;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

public class GameApplication extends Application {
	public final static int size;// usare la percentuale dello schermo non valori da 0 a 1: 0.n * size; dove n �
									// la percentuale dello schermo. ergo ho i valori da 0 a 1
	public int score = 0;
	private Stage primaryStage2 = new Stage();
	/*
	 * creati dentro i singoli metodi ScoreCalc scoreCalc; ScoreManager scoremanager
	 * ; GameScene gamescene; ScoreScene scorescene ; Leaderboard leaderBoard;
	 */
	static {
		Rectangle2D screenBounds = Screen.getPrimary().getBounds();
		size = (int) Math.min(screenBounds.getWidth(), screenBounds.getHeight());
	}

	// prende un int che va da zero a 1
	static double convertToDouble(final int num) {
		// deve essere compreso tra 0 e n e lo devo trasformare in 0-1
		double tmp = num / size;
		// ritorna la posizione percentuale rispetto allo scermo. esempio gli passo 350
		// e la risoluzione � 700 lui mi tira fuori 0.5 (50% della lunghezza dello
		// schermo )
		return tmp;
	}

	// fa l'opposto di quello sopra
	static int convertToInt(final double num) {
		int tmp = (int) num * size;
		return tmp;
	}

	public void startGameApplication() { // public void start(final Stage primaryStage) {
		primaryStage2.setWidth(size);
		primaryStage2.setHeight(size);
		primaryStage2.setResizable(false);
		/*
		 * ScoreCalc scolreCalc= new ScoreCalc(); ScoreManager scoremanager = new
		 * ScoreManager(this.scolreCalc);// prende score e il player dal game engine
		 */
		this.menu();
	}

	public void menu() {
		MenuManager menumanager = new MenuManager();		//manca la classe, aspetto per modificare il cosrtuttore
		MenuScene menuscene = new MenuScene(menumanager);	//manca la classe, aspetto per modificare il cosrtuttore
		this.setSceneM(menuscene.get());					//manca la classe, aspett il nome del metodo
	}


	public void game() {
		GameScene gamescene = new GameScene(size);
		GameEngine gameEngine = new GameEngine(gamescene, this);
		gameEngine.run();
		/*
		 * primaryStage2.setscene(gamescene); primaryStage2.show();
		 */
		this.setSceneM(gamescene.getScene());

	}

	public void score() {
		ScoreCalc scolreCalc = new ScoreCalc();
		ScoreManager scoremanager = new ScoreManager(scolreCalc);// prende score e il player dal game engine
		ScoreScene scoreScene = new ScoreScene(scolreCalc);
		scoreScene.start(this.primaryStage2);
		this.setSceneM(scoreScene.get());

	}
	/* DA FARE 
	 * public void leaderBoard() { leaderboardScene = new Scene(scoremanager);// ci
	 * va menumanager this.setsceneM(leaderboard);
	 * 
	 * }
	 */

	// SETTA LA SCENA CHE GLI PASSO AL THREAD DEL JAVA FX
	void setSceneM(Scene scene) {
		Platform.runLater(() -> {
			primaryStage2.setscene(scene);
			primaryStage2.show();
		});
	}
	
	public  void main(String[] args) {
		startGameApplication();
	}

}