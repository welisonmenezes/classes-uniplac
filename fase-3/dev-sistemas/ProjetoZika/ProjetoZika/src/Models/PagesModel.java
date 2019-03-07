/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.util.ArrayList;

/**
 *
 * @author Welison
 */
public class PagesModel {
    public static ArrayList<PageModel> pages = new ArrayList();
    
    public static void addPage(PageModel page) {
        pages.add(page);
    }
    
    public static PageModel getPage(String name) {
        for(PageModel page : pages) {
            if(page.getName().equals(name)) {
                return page;
            }
        }
        return null;
    }
}
