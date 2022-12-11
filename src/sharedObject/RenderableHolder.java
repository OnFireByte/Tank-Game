package sharedObject;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
	public static AudioClip ShootSound;
	public static AudioClip explosionSound;

	// image
	public static Image test;
	public static Image wall1;
	public static Image wall2;
	public static Image tank1;
	public static Image tank1Hit;
	public static Image tank2;
	public static Image tankExplosion;
	public static Image button;
	public static Image buttonPressed;
	public static Image txtFrame;
	public static Image speedUpgrade;
	public static Image fireRateUpgrade;
	public static Image maxHpUpgrade;
	public static Image sizeUpgrade;
	public static Image heal;
	public static Image upgrade;
	// font
	public static Image map;
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
		mainMenuGif = new Image(ClassLoader.getSystemResource(image + "mainmenu.gif").toString(), 1000, 700, false,
				false);
		iconGif = new Image(ClassLoader.getSystemResource(image + "tank.gif").toString());

		// image
		test = new Image(ClassLoader.getSystemResource(image + "test.jpg").toString(), 700, 500, false, false);
		wall1 = new Image(ClassLoader.getSystemResource(image + "Wall1.jpg").toString());
		wall2 = new Image(ClassLoader.getSystemResource(image + "Wall2.jpg").toString());
		tank1 = new Image(ClassLoader.getSystemResource(image + "Tank1.gif").toString());
		tank1Hit = new Image(ClassLoader.getSystemResource(image + "Tank1hit.png").toString());
		tank2 = new Image(ClassLoader.getSystemResource(image + "Tank2.gif").toString());
		tankExplosion = new Image(ClassLoader.getSystemResource(image + "explosion.gif").toString());
		button = new Image(ClassLoader.getSystemResource(image + "Button.png").toString());
		buttonPressed = new Image(ClassLoader.getSystemResource(image + "ButtonPressed.png").toString());
		txtFrame = new Image(ClassLoader.getSystemResource(image + "txt.png").toString(), 450, 90, false, false);
		speedUpgrade = new Image(ClassLoader.getSystemResource(image + "speed.gif").toString());
		fireRateUpgrade = new Image(ClassLoader.getSystemResource(image + "firerate.gif").toString());
		maxHpUpgrade = new Image(ClassLoader.getSystemResource(image + "maxhp.gif").toString());
		sizeUpgrade = new Image(ClassLoader.getSystemResource(image + "size.gif").toString());
		heal = new Image(ClassLoader.getSystemResource(image + "heal.gif").toString());
		upgrade = new Image(ClassLoader.getSystemResource(image + "upgrade.png").toString());

		// map image
		map = new Image(ClassLoader.getSystemResource(image + "map.png").toString());

		// audio
		clickSound = new AudioClip(ClassLoader.getSystemResource(sound + "soundclick.mp3").toString());
		mainMenuMusic = new AudioClip(ClassLoader.getSystemResource(sound + "mainmenu_music.mp3").toString());
		ShootSound = new AudioClip(ClassLoader.getSystemResource(sound + "Shoot.mp3").toString());
		explosionSound = new AudioClip(ClassLoader.getSystemResource(sound + "Explode.mp3").toString());

		// font
		buttonFont = Font.loadFont(ClassLoader.getSystemResource(font + "8bit.ttf").toString(), 100);

	}

	public static Font getFont(double size) {
		return Font.loadFont(ClassLoader.getSystemResource("font/8bit.ttf").toString(), size);
	}

	public static ImageView getButton(double w, double h) {
		return new ImageView(
				new Image(ClassLoader.getSystemResource("images/Button.png").toString(), w, h, false, false));
	}

	public static ImageView getButtonPressed(double w, double h) {
		return new ImageView(
				new Image(ClassLoader.getSystemResource("images/ButtonPressed.png").toString(), w, h, false, false));
	}

	public static Image loadNewTankExplosion() {
		// Need to load new Image because they aren't restart.s
		return new Image(ClassLoader.getSystemResource("images/explosion.gif").toString());

	}
}
