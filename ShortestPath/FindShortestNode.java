package dev.run;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FindShortestNode {

	private static FindShortestNode findShortestNode = new FindShortestNode();
	
	public static void main(String[] args) {
		
		List<NodeModel> listNodeDb = findShortestNode.readFileCsv("graph.csv");
		if (listNodeDb.isEmpty()) {
			System.out.println("Invalid file graph");
			return;
		}
		
		Scanner scanner = new Scanner(System.in);
		System.out.print("What is start node?: ");
		String start = scanner.nextLine();
		System.out.print("What is goal node?: ");
		String goal = scanner.nextLine();
		scanner.close();
		if (start.isEmpty() || goal.isEmpty()) {
			System.out.println("Input is empty");
			return;
		}
		start = start.toUpperCase();
		goal = goal.toUpperCase();
		
		InputModel input = new InputModel(start, goal);
		
		List<NodeTemp> listSearch = findShortestNode.searchNodeBegin(input.getStart(), listNodeDb);
		if (listSearch.isEmpty()) {
			System.out.println("Node: " + input.getStart() + " cannot go to node: " + input.getGoal());
			return;
		}
		
		List<List<NodeTemp>> listPossible = findShortestNode.searchNodeAction(listNodeDb, listSearch, input);
		List<NodeTemp> listShortest = findShortestNode.findShortestList(listPossible);
		findShortestNode.printValue(input, listShortest);
	}
	
	public List<NodeModel> readFileCsv(String fileName) {
		List<NodeModel> listNodeDb = new ArrayList<>();

		InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
		if (in == null) {
			return new ArrayList<>();
		}

		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String line;
			NodeModel node;
			while ((line = br.readLine()) != null) {
				String[] index = line.split(",");
				node = new NodeModel(index[0].trim(), index[1].trim(), Integer.parseInt(index[2].trim()));
				listNodeDb.add(node);
			}
			br.close();
			return listNodeDb;
		} catch (IOException e) {
			System.out.println("IOException: " + e.getMessage());
			return new ArrayList<>();
		}
	}

	public List<NodeTemp> searchNodeBegin(String search, List<NodeModel> listNodeDb) {
		List<NodeTemp> listWay = new ArrayList<>();
		for (NodeModel node : listNodeDb) {
			if (node.getStart().equals(search)) {
				NodeTemp model = new NodeTemp(node.getGoal(), node.getValue());
				listWay.add(model);
			} else if (node.getGoal().equals(search)) {
				NodeTemp model = new NodeTemp(node.getStart(), node.getValue());
				listWay.add(model);
			}
		}
		
		return listWay;
	}
	
	public List<List<NodeTemp>> searchNodeAction(List<NodeModel> listNodeDb, List<NodeTemp> listSearch, InputModel input) {
		List<List<NodeTemp>> listPossible = new ArrayList<>();
		for (NodeTemp nodeTemp : listSearch) {
			
			List<NodeTemp> listMain = new ArrayList<>();
			NodeTemp init = new NodeTemp(input.getStart(), 0);
			listMain.add(init);

			if (nodeTemp.getName().equals(input.getGoal())) {
				init = new NodeTemp(nodeTemp.getName(), nodeTemp.getValue());
				listMain.add(init);
				listPossible.add(listMain);
				break;
			}
			searchNode(listNodeDb, nodeTemp, input, listMain, listPossible, new ArrayList<>());
		}
		return listPossible;
	}
	
	private List<List<NodeTemp>> searchNode(List<NodeModel> listNodeDb, NodeTemp nodeNext, InputModel input, 
			List<NodeTemp> listMain, List<List<NodeTemp>> listPossible, List<List<NodeTemp>> listSavePoint) {
		
		String start = listMain.get(listMain.size() - 1).getName();
		String goal = nodeNext.getName();
		List<NodeTemp> listSearching = searchDeep(listNodeDb, goal, start);
		
		System.out.println("start: " + start);
		System.out.println("goal: " + goal + "\n");
		
		if (goal.equals(input.getStart()) || goal.equals(input.getGoal()) || isLostWay(goal, listMain)) {
			
			if (goal.equals(input.getGoal())) {
				System.out.print("Goal!!: ");
				saveNode(listMain, nodeNext);
				
				boolean first = true;
				StringBuilder sb = new StringBuilder();
				int total = 0;
				for (NodeTemp result : listMain) {
					
					if (first) {
						sb.append(result.getName() + "(" + result.getValue() + ")");
						first = false;
					} else {
						sb.append(" -> " + result.getName() + "(" + result.getValue() + ")");
					}
					
					total = total + result.getValue();
				}
				System.out.println(sb.toString() + ", cost: " + total + "\n");
				
				listPossible.add(listMain);
			} else if (goal.equals(input.getStart())) {
				System.out.println("Re-Start!!\n");
			} else if (isLostWay(goal, listMain)) {
				System.out.println("Lost way!!\n");
			}
			
			return loadAdnotherSave(listNodeDb, input, listPossible, listSavePoint);
		}
		
		saveNode(listMain, nodeNext);
		if (listSearching.isEmpty()) {
			System.out.println("Dead end!!\n");
			return loadAdnotherSave(listNodeDb, input, listPossible, listSavePoint);
		} else {
			if (listSearching.size() > 1) {
				List<NodeTemp> listAnotherSave = new ArrayList<>();
				listAnotherSave.addAll(listSearching);
				listAnotherSave.remove(0);
				for (NodeTemp nodeTemp : listAnotherSave) {
					savePoint(listSavePoint, listMain, nodeTemp);
				}
			}
			
			nodeNext = listSearching.get(0);
			return searchNode(listNodeDb, nodeNext, input, listMain, listPossible, listSavePoint);
		}
	}
	
	private List<List<NodeTemp>> loadAdnotherSave(List<NodeModel> listNodeDb, InputModel input,
			List<List<NodeTemp>> listPossible, List<List<NodeTemp>> listSavePoint) {
		
		if (listSavePoint.isEmpty()) {
			return listPossible;
		} else {
			List<NodeTemp> listAnotherSave = new ArrayList<>();
			listAnotherSave.addAll(listSavePoint.get(0));
			listSavePoint.remove(0);
			NodeTemp nodeNext = listAnotherSave.get(listAnotherSave.size() - 1);

			List<NodeTemp> listMain = new ArrayList<>();
			listMain.addAll(listAnotherSave);
			listMain.remove(listMain.size() - 1);
			return searchNode(listNodeDb, nodeNext, input, listMain, listPossible, listSavePoint);
		}
	}
	
	private boolean isLostWay(String goal, List<NodeTemp> listMain) {
		for (NodeTemp nodeTemp : listMain) {
			if (nodeTemp.getName().equals(goal)) {
				return true;
			}
		}
		
		return false;
	}
	
	private void saveNode(List<NodeTemp> listMain, NodeTemp nodeToSave) {
		for (NodeTemp nodeHist : listMain) {
			if (nodeHist.getName().equals(nodeToSave.getName())) {
				return;
			}
		}
		listMain.add(nodeToSave);
	}
	
	private static List<NodeTemp> searchDeep(List<NodeModel> listNodeDb, String search, String backStep) {
		List<NodeTemp> listNo = new ArrayList<>();
		
		for (NodeModel node : listNodeDb) {
			if (node.getStart().equals(search) && !node.getGoal().equals(backStep)) {
				String searchNext = node.getGoal();
				NodeTemp model = new NodeTemp(searchNext, node.getValue());
				listNo.add(model);
			} else if (node.getGoal().equals(search) && !node.getStart().equals(backStep)) {
				String searchNext = node.getStart();
				NodeTemp model = new NodeTemp(searchNext, node.getValue());
				listNo.add(model);
			}
		}
		
		return listNo;
	}
	
	private static List<List<NodeTemp>> savePoint(List<List<NodeTemp>> listSavePoint, List<NodeTemp> listMain, NodeTemp nodeTemp) {
		List<NodeTemp> listTemp = new ArrayList<>();
		listTemp.addAll(listMain);
		if (findShortestNode.isDuplicate(listTemp, nodeTemp)) {
			listTemp.add(nodeTemp);
			listSavePoint.add(listTemp);
		}
		return listSavePoint;
	}
	
	private boolean isDuplicate(List<NodeTemp> listTemp, NodeTemp nodeTemp) {
		for (NodeTemp nodeHist : listTemp) {
			if (nodeHist.getName().equals(nodeTemp.getName())) {
				return false;
			}
		}
		return true;
	}
	
	public List<NodeTemp> findShortestList(List<List<NodeTemp>> listPossible) {
		
		if (listPossible.isEmpty()) {
			return new ArrayList<>();
		}
		
		List<NodeTemp> listShortest = new ArrayList<>();
		boolean checkFirstTotal = true;
		int total = 0;
		int shortestCost = 0;
		boolean first = true;
		StringBuilder sb = new StringBuilder();
		for (List<NodeTemp> listNo : listPossible) {
			for (NodeTemp result : listNo) {
				
				if (first) {
					sb.append(result.getName() + "(" + result.getValue() + ")");
					first = false;
				} else {
					sb.append(" -> " + result.getName() + "(" + result.getValue() + ")");
				}
				
				total = total + result.getValue();
			}
			System.out.println(sb.toString() + ", cost: " + total);
			
			if (checkFirstTotal) {
				shortestCost = total;
				checkFirstTotal = false;
				listShortest.addAll(listNo);
			} else {
				if (shortestCost > total) {
					shortestCost = total;
					listShortest = new ArrayList<>();
					listShortest.addAll(listNo);
				}
			}
			total = 0;
			first = true;
			sb = new StringBuilder();
		}
		return listShortest;
	}
	
	public int printValue(InputModel input, List<NodeTemp> listShortest) {
		StringBuilder sb = new StringBuilder();
		boolean first = true;
		int total = 0;
		for (NodeTemp result : listShortest) {
			if (first) {
				sb.append(result.getName() + "(" + result.getValue() + ")");
				first = false;
			} else {
				sb.append(" -> " + result.getName() + "(" + result.getValue() + ")");
			}
			total = total + result.getValue();
		}
		System.out.println("Path from " + input.getStart() + " to " + input.getGoal() + " is " + sb.toString() + ", and have cost " + total + ".");
		return total;
	}
}
