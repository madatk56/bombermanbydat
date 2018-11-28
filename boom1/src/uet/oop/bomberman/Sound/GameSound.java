/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uet.oop.bomberman.Sound;



/**
 *
 * @author Admin
 */
    
    import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.util.HashMap;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class GameSound {
	
         public static void playSound(String path){
            File sound = new File(path);
            try{
                Clip clip= AudioSystem.getClip();
                clip.open(AudioSystem.getAudioInputStream(sound));
                clip.start();
               //Thread.sleep(clip.getMicrosecondLength()/1000);
            }catch(Exception e){
                
            }
        }
}


