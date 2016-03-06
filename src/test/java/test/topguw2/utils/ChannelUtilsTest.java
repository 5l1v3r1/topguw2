/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.topguw2.utils;

import gsm.topguw.channels.Channels;
import gsm.topguw.conf.RtlsdrConf;
import gsm.topguw.err.ChannelError;
import gsm.topguw.generality.Cell;
import gsm.topguw.tools.Decode;
import gsm.topguw2.utils.ChannelUtils;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import org.junit.Test;


/**
 *
 * @author root
 */
public class ChannelUtilsTest {
    
    public static void main(String[] args) {
        ChannelUtilsTest a = new ChannelUtilsTest();
        a.testFindTimeslot();
    }
    /**
     * Test of findImmediateAssig method, of class ChannelUtils.
     */
    @Test
    public void testFindTimeslot() {
       // cell
        Cell cell = new Cell("951500000", 121, "GSM900", "152200");
        // rtl conf
        RtlsdrConf conf = new RtlsdrConf(0, 1000000, 0);

        // channel
        Channels broadcast;

        try {
            broadcast = Decode.getChannel("noncombined", 0, 0, new File("/root/Bureau/temp/test2.cfile"));
            // get all frames from the capture
            Decode.getChannelFrame(broadcast, cell, conf, new String[0]);
            // found immediate assignment frame
            ArrayList<Integer> im = ChannelUtils.findImmediateAssig(broadcast);
            // try to find which timeslot is used for standalone channel
            System.out.println(ChannelUtils.findTimeSlot(broadcast, im));
            
        } catch (ChannelError e) {
            System.err.println(e.getMessage());
        }
        
    }

    
}
