package database;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.*;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class DBHandler {
    private static DBHandler instance = null;
    private Firestore db = null;

    public static DBHandler getInstance() {
        if (instance == null) {
            instance = new DBHandler();
        }
        return instance;
    }

    private DBHandler() {
        //initialising
        try {
            // Use a service account
            InputStream serviceAccount = new FileInputStream("C:\\Users\\olive\\OneDrive\\Documents\\Personal\\Projects\\TODO\\TODO\\src\\main\\resources\\todo-20520-3ba08733331c.json");
            GoogleCredentials credentials = GoogleCredentials.fromStream(serviceAccount);
            FirebaseOptions options =  FirebaseOptions.builder()
                    .setCredentials(credentials)
                    .build();
            FirebaseApp.initializeApp(options);

            this.db = FirestoreClient.getFirestore();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeToData(String toWrite) {
        DocumentReference docRef = db.collection("users").document("alovelace");
        // Add document data  with id "alovelace" using a hashmap
        Map<String, Object> data = new HashMap<>();
        data.put("first", "Ada");
        data.put("last", "Lovelace");
        data.put("born", 1815);
        //asynchronously write data
        ApiFuture<WriteResult> result = docRef.set(data);
        // ...
        // result.get() blocks on response

        try {
            System.out.println("Update time : " + result.get().getUpdateTime());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    public void readData() {
        // asynchronously retrieve all users
        ApiFuture<QuerySnapshot> query = db.collection("users").get();
// ...
// query.get() blocks on response
        QuerySnapshot querySnapshot = null;
        try {
            querySnapshot = query.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
        for (QueryDocumentSnapshot document : documents) {
            System.out.println("User: " + document.getId());
            System.out.println("First: " + document.getString("first"));
            if (document.contains("middle")) {
                System.out.println("Middle: " + document.getString("middle"));
            }
            System.out.println("Last: " + document.getString("last"));
            System.out.println("Born: " + document.getLong("born"));
        }
    }
}
