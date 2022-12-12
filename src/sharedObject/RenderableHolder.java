package sharedObject;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;
import javafx.util.Duration;

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
	public static AudioClip gameOverSound;
	public static AudioClip upgradeSound;
	public static MediaPlayer mainGameMusic;

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
	public static Image unbreakableStone;
	public static Image openingAnimationTank;
	public static Image grass;
	public static Image map1Preview;
	public static Image map2Preview;
	public static Image map3Preview;

	// map
	public static Image map1;
	public static Image map2;
	public static Image map3;
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
		mainGameMusic.setOnEndOfMedia(new Runnable() {
			public void run() {
				mainGameMusic.seek(Duration.ZERO);
			}
		});
		mainGameMusic.setVolume(0.4);
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
		test = new Image(ClassLoader.getSystemResource(image + "test.png").toString(), 700, 500, false, false);
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
		unbreakableStone = new Image(ClassLoader.getSystemResource(image + "UnbreakableStone.png").toString());
		openingAnimationTank = new Image(ClassLoader.getSystemResource(image + "Opening.gif").toString());
		grass = new Image(ClassLoader.getSystemResource(image + "grass.png").toString());
		map1Preview = new Image(ClassLoader.getSystemResource(image + "map1preview.png").toString());
		map2Preview = new Image(ClassLoader.getSystemResource(image + "map2preview.png").toString());
		map3Preview = new Image(ClassLoader.getSystemResource(image + "map3preview.png").toString());

		// map image
		map1 = new Image(ClassLoader.getSystemResource(image + "map.png").toString());
		map2 = new Image(ClassLoader.getSystemResource(image + "map2.png").toString());
		map3 = new Image(ClassLoader.getSystemResource(image + "map3.png").toString());

		// audio
		clickSound = new AudioClip(ClassLoader.getSystemResource(sound + "soundclick.mp3").toString());
		mainMenuMusic = new AudioClip(ClassLoader.getSystemResource(sound + "mainmenu_music.mp3").toString());
		ShootSound = new AudioClip(ClassLoader.getSystemResource(sound + "Shoot.mp3").toString());
		explosionSound = new AudioClip(ClassLoader.getSystemResource(sound + "Explode.mp3").toString());
		gameOverSound = new AudioClip(ClassLoader.getSystemResource(sound + "gameover.mp3").toString());
		upgradeSound = new AudioClip(ClassLoader.getSystemResource(sound + "upgrade.mp3").toString());
		mainGameMusic = new MediaPlayer(new Media(ClassLoader.getSystemResource(sound + "mainGame.mp3").toString()));

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

	public static Image getMap(int idx) {
		switch (idx) {
			case 1:
				return map1;
			case 2:
				return map2;
			case 3:
				return map3;
			default:
				return map1;
		}
	}

	public static Image getMapPreview(int idx) {
		switch (idx) {
			case 1:
				return map1Preview;
			case 2:
				return map2Preview;
			case 3:
				return map3Preview;
			default:
				return map1Preview;
		}
	}
}
