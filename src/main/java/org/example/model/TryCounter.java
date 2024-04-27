package org.example.model;

import java.util.HashMap;
import java.util.Map;

public class TryCounter {
    int failedRFIDCount = 0;
    int failedFingerCount = 0;
    int failedFaceCount = 0;

    public TryCounter() {
    }

    public boolean incrementFailedAttempts(char attemptType){
        switch(attemptType){
            case 'p':
                // Failed fingerprint attempt
                failedFingerCount++;
                System.out.println("Current Failed Fingerprint Scans: " + failedFingerCount);
                if(failedFingerCount >= 3){
                    // Fail count exceeded
                    System.out.println("Fingerprint Fail Count Exceeded. ");
                    return true;
                }
                break;
            case 'f':
                // Failed face attempt
                failedFaceCount++;
                System.out.println("Current Failed Face Scans: " + failedFaceCount);
                if(failedFaceCount >= 3){
                    // Fail count exceeded
                    System.out.println("Face Fail Count Exceeded. ");
                    return true;
                }
                break;
            case 'r':
                // Failed rfid attempt
                failedRFIDCount++;
                System.out.println("Current Failed RFID Scans: " + failedRFIDCount);
                if(failedRFIDCount >= 3){
                    // Fail count exceeded
                    System.out.println("RFID Fail Count Exceeded. ");
                    return true;
                }
                break;
        }
        return false;
    }
}
