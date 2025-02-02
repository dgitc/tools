import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegularTest {
    public static void main(String[] args) {
        String src = "22222id=@id@3333ou_code=@ou_code@4444";
        Pattern pattern = Pattern.compile("@[a-z|A-Z|\\d]+_?[a-z|A-Z|\\d]*@");
        Matcher matcher = pattern.matcher(src);
        String target=null;
        Map<String,String> map=new HashMap<>();
        map.put("id", "0000");
        map.put("ou_code", "9999");
/*        System.out.println(matcher.find());
        String group = matcher.group();*/
        int count=0;
        while (matcher.find()) {
            count++;
            String group = matcher.group();
            System.out.println(group);
            System.out.println("Match number "+count);
            System.out.println("start(): "+matcher.start());
            System.out.println("end(): "+matcher.end());
            String[] split = group.split("@");
            src = src.replaceAll(group, map.get(split[1]));
            System.out.println(src);
            //target=matcher.replaceAll("00000");
            //System.out.println(target);
        }
      //  System.out.println(src);
    }
}
