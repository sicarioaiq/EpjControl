package com.cah.control.epj.epjcontrol;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.cah.control.epj.epjcontrol.CustomAdapter.*;
import com.cah.control.epj.epjcontrol.DAO.SQLController;
import com.cah.control.epj.epjcontrol.DAO.TMPersonaDA;

import java.util.ArrayList;
import java.util.List;

import static com.cah.control.epj.epjcontrol.CustomAdapter.CustomAdapter.*;


@SuppressLint("NewApi")
public class PersonaFragment extends Fragment implements AdapterView.OnItemSelectedListener{
    TableLayout table_layout;
    View rootView;
    EditText  etNombre;
    TMPersonaDA sqlcon;
    Spinner spinnerNombre;
    ListView list;
    String[] etiquetaDescripcion ={
            "César Aiquipa Herrera",
            "Mariana Tavara",
            "Eros Wong",
            "Aaron Wong",
            "Jacky Huaringa",
            "Android Folder",
            "VLC Player",
            "Cold War"
    };

    ArrayList<String> arrayListNombres;
    ArrayList<String> arrayListEstados;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_persona, container, false);
        spinnerNombre = (Spinner)rootView.findViewById(R.id.spinnerNombre);
        etNombre = (EditText)rootView.findViewById(R.id.etNombre);
        spinnerNombre.setOnItemSelectedListener(this);
        cargarComboNombres();
        getPersonas();
        BuildListView();

        return rootView;
    }

    private void cargarComboNombres() {
        sqlcon = new TMPersonaDA(this.getActivity());
        sqlcon.open();
        List<String> lables = sqlcon.getAllPerona(true);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, lables);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinnerNombre.setAdapter(dataAdapter);
        sqlcon.close();
    }

    private void getPersonas(){
        sqlcon = new TMPersonaDA(this.getActivity());
        sqlcon.open();
        final Cursor c = sqlcon.readEntryPersona(etNombre.getText().toString().trim());
        final int rows = c.getCount();
        final int cols = c.getColumnCount();
        c.moveToFirst();
        arrayListNombres = new ArrayList<String>();
        arrayListEstados = new ArrayList<String>();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                //se obtiene solo el codigo con el j==1
                if (j==0)
                {

                }
                //se obtiene solo el nombre con el j==1
                if (j==1)
                {
                    arrayListNombres.add(c.getString(j));
                }
                //se obtiene solo los estados con el j==2
                if (j==2)
                {
                    arrayListEstados.add(c.getString(j));
                }
            }
            c.moveToNext();
        }
        c.close();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }




    private void BuildListView() {
        CustomAdapterLisView adapter = new CustomAdapterLisView(this.getActivity(), arrayListNombres,arrayListEstados);
        list=(ListView)rootView.findViewById(R.id.list);
        list.setAdapter(adapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String label = parent.getItemAtPosition(position).toString();

        if (parent == spinnerNombre)
            if (label=="Todos") etNombre.setText(""); else etNombre.setText(label);
//        else
//        if (label=="Todos") nombre_et.setText(""); else nombre_et.setText(label);

//        table_layout.removeAllViews();
        etNombre.setWidth(1);
        etNombre.setHeight(1);
        getPersonas();
        BuildListView();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
//    private void BuildTable() {
//        int rows = 22;
//        for (int i = 0; i < rows; i++) {
////            final TableRow row2 = new TableRow(this.getActivity());
////            row2.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
////            row2.setBackgroundResource(R.drawable.linearlayout_bg);
//
//            final TableRow row = new TableRow(this.getActivity());
//            row.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
//            row.setBackgroundResource(R.drawable.border);
//
//            final TableRow row1 = new TableRow(this.getActivity());
//            row1.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
//            row1.setBackgroundResource(R.drawable.border);
//
////            final ImageView img = new ImageView(this.getActivity());
////            img.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
////            img.setBackgroundColor(Color.GREEN);
////            img.setVisibility(View.VISIBLE);
////            row.addView(img,0);
//
//            TableRow.LayoutParams params = (TableRow.LayoutParams) row.getLayoutParams();
//            params.span =2;
//
//
//            final TextView tv1 = new TextView(this.getActivity());
//            tv1.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
//            tv1.setLayoutParams(new TableRow.LayoutParams(0));
//            tv1.setBackgroundColor(Color.TRANSPARENT);
//
//            tv1.setBackgroundResource(R.drawable.abc_btn_default_mtrl_shape);
//            tv1.setGravity(Gravity.LEFT);
//            tv1.setTextSize(18);
//            tv1.setText("Cesar Miguel Aiquipa Herrera " + i);
//            row.setLayoutParams(params);
//            row.addView(tv1, 0, params);
////            row2.addView(row,0,params);
//
//            final TextView tv2 = new TextView(this.getActivity());
//            tv2.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
//            tv2.setLayoutParams(new TableRow.LayoutParams(0));
//            tv2.setBackgroundColor(Color.TRANSPARENT);
//            tv2.setBackgroundResource(R.drawable.abc_btn_default_mtrl_shape);
//            tv2.setGravity(Gravity.RIGHT);
//            tv2.setText("N° Tickets pagados");
//            row1.addView(tv2, 0);
////            row2.addView(tv2, 1);
//
//
//            final TextView tv3 = new TextView(this.getActivity());
//            tv3.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
//            tv3.setLayoutParams(new TableRow.LayoutParams(1));
//            tv3.setBackgroundColor(Color.TRANSPARENT);
//            tv3.setBackgroundResource(R.drawable.abc_btn_default_mtrl_shape);
//            tv3.setGravity(Gravity.RIGHT);
//            tv3.setText("Pago: " + i);
//            row1.addView(tv3, 1);
//
//
//            TableRow separador_cabecera = new TableRow(this.getActivity());
//            separador_cabecera.setLayoutParams(new TableLayout.LayoutParams(
//                    TableLayout.LayoutParams.FILL_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
//            FrameLayout linea_cabecera = new FrameLayout(this.getActivity());
//            TableRow.LayoutParams linea_cabecera_params = new TableRow.LayoutParams(TableLayout.LayoutParams.FILL_PARENT, 4);
//            linea_cabecera_params.span = 3;
//            linea_cabecera.setBackgroundColor(Color.parseColor("#FFFFFF"));
//            separador_cabecera.addView(linea_cabecera, linea_cabecera_params);
//            table_layout.addView(separador_cabecera);
////            row2.addView(tv3, 2);
//
//            table_layout.addView(row);
//            table_layout.addView(row1);
//        }
//
//
//    }