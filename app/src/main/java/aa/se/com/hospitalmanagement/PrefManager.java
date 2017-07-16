package aa.se.com.hospitalmanagement;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;


public class PrefManager {

    Context context;
    SharedPreferences sharedPreferences;
    Editor editor;


    public PrefManager(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences("pref_manager", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.apply();
    }

    public void setReservationTime(String value, int i) {
        editor.putString("reservationTime" + i, value);
        editor.commit();
    }

    public String getReservationTime(int i) {
        return sharedPreferences.getString("reservationTime" + i, "");
    }

    public void setReservationNumber(int value) {
        editor.putInt("reservationNum", value);
        editor.commit();
        editor.apply();
    }

    public int getReservationNumber() {
        return sharedPreferences.getInt("reservationNum", 0);
    }

    public void setReservationDay(String value, int i) {
        editor.putString("reservationDay" + i, value);
        editor.commit();
    }

    public String getReservationDay(int i) {
        return sharedPreferences.getString("reservationDay" + i, "");
    }

    public void setReservationDayOfMonth(String value, int i) {
        editor.putString("reservationDayOfMonth" + i, value);
        editor.commit();
    }

    public String getReservationDayOfMonth(int i) {
        return sharedPreferences.getString("reservationDayOfMonth" + i, "");
    }

    public void setReservationMonth(String value, int i) {
        editor.putString("reservationMonth" + i, value);
        editor.commit();
    }

    public String getReservationMonth(int i) {
        return sharedPreferences.getString("reservationMonth" + i, "");
    }

    public void setDoctorName(String value, int i) {
        editor.putString("doctorName" + i, value);
        editor.commit();
    }

    public String getDoctorName(int i) {
        return sharedPreferences.getString("doctorName" + i, "");
    }

}
