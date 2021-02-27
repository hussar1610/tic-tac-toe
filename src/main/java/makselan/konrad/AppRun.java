package makselan.konrad;

public class AppRun {
	public static void main(String[] args) {
		AppView appView = new AppView();
		AppModel appModel = new AppModel();
		AppController appController = new AppController(appView, appModel);
	}
}
