package DemoClasses;

import java.util.Iterator;

import java.util.Set;

import edu.unm.cs.cs351.tdrl.f10.p1.Graph;
import edu.unm.cs.cs351.tdrl.f10.p1.GraphStructureException;

public class demoWebGraph<T> implements Graph<T> {

	@Override
	public void addEdge(T arg0, T arg1) throws GraphStructureException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addNode(T arg0) throws NullPointerException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean containsEdge(T arg0, T arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean containsNode(T arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int edgeCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Iterator<T> getBFSIterator(T arg0) throws GraphStructureException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<T> getDFSIterator(T arg0) throws GraphStructureException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getNodeID(T arg0) throws GraphStructureException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int neighborCount(T arg0) throws GraphStructureException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Set<T> neighborSet(T arg0) throws GraphStructureException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<T> nodeSet() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}


}
