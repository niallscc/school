package chavez_nialls_lab4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Lab04<T>
{


	// Strongly Connected Components Lab

		/**
		 * Finds all the strongly connected components in a graph. The Graph is made of simple nodes with pointers to
		 * children.
		 * 
		 * 
		 * @param nodes A hashSet containing a list of all the nodes in the graph
		 * 
		 */
		public HashMap<Node<T>, HashSet<Node<T>>> findSCC(HashSet<Node<T>> nodes)
		{
			HashMap<Node<T>, HashSet<Node<T>>> m = new HashMap<Node<T>, HashSet<Node<T>>>();
			
			
			//sNode<T> myNode = new Node<T>(nodes);			
			//TODO Iterate over all elements in the HashSet nodes and see if they are SCC with any other nodes
			// Use m as way to store the SCCs groups. The Key (Node<T>) is the head of the group the value for the key (HashSet<Node<T>>) is the set of all
			//nodes which are Strongly connected to the key (and hence strongly connected to eachother)
			
			Boolean flag=false;
			// Iterate over nodes
			for( Node<T> a: nodes){
				
				for(Node<T> b: m.keySet() ){
					// See if a node is strongly connected to any of the SCC groups in m
					
					if( isSCC(a, b)){
						// if the node is, add it to the SCC group in m
						m.get(b).add(a);
						flag=true;
					
					}
				}
				if(flag.equals(false))
				{	
					// if it is not strongly connected to ANY of the SCC groups in m, make a new SCC and add it to m
						
					m.put(a, new HashSet<Node <T>>());
				}
						
					
			}			
			return m;
			
		}
		
		
		/**
		 * Tests to see if two components of a graph are strongly connected. Two A and B of a 
		 * graph are Strongly Connected Components if and only if there is a path from A to B and a path from B to A. 
		 * 
		 * 
		 * @param root the first component to test
		 * @param dest the second component to test
		 * @return
		 */
		public boolean isSCC(Node<T> root, Node<T> dest)
		{				
			
			boolean rootToDest = false;
			boolean destToRoot = false;
				
			ArrayList<Node <T>> open= new ArrayList<Node<T>>();
			HashSet<Node <T>> closed= new HashSet<Node<T>>();
			
			
			open.add(root);
			
			//TODO Check to see if root is connected to dest
			while(!open.isEmpty()){
				
				Node<T> tempVar= open.remove(0);
				//System.out.println("tempVar(rootToDest): "+tempVar);
				if(tempVar.getChildren().contains(dest)){
					
					rootToDest=true;
					break;
				}
				if(!closed.contains(tempVar)){
					
					closed.add(tempVar);
					open.addAll(tempVar.getChildren());
				}
				
				
			}
			open.clear();
			
			open.add(dest);
			//TODO Check to see if dest is connected to root
			while(!open.isEmpty()){
				
				Node<T> tempVar= open.remove(0);
				//System.out.println("tempVar(destToRoot): "+tempVar);
				if(tempVar.getChildren().contains(root)){
					destToRoot=true;
					break;
				}
				if(!closed.contains(tempVar)){
					
					closed.add(tempVar);
					open.addAll(tempVar.getChildren());
				}
				
			}
			open.clear();
			
			
			
			return rootToDest && destToRoot;
		}
		
		
		/**
		 * Prints out a HashMap<Node<T>, HashSet<Node<T>>, g, as though it were a connection graph. 
		 * Keys are the Nodes in the graph, elements of the HashSet are the children of that given node.
		 * 
		 * 
		 * @param g the HashMap to be printed
		 */
		public void printMap(HashMap<Node<T>, HashSet<Node<T>>> g)
		{
			for (Node<T> node : g.keySet())
			{
				System.out.println(node.toString());
				
				for (Node<T> child : g.get(node))
				{
					System.out.println("\t " + child.toString());
				}
			}
		}
		
	
		/**
		 * Runs a few sample graph to search for SCC's
		 * 
		 * 
		 * 
		 * @param args
		 */
		public static void main(String args[])
		{
			Lab04<String> scc = new Lab04<String>();
			
			HashSet<Node<String>> graph = new HashSet<Node<String>>();
			
			// Create simple 5 node graph with 1 SCC (A <-> C)
			/*
			 * 
			 *               A
			 *            B     C
			 *           D 
			 *          E  
			 * 
			 */
			Node<String> A = new Node<String>("A"); 
			Node<String> B = new Node<String>("B"); 
			Node<String> C = new Node<String>("C"); 
			Node<String> D = new Node<String>("D"); 
			Node<String> E = new Node<String>("E"); 
			
				A.addChild(B);
				A.addChild(C);
				B.addChild(D);
				D.addChild(E);
				
				C.addChild(A);
				
			graph.add(A);
			graph.add(B);
			graph.add(C);
			graph.add(D);
			graph.add(E);
		
			
			scc.printMap(scc.findSCC(graph));
			
			System.out.println("------------------");
			
			graph = new HashSet<Node<String>>();
			
			Node<String> F = new Node<String>("F"); 
			Node<String> G = new Node<String>("G"); 
			Node<String> H = new Node<String>("H"); 
			Node<String> I = new Node<String>("I"); 
			Node<String> J = new Node<String>("J"); 
			
			/*
			 * 
			 * 		F<--->G
			 * 		^
			 * 		|
			 * 		|
			 *      V
			 *      H<--->I
			 *      
			 *      
			 *      
			 *      J
			 * 
			 * 
			 * 
			 */
			
			
			graph.add(F);
			graph.add(G);
			graph.add(H);
			graph.add(I);
			graph.add(J);
			
			F.addChild(G);
			F.addChild(H);
			
			G.addChild(F);
			
			H.addChild(I);
			H.addChild(F);
			
			I.addChild(H);
				
			
			scc.printMap(scc.findSCC(graph));
			
			System.out.println("------------------");
			
			graph = new HashSet<Node<String>>();
			
			/*			s
			 *			^	
			 * 			|
			 * 			v
			 * 			K<---->L----->M
			 *          ^	          ^                        T
			 *          |             |                        ^
			 *          |             |                        |
			 *          v             v                        v
			 *          N<----------->O----------->P<--------->Q<-----------R
			 *          
			 *          
			 *          {S, K, L, N, O, M} {P, Q, T} {R}
			 * 
			 */
			
			Node<String> K = new Node<String>("K"); 
			Node<String> L = new Node<String>("L"); 
			Node<String> M = new Node<String>("M"); 
			Node<String> N = new Node<String>("N"); 
			Node<String> O = new Node<String>("O"); 
			Node<String> P = new Node<String>("P"); 
			Node<String> Q = new Node<String>("Q"); 
			Node<String> R = new Node<String>("R"); 
			Node<String> S = new Node<String>("S"); 
			Node<String> T = new Node<String>("T"); 
			
			graph.add(K);
			graph.add(L);
			graph.add(M);
			graph.add(N);
			graph.add(O);
			graph.add(P);
			graph.add(Q);
			graph.add(R);
			graph.add(S);
			graph.add(T);
			
			K.addChild(L);
			K.addChild(N);
			K.addChild(S);
			
			L.addChild(K);
			L.addChild(M);
			
			M.addChild(O);
		
			N.addChild(K);
			N.addChild(O);
			
			O.addChild(N);
			O.addChild(P);
			
			P.addChild(Q);
			
			Q.addChild(P);
			Q.addChild(T);
			
		
			R.addChild(Q);
			
			S.addChild(K);
			S.addChild(N);
			S.addChild(O);
			
			T.addChild(Q);
			
			scc.printMap(scc.findSCC(graph));
			
		}
		
	}
	

