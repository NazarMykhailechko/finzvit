package app.controller;


import app.dto.Bal;
import app.dto.BalDat;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;

import javax.persistence.*;
import javax.servlet.http.HttpServletRequest;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

    @RestController
    @RequestMapping("/api/clients")
    public static class ClientRestController {

        @PersistenceContext
        private EntityManager em;


        @RequestMapping(value = "divsall/{data}", method = RequestMethod.GET, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
        public ResponseEntity<Iterable<BalDat>> divsAll(@PathVariable String data, Principal principal) throws IOException {

    /*        Bal[] dto = new Gson().fromJson(new InputStreamReader(new FileInputStream("src/main/data.json")), Bal[].class);
            List<Bal> divsAllList = Arrays.asList(dto);
            List<Bal> temp = divsAllList.stream().filter(d -> d.getTIN().equals(data)).collect(Collectors.toList());*/

            JsonElement jsonElement;
            List<BalDat> res = new ArrayList<>();

            for(int x = 1; x < 18; x++){


                //BufferedReader reader = new BufferedReader(new FileReader("src/main/data" + x + ".json", StandardCharsets.UTF_8));
                //Bal[] dto = new Gson().fromJson(new BufferedReader(new FileReader("src/main/data" + x + ".json")), Bal[].class);

                Bal[] dto = new Gson().fromJson(new InputStreamReader(new FileInputStream("src/main/data" + x + ".json"),"UTF-8"), Bal[].class);
                List<Bal> divsAllList = Arrays.asList(dto);
                List<Bal> temp = divsAllList.stream().filter(d -> d.getTIN().equals(data)).collect(Collectors.toList());

                if (!temp.toString().equals("[]")) {
                    jsonElement = new JsonParser().parse(temp.toString().replace("[", "").replace("]", ""));
                    JsonObject jsonObject = jsonElement.getAsJsonObject();

                    res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;     Нематеріальні активи","1000",jsonObject.get("R1000G4").getAsDouble(),jsonObject.get("R1000G3").getAsDouble()));
                    res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;         первісна вартість","1001",jsonObject.get("R1001G4").getAsDouble(),jsonObject.get("R1001G3").getAsDouble()));
                    res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;         накопичена амортизація","1002",jsonObject.get("R1002G4").getAsDouble(),jsonObject.get("R1002G3").getAsDouble()));
                    res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;     Незавершені капітальні інвестиції","1005",jsonObject.get("R1005G4").getAsDouble(),jsonObject.get("R1005G3").getAsDouble()));
                    res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;     Основні засоби","1010",jsonObject.get("R1010G4").getAsDouble(),jsonObject.get("R1010G3").getAsDouble()));
                    res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;         первісна вартість","1011",jsonObject.get("R1011G4").getAsDouble(),jsonObject.get("R1011G3").getAsDouble()));
                    res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;         знос","1012",jsonObject.get("R1012G4").getAsDouble(),jsonObject.get("R1012G3").getAsDouble()));
                    res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;     Інвестиційна нерухомість","1015",jsonObject.get("R1015G4").getAsDouble(),jsonObject.get("R1015G3").getAsDouble()));
                    res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;         первісна вартість інвестиційної нерухомості","1016",jsonObject.get("R1016G4").getAsDouble(),jsonObject.get("R1016G3").getAsDouble()));
                    res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;         знос інвестиційної нерухомості","1017",jsonObject.get("R1017G4").getAsDouble(),jsonObject.get("R1017G3").getAsDouble()));
                    res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;     Довгострокові біологічні активи","1020",jsonObject.get("R1020G4").getAsDouble(),jsonObject.get("R1020G3").getAsDouble()));
                    res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;         Первісна вартість довгострокових біологічних активів","1021",jsonObject.get("R1021G4").getAsDouble(),jsonObject.get("R1021G3").getAsDouble()));
                    res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;         Накопичена амортизація довгострокових біологічних активів","1022",jsonObject.get("R1022G4").getAsDouble(),jsonObject.get("R1022G3").getAsDouble()));
                    res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;     Довгострокові фінансові інвестиції які обліковуються заметодом участі в капіталі інших підприємств","1030",jsonObject.get("R1030G4").getAsDouble(),jsonObject.get("R1030G3").getAsDouble()));
                    res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;     інші фінансові інвестиції","1035",jsonObject.get("R1035G4").getAsDouble(),jsonObject.get("R1035G3").getAsDouble()));
                    res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;     Довгострокова дебіторська заборгованість","1040",jsonObject.get("R1040G4").getAsDouble(),jsonObject.get("R1040G3").getAsDouble()));
                    res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;     Відстрочені податкові активи","1045",jsonObject.get("R1045G4").getAsDouble(),jsonObject.get("R1045G3").getAsDouble()));
                    res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;     Гудвіл","1050",jsonObject.get("R1050G4").getAsDouble(),jsonObject.get("R1050G3").getAsDouble()));
                    res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;     Відстрочені аквізиційні витрати","1060",jsonObject.get("R1060G4").getAsDouble(),jsonObject.get("R1060G3").getAsDouble()));
                    res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;     Залишок коштів у централізованих страхових резервних фондах","1065",jsonObject.get("R1065G4").getAsDouble(),jsonObject.get("R1065G3").getAsDouble()));
                    res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;     Інші необоротні активи","1090",jsonObject.get("R1090G4").getAsDouble(),jsonObject.get("R1090G3").getAsDouble()));
                    res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"Усього за розділом I","1095",jsonObject.get("R1095G4").getAsDouble(),jsonObject.get("R1095G3").getAsDouble()));
                    res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;     Запаси","1100",jsonObject.get("R1100G4").getAsDouble(),jsonObject.get("R1100G3").getAsDouble()));
                    res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;     Виробничі запаси","1101",jsonObject.get("R1101G4").getAsDouble(),jsonObject.get("R1101G3").getAsDouble()));
                    res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;     Незавершене виробництво","1102",jsonObject.get("R1102G4").getAsDouble(),jsonObject.get("R1102G3").getAsDouble()));
                    res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;     Готова продукція","1103",jsonObject.get("R1103G4").getAsDouble(),jsonObject.get("R1103G3").getAsDouble()));
                    res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;     Товари","1104",jsonObject.get("R1104G4").getAsDouble(),jsonObject.get("R1104G3").getAsDouble()));
                    res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;     Поточні біологічні активи","1110",jsonObject.get("R1110G4").getAsDouble(),jsonObject.get("R1110G3").getAsDouble()));
                    res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;     Депозити перестрахування","1115",jsonObject.get("R1115G4").getAsDouble(),jsonObject.get("R1115G3").getAsDouble()));
                    res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;     Векселі одержані","1120",jsonObject.get("R1120G4").getAsDouble(),jsonObject.get("R1120G3").getAsDouble()));
                    res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;     Дебіторська заборгованість за продукцію, товари, роботи, послуги","1125",jsonObject.get("R1125G4").getAsDouble(),jsonObject.get("R1125G3").getAsDouble()));
                    res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;     Дебіторська заборгованість за розрахунками: за виданими авансами","1130",jsonObject.get("R1130G4").getAsDouble(),jsonObject.get("R1130G3").getAsDouble()));
                    res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;     з бюджетом","1135",jsonObject.get("R1135G4").getAsDouble(),jsonObject.get("R1135G3").getAsDouble()));
                    res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;         у тому числі з податку на прибуток","1136",jsonObject.get("R1136G4").getAsDouble(),jsonObject.get("R1136G3").getAsDouble()));
                    res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;     Дебіторська заборгованість за розрахунками з нарахованих доходів","1140",jsonObject.get("R1140G4").getAsDouble(),jsonObject.get("R1140G3").getAsDouble()));
                    res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;     Дебіторська заборгованість за розрахунками із внутрішніх розрахунків","1145",jsonObject.get("R1145G4").getAsDouble(),jsonObject.get("R1145G3").getAsDouble()));
                    res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;     Інша поточна дебіторська заборгованість","1155",jsonObject.get("R1155G4").getAsDouble(),jsonObject.get("R1155G3").getAsDouble()));
                    res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;     Поточні фінансові інвестиції","1160",jsonObject.get("R1160G4").getAsDouble(),jsonObject.get("R1160G3").getAsDouble()));
                    res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;     Гроші та їх еквіваленти","1165",jsonObject.get("R1165G4").getAsDouble(),jsonObject.get("R1165G3").getAsDouble()));
                    res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"Готівка","1166",jsonObject.get("R1166G4").getAsDouble(),jsonObject.get("R1166G3").getAsDouble()));
                    res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"Рахунки в банках","1167",jsonObject.get("R1167G4").getAsDouble(),jsonObject.get("R1167G3").getAsDouble()));
                    res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;     Витрати майбутніх періодів","1170",jsonObject.get("R1170G4").getAsDouble(),jsonObject.get("R1170G3").getAsDouble()));
                    res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;     Частка перестраховика у страхових резервах","1180",jsonObject.get("R1180G4").getAsDouble(),jsonObject.get("R1180G3").getAsDouble()));
                    res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;           у тому числі в: резервах довгострокових зобов'язань","1181",jsonObject.get("R1181G4").getAsDouble(),jsonObject.get("R1181G3").getAsDouble()));
                    res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;           резервах збитків або резервах належних виплат","1182",jsonObject.get("R1182G4").getAsDouble(),jsonObject.get("R1182G3").getAsDouble()));
                    res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;           резервах незароблених премій","1183",jsonObject.get("R1183G4").getAsDouble(),jsonObject.get("R1183G3").getAsDouble()));
                    res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;           інших страхових резервах","1184",jsonObject.get("R1184G4").getAsDouble(),jsonObject.get("R1184G3").getAsDouble()));
                    res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;      Інші оборотні активи","1190",jsonObject.get("R1190G4").getAsDouble(),jsonObject.get("R1190G3").getAsDouble()));
                    res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"Усього за розділом II","1195",jsonObject.get("R1195G4").getAsDouble(),jsonObject.get("R1195G3").getAsDouble()));
                    res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"III. Необоротні активи, утримувані для продажу, та групи вибуття","1200",jsonObject.get("R1200G4").getAsDouble(),jsonObject.get("R1200G3").getAsDouble()));
                    res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"БАЛАНС","1300",jsonObject.get("R1300G4").getAsDouble(),jsonObject.get("R1300G3").getAsDouble()));
                    res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;      Зареєстрований капітал","1400",jsonObject.get("R1400G4").getAsDouble(),jsonObject.get("R1400G3").getAsDouble()));
                    res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;      Внески до незареєстрованого статутного капіталу","1401",jsonObject.get("R1401G4").getAsDouble(),jsonObject.get("R1401G3").getAsDouble()));
                    res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;      Капітал у дооцінках","1405",jsonObject.get("R1405G4").getAsDouble(),jsonObject.get("R1405G3").getAsDouble()));
                    res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;      Додатковий капітал","1410",jsonObject.get("R1410G4").getAsDouble(),jsonObject.get("R1410G3").getAsDouble()));
                    res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;      Емісійний дохід","1411",jsonObject.get("R1411G4").getAsDouble(),jsonObject.get("R1411G3").getAsDouble()));
                    res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;      Накопичені курсові різниці","1412",jsonObject.get("R1412G4").getAsDouble(),jsonObject.get("R1412G3").getAsDouble()));
                    res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;      Резервний капітал","1415",jsonObject.get("R1415G4").getAsDouble(),jsonObject.get("R1415G3").getAsDouble()));
                    res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;      Нерозподілений прибуток (непокритий збиток)","1420",jsonObject.get("R1420G4").getAsDouble(),jsonObject.get("R1420G3").getAsDouble()));
                    res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;      Неоплачений капітал","1425",jsonObject.get("R1425G4").getAsDouble(),jsonObject.get("R1425G3").getAsDouble()));
                    res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;      Вилучений капітал","1430",jsonObject.get("R1430G4").getAsDouble(),jsonObject.get("R1430G3").getAsDouble()));
                    res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;      Інші резерви","1435",jsonObject.get("R1435G4").getAsDouble(),jsonObject.get("R1435G3").getAsDouble()));
                    res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"Усього за розділом I","1495",jsonObject.get("R1495G4").getAsDouble(),jsonObject.get("R1495G3").getAsDouble()));
                    res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;      Відстрочені податкові зобов'язання","1500",jsonObject.get("R1500G4").getAsDouble(),jsonObject.get("R1500G3").getAsDouble()));
                    res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;      Пенсійні зобов'язання","1505",jsonObject.get("R1505G4").getAsDouble(),jsonObject.get("R1505G3").getAsDouble()));
                    res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;      Довгострокові кредити банків","1510",jsonObject.get("R1510G4").getAsDouble(),jsonObject.get("R1510G3").getAsDouble()));
                    res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;      Інші довгострокові зобов'язання","1515",jsonObject.get("R1515G4").getAsDouble(),jsonObject.get("R1515G3").getAsDouble()));
                    res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;      Довгострокові забезпечення","1520",jsonObject.get("R1520G4").getAsDouble(),jsonObject.get("R1520G3").getAsDouble()));
                    res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;      Довгострокові забезпечення витрат персоналу","1521",jsonObject.get("R1521G4").getAsDouble(),jsonObject.get("R1521G3").getAsDouble()));
                    res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;      Цільове фінансування","1525",jsonObject.get("R1525G4").getAsDouble(),jsonObject.get("R1525G3").getAsDouble()));
                    res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;      Благодійна допомога","1526",jsonObject.get("R1526G4").getAsDouble(),jsonObject.get("R1526G3").getAsDouble()));
                    res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;      Страхові резерви","1530",jsonObject.get("R1530G4").getAsDouble(),jsonObject.get("R1530G3").getAsDouble()));
                    res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;           у тому числі:резерв довгострокових зобов'язань","1531",jsonObject.get("R1531G4").getAsDouble(),jsonObject.get("R1531G3").getAsDouble()));
                    res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;           резерв збитків або резерв належних виплат","1532",jsonObject.get("R1532G4").getAsDouble(),jsonObject.get("R1532G3").getAsDouble()));
                    res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;           резерв незароблених премій","1533",jsonObject.get("R1533G4").getAsDouble(),jsonObject.get("R1533G3").getAsDouble()));
                    res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;           інші страхові резерви","1534",jsonObject.get("R1534G4").getAsDouble(),jsonObject.get("R1534G3").getAsDouble()));
                    res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;      Інвестиційні контракти","1535",jsonObject.get("R1535G4").getAsDouble(),jsonObject.get("R1535G3").getAsDouble()));
                    res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;      Призовий фонд","1540",jsonObject.get("R1540G4").getAsDouble(),jsonObject.get("R1540G3").getAsDouble()));
                    res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;      Резерв на виплату джек-поту","1545",jsonObject.get("R1545G4").getAsDouble(),jsonObject.get("R1545G3").getAsDouble()));
                    res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"Усього за розділом II","1595",jsonObject.get("R1595G4").getAsDouble(),jsonObject.get("R1595G3").getAsDouble()));
                    res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;      Короткострокові кредити банків","1600",jsonObject.get("R1600G4").getAsDouble(),jsonObject.get("R1600G3").getAsDouble()));
                    res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;      Векселі видані","1605",jsonObject.get("R1605G4").getAsDouble(),jsonObject.get("R1605G3").getAsDouble()));
                    res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;      Поточна кредиторська заборгованість за: довгостроковими зобов'язаннями","1610",jsonObject.get("R1610G4").getAsDouble(),jsonObject.get("R1610G3").getAsDouble()));
                    res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;            товари, роботи, послуги","1615",jsonObject.get("R1615G4").getAsDouble(),jsonObject.get("R1615G3").getAsDouble()));
                    res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;            розрахунками з бюджетом","1620",jsonObject.get("R1620G4").getAsDouble(),jsonObject.get("R1620G3").getAsDouble()));
                    res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;            у тому числі з податку на прибуток","1621",jsonObject.get("R1621G4").getAsDouble(),jsonObject.get("R1621G3").getAsDouble()));
                    res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;            розрахунками зі страхування","1625",jsonObject.get("R1625G4").getAsDouble(),jsonObject.get("R1625G3").getAsDouble()));
                    res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;            розрахунками з оплати праці","1630",jsonObject.get("R1630G4").getAsDouble(),jsonObject.get("R1630G3").getAsDouble()));
                    res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;      Поточна кредиторська заборгованість за одержаними авансами","1635",jsonObject.get("R1635G4").getAsDouble(),jsonObject.get("R1635G3").getAsDouble()));
                    res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;      Поточна кредиторська заборгованість за розрахунками з учасниками","1640",jsonObject.get("R1640G4").getAsDouble(),jsonObject.get("R1640G3").getAsDouble()));
                    res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;      Поточна кредиторська заборгованість із внутрішніх розрахунків","1645",jsonObject.get("R1645G4").getAsDouble(),jsonObject.get("R1645G3").getAsDouble()));
                    res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;      Поточна кредиторська заборгованість за страховою діяльністю","1650",jsonObject.get("R1650G4").getAsDouble(),jsonObject.get("R1650G3").getAsDouble()));
                    res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;      Поточні забезпечення","1660",jsonObject.get("R1660G4").getAsDouble(),jsonObject.get("R1660G3").getAsDouble()));
                    res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;      Доходи майбутніх періодів","1665",jsonObject.get("R1665G4").getAsDouble(),jsonObject.get("R1665G3").getAsDouble()));
                    res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;      Відстрочені комісійні доходи від перестраховиків","1670",jsonObject.get("R1670G4").getAsDouble(),jsonObject.get("R1670G3").getAsDouble()));
                    res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;      Інші поточні зобов'язання","1690",jsonObject.get("R1690G4").getAsDouble(),jsonObject.get("R1690G3").getAsDouble()));
                    res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"Усього за розділом IІІ","1695",jsonObject.get("R1695G4").getAsDouble(),jsonObject.get("R1695G3").getAsDouble()));
                    res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"ІV. Зобов'язання, пов'язані з необоротними активами, утримуваними для продажу, та групами вибуття","1700",jsonObject.get("R1700G4").getAsDouble(),jsonObject.get("R1700G3").getAsDouble()));
                    res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"V. Чиста вартість активів недержавного пенсійного фонду","1800",jsonObject.get("R1800G4").getAsDouble(),jsonObject.get("R1800G3").getAsDouble()));
                    res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"БАЛАНС","1900",jsonObject.get("R1900G4").getAsDouble(),jsonObject.get("R1900G3").getAsDouble()));
                  break;
                }else{
                    jsonElement = new JsonParser().parse("{}");
                    //JsonObject jsonObject = jsonElement.getAsJsonObject();
                }

            }
            System.out.println(principal.getName());

            try {
                return new ResponseEntity<Iterable<BalDat>>(res, HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<Iterable<BalDat>>(HttpStatus.BAD_REQUEST);
            }
        }

        @RequestMapping(value = "divsallprofit/{data}", method = RequestMethod.GET, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
        public ResponseEntity<Iterable<BalDat>> divsAllProfit(@PathVariable String data) throws IOException {

    /*        Bal[] dto = new Gson().fromJson(new InputStreamReader(new FileInputStream("src/main/data.json")), Bal[].class);
            List<Bal> divsAllList = Arrays.asList(dto);
            List<Bal> temp = divsAllList.stream().filter(d -> d.getTIN().equals(data)).collect(Collectors.toList());*/

            JsonElement jsonElement;
            List<BalDat> res = new ArrayList<>();

            for(int x = 1; x < 18; x++){


                //BufferedReader reader = new BufferedReader(new FileReader("src/main/data" + x + ".json", StandardCharsets.UTF_8));
                //Bal[] dto = new Gson().fromJson(new BufferedReader(new FileReader("src/main/data" + x + ".json")), Bal[].class);

                Bal[] dto = new Gson().fromJson(new InputStreamReader(new FileInputStream("src/main/data" + x + ".json"),"UTF-8"), Bal[].class);
                List<Bal> divsAllList = Arrays.asList(dto);
                List<Bal> temp = divsAllList.stream().filter(d -> d.getTIN().equals(data)).collect(Collectors.toList());

                if (!temp.toString().equals("[]")) {
                    jsonElement = new JsonParser().parse(temp.toString().replace("[", "").replace("]", ""));
                    JsonObject jsonObject = jsonElement.getAsJsonObject();

                    res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"Чистий дохід від реалізації продукції (товарів, робіт, послуг)","2000",jsonObject.get("R2000G3").getAsDouble(),jsonObject.get("R2000G3").getAsDouble()));
                    res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"Чисті зароблені страхові премії","2010",jsonObject.get("R2010G3").getAsDouble(),jsonObject.get("R2010G3").getAsDouble()));
                    res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"Премії підписані, валова сума","2011",jsonObject.get("R2011G3").getAsDouble(),jsonObject.get("R2011G3").getAsDouble()));
                    res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"Премії, передані у перестрахування","2012",jsonObject.get("R2012G3").getAsDouble(),jsonObject.get("R2012G3").getAsDouble()));
                    res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"Зміна резерву незароблених премій, валова сума","2013",jsonObject.get("R2013G3").getAsDouble(),jsonObject.get("R2013G3").getAsDouble()));
                    res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"Зміна частки перестраховиків у резерві незароблених премій","2014",jsonObject.get("R2014G3").getAsDouble(),jsonObject.get("R2014G3").getAsDouble()));
                    res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"Собівартість реалізованої продукції (товарів, робіт, послуг)","2050",jsonObject.get("R2050G3").getAsDouble(),jsonObject.get("R2050G3").getAsDouble()));
                    res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"Чисті понесені збитки за страховими виплатами","2070",jsonObject.get("R2070G3").getAsDouble(),jsonObject.get("R2070G3").getAsDouble()));
                    res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"Валовий: прибуток","2090",jsonObject.get("R2090G3").getAsDouble(),jsonObject.get("R2090G3").getAsDouble()));
                    res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"збиток","2095",jsonObject.get("R2095G3").getAsDouble(),jsonObject.get("R2095G3").getAsDouble()));
                    res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"Дохід (витрати) від зміни у резервах довгострокових зобов’язань","2105",jsonObject.get("R2105G3").getAsDouble(),jsonObject.get("R2105G3").getAsDouble()));
                    res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"Дохід (витрати) від зміни інших страхових резервів","2110",jsonObject.get("R2110G3").getAsDouble(),jsonObject.get("R2110G3").getAsDouble()));
                    res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"Зміна інших страхових резервів, валова сума","2111",jsonObject.get("R2111G3").getAsDouble(),jsonObject.get("R2111G3").getAsDouble()));
                    res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"Зміна частки перестраховиків в інших страхових резервах","2112",jsonObject.get("R2112G3").getAsDouble(),jsonObject.get("R2112G3").getAsDouble()));
                    res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"Інші операційні доходи","2120",jsonObject.get("R2120G3").getAsDouble(),jsonObject.get("R2120G3").getAsDouble()));
                    res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"Дохід від зміни вартості активів, які оцінюються за справедливою вартістю","2121",jsonObject.get("R2121G3").getAsDouble(),jsonObject.get("R2121G3").getAsDouble()));
                    res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"Дохід від первісного визнання біологічних активів і сільськогосподарської продукції","2122",jsonObject.get("R2122G3").getAsDouble(),jsonObject.get("R2122G3").getAsDouble()));
                    res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"Дохід від використання коштів, вивільнених від оподаткування","2123",jsonObject.get("R2123G3").getAsDouble(),jsonObject.get("R2123G3").getAsDouble()));
                    res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"Адміністративні витрати","2130",jsonObject.get("R2130G3").getAsDouble(),jsonObject.get("R2130G3").getAsDouble()));
                    res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"Витрати на збут","2150",jsonObject.get("R2150G3").getAsDouble(),jsonObject.get("R2150G3").getAsDouble()));
                    res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"Інші доходи","2160",jsonObject.get("R2160G3").getAsDouble(),jsonObject.get("R2160G3").getAsDouble()));
                    res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"Інші витрати","2165",jsonObject.get("R2165G3").getAsDouble(),jsonObject.get("R2165G3").getAsDouble()));
                    res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"Інші операційні витрати","2180",jsonObject.get("R2180G3").getAsDouble(),jsonObject.get("R2180G3").getAsDouble()));
                    res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"Витрат від зміни вартості активів, які оцінюються за справедливою вартістю","2181",jsonObject.get("R2181G3").getAsDouble(),jsonObject.get("R2181G3").getAsDouble()));
                    res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"Витрат від первісного визнання біологічних активів і сільськогосподарської продукції","2182",jsonObject.get("R2182G3").getAsDouble(),jsonObject.get("R2182G3").getAsDouble()));
                    res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"Фінансовий результат від операційної діяльності: прибуток","2190",jsonObject.get("R2190G3").getAsDouble(),jsonObject.get("R2190G3").getAsDouble()));
                    res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"збиток","2195",jsonObject.get("R2195G3").getAsDouble(),jsonObject.get("R2195G3").getAsDouble()));
                    res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"Дохід від участі в капіталі","2200",jsonObject.get("R2200G3").getAsDouble(),jsonObject.get("R2200G3").getAsDouble()));
                    res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"Інші фінансові доходи","2220",jsonObject.get("R2220G3").getAsDouble(),jsonObject.get("R2220G3").getAsDouble()));
                    res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"Інші доходи","2240",jsonObject.get("R2240G3").getAsDouble(),jsonObject.get("R2240G3").getAsDouble()));
                    res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"Дохід від благодійної допомоги","2241",jsonObject.get("R2241G3").getAsDouble(),jsonObject.get("R2241G3").getAsDouble()));
                    res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"Фінансові витрати","2250",jsonObject.get("R2250G3").getAsDouble(),jsonObject.get("R2250G3").getAsDouble()));
                    res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"Втрати від участі в капіталі","2255",jsonObject.get("R2255G3").getAsDouble(),jsonObject.get("R2255G3").getAsDouble()));
                    res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"Інші витрати","2270",jsonObject.get("R2270G3").getAsDouble(),jsonObject.get("R2270G3").getAsDouble()));
                    res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"Прибуток (збиток) від впливу інфляції на монетарні статті","2275",jsonObject.get("R2275G3").getAsDouble(),jsonObject.get("R2275G3").getAsDouble()));
                    res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"Разом доходи","2280",jsonObject.get("R2280G3").getAsDouble(),jsonObject.get("R2280G3").getAsDouble()));
                    res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"Разом витрати (2050 + 2180 + 2270)","2285",jsonObject.get("R2285G3").getAsDouble(),jsonObject.get("R2285G3").getAsDouble()));
                    res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"Фінансовий результат до оподаткування: прибуток","2290",jsonObject.get("R2290G3").getAsDouble(),jsonObject.get("R2290G3").getAsDouble()));
                    res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"збиток","2295",jsonObject.get("R2295G3").getAsDouble(),jsonObject.get("R2295G3").getAsDouble()));
                    res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"Витрати (дохід) з податку на прибуток","2300",jsonObject.get("R2300G3").getAsDouble(),jsonObject.get("R2300G3").getAsDouble()));
                    res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"Прибуток (збиток) від припиненої діяльності після оподаткування","2305",jsonObject.get("R2305G3").getAsDouble(),jsonObject.get("R2305G3").getAsDouble()));
                    res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"Витрати (доходи), які зменшують (збільшують) фінансовий результат після оподаткування","2310",jsonObject.get("R2310G3").getAsDouble(),jsonObject.get("R2310G3").getAsDouble()));
                    res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"Чистий фінансовий результат: прибуток","2350",jsonObject.get("R2350G3").getAsDouble(),jsonObject.get("R2350G3").getAsDouble()));
                    res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"збиток","2355",jsonObject.get("R2355G3").getAsDouble(),jsonObject.get("R2355G3").getAsDouble()));
                    res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"Дооцінка (уцінка) необоротних активів","2400",jsonObject.get("R2400G3").getAsDouble(),jsonObject.get("R2400G3").getAsDouble()));
                    res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"Дооцінка (уцінка) фінансових інструментів","2405",jsonObject.get("R2405G3").getAsDouble(),jsonObject.get("R2405G3").getAsDouble()));
                    res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"Накопичені курсові різниці","2410",jsonObject.get("R2410G3").getAsDouble(),jsonObject.get("R2410G3").getAsDouble()));
                    res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"Частка іншого сукупного доходу асоційованих та спільних підприємств","2415",jsonObject.get("R2415G3").getAsDouble(),jsonObject.get("R2415G3").getAsDouble()));
                    res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"Інший сукупний дохід","2445",jsonObject.get("R2445G3").getAsDouble(),jsonObject.get("R2445G3").getAsDouble()));
                    res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"Інший сукупний дохід до оподаткування","2450",jsonObject.get("R2450G3").getAsDouble(),jsonObject.get("R2450G3").getAsDouble()));
                    res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"Податок на прибуток, пов’язаний з іншим сукупним доходом","2455",jsonObject.get("R2455G3").getAsDouble(),jsonObject.get("R2455G3").getAsDouble()));
                    res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"Інший сукупний дохід після оподаткування","2460",jsonObject.get("R2460G3").getAsDouble(),jsonObject.get("R2460G3").getAsDouble()));
                    res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"Сукупний дохід (сума рядків 2350, 2355 та 2460)","2465",jsonObject.get("R2465G3").getAsDouble(),jsonObject.get("R2465G3").getAsDouble()));
                    res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"Матеріальні затрати За звітний період","2500",jsonObject.get("R2500G3").getAsDouble(),jsonObject.get("R2500G3").getAsDouble()));
                    res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"Витрати на оплату праці","2505",jsonObject.get("R2505G3").getAsDouble(),jsonObject.get("R2505G3").getAsDouble()));
                    res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"Відрахування на соціальні заходи","2510",jsonObject.get("R2510G3").getAsDouble(),jsonObject.get("R2510G3").getAsDouble()));
                    res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"Амортизація","2515",jsonObject.get("R2515G3").getAsDouble(),jsonObject.get("R2515G3").getAsDouble()));
                    res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"Інші операційні витрати","2520",jsonObject.get("R2520G3").getAsDouble(),jsonObject.get("R2520G3").getAsDouble()));
                    res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"Разом","2550",jsonObject.get("R2550G3").getAsDouble(),jsonObject.get("R2550G3").getAsDouble()));
                    res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"Середньорічна кількість простих акцій","2600",jsonObject.get("R2600G3").getAsDouble(),jsonObject.get("R2600G3").getAsDouble()));
                    res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"Скоригована середньорічна кількість простих акцій","2605",jsonObject.get("R2605G3").getAsDouble(),jsonObject.get("R2605G3").getAsDouble()));
                    res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"Чистий прибуток (збиток) на одну просту акцію","2610",jsonObject.get("R2610G3").getAsDouble(),jsonObject.get("R2610G3").getAsDouble()));
                    res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"Скоригований чистий прибуток (збиток) на одну просту акцію","2615",jsonObject.get("R2615G3").getAsDouble(),jsonObject.get("R2615G3").getAsDouble()));
                    res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"Дивіденди на одну просту акцію","2650",jsonObject.get("R2650G3").getAsDouble(),jsonObject.get("R2650G3").getAsDouble()));


                    break;
                }else{
                    jsonElement = new JsonParser().parse("{}");
                    //JsonObject jsonObject = jsonElement.getAsJsonObject();
                }

            }

            try {
                return new ResponseEntity<Iterable<BalDat>>(res, HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<Iterable<BalDat>>(HttpStatus.BAD_REQUEST);
            }
        }

    }
}
