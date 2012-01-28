package com.drighetto.neo4j;

import java.util.Iterator;

import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.RelationshipType;
import org.neo4j.graphdb.ReturnableEvaluator;
import org.neo4j.graphdb.StopEvaluator;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.Traverser;
import org.neo4j.graphdb.Traverser.Order;
import org.neo4j.kernel.EmbeddedGraphDatabase;

/**
 * Class in order to show a sample of use of the NEO4J API in order to use a
 * NoSQL graph database.<br>
 * <br>
 * In this sample we will represents a social network...
 * 
 * @author Dominique Righetto (dominique.righetto@gmail.com)
 * 
 */
public class SocialNetworkSample {

	/**
	 * Entry point
	 * 
	 * @param args
	 *        Command line argument
	 * @throws Exception
	 */
	@SuppressWarnings("boxing")
	public static void main(String[] args) throws Exception {

		/* Create or load the database content from a filesystem storage */
		final GraphDatabaseService graphDatabase = new EmbeddedGraphDatabase(System.getProperty("user.dir") + "/neo4j-graphdb");

		/* Add a JVM shutdown hook in order to stop cleanly the database */
		Runtime.getRuntime().addShutdownHook(new Thread() {
			/**
			 * Stop cleanly the database <br>
			 * 
			 * {@inheritDoc}
			 * 
			 * @see java.lang.Thread#run()
			 */
			@Override
			public void run() {
				System.out.print("\n==> Shutdown cleanly the database...");
				graphDatabase.shutdown();
				System.out.println("OK");
			}
		});

		/* Add nodes (members in our sample) and relationships between them */
		// Create graph database nodes using the database reference object
		Node memberA = createNode(graphDatabase, "Dominique");
		Node memberB = createNode(graphDatabase, "Tony");
		Node memberC = createNode(graphDatabase, "Guillaume");
		Node memberD = createNode(graphDatabase, "Sebastien");
		// Link nodes between them by adding relationships
		// ---Relationship owned by the MemberA node
		Relationship r01 = createRelationship(graphDatabase, memberA, memberB, SocialNetworkRelationship.FRIEND);
		Relationship r02 = createRelationship(graphDatabase, memberA, memberC, SocialNetworkRelationship.TEAMMATE);
		// ---Relationship owned by the MemberB node
		Relationship r03 = createRelationship(graphDatabase, memberB, memberC, SocialNetworkRelationship.TEAMMATE);
		// ---Relationship owned by the MemberC node
		Relationship r04 = createRelationship(graphDatabase, memberC, memberB, SocialNetworkRelationship.FRIEND);
		// ---Relationship owned by the MemberD node
		Relationship r05 = createRelationship(graphDatabase, memberD, memberC, SocialNetworkRelationship.FRIEND);
		// Display relationship created above
		System.out.println("*** MEMBERS RELATIONSHIPS CREATED ***");
		displayRelationship(r01);
		displayRelationship(r02);
		displayRelationship(r03);
		displayRelationship(r04);
		displayRelationship(r05);

		/* Perform selections on the graph database */
		System.out.println("\n*** MEMBERS RELATIONSHIPS SELECTION ***");
		// Use a transaction for selections because it's required by Neo4J...
		Transaction transaction = graphDatabase.beginTx();

		// Find a node according to a property value (search for Tony member)
		System.out.println("** Find a node according to a property value (search for Tony member)");
		Iterable<Node> members = graphDatabase.getAllNodes();
		long tonyId = -1;
		for (Node member : members) {
			if (member.hasProperty("MemberName") && "Tony".equals(member.getProperty("MemberName"))) {
				System.out.printf("Tony found, is node ID is %s\n", member.getId());
				tonyId = member.getId();
			}
		}

		// Get all relationships of a specific node (of Tony member in this
		// case)
		System.out.println("** Get all relationships of a specific node (of Tony member in this case)");
		// --Get directory access to the node using is unique ID
		Node tonyNode = graphDatabase.getNodeById(tonyId);
		// --Get is relationships in both directions (from this node to another
		// node (OUTGOING direction name) and another node to this node
		// (INCOMING direction name))
		Iterator<Relationship> relationships = tonyNode.getRelationships(Direction.BOTH).iterator();
		while (relationships.hasNext()) {
			displayRelationship(relationships.next());
		}

		// Use a traverser to traverse all specific relationships that ending to
		// a specific node (our goal here is to find all teamate member of
		// Guillaume)
		System.out.println("** Use a traverser to traverse all specific relationships that ending to a specific node (our goal here is to find all teamate member of Guillaume)");
		Node guillaumeNode = graphDatabase.getNodeById(3);
		Traverser teammates = guillaumeNode.traverse(Order.BREADTH_FIRST, StopEvaluator.END_OF_GRAPH, ReturnableEvaluator.ALL_BUT_START_NODE, SocialNetworkRelationship.TEAMMATE, Direction.INCOMING);
		for (Node teammateMember : teammates) {
			System.out.printf("%s (NodeID=%s)\n", teammateMember.getProperty("MemberName"), teammateMember.getId());
		}

		// Finalize transaction
		// If success() method isn't call (it's our case because we only
		// perforrm read operations) the transaction is rolledback on finish()
		// method invocation...
		transaction.finish();
	}

	/**
	 * Create a new node and set a property representing the member name.<br>
	 * All operations that work with the graph (be it read or write operations)
	 * must be performed in a transaction. It's required by NEO4J.<br>
	 * <br>
	 * See http://wiki.neo4j.org/content/Transactions#The_basics
	 * 
	 * @param graphDatabase
	 *        The source graph database
	 * @param memberName
	 *        Member name
	 * @return a object representing the new created node
	 * @throws Exception
	 */
	private static Node createNode(GraphDatabaseService graphDb, String memberName) throws Exception {
		Node newNode = null;
		// Create and start a new transaction
		Transaction tx = graphDb.beginTx();
		try {
			// First search in the DB if the node do not already exists...
			Iterable<Node> members = graphDb.getAllNodes();
			for (Node member : members) {
				if (member.hasProperty("MemberName") && memberName.equals(member.getProperty("MemberName"))) {
					newNode = member;
					break;
				}
			}
			// Seconds try only to the create a new node if the node do not
			// exists in the DB
			if (newNode == null) {
				newNode = graphDb.createNode();
				// Set a property on the node representing the member name
				newNode.setProperty("MemberName", memberName);
			}
			// Mark the transaction as succeed
			tx.success();
		} catch (Exception e) {
			// Mark the transaction as failed
			tx.failure();
			throw e;
		} finally {
			// Mark the transactin as ended
			tx.finish();
		}
		return newNode;
	}

	/**
	 * Create a relationship between 2 nodes.<br>
	 * All operations that work with the graph (be it read or write operations)
	 * must be performed in a transaction. It's required by NEO4J.<br>
	 * <br>
	 * See http://wiki.neo4j.org/content/Transactions#The_basics
	 * 
	 * @param graphDatabase
	 *        The source graph database
	 * @param ownerNode
	 *        Source node of the relationship
	 * @param counterpartNode
	 *        Target node of the relationship
	 * @param relationshipType
	 *        Type of the relationship
	 * @return a object representing the relationship
	 * @throws Exception
	 */
	private static Relationship createRelationship(GraphDatabaseService graphDb, Node ownerNode, Node counterpartNode, RelationshipType relationshipType) throws Exception {
		Relationship newRelationship = null;
		// Create and start a new transaction
		Transaction tx = graphDb.beginTx();
		try {
			// First check if the relationship do not already exists
			Iterator<Relationship> relationships = ownerNode.getRelationships(relationshipType, Direction.OUTGOING).iterator();
			while (relationships.hasNext()) {
				Relationship r = relationships.next();
				if (r.getEndNode().getProperty("MemberName").equals(counterpartNode.getProperty("MemberName"))) {
					newRelationship = r;
					break;
				}
			}
			// Seconds try only to the create a new relationship from the
			// owner node to the
			// target node if the relationships do not already exists in the DB
			if (newRelationship == null) {
				newRelationship = ownerNode.createRelationshipTo(counterpartNode, relationshipType);
			}
			// Mark the transaction as succeed
			tx.success();
		} catch (Exception e) {
			// Mark the transaction as failed
			tx.failure();
			throw e;
		} finally {
			// Mark the transactin as ended
			tx.finish();
		}
		return newRelationship;
	}

	/**
	 * Display details of a relationship
	 * 
	 * @param r
	 *        the source relationship
	 * @throws Exception
	 */
	private static void displayRelationship(Relationship r) throws Exception {
		System.out.printf("%s --[%s]--> %s\n", r.getStartNode().getProperty("MemberName"), r.getType(), r.getEndNode().getProperty("MemberName"));
	}

}
