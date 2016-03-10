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
import gsm.topguw.conf.RtlsdrConf;
import gsm.topguw.err.ChannelError;
import gsm.topguw.generality.Cell;
import gsm.topguw.tools.Decode;
import java.io.File;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;


/**
 *
 * @author bastien.enjalbert
 */
public class Utils {


        /**
         * Perform an analyse on a cfile to try to recover some keystream
         * @param cfile the cfile to work on
         * @param workspace workspace that stores analysis files
         * @param rtlconf the rtl-sdr device conf
         * @param cell the cell
         * @param log the text location where data will be
         */
        public static void performAnalyse(File cfile, File workspace, RtlsdrConf rtlconf, Cell cell, JTextPane log) {
            try {
                // getting the broadcast channel
                Channels broadcast = Decode.getChannel("standalonecontrol", 0, 0, new File("/root/Bureau/temp/test2.cfile"));
                //findImmediateAssig
                appendString("test", log);
            } catch (ChannelError | BadLocationException ex) {
                //log.getStyledDocument().insertString(log.getStyledDocument().getLength(), ex.getMessage(), null);
            }
            
            
            
        }
        
        public static void appendString(String str, JTextPane jtext) throws BadLocationException {
             StyledDocument document = (StyledDocument) jtext.getDocument();
             document.insertString(document.getLength(), str, null);
                                                            // ^ or your style attribute  
         }
   
    
}
