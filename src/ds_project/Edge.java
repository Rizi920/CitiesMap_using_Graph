/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ds_project;

/**
 *
 * @author Rizwan Ahmed
 */
public class Edge {
    public String road_name;
    public String weight;
    Vertex end_vertex;
    Vertex start_vertex;
    Edge(){
        road_name=null;
        weight=null;
        end_vertex=null;
        start_vertex=null;
    }
}
