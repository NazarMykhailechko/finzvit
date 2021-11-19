package app.controller;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import app.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.Principal;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/clients")
public class ClientRestController {

    @PersistenceContext
    private EntityManager em;


    @RequestMapping(value = "divsall/{data}", method = RequestMethod.GET, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<BalDat>> divsAll(@PathVariable String data) throws IOException {

/*        Bal[] dto = new Gson().fromJson(new InputStreamReader(new FileInputStream("src/main/data.json")), Bal[].class);
        List<Bal> divsAllList = Arrays.asList(dto);
        List<Bal> temp = divsAllList.stream().filter(d -> d.getTIN().equals(data)).collect(Collectors.toList());*/

        JsonElement jsonElement;
        List<BalDat> res = new ArrayList<>();

        for(int x = 1; x < 6; x++){

            Bal[] dto = new Gson().fromJson(new InputStreamReader(new FileInputStream("src/main/data" + x + ".json")), Bal[].class);
            List<Bal> divsAllList = Arrays.asList(dto);
            List<Bal> temp = divsAllList.stream().filter(d -> d.getTIN().equals(data)).collect(Collectors.toList());

            if (!temp.toString().equals("[]")) {
                jsonElement = new JsonParser().parse(temp.toString().replace("[", "").replace("]", ""));
                JsonObject jsonObject = jsonElement.getAsJsonObject();
                res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;     Нематеріальні активи","1000",jsonObject.get("R1000G3").getAsDouble(),jsonObject.get("R1000G4").getAsDouble()));
                res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;         первісна вартість","1001",jsonObject.get("R1001G3").getAsDouble(),jsonObject.get("R1001G4").getAsDouble()));
                res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;         накопичена амортизація","1002",jsonObject.get("R1002G3").getAsDouble(),jsonObject.get("R1002G4").getAsDouble()));
                res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;     Незавершені капітальні інвестиції","1005",jsonObject.get("R1005G3").getAsDouble(),jsonObject.get("R1005G4").getAsDouble()));
                res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;     Основні засоби","1010",jsonObject.get("R1010G3").getAsDouble(),jsonObject.get("R1010G4").getAsDouble()));
                res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;         первісна вартість","1011",jsonObject.get("R1011G3").getAsDouble(),jsonObject.get("R1011G4").getAsDouble()));
                res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;         знос","1012",jsonObject.get("R1012G3").getAsDouble(),jsonObject.get("R1012G4").getAsDouble()));
                res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;     Інвестиційна нерухомість","1015",jsonObject.get("R1015G3").getAsDouble(),jsonObject.get("R1015G4").getAsDouble()));
                res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;         первісна вартість інвестиційної нерухомості","1016",jsonObject.get("R1016G3").getAsDouble(),jsonObject.get("R1016G4").getAsDouble()));
                res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;         знос інвестиційної нерухомості","1017",jsonObject.get("R1017G3").getAsDouble(),jsonObject.get("R1017G4").getAsDouble()));
                res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;     Довгострокові біологічні активи","1020",jsonObject.get("R1020G3").getAsDouble(),jsonObject.get("R1020G4").getAsDouble()));
                res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;         Первісна вартість довгострокових біологічних активів","1021",jsonObject.get("R1021G3").getAsDouble(),jsonObject.get("R1021G4").getAsDouble()));
                res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;         Накопичена амортизація довгострокових біологічних активів","1022",jsonObject.get("R1022G3").getAsDouble(),jsonObject.get("R1022G4").getAsDouble()));
                res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;     Довгострокові фінансові інвестиції які обліковуються заметодом участі в капіталі інших підприємств","1030",jsonObject.get("R1030G3").getAsDouble(),jsonObject.get("R1030G4").getAsDouble()));
                res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;     інші фінансові інвестиції","1035",jsonObject.get("R1035G3").getAsDouble(),jsonObject.get("R1035G4").getAsDouble()));
                res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;     Довгострокова дебіторська заборгованість","1040",jsonObject.get("R1040G3").getAsDouble(),jsonObject.get("R1040G4").getAsDouble()));
                res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;     Відстрочені податкові активи","1045",jsonObject.get("R1045G3").getAsDouble(),jsonObject.get("R1045G4").getAsDouble()));
                res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;     Гудвіл","1050",jsonObject.get("R1050G3").getAsDouble(),jsonObject.get("R1050G4").getAsDouble()));
                res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;     Відстрочені аквізиційні витрати","1060",jsonObject.get("R1060G3").getAsDouble(),jsonObject.get("R1060G4").getAsDouble()));
                res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;     Залишок коштів у централізованих страхових резервних фондах","1065",jsonObject.get("R1065G3").getAsDouble(),jsonObject.get("R1065G4").getAsDouble()));
                res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;     Інші необоротні активи","1090",jsonObject.get("R1090G3").getAsDouble(),jsonObject.get("R1090G4").getAsDouble()));
                res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"Усього за розділом I","1095",jsonObject.get("R1095G3").getAsDouble(),jsonObject.get("R1095G4").getAsDouble()));
                res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;     Запаси","1100",jsonObject.get("R1100G3").getAsDouble(),jsonObject.get("R1100G4").getAsDouble()));
                res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;     Виробничі запаси","1101",jsonObject.get("R1101G3").getAsDouble(),jsonObject.get("R1101G4").getAsDouble()));
                res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;     Незавершене виробництво","1102",jsonObject.get("R1102G3").getAsDouble(),jsonObject.get("R1102G4").getAsDouble()));
                res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;     Готова продукція","1103",jsonObject.get("R1103G3").getAsDouble(),jsonObject.get("R1103G4").getAsDouble()));
                res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;     Товари","1104",jsonObject.get("R1104G3").getAsDouble(),jsonObject.get("R1104G4").getAsDouble()));
                res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;     Поточні біологічні активи","1110",jsonObject.get("R1110G3").getAsDouble(),jsonObject.get("R1110G4").getAsDouble()));
                res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;     Депозити перестрахування","1115",jsonObject.get("R1115G3").getAsDouble(),jsonObject.get("R1115G4").getAsDouble()));
                res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;     Векселі одержані","1120",jsonObject.get("R1120G3").getAsDouble(),jsonObject.get("R1120G4").getAsDouble()));
                res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;     Дебіторська заборгованість за продукцію, товари, роботи, послуги","1125",jsonObject.get("R1125G3").getAsDouble(),jsonObject.get("R1125G4").getAsDouble()));
                res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;     Дебіторська заборгованість за розрахунками: за виданими авансами","1130",jsonObject.get("R1130G3").getAsDouble(),jsonObject.get("R1130G4").getAsDouble()));
                res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;     з бюджетом","1135",jsonObject.get("R1135G3").getAsDouble(),jsonObject.get("R1135G4").getAsDouble()));
                res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;         у тому числі з податку на прибуток","1136",jsonObject.get("R1136G3").getAsDouble(),jsonObject.get("R1136G4").getAsDouble()));
                res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;     Дебіторська заборгованість за розрахунками з нарахованих доходів","1140",jsonObject.get("R1140G3").getAsDouble(),jsonObject.get("R1140G4").getAsDouble()));
                res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;     Дебіторська заборгованість за розрахунками із внутрішніх розрахунків","1145",jsonObject.get("R1145G3").getAsDouble(),jsonObject.get("R1145G4").getAsDouble()));
                res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;     Інша поточна дебіторська заборгованість","1155",jsonObject.get("R1155G3").getAsDouble(),jsonObject.get("R1155G4").getAsDouble()));
                res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;     Поточні фінансові інвестиції","1160",jsonObject.get("R1160G3").getAsDouble(),jsonObject.get("R1160G4").getAsDouble()));
                res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;     Гроші та їх еквіваленти","1165",jsonObject.get("R1165G3").getAsDouble(),jsonObject.get("R1165G4").getAsDouble()));
                res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"Готівка","1166",jsonObject.get("R1166G3").getAsDouble(),jsonObject.get("R1166G4").getAsDouble()));
                res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"Рахунки в банках","1167",jsonObject.get("R1167G3").getAsDouble(),jsonObject.get("R1167G4").getAsDouble()));
                res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;     Витрати майбутніх періодів","1170",jsonObject.get("R1170G3").getAsDouble(),jsonObject.get("R1170G4").getAsDouble()));
                res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;     Частка перестраховика у страхових резервах","1180",jsonObject.get("R1180G3").getAsDouble(),jsonObject.get("R1180G4").getAsDouble()));
                res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;           у тому числі в: резервах довгострокових зобов'язань","1181",jsonObject.get("R1181G3").getAsDouble(),jsonObject.get("R1181G4").getAsDouble()));
                res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;           резервах збитків або резервах належних виплат","1182",jsonObject.get("R1182G3").getAsDouble(),jsonObject.get("R1182G4").getAsDouble()));
                res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;           резервах незароблених премій","1183",jsonObject.get("R1183G3").getAsDouble(),jsonObject.get("R1183G4").getAsDouble()));
                res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;           інших страхових резервах","1184",jsonObject.get("R1184G3").getAsDouble(),jsonObject.get("R1184G4").getAsDouble()));
                res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;      Інші оборотні активи","1190",jsonObject.get("R1190G3").getAsDouble(),jsonObject.get("R1190G4").getAsDouble()));
                res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"Усього за розділом II","1195",jsonObject.get("R1195G3").getAsDouble(),jsonObject.get("R1195G4").getAsDouble()));
                res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"III. Необоротні активи, утримувані для продажу, та групи вибуття","1200",jsonObject.get("R1200G3").getAsDouble(),jsonObject.get("R1200G4").getAsDouble()));
                res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"БАЛАНС","1300",jsonObject.get("R1300G3").getAsDouble(),jsonObject.get("R1300G4").getAsDouble()));
                res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"&nbsp;      Зареєстрований капітал","1400",jsonObject.get("R1400G3").getAsDouble(),jsonObject.get("R1400G4").getAsDouble()));
                res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"&nbsp;      Внески до незареєстрованого статутного капіталу","1401",jsonObject.get("R1401G3").getAsDouble(),jsonObject.get("R1401G4").getAsDouble()));
                res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"&nbsp;      Капітал у дооцінках","1405",jsonObject.get("R1405G3").getAsDouble(),jsonObject.get("R1405G4").getAsDouble()));
                res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"&nbsp;      Додатковий капітал","1410",jsonObject.get("R1410G3").getAsDouble(),jsonObject.get("R1410G4").getAsDouble()));
                res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"&nbsp;      Емісійний дохід","1411",jsonObject.get("R1411G3").getAsDouble(),jsonObject.get("R1411G4").getAsDouble()));
                res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"&nbsp;      Накопичені курсові різниці","1412",jsonObject.get("R1412G3").getAsDouble(),jsonObject.get("R1412G4").getAsDouble()));
                res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"&nbsp;      Резервний капітал","1415",jsonObject.get("R1415G3").getAsDouble(),jsonObject.get("R1415G4").getAsDouble()));
                res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"&nbsp;      Нерозподілений прибуток (непокритий збиток)","1420",jsonObject.get("R1420G3").getAsDouble(),jsonObject.get("R1420G4").getAsDouble()));
                res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"&nbsp;      Неоплачений капітал","1425",jsonObject.get("R1425G3").getAsDouble(),jsonObject.get("R1425G4").getAsDouble()));
                res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"&nbsp;      Вилучений капітал","1430",jsonObject.get("R1430G3").getAsDouble(),jsonObject.get("R1430G4").getAsDouble()));
                res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"&nbsp;      Інші резерви","1435",jsonObject.get("R1435G3").getAsDouble(),jsonObject.get("R1435G4").getAsDouble()));
                res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"Усього за розділом I","1495",jsonObject.get("R1495G3").getAsDouble(),jsonObject.get("R1495G4").getAsDouble()));
                res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"&nbsp;      Відстрочені податкові зобов'язання","1500",jsonObject.get("R1500G3").getAsDouble(),jsonObject.get("R1500G4").getAsDouble()));
                res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"&nbsp;      Пенсійні зобов'язання","1505",jsonObject.get("R1505G3").getAsDouble(),jsonObject.get("R1505G4").getAsDouble()));
                res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"&nbsp;      Довгострокові кредити банків","1510",jsonObject.get("R1510G3").getAsDouble(),jsonObject.get("R1510G4").getAsDouble()));
                res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"&nbsp;      Інші довгострокові зобов'язання","1515",jsonObject.get("R1515G3").getAsDouble(),jsonObject.get("R1515G4").getAsDouble()));
                res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"&nbsp;      Довгострокові забезпечення","1520",jsonObject.get("R1520G3").getAsDouble(),jsonObject.get("R1520G4").getAsDouble()));
                res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"&nbsp;      Довгострокові забезпечення витрат персоналу","1521",jsonObject.get("R1521G3").getAsDouble(),jsonObject.get("R1521G4").getAsDouble()));
                res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"&nbsp;      Цільове фінансування","1525",jsonObject.get("R1525G3").getAsDouble(),jsonObject.get("R1525G4").getAsDouble()));
                res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"&nbsp;      Благодійна допомога","1526",jsonObject.get("R1526G3").getAsDouble(),jsonObject.get("R1526G4").getAsDouble()));
                res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"&nbsp;      Страхові резерви","1530",jsonObject.get("R1530G3").getAsDouble(),jsonObject.get("R1530G4").getAsDouble()));
                res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"&nbsp;           у тому числі:резерв довгострокових зобов'язань","1531",jsonObject.get("R1531G3").getAsDouble(),jsonObject.get("R1531G4").getAsDouble()));
                res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"&nbsp;           резерв збитків або резерв належних виплат","1532",jsonObject.get("R1532G3").getAsDouble(),jsonObject.get("R1532G4").getAsDouble()));
                res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"&nbsp;           резерв незароблених премій","1533",jsonObject.get("R1533G3").getAsDouble(),jsonObject.get("R1533G4").getAsDouble()));
                res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"&nbsp;           інші страхові резерви","1534",jsonObject.get("R1534G3").getAsDouble(),jsonObject.get("R1534G4").getAsDouble()));
                res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"&nbsp;      Інвестиційні контракти","1535",jsonObject.get("R1535G3").getAsDouble(),jsonObject.get("R1535G4").getAsDouble()));
                res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"&nbsp;      Призовий фонд","1540",jsonObject.get("R1540G3").getAsDouble(),jsonObject.get("R1540G4").getAsDouble()));
                res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"&nbsp;      Резерв на виплату джек-поту","1545",jsonObject.get("R1545G3").getAsDouble(),jsonObject.get("R1545G4").getAsDouble()));
                res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"Усього за розділом II","1595",jsonObject.get("R1595G3").getAsDouble(),jsonObject.get("R1595G4").getAsDouble()));
                res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"&nbsp;      Короткострокові кредити банків","1600",jsonObject.get("R1600G3").getAsDouble(),jsonObject.get("R1600G4").getAsDouble()));
                res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"&nbsp;      Векселі видані","1605",jsonObject.get("R1605G3").getAsDouble(),jsonObject.get("R1605G4").getAsDouble()));
                res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"&nbsp;      Поточна кредиторська заборгованість за: довгостроковими зобов'язаннями","1610",jsonObject.get("R1610G3").getAsDouble(),jsonObject.get("R1610G4").getAsDouble()));
                res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"&nbsp;           товари, роботи, послуги","1615",jsonObject.get("R1615G3").getAsDouble(),jsonObject.get("R1615G4").getAsDouble()));
                res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"&nbsp;           розрахунками з бюджетом","1620",jsonObject.get("R1620G3").getAsDouble(),jsonObject.get("R1620G4").getAsDouble()));
                res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"&nbsp;           у тому числі з податку на прибуток","1621",jsonObject.get("R1621G3").getAsDouble(),jsonObject.get("R1621G4").getAsDouble()));
                res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"&nbsp;           розрахунками зі страхування","1625",jsonObject.get("R1625G3").getAsDouble(),jsonObject.get("R1625G4").getAsDouble()));
                res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"&nbsp;           розрахунками з оплати праці","1630",jsonObject.get("R1630G3").getAsDouble(),jsonObject.get("R1630G4").getAsDouble()));
                res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"&nbsp;      Поточна кредиторська заборгованість за одержаними авансами","1635",jsonObject.get("R1635G3").getAsDouble(),jsonObject.get("R1635G4").getAsDouble()));
                res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"&nbsp;      Поточна кредиторська заборгованість за розрахунками з учасниками","1640",jsonObject.get("R1640G3").getAsDouble(),jsonObject.get("R1640G4").getAsDouble()));
                res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"&nbsp;      Поточна кредиторська заборгованість із внутрішніх розрахунків","1645",jsonObject.get("R1645G3").getAsDouble(),jsonObject.get("R1645G4").getAsDouble()));
                res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"&nbsp;      Поточна кредиторська заборгованість за страховою діяльністю","1650",jsonObject.get("R1650G3").getAsDouble(),jsonObject.get("R1650G4").getAsDouble()));
                res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"&nbsp;      Поточні забезпечення","1660",jsonObject.get("R1660G3").getAsDouble(),jsonObject.get("R1660G4").getAsDouble()));
                res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"&nbsp;      Доходи майбутніх періодів","1665",jsonObject.get("R1665G3").getAsDouble(),jsonObject.get("R1665G4").getAsDouble()));
                res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"&nbsp;      Відстрочені комісійні доходи від перестраховиків","1670",jsonObject.get("R1670G3").getAsDouble(),jsonObject.get("R1670G4").getAsDouble()));
                res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"&nbsp;      Інші поточні зобов'язання","1690",jsonObject.get("R1690G3").getAsDouble(),jsonObject.get("R1690G4").getAsDouble()));
                res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"Усього за розділом IІІ","1695",jsonObject.get("R1695G3").getAsDouble(),jsonObject.get("R1695G4").getAsDouble()));
                res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"ІV. Зобов'язання, пов'язані з необоротними активами, утримуваними для продажу, та групами вибуття","1700",jsonObject.get("R1700G3").getAsDouble(),jsonObject.get("R1700G4").getAsDouble()));
                res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"V. Чиста вартість активів недержавного пенсійного фонду","1800",jsonObject.get("R1800G3").getAsDouble(),jsonObject.get("R1800G4").getAsDouble()));
                res.add(new BalDat(jsonObject.get("TIN").getAsString(),jsonObject.get("FULL_NAME").getAsString(),"БАЛАНС","1900",jsonObject.get("R1900G3").getAsDouble(),jsonObject.get("R1900G4").getAsDouble()));
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