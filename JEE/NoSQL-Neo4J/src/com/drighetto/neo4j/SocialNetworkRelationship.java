package com.drighetto.neo4j;

import org.neo4j.graphdb.RelationshipType;

/**
 * We define here relationship types between 2 nodes of the graph database
 * 
 * @author Dominique Righetto (dominique.righetto@gmail.com)
 * 
 */
public enum SocialNetworkRelationship implements RelationshipType {

	/** Teammate relationship */
	TEAMMATE,
	/** Friend relationship */
	FRIEND;

}
