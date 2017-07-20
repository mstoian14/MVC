package ro.teamnet.zth.web;

import ro.teamnet.zth.api.annotations.MyController;
import ro.teamnet.zth.api.annotations.MyRequestMethod;
import ro.teamnet.zth.fmk.AnnotationScanUtils;
import ro.teamnet.zth.fmk.MethodAttributes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Mihaela.Stoian on 7/20/2017.
 */
public class MyDispatcherServlet extends HttpServlet {
    Map<String, MethodAttributes> allowedMethods;

    public void init() {
        //load contr.
        Iterable<Class> iterableClasses = null;
        allowedMethods = new HashMap<>();
        try {
            iterableClasses = AnnotationScanUtils.getClasses("ro.teamnet.zth.appl.controller");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(iterableClasses != null) {
            for(Class cl : iterableClasses) {
                MyController myController = (MyController) cl.getDeclaredAnnotation(MyController.class);
                String urlController = myController.urlPath();
                Method methods[] = cl.getDeclaredMethods();
                for(Method method : methods) {
                    MyRequestMethod myRequestMethod = (MyRequestMethod) method.getDeclaredAnnotation(MyRequestMethod.class);
                    String urlMethod =  "/app/mvc" + urlController +"" + myRequestMethod.urlPath();
                    urlMethod += myRequestMethod.methodType();
                    MethodAttributes methodAttributes = new MethodAttributes();
                    methodAttributes.setControllerClass(myController.getClass().getSimpleName());
                    methodAttributes.setMethodName(myRequestMethod.getClass().getSimpleName());
                    methodAttributes.setMethodType(myRequestMethod.getClass().getTypeName());
                    allowedMethods.put(urlMethod, methodAttributes);
                }
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        dispatchReply(request, response, "POST");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        dispatchReply(request, response, "GET");
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        dispatchReply(req, resp, "PUT");
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        dispatchReply(req, resp, "DELETE");
    }

    private void dispatchReply(HttpServletRequest request, HttpServletResponse response, String typeMeth) {
        try {
            Object resulToDisplay = dispatch(request, typeMeth);
        } catch (Exception e) {
            sendExceptionError();
        }
    }

    private void sendExceptionError() {

    }

    private Object dispatch(HttpServletRequest request, String type) {
        //invoke the right method
        String urlReqKey = request.getRequestURL() + type;
        MethodAttributes methodAttributes = allowedMethods.get(urlReqKey);
        if(methodAttributes != null) {
            
        }


        return null;
    }

    private void replay() {
        //return result
    }

}
