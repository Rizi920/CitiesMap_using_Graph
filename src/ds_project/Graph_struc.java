/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ds_project;

import java.io.IOException;
import java.util.LinkedList;
import java.io.*;
import au.com.bytecode.opencsv.CSVWriter;
import au.com.bytecode.opencsv.CSVReader;
import java.util.*;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 *
 * @author Rizwan Ahmed
 */
public class Graph_struc {
    CSVWriter city_info;
 CSVWriter road_info;
 CSVReader city_data;
 CSVReader road_data;
    public LinkedList<Vertex> cities;
    
    Graph_struc(){
         city_info=null;
                road_info=null;
                city_data=null;
                road_data=null;
        cities= new LinkedList();
    }

    
    
    
 public void add_city(String name,String longitude,String latitude){
       Vertex a = new Vertex();
     String[] city_info={name,longitude,latitude};
     
       a.City_name=name;
       a.latitude=latitude;
       a.longitude=longitude;
       cities.addLast(a);
       
      
       try{
           write_city_info(city_info);
    String multiLineMsg[] = { "city data inserted successfully :)" };
    JOptionPane pane = new JOptionPane();
    pane.setMessage(multiLineMsg);
    JDialog d = pane.createDialog(null, "Done!");
    d.setVisible(true);
       }
       catch(Exception x){
           System.out.println(x.getMessage());
     String multiLineMsg[] = { "thers been problem in inesrting data!!!!!:)" };
    JOptionPane pane = new JOptionPane();
    pane.setMessage(multiLineMsg);
    JDialog d = pane.createDialog(null, "sorry!");
    d.setVisible(true);
       }
    
    }
      
    
    
    
    public void edit_city(String old_name, String new_name){
        for (Vertex citie : cities) {
            //for(int i=0;i<cities.size();i++)
            if (citie.City_name.equals(old_name)) {
                citie.City_name = new_name;
                System.out.println(old_name+"  changed to "+new_name);
            } else {
                System.out.println("cant change coz no such city  found");
                break;
            }
        }
    }
       
      
      
      
      public void delete_city(String name){
        int i;
        for(i=0;i<cities.size();i++){
            if(cities.get(i).City_name.equals(name)){
                cities.remove(i);
                System.out.println(name+" deleted");
                for(int j=0;j<cities.get(i).edges.size();j++){
                    if(cities.get(i).edges.get(j).start_vertex.City_name.equals(name)){
                        cities.get(i).edges.remove(j);
                    }else if(cities.get(i).edges.get(j).end_vertex.City_name.equals(name)){
                        cities.get(i).edges.remove(j);
                    }
                }
            }
        }
        if(i<0){
                System.out.println("no such city found to delete");
              
            }
    }
      
      
      public void search_city(String name) throws IOException{
       
          int list_size=cities.size()-1;
          int flag=0;
          while(list_size>=0){
              if(cities.get(list_size).City_name.equals(name)){
     String multiLineMsg[] = { cities.get(list_size).City_name, "latitude of city is",cities.get(list_size).latitude,"longitude of city is",cities.get(list_size).longitude };
    JOptionPane pane = new JOptionPane();
    pane.setMessage(multiLineMsg);
    JDialog d = pane.createDialog(null, "city found!");
    d.setVisible(true);
   
     
              flag=69;         
    break;          
    }
          
              list_size--;
      }
          if(flag!=69){
    String multiLineMsg[] = { "No such city found :)" };
    JOptionPane pane = new JOptionPane();
    pane.setMessage(multiLineMsg);
    JDialog d = pane.createDialog(null, "sorry!");
    d.setVisible(true);
   
          }
       
      }
      
      
      
      
      
      public Vertex find_city(String name){
        int list_size=cities.size()-1;
       
        while(!cities.get(list_size).City_name.equals(name)&&list_size>0){
          
            list_size--;
        }
           if(list_size<0){
        return null;
    }else{
            return cities.get(list_size);
          }
          
        
    }
    
     
     
     public void add_road(String Name,String Start_city,String End_city,String weight){
        Vertex a=find_city(Start_city);
        Vertex b=find_city(End_city);
        String[] road_info={Name,weight,Start_city,End_city};
        if(a!=null&&b!=null){
            Edge new_road=new Edge();
            new_road.road_name=Name;
            new_road.weight=weight;
            new_road.start_vertex=a;
            new_road.end_vertex=b;
            a.edges.addLast(new_road);
    
            
            
            try{
          write_road_info(road_info);
          String multiLineMsg[] = { "Road inserted seccessfully :)" };
    JOptionPane pane = new JOptionPane();
    pane.setMessage(multiLineMsg);
    JDialog d = pane.createDialog(null, "Done!");
    d.setVisible(true);       
      }
        catch(Exception x){
           System.out.println(x.getMessage());
           String multiLineMsg[] = { "thers been problem in inserting road data!!!!!!!! :)" };
    JOptionPane pane = new JOptionPane();
    pane.setMessage(multiLineMsg);
    JDialog d = pane.createDialog(null, "sorry!");
    d.setVisible(true);
       }
        
        
        
       
        }else{
           System.out.println("cant build road because no such cities found");
        }
    }
     
    
    
    public void  edit_road(String old_name,String new_name){
         for(int i=0;i<cities.size();i++){
             for(int k=0;k<cities.get(i).edges.size();k++){
           if(cities.get(i).edges.get(k).road_name.equals(old_name)){
               cities.get(i).edges.get(k).road_name=new_name;
               System.out.println(old_name+" changed to  "+new_name);
           }else{
               System.out.println("cant update coz no such road found");
              
           }
         }break;
    }
    }
   public void find_nearest_path(String start_city, String end_city){
       int flag=0; 
      int flag0=0;
       for (Vertex citie : cities) {
            if (citie.City_name.equals(start_city)) {
                for (int r = 0; r <citie.edges.size(); r++) {
                    if (citie.edges.get(r).end_vertex.City_name.equals(end_city))
                    { 
                        if (flag0!=69){
                            
                            flag0=69;
                        }
    String multiLineMsg[] = { "Road name", citie.edges.get(r).road_name,"Road Length (km)",citie.edges.get(r).weight };
    JOptionPane pane = new JOptionPane();
    pane.setMessage(multiLineMsg);
    JDialog d = pane.createDialog(null, "Road found!");
    d.setVisible(true);
                       
                        flag=69;
                        
                    }  
                    
               }break;
         }
       }
       if(flag!=69){
                String multiLineMsg[] = { "No suitable road found for these cities " };
    JOptionPane pane = new JOptionPane();
    pane.setMessage(multiLineMsg);
    JDialog d = pane.createDialog(null, "sorry!");
    d.setVisible(true);
       }
   }
   
   
   
   
   
   
    public void delete_road(String start_city,String end_city){
        
        for (Vertex citie : cities) {
            if (citie.City_name.equals(start_city)) {
                for (int r = 0; r <citie.edges.size(); r++) {
                    if (citie.edges.get(r).end_vertex.City_name.equals(end_city)) {
                        citie.edges.remove(r);
                        System.out.println("road  deletd");
                    }else{
                        System.out.println("no such roaad found to delete");
                    }break;
                }
            }else{
                System.out.println("no such road found to bdelete");
            }break;
        }
    }   
  

    
    
    
    public void search_road(String road_name) throws IOException{
    
     int  t;
     int flag=0;
     for (int n=0;n<cities.size();n++){ 
            Vertex city=cities.get(n);
             for(t=0;t<city.edges.size();t++){
                 if(city.edges.get(t).road_name.equals(road_name)){
    String multiLineMsg[] = {"road exist between city",city.edges.get(t).start_vertex.City_name," and city" ,city.edges.get(t).end_vertex.City_name };
    JOptionPane pane = new JOptionPane();
    pane.setMessage(multiLineMsg);
    JDialog d = pane.createDialog(null, "Road found!");
    d.setVisible(true);
               
                  flag=69;
                  
                 }
                 
             }break;
                     
              
            
     }if(flag!=69){
         String multiLineMsg[] = {"No such road found" };
    JOptionPane pane = new JOptionPane();
    pane.setMessage(multiLineMsg);
    JDialog d = pane.createDialog(null,"sorry!");
    d.setVisible(true);
  
 }   
 }
 
 
    
    
    
    public void display(){
        int h;
        for (int n=0;n<cities.size();n++){
            Vertex city=cities.get(n);
            System.out.println("start city   "+city.City_name+"("+city.longitude+","+city.latitude+")");
            for(h=0;h<city.edges.size();h++){
                System.out.println("connected by road   "+city.edges.get(h).road_name+"("+city.edges.get(h).weight+"km"+")");
                System.out.println("end_city   "+city.edges.get(h).end_vertex.City_name);
            }if(h<=0){
                System.out.println("no road for this city");
            }
        }
    }
 
 
 
 
   public void filll_city_graph(String nam,String longitude,String latitude) throws IOException{
     
       Vertex v = new Vertex();
       v.City_name=nam;
       v.latitude=latitude;
       v.longitude=longitude;
       cities.addLast(v);
       
           
    }
   
   
   
   
  
       public void filll_road_graph(String Name,String weight,String Start_city,String End_city){
        Vertex a=find_city(Start_city);
        Vertex b=find_city(End_city);
        if(a!=null&&b!=null){
            Edge new_road=new Edge();
            new_road.road_name=Name;
            new_road.weight=weight;
            new_road.start_vertex=a;
            new_road.end_vertex=b;
            a.edges.addLast(new_road);
    
            
            
    
        
        
       
        }else{
           System.out.println("cant build road because no such cities found");
        }
    }
   
   
   
   
  
     
    
   

//.......................................................................................................................................


 /* in this code we are using a third party library (au.com.bytecode.opencsv.CSVWriter) we import this from library after adding this library by ourself*/       
          public  void write_city_info(String[] info) throws Exception{
          String csv = "city_data.csv";
      CSVWriter writer = new CSVWriter(new FileWriter(csv,true));
        
      //Create record
     // String [] record = "city-name,Longitude,Latitude".split(",");
      //Write the record to file
      //writer.writeNext(record);
      writer.writeNext(info);
        
      //close the writer
      writer.close();
          }
          
          
         
          
          
          public void write_road_info(String[] info) throws Exception{
              String str="road_data.csv";
               CSVWriter writer = new CSVWriter(new FileWriter(str,true));
               String[] record ="road_name,weight,Strat_city,End_city".split(",");
               ///writer.writeAll(record, true);
               writer.writeNext(info);
               writer.close();
          }
          
          
          
          
          
 
          public void read_city_data() throws Exception{
             CSVReader reader = new CSVReader(new FileReader("city_data.csv"), ',', '"', 0);
             
      //Read all rows at once
      String[] firstRow = reader.readNext();
      while(firstRow!=null){
      if(firstRow[0].equals("kamalia")){    
       
      System.out.println(firstRow[0]);
// break;
      }    else{
         System.out.println("not kml");
      }
//Read CSV line by line and use the string array as you want
     //for(String[] row : firstRow){
       // System.out.println(Arrays.toString(row)); 
         // }
       firstRow=reader.readNext();
       
      }
}
          
          
          
          public void read_road_data() throws Exception{
                 CSVReader reader = new CSVReader(new FileReader("road_data.csv"), ',', '"', 0);
                   //Read all rows at once
      List<String[]> firstRow = reader.readAll();
       
      //Read CSV line by line and use the string array as you want
     for(String[] row : firstRow){
        System.out.println(Arrays.toString(row)); 
          }
}
          
          
          
          
          public void fill_city_graph() throws FileNotFoundException, IOException{
              
              CSVReader reader = new CSVReader(new FileReader("city_data.csv"), ',', '"', 0);
             
      //Read all rows at once
      String[] firstRow = reader.readNext();
      while(firstRow!=null){
          
            filll_city_graph(firstRow[0], firstRow[1], firstRow[2]);
           
            firstRow=reader.readNext();
            
            
      }
      
     
   
          }
          
          
          
          
          public void fill_road_graph() throws IOException{
        
              CSVReader reader = new CSVReader(new FileReader("city_data.csv"), ',', '"', 0);
             
      //Read all rows at once
      String[] firstRow = reader.readNext();
      while(firstRow!=null){
          
            filll_city_graph(firstRow[0], firstRow[1], firstRow[2]);
            firstRow=reader.readNext();
            
            
      }
           
        CSVReader readerrr = new CSVReader(new FileReader("road_data.csv"), ',', '"', 0);
           
      //Read all rows at once
      String[] firstRowww = readerrr.readNext();
      while(firstRowww!=null){
          
            filll_road_graph(firstRowww[0], firstRowww[1], firstRowww[2],firstRowww[3]);
       
            firstRowww=readerrr.readNext();
            
            
      }
    
          }
}