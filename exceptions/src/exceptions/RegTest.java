/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package exceptions;

import java.util.regex.*;

/**
 *
 * @author less
 */
public class RegTest {
    public void demo(){
        String src  = "<br><hr><a href=''>123</a><div>456</div>";
        Pattern regex = Pattern.compile(">(\\w+)</");
        Matcher match = regex.matcher(src);
        while(match.find()){
            s.p(match.group(1));
        }
    }
}
