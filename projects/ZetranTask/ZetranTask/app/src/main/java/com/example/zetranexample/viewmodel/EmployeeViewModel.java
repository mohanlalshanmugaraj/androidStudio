package com.example.zetranexample.viewmodel;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.zetranexample.database.DBController;
import com.example.zetranexample.database.Employee;
import com.example.zetranexample.database.PunchInOut;

import java.util.List;
import java.util.concurrent.Executors;

public class EmployeeViewModel extends AndroidViewModel {

    private MutableLiveData<Employee> employeeLiveData = new MutableLiveData<>();
    private MutableLiveData<PunchInOut> employeePIOLiveData = new MutableLiveData<>();
    private MutableLiveData<String> statusMsgLiveData = new MutableLiveData<>();
    private MutableLiveData<List<PunchInOut>> employeeListLiveData = new MutableLiveData<>();
    private MutableLiveData<List<Integer>> employeeIdsLiveData = new MutableLiveData<>();
    private MutableLiveData<String> employeePunchInLiveData = new MutableLiveData<>();
    private Context context;

    public EmployeeViewModel(@NonNull Application application) {
        super(application);
        context = application.getBaseContext();
    }

    public void insertEmployee(Employee employee) {
        Executors.newSingleThreadExecutor().execute(() -> {
            DBController.getInstance(context).getEmpDAO().insertEmpData(employee);
            statusMsgLiveData.postValue("Updated Successfully");

        });
    }

    public void getEmployee(Integer id) {
        Executors.newSingleThreadExecutor().execute(() -> {
            Employee employee = DBController.getInstance(context).getEmpDAO().getEmp(id);
            employeeLiveData.postValue(employee);
        });
    }

    public void getPunchInList() {
        Executors.newSingleThreadExecutor().execute(() -> {
            List<PunchInOut> employees = DBController.getInstance(context).getEmpDAO().getAllPunchInOut();
            if (employees != null && !employees.isEmpty()) {
                employeeListLiveData.postValue(employees);
            } else {
                statusMsgLiveData.postValue("No employee records found");
            }
        });
    }

    public void getEmpIds() {
        Executors.newSingleThreadExecutor().execute(() -> {
            List<Integer> ids = DBController.getInstance(context).getEmpDAO().getEmpIds();
            employeeIdsLiveData.postValue(ids);
        });
    }

    public MutableLiveData<Employee> getEmpLiveData() {
        return employeeLiveData;
    }

    public MutableLiveData<String> getStatusMsgLiveData() {
        return statusMsgLiveData;
    }

    public MutableLiveData<List<PunchInOut>> getEmployeeListLiveData() {
        return employeeListLiveData;
    }

    public MutableLiveData<List<Integer>> getEmployeeIdsLiveData() {
        return employeeIdsLiveData;
    }

    public MutableLiveData<String> getEmployeePunchInLiveData() {
        return employeePunchInLiveData;
    }

    public void insertPunchData(PunchInOut punchInOut) {
        Executors.newSingleThreadExecutor().execute(() -> {
            DBController dbController = DBController.getInstance(context);
            int count = dbController.getEmpDAO()
                    .checkDateAlreadyInserted(punchInOut.getDate(), punchInOut.getEmpId());
            if (count <= 0) {
                punchInOut.setName(dbController.getEmpDAO().getEmpName(punchInOut.getEmpId()));
                dbController.getEmpDAO()
                        .insertPunchData(punchInOut);
            }
        });
    }
    public void updatePunchInTime(Integer id, String date, String time) {
        Executors.newSingleThreadExecutor().execute(() -> {
            DBController.getInstance(context).getEmpDAO()
                    .updatePunchInTime(id, date, time);
            employeePunchInLiveData.postValue("Punch in is done");
        });
    }

    public void updatePunchOutTime(Integer id, String date, String time) {
        Executors.newSingleThreadExecutor().execute(() -> {
            DBController.getInstance(context).getEmpDAO()
                    .updatePunchOutTime(id, date, time);
            employeePunchInLiveData.postValue("Punch out is done");
        });
    }

    public void updateTime(Integer id, String time) {
        Executors.newSingleThreadExecutor().execute(() -> {
            DBController.getInstance(context).getEmpDAO()
                    .updateDateForTheDay(id, time);
        });
    }

    public void getEmpDataOnDailyBasis(Integer id, String todayDate) {
        Executors.newSingleThreadExecutor().execute(() -> {
            PunchInOut punchInOut = DBController.getInstance(context).getEmpDAO()
                    .getEmpBasedDate(id, todayDate);
            employeePIOLiveData.postValue(punchInOut);
        });
    }

    public MutableLiveData<PunchInOut> getEmployeePIOLiveData() {
        return employeePIOLiveData;
    }
}
