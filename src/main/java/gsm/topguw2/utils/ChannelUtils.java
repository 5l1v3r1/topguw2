/* Utils.java - 23 fe. 2016  -  UTF-8 - 
 * --------------------------------- DISCLAMER ---------------------------------
 * Copyright (c) 2015, Bastien Enjalbert All rights reserved.
 * Redistribution and use in source and binary forms, with or without modification, 
 * are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, 
 * this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright notice, 
 * this list of conditions and the following disclaimer in the documentation 
 * and/or other materials provided with the distribution.

 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND 
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED 
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. 
 * IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, 
 * INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, 
 * BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, 
 * OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, 
 * EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 * The views and conclusions contained in the software and documentation are 
 * those of the authors and should not be interpreted as representing official 
 * policies, either expressed or implied, of the FreeBSD Project.
 * @author Bastien Enjalbert
 */
package gsm.topguw2.utils;

import gsm.topguw.channels.Channels;
import gsm.topguw.generality.Frame;
import java.math.BigInteger;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

/**
 * Contains many methods to analyze GSM datas
 *
 * @author bastien.enjalbert
 */
public class ChannelUtils {

    /**
     * Find immediate assignment frames
     *
     * @param broadcast the broadcast channel with GSM datas
     * @return all frame numbers of immediate assigment found or empty if
     * nothing was found
     */
    public static ArrayList<Integer> findImmediateAssig(Channels broadcast) {

        ArrayList<Integer> found = new ArrayList<>();

        for (Map.Entry pair : broadcast.getRecordedFrames().entrySet()) {
            if (((Frame) pair.getValue()).getData()[1].equals("06")
                    && ((Frame) pair.getValue()).getData()[2].equals("3f")) {
                found.add((Integer) pair.getKey());
            }
        }
        return found;
    }

    /**
     * Find which timeslot is used for standalone traffic
     *
     * @param broadcast the broadcast channel with GSM datas
     * @param im immediate assignment already found
     * @return the timeslot or null is nothing was found
     */
    public static Integer findTimeSlot(Channels broadcast, ArrayList<Integer> im) {
        //todo verif null poointerexception qd on récup les données
        System.err.println("taille " + broadcast.getRecordedFrames().size());
        for (int i = 0; i < im.size(); i++) {
            String aboutTm = broadcast.getRecordedFrames().get(im.get(i)).getData()[4];
            // convert datas in binary
            aboutTm = new BigInteger(aboutTm, 16).toString(2);
            // Check integrity of the binary frame
            for (; aboutTm.length() < 8;) {
                aboutTm = "0" + aboutTm;
            }
            // if the frame redirects to a real channel type
            if (aboutTm.charAt(1) == '1') {
                // get the timeslot in decimal from binary
                return Integer.parseInt(aboutTm.substring(5, 8), 2);
            }
        }
        return null;
    }

}
