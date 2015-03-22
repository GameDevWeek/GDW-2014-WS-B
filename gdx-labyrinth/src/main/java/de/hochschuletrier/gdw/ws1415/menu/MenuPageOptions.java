package de.hochschuletrier.gdw.ws1415.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import de.hochschuletrier.gdw.commons.gdx.audio.MusicManager;
import de.hochschuletrier.gdw.commons.gdx.audio.SoundEmitter;
import de.hochschuletrier.gdw.commons.gdx.menu.MenuManager;
import de.hochschuletrier.gdw.commons.gdx.menu.widgets.DecoImage;
import de.hochschuletrier.gdw.commons.gdx.utils.ScreenUtil;
import de.hochschuletrier.gdw.ws1415.Settings;
import de.hochschuletrier.gdw.ws1415.game.GameConstants;

public class MenuPageOptions extends MenuPage {
    
    private final Label soundLabel, musicLabel;
    private final Slider soundSlider, musicSlider;
    private final TextButton soundMuteButton, musicMuteButton;
    private final DecoImage musicSet, soundSet;
//    private final TextButton fullscreenButton;

    public MenuPageOptions(Skin skin, MenuManager menuManager){
        super(skin, "background");
        
        int y = 400;
        
        /* Vollbild gerade nicht notwendig, die resize = false */
//        createLabel(100, y).setText("Vollbild Modus:");
//        fullscreenButton = createToggleButton(440, y, "Aus", this::onFullscreenChanged);    
//        y -= 50;
        soundSet = new DecoImage(assetManager.getTexture("back"));
        soundSet.setPosition(440, y);
        addActor(soundSet);
      
        createLabel(100,y+10).setText("Sound: ");
        soundSlider = createSlider(170, y+10, this::onSoundVolumeChanged);
        soundLabel = createLabel(380, y+10);
        soundMuteButton = createToggleButton(427, y+14, "off", this::onSoundMuteChanged);
        y-=50;
        
        musicSet = new DecoImage(assetManager.getTexture("back"));
        musicSet.setPosition(440, y-20);
        addActor(musicSet);
        
        createLabel(100,y-10).setText("Musik: ");
        musicSlider = createSlider(170, y-10, this::onMusicVolumeChanged);
        musicLabel = createLabel(380, y-10);

        musicMuteButton = createToggleButton(427, y-4, "off", this::onMusicMuteChanged);
        
        DecoImage back = new DecoImage(assetManager.getTexture("back"));
        back.setPosition(25, 530);
        addActor(back);
        addCenteredButton(menuManager.getWidth() - 965, 560, 100, 40, "<", () -> menuManager.popPage());
    }
    
    private TextButton createToggleButton(int x, int y, String text, Runnable runnable) {
        TextButton button = new TextButton(text, skin, "mainMenu");
        //TextButton button = new TextButton(text, skin);
        button.setBounds(x, y, 100, 30);
        addActor(button);

        button.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                runnable.run();
            }
        });
        return button;
    }
    
    private Label createLabel(int x, int y) {
        Label label = new Label("0%", skin);
        //Label label = new Label("", skin);
        label.setBounds(x, y, 60, 30);
        addActor(label);
        return label;
    }
    
    private Slider createSlider(int x, int y, Runnable runnable){
        Slider slider = new Slider(0,1, 0.01f, false, skin);
        slider.setBounds(x, y, 200, 30);
        addActor(slider);
        
        slider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                runnable.run();
            }
        });
        
        return slider;
    }
    
    public final String pctToString(float value) {
        int pct = (int) (100 * value);
        return pct + "%";
    }
    
//    private void onFullscreenChanged() {
//        boolean fullscreenOn = fullscreenButton.isChecked();
//        fullscreenButton.setText(fullscreenOn ? "An" : "Aus");
//
//        ScreenUtil.setFullscreen(fullscreenOn);
//    }
    
    private void onSoundVolumeChanged() {
        final float value = soundSlider.getValue();
        soundLabel.setText(pctToString(value));
        SoundEmitter.setGlobalVolume(value);
    }
    
    private void onMusicVolumeChanged(){
        final float value = musicSlider.getValue();
        musicLabel.setText(pctToString(value));
        MusicManager.setGlobalVolume(value);
    }
    
    private void onSoundMuteChanged() {
        boolean soundOn = soundMuteButton.isChecked();
        soundMuteButton.setText(soundOn ? "on" : "off");
        SoundEmitter.setMuted(!soundOn);
    }

    private void onMusicMuteChanged() {
        boolean musicOn = musicMuteButton.isChecked();
        musicMuteButton.setText(musicOn ? "on" : "off");
        MusicManager.setMuted(!musicOn);
    }

    @Override
    public void setVisible(boolean visible) {
        if (soundSlider != null && isVisible() != visible) {
            if (visible) {
                restoreSettings();
            } else {
                storeSettings();
            }
        }
        super.setVisible(visible);
    }

    private void restoreSettings() {
        soundSlider.setValue(Settings.SOUND_VOLUME.get());
        soundMuteButton.setChecked(!Settings.SOUND_MUTE.get());
        musicSlider.setValue(Settings.MUSIC_VOLUME.get());
        musicMuteButton.setChecked(!Settings.MUSIC_MUTE.get());
//        fullscreenButton.setChecked(Gdx.graphics.isFullscreen());
    }

    private void storeSettings() {
        Settings.SOUND_VOLUME.set(soundSlider.getValue());
        Settings.SOUND_MUTE.set(!soundMuteButton.isChecked());
        Settings.MUSIC_VOLUME.set(musicSlider.getValue());
        Settings.MUSIC_MUTE.set(!musicMuteButton.isChecked());
        Settings.FULLSCREEN.set(Gdx.graphics.isFullscreen());
        Settings.flush();
    }
}
