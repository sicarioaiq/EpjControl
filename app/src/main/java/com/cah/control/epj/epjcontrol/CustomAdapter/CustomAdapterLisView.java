package com.cah.control.epj.epjcontrol.CustomAdapter;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.cah.control.epj.epjcontrol.R;

import java.util.ArrayList;


public class CustomAdapterLisView extends ArrayAdapter<String> {

    private final Activity context;
//    private final String[] itemname;
    private final ArrayList<String> arrayListNombres;
    private final ArrayList<String> arrayListEstados;
//		private final Integer[] imgid;

    public CustomAdapterLisView(Activity context, ArrayList<String> arrayListNombres,ArrayList<String> arrayListEstados) {
        super(context, R.layout.list_view_custom, arrayListNombres);
        // TODO Auto-generated constructor stub

        this.context=context;
        this.arrayListNombres=arrayListNombres;
        this.arrayListEstados=arrayListEstados;
//			this.imgid=imgid;
    }

    public View getView(int position,View view,ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.list_view_custom, null,true);
        rowView.setBackgroundColor(Color.BLACK);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);

        TextView tvNombre = (TextView) rowView.findViewById(R.id.tvNombre);

        TextView tvDescripcion = (TextView) rowView.findViewById(R.id.tvDescripcion);
        TextView tvDescripcion1 =  (TextView) rowView.findViewById(R.id.tvDescripcion1);
        TextView tvDescripcion2 =  (TextView) rowView.findViewById(R.id.tvDescripcion2);
        TextView tvDescripcion3 =  (TextView) rowView.findViewById(R.id.tvDescripcion3);
        TextView tvDescripcion4 =  (TextView) rowView.findViewById(R.id.tvDescripcion4);
        TextView tvDescripcion5 =  (TextView) rowView.findViewById(R.id.tvDescripcion5);

        TableLayout table_layout = (TableLayout) rowView.findViewById(R.id.tableLayout);
        TableRow tr2 = (TableRow) rowView.findViewById(R.id.tr2);
        TableRow tr4 = (TableRow) rowView.findViewById(R.id.tr4);
        TableRow tr6 = (TableRow) rowView.findViewById(R.id.tr6);

        if ( arrayListEstados.get(position).equals("0"))
        {
            imageView.setBackgroundColor(Color.RED);
            imageView.setBackgroundResource(R.drawable.rojo);
        }
        else
        {
            imageView.setBackgroundColor(Color.GREEN);
            imageView.setBackgroundResource(R.drawable.verde);
        }

        imageView.setAdjustViewBounds(true);
        tvNombre.setTextColor(Color.BLACK);
        tvNombre.setTextSize(20);
        tvNombre.setText(arrayListNombres.get(position));
        tvNombre.setGravity(Gravity.LEFT);

        tvDescripcion.setTextColor(Color.BLACK);
        tvDescripcion.setTextSize(12);
        tvDescripcion.setGravity(Gravity.RIGHT);
        tvDescripcion.setText("Description " + arrayListNombres.get(position));

        tvDescripcion1.setTextColor(Color.BLACK);
        tvDescripcion1.setTextSize(12);
        tvDescripcion1.setGravity(Gravity.RIGHT);
        tvDescripcion1.setText("Pago "+arrayListNombres.get(position));

        tr2.setLayoutParams(new TableLayout.LayoutParams(
                TableLayout.LayoutParams.FILL_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
        FrameLayout linea_cabecera = new FrameLayout(context);


        TableRow.LayoutParams linea_cabecera_params = new TableRow.LayoutParams(TableLayout.LayoutParams.FILL_PARENT, 4);
        linea_cabecera_params.span = 3;
        linea_cabecera.setBackgroundColor(Color.parseColor("#000000"));
        tr2.addView(linea_cabecera, linea_cabecera_params);

        tvDescripcion2.setTextColor(Color.BLACK);
        tvDescripcion2.setTextSize(12);
        tvDescripcion2.setText("Description " + arrayListNombres.get(position));
        tvDescripcion2.setGravity(Gravity.RIGHT);

        tvDescripcion3.setTextColor(Color.BLACK);
        tvDescripcion3.setTextSize(12);
        tvDescripcion3.setText("Pago " + arrayListNombres.get(position));
        tvDescripcion3.setGravity(Gravity.RIGHT);

        tr4.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.FILL_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
        FrameLayout linea_cabecera1 = new FrameLayout(context);
        TableRow.LayoutParams linea_cabecera_params1 = new TableRow.LayoutParams(TableLayout.LayoutParams.FILL_PARENT, 4);
        linea_cabecera_params1.span = 3;
        linea_cabecera1.setBackgroundColor(Color.parseColor("#000000"));
        tr4.addView(linea_cabecera1, linea_cabecera_params1);


        tvDescripcion4.setTextColor(Color.BLACK);
        tvDescripcion4.setTextSize(12);
        tvDescripcion4.setText("Description " + arrayListNombres.get(position));
        tvDescripcion4.setGravity(Gravity.RIGHT);

        tvDescripcion5.setTextColor(Color.BLACK);
        tvDescripcion5.setTextSize(12);
        tvDescripcion5.setText("Pago " + arrayListNombres.get(position));
        tvDescripcion5.setGravity(Gravity.RIGHT);

        tr6.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.FILL_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
        FrameLayout linea_cabecera3 = new FrameLayout(context);
        TableRow.LayoutParams linea_cabecera_params3 = new TableRow.LayoutParams(TableLayout.LayoutParams.FILL_PARENT, 4);
        linea_cabecera_params3.span = 3;
        linea_cabecera3.setBackgroundColor(Color.parseColor("#000000"));
        tr6.addView(linea_cabecera3, linea_cabecera_params3);


        TableRow separador_cabecera2 = new TableRow(context);
        separador_cabecera2.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.FILL_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
        FrameLayout linea_cabecera2 = new FrameLayout(context);
        TableRow.LayoutParams linea_cabecera_params2 = new TableRow.LayoutParams(TableLayout.LayoutParams.FILL_PARENT, 4);
        linea_cabecera_params2.span = 3;
        linea_cabecera2.setBackgroundColor(Color.parseColor("#000000"));
        separador_cabecera2.addView(linea_cabecera2, linea_cabecera_params2);
        table_layout.addView(separador_cabecera2);

        return rowView;

    };
}

