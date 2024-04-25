package org.example.service;
import org.example.model.Face;

public class FaceAuthenticator {
    int faceAuthID;
    public FaceAuthenticator(int authID){
        faceAuthID = authID;
    }

    public Face scanFace(){
        return new Face(0,0);
    }
}
