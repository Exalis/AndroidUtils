package com.exalis.androidutils.ErrorHandling;

import android.util.Log;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.Console;
import java.util.*;

public class FirebaseErrorHandler {

    private FirebaseFirestore firestoreInstance;

    public FirebaseErrorHandler(FirebaseFirestore firestoreInstance){
        this.firestoreInstance = firestoreInstance;
    }

    public void logErrorInDatabase(Exception error){
        firestoreInstance.collection("Errors")
                .document(getCurrentUserUID())
                .collection("UserErrors")
                .document(getRandomUUID())
                .set(getHashMapDataForLogging(error));
    }

    private HashMap<String, Object> getHashMapDataForLogging(Exception error) {
        HashMap<String, Object> dataForLogging = new HashMap<>();
        dataForLogging.put("UserUID", getCurrentUserUID());
        dataForLogging.put("StackTraces", getStackTraces(error));
        dataForLogging.put("Message", error.getMessage());
        dataForLogging.put("Date", getCurrentTimestamp());

        return dataForLogging;
    }

    private Timestamp getCurrentTimestamp() {
        Date date = new Date();

        return new Timestamp(date);
    }

    private String getRandomUUID() {
        return UUID.randomUUID().toString();
    }

    private List<String> getStackTraces(Exception error) {
        List<String> stackTrace = new ArrayList<>();
        for(StackTraceElement traceElement : error.getStackTrace()){
            stackTrace.add(traceElement.toString());
        }
        return stackTrace;
    }

    public String getCurrentUserUID(){
        return FirebaseAuth.getInstance().getCurrentUser().getUid();
    }

}
