package com.consultoraestrategia.ss_crmeducativo.util;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.DrawableRes;
import android.support.v7.app.AlertDialog;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.consultoraestrategia.ss_crmeducativo.api.RestAPI;
import com.consultoraestrategia.ss_crmeducativo.core2.R;
import com.consultoraestrategia.ss_crmeducativo.entities.BaseEntity;
import com.consultoraestrategia.ss_crmeducativo.entities.EvaluacionProcesoC;
import com.consultoraestrategia.ss_crmeducativo.entities.EvaluacionProcesoC_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.EvaluacionResultado;
import com.consultoraestrategia.ss_crmeducativo.entities.EvaluacionResultado_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.GlobalSettings;
import com.consultoraestrategia.ss_crmeducativo.entities.RubroEvaluacionProcesoC;
import com.consultoraestrategia.ss_crmeducativo.entities.RubroEvaluacionProcesoC_Table;
import com.consultoraestrategia.ss_crmeducativo.entities.queryCustomList.UtilModel;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.NameAlias;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.sql.language.property.IProperty;
import com.raizlabs.android.dbflow.sql.language.property.Property;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.Normalizer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

/**
 * Created by irvinmarin on 13/04/2017.
 */

public class Utils {

    private static final String TAG = Utils.class.getSimpleName();

    public static boolean isLinkYoutube(String txtUrlVideo) {
        Log.d(TAG,"txtUrlVideo: " + txtUrlVideo);
        String[] linkYoutube = {"youtube.com", "youtu.be"};
        if(txtUrlVideo.contains(linkYoutube[0])||txtUrlVideo.contains(linkYoutube[1])){
            return true;
        }else {
            return false;
        }
    }

    public static boolean isOnlineNet() {
        try {
            Process p = Runtime.getRuntime().exec("ping -c 1 ss.consultoraestrategia.com");
            int val = p.waitFor();
            return (val == 0);
        } catch (Exception e) {
            Log.e("isOnlineNet", e.toString());
        }
        return false;
    }

    public static boolean checkServerConnection() {
        try {
            String serverUrl = GlobalSettings.getServerUrl();
            if (TextUtils.isEmpty(serverUrl)) {
                serverUrl = RestAPI.REMOTE_URL;
            }
            String startString = "http://";
            String endString = "/";
            if (TextUtils.isEmpty(serverUrl)) return false;

            int prefixLength = startString.length();
            String domain = serverUrl.substring(prefixLength, serverUrl.indexOf(endString, prefixLength + 1));
            Log.d(TAG, "domain: " + domain);

            Process p = Runtime.getRuntime().exec("ping -c 1 " + domain);
            int val = p.waitFor();
            Log.d(TAG, "val: " + val);
            return (val == 0);
        } catch (Exception e) {
            Log.e("isOnlineNet", e.toString());
        }
        return false;
    }

    public static boolean isOnlineInterNet() {

        try {
            Process p = Runtime.getRuntime().exec("ping -c 1 www.google.com");

            int val = p.waitFor();
            boolean reachable = (val == 0);
            return reachable;

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }

    public static boolean isSuccess(JSONObject jsonObject) {
        boolean estado;
        try {
            estado = jsonObject.getBoolean("Successful");
        } catch (JSONException e) {
            e.printStackTrace();
            estado = false;
        }
        return estado;
    }

    public static String getJsonObResultArray(JSONObject jsonObject) {
        String s = "";
        try {
            s = jsonObject.getJSONArray("Value").toString();

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return s;
    }

    public static IProperty[] f_allcolumnTable(IProperty[] ALL_COLUMN_PROPERTIES) {
        for (int i = 0; i < ALL_COLUMN_PROPERTIES.length; i++) {
            ALL_COLUMN_PROPERTIES[i] = ALL_COLUMN_PROPERTIES[i].withTable();
        }
        return ALL_COLUMN_PROPERTIES;
    }


    public static IProperty[] f_allcolumnTable(IProperty[] ALL_COLUMN_PROPERTIES, IProperty... ALL_SECOND_COLUMN_PROPERTIES) {

        for (int i = 0; i < ALL_COLUMN_PROPERTIES.length; i++) {
            ALL_COLUMN_PROPERTIES[i] = ALL_COLUMN_PROPERTIES[i].withTable();
        }

        for (int i = 0; i < ALL_SECOND_COLUMN_PROPERTIES.length; i++) {
            ALL_SECOND_COLUMN_PROPERTIES[i] = ALL_SECOND_COLUMN_PROPERTIES[i];
        }

        int lengA = ALL_COLUMN_PROPERTIES.length;
        int lengB = ALL_SECOND_COLUMN_PROPERTIES.length;

        IProperty[] iProperties = new IProperty[lengA + lengB];
        System.arraycopy(ALL_COLUMN_PROPERTIES, 0, iProperties, 0, lengA);
        System.arraycopy(ALL_SECOND_COLUMN_PROPERTIES, 0, iProperties, lengA, lengB);


        return iProperties;
    }

    public static String f_fecha_letras(String vstr_Start) {
        String mstr_fecha = "";
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        try {

            String[] vobj_days = {"Domingo", "Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado"};
            String[] vobj_Meses = {"Ene.", "Feb.", "Mar.", "Abr.", "May.", "Jun.", "Jul.", "Ago.", "Sept.", "Oct.", "Nov.", "Dic."};

            Date date = format.parse(vstr_Start);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH); // Jan = 0, dec = 11
            int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
            int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

            mstr_fecha = vobj_days[dayOfWeek - 1] + " " + dayOfMonth + " de " + vobj_Meses[month];
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return mstr_fecha;
    }


    public static String f_fecha_letras(long timesTamp) {

        String mstr_fecha = "";

        String[] vobj_days = {"Dom", "Lun", "Mart", "Mié", "Jue", "Vie", "Sáb"};
        String[] vobj_Meses = {"Ene.", "Feb.", "Mar.", "Abr.", "May.", "Jun.", "Jul.", "Ago.", "Sept.", "Oct.", "Nov.", "Dic."};

        Date date = new Date(timesTamp);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH); // Jan = 0, dec = 11
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        mstr_fecha = vobj_days[dayOfWeek - 1] + " " + dayOfMonth + " de " + vobj_Meses[month];

        return mstr_fecha;
    }

    public static String f_dia_semana(long timesTamp) {

        String mstr_fecha = "";

        String[] vobj_days = {"domingo", "lunes", "martes", "miércoles", "jueves", "viernes", "sábado"};

        Date date = new Date(timesTamp);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        mstr_fecha = vobj_days[dayOfWeek - 1];

        return mstr_fecha;
    }

    public static String f_fecha_letras_2(long timesTamp) {

        String mstr_fecha = "";

        String[] vobj_days = {"Dom", "Lun", "Mart", "Mié", "Jue", "Vie", "Sáb"};
        String[] vobj_Meses = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};

        Date date = new Date(timesTamp);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH); // Jan = 0, dec = 11
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        mstr_fecha = vobj_days[dayOfWeek - 1] + ", " + dayOfMonth  + " "+ vobj_Meses[month] + " " + year;

        return mstr_fecha;
    }

    public static String f_fecha_letras_dos(long timesTamp) {

        String mstr_fecha = "";

        String[] vobj_Meses = {"Ene.", "Feb.", "Mar.", "Abr.", "May.", "Jun.", "Jul.", "Ago.", "Sept.", "Oct.", "Nov.", "Dic."};

        Date date = new Date(timesTamp);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH); // Jan = 0, dec = 11
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        mstr_fecha = dayOfMonth + " " + vobj_Meses[month] + ", "+ year;

        return mstr_fecha;
    }

    public static String capitalize(String x) {
        return x.substring(0, 1).toUpperCase() + x.substring(1).toLowerCase();
    }

    public static String formatPhoneNumber(TelephonyManager manager, String phoneNumber) {
        Log.d(TAG, "formatPhoneNumber: " + phoneNumber);
        String formatNumber = null;
        //TelephonyManager manager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        String usersCountryISOCode = manager.getNetworkCountryIso().toUpperCase();
//Log.d(TAG, "usersCountryISOCode: " + usersCountryISOCode);

        try {

            PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();

            Phonenumber.PhoneNumber numberProto = phoneUtil.parse(phoneNumber,
                    "PE");
            if (phoneUtil.isValidNumber(numberProto)) {
                formatNumber = phoneUtil.format(numberProto, PhoneNumberUtil.PhoneNumberFormat.E164);
                //Log.d(TAG, "formatPhoneNumber: " + phoneNumber + " -> " + formatNumber);
            }
        } catch (Exception e) {
            Log.d(TAG, "phoneNumber: " + phoneNumber + " Exception: " + e.getMessage());
            return null;
        }
        return formatNumber;
    }


    public static String limpiarAcentos(String cadena) {
        String limpio = null;
        if (cadena != null) {
            String valor = cadena;
            valor = valor.toUpperCase();
            // Normalizar texto para eliminar acentos, dieresis, cedillas y tildes
            limpio = Normalizer.normalize(valor, Normalizer.Form.NFD);
            // Quitar caracteres no ASCII excepto la enie, interrogacion que abre, exclamacion que abre, grados, U con dieresis.
            limpio = limpio.replaceAll("[^\\p{ASCII}(N\u0303)(n\u0303)(\u00A1)(\u00BF)(\u00B0)(U\u0308)(u\u0308)]", "");
            // Regresar a la forma compuesta, para poder comparar la enie con la tabla de valores
            limpio = Normalizer.normalize(limpio, Normalizer.Form.NFC);
        }
        return limpio;
    }

    public static String getFirstWord(String text) {
        if (text.indexOf(' ') > -1) { // Check if there is more than one word.
            return text.substring(0, text.indexOf(' ')); // Extract first word.
        } else {
            return text; // Text is the first word itself.
        }
    }

    public static String getDatePhone() {
        Date now = new Date();
        Date alsoNow = Calendar.getInstance().getTime();
        String nowAsString = new SimpleDateFormat("dd/MM/yyyy").format(now);
        return nowAsString;
    }


    /**
     * a = valor minimo del origen
     * b = valor maximo del origen
     * x = valor a transformar
     * c = valor minimo transformado
     * d = valor maximo transformado
     */
    public static double transformacionInvariante(double a, double b, double x, double c, double d) {
        double t = (1 - ((b - x) / (b - a))) * (d - c);
        Log.d(TAG, "notaTransformada: " + "1 - ((" + b + "-" + x + ")/(" + b + "-" + a + "))) * (" + d + " - " + c + ") = " + t);
        return t;
    }

    public static Double formatearDecimales(Double numero, Integer numeroDecimales) {
        return Math.round(numero * Math.pow(10, numeroDecimales)) / Math.pow(10, numeroDecimales);
    }

    public static double formulaConPeso(double a, double b, double x, double c, double d, double peso) {
        double T = transformacionInvariante(a, b, x, c, d);
        return T / 1 - peso;
    }

    public static double stdDev(double promedio,  List<Double> listaNotas) {
        double prom, sum = 0;
        int i;
        double n = listaNotas.size();
        prom = promedio;

        for (i = 0; i < n; i++){
            sum += Math.pow( listaNotas.get(i)- prom, 2);
        }

        return Math.sqrt(sum / (double) n);
    }

    private static RequestOptions getOptions() {
        return new RequestOptions();
    }

    public static RequestOptions getGlideRequestOptionsSimple() {
        return getOptions()
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL);
    }

    public static RequestOptions getGlideRequestOptions() {
        return getOptions()
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .error(R.drawable.ic_error_outline_black);
    }

    public static RequestOptions getGlideRequestOptions(@DrawableRes int res) {
        return getOptions()
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .error(res);
    }

    public static RequestOptions getGlideRequestOptions(Drawable drawable) {
        return getOptions()
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .error(drawable);
    }

    public static String getFechaDetalle(long fecha) {

        Date date = new Date(fecha);
        SimpleDateFormat formatterUI = new SimpleDateFormat("EEEE, d MMM ", Locale.getDefault());
        return formatterUI.format(date);
    }

    public static String getFechaDiaMesAnho(long fecha) {

        Date date = new Date(fecha);
        SimpleDateFormat formatterUI = new SimpleDateFormat("d MMM yyyy", Locale.getDefault());
        return formatterUI.format(date);
    }

    public static String getFechaHora(long fecha){

        Date date = new Date(fecha);
        SimpleDateFormat formatterUI = new SimpleDateFormat("hh:mm a", Locale.getDefault());
        return formatterUI.format(date);
    }

    public static String getFechaDiaMes(long fecha){

        Date date = new Date(fecha);
        SimpleDateFormat formatterUI = new SimpleDateFormat("dd MMMM", Locale.getDefault());
        return formatterUI.format(date);
    }


    public static String getHoraformt24(Date date){
        Calendar calendar = GregorianCalendar.getInstance(); // creates a new calendar instance
        calendar.setTime(date);   // assigns calendar to given date
        calendar.get(Calendar.HOUR_OF_DAY); // gets hour in 24h format
        int horas = calendar.get(Calendar.HOUR);        // gets hour in 12h format
        int minutos = calendar.get(Calendar.MINUTE);
        calendar.get(Calendar.MONTH);       // gets month number, NOTE this is zero based!
        return horas + ":" + minutos;
    }

    public static Calendar getCalendar(Date date){
        Calendar calendar = GregorianCalendar.getInstance(); // creates a new calendar instance
        calendar.setTime(date);   // assigns calendar to given date
        return calendar;
    }

    public static String f_mediaDesviacionEstandar(int key) {
        String mediaDesviacionEstandar = "0.0(0.0)";
        List<Double>listNotas= new ArrayList<>();

        double media=0.0;
        List<EvaluacionResultado>evaluaciones= SQLite.select().from(EvaluacionResultado.class)
                .where(EvaluacionResultado_Table.rubroEvalResultadoId.eq(key)).queryList();
                for(EvaluacionResultado e: evaluaciones){
                    media+=e.getNota()/evaluaciones.size();
                    listNotas.add(e.getNota());
               }
        if(evaluaciones!=null){
            double stdDev = Utils.stdDev(media, listNotas);
            String avg = String.format("%.1f", media);
            String varianza = String.format("%.2f", stdDev);
            if (listNotas.size() == 0) varianza = "0";
            mediaDesviacionEstandar = (avg + "(" + varianza + ")");
        }


        return mediaDesviacionEstandar;
    }

    public static String getFechaDetalleSumado(int nroDiasSumadoDesdeHoy) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, nroDiasSumadoDesdeHoy);  // numero de días a añadir, o restar en caso de días<0

        Date date = new Date(calendar.getTimeInMillis());
        SimpleDateFormat formatterUI = new SimpleDateFormat("EEEE, d MMM ", Locale.getDefault());
        return formatterUI.format(date);
    }



    public static double moda(Integer[] valores) {

        Integer resultado = null;
        Integer contador = 0;
        Integer contadorFinal = 0;


        for (int i = 0; i < valores.length; i++) {

            contador = 0;
            for (int j = 0; j < valores.length; j++) {
                    if (valores[i] ==valores[j])
                        contador++;
            }

            if (contador > contadorFinal) {
                resultado = valores[i];
                contadorFinal = contador;
            }


        }
        Log.d(TAG, "resultado : " + resultado + " repite total de vecves " + contadorFinal);
        return resultado;
    }

    public static IProperty withTableAs(IProperty property, String aliasName){
        NameAlias tableNameAlias = NameAlias.builder(FlowManager.getTableName(property.getTable())).build();
        NameAlias withTableNameAlias = NameAlias.builder(property.getNameAlias().name()).withTable(tableNameAlias.name()).build();
        return  new Property<>(property.getTable(), withTableNameAlias.getFullQuery(), aliasName);


    }


    public static String changeTime12Hour(int hr , int min){
        return  hr%12 + ":" + min + " " + ((hr>=12) ? "PM" : "AM");
    }

    public static String changeTime12Hour(String _24HourTime){
        String hora = null;
        try {
            SimpleDateFormat _24HourSDF = new SimpleDateFormat("HH:mm");
            SimpleDateFormat _12HourSDF = new SimpleDateFormat("hh:mm a");
            Date _24HourDt = _24HourSDF.parse(_24HourTime);
            hora = _12HourSDF.format(_24HourDt);
        }catch (Exception e){
            e.printStackTrace();

        }
        return  hora;
    }



    public static void MsgConfirm(Activity activity, String titulo, String msg, DialogInterface.OnClickListener listenerSi, DialogInterface.OnClickListener listenerNo) {
        AlertDialog.Builder alert = new AlertDialog.Builder(activity);
        alert.setTitle(titulo);
        alert.setMessage(msg);
        alert.setPositiveButton("Si", listenerSi);
        alert.setNegativeButton("No", listenerNo);
        alert.show();
    }

    public static  <T> List<List<T>> chunkSizeList(List<T> list, int chunkSize) {
        if (chunkSize <= 0) {
            throw new IllegalArgumentException("Invalid chunk size: " + chunkSize);
        }
        List<List<T>> chunkList = new ArrayList<>(list.size() / chunkSize);
        for (int i = 0; i < list.size(); i += chunkSize) {
            chunkList.add(list.subList(i, i + chunkSize >= list.size() ? list.size() : i + chunkSize));
        }
        return chunkList;
    }


    public static  <T> List<List<T>> chunkList(List<T> list, int chunk) {
        if (chunk <= 0 || list.size() <  chunk) {
            throw new IllegalArgumentException("Invalid chunk size: " + chunk);
        }

        int chunkSize = list.size() / chunk;

        List<List<T>> result = new ArrayList<>();
        int salto = 0;
        for (int i = 0; i < chunk; i++) {
            result.add(list.subList(salto,salto + chunkSize >= list.size() ? list.size() : salto + chunkSize));
            salto = salto + chunkSize;
        }

        if(salto <  list.size()){
            int cantidaddfaltante = list.size() - salto;
            int posicion = 0;
            for (int i = 0; i < cantidaddfaltante; i ++) {
                if(posicion > list.size())posicion = 0;
                List<T> tList = new ArrayList<>(result.get(posicion));
                tList.add(list.get(salto + i));
                result.set(posicion, tList);
                posicion++;
            }
        }

        return result;
    }

    public static long transformarFecha_a_FechaAsistenciaSinHora(long fechaAsistencia) {
        Date date =  new Date(fechaAsistencia);
        Calendar cal = Calendar.getInstance(); // locale-specific
        cal.setTime(date);
        cal.set(Calendar.HOUR, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTimeInMillis();
    }
    public static List<Date> getDaysBetweenDates(Date startdate, Date enddate) {
        List<Date> dates = new ArrayList<Date>();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(startdate);

        while (calendar.getTime().before(enddate)) {
            Date result = calendar.getTime();
            dates.add(result);
            calendar.add(Calendar.DATE, 1);
        }
        return dates;
    }
    public static String f_diaSemana(int dia) {
        if (dia > 6) dia = 0;
        String[] vobj_days = {"dom.", "lun.", "mar.", "mié.", "jue.", "Vie.", "Sáb."};
        return vobj_days[dia];
    }

    public static String f_intervaloSemana(Double doubles) {

        String fecha = doubles + "";

        String part1 = fecha.substring(0, fecha.indexOf("."));
        String part2 = fecha.substring(fecha.indexOf(".") + 1);
        int p1 = Integer.valueOf(part1);
        int p2 = Integer.valueOf(part2);

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, p2);
        calendar.set(Calendar.WEEK_OF_YEAR, p1);

        int dia =0;
        int diaF=0;
        while (calendar.get(Calendar.DAY_OF_WEEK) > calendar.getFirstDayOfWeek()) {
            calendar.add(Calendar.DATE, -1);
            dia = calendar.get(Calendar.DAY_OF_MONTH);
            diaF=dia+6;
        }
        String[] vobj_Meses = {"ene.", "feb.", "mar.", "abr.", "may.", "jun.", "jul.", "ago.", "sept.", "oct.", "nov.", "dic."};

        //int start = calendar.get(Calendar.)
        return "semana del " + dia+ " al " + diaF + " de "+vobj_Meses[calendar.get(Calendar.MONTH)];
    }

    /**
     * This method converts dp unit to equivalent pixels, depending on device density.
     *
     * @param dp A value in dp (density independent pixels) unit. Which we need to convert into pixels
     * @param context Context to get resources and device specific display metrics
     * @return A float value to represent px equivalent to dp depending on device density
     */
    public static float convertDpToPixel(float dp, Context context){
        return dp * ((float) context.getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT);
    }

    /**
     * This method converts device specific pixels to density independent pixels.
     *
     * @param px A value in px (pixels) unit. Which we need to convert into db
     * @param context Context to get resources and device specific display metrics
     * @return A float value to represent dp equivalent to px value
     */
    public static float convertPixelsToDp(float px, Context context){
        return px / ((float) context.getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT);
    }
}
