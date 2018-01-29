package application;

import javafx.stage.Stage;

// TODO: Auto-generated Javadoc
/**
 *Klas projectsinglton je zadužena za otvaranje i zatvaranje formi.
 */
public class ProjectSingleton {
	
	/** The instance. */
	private static ProjectSingleton instance;
	
	/** Otvaranje stage setuje se na null. */
	private Stage openedSecondStage = null;

	/**
	 * Prazan konstruktor ProjectSinglton.
	 */
	public ProjectSingleton() {

	}

	/**
	 * Gets the single instance of ProjectSingleton.
	 *
	 * @return single instance of ProjectSingleton
	 */
	public static ProjectSingleton getInstance() {
		if (instance == null) {
			instance = new ProjectSingleton();
		}
		return instance;
	}

	/**
	 * Zatvaranje forme pomoæu metode closeOpenedStage.
	 */
	public void closeOpenedSecondStage() {
		if (openedSecondStage != null) {
			openedSecondStage.close();
		}
	}

	/**
	 * Postavljanje getera za stage.
	 *
	 * @return the opened second stage
	 */
	public Stage getOpenedSecondStage() {
		return openedSecondStage;
	}

	/**
	 * Sets the opened second stage.
	 *
	 * @param openedSecondStage postavljanje setera za stage.
	 */
	public void setOpenedSecondStage(Stage openedSecondStage) {
		this.openedSecondStage = openedSecondStage;
	}
}
