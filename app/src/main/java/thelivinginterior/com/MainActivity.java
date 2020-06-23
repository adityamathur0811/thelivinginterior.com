package thelivinginterior.com;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.ismaeldivita.chipnavigation.ChipNavigationBar;

import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    String search,whichFragment;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        ChipNavigationBar bottomNavigationView = findViewById(R.id.nav);

        bottomNavigationView.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int i) {
             switch (i)
             {
                 case R.id.item1:
                     setFragment(new HomeFragment());
                        break;
                 case R.id.item2:
                     setFragment(new ProductFragment());
                     break;
                 case R.id.item3:
                     setFragment(new OurServicesFragment());
                     break;
             }

            }
        });
        String temp=getIntent().getStringExtra("which");
        if (temp==null)
        {
            whichFragment="home";
        }
        else
        {
            whichFragment=temp;
        }
        if (whichFragment.equalsIgnoreCase("home"))
        {
            setFragment(new HomeFragment());
            bottomNavigationView.setItemSelected(R.id.item1,true);

        }
        else if (whichFragment.equalsIgnoreCase("product"))
        {
            setFragment(new ProductFragment());
            bottomNavigationView.setItemSelected(R.id.item2,true);
            whichFragment="home";
        }
        else {
            setFragment(new ProductFragment());
            bottomNavigationView.setItemSelected(R.id.item3,true);
            whichFragment="home";

        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.searched_item, menu);
        MenuItem searchBar = menu.findItem(R.id.search_bar);


        MenuItem mic = menu.findItem(R.id.mic);
        mic.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent i=new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                i.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                i.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
                i.putExtra(RecognizerIntent.EXTRA_PROMPT,"what you want to search");
                startActivityForResult(i,100);
                return true;
            }
        });


        final SearchView searchView = (SearchView) searchBar.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                search = query;
                Bundle bundle = new Bundle();
                if(!query.isEmpty())
                {
                    bundle.putString("Search", search);
                }


                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                return true;
            }
        });


        return super.onCreateOptionsMenu(menu);

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        try {
            assert data != null;
            List<String> list = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            assert list != null;
            String value = list.get(0);
            if (!value.isEmpty())
            {
                search = value;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
    public void setFragment(Fragment fragment) {
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.setFragment, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

}