import java.io.*;
import java.util.*;

class solution {
    public static class Edge{
        int nbr;
        int wt;
        Edge(int nbr, int wt){
            this.nbr=nbr;
            this.wt=wt;
        }
    }
  public static void main(String[] args) {
    Scanner sc=new Scanner(System.in);
    int n=sc.nextInt();
    int e=sc.nextInt();
    ArrayList<Edge> graph[]=new ArrayList[n+1];
    for(int i=0;i<n+1;i++){
        graph[i]=new ArrayList<>();
    }
    int wells[]=new int[n];
    for(int i=0;i<n;i++){
        wells[i]=sc.nextInt();
        graph[0].add(new Edge(i+1, wells[i]));
        graph[i+1].add(new Edge(0,wells[i]));
    }
    int cost[][]=new int[e][3];
    
    for(int i=0;i<e;i++){
       cost[i][0]=sc.nextInt(); 
       cost[i][1]=sc.nextInt();
       cost[i][2]=sc.nextInt();
       graph[cost[i][0]].add(new Edge(cost[i][1], cost[i][2]));
       graph[cost[i][1]].add(new Edge(cost[i][0], cost[i][2]));
    }
    // for(int i=0;i<n+1;i++){
    //     System.out.print(i+"-> ");
    //     for(int j=0;j<graph[i].size();j++){
    //         System.out.print(graph[i].get(j).nbr+" @ "+ graph[i].get(j).wt+", ");
    //     }
    //     System.out.println();
    // }
    boolean vis[]=new boolean[n+1];
    int ans=primsMst(graph, vis, 0);
    System.out.println(ans);
  }
  public static class Pair implements Comparable<Pair>{
      int src;
      int wt;
      Pair(int src, int wt){
          this.src=src;
          this.wt=wt;
      }
      public int compareTo(Pair o){
          return this.wt-o.wt;
      }
  }
  public static int primsMst(ArrayList<Edge> graph[], boolean vis[], int src){
      int ans=0;
      PriorityQueue<Pair> pq=new PriorityQueue<>();
      pq.add(new Pair(src, 0));
      while(!pq.isEmpty()){
          Pair rem=pq.remove();
          if(vis[rem.src]){
              continue;
          }
          ans+=rem.wt;
          vis[rem.src]=true;
          for(Edge e:graph[rem.src]){
              if(!vis[e.nbr]){
                  pq.add(new Pair(e.nbr, e.wt));
              }
          }
      }
      return ans;
      
  }
  
  
    
}