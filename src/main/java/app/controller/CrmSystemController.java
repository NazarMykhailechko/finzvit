package app.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.Principal;
import java.util.List;

@Controller
public class CrmSystemController {

    @PersistenceContext
    private EntityManager em;


    @RequestMapping("/mainform")
    public String home(Model model, Principal principal, Authentication authentication, HttpServletRequest request) throws IOException {


            boolean hasUserRole = authentication.getAuthorities().stream()
                    .anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN"));

            if (hasUserRole){
                return "mainform";


            }else{

                return "mainform";

            }

    }


    @RequestMapping("/mainform/edrpou/{edrpou}/cust/{cust}")
    public String getCommissDetails(Model model, @PathVariable(name = "edrpou") String edrpou, @PathVariable(name = "cust") String cust) {

        Query fieldnames = em.createNativeQuery("SELECT distinct [dates] FROM [DATABASEcomision] order by [dates] desc");
        List<Object[]> listOfFields = fieldnames.getResultList();

        Query q = em.createNativeQuery("EXEC [dbo].[crossdata] ?, ?");
        q.setParameter(1,edrpou);
        q.setParameter(2,cust);

        List<Object[]> results = q.getResultList();

/*        Query fieldnames = em.createNativeQuery("SELECT distinct [dates] FROM [DATABASEcomision] order by [dates] desc");
        List<Dates[]> listOfFields = fieldnames.getResultList();

        Query q = em.createNativeQuery("EXEC [dbo].[crossdata] ?, ?");
        q.setParameter(1,edrpou);
        q.setParameter(2,cust);

        List<CommisDetails[]> results = q.getResultList();*/

        System.out.println(listOfFields);
        System.out.println(results);

        model.addAttribute("listOfVotes", results);
        model.addAttribute("listOfFields", listOfFields);

        return "comisdetails";
    }

    @RequestMapping("/managersreports")
    public String data(Model model) {
        return "managersreports";
    }


    @RequestMapping("/risk")
    public String risk(Model model) {
        //model.addAttribute("listOfLoans", loanDao.findAll());
        return "risk";
    }

}
