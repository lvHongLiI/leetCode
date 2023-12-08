import java.io.IOException;
import java.net.InetAddress;
import java.net.URI;
import java.net.URISyntaxException;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 吕宏力
 * @Date: 2021/07/30/15:22
 * @Description:
 */
public class Test2{
    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {

        String[] str= {
            "JBT00001		93工改保留补贴",
            "JBT00002		老粮贴",
            "JBT00003		回民补贴",
            "JBT00004		人民警察警衔津贴",
            "JBT00005		艰苦边远地区津贴",
            "JBT00006		独生子女费",
            "JBT00010		人民警察值勤岗位津贴",
            "JBT00011		纪检、监察办案人员补贴",
            "JBT00012		法院、检察院工改保留津贴",
            "JBT00013		审计人员工作补贴",
            "JBT00025		规范津贴补贴",
            "JBT00028		人民警察法定工作日之外加班补贴",
            "JBT00029		司法助理员岗位津贴",
            "JBT00030		密码人员岗位津贴",
            "JBT00040		信访工作人员岗位津贴",
            "JBT00043		高海拔地区折算工龄补贴",
            "JBT00063		乡镇工作补贴",
            "JBT00065		高海拔乡镇临时岗位补贴",
            "JBT00066		政法委机关工作津贴",
            "JBT00068		纪委监委机构改革保留补贴",
            "JBT00099		年度考核奖",
            "JBT00110		基础绩效奖",
            "JBT00121		应急管理岗位津贴",
            "JBT00112		应急救援补贴",
            "JBT00113		应急值班补贴",
            "JBT000082		岗位职务补贴",
            "JBT000083		工作性津贴",
            "JBT000084		生活性补贴",
            "JBT000085		法院、检察院办案津贴",
            "JBT000086		安全生产监管监察岗位津贴",
            "JBT000087		法检绩效考核奖金",
            "JBT000088		应急加班补贴",







        };
        for (String s : str) {
            String[] split = s.split("\\s+");
            System.out.println("    {\n" +
                    "            \"name\": \""+split[1]+"\",\n" +
                    "            \"fieldName\": \"subsidySalaryMap."+split[0]+"\",\n" +
                    "            \"filedType\": \"Double\"\n" +
                    "        },");
        }
//

    }






}
