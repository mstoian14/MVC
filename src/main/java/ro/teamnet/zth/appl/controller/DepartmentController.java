package ro.teamnet.zth.appl.controller;

import ro.teamnet.zth.api.annotations.MyController;
import ro.teamnet.zth.api.annotations.MyRequestMethod;

/**
 * Created by Mihaela.Stoian on 7/20/2017.
 */

@MyController(urlPath = "/departments")
public class DepartmentController {
    @MyRequestMethod(urlPath = "/all", methodType = "GET")
    public String getAllDepartments() {
        return "allDepartments";
    }
}
