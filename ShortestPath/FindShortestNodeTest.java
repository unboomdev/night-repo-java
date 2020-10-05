package dev.test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dev.run.FindShortestNode;
import dev.run.InputModel;
import dev.run.NodeModel;
import dev.run.NodeTemp;

public class FindShortestNodeTest {

	private FindShortestNode a;
	private List<NodeModel> listNodeDb;

	@Before
	public void setup() {
		a = new FindShortestNode();
	}

	@Test
	public void canReadCsv() {
		listNodeDb = a.readFileCsv("graph.csvv");
		assertEquals(new ArrayList<>(), listNodeDb);
		
		listNodeDb = a.readFileCsv("graph.csv");
		assertNotNull(listNodeDb);
	}

	@Test
	public void findNodeBegin() {
		listNodeDb = a.readFileCsv("graph.csv");
		
		InputModel input = new InputModel("A", "B");
		List<NodeTemp> listSearch = a.searchNodeBegin(input.getStart(), listNodeDb);
		
		List<String> listStr = new ArrayList<>();
		for (NodeTemp nodeTemp : listSearch) {
			listStr.add(nodeTemp.getName());
		}
		
		String[] methodOutput = new String[listStr.size()];
		methodOutput = listStr.toArray(methodOutput);
		
		String[] expectedOutputA = {"B", "D", "E"};
		assertArrayEquals(expectedOutputA, methodOutput);
		
		input = new InputModel("E", "A");
		listSearch = a.searchNodeBegin(input.getStart(), listNodeDb);
		
		listStr = new ArrayList<>();
		for (NodeTemp nodeTemp : listSearch) {
			listStr.add(nodeTemp.getName());
		}
		
		methodOutput = new String[listStr.size()];
		methodOutput = listStr.toArray(methodOutput);
		
		String[] expectedOutputE = {"A", "F"};
		assertArrayEquals(expectedOutputE, methodOutput);
	}
	
	@Test
	public void findNodeActionAB() {
		listNodeDb = a.readFileCsv("graph.csv");
		InputModel input = new InputModel("A", "B");
		List<NodeTemp> listSearch = a.searchNodeBegin(input.getStart(), listNodeDb);
		
		List<List<NodeTemp>> listPossible = a.searchNodeAction(listNodeDb, listSearch, input);
		assertEquals(1, listPossible.size());
		
		List<NodeTemp> listShortest = a.findShortestList(listPossible);
		int cost = a.printValue(input, listShortest);
		assertEquals(5, cost);
	}
	
	@Test
	public void findNodeActionBA() {
		listNodeDb = a.readFileCsv("graph.csv");
		InputModel input = new InputModel("B", "A");
		List<NodeTemp> listSearch = a.searchNodeBegin(input.getStart(), listNodeDb);
		
		List<List<NodeTemp>> listPossible = a.searchNodeAction(listNodeDb, listSearch, input);
		assertEquals(1, listPossible.size());
		
		List<NodeTemp> listShortest = a.findShortestList(listPossible);
		int cost = a.printValue(input, listShortest);
		assertEquals(5, cost);
	}
	
	@Test
	public void findNodeActionCF() {
		listNodeDb = a.readFileCsv("graph.csv");
		InputModel input = new InputModel("C", "F");
		List<NodeTemp> listSearch = a.searchNodeBegin(input.getStart(), listNodeDb);
		
		List<List<NodeTemp>> listPossible = a.searchNodeAction(listNodeDb, listSearch, input);
		assertEquals(4, listPossible.size());
		
		List<NodeTemp> listShortest = a.findShortestList(listPossible);
		int cost = a.printValue(input, listShortest);
		assertEquals(10, cost);
	}
	
	@Test
	public void findNodeActionFG() {
		listNodeDb = a.readFileCsv("graph.csv");
		InputModel input = new InputModel("F", "G");
		List<NodeTemp> listSearch = a.searchNodeBegin(input.getStart(), listNodeDb);
		
		List<List<NodeTemp>> listPossible = a.searchNodeAction(listNodeDb, listSearch, input);
		assertEquals(3, listPossible.size());
		
		List<NodeTemp> listShortest = a.findShortestList(listPossible);
		int cost = a.printValue(input, listShortest);
		assertEquals(8, cost);
	}
	
	@Test
	public void findNodeActionFC() {
		listNodeDb = a.readFileCsv("graph.csv");
		InputModel input = new InputModel("F", "C");
		List<NodeTemp> listSearch = a.searchNodeBegin(input.getStart(), listNodeDb);
		
		List<List<NodeTemp>> listPossible = a.searchNodeAction(listNodeDb, listSearch, input);
		assertEquals(4, listPossible.size());
		
		List<NodeTemp> listShortest = a.findShortestList(listPossible);
		int cost = a.printValue(input, listShortest);
		assertEquals(13, cost);
	}
	
	@Test
	public void findNodeActionHJ() {
		listNodeDb = a.readFileCsv("graph.csv");
		InputModel input = new InputModel("H", "J");
		List<NodeTemp> listSearch = a.searchNodeBegin(input.getStart(), listNodeDb);
		
		List<List<NodeTemp>> listPossible = a.searchNodeAction(listNodeDb, listSearch, input);
		assertEquals(4, listPossible.size());
		
		List<NodeTemp> listShortest = a.findShortestList(listPossible);
		int cost = a.printValue(input, listShortest);
		assertEquals(10, cost);
	}
	
	@After
	public void tearDown() {
		a = null;
	}
}
