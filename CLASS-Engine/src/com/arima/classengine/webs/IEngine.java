package com.arima.classengine.webs;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Dell
 * Date: 11/7/13
 * Time: 1:32 AM
 * To change this template use File | Settings | File Templates.
 */
@WebService
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT, use = SOAPBinding.Use.LITERAL)
public interface IEngine {
    @WebMethod
    public String getmodel();

    /**
     * @param schoolNo
     * @param grade
     * @param term
     * @param subjects
     * @param marks
     * @return
     */
    @WebMethod
    public ArrayList<Integer> getNearestLocalProfiles(int schoolNo, int grade, int term, List<String> subjects, List<Integer> marks);

    /**
     *
     * @param grade
     * @param term
     * @param subjects
     * @param marks
     * @return
     */
    @WebMethod
    public ArrayList<Integer> getNearestGlobalProfiles(int grade, int term, List<String> subjects, List<Integer> marks);


}
