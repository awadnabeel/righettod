package net.mlw.vlh.adapter.toplink;

import net.mlw.vlh.DefaultListBackedValueList;
import net.mlw.vlh.ValueList;
import net.mlw.vlh.ValueListInfo;
import net.mlw.vlh.adapter.AbstractValueListAdapter;
import oracle.toplink.queryframework.DatabaseQuery;
import oracle.toplink.sessions.Session;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.toplink.ServerSessionFactory;

import java.util.List;
import java.util.Vector;

/**
 * Oracle Toplink ORM adapter for VLH
 * 
 * @author Dominique RIGHETTO<br>
 * @version 1.0
 * 
 * <br>
 * <br>
 * <b>Note :</b> We have used the class <i>java.util.Vector</i> because
 * Toplink use <i>Vector</i> class for collection storing. <br>
 * <br>
 * <b>Functionalities supported :</b>
 * <ul>
 * <li>NamedQueries</li>
 * </ul>
 * <br>
 * <b>Functionalities to add :</b>
 * <ul>
 * <li>Support DatabaseQuery (with DatabaseQuery object instance)</li>
 * <li>Support for paging read on Toplink side</li>
 * <li>Support for focus</li>
 * </ul>
 */
public class ToplinkAdapter extends AbstractValueListAdapter {

	/** Commons logger */
	private static final Log LOGGER = LogFactory.getLog(ToplinkAdapter.class);

	/** Toplink sessionFactory factory */
	private ServerSessionFactory sessionFactory = null;

	/**
	 * Name of the class of object extracted by the named query or database
	 * query
	 */
	private String domainClass = null;

	/** Name of the named query to execute */
	private String namedQuery = null;

	/** Flag to indicate if named query use parameters */
	private boolean namedQueryUseParameters = false;

	/** Parameters list for named query separated by a pipe */
	private String namedQueryParameters = null;

	/** Toplink database query to execute */
	private DatabaseQuery databaseQuery = null;

	/**
	 * Getter for databaseQuery<br>
	 * 
	 * @author Dominique RIGHETTO<br>
	 *         18 juil. 07<br>
	 * @return the databaseQuery
	 */
	public DatabaseQuery getDatabaseQuery() {
		return this.databaseQuery;
	}

	/**
	 * Setter for databaseQuery<br>
	 * 
	 * @author Dominique RIGHETTO<br>
	 *         18 juil. 07<br>
	 * @param tplDatabaseQuery
	 *            the databaseQuery to set
	 */
	public void setDatabaseQuery(DatabaseQuery tplDatabaseQuery) {
		this.databaseQuery = tplDatabaseQuery;
	}

	/**
	 * Getter for namedQuery<br>
	 * 
	 * @author Dominique RIGHETTO<br>
	 *         18 juil. 07<br>
	 * @return the namedQuery
	 */
	public String getNamedQuery() {
		return this.namedQuery;
	}

	/**
	 * Setter for namedQuery<br>
	 * 
	 * @author Dominique RIGHETTO<br>
	 *         18 juil. 07<br>
	 * @param tplNamedQuery
	 *            the namedQuery to set
	 */
	public void setNamedQuery(String tplNamedQuery) {
		this.namedQuery = tplNamedQuery;
	}

	/**
	 * Getter for namedQueryParameters<br>
	 * 
	 * @author Dominique RIGHETTO<br>
	 *         18 juil. 07<br>
	 * @return the namedQueryParameters
	 */
	public String getNamedQueryParameters() {
		return this.namedQueryParameters;
	}

	/**
	 * Setter for namedQueryParameters<br>
	 * 
	 * @author Dominique RIGHETTO<br>
	 *         18 juil. 07<br>
	 * @param tplNnamedQueryparameters
	 *            the namedQueryParameters to set
	 */
	public void setNamedQueryParameters(String tplNnamedQueryparameters) {
		this.namedQueryParameters = tplNnamedQueryparameters;
	}

	/**
	 * Setter for sessionFactory<br>
	 * 
	 * @author Dominique RIGHETTO<br>
	 *         18 juil. 07<br>
	 * @param tplSessionFactory
	 *            the sessionFactory to set
	 */
	public void setSessionFactory(ServerSessionFactory tplSessionFactory) {
		this.sessionFactory = tplSessionFactory;
	}

	/**
	 * Getter for domainClass<br>
	 * 
	 * @author Dominique RIGHETTO<br>
	 *         18 juil. 07<br>
	 * @return the domainClass
	 */
	public String getDomainClass() {
		return this.domainClass;
	}

	/**
	 * Setter for domainClass<br>
	 * 
	 * @author Dominique RIGHETTO<br>
	 *         18 juil. 07<br>
	 * @param tplNamedQueryDomainClass
	 *            the domainClass to set
	 */
	public void setDomainClass(String tplNamedQueryDomainClass) {
		this.domainClass = tplNamedQueryDomainClass;
	}

	/**
	 * Getter for namedQueryUseParameters<br>
	 * 
	 * @author Dominique RIGHETTO<br>
	 *         18 juil. 07<br>
	 * @return the namedQueryUseParameters
	 */
	public boolean isNamedQueryUseParameters() {
		return this.namedQueryUseParameters;
	}

	/**
	 * Setter for namedQueryUseParameters<br>
	 * 
	 * @author Dominique RIGHETTO<br>
	 *         18 juil. 07<br>
	 * @param tplNamedQueryUseParameters
	 *            the namedQueryUseParameters to set
	 */
	public void setNamedQueryUseParameters(boolean tplNamedQueryUseParameters) {
		this.namedQueryUseParameters = tplNamedQueryUseParameters;
	}

	/**
	 * @see net.mlw.vlh.ValueListAdapter#getValueList(java.lang.String,
	 *      net.mlw.vlh.ValueListInfo) <br>
	 *      <br>
	 *      {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public ValueList getValueList(String name, ValueListInfo info) {
		int numberPerPage = 0;
		Object tplReturn = null;
		Session tplSession = null;
		List list = null;
		ValueList returnValueList = null;
		Vector<String> params = null;
		String tmpStr = null;

		try {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("ToplinkAdapter.getValueList : '" + name
						+ "' start...");
			}

			/* Check factory object validity */
			if (this.sessionFactory == null) {
				throw new IllegalArgumentException(
						"Toplink session factory can't be null !");
			}

			/* Configure ValueListInfo object */
			// Sorting
			if (info.getSortingColumn() == null) {
				info.setPrimarySortColumn(getDefaultSortColumn());
				info.setPrimarySortDirection(getDefaultSortDirectionInteger());
				if (LOGGER.isDebugEnabled()) {
					LOGGER.debug("The default sort column '"
							+ getDefaultSortColumn() + "' with direction '"
							+ getDefaultSortDirectionInteger() + "' was  set.");
				}
			}

			// Paging
			numberPerPage = info.getPagingNumberPer();
			if (numberPerPage == Integer.MAX_VALUE) {
				numberPerPage = getDefaultNumberPerPage();
				info.setPagingNumberPer(numberPerPage);
				if (LOGGER.isDebugEnabled()) {
					LOGGER.debug("The paging number per page '" + numberPerPage
							+ "' was  set.");
				}
			}

			/* Extract data from database */
			// Check type of extraction : DatabaseQuery or NamedQuery
			if ((this.databaseQuery == null)
					&& (this.namedQuery == null || "".equals(this.namedQuery))) {
				throw new IllegalArgumentException(
						"One type of database operation must be setted : 'databaseQuery' and 'namedQuery' can't both be null !");
			}
			// Check parameter validty
			if (this.domainClass == null || "".equals(this.domainClass)) {
				throw new IllegalArgumentException(
						"'domainClass' can't be null !");
			}

			// Acquire Toplink session
			tplSession = this.sessionFactory.createSession();
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Toplink session '" + tplSession.getName()
						+ "' acquired !");
			}

			// Data extraction using DatabaseQuery
			if (this.databaseQuery != null) {
				tplReturn = tplSession.executeQuery(this.databaseQuery);
			}
			// Data extraction using NamedQuery
			else if (this.namedQuery != null && !"".equals(this.namedQuery)) {
				// We check if NamedQuery use parameter
				if (!this.namedQueryUseParameters) {
					tplReturn = tplSession.executeQuery(this.namedQuery);
				} else {
					// We check to see if NamedQuery parameters are passed by
					// HTTP request instead of XML configuration
					// HTTP request parameter have higher priority on XML
					// configuration. Parameters defined in XML configuration as
					// consédered as default parameter if no parameter is find
					// in HTTP request
					if ((info.getFilters().get("namedQueryParameters") != null)
							&& !"".equals(info.getFilters().get(
									"namedQueryParameters"))) {
						tmpStr = (String) info.getFilters().get(
								"namedQueryParameters");
					} else {
						tmpStr = (this.namedQueryParameters == null) ? ""
								: this.namedQueryParameters;
					}
					// Build parameters array
					params = new Vector<String>();
					if (tmpStr.indexOf("|") != -1) {
						for (String str : tmpStr.split("\\|")) {
							params.add(str);
						}
					} else {
						params.add(tmpStr);
					}
					tplReturn = tplSession.executeQuery(this.namedQuery, Class
							.forName(this.domainClass.trim()), params);
				}
			}
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Database request executed !");
			}

			// Process output object
			// The database request have extracted a collection of object
			if (tplReturn instanceof Vector) {
				list = (Vector) tplReturn;
			} else {
				// The database request have extracted a single object or nothing
				list = new Vector();
				if (tplReturn != null) {
					list.add(tplReturn);
				}
			}
			info.setTotalNumberOfEntries(list.size());
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Output object processed !");
			}

			// Prepare returned object
			returnValueList = new DefaultListBackedValueList(list, info);
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Retrieved list was wrapped in valuelist, info = "
						+ info);
			}

		} catch (Exception e) {
			// Log exception
			e.printStackTrace();
			LOGGER.error(e);
		} finally {
			// Release session
			if (tplSession != null) {
				tplSession.release();
				if (LOGGER.isDebugEnabled()) {
					LOGGER.debug("Toplink session '" + tplSession.getName()
							+ "' released !");
				}
			}
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("ToplinkAdapter.getValueList : '" + name
						+ "' end.");
			}
		}

		return returnValueList;
	}
}
