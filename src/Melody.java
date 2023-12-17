// Ashvin Ganesan
// AP CS Project 1- Melody player- Checkpoint 3
// Friday December 13th 2019

import java.util.*;
import java.io.*;
import melody.audio.*;

public class Melody {

    private String artist;
    private String title;
    private Note[] melody;
// Declares and initializes the variables sets melody, title, and artist
    public Melody(File file) throws IOException {
        Scanner reader = new Scanner(file);
        title = reader.nextLine();
        artist = reader.nextLine();
        String NumOfnotes = reader.nextLine();
        int size = Integer.parseInt(NumOfnotes);
        melody = new Note[size];
        for (int i = 0; i < size; i++) {// for every note in the file it finds the duration pitch rest accidental octave 
            String nextLine = reader.nextLine(); //and it makes the note of the same position in melody have these attributes
            //System.out.println(nextLine);
            double duration = Double.parseDouble(nextLine.substring(0, nextLine.indexOf(" "))); //this whole section is taking the info from the file and putting it into melody
            nextLine = nextLine.substring(nextLine.indexOf(" ") + 1);
            Pitch pitch = Pitch.getValueOf("" + nextLine.charAt(0));
            boolean rest = false;
            //System.out.println(nextLine.charAt(0));

            if (pitch == Pitch.R) { //checks for rest
                rest = true;
            }
            //System.out.println(rest);
            nextLine = nextLine.substring(nextLine.indexOf(" ") + 1);
            Accidental accidental = null;
            int octave = 0;
            if (rest == false) { // this makes sure you are only checking for available information
                octave = Integer.parseInt(nextLine.substring(0, 1));
                nextLine = nextLine.substring(nextLine.indexOf(" ") + 1);
                accidental = Accidental.getValueOf(nextLine.substring(0, nextLine.indexOf(" ")));
                nextLine = nextLine.substring(nextLine.indexOf(" ") + 1);
            }
            boolean repeat = Boolean.parseBoolean(nextLine);
            Note note;
            if (rest) {

                note = new Note(duration, repeat);
            } else {
                note = new Note(duration, pitch, octave, accidental, repeat);
            }
            melody[i] = note;

        }

    }

    public void changeTempo(double ratio) { // this method will make the duration = duration * ratio, so if ratio = 2 it will last longer
        for (Note n: melody) {// if its a decimal it will be shorter
            n.setDuration(n.getDuration() * ratio);
        }

    }

    public String getArtist() {//returns artist
        return artist;
    }

    public String getTitle() {//returns title
        return title;
    }

    public double getTotalDuration() {//returns duration of the song
        double duration = 0.0;
        boolean starting = false;
        int startPosition = 0;
        for (int i = 0; i < melody.length;i++) {
            duration += melody[i].getDuration(); // iterates through and adds all the duration
            if(melody[i].isRepeat() && !starting) {// this section deals with repeated chuncks similar to how its done in play
                startPosition = i;
                starting = true;
            } else if (melody[i].isRepeat()) {
                starting = false;
                for(int a = startPosition;a <= i;a++ ) {
                    duration += melody[a].getDuration();
                }
            }
            
        
        }
            
        return duration;
    }
    public boolean octaveDown() {//checks if it can move an octave down then it does if possible or returns false
        boolean canChangeDown = true;
        for (Note n : melody) {
            if (n.getPitch() != Pitch.R) { // checks if pitch is a rest
                if (n.getOctave() == 1) { // checks that there isn't the lowest octave
                    canChangeDown = false; // n.getOctave makes sure octave isn't one
                }
            }
        }
        if (canChangeDown == false) {
            return false; 
        } else {
            for (Note na : melody) {
                if (na.getPitch() != Pitch.R) { // check if its a rest
                    na.setOctave(na.getOctave() - 1); // if isn't res set the octave to octave minus 1
                }
            }
        }
        return canChangeDown; // was originally jus treturning false check what this is used for and find what you should return
    } // that may be just supposed to return false but IDK

    public boolean octaveUp() {//checks if it can move octave up and does it if it can
        boolean canChangeDown = true;
        for (Note n : melody) {
            if (n.getPitch() != Pitch.R) { /// checks if pitch is rest
                if (n.getOctave() == 10) { // and checked against R this is just making sure that its not a rest
                    canChangeDown = false; // n.getOctave makes sure octave isn't one
                }
            }
        }
        if (canChangeDown == false) {
            return false;
        } else {
            for (Note na : melody) {
                if (na.getPitch() != Pitch.R) { 
                    na.setOctave(na.getOctave() + 1); // if isn't rest set the octave to octave minus 1
                }
            }
        }
        return canChangeDown; // was originally jus treturning false check what this is used for and find what you should return
    } // that may be just supposed to return false but IDK

    public void play() { //plays the melody
        int starting = 0;// YOU CANNOT CALL PLAY if you have your gui already called
        boolean going = false;// This error is not from me, but from the gui
        for(int i = 0; i < melody.length; i++) {
            //System.out.println("About to play");
            melody[i].play();
            //System.out.println("Played");
            if (melody[i].isRepeat() && going == false) {//if it is the first repeat
                starting = i;
                going = true;
            } else if(melody[i].isRepeat()) {// only for second repeating will repeat the section
                for(int a = starting; a <= i; a++) {// this starting doesn't matter
                    melody[a].play();
                }
                going = false;
            }
        }
    }

    public void reverse() {//reverses the order of melody
        Note holderOne;// this uses 2 place holder method and switches between them while setting
        Note holderTwo;
        for(int i = 0; i < (melody.length/2); i++) { // check if its <= or <
            holderOne = melody[i];
            holderTwo = melody[melody.length-(i + 1)]; // there is an error in this line fix it
            melody[i] = holderTwo;
            melody[melody.length-(i + 1)] = holderOne;
        }
    }

    public String toString() {// Puts info into a string and returns it. 
        String output = new String("");
        int numberOfNotes = 0;
        for (Note n: melody) {
            output+="\nNew Note";
            output+=("\nthe duration is " + n.getDuration());
            output+=(" the pitch is " + n.getPitch());
            if(n.getPitch() != Pitch.R) {
                output+=(" the octave is " + n.getOctave());
                output+=(" the accidental is " + n.getAccidental());
            }
            output+=(" reapeating is " + n.isRepeat());
            numberOfNotes++;
        }
        output+= ("\nnumber of Notes in the song is " + numberOfNotes);
        return output;
    }
    public void mergeMelody(Melody melody2) {
        Note[] temp = new Note[melody.length + melody2.melody.length]; // this makes a new array and sets melody to it. 
        for (int i = 0; i< melody.length; i++) {// this array is the sumation of the original melody and melody2(the parameter)
            temp[i] = melody[i];
        }
        for (int a = 0; a< melody2.melody.length; a++) {
            temp[a+melody.length] = melody2.melody[a];
        }
        melody = temp;  
    }
    
    
    // From this point everything is extra credit
    
    //This is Extra credit part 6
    public void playXTimes(int x) {// This method plays the song x times
        for (int i = 0; i < x; i++) {
            play();
        }
    }
    //This is Extra credit part 7
    // sets all of the accidentals in melody to sharp
    public void toSharp() {
        for(Note note: melody) {
            note.setAccidental(Accidental.SHARP);
        }
    }
    //This is Extra credit part 8
    //sets all of the accidentals in melody to flat
    public void toFlat() {
        for(Note note: melody) {
            note.setAccidental(Accidental.FLAT);
        }
    }
    //removes all accidentals from melody.
    //This is Extra credit part 9
    public void toNatural() {
        for(Note note: melody) {
            note.setAccidental(Accidental.NATURAL);
        }
    }
}
