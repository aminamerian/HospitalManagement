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


    public void setUserPhoneNumber(String value) {
        editor.putString("userPhoneNumber", value);
        editor.commit();
        editor.apply();
    }

    public String getUserPhoneNumber() {
        return sharedPreferences.getString("userPhoneNumber", "00000000000");
    }

    public void setUserHasBeenAuthenticated(Boolean value) {
        editor.putBoolean("hasBeenAuthenticated", value);
        editor.commit();
        editor.apply();
    }

    public Boolean getUserHasBeenAuthenticated() {
        return sharedPreferences.getBoolean("hasBeenAuthenticated", false);
    }

    public void setUserHasRegistered(Boolean value) {
        editor.putBoolean("hasregistered", value);
        editor.commit();
        editor.apply();
    }

    public Boolean getUserHasRegistered() {
        return sharedPreferences.getBoolean("hasregistered", false);
    }

    public void setUserName(String value) {
        editor.putString("name", value);
        editor.commit();
        editor.apply();
    }

    public String getUserName() {
        return sharedPreferences.getString("name", "");
    }

    public void setUserLastName(String value) {
        editor.putString("lname", value);
        editor.commit();
        editor.apply();
    }

    public String getUserLastName() {
        return sharedPreferences.getString("lname", "");
    }

    public void setUserNationalNumber(String value) {
        editor.putString("nname", value);
        editor.commit();
        editor.apply();
    }

    public String getUserNationalNumber() {
        return sharedPreferences.getString("nname", "");
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
