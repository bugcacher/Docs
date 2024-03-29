package com.example.docs.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognitionService;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.docs.Document;
import com.example.docs.Editor;
import com.example.docs.MainActivity;
import com.example.docs.R;
import com.example.docs.RecyclerAdapter;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class Recent_fragment extends Fragment {
    List<Document> myList = new ArrayList<>();
    FirebaseFirestore database = FirebaseFirestore.getInstance();
    CollectionReference myCollection = database.collection("My Documents");
    RecyclerView recyclerView;
    RecyclerAdapter adapter;

    @Override
    public void onStart() {
        super.onStart();
        getData();

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recent_fragment,container,false);
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(Recent_fragment.this.getContext()
                ,2));
       /* myCollection.get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        if (queryDocumentSnapshots==null)
                        {
                            return;
                        }
                        else {
                            for (QueryDocumentSnapshot snapshot : queryDocumentSnapshots)
                            {
                                Document doc = snapshot.toObject(Document.class);
                                myList.add(doc);
                            }
                        }

                        adapter = new RecyclerAdapter(Recent_fragment.this.getContext(),myList);
                        recyclerView.setAdapter(adapter);
                        adapter.notifyDataSetChanged();

                    }
                });

*/
        return  view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.sort_menu,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.gridMenu:
                recyclerView.setLayoutManager(new GridLayoutManager(Recent_fragment.this.getContext()
                        ,2));
                return true;
            case R.id.listMenu:
                recyclerView.setLayoutManager(new LinearLayoutManager(Recent_fragment.this.getContext()));
                return true;
            case R.id.lastModifiedMenu:
                        getSortedasModified();
                break;
            case R.id.nameMenu:
                    getSortedasName();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    void getData()
    {
        myCollection.whereEqualTo("onBin",false)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@javax.annotation.Nullable QuerySnapshot queryDocumentSnapshots, @javax.annotation.Nullable FirebaseFirestoreException e) {
                if (e!=null)
                {
                    return;
                }else {
                    if (queryDocumentSnapshots==null)
                    {
                        return;
                    }
                    else {
                        myList.clear();
                        for (QueryDocumentSnapshot snapshot : queryDocumentSnapshots)
                        {
                            Document doc = snapshot.toObject(Document.class);
                            doc.setDocID(snapshot.getId());
                            myList.add(doc);
                        }
                    }

                    adapter = new RecyclerAdapter(Recent_fragment.this.getContext(),myList);
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }

            }
        });
    }
    void getSortedasName()
    {
        myCollection.whereEqualTo("onBin",false)
                .orderBy("fileName")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@javax.annotation.Nullable QuerySnapshot queryDocumentSnapshots,
                                @javax.annotation.Nullable FirebaseFirestoreException e) {
                if (e!=null)
                {
                    return;
                }else {
                    if (queryDocumentSnapshots==null)
                    {
                        return;
                    }
                    else {
                        myList.clear();
                        for (QueryDocumentSnapshot snapshot : queryDocumentSnapshots)
                        {
                            Document doc = snapshot.toObject(Document.class);
                            doc.setDocID(snapshot.getId());
                            myList.add(doc);
                        }
                    }
                    adapter = new RecyclerAdapter(Recent_fragment.this.getContext(),myList);
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }
            }
        });
    }

    void getSortedasModified()
    {
        myCollection.whereEqualTo("onBin",false)
                .whereEqualTo("onBin",false)
                .orderBy("date", Query.Direction.DESCENDING)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@javax.annotation.Nullable QuerySnapshot queryDocumentSnapshots,
                                        @javax.annotation.Nullable FirebaseFirestoreException e) {
                        if (e!=null)
                        {
                            return;
                        }else {
                            if (queryDocumentSnapshots==null)
                            {
                                return;
                            }
                            else {
                                myList.clear();
                                for (QueryDocumentSnapshot snapshot : queryDocumentSnapshots)
                                {
                                    Document doc = snapshot.toObject(Document.class);
                                    doc.setDocID(snapshot.getId());
                                    myList.add(doc);
                                }
                            }
                            adapter = new RecyclerAdapter(Recent_fragment.this.getContext(),myList);
                            recyclerView.setAdapter(adapter);
                            adapter.notifyDataSetChanged();
                        }
                    }
                });
    }

}
