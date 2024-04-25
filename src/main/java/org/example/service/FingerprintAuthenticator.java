package org.example.service;

import org.example.model.Face;
import org.example.model.Fingerprint;

public class FingerprintAuthenticator {
    int fingerprintAuthID;
    public FingerprintAuthenticator(int authID){
        fingerprintAuthID = authID;
    }

    public Fingerprint scanFingerprint(){
        return new Fingerprint(0,0);
    }
}
