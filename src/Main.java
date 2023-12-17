// Ashvin Ganesan
// AP CS Project 1- Melody player- Checkpoint 3
// Friday December 13th 2019

import java.io.File;
import java.io.IOException;

public class Main {
	/*
	 * Runs the program.
	 */
	public static void main(String[] args) throws IOException {
            
            
		//BUG ERROR If you call the GUI and after call the play then it will give you an error
                // This is not an error with my code. This also happens with the playlist method. 
//                Melody first = new Melody(new File ("res/melodies/repeat1.txt"));
//                Melody second = new Melody(new File ("res/melodies/happybirthday.txt"));
//                Melody third = new Melody(new File ("res/melodies/mysong.txt"));
//                Melody fourth = new Melody(new File("res/melodies/scale.txt"));
//                Melody fifth = new Melody(new File("res/melodies/submarine.txt"));
//                fifth.reverse();
//                Playlist demo = new Playlist(first, second, third, fourth, fifth);
//                demo.changePlayListTempo(0.5);
//                demo.playPlaylist();
//                demo.playPlaylistReverse();
//                System.out.println(demo.getArtists());
//                System.out.println(demo.getSongs());
//                System.out.println("The duration of the playlist is " + demo.getPlaylistDuration() + " seconds.");
//                demo.playPlaylist();
//                demo.shufflePlay();
//                Melody myMelody = new Melody(new File ("res/melodies/scale.txt"));
//                fourth.playXTimes(3);
//                System.out.println(myMelody.getArtist());
//                System.out.println(myMelody.getTitle());
//                myMelody.play();
//                System.out.println(myMelody.getTotalDuration());
//                myMelody.changeTempo(0.5);
//                System.out.println(myMelody.getTotalDuration());
//                myMelody.mergeMelody(second);
//                myMelody.play();
//                myMelody.toFlat();
//                myMelody.play();
//                myMelody.toSharp();
//                myMelody.play();
//                myMelody.toNatural();
//                myMelody.play();
//                myMelody.octaveUp();
//                myMelody.play();
//                myMelody.octaveDown();
//                myMelody.play();
//                System.out.println(myMelody);
                new MelodyGUI();

                

                
	}
        
}
