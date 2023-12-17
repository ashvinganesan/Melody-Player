// Ashvin Ganesan
// AP CS Project 1- Melody player- Checkpoint 3
// Friday December 13th 2019


import java.io.IOException;
import java.util.ArrayList;

//this whole class is extra credit.
//This is Extra credit part 1
public class Playlist {
    private Melody[] playlist = new Melody[5];
    private Melody melodyOne;
    private Melody melodyTwo;
    private Melody melodyThree;
    private Melody melodyFour;
    private Melody melodyFive;
    //Declares and initializes the five variables of playlist. Sets them in order into the playlist.
    public Playlist(Melody melody1, Melody melody2, Melody melody3, Melody melody4, Melody melody5) throws IOException {
        melodyOne = melody1;
        melodyTwo = melody2;     
        melodyThree = melody3;            
        melodyFour = melody4;
        melodyFive = melody5; 
        playlist[0] = melodyOne;
        playlist[1] = melodyTwo;
        playlist[2] = melodyThree;
        playlist[3] = melodyFour;
        playlist[4] = melodyFive;
    }
    // plays the playlist in the order that it is originally in- this means order its inputed in.
    public void playPlaylist() {
        for (int i = 0; i < playlist.length;i++) {
            playlist[i].play();
        }
    }
    //plays the playlist in reverse order 
    //Extra credit number 10
    public void playPlaylistReverse() {
        for (int i = playlist.length -1; i>= 0; i--) {
            playlist[i].play();
        }
    }
    
    
    //This method plays the playlist in a random order. AKA it shuffles it 
    // IT DOES NOT shuffle the order of the playlist permanently, it only plays it differently on this button
    // this is intentional like music apps :D. 
    //This is Extra credit part 2
    public void shufflePlay() {
        Integer randomNumber = (int)(Math.random() * 5);
        Melody[] playlist2 = new Melody[5];
        playlist2[randomNumber] = playlist[0];
        ArrayList<Integer> numbers = new ArrayList <Integer>();
        numbers.add(randomNumber);
        while (numbers.size() < 5) {
            System.out.println(numbers.size());
            randomNumber = (int)(Math.random() * 5);//randomizes the number if not already contained 
            if (numbers.contains(randomNumber)) {// then this number is used as the position of list 2 for however many numbers have occured
                
                
            } else {
                playlist2[randomNumber] = playlist[numbers.size()];
                numbers.add(randomNumber);
                
            }
        }
        for(int i = 0; i < playlist2.length; i++) {
            playlist2[i].play();
        }        
        
    }
    
    //This goes and finds the total duration of the playlist and returns that.  
    //This is Extra credit part 3
    public double getPlaylistDuration() {
        double duration = 0.0;
        for(Melody melody: playlist) {
            duration+= melody.getTotalDuration();
        }
        return duration;
    }
    //this returns for every song in the playlist- that songs artist 
    //This is Extra credit part 4
    public String getArtists() {
        String artists = new String("");
        artists += playlist[0].getArtist();
        for(int i = 1; i < playlist.length-1; i++) {
            artists += ", " + playlist[i].getArtist();
        }
        artists += ", and " + playlist[playlist.length -1].getArtist();
        return artists;
    }
    //this returns every song in the playlist
    //This is Extra credit part 5
    public String getSongs() {
        String titles = new String("");
        titles += playlist[0].getTitle();
        for(int i = 1; i < playlist.length-1; i++) {
            titles += ", " + playlist[i].getTitle();
        }
        titles += ", and " + playlist[playlist.length -1].getTitle();
        return titles;
    }
    // This is extra credit # 11
    // This takes a ratio and changes the tempo for every melody in the playlist
    public void changePlayListTempo(double ratio) {
        for(Melody melody: playlist) {
            melody.changeTempo(ratio);
        }
    }
}
