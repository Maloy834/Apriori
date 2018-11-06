package apriori;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.security.KeyStore.Entry;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class Apriori {
	int arr[]=new int [6]; 
	ArrayList<Integer>elt=new ArrayList<>();
	int [] element;
	
	ArrayList<Integer>list=new ArrayList<>();
	ArrayList <Integer>c1List=new ArrayList<>();
	ArrayList<String>c2List=new ArrayList<>();
	ArrayList<String>c3List=new ArrayList<>();
	ArrayList<String>c3candidateList=new ArrayList<>();
	TreeMap<Integer, Integer>l1=new TreeMap<>();
	TreeMap<String,Integer>l2=new TreeMap<>();
	TreeMap<String, Integer>l3=new TreeMap<>();
  public void readFile() throws IOException
  {
	  BufferedReader br=null;
	  FileReader fr=null;
	  fr=new FileReader("H:\\workspace\\Apriori\\src\\apriori\\input.txt");
	  br=new BufferedReader(fr);
	  String line;
	  while((line=br.readLine())!=null)
	  {
		  String array[]=line.split(",");
		  for(int i=1;i<array.length;i++)
		  {
			 //System.out.println(array[i]);
			 int t=Integer.parseInt(array[i]);
			 arr[t]++;
			 if(!list.contains(t))
			 {
			     list.add(t);
			 }
		  }
	  }
	  Collections.sort(list);
	  
	  for(Integer lis:list)
	  {
		  l1.put(lis, arr[lis]);
		  if(arr[lis]>=2){
			  
			 elt.add(lis);
			 
		  }
	  }
	  element=new int[elt.size()];
	 for(int i=0;i<elt.size();i++)
	 {
		 element[i]=elt.get(i).intValue();
	 }
  }
  public int countSupportForC2(int t,int m) throws NumberFormatException, IOException
  {
	  int supportCount=0;
	  BufferedReader br=null;
	  FileReader fr=null;
	  fr=new FileReader("H:\\workspace\\Apriori\\src\\apriori\\input.txt");
	  br=new BufferedReader(fr);
	  String line;
	  while((line=br.readLine())!=null)
	  {
		  int cnt=0;
		  String array[]=line.split(",");
		  for(int i=1;i<array.length;i++)
		  {
			 //System.out.println(array[i]);
			 int a=Integer.parseInt(array[i]);
			 if(a==t||a==m)
				 cnt++;
			 
		  }
		  if(cnt==2)
			  supportCount++;
		  
	  }
	return supportCount;
  }
  public int countSupportForC3(int t,int m,int n) throws NumberFormatException, IOException
  {
	  int supportCount=0;
	  BufferedReader br=null;
	  FileReader fr=null;
	  fr=new FileReader("H:\\workspace\\Apriori\\src\\apriori\\input.txt");
	  br=new BufferedReader(fr);
	  String line;
	  while((line=br.readLine())!=null)
	  {
		  int cnt=0;
		  String array[]=line.split(",");
		  for(int i=1;i<array.length;i++)
		  {
			 //System.out.println(array[i]);
			 int a=Integer.parseInt(array[i]);
			 if(a==t||a==m||a==n)
				 cnt++;
			 
		  }
		  if(cnt==3)
			  supportCount++;
		  
	  }
	return supportCount;
  }
  public void GenerateC2List() throws NumberFormatException, IOException
  {
	 //int data[];
	  int data[] = new int[6];
	 makeCombination(c1List,element,data,0,element.length-1,0,2);
	 int [] temp=new int[c1List.size()];
	 for(int i=0;i<temp.length;i++)
	 {
		 temp[i]=c1List.get(i).intValue();
	 }
	 for(int i=0;i<temp.length;i=i+2)
	 {
		 int t=temp[i];
		 int m=temp[i+1];
		 int support=countSupportForC2(t, m);
		 
		   String s=String.valueOf(t)+","+String.valueOf(m);
		   l2.put(s, support);
		   if(support>=2)
			   c2List.add(s);
		
	 }
	  
  }
  public void GenerateC3List() throws NumberFormatException, IOException
  {
	  String[] list = c2List.toArray(new String[0]);
	  //listArr=new String[c2List.size()];
	  for(int i=0;i<list.length;i++)
	  {
		  String[] temp=list[i].split(",");
		  for(int j=i+1;j<list.length;j++)
		  {
			  String [] str=list[j].split(",");
			  if(temp[0].equals(str[0]))
			  {
				  String s=temp[0]+","+temp[1]+","+str[1];
				  c3List.add(s);
			  }
		  }
	  }
	  printFirstc3List();
	 c3candidateList= generateCandidateList(2,c2List,c3List);
	  printCandidateC3List();
	  for(String strList:c3candidateList)
	  {
		  String [] array=strList.split(",");
		  int t=Integer.parseInt(array[0]);
		  int m=Integer.parseInt(array[1]);
		  int n=Integer.parseInt(array[2]);
		  int support=countSupportForC3(t,m,n);
		  String s=array[0]+","+array[1]+","+array[2];
		  l3.put(s, support);
	  }
	  printC3ListAndSupport();
	  printL3();
  }
 public ArrayList<String> generateCandidateList(int r, ArrayList<String>previousList,ArrayList<String>CurrentList)
 {
	 ArrayList<String>cList=new ArrayList<>();
	 for(String list1:CurrentList)
	 {
		 String [] str=list1.split(",");
		 String s = null,m = null;
		 for(int i=0;i<r-1;i++)
		     s=str[i]+",";
		 s+=str[r-1];
		 for(int i=1;i<str.length-1;i++)
			 m=str[i]+",";
		 m+=str[str.length-1];
		 
		 if(previousList.contains(s)&&previousList.contains(m))
		 {
			 //System.out.println(list1);
			 //String st=str[0]+","+str[1]+","+str[2];
			//c3candidateList.add(st);
			// c3candidateList.add(list1);
			 cList.add(list1);
		 }
	 }
	return cList;
 }
  public static void makeCombination(ArrayList<Integer>temp,int[]element,int[]data,int start,int end,int index,int r)
  {
	 
	 
	  if (index == r)
      {
		  for (int j=0; j<r; j++)
			   temp.add(data[j]);
             
        return ;
      }

      
      for (int i=start; i<=end && end-i+1 >= r-index; i++)
      {
          data[index] = element[i];
          makeCombination(temp,element, data, i+1, end, index+1, r);
      }
    
  }
  public void PrintC1()
  {
	  System.out.println("C1 after scanning Dataset:");
	  System.out.println("Itemset"+"  "+"SupportCount");
	  for(Map.Entry<Integer, Integer> entry:l1.entrySet())
	  {
		  System.out.println("["+entry.getKey()+"]"+"         "+entry.getValue());
	  }
	  System.out.println(" ");
  }
  public void printL1()
  {   System.out.println("L1:");
	  System.out.println("Itemset"+"  "+"SupportCount");
	  for(Map.Entry<Integer, Integer> entry:l1.entrySet())
	  {
		  if(entry.getValue()>=2)
		    System.out.println("["+entry.getKey()+"]"+"         "+entry.getValue());
	  }
	  System.out.println(" ");
  }
  public void printC2()
  {
      System.out.println("C2 after Scanning Dataset:");
	  System.out.println("Itemset"+"  "+"SupportCount");
	  for(Map.Entry<String, Integer>entry:l2.entrySet())
	  {
		  System.out.println("["+entry.getKey()+"]"+"         "+entry.getValue());
	  }
	  System.out.println(" ");
  }
  public void printL2()
  {
	  System.out.println("L2:");
	  System.out.println("Itemset"+"  "+"SupportCount");
	  for(Map.Entry<String, Integer>entry:l2.entrySet())
	  {
		  if(entry.getValue()>=2)
		  System.out.println("["+entry.getKey()+"]"+"         "+entry.getValue());
	  }
	  System.out.println(" ");
  }
  public void printFirstc3List()
  {
	  System.out.println("C3 List combination:");
	  for(String lis:c3List)
	  {
		  System.out.println("["+lis+"]");
	  }
	  System.out.println(" ");
  }
  public void printCandidateC3List()
  {
	  System.out.println("C3 Candidates List:");
	  for(String lis:c3candidateList)
	  {
		  System.out.println("["+lis+"]");
	  }
	  System.out.println(" ");
  }
  public void printC3ListAndSupport()
  {
	  System.out.println("C3 after Scanning Dataset:");
	  System.out.println("Itemset"+"  "+"SupportCount");
	  for(Map.Entry<String, Integer>entry:l3.entrySet())
	  {
		  System.out.println("["+entry.getKey()+"]"+"         "+entry.getValue());
	  }
	  System.out.println(" ");
  }
  public void printL3()
  {
	  System.out.println("L3:");
	  System.out.println("Itemset"+"  "+"SupportCount");
	  for(Map.Entry<String, Integer>entry:l3.entrySet())
	  {
		  if(entry.getValue()>=2)
		     System.out.println("["+entry.getKey()+"]"+"         "+entry.getValue());
	  }
	  System.out.println(" ");
  }
  public void calculateConfidence()
  {
	  System.out.println("Confidence Calculation:");
	  float confidence;
	  confidence=((float)l2.get("1,2")/(float)l1.get(1))*100;
	  //System.out.println(confidence);
	  //System.out.println(l1.get(1));
	  System.out.println("1->2 ="+confidence+"%");
	  confidence=((float)l2.get("1,3")/(float)l1.get(1))*100;
	  System.out.println("1->3 ="+confidence+"%");
	  confidence=((float)l2.get("1,4")/(float)l1.get(1))*100;
	  System.out.println("1->4 ="+confidence+"%");
	  confidence=((float)l2.get("1,5")/(float)l1.get(1))*100;
	  System.out.println("1->5 ="+confidence+"%");
	  confidence=((float)l2.get("2,3")/(float)l1.get(2))*100;
	  System.out.println("2->3 ="+confidence+"%");
	  confidence=((float)l2.get("2,4")/(float)l1.get(2))*100;
	  System.out.println("2->4 ="+confidence+"%");
	  confidence=((float)l2.get("2,5")/(float)l1.get(2))*100;
	  System.out.println("2->5 ="+confidence+"%");
	  confidence=((float)l2.get("3,4")/(float)l1.get(3))*100;
	  System.out.println("3->4 ="+confidence+"%");
	  confidence=((float)l2.get("3,5")/(float)l1.get(3))*100;
	  System.out.println("3->5 ="+confidence+"%");
	  confidence=((float)l2.get("4,5")/(float)l1.get(4))*100;
	  System.out.println("4->5 ="+confidence+"%");
	  confidence=((float)l3.get("1,2,5")/(float)l2.get("1,2"))*100;
	  System.out.println("1^2->5 ="+confidence+"%");
	  confidence=((float)l3.get("1,2,5")/(float)l2.get("1,5"))*100;
	  System.out.println("1^5->2 ="+confidence+"%");
	  confidence=((float)l3.get("1,2,5")/(float)l2.get("2,5"))*100;
	  System.out.println("2^5->1 ="+confidence+"%");
	  confidence=((float)l3.get("1,2,5")/(float)l1.get(1))*100;
	  System.out.println("1->2^5 ="+confidence+"%");
	  confidence=((float)l3.get("1,2,5")/(float)l1.get(2))*100;
	  System.out.println("2->1^5 ="+confidence+"%");
	  confidence=((float)l3.get("1,2,5")/(float)l1.get(5))*100;
	  System.out.println("5->1^2 ="+confidence+"%");
  }
}
