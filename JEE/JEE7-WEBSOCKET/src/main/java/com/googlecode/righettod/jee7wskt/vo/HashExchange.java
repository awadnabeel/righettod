package com.googlecode.righettod.jee7wskt.vo;

/**
 * Value object class used to store hash request and response.
 *
 * @author Dominique Righetto (dominique.righetto@gmail.com)
 */
public class HashExchange {

    /**
     * Hashing algorithm
     */
    private String algorithm = null;

    /**
     * Original data
     */
    private String originalData = null;

    /**
     * Hashed data
     */
    private String hashedData = null;

    /**
     * Flag to indicate if the handler was secured
     */
    private boolean fromSecuredHandler = false;

    public String getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

    public String getOriginalData() {
        return originalData;
    }

    public void setOriginalData(String originalData) {
        this.originalData = originalData;
    }

    public String getHashedData() {
        return hashedData;
    }

    public void setHashedData(String hashedData) {
        this.hashedData = hashedData;
    }

    public boolean isFromSecuredHandler() {
        return fromSecuredHandler;
    }

    public void setFromSecuredHandler(boolean fromSecuredHandler) {
        this.fromSecuredHandler = fromSecuredHandler;
    }

}
