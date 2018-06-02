/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ds_project;

import java.util.LinkedList;

/**
 *
 * @author Rizwan Ahmed
 */
public class Vertex {
    public String City_name;
    public String longitude;
    public String latitude;
    public LinkedList<Edge> edges;
     Vertex(){
      City_name=null;
      edges=new LinkedList();
    }
    
}
