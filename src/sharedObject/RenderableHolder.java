package sharedObject;

import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;
import javafx.scene.text.Font;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class RenderableHolder {

	private static final RenderableHolder instance = new RenderableHolder();
	private ArrayList<IRenderable> entities;
	private Comparator<IRenderable> comparator;

	// GIF image
	public static Image mainMenuGif;
	public static Image iconGif;

	// audio
	public static AudioClip clickSound;
	public static AudioClip mainMenuMusic;

	// image
	public static Image test;
	public static Image Wall1;
	public static Image Tank1;
	public static Image Tank1Hit;
	public static Image Tank2;

	// font
	public static Font buttonFont;

	// -------------------------------------------------------------------------------------------------------------------------------//
	public RenderableHolder() {
		entities = new ArrayList<IRenderable>();
		comparator = (IRenderable o1, IRenderable o2) -> {
			if (o1.getZ() > o2.getZ())
				return 1;
			return -1;
		};
	}

	public static RenderableHolder getInstance() {
		return instance;
	}

	public void add(IRenderable entity) {
		entities.add(entity);
		Collections.sort(entities, comparator);
	}

	public void update() {
		for (int i = entities.size() - 1; i >= 0; i--) {
			if (entities.get(i).isDestroyed())
				entities.remove(i);
		}
	}

	public List<IRenderable> getEntities() {
		return entities;
	}

	// -------------------------------------------------------------------------------------------------------------------------------//

	static {
		loadResource();
	}

	public static void loadResource() {
		String image = "images/";
		String sound = "sound/";
		String font = "font/";

		// GIF image
		mainMenuGif = new Image(ClassLoader.getSystemResource(image + "mainmenu.gif").toString(), 1000, 600, false,
				false);
		iconGif = new Image(ClassLoader.getSystemResource(image + "tank.gif").toString());

		// image
		test = new Image(ClassLoader.getSystemResource(image + "test.jpg").toString(), 700, 500, false, false);
		Wall1 = new Image(ClassLoader.getSystemResource(image + "Wall1.jpg").toString());
		Tank1 = new Image(ClassLoader.getSystemResource(image + "Tank1.gif").toString());
		Tank1Hit = new Image(ClassLoader.getSystemResource(image + "Tank1hit.png").toString());
		Tank2 = new Image(ClassLoader.getSystemResource(image + "Tank2.gif").toString());

		// audio
		clickSound = new AudioClip(ClassLoader.getSystemResource(sound + "soundclick.mp3").toString());
		mainMenuMusic = new AudioClip(ClassLoader.getSystemResource(sound + "mainmenu_music.mp3").toString());

		// font
		buttonFont = Font.loadFont(ClassLoader.getSystemResource(font + "8bit.ttf").toString(), 100);

	}
}
